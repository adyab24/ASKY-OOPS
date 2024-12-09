import java.util.ArrayList;
import java.util.List;

public class Chef extends User {
    private String specialty;
    private final List<Recipe> createdRecipes;

    public Chef(String username, String specialty) {
        super(username);
        this.specialty = specialty;
        this.createdRecipes = new ArrayList<>();
    }

    public String getSpecialty() {
        return specialty;
    }

    public void createSpecialDish(Recipe recipe) {
        createdRecipes.add(recipe);
        System.out.println("Chef " + getUsername() + " created " + recipe.getName());
    }
}
