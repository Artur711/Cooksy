import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {RecipeComponent} from "./recipe/recipe.component";
import {RecipeDetailsComponent} from "./recipe-details/recipe-details.component";
import {ShoppingListComponent} from "./shopping-list/shopping-list.component";
import {HomeComponent} from "./home/home.component";
import {FavoritesComponent} from "./favorites/favorites.component";
import {RegisterComponent} from "./register/register.component";
import {LoginComponent} from "./login/login.component";
import {AuthGuard} from "./guards/auth.guard";

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full'},
  { path: 'home', component: HomeComponent, canActivate: [AuthGuard]},
  { path: 'recipes', component: RecipeComponent, canActivate: [AuthGuard]},
  { path: 'recipes/:page', component:RecipeComponent, canActivate: [AuthGuard]},
  { path: 'detail/:id', component: RecipeDetailsComponent, canActivate: [AuthGuard]},
  { path: 'shopping-lists', component: ShoppingListComponent, canActivate: [AuthGuard]},
  { path: 'favorites', component: FavoritesComponent, canActivate: [AuthGuard]},
  { path: 'products', component: HomeComponent, canActivate: [AuthGuard]},
  { path: 'login', component: LoginComponent},
  { path: 'register', component: RegisterComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
