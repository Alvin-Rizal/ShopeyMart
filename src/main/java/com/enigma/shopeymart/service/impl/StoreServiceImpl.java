package com.enigma.shopeymart.service.impl;

import com.enigma.shopeymart.dto.request.StoreRequest;
import com.enigma.shopeymart.dto.response.StoreResponse;
import com.enigma.shopeymart.entity.Store;
import com.enigma.shopeymart.repository.StoreRepository;
import com.enigma.shopeymart.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    //keyword final harus dibuatkan agar bean dapat dibuatkan dan data tidak null.
    private final StoreRepository storeRepository;
    @Override
    public Store create(Store store) {
        return storeRepository.save(store);
    }

    @Override
    public Store getByID(String id) {
        return storeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Store> getAll() {
        return storeRepository.findAll();
    }

    @Override
    public Store update(Store store) {
        Store currentStoreId = getByID(store.getId());
        if(currentStoreId!= null){
        return storeRepository.save(store);
        }
        return null;
    }

    @Override
    public void delete(String id) {
        storeRepository.deleteById(id);

    }
    //Program Contoh Untuk Soft Delete, rubah variable setName menjadi setIsActive
    public void softDelete(String id) {
        Store store = this.getByID(id);
        store.setName("0");
        storeRepository.save(store);
    }

    @Override
    public StoreResponse create(StoreRequest storeRequest) {
        Store store = Store.builder()
                .name(storeRequest.getName())
                .noSiup(storeRequest.getNoSiup())
                .address(storeRequest.getAddress())
                .mobilePhone(storeRequest.getMobilePhone())
                .build();
        storeRepository.save(store);
        return StoreResponse.builder()
                .storeName(store.getName())
                .noSiup(store.getNoSiup())
                .Phone(store.getMobilePhone())
                .build(); //menyiapkan tempat mapping

    }
}
