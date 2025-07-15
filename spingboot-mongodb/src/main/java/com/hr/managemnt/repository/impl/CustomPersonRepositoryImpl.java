package com.hr.managemnt.repository.impl;

import com.hr.managemnt.model.Persons;
import com.hr.managemnt.repository.CustomPersonRepository;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;


@Repository
public class CustomPersonRepositoryImpl implements CustomPersonRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Persons> getPersonsAboveAge25() {
        Aggregation agg = newAggregation(
                match(org.springframework.data.mongodb.core.query.Criteria.where("age").gt(30))
        );

        AggregationResults<Persons> results = mongoTemplate.aggregate(agg, "persons", Persons.class);
        return results.getMappedResults();
    }



    @Override
    public List<Persons> personsWithThreeTags() {
        Aggregation agg = newAggregation(
                match(org.springframework.data.mongodb.core.query.Criteria.where("tags").size(3)));
        AggregationResults<Persons> results = mongoTemplate.aggregate(agg, "persons", Persons.class);
        return results.getMappedResults();
    }

    @Override
    public List<Document> groupByEyeColor() {
        Aggregation aggregation = Aggregation.newAggregation(group("eyeColor"));
        AggregationResults<Document> results =
                mongoTemplate.aggregate(aggregation, "persons", Document.class);
        return results.getMappedResults();
    }



   @Override
    public List<Document> matchEyeColorBlue() {
        Aggregation aggregation = newAggregation(
               match(org.springframework.data.mongodb.core.query.Criteria.where("eyeColor").is("blue"))
       );
       AggregationResults<Document> results =
               mongoTemplate.aggregate(aggregation, "persons", Document.class);
        return results.getMappedResults();
    }

    @Override
    public List<Document> matchActiveUsers() {
        Aggregation aggregation = newAggregation(
                match(org.springframework.data.mongodb.core.query.Criteria.where("isActive").is(true))
        );
        AggregationResults<Document> results =
                mongoTemplate.aggregate(aggregation, "persons", Document.class);
        return results.getMappedResults();
    }



    @Override
    public List<Document> groupByAge() {
        Aggregation aggregation = Aggregation.newAggregation(group("age").count().as("count"));
        AggregationResults<Document> results =
                mongoTemplate.aggregate(aggregation, "persons", Document.class);
        return results.getMappedResults();
    }

    @Override
    public List<Document> unwindTags() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.unwind("tags"));
        AggregationResults<Document> results =
                mongoTemplate.aggregate(aggregation, "persons", Document.class);
        return results.getMappedResults();


    }





    @Override
    public List<Document> unwindTagsThenGroup() {
        UnwindOperation unwind = Aggregation.unwind("tags");
        GroupOperation group = Aggregation.group("tags").count().as("count");
        Aggregation aggregation = Aggregation.newAggregation(unwind, group);
        return mongoTemplate.aggregate(aggregation, "persons", Document.class).getMappedResults();
    }



    @Override
    public List<Document> projectCompanyLocation() {
        ProjectionOperation project = Aggregation.project().and("company.location").as("location");
        Aggregation aggregation = Aggregation.newAggregation(project);
        return mongoTemplate.aggregate(aggregation, "persons", Document.class).getMappedResults();
    }



    @Override
    public List<Document> filterTagsContainingConsequat() {
        MatchOperation match = match(Criteria.where("tags").in("consequat"));
        Aggregation aggregation = Aggregation.newAggregation(match);
        return mongoTemplate.aggregate(aggregation, "persons", Document.class).getMappedResults();
    }


    @Override
    public List<Document> ageBetween20And40() {
        MatchOperation match = match(Criteria.where("age").gte(20).lte(40));
        Aggregation aggregation = Aggregation.newAggregation(match);
        return mongoTemplate.aggregate(aggregation, "persons", Document.class).getMappedResults();

    }

    @Override
    public List<Document> matchCompanyCountryUSA() {
        MatchOperation match = match(Criteria.where("company.location.country").is("USA"));
        Aggregation aggregation = Aggregation.newAggregation(match);
        return mongoTemplate.aggregate(aggregation, "persons", Document.class).getMappedResults();
    }

    @Override
    public List<Document> groupByCountry() {
        GroupOperation group = group("company.location.country").count().as("count");
        Aggregation aggregation = Aggregation.newAggregation(group);
        return mongoTemplate.aggregate(aggregation, "persons", Document.class).getMappedResults();


    }



    @Override
    public List<Document> groupByCompanyCountry() {
        GroupOperation group = group("company.location.country");
        Aggregation aggregation = Aggregation.newAggregation(group);
        return mongoTemplate.aggregate(aggregation, "persons", Document.class).getMappedResults();

    }


    @Override
    public List<Document> groupByEyeColorFruitGender() {
        GroupOperation group = group(
                Fields.from(
                        Fields.field("eyeColor", "eyeColor"),
                        Fields.field("favoriteFruit", "favoriteFruit"),
                        Fields.field("gender", "gender")
                )
        );
        Aggregation aggregation = Aggregation.newAggregation(group);
        return mongoTemplate.aggregate(aggregation, "persons", Document.class).getMappedResults();
    }


    @Override
    public List<Document> groupEyeColorAgeAbove30() {
        GroupOperation group = group(
                Fields.from(
                        Fields.field("eyeColor", "eyeColor"),
                        Fields.field("age", "age")
                )
        );
        MatchOperation match = match(Criteria.where("_id.age").gt(30));
        SortOperation sort = Aggregation.sort(Sort.by(Sort.Direction.ASC, "_id.age"));
        Aggregation aggregation = Aggregation.newAggregation(group, match, sort);
        return mongoTemplate.aggregate(aggregation, "persons", Document.class).getMappedResults();
    }

    @Override
    public List<Document> countEyeColorAgeCombinations() {
        MatchOperation match = match(Criteria.where("age").gte(25));
        GroupOperation group = group(
                Fields.from(
                        Fields.field("eyeColor", "eyeColor"),
                        Fields.field("age", "age")
                )
        );
        CountOperation count = Aggregation.count().as("counEyeColorAgeCount");
        Aggregation aggregation = Aggregation.newAggregation(match, group, count);
        return mongoTemplate.aggregate(aggregation, "persons", Document.class).getMappedResults();
    }


    @Override
    public List<Document> groupAndSortNonBrownEyesFruit() {
        MatchOperation match = match(Criteria.where("eyeColor").ne("brown"));
        GroupOperation group = group(
                Fields.from(
                        Fields.field("favoriteFruit", "favoriteFruit"),
                        Fields.field("eyeColor", "eyeColor")
                )
        );
        SortOperation sort = Aggregation.sort(Sort.by(Sort.Order.asc("_id.favoriteFruit"), Sort.Order.desc("_id.eyeColor")));
        Aggregation aggregation = Aggregation.newAggregation(match, group, sort);
        return mongoTemplate.aggregate(aggregation, "persons", Document.class).getMappedResults();
    }

    @Override
    public List<Document> projectCountryAndEmail() {
        ProjectionOperation project = Aggregation.project("name")
                .and("company.location.country").as("info.country")
                .and("company.email").as("info.email");
        Aggregation aggregation = Aggregation.newAggregation(project);
        return mongoTemplate.aggregate(aggregation, "persons", Document.class).getMappedResults();
    }

    @Override
    public List<Document> unwindTagsWithFields() {
        UnwindOperation unwind = Aggregation.unwind("tags");
        ProjectionOperation project = Aggregation.project("name", "age", "eyeColor", "tags").andExclude("_id");
        Aggregation aggregation = Aggregation.newAggregation(unwind, project);
        return mongoTemplate.aggregate(aggregation, "persons", Document.class).getMappedResults();
    }

    @Override
    public List<Document> groupByNameAndTags() {
        UnwindOperation unwind = Aggregation.unwind("tags");
        GroupOperation group = group(
                Fields.from(
                        Fields.field("name", "name"),
                        Fields.field("tags", "tags")
                )
        );
        Aggregation aggregation = Aggregation.newAggregation(unwind, group);
        return mongoTemplate.aggregate(aggregation, "persons", Document.class).getMappedResults();
    }

    @Override
    public List<Document> groupByFavoriteFruitCount() {
        GroupOperation group = group("favoriteFruit").count().as("count");

        Aggregation aggregation = Aggregation.newAggregation(group);
        return mongoTemplate.aggregate(aggregation, "persons", Document.class).getMappedResults();
    }

    @Override
    public List<Document> groupByTagsSorted() {
        UnwindOperation unwind = Aggregation.unwind("tags");
        GroupOperation group = group("tags").count().as("count");
        SortOperation sort = Aggregation.sort(Sort.by("_id.name"));
        Aggregation aggregation = Aggregation.newAggregation(unwind, group, sort);
        return mongoTemplate.aggregate(aggregation, "persons", Document.class).getMappedResults();
    }

    @Override
    public List<Document> avgAgeByEyeColor() {
        GroupOperation group = group("eyeColor").avg("age").as("avgAge");
        Aggregation aggregation = Aggregation.newAggregation(group);
        return mongoTemplate.aggregate(aggregation, "persons", Document.class).getMappedResults();
    }

    public List<Document> getPersonsByEyeColor(String eyeColor) {
        MatchOperation match = Aggregation.match(Criteria.where("eyeColor").is(eyeColor));
        Aggregation aggregation = Aggregation.newAggregation(match);
        AggregationResults<Document> results = mongoTemplate.aggregate(aggregation, "persons", Document.class);
        return results.getMappedResults();
    }

    public List<Document> getPersonsByCountry(String country) {
        MatchOperation match = match(Criteria.where("company.location.country").is(country));
        Aggregation aggregation = newAggregation(match);
        AggregationResults<Document> results = mongoTemplate.aggregate(aggregation, Persons.class, Document.class);
        return results.getMappedResults();
    }

    public List<String> getDistinctEyeColors() {
        return mongoTemplate.query(Persons.class)
                        .distinct("eyeColor")
                        .as(String.class)
                        .all();


    }

    public List<String> getDistinctCountries() {
        return mongoTemplate.query(Persons.class)
                .distinct("company.location.country")
                .as(String.class)
                .all();
    }


    public List<Document> searchPersons(String eyeColor, String country, String name) {
        Criteria criteria = new Criteria();

        // Apply optional filters
        if (eyeColor != null && !eyeColor.trim().isEmpty()) {
            criteria = criteria.and("eyeColor").is(eyeColor.trim());
        }

        if (country != null && !country.trim().isEmpty()) {
            criteria = criteria.and("company.location.country").is(country.trim());
        }

        if (name != null && !name.trim().isEmpty()) {
            criteria = criteria.and("name").regex(".*" + name.trim() + ".*", "i"); // case-insensitive
        }

        MatchOperation match = match(criteria);
        Aggregation aggregation = newAggregation(match);
        System.out.println("Searching with criteria: " + criteria.getCriteriaObject().toJson());
        AggregationResults<Document> results =
                mongoTemplate.aggregate(aggregation, Persons.class, Document.class);



        return results.getMappedResults();
    }





}