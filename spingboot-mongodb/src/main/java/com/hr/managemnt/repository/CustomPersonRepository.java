package com.hr.managemnt.repository;


import com.hr.managemnt.model.Persons;
import org.bson.Document;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomPersonRepository {

    List<Persons> getPersonsAboveAge25();
    List<Persons> personsWithThreeTags();
    List<Document> groupByEyeColor();
    List<Document> matchEyeColorBlue();
    List<Document> matchActiveUsers();
    List<Document> groupByAge();
    List<Document> unwindTags();
    List<Document> unwindTagsThenGroup();
    List<Document> projectCompanyLocation();
    List<Document> filterTagsContainingConsequat();
    List<Document> ageBetween20And40();
    List<Document> matchCompanyCountryUSA();
    List<Document> groupByCountry();

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

}
