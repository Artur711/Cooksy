import {Component, OnInit} from '@angular/core';
import {Location} from '@angular/common';
import {RecipesService} from "../service/recipes.service";
import {ActivatedRoute} from "@angular/router";
import {RecipeDetails} from "../model/recipeDetails";
import {FavoritesService} from "../service/favorites.service";

@Component({
  selector: 'app-recipe-details',
  templateUrl: './recipe-details.component.html',
  styleUrls: ['./recipe-details.component.css']
})
export class RecipeDetailsComponent implements OnInit {
  private favoriteId = 0;
  recipeId: string = '';
  isFavorite = false;
  details$!: RecipeDetails;

  constructor(
    private activatedRoute: ActivatedRoute,
    private recipesService: RecipesService,
    private favoritesService: FavoritesService,
    private location: Location
  ) { }


  ngOnInit(): void {
    this.getRecipe();
    this.checkIfFavorite();
  }

  getRecipe(): void {
    const id = this.activatedRoute.snapshot.paramMap.get('id');
    this.recipesService.getRecipeDetail$(id)
      .subscribe(details => this.details$ = details);
  }

  goBack(): void {
    this.location.back();
  }

  checkIfFavorite(): void {
    const id = this.activatedRoute.snapshot.paramMap.get('id');
    this.favoritesService.isFavorite$(id)
      .subscribe(favorite => {
        this.isFavorite = favorite.favoriteId != null;
        this.favoriteId = favorite.favoriteId;
      });
  }

  mark(): void {
    if (this.isFavorite) {
      this.removeRecipeFromFavorite();
    } else {
      this.addRecipeToFavorite();
    }
  }

  private addRecipeToFavorite(): void {
    this.favoritesService.addRecipeToFavorite(this.details$)
      .subscribe(success => {
        if (success) {
          this.checkIfFavorite();
        }
      });
  }

  private removeRecipeFromFavorite():void {
    this.favoritesService.removeRecipeFromFavorite(this.favoriteId)
      .subscribe(success => {
        if (success) {
          this.checkIfFavorite();
        }
      });
  }

  // we need to change parameter to global variable at the end
  addRecipeToList( userID: string) {
    this.recipesService.addRecipe(this.details$.recipeId.toString(), userID).subscribe();
  }
}
