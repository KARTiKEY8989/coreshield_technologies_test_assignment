import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Specify the file path for the JSON file
        String LocationPath = "src/Location.json";
        String MetadataPath = "src/Metadata.json";
        Operations operation = new Operations();

        // Create an instance of JsonReader
        JsonReader jsonReader = new JsonReader(LocationPath,MetadataPath);

        // Use getAllLocations() to retrieve the list of Location objects
        List<Location> locations = jsonReader.getAllLocations();
        List<Metadata> metadata = jsonReader.getAllMetadata();

        System.out.println("List of Locations");
        for (Location location : locations) {
            System.out.println(location);
        }
        System.out.println("List of Metadata");
        for (Metadata metadata1 : metadata) {
            System.out.println(metadata1);
        }
        System.out.println("Count of valid points exist per type");
        System.out.println(operation.count(metadata,locations));
        System.out.println("Calculate the average rating per type, considering only valid entries.");
        System.out.println(operation.calculateAverageRating(metadata,locations));
        System.out.println("Identify the location with the highest number of reviews.");
        System.out.println(operation.getHighestReviewsPerType(metadata, locations));
    }
}
