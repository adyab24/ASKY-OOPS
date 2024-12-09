import java.util.ArrayList;
import java.util.List;

public class Recipe {
    private String name;
    private String author; // User who created the recipe
    private final List<Ingredient> ingredients;
    private String steps;
    private int likes;
    private int calories; // Calories for the recipe
    private String ageGroup; // Target age group for the recipe
    private final List<String> comments;
    private final List<String> suggestions; // Suggestions for the recipe

    // Constructor
    public Recipe(String name, String author) {
        this.name = name;
        this.author = author;
        this.ingredients = new ArrayList<>();
        this.steps = "";
        this.likes = 0;
        this.calories = 0; // Default value
        this.ageGroup = "All Ages"; // Default value
        this.comments = new ArrayList<>();
        this.suggestions = new ArrayList<>();
    }

    // Getters and setters for new fields
    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public List<String> getSuggestions() {
        return suggestions;
    }

    public void addSuggestion(String suggestion) {
        suggestions.add(suggestion);
    }

    // Other methods remain unchanged...
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public int getLikes() {
        return likes;
    }

    public void incrementLikes() {
        likes++;
    }

    public void decrementLikes() {
        if (likes > 0) {
            likes--;
        }
    }

    public List<String> getComments() {
        return comments;
    }

    public void addComment(String comment) {
        comments.add(comment);
    }
}
