# ASKY
OOPS PROJECT
This application is a recipe management platform where users can:
>Save recipes with ingredients, steps, calories, and target age groups.
>View recipes globally, like recipes, and comment on them.
>Add suggestions for recipes.
>Log in or register as a user.


Prerequisites
Ensure the following tools and libraries are installed on your system before running the application:
>Java Development Kit (JDK):
Version: 8 or above.
>Download and install from Oracle's Java website or use an OpenJDK distribution.
>VS Code:
Download and install Visual Studio Code.
>VS Code Extensions:
Install the following extensions in VS Code:
>Java Extension Pack by Microsoft.
>Git:
Install Git to manage and download the code repository.


Setting Up the Project
>Step 1: Clone or Download the Project
Place the provided Updated_RecipeApp folder in your working directory.
Open VS Code.
Go to File → Open Folder... → Select the Updated_RecipeApp folder.
>Step 2: Configure VS Code for Java
After opening the folder, VS Code will detect Java files.
Ensure that the Java Extension Pack is installed.
You can confirm by checking the extensions view (Ctrl + Shift + X) in VS Code.
>Step 3: Build the Project
Open the Terminal in VS Code:
Go to View → Terminal.
Alternatively, press Ctrl + `.
Compile the Java files:
javac RecipeApp/src/*.java
>Step 4: Run the Application
After compilation, run the main class:
java -cp RecipeApp/src RecipeAppMain
This will launch the application with the GUI.

Key Files and Directories
>src Directory:
Contains all the source files required for the application.
>Main Classes:
RecipeAppMain.java: The main entry point of the application.
Recipe.java: Defines the structure and behavior of a recipe.
User.java: Manages user-related functionality.
>Supporting Classes:
Ingredient.java: Represents an ingredient with a name and calorie count.
Like.java: Handles like functionality.
Comment.java: Handles recipe comments.

Features
>Login and Registration:
Users can register with a username and password or log in if already registered.
>Save Recipe:
Save recipes with:
Name
Ingredients
Steps
Calories
Target age group
>View Recipes:
View a list of all saved recipes.
See details including likes, comments.
>Interactions:
Like recipes.
Add comments to recipes.
.

Libraries and Dependencies
This project does not use any external libraries; it runs entirely on Java's standard libraries, including:
AWT (java.awt) for GUI components.
Swing (javax.swing) for GUI design.


