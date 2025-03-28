# coreshield_technologies_test_assignment
This repository is for the assignment given by coreshield technologies.

Code Explaination:-

1. JSON files :-

 Location.json -
        this file contains a JSON array containing a list of location objects. Each object represents a geographical location with three properties: id, latitude, longitude.

 Metadata.json -
        this file contains a JSON array containing a list of objects. Each object represents a location with specific attributes.id, type, rating, reviews.

2. Object Classes :-

 Location -
        This class is designed to represent a geographical location with three main attributes: an identifier (id), a latitude, and a longitude.

 Metadata -
        This class is designed to represent metadata information with four attributes: id, type, rating, and reviews.

3. JsonReader:-

 constructor - 
        To create an instance of the JsonReader class. 
        It initializes the object with specific file paths for the locations and metadata JSON files.

 getAllLocations Method -   
        This method is responsible for reading a JSON file containing location data and converting it into a list of Location objects.

 getAllMetadata Method -
        This method is responsible for reading a JSON file containing metadata information and converting it into a list of Metadata objects.

4. Operations :-

 Methods-
 
 isValid(Metadata metadata, List<Location> locationList) -
         
        Checks whether a Metadata object has a valid Location ID. 
        It ensures that the id in Metadata matches with the id of any Location in the provided locationList.
        Used to filter out invalid Metadata objects that refer to non-existent or mismatched Location IDs.

 count(List<Metadata> metadataList, List<Location> locationList) -
        
        It counts how many valid points exist for each type of location (e.g., restaurants, hotels, parks, etc.).
        
        It iterates through the Metadata list.and for each valid Metadata (using the isValid() method),
        it sets the count for its associated type in a Map.
        and returns a Map where the key is the "type"  and the value is the count of valid points for that type.

 calculateAverageRating(List<Metadata> metadataList, List<Location> locationList) -

        Calculates the average rating for each type of location, considering only valid entries.

        it sums up the ratings for each type while keeping track of the count of valid entries.
        Then, it computes the average by dividing the total rating by the count for each type.
        The average is rounded to 2 decimal places for better readability.
        and returns a Map where the key is the "type" and the value is the average rating.

 getIncompleteLocations(JsonReader jsonReader)

        this method identifies and returns a list of Location objects with incomplete data (missing id, latitude, or longitude).
        
        It uses the JsonReader to retrieve all locations.
        it checks each location to see if any fields are empty, null, or invalid (NaN).
        Incomplete locations are added to a separate list.
        the method returns a List of locations containing all locations with incomplete data.
