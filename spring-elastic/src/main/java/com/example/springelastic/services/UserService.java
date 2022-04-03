package com.example.springelastic.services;

import com.example.springelastic.entities.documents.UserDocument;
import com.example.springelastic.entities.User;
import com.example.springelastic.repositories.UserRepository;
import com.example.springelastic.repositories.els.UserDocumentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDocumentRepository userDocumentRepository;

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

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

    public List<UserDocument> listFromElastic(String username) {
        Criteria criteriaBrand = new Criteria("username");
        if(!username.equals("")){
            criteriaBrand.is(username);
        }

        // TODO: Like ทำ setting search ไทย ด้วย

        // TODO: Less than, getter than

        // TODO: Between

        Query searchQuery = new CriteriaQuery(criteriaBrand);

        SearchHits<UserDocument> search = elasticsearchOperations
                .search(searchQuery,
                        UserDocument.class,
                        IndexCoordinates.of("users"));

        return search.stream().map(SearchHit::getContent).collect(Collectors.toList());
    }

    public void reIndex() {
        // Re-index
        this.userDocumentRepository.deleteAll();
        List<User> users = userRepository.findAll();
        logger.info("Start re-index users : " + users.size());

        List<UserDocument> userDocuments = new ArrayList<>();
        for(User u : users){
            userDocuments.add(new UserDocument(u.getId(), u.getUsername(), u.getFirstName(), u.getLastName(), u.getEmail()));
        }
        this.userDocumentRepository.saveAll(userDocuments);
    }

}