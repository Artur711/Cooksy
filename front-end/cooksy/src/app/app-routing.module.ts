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
import {AlwaysAuthGuardGuard} from "./guards/always-auth-guard.guard";


const routes: Routes = [
  { path: '', redirectTo: '/start', pathMatch: 'full', canActivate: [AlwaysAuthGuardGuard]},
  { path: 'start', component: FrontPageComponent, canActivate: [AlwaysAuthGuardGuard]},
  { path: 'start/login', component: LoginComponent, canActivate: [AlwaysAuthGuardGuard]},
  { path: 'start/register', component: RegisterComponent, canActivate: [AlwaysAuthGuardGuard]},
  { path: 'menu', component: MenuComponent, canActivate: [AuthGuard, AlwaysAuthGuardGuard],
  children: [
    { path: 'home', component: HomeComponent, canActivate: [AuthGuard, AlwaysAuthGuardGuard]},
    { path: 'recipes', component: RecipeComponent, canActivate: [AuthGuard, AlwaysAuthGuardGuard]},
    { path: 'recipes/:page', component:RecipeComponent, canActivate: [AuthGuard, AlwaysAuthGuardGuard]},
    { path: 'recipes/detail/:id', component: RecipeDetailsComponent, canActivate: [AuthGuard, AlwaysAuthGuardGuard]},
    { path: 'shopping-lists', component: ShoppingListComponent, canActivate: [AuthGuard, AlwaysAuthGuardGuard]},
    { path: 'favorites', component: FavoritesComponent, canActivate: [AuthGuard, AlwaysAuthGuardGuard]},
    { path: 'favorites/detail/:id', component: RecipeDetailsComponent, canActivate: [AuthGuard, AlwaysAuthGuardGuard]},
    { path: 'setting', component: HomeComponent, canActivate: [AuthGuard, AlwaysAuthGuardGuard]}]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
