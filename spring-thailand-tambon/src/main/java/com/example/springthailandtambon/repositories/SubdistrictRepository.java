package com.example.springthailandtambon.repositories;

import com.example.springthailandtambon.entities.Subdistrict;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubdistrictRepository extends JpaRepository<Subdistrict, Integer> {

    List<Subdistrict> findAllByDistrictId(int districtId);

}
