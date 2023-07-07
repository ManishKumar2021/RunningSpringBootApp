package com.dog.breed.repository;

import com.dog.breed.model.DogBreed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DogBreedRepository extends JpaRepository<DogBreed, Long> {
    Optional<DogBreed> findByBreed(String breed);
}
