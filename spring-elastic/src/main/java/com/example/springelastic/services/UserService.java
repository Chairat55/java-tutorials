package com.example.springelastic.services;

import com.example.springelastic.entities.documents.UserDocument;
import com.example.springelastic.entities.User;
import com.example.springelastic.repositories.UserRepository;
import com.example.springelastic.repositories.els.UserDocumentRepository;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
    @Autowired
    private ModelMapper modelMapper;

    public void initUser() {
        Random rand = new Random();

        for (int i = 1; i <= 10; i++) {
            // Obtain a number between [0 - 99].
            int age = rand.nextInt(100);

            User user = new User(
                    "User_" + age,
                    "FirstName" + age,
                    "LastName_" + age,
                    "Email_" + age,
                    age
            );
            userRepository.saveAndFlush(user);

            UserDocument userDocument = new UserDocument();
            modelMapper.map(user, userDocument);
            userDocumentRepository.save(userDocument);
        }
    }

    public List<User> list() {
        return userRepository.findAll();
    }

    public List<UserDocument> listFromElastic(
            String username,
            Integer ageFrom,
            Integer ageTo
    ) {
        BoolQueryBuilder qb = new BoolQueryBuilder();

        if (!username.equals("")) {
            qb.must(new MatchQueryBuilder("username", username));
        }
        if (ageFrom != null) {
            qb.must(new RangeQueryBuilder("age").gte(ageFrom));
        }
        if (ageTo != null) {
            qb.must(new RangeQueryBuilder("age").lte(ageTo));
        }

        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(qb)
                .build();

        SearchHits<UserDocument> search = elasticsearchOperations
                .search(
                        searchQuery,
                        UserDocument.class
                );

        return search.stream().map(SearchHit::getContent).collect(Collectors.toList());
    }

    public void reIndex() {
        // Re-index
        this.userDocumentRepository.deleteAll();
        List<User> users = userRepository.findAll();
        logger.info("Start re-index users : " + users.size());

        List<UserDocument> userDocuments = new ArrayList<>();
        for (User u : users) {
            UserDocument userDocument = new UserDocument();
            modelMapper.map(u, userDocument);
            userDocuments.add(userDocument);
        }
        this.userDocumentRepository.saveAll(userDocuments);
    }

}