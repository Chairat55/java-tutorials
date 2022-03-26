package com.example.springnativequery.services;

import com.example.springnativequery.dtos.UserDTO;
import com.example.springnativequery.entities.User;
import com.example.springnativequery.repositories.UserRepository;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager em;

    public void initUser () {
        for (int i = 1; i <= 5; i++) {
            userRepository.saveAndFlush(
                    new User(
                            "User_" + i,
                            "FirstName" + i,
                            "LastName_" + i,
                            "Email_" + i
                    )
            );
        }
    }

    public List<User> list() {
        return userRepository.findAll();
    }

    public List<UserDTO> listFromNQ() {
        String sql = "SELECT id, user_name as username FROM users";
        Query query = em.createNativeQuery(sql).unwrap(org.hibernate.query.Query.class)
                .setResultTransformer(new AliasToBeanResultTransformer(UserDTO.class));

        return query.getResultList();
    }

}