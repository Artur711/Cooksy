import { Component, OnInit } from '@angular/core';
import {RecipesService} from "../service/recipes.service";
import {Recipe} from "../model/recipe";
import { debounceTime, distinctUntilChanged} from 'rxjs/operators';
import {TypeDish} from "../model/type";

@Component({
  selector: 'app-recipe',
  templateUrl: './recipe.component.html',
  styleUrls: ['./recipe.component.css']
})
export class RecipeComponent implements OnInit {
  ingredients: string[] = [];
  equipments: string[] = [];
  types: TypeDish[] = [];
  recipes: Recipe[] = [];
  pages = 1;
  page = 1;

  constructor(private recipeService: RecipesService) { }

  ngOnInit(): void {
    const ingredient = RecipeComponent.getStringFromArrayElements(this.ingredients);
    const equipment = RecipeComponent.getStringFromArrayElements(this.equipments);
    const type = RecipeComponent.getStringFromArrayTypes(this.types);

    this.recipeService
      .getRecipesPage$(this.page, ingredient, equipment, type)
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
    const type = RecipeComponent.getStringFromArrayTypes(this.types);

    this.recipeService.getRecipesPage$(this.page, ingredient, equipment, type)
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

  private static getStringFromArrayTypes(array: TypeDish[]): string {
    let types = array.filter(type => type.isChecked);
    let result = '';

    if (types.length != array.length) {
      for (let index = 0; index < types.length; index++) {
        if (index != 0) {
          result = result + '-';
        }
        result = result + types[index].name;
      }
    }
    return result;
  }
}
