import {Component, OnInit} from '@angular/core';
import {Location} from '@angular/common';
import {RecipesService} from "../service/recipes.service";
import {ActivatedRoute} from "@angular/router";
import {Details} from "../model/details";
import {FavoritesService} from "../service/favorites.service";

@Component({
  selector: 'app-recipe-details',
  templateUrl: './recipe-details.component.html',
  styleUrls: ['./recipe-details.component.css']
})
export class RecipeDetailsComponent implements OnInit {
  recipeId: string = '';

  details$!: Details;

  constructor(
    private activatedRoute: ActivatedRoute,
    private recipesService: RecipesService,
    private favoritesService: FavoritesService,
    private location: Location
  ) { }


  ngOnInit(): void {
    this.getRecipe();
  }

  getRecipe(): void {
    const id = this.activatedRoute.snapshot.paramMap.get('id');
    this.recipesService.getRecipeDetail$(id)
      .subscribe(details => this.details$ = details);
  }

  goBack(): void {
    this.location.back();
  }

  addRecipeToFavorite(): void {
    this.favoritesService.addRecipeToFavorite(this.details$)
      .subscribe(success => {
        if (success) {
          console.log('Success');
        }
      });
  }

  // we need to change parameter to global variable at the end
  addRecipeToList( userID: string) {
    this.recipesService.addRecipe(this.details.recipeId.toString(), userID).subscribe();
  }
}
