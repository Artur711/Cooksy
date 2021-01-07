import {Component, OnInit} from '@angular/core';
import {Location} from '@angular/common';
import {RecipesService} from "../recipes.service";
import {ActivatedRoute} from "@angular/router";
import {Details} from "../details";


@Component({
  selector: 'app-recipe-details',
  templateUrl: './recipe-details.component.html',
  styleUrls: ['./recipe-details.component.css']
})
export class RecipeDetailsComponent implements OnInit {
  recipeId: string | null = '';

  details: Details | undefined;

  constructor(
    private route: ActivatedRoute,
    private recipesService: RecipesService,
    private location: Location
  ) { }


  ngOnInit(): void {
    this.getRecipe();

    this.recipeId = this.route.snapshot.paramMap.get('id')

  }

  getRecipe(): void {
    //const id = +this.route.snapshot.paramMap.get('id');

    const id = 716426;
    this.recipesService.getRecipeDetail(id)
      .subscribe(details => this.details = details);
  }

  goBack(): void {
    this.location.back();
  }
  // we need to change parameter to global variable at the end
  addRecipeToList(recipeID: string, userID: string) {
    this.recipesService.addRecipe(recipeID, userID);
  }
}
