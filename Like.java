public class Like {
    public void likeRecipe(User user, Recipe recipe) {
        recipe.incrementLikes(); // Increment likes for the recipe
        System.out.println(user.getUsername() + " liked " + recipe.getName());
    }

    public void unlikeRecipe(User user, Recipe recipe) {
        recipe.decrementLikes(); // Decrement likes for the recipe
        System.out.println(user.getUsername() + " unliked " + recipe.getName());
    }
}
