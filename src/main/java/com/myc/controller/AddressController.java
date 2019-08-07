package com.myc.controller;

import com.myc.bean.Address;
import com.myc.mapper.AddressMapper;
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

    @RequestMapping("/Address/{userId}")
    public List<Address> getAddressList(@PathVariable("userId") Integer userId) {
        return addressMapper.list(userId);
    }

    @RequestMapping("CountAddress/{userId}")
    public String count(@PathVariable("userId") Integer userId) {
        return Integer.toString(addressMapper.count(userId));
    }
}
