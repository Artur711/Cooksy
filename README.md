# Cooksy

## General info
Application CookSy was created to improve making shopping list by own by using external recipes.
In our application we use external API to downloading and displaying recipes.
Our app let on selecting dish by type, ingredients and equipments.
You can also select products to your shopping list or add recipe to your favorites.
On the main page (below) we have placed a products search engine.

## Necessary file
For the application to work correctly it's necessary add two file in the folder resources next application.properties file
(path: /src/main/resources) by named: "kroger-credentials.txt" and "api-key.txt".

In case kroger-credentials.txt file, first be due create account in https://developer.kroger.com/.
Then you should register your application, (after sing in,  go to the apps tab).
After registered your app, you should get client_id, client_secret and scope.
Each of details put in  a new line (without any from words client_id, only the code itself).

In case api-key.txt file, you have to create account in https://spoonacular.com/food-api.
After sign in, go to the profile tab. Copy api key in paste in to the file with prefix "apiKey=..." where in place 
"..." you paste your api key code.
Can you register more one on spoonacular, because only free points is 150/day. In this case each of api keys put
each in new line with each of them with prefix "apiKey=".

## Technology
Project is created with:
* Java version: 11.0
* Bootstrap version: 4
* Angular version: CLI

##Launch

