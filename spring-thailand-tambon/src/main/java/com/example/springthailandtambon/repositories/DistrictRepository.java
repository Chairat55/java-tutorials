package com.example.springthailandtambon.repositories;

import com.example.springthailandtambon.entities.District;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DistrictRepository extends JpaRepository<District, Integer> {

    List<District> findAllByProvinceId(int provinceId);

}
