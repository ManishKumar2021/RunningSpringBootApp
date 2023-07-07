package com.dog.breed.controller;

import com.dog.breed.exception.BreedNotFoundException;
import com.dog.breed.model.DogBreed;
import com.dog.breed.service.DogBreedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/dogBreed")
public class DogBreedController {
    private final DogBreedService dogBreedService;

    public DogBreedController(DogBreedService dogBreedService) {
        this.dogBreedService = dogBreedService;
    }

    @GetMapping
    public ResponseEntity<DogBreed> getDogBreed(@RequestParam("breed") String breed) {
        Optional<DogBreed> optionalDogBreed = dogBreedService.getDogBreedByBreed(breed);
        DogBreed dogBreed = optionalDogBreed.orElseGet(() -> dogBreedService.fetchAndSaveBreedFromApi(breed));
        
        return ResponseEntity.ok(dogBreed);
    }
}

