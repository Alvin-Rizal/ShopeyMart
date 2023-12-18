package com.enigma.shopeymart.service.impl;

import com.enigma.shopeymart.dto.request.StoreRequest;
import com.enigma.shopeymart.dto.response.StoreResponse;
import com.enigma.shopeymart.entity.Store;
import com.enigma.shopeymart.repository.StoreRepository;
import com.enigma.shopeymart.service.StoreService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class StoreServiceImplTest {
    //Mock storeRepository
    @Mock
    private final StoreRepository storeRepository = mock(StoreRepository.class);
    private StoreServiceImpl storeService = new StoreServiceImpl(storeRepository);


//    public void setUp(){
//        MockitoAnnotations.initMocks(this);
//        storeService = new StoreServiceImpl(storeRepository);
//    }
    @Test
    void itShouldReturnStoreWhenCreatedNewStore() {
        StoreRequest dummyStore = new StoreRequest();
        dummyStore.setId("1234");
        dummyStore.setName("Jaya Selalu");

        StoreResponse dummnyStoreResponse = storeService.create(dummyStore);
        verify(storeRepository).save(any(Store.class));
//        assertEquals(dummyStore.getId() , dummnyStoreResponse.getId());
        assertEquals(dummyStore.getName(),dummnyStoreResponse.getStoreName());

        //
//        Store store = new Store();
//        verify(storeRepository, times(1)).save(store);

//        //mock behavior repository -> save
//        when(storeRepository.save(any(Store.class))).thenReturn(dummyStore);
//
//        //perform then create operation
//        Store createStore = storeService.create(dummyStore);

        //verify repository

    }

    @Test
    void itShouldGetAllDataStoreWhenCallGetAll() {
        List<Store> dummyListStore = new ArrayList<>();
        dummyListStore.add(new Store("1","123","Berkah Selalu","Ragunan","123545"));
        dummyListStore.add(new Store("2","12334","Berkah Selalu","Ragunan1","123545234"));

        when(storeRepository.findAll()).thenReturn(dummyListStore);
        List<StoreResponse> retrivedStore = storeService.getAll();

        assertEquals(dummyListStore.size(),retrivedStore.size());

        for (int i=0;i<dummyListStore.size();i++) {
            assertEquals(dummyListStore.get(i).getName(),retrivedStore.get(i).getStoreName());
        }
    }

    @Test
    void ItShouldGetDataStoreWhenGeiByIdStore() {
        String storeId = "1";
        Store store = new Store("1","123","Berkah Selalu","Ragunan","123545");
        when(storeRepository.findById(storeId)).thenReturn(Optional.of(store));

        StoreResponse storeResponse = storeService.getById(storeId);

        verify(storeRepository).findById(storeId);
        assertNotNull(storeResponse);
        assertEquals(storeId,storeResponse.getId());
        assertEquals(store.getName(),storeResponse.getStoreName());
    }
    @Test
    void itShouldDeleteStoreDataWhenDeleteByIdStore() {
        String storeId = "1";
        Store store = new Store("1","123","Berkah Selalu","Ragunan","123545");
        when(storeRepository.findById(storeId)).thenReturn(Optional.of(store));
        storeService.delete(storeId);
        verify(storeRepository,times(1)).deleteById(storeId);
    }

    @Test
    void itShouldUpdateStoreWhenStoreUpdate() {
        String storeId = "1";
        Store dummyStore = new Store("1","123","Berkah Selalu","Ragunan","123545");
        StoreRequest store = new StoreRequest("1","123","Berkah Selalu","Ragunan","123545");
        when(storeRepository.findById(any(String.class))).thenReturn(Optional.of(dummyStore));
        when(storeRepository.save(any(Store.class))).thenReturn(dummyStore);
        StoreResponse storeResponse = storeService.update(store);
        verify(storeRepository,times(1)).findById(store.getId());
        verify(storeRepository,times(1)).save(dummyStore);

        assertNotNull(storeResponse);
        assertEquals(store.getId(),storeResponse.getId());
        assertEquals(store.getName(),storeResponse.getStoreName());
    }

}