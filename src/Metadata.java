public class Metadata {
    private String id;
    private String type;
    private double rating;
    private int reviews;

    public Metadata() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getReviews() {
        return reviews;
    }

    public void setReviews(int reviews) {
        this.reviews = reviews;
    }

    public Metadata(String id, String type, double rating, int reviews) {
        this.id = id;
        this.type = type;
        this.rating = rating;
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "Metadata{id='" + id + "', type='" + type + "', rating=" + rating + ", reviews=" + reviews + '}';
    }
}
