# Cooksy

## General info
Application CookSy was created to improve making shopping list by own by using external recipes.
In our application we use external API to downloading and displaying recipes.
Our app let on selecting dish by type, ingredients and equipments.
You can also select products to your shopping list or add the recipe to your favorites.

## Necessary file
For the application to work correctly it's necessary add environment properties. 
First be due create account in https://developer.kroger.com/.
Then you should register your application, (after sing in,  go to the apps tab).
After registered your app, you should get client_id, client_secret and scope.

In second case, you have to create account in https://spoonacular.com/food-api.
After sign in, go to the profile tab and get api key.
You can register more one on spoonacular, because only free points is 150 per day.
New api key exchange in environment properties when you will reach a day limit.

## Technology
Project is created with:
* Java version: 11.0
* Bootstrap version: 4
* Angular version: CLI

## Launch
In terminal write below commands:
cd front-end/cooksy
npm install - g @angular/cli

Next go o to  front-end/cooksy/node_modules/sweetalert/typings/sweetalert.d.ts file.
In this file remove line with "const swal: SweetAlert;" should be four line.

Run Angular (e.g in terminal write "ng serve") and Spring.

