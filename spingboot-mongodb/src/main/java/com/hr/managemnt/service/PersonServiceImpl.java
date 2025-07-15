package com.hr.managemnt.service;

import com.hr.managemnt.model.Persons;
import com.hr.managemnt.repository.CustomPersonRepository;
import com.hr.managemnt.repository.PersonRepository;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private CustomPersonRepository customPersonRepository;

    @Autowired
    PersonRepository personRepository;

    public List<Persons> getAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public Persons getPersonByIndex(String id) {
        return personRepository.findById(id).orElse(null);
    }

    public List<Persons> getPersonsAboveAge25() {
        return customPersonRepository.getPersonsAboveAge25();
    }


    @Override
    public List<Persons> getPersonsWithThreeTags() {
        return customPersonRepository.personsWithThreeTags();
    }

    @Override
    public List<Document> getGroupedByEyeColor() {
        return customPersonRepository.groupByEyeColor();
    }


    @Override
    public List<Document> getEyeColorBlue() {
        return customPersonRepository.matchEyeColorBlue();
    }

    public List<Document> getPersonsByEyeColor(String eyeColor){
        return customPersonRepository.getPersonsByEyeColor(eyeColor);
    }


    @Override
    public List<Document> getActiveUsers() {
        return customPersonRepository.matchActiveUsers();
    }

    @Override
    public List<Document> getGroupedByAge() {
        return customPersonRepository.groupByAge();
    }

    @Override
    public List<Document> getUnwindTags() {
        return customPersonRepository.unwindTags();
    }

    @Override
    public List<Document> getUnwindTagsGrouped() {
        return customPersonRepository.unwindTagsThenGroup();
    }

    @Override
    public List<Document> getCompanyLocation() {
        return customPersonRepository.projectCompanyLocation();
    }


    @Override
    public List<Document> getTagsContainingConsequat() {
        return customPersonRepository.filterTagsContainingConsequat();
    }

    @Override
    public List<Document> groupByCompanyCountry() {
        return customPersonRepository.groupByCompanyCountry();
    }


    @Override
    public List<Document> groupByEyeColorFruitGender() {
        return customPersonRepository.groupByEyeColorFruitGender();
    }


    @Override
    public List<Document> groupEyeColorAgeAbove30() {
        return customPersonRepository.groupEyeColorAgeAbove30();
    }

    @Override
    public List<Document> countEyeColorAgeCombinations() {
        return customPersonRepository.countEyeColorAgeCombinations();
    }

    @Override
    public List<Document> groupAndSortNonBrownEyesFruit() {
        return customPersonRepository.groupAndSortNonBrownEyesFruit();
    }

    @Override
    public List<Document> projectCountryAndEmail() {
        return customPersonRepository.projectCountryAndEmail();
    }

    @Override
    public List<Document> unwindTagsWithFields() {
        return customPersonRepository.unwindTagsWithFields();
    }

    @Override
    public List<Document> groupByNameAndTags() {
        return customPersonRepository.groupByNameAndTags();
    }

    @Override
    public List<Document> groupByFavoriteFruitCount() {
        return customPersonRepository.groupByFavoriteFruitCount();
    }

    @Override
    public List<Document> groupByTagsSorted() {
        return customPersonRepository.groupByTagsSorted();
    }

    @Override
    public List<Document> avgAgeByEyeColor() {
        return customPersonRepository.avgAgeByEyeColor();
    }

    public List<String> getDistinctEyeColors(){
        return customPersonRepository.getDistinctEyeColors();
    }
    public List<String> getDistinctCountries(){
        return customPersonRepository.getDistinctCountries();
    }

    public List<Document> getPersonsByCountry(String country){
        return customPersonRepository.getPersonsByCountry(country);
    }

    public List<Document> searchPersons(String eyeColor, String country, String name){
        return customPersonRepository.searchPersons(eyeColor, country, name);
    }

}