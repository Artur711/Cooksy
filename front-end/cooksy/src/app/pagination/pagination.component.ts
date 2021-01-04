import { Component, OnInit } from '@angular/core';
import {RecipesService} from "../recipes.service";

@Component({
  selector: 'app-pagination',
  templateUrl: './pagination.component.html',
  styleUrls: ['./pagination.component.css']
})
export class PaginationComponent implements OnInit {
  pages: number = 0;
  page: number = 0;

  constructor(private recipeService: RecipesService) { }

  ngOnInit(): void {
    this.recipeService
      .getRecipes()
      .subscribe(recipes => this.pages = recipes.totalResults/recipes.limit);

    this.recipeService
      .getRecipes()
      .subscribe(recipes => this.page = recipes.offset/recipes.limit + 1);
  }
}
