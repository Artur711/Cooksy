import { Component, OnInit } from '@angular/core';
import {Recipe} from "../recipe";
import {RecipesService} from "../recipes.service";

@Component({
  selector: 'app-recipe',
  templateUrl: './recipe.component.html',
  styleUrls: ['./recipe.component.css']
})
export class RecipeComponent implements OnInit {
  recipes: Recipe[] = [];
  title = 'Dom'

  constructor(private recipeService: RecipesService) { }

  ngOnInit(): void {
    this.recipeService
      .getRecipes()
      // .subscribe(recipes => this.recipes = recipes);
  }
}
