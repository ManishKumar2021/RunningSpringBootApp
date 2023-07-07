@Service
public class DogBreedService {
    private final DogBreedRepository dogBreedRepository;
    private final String API_URL = "https://dog-breeds2.p.rapidapi.com/dog_breeds";
    private final String API_KEY = "0e73afb71emsha51de228eda132cp1a70b5jsn87a4bf3c9ec6";
    private final String API_HOST = "dog-breeds2.p.rapidapi.com";

    public DogBreedService(DogBreedRepository dogBreedRepository) {
        this.dogBreedRepository = dogBreedRepository;
    }

    public List<DogBreed> getAllDogBreeds() {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", API_KEY);
        headers.set("X-RapidAPI-Host", API_HOST);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            DogBreed[] dogBreeds = responseEntity.getBody();
            if (dogBreeds != null) {
                for (DogBreed dogBreed : dogBreeds) {
                    dogBreedRepository.save(dogBreed);
                }
                return Arrays.asList(dogBreeds);
            }
        }
        ResponseEntity<DogBreed[]> responseEntity = restTemplate.exchange(
                API_URL,
                HttpMethod.GET,
                entity,
                DogBreed[].class
        );


        throw new DogBreedsApiException("Failed to fetch dog breeds from the API.");
    }

    @Retry(name = "dogBreedApiRetry", fallbackMethod = "handleDogBreedsApiRetryFallback")
    public List<DogBreed> getAllDogBreedsWithRetry() {
        return getAllDogBreeds();
    }

    private List<DogBreed> handleDogBreedsApiRetryFallback(Exception ex) {
        throw new DogBreedsApiException("Failed to fetch dog breeds even after retrying.");
    }
}
