package com.enigma.shopeymart.dto.request;

import com.enigma.shopeymart.entity.Store;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ProductRequest {
    private String id;
    @NotBlank(message = "product name is required")
    private String name;
    @NotBlank(message = "product description is required")
    private String description;
    @NotBlank(message = "product price is required")
    private Long price;
    @NotBlank(message = "product stock is required")
    private Integer stock;
    @NotBlank(message = "Id Store is required")
    private Store storeId;
}
