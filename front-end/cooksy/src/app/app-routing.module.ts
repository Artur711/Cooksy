import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ProductComponent} from "./product/product.component";
import {MenuComponent} from "./menu/menu.component";
import {RecipeComponent} from "./recipe/recipe.component";
import {RecipeDetailsComponent} from "./recipe-details/recipe-details.component";
import {ShoppingListComponent} from "./shopping-list/shopping-list.component";
import {HomeComponent} from "./home/home.component";
import {FavoritesComponent} from "./favorites/favorites.component";

const routes: Routes = [
  { path: '', redirectTo: '/menu', pathMatch: 'full'},
  { path: 'home', component: HomeComponent},
  { path: 'menu', component: MenuComponent},
  { path: 'recipes', component: RecipeComponent},
  { path: 'recipes/:page', component:RecipeComponent},
  { path: 'detail/:id', component: RecipeDetailsComponent},
  { path: 'shopping-lists', component: ShoppingListComponent},
  { path: 'favorites', component: FavoritesComponent},
  { path: 'products', component: ProductComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
