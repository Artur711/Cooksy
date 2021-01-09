import {Component, OnInit} from '@angular/core';
import {Location} from '@angular/common';
import {RecipesService} from "../service/recipes.service";
import {ActivatedRoute} from "@angular/router";
import {Details} from "../model/details";

@Component({
  selector: 'app-recipe-details',
  templateUrl: './recipe-details.component.html',
  styleUrls: ['./recipe-details.component.css']
})
export class RecipeDetailsComponent implements OnInit {
  recipeId: string | null = '';

  details!: Details;

  constructor(
    private route: ActivatedRoute,
    private recipesService: RecipesService,
    private location: Location
  ) { }


  ngOnInit(): void {
    this.getRecipe();
  }

  getRecipe(): void {
    const id = this.route.snapshot.paramMap.get('id');
    console.log(id);
    this.recipesService.getRecipeDetail(id)
      .subscribe(details => this.details = details);
  }

  goBack(): void {
    this.location.back();
  }
  // we need to change parameter to global variable at the end
  addRecipeToList( userID: string) {
    this.recipesService.addRecipe(this.details.recipeId.toString(), userID).subscribe();
  }
}
