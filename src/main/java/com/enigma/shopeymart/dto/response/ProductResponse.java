package com.enigma.shopeymart.dto.response;

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
public class ProductResponse {
    private String productId;
    private String productName;
    private String description;
    private Long price;
    private Integer stock;
    private StoreResponse store;
}
