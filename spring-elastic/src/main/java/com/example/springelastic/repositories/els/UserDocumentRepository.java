package com.example.springelastic.repositories.els;

import com.example.springelastic.entities.documents.UserDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@EnableElasticsearchRepositories
public interface UserDocumentRepository extends ElasticsearchRepository<UserDocument, Integer> {

    List<UserDocument> findAll();

    @Query("SELECT * FROM users WHERE username LIKE %:username%")
    List<UserDocument> findAllByUsername(String username);

}
