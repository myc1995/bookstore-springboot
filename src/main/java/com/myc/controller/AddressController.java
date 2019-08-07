package com.myc.controller;

import com.myc.bean.Address;
import com.myc.mapper.AddressMapper;
import com.myc.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
public class AddressController {

    @Autowired
    AddressMapper addressMapper;

    @Autowired
    AddressService addressService;

    @RequestMapping("/getAddressList/{userId}")
    public List<Address> getAddressList(@PathVariable("userId") Integer userId) {
        return addressMapper.list(userId);
    }

    @RequestMapping("/getAddressCount/{userId}")
    public int countAddress(@PathVariable("userId") Integer userId) {
        return addressMapper.count(userId);
    }

    @RequestMapping("/cancelDefaultAddress/{userId}")
    public int cancelDefaultAddress(@PathVariable("userId") Integer userId) {
        return addressMapper.cancelExistingDefault(userId);
    }

    @RequestMapping("/addAddress/{Address}")//error
    public int addAddress(@PathVariable("Address") Address address) {
        return addressService.handleAddAddress(address);
    }

    @RequestMapping("/findAddressById/{userId}")
    public List<Address> findAddressById(@PathVariable("userId") Integer userId) {
        return addressMapper.findById(userId);
    }
}
