package com.enigma.shopeymart.dto.request;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class StoreRequest { //Di Mapping Ke Entity . Dimasukan sesuai dengan file Entity nama field nya
    private String id;
    private String noSiup;
    private String name;
    private String address;
    private String mobilePhone;
}
