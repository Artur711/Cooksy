import { Component, OnInit } from '@angular/core';
import {RecipesService} from "../service/recipes.service";
import {Recipe} from "../model/recipe";
import { debounceTime, distinctUntilChanged} from 'rxjs/operators';

@Component({
  selector: 'app-recipe',
  templateUrl: './recipe.component.html',
  styleUrls: ['./recipe.component.css']
})
export class RecipeComponent implements OnInit {
  ingredient = '';
  recipes: Recipe[] = [];
  pages = 1;
  page = 1;

  constructor(private recipeService: RecipesService) { }

  ngOnInit(): void {
    console.log(this.ingredient);
    this.recipeService
      .getRecipesPage$(this.page, this.ingredient)
      .pipe(
        debounceTime(300),
        distinctUntilChanged(),
      )
      .subscribe(recipes => {this.recipes = recipes.recipes
      this.pages = recipes.numberOfPages
      this.page = recipes.page});
  }

  getPage(): void {
    this.recipeService.getRecipesPage$(this.page, this.ingredient)
      .subscribe(recipes => {this.recipes = recipes.recipes
      this.pages = recipes.numberOfPages
      this.page = recipes.page});
  }
}
