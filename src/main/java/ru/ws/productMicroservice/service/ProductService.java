package ru.ws.productMicroservice.service;

import ru.ws.productMicroservice.dto.CreateProductDto;

public interface ProductService {
    String createProduct(CreateProductDto createProductDto);
}
