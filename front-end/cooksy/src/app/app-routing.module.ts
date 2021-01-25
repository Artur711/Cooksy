import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ProductComponent} from "./product/product.component";
import {RecipeComponent} from "./recipe/recipe.component";
import {RecipeDetailsComponent} from "./recipe-details/recipe-details.component";
import {ShoppingListComponent} from "./shopping-list/shopping-list.component";
import {HomeComponent} from "./home/home.component";
import {FavoritesComponent} from "./favorites/favorites.component";
import {RegisterComponent} from "./register/register.component";
import {LoginComponent} from "./login/login.component";
import {AuthGuard} from "./guards/auth.guard";
import {FrontPageComponent} from "./front-page/front-page.component";

const routes: Routes = [
  { path: '', redirectTo: '/start', pathMatch: 'full'},
  { path: 'home', component: HomeComponent},
  { path: 'recipes', component: RecipeComponent},
  { path: 'recipes/:page', component:RecipeComponent},
  { path: 'detail/:id', component: RecipeDetailsComponent},
  { path: 'shopping-lists', component: ShoppingListComponent},
  { path: 'favorites', component: FavoritesComponent},
  { path: 'products', component: ProductComponent},
  { path: 'start', component: FrontPageComponent},
  { path: 'login', component: LoginComponent},
  { path: 'register', component: RegisterComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
