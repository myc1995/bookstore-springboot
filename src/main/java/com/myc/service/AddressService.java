package com.myc.service;

import com.myc.entities.Address;
import com.myc.mapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    AddressMapper addressMapper;

    public List<Address> handleGetAddressList(Integer userId) {
        List<Address> res = addressMapper.list(userId);
        return res;
    }
}
