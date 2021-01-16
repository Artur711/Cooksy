import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import {ActivatedRoute} from "@angular/router";
import {RecipesService} from "../recipes.service";
import {Details} from "../details";


@Component({
  selector: 'app-recipe-details',
  templateUrl: './recipe-details.component.html',
  styleUrls: ['./recipe-details.component.css']
})
export class RecipeDetailsComponent implements OnInit {

  details: Details | undefined;

  constructor(
    private route: ActivatedRoute,
    private recipesService: RecipesService,
    private location: Location
  ) { }

  ngOnInit(): void {
    // this.getRecipe();
  }

  // getRecipe(): void {
  //   //const id = +this.route.snapshot.paramMap.get('id');
  //
  //   const id = 716426;
  //   this.recipesService.getRecipeDetail(id)
  //     .subscribe(details => this.details = details);
  // }

  goBack(): void {
    this.location.back();
  }
}
