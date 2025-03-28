import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Operations {

    // method to check if the metadata has a valid Location ID
    private boolean isValid(Metadata metadata, List<Location> locationList) {

        return locationList.stream()
                .anyMatch(location -> location.getId().equals(metadata.getId()));
    }

    // Method to Count how many valid points exist per type (e.g., restaurants, hotels, cafes, museums,parks).
    public Map<String, Integer> count(List<Metadata> metadataList, List<Location> locationList) {
        Map<String, Integer> typeCountMap = new HashMap<>();

        for (Metadata metadata : metadataList) {
            if (isValid(metadata, locationList)) {
                String type = metadata.getType();

                // Increment the count for the type
                typeCountMap.put(type, typeCountMap.getOrDefault(type, 0) + 1);
            }
        }

        return typeCountMap;
    }

    // method to Calculate the average rating per type, considering only valid entries.
    public Map<String, Double> calculateAverageRating(List<Metadata> metadataList, List<Location> locationList) {
        Map<String, Double> ratingMap = new HashMap<>();
        Map<String, Integer> countMap = new HashMap<>();

        for (Metadata metadata : metadataList) {
            if (isValid(metadata, locationList)) {
                String type = metadata.getType();
                double rating = metadata.getRating();
                // Add the rating to the type's total sum and increment the count for the type'
                ratingMap.put(type, ratingMap.getOrDefault(type, 0.0) + rating);
                countMap.put(type, countMap.getOrDefault(type, 0) + 1);
            }
        }

        // Calculate average rating per type and round it off to 2 decimal places
        Map<String, Double> averageRatingMap = new HashMap<>();
        for (String type : ratingMap.keySet()) {
            double average = ratingMap.get(type) / countMap.get(type);
            averageRatingMap.put(type, Math.round(average * 100.0) / 100.0); // Round to 2 decimal places
        }

        return averageRatingMap;
    }

    // method to find the location with the highest number of reviews per type
    public Map<String, String> getHighestReviewsPerType(List<Metadata> metadataList, List<Location> locationList) {
        Map<String, String> highestReviewsMap = new HashMap<>();
        Map<String, Integer> maxReviewsMap = new HashMap<>();

        for (Metadata metadata : metadataList) {
            if (isValid(metadata, locationList)) {
                String type = metadata.getType();
                String locationId = metadata.getId();
                int reviews = metadata.getReviews();

                // Update the map if this metadata has more reviews for the type
                if (!maxReviewsMap.containsKey(type) || reviews > maxReviewsMap.get(type)) {
                    highestReviewsMap.put(type, locationId);
                    maxReviewsMap.put(type, reviews);
                }
            }
        }

        return highestReviewsMap;
    }

    // Method to identify locations with incomplete data
    public List<Location> getIncompleteLocations(JsonReader jsonReader) {
        List<Location> incompleteLocations = new ArrayList<>();
        List<Location> allLocations = jsonReader.getAllLocations();

        for (Location location : allLocations) {
            // check if there is any missing fields
            if (location.getId() == null || location.getId().isEmpty() ||
                    Double.isNaN(location.getLatitude()) || Double.isNaN(location.getLongitude())) {
                incompleteLocations.add(location);
            }
        }

        return incompleteLocations;
    }

}
