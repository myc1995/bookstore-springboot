package com.myc.controller;

import com.myc.entities.Address;
import com.myc.mapper.AddressMapper;
import com.myc.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class AddressController {

    @Autowired
    AddressMapper addressMapper;

    @Autowired
    AddressService addressService;

    @RequestMapping("/getAddressList/{userId}")
    public String getAddressList(@PathVariable("userId") Integer userId, Model model) {
        List<Address> addresses = addressService.handleGetAddressList(userId);
        model.addAttribute("addressList", addresses);
        return "address/list";
    }

    @RequestMapping("/getAddressCount/{userId}")
    public int countAddress(@PathVariable("userId") Integer userId) {
        return addressMapper.count(userId);
    }

    @RequestMapping("/cancelDefaultAddress/{userId}")
    public int cancelDefaultAddress(@PathVariable("userId") Integer userId) {
        addressMapper.cancelExistingDefault(userId);
        return 1;
    }

    @RequestMapping("/addAddress/{Address}")//error
    public int addAddress(@PathVariable("Address") Address address) {
        return 1;
    }

    @RequestMapping("/findAddressById/{userId}")
    public List<Address> findAddressById(@PathVariable("userId") Integer userId) {
        return addressMapper.findById(userId);
    }
}
