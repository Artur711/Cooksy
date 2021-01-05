import {Component, OnInit} from '@angular/core';
import {Location} from '@angular/common';
import {RecipesService} from "../recipes.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-recipe-details',
  templateUrl: './recipe-details.component.html',
  styleUrls: ['./recipe-details.component.css']
})
export class RecipeDetailsComponent implements OnInit {
  recipeId: string | null = '';

  constructor(
    private location: Location,
    private recipeService: RecipesService,
    private activatedRoute: ActivatedRoute
  ) {
    this.recipeId = this.activatedRoute.snapshot.paramMap.get('id')
  }

  ngOnInit(): void {
  }

  goBack(): void {
    this.location.back();
  }
  // we need to change parameter to global variable at the end
  addRecipeToList(recipeID: string, userID: string) {
    this.recipeService.addRecipe(recipeID, userID);
  }
}
