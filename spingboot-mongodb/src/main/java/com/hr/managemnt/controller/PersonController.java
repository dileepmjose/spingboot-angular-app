package com.hr.managemnt.controller;

import com.hr.managemnt.model.Persons;
import com.hr.managemnt.service.PersonService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("/create")
    public ResponseEntity<Persons> savePerson(@RequestBody Persons person) {
        return ResponseEntity.ok( personService.createPerson(person));
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Persons>> getAll() {
        return ResponseEntity.ok(personService.getAllPersons());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persons> getPersonById(@PathVariable("id") String id) {
        Persons person = personService.getPersonByIndex(id);
        if (person != null) {
            return ResponseEntity.ok(person);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Document>> searchPersons(
            @RequestParam(required = false) String eyeColor,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String name
    ) {
        return ResponseEntity.ok(personService.searchPersons(eyeColor, country, name));
    }

    @GetMapping
    public ResponseEntity<List<Document>> getPersonsByEyeColor(@RequestParam(required = false) String eyecolor) {
        return ResponseEntity.ok(personService.getPersonsByEyeColor(eyecolor));
    }

    @GetMapping("/getBycountry")
    public ResponseEntity<List<Document>> getPersonsByCountry(@RequestParam String country) {
        return ResponseEntity.ok(personService.getPersonsByCountry(country));
    }


    @GetMapping("/eyecolors")
    public ResponseEntity<List<String>> getDistinctEyeColors() {
        List<String> eyeColors = personService.getDistinctEyeColors();
        return ResponseEntity.ok(eyeColors);
    }

    // GET /api/persons/countries
    @GetMapping("/countries")
    public ResponseEntity<List<String>> getDistinctCountries() {
        List<String> countries = personService.getDistinctCountries();
        return ResponseEntity.ok(countries);
    }

    @GetMapping("/age-greater-than-25")
    public ResponseEntity<List<Persons>> getAgeGreaterThan25() {
        return ResponseEntity.ok(personService.getPersonsAboveAge25());
    }

    @GetMapping("/three-tags")
    public ResponseEntity<List<Persons>> getThreeTags() {
        return ResponseEntity.ok(personService.getPersonsWithThreeTags());
    }

    @GetMapping("/group-by-eyecolor")
    public ResponseEntity<List<Document>> groupByEyeColor() {
        return ResponseEntity.ok(personService.getGroupedByEyeColor());
    }



    @GetMapping("/eyecolor-blue")
    public ResponseEntity<List<Document>> getEyeColorBlue() {
        return ResponseEntity.ok(personService.getEyeColorBlue());
    }

    @GetMapping("/active-users")
    public ResponseEntity<List<Document>> getActiveUsers() {
        return ResponseEntity.ok(personService.getActiveUsers());
    }

    @GetMapping("/group-by-age")
    public ResponseEntity<List<Document>> groupByAge() {
        return ResponseEntity.ok(personService.getGroupedByAge());
    }

    @GetMapping("/unwind-tags")
    public ResponseEntity<List<Document>> unwindTags() {
        return ResponseEntity.ok(personService.getUnwindTags());
    }

    @GetMapping("/unwind-tags-then-group")
    public ResponseEntity<List<Document>> unwindTagsThenGroup() {
        return ResponseEntity.ok(personService.getUnwindTagsGrouped());
    }

    @GetMapping("/project-company-location")
    public ResponseEntity<List<Document>> projectCompanyLocation() {
        return ResponseEntity.ok(personService.getCompanyLocation());
    }

    @GetMapping("/tags-containing-consequat")
    public ResponseEntity<List<Document>> filterTagsConsequat() {
        return ResponseEntity.ok(personService.getTagsContainingConsequat());
    }

    @GetMapping("/group-by-company-country")
    public ResponseEntity<List<Document>> groupByCompanyCountry() {
        return ResponseEntity.ok(personService.groupByCompanyCountry());
    }

    @GetMapping("/group-by-eyeColor-fruit-gender")
    public ResponseEntity<List<Document>> groupByEyeColorFruitGender() {
        return ResponseEntity.ok(personService.groupByEyeColorFruitGender());
    }

    @GetMapping("/group-eyeColor-age-above-30")
    public ResponseEntity<List<Document>> groupEyeColorAgeAbove30() {
        return ResponseEntity.ok(personService.groupEyeColorAgeAbove30());
    }

    @GetMapping("/count-eyeColor-age-combinations")
    public ResponseEntity<List<Document>> countEyeColorAgeCombinations() {
        return ResponseEntity.ok(personService.countEyeColorAgeCombinations());
    }

    @GetMapping("/group-sort-non-brown-eyes-fruit")
    public ResponseEntity<List<Document>> groupAndSortNonBrownEyesFruit() {
        return ResponseEntity.ok(personService.groupAndSortNonBrownEyesFruit());
    }

    @GetMapping("/project-country-email")
    public ResponseEntity<List<Document>> projectCountryAndEmail() {
        return ResponseEntity.ok(personService.projectCountryAndEmail());
    }

    @GetMapping("/unwind-tags-with-fields")
    public ResponseEntity<List<Document>> unwindTagsWithFields() {
        return ResponseEntity.ok(personService.unwindTagsWithFields());
    }

    @GetMapping("/group-by-name-tags")
    public ResponseEntity<List<Document>> groupByNameAndTags() {
        return ResponseEntity.ok(personService.groupByNameAndTags());
    }

    @GetMapping("/group-by-favorite-fruit-count")
    public ResponseEntity<List<Document>> groupByFavoriteFruitCount() {
        return ResponseEntity.ok(personService.groupByFavoriteFruitCount());
    }

    @GetMapping("/group-by-tags-sorted")
    public ResponseEntity<List<Document>> groupByTagsSorted() {
        return ResponseEntity.ok(personService.groupByTagsSorted());
    }

    @GetMapping("/avg-age-by-eyeColor")
    public ResponseEntity<List<Document>> avgAgeByEyeColor() {
        return ResponseEntity.ok(personService.avgAgeByEyeColor());
    }

}
