import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ProductComponent} from "./product/product.component";
import {MenuComponent} from "./menu/menu.component";
import {RecipeComponent} from "./recipe/recipe.component";
import {RecipeDetailsComponent} from "./recipe-details/recipe-details.component";
import {ShoppingListComponent} from "./shopping-list/shopping-list.component";
import {LoginComponent} from "./login/login.component";
import {RegisterComponent} from "./register/register.component";
import {AuthGuard} from "./guards/auth.guard";
import {CustomersGuard} from "./guards/customers.guard";

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'home', component: ProductComponent},
  { path: 'menu', component: MenuComponent},
  { path: 'recipes', component: RecipeComponent},
  { path: 'detail/:id', component: RecipeDetailsComponent},
  { path: 'shopping-lists', component: ShoppingListComponent},
  { path: 'products', component: ProductComponent},
  {
    path: 'login',
    component: LoginComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'register',
    component: RegisterComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'recipe',
    component: RecipeComponent,
    canActivate: [CustomersGuard],
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
