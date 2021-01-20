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
  ingredients: string[] = [];
  equipments: string[] = [];
  recipes: Recipe[] = [];
  pages = 1;
  page = 1;

  constructor(private recipeService: RecipesService) { }

  ngOnInit(): void {
    const ingredient = RecipeComponent.getStringFromArrayElements(this.ingredients);
    const equipment = RecipeComponent.getStringFromArrayElements(this.equipments);

    this.recipeService
      .getRecipesPage$(this.page, ingredient, equipment)
      .pipe(
        debounceTime(300),
        distinctUntilChanged(),
      )
      .subscribe(recipes => {this.recipes = recipes.recipes
      this.pages = recipes.numberOfPages
      this.page = recipes.page});
  }

  getPage(): void {
    const ingredient = RecipeComponent.getStringFromArrayElements(this.ingredients);
    const equipment = RecipeComponent.getStringFromArrayElements(this.equipments);

    this.recipeService.getRecipesPage$(this.page, ingredient, equipment)
      .subscribe(recipes => {this.recipes = recipes.recipes
      this.pages = recipes.numberOfPages
      this.page = recipes.page});
  }

  private static getStringFromArrayElements(array: string[]): string {
    let result = '';
    for (let index = 0; index < array.length; index++) {
      if (index != 0) {
        result = result + '-';
      }
      result = result + array[index];
    }
    return result;
  }
}
