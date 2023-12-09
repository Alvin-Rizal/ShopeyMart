package com.enigma.shopeymart.controller;

import com.enigma.shopeymart.constant.AppPath;
import com.enigma.shopeymart.dto.request.StoreRequest;
import com.enigma.shopeymart.dto.response.StoreResponse;
import com.enigma.shopeymart.entity.Store;
import com.enigma.shopeymart.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.STORE)  //untuk mapping utama. Tidak perlu untuk membuat /store lagi di setiap  mapping
public class StoreController {
    private final StoreService storeService;
    @PostMapping
    public Store createStore(@RequestBody Store store){
        return storeService.create(store);
    }
    @GetMapping
    public List<Store> getAll(){
        return storeService.getAll();
    }
    @PutMapping(value = "/{id}")
    public Store updateStore(@PathVariable String id, @RequestBody Store store){
        store.setId(id);
        return storeService.update(store);
    }
//    Metode Tidak menggunakan Id
//    @PutMapping(value = "/store")
//    public Store updateStore(@RequestBody Store store){
//        return storeService.update(store);
//    }

    @DeleteMapping(value = "/{id}")
    public void deleteStore(@PathVariable String id){
        storeService.delete(id);
    }
    @GetMapping(value = "/{id}")
    public Store getByIdStore(@PathVariable String id){
        return storeService.getByID(id);
    }
//    public Store getByIdStore()

//    @DeleteMapping
//    public void deleteSoft(String id) {
//
//    }

    @PostMapping("/v1")
    public StoreResponse createStores(@RequestBody StoreRequest storeRequest){
        return storeService.create(storeRequest);
    }


}
