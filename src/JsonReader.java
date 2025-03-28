import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonReader {
    private String locationsFilePath;
    private String metadataFilePath;
    public JsonReader(String locationsFilePath , String metadataFilePath) {
        this.locationsFilePath = locationsFilePath;
        this.metadataFilePath = metadataFilePath;
    }

    // method to get the list of Locations from the JSON file
    public List<Location> getAllLocations() {
        List<Location> locations = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(locationsFilePath))) {
            StringBuilder jsonBuilder = new StringBuilder();
            String line;

            // Read the entire JSON file content
            while ((line = br.readLine()) != null) {
                jsonBuilder.append(line);
            }
            String json = jsonBuilder.toString();

            // read the metadata JSON file content and create a list of locations
            json = json.substring(1, json.length() - 1); // Removes [] brackets
            String[] items = json.split("},\\s*\\{"); // Splits objects using "}{" or "} ,{"

            for (String item : items) {
                item = item.replace("{", "").replace("}", ""); // Removes curly braces
                String[] fields = item.split(",");

                String id = fields[0].split(":")[1].replace("\"", "").trim();
                double latitude = Double.parseDouble(fields[1].split(":")[1].trim());
                double longitude = Double.parseDouble(fields[2].split(":")[1].trim());

                // add each Location object to the list
                locations.add(new Location(id, latitude, longitude));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return locations;
    }
    //method to get the metadata list from the JSON file
    public List<Metadata> getAllMetadata() {
        List<Metadata> metadataList = new ArrayList<>();
        // read the metadata JSON file content and create a list of Metadata objects
        try (BufferedReader br = new BufferedReader(new FileReader(metadataFilePath))) {
            StringBuilder jsonBuilder = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                jsonBuilder.append(line);
            }

            String json = jsonBuilder.toString();

            // Process JSON data (split the string into individual metadata objects)
            json = json.substring(1, json.length() - 1); // Remove square brackets
            String[] items = json.split("},\\s*\\{"); // Split objects using "}{" or "} ,{"

            for (String item : items) {
                item = item.replace("{", "").replace("}", ""); // Remove curly braces
                String[] fields = item.split(",");

                String id = fields[0].split(":")[1].replace("\"", "").trim();
                String type = fields[1].split(":")[1].replace("\"", "").trim();
                double rating = Double.parseDouble(fields[2].split(":")[1].trim());
                int reviews = Integer.parseInt(fields[3].split(":")[1].trim());

                // add each Metadata object to the list
                metadataList.add(new Metadata(id, type, rating, reviews));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return metadataList;
    }
}
