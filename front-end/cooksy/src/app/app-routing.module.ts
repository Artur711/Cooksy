import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ProductComponent} from "./product/product.component";
import {MenuComponent} from "./menu/menu.component";
import {RecipeComponent} from "./recipe/recipe.component";
import {RecipeDetailsComponent} from "./recipe-details/recipe-details.component";

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'home', component: ProductComponent},
  { path: 'menu', component: MenuComponent},
  { path: 'recipes', component: RecipeComponent},
  { path: 'detail/:id', component: RecipeDetailsComponent},
  { path: 'products', component: ProductComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
