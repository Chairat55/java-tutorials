package com.example.springthailandtambon.controllers;

import com.example.springthailandtambon.dtos.AddressDTO;
import com.example.springthailandtambon.entities.District;
import com.example.springthailandtambon.entities.Province;
import com.example.springthailandtambon.entities.Subdistrict;
import com.example.springthailandtambon.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/search")
    public AddressDTO search(
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return addressService.search(search, page, size);
    }

    @GetMapping("/provinces")
    public List<Province> getProvinces(){
        return addressService.getProvinces();
    }

    @GetMapping("/districts")
    public List<District> getDistricts(int provinceId){
        return addressService.getDistricts(provinceId);
    }

    @GetMapping("/subdistricts")
    public List<Subdistrict> getSubdistricts(int districtId){
        return addressService.getSubdistricts(districtId);
    }

}
