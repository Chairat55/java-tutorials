package com.example.springthailandtambon.services;

import com.example.springthailandtambon.dtos.AddressDTO;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

@Service
public class AddressService {

    @Autowired
    private EntityManager em;

    public AddressDTO search(String search, int page, int size) {
        String sqlSelectCount = "SELECT COUNT(p.id) ";

        String sqlSelect = "SELECT p.id as provinceid " +
                "   , p.name_th as provinceNameTh " +
                "   , p.name_en as provinceNameEn " +
                "   , d.id as districtId " +
                "   , d.name_th as districtNameTh " +
                "   , d.name_en as districtNameEn " +
                "   , s.id as subdistrictId " +
                "   , s.name_th as subdistrictNameTh " +
                "   , s.name_en as subdistrictNameEn " +
                "   , s.zipcode as zipcode ";

        String sqlFrom = "FROM provinces p " +
                "JOIN districts d ON d.province_id = p.id " +
                "JOIN subdistricts s ON s.district_id = d.id " +
                "WHERE p.name_th LIKE :search " +
                "   OR d.name_th LIKE :search " +
                "   OR s.name_th LIKE :search " +
                "   OR s.zipcode LIKE :search ";

        sqlSelectCount += sqlFrom;
        sqlSelect += sqlFrom + " ORDER BY p.name_th";

        Query queryCount = em.createNativeQuery(sqlSelectCount);
        queryCount.setParameter("search", "%" + search + "%");

        int totalResult = ((BigInteger) queryCount.getSingleResult()).intValue();

        Query query = em.createNativeQuery(sqlSelect).unwrap(org.hibernate.query.Query.class)
                .setResultTransformer(new AliasToBeanResultTransformer(AddressDTO.Address.class));
        query.setParameter("search", "%" + search + "%");
        query.setFirstResult((page - 1) * size);
        query.setMaxResults(page * size);

        List<AddressDTO.Address> result = query.getResultList();

        int totalPage = (int) Math.ceil(totalResult / (size + 0.0));

        return new AddressDTO(page, size, totalPage, totalResult, result);
    }

}
