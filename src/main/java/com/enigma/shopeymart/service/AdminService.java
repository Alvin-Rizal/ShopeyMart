package com.enigma.shopeymart.service;

import com.enigma.shopeymart.dto.response.AdminResponse;
import com.enigma.shopeymart.entity.Admin;

public interface AdminService {
    AdminResponse createNewAdmin(Admin admin);
}
