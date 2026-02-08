package ru.ws.productMicroservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import ru.ws.productMicroservice.dto.CreateProductDto;
import ru.ws.productMicroservice.event.ProductCreatedEvent;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class ProductServiceImpl implements ProductService {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
    private KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;

    public ProductServiceImpl(KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public String createProduct(CreateProductDto createProductDto) throws ExecutionException, InterruptedException {
        //TODO save DB
        String productId = UUID.randomUUID().toString();

        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent(productId, createProductDto.getTitle(),
                createProductDto.getPrice(), createProductDto.getQuantity()
        );

        SendResult<String, ProductCreatedEvent> result = kafkaTemplate.send("product-created-event-topic", productId, productCreatedEvent).get();

        LOGGER.info("Topic: {}", result.getProducerRecord().topic());
        LOGGER.info("Partition: {}", result.getProducerRecord().partition());
        LOGGER.info("Offset: {}", result.getRecordMetadata().offset());

        LOGGER.info("Return: {}", productId);
        return productId;
    }
}
