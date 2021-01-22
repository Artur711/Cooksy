import { Component, OnInit } from '@angular/core';
import {RecipesService} from "../service/recipes.service";
import {Recipe} from "../model/recipe";
import { debounceTime, distinctUntilChanged} from 'rxjs/operators';
import {TypeDish} from "../model/type";
import {Select} from "../model/select";

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
    this.getPage();
  }

  getPage(): void {
    this.recipeService.getRecipesPage$(this.page, this.ingredients, this.equipments, this.types)
      // .pipe(
      //   debounceTime(120),
      //   distinctUntilChanged(),
      // )
      .subscribe(recipes => {this.recipes = recipes.recipes
      this.pages = recipes.numberOfPages
      this.page = recipes.page});
  }

  onSelectChange(select: Select): void {
    this.ingredients = select.ingredients;
    this.equipments = select.equipments;
    this.types = select.types;
    this.getPage();
  }
}
