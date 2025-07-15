package com.hr.managemnt.service;

import com.hr.managemnt.model.Persons;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

public interface PersonService {

    List<Persons> getAllPersons();
    Persons getPersonByIndex(String id);
    List<Persons> getPersonsAboveAge25();
    List<Persons> getPersonsWithThreeTags();
    List<Document> getGroupedByEyeColor();
    List<Document> getEyeColorBlue();
    List<Document> getActiveUsers();
    List<Document> getGroupedByAge();
    List<Document> getUnwindTags();
    List<Document> getUnwindTagsGrouped();
    List<Document> getCompanyLocation();
    List<Document> getTagsContainingConsequat();
    List<Document> groupByCompanyCountry();
    List<Document> groupByEyeColorFruitGender();
    List<Document> groupEyeColorAgeAbove30();
    List<Document> countEyeColorAgeCombinations();
    List<Document> groupAndSortNonBrownEyesFruit();
    List<Document> projectCountryAndEmail();
    List<Document> unwindTagsWithFields();
    List<Document> groupByNameAndTags();
    List<Document> groupByFavoriteFruitCount();
    List<Document> groupByTagsSorted();
    List<Document> avgAgeByEyeColor();
    List<Document> getPersonsByEyeColor(String eyeColor);

    List<String> getDistinctEyeColors();
    List<String> getDistinctCountries();

    List<Document> getPersonsByCountry(String country);
    List<Document> searchPersons(String eyeColor, String country, String name);
    Persons createPerson(Persons person);
}