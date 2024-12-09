import java.util.ArrayList;
import java.util.List;

public class RecipeSuggestion {

    public List<Recipe> suggestRecipes(Ingredient ingredient, List<Recipe> recipes) {
        List<Recipe> suggestedRecipes = new ArrayList<>();
        for (Recipe recipe : recipes) {
            if (recipeContainsIngredient(recipe, ingredient)) {
                suggestedRecipes.add(recipe);
            }
        }
        return suggestedRecipes;
    }

    public boolean recipeContainsIngredient(Recipe recipe, Ingredient ingredient) {
        return recipe.getIngredients().contains(ingredient);
    }
}
