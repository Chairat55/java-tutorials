package com.example.springelastic.repositories.els;

import com.example.springelastic.entities.documents.UserDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.util.List;

@EnableElasticsearchRepositories
public interface UserDocumentRepository extends ElasticsearchRepository<UserDocument, Integer> {

    List<UserDocument> findAll();

}
