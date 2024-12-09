import java.util.ArrayList;
import java.util.List;

public class RecipeApp {
    private final List<User> users;
    private final List<Recipe> recipes;

    public RecipeApp() {
        users = new ArrayList<>();
        recipes = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }
}
