package main.java.com.dog.breed.dto;

import lombok.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Data
public class DogBreedDTO {
    private String id;
    private String breed;
    private String origin;
    private String url;

    public DogBreedDTO(String id, String breed, String origin, String url) {
        this.id = id;
        this.breed = breed;
        this.origin = origin;
        this.url = url;
    }
}