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
import {FrontPageComponent} from "./front-page/front-page.component";
import {MenuComponent} from "./menu/menu.component";
import {AlwaysAuthGuard} from "./guards/always-auth.guard";


const routes: Routes = [
  { path: '', redirectTo: '/start', pathMatch: 'full', canActivate: [AlwaysAuthGuard]},
  { path: 'start', component: FrontPageComponent, canActivate: [AlwaysAuthGuard]},
  { path: 'login', component: LoginComponent, canActivate: [AlwaysAuthGuard]},
  { path: 'register', component: RegisterComponent, canActivate: [AlwaysAuthGuard]},
  { path: 'menu', component: MenuComponent, canActivate: [AuthGuard, AlwaysAuthGuard],
  children: [
    { path: 'home', component: HomeComponent, canActivate: [AuthGuard, AlwaysAuthGuard]},
    { path: 'recipes', component: RecipeComponent, canActivate: [AuthGuard, AlwaysAuthGuard]},
    { path: 'recipes/:page', component:RecipeComponent, canActivate: [AuthGuard, AlwaysAuthGuard]},
    { path: 'recipes/detail/:id', component: RecipeDetailsComponent, canActivate: [AuthGuard, AlwaysAuthGuard]},
    { path: 'shopping-lists', component: ShoppingListComponent, canActivate: [AuthGuard, AlwaysAuthGuard]},
    { path: 'favorites', component: FavoritesComponent, canActivate: [AuthGuard, AlwaysAuthGuard]},
    { path: 'favorites/detail/:id', component: RecipeDetailsComponent, canActivate: [AuthGuard, AlwaysAuthGuard]},
    { path: 'setting', component: HomeComponent, canActivate: [AuthGuard, AlwaysAuthGuard]}]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
