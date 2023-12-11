package com.enigma.shopeymart.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder(toBuilder = true)
    public class ProductPriceResponse {
        private String id;
        private Integer productStock;
        private Boolean productIsActive;
        private Long productPrice;
    }
