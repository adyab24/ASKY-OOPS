import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;

public class RecipeAppMain extends JFrame {
    private final JPanel mainPanel;
    private RecipePanel recipePanel;
    private final Map<String, String> users; // Stores usernames and passwords
    private final List<Recipe> globalRecipes; // Stores recipes globally
    private String currentUser; // Tracks the logged-in user

    public RecipeAppMain() {
        setTitle("Recipe Management and Social Platform");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);

        users = new HashMap<>();
        globalRecipes = new ArrayList<>();
        recipePanel = new RecipePanel(); // Initialize the RecipePanel

        showLoginScreen();
    }

    private void showLoginScreen() {
        mainPanel.removeAll();
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        inputPanel.add(new JLabel("Username:"));
        inputPanel.add(usernameField);
        inputPanel.add(new JLabel("Password:"));
        inputPanel.add(passwordField);
        inputPanel.add(loginButton);
        inputPanel.add(registerButton);

        mainPanel.add(inputPanel, BorderLayout.CENTER);

        loginButton.addActionListener(_ -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());

            if (users.containsKey(username) && users.get(username).equals(password)) {
                currentUser = username;
                JOptionPane.showMessageDialog(this, "Login successful!");
                showMainMenu();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        registerButton.addActionListener(_ -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());

            if (!username.isEmpty() && !password.isEmpty()) {
                if (users.containsKey(username)) {
                    JOptionPane.showMessageDialog(this, "Username already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    users.put(username, password);
                    currentUser = username;
                    JOptionPane.showMessageDialog(this, "Registration successful!");
                    showMainMenu();
                }
            } else {
                JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void showMainMenu() {
        mainPanel.removeAll();

        JPanel buttonPanel = new JPanel();
        JButton saveRecipeButton = new JButton("Save Recipe");
        JButton viewRecipesButton = new JButton("View Recipes");
        JButton logoutButton = new JButton("Logout");

        buttonPanel.add(saveRecipeButton);
        buttonPanel.add(viewRecipesButton);
        buttonPanel.add(logoutButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        saveRecipeButton.addActionListener(_ -> showSaveRecipeDialog());
        viewRecipesButton.addActionListener(_ -> showRecipePanel());
        logoutButton.addActionListener(_ -> {
            currentUser = null;
            showLoginScreen();
        });

        JLabel welcomeLabel = new JLabel("Welcome, " + currentUser + "!", SwingConstants.CENTER);
        mainPanel.add(welcomeLabel, BorderLayout.CENTER);

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void showSaveRecipeDialog() {
        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        JTextField nameField = new JTextField();
        JTextField ingredientsField = new JTextField();
        JTextArea stepsArea = new JTextArea();
        JTextField caloriesField = new JTextField();
        JTextField ageGroupField = new JTextField();

        inputPanel.add(new JLabel("Recipe Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Ingredients (comma-separated):"));
        inputPanel.add(ingredientsField);
        inputPanel.add(new JLabel("Steps:"));
        inputPanel.add(new JScrollPane(stepsArea));
        inputPanel.add(new JLabel("Calories:"));
        inputPanel.add(caloriesField);
        inputPanel.add(new JLabel("Age Group:"));
        inputPanel.add(ageGroupField);

        int result = JOptionPane.showConfirmDialog(this, inputPanel, "Save Recipe", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String name = nameField.getText().trim();
            String ingredientsText = ingredientsField.getText().trim();
            String steps = stepsArea.getText().trim();
            String caloriesText = caloriesField.getText().trim();
            String ageGroup = ageGroupField.getText().trim();

            if (!name.isEmpty() && !ingredientsText.isEmpty() && !steps.isEmpty() && !caloriesText.isEmpty() && !ageGroup.isEmpty()) {
                try {
                    int calories = Integer.parseInt(caloriesText);
                    List<Ingredient> ingredients = new ArrayList<>();
                    for (String ingredientName : ingredientsText.split(",")) {
                        ingredients.add(new Ingredient(ingredientName.trim(), 0));
                    }

                    Recipe newRecipe = new Recipe(name, currentUser);
                    newRecipe.setSteps(steps);
                    newRecipe.getIngredients().addAll(ingredients);
                    newRecipe.setCalories(calories);
                    newRecipe.setAgeGroup(ageGroup);

                    globalRecipes.add(newRecipe);
                    recipePanel.updateRecipeList();

                    JOptionPane.showMessageDialog(this, "Recipe saved successfully!");
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Calories must be a valid number!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void showRecipePanel() {
        if (recipePanel == null) {
            recipePanel = new RecipePanel();
        }
        mainPanel.removeAll();
        mainPanel.add(recipePanel, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    class RecipePanel extends JPanel {
        private final JList<String> recipeList;
        private final DefaultListModel<String> listModel;

        public RecipePanel() {
            setLayout(new BorderLayout());
            listModel = new DefaultListModel<>();
            recipeList = new JList<>(listModel);
            recipeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            JPanel buttonPanel = new JPanel();
            JButton viewButton = new JButton("View Details");
            JButton commentButton = new JButton("Comment");
            JButton likeButton = new JButton("Like");
            JButton backButton = new JButton("Back");

            buttonPanel.add(viewButton);
            buttonPanel.add(commentButton);
            buttonPanel.add(likeButton);
            buttonPanel.add(backButton);

            viewButton.addActionListener(_ -> viewRecipeDetails());
            commentButton.addActionListener(_ -> addCommentToRecipe());
            likeButton.addActionListener(_ -> likeRecipe());
            backButton.addActionListener(_ -> goBack());

            add(new JScrollPane(recipeList), BorderLayout.CENTER);
            add(buttonPanel, BorderLayout.SOUTH);

            updateRecipeList();
        }

        public void updateRecipeList() {
            listModel.clear();
            for (Recipe recipe : globalRecipes) {
                listModel.addElement(recipe.getName() + " (" + recipe.getLikes() + " likes)");
            }
        }

        private void viewRecipeDetails() {
            int selectedIndex = recipeList.getSelectedIndex();
            if (selectedIndex >= 0) {
                Recipe recipe = globalRecipes.get(selectedIndex);
                JOptionPane.showMessageDialog(
                    this,
                    """
                    Recipe Details:
                    Name: %s
                    Author: %s
                    Ingredients: %s
                    Steps: %s
                    Calories: %d
                    Age Group: %s
                    Likes: %d
                    Comments: %s
                    """.formatted(
                        recipe.getName(),
                        recipe.getAuthor(),
                        recipe.getIngredients().stream().map(Ingredient::getName).reduce((a, b) -> a + ", " + b).orElse("No ingredients"),
                        recipe.getSteps(),
                        recipe.getCalories(),
                        recipe.getAgeGroup(),
                        recipe.getLikes(),
                        recipe.getComments().isEmpty() ? "No comments" : String.join("\n", recipe.getComments())
                    ),
                    "Recipe Details",
                    JOptionPane.INFORMATION_MESSAGE
                );
            } else {
                JOptionPane.showMessageDialog(this, "Please select a recipe to view.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        private void addCommentToRecipe() {
            int selectedIndex = recipeList.getSelectedIndex();
            if (selectedIndex >= 0) {
                Recipe recipe = globalRecipes.get(selectedIndex);
                String comment = JOptionPane.showInputDialog(this, "Enter your comment:");
                if (comment != null && !comment.trim().isEmpty()) {
                    recipe.addComment(currentUser + ": " + comment.trim());
                    JOptionPane.showMessageDialog(this, "Comment added!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a recipe to comment on.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        private void likeRecipe() {
            int selectedIndex = recipeList.getSelectedIndex();
            if (selectedIndex >= 0) {
                Recipe recipe = globalRecipes.get(selectedIndex);
                recipe.incrementLikes();
                updateRecipeList();
                JOptionPane.showMessageDialog(this, "You liked this recipe!");
            } else {
                JOptionPane.showMessageDialog(this, "Please select a recipe to like.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        private void goBack() {
            showMainMenu();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RecipeAppMain().setVisible(true));
    }
}
