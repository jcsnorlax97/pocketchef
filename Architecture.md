# Architecture

**Directory**

## Iteration 1 Diagram

![architecture](architecture_model_1.png)

## Presentation/UI Layer
[MainActivity](https://code.cs.umanitoba.ca)
- The main activity is responsible for setting up the navigation bar and immediately going to the home fragment

[Home](https://code.cs.umanitoba.ca)
- the home view which is the view the users will first see when they open the app. Can search in home or select other views through navigation bar.

[AddRecipe](https://code.cs.umanitoba.ca)
- The view screen where users can add their own recipes to the application.

[Settings/ProfileXML](https://code.cs.umanitoba.ca)
- Undecided in iteration 1. Will either be a user profile or a settings view.

## Business Logic
[fragments](https://code.cs.umanitoba.ca)
- Undecided in iteration 1. Will either have business layer logic for each respective fragment, or a handler will be set for the fragments.

[RecipeHandler](https://code.cs.umanitoba.ca)
- Will handle all the logic regarding the fragments interacting with the database.

[settings/profile](https://code.cs.umanitoba.ca)
- Undecided in iteration 1. Will handle the logic of settings/profile and interact with DB

## Persistence/DB
[Recipes](https://code.cs.umanitoba.ca)
- Will store all the recipes

[Account](https://code.cs.umanitoba.ca)
- Undecided in iteration 1. Will store all the account settings. 

## Domain Specific Objects
[Recipe](https://code.cs.umanitoba.ca)
- The recipe object