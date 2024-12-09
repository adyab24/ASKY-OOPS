
import java.util.ArrayList;
import java.util.List;

public class User {
    private final String username;
    private final List<Recipe> recipes; // List of user's recipes
    private final List<User> following; // List of users this user follows
    private final List<Recipe> likes; // List of liked recipes

    public User(String username) {
        this.username = username;
        this.recipes = new ArrayList<>();
        this.following = new ArrayList<>();
        this.likes = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    // Add a recipe created by the user
    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    // Get the list of recipes created by the user
    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void likeRecipe(Recipe recipe) {
        recipe.incrementLikes(); // Increment likes for the recipe
        likes.add(recipe);
    }

    public void unlikeRecipe(Recipe recipe) {
        recipe.decrementLikes(); // Decrement likes for the recipe
        likes.remove(recipe);
    }

    // Future placeholder for following users
    public void followUser(User user) {
        if (!following.contains(user)) {
            following.add(user);
        }
    }

    public List<User> getFollowing() {
        return following;
    }
}
