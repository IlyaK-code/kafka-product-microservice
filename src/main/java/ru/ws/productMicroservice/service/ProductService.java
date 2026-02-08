package ru.ws.productMicroservice.service;

import ru.ws.productMicroservice.dto.CreateProductDto;

import java.util.concurrent.ExecutionException;

public interface ProductService {
    String createProduct(CreateProductDto createProductDto) throws ExecutionException, InterruptedException;
}
