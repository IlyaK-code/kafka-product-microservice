package ru.ws.productMicroservice.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreatedEvent {
    private String productId;
    private String title;
    private String price;
    private String quantity;

}
