import { Component, OnInit } from '@angular/core';
import {RecipeComponent} from "../recipe/recipe.component";

@Component({
  selector: 'app-select',
  templateUrl: './select.component.html',
  styleUrls: ['./select.component.css']
})
export class SelectComponent implements OnInit {

  ingredient = '';
  collapsed = true;
  meatIsCollapsed = true;
  vegetablesIsCollapsed = true;
  additionalIsCollapsed = true;
  promotionIsCollapsed = true;
  searchIsCollapsed = true;

  constructor(private recipe: RecipeComponent) { }

  ngOnInit(): void { }

  meatCollapsed(): void {
    this.collapsedAll(this.meatIsCollapsed);
    this.meatIsCollapsed = !this.collapsed;
  }

  vegetablesCollapsed(): void {
    this.collapsedAll(this.vegetablesIsCollapsed);
    this.vegetablesIsCollapsed = !this.collapsed;
  }

  additionalCollapsed(): void {
    this.collapsedAll(this.additionalIsCollapsed);
    this.additionalIsCollapsed = !this.collapsed;
  }

  promotionCollapsed(): void {
    this.collapsedAll(this.promotionIsCollapsed);
    this.promotionIsCollapsed = !this.collapsed;
  }

  searchCollapsed(): void {
    this.collapsedAll(this.searchIsCollapsed);
    this.searchIsCollapsed = !this.collapsed;
  }

  search(ingredient: string): void {
    this.ingredient = ingredient.replace(' ', '-');
    this.recipe.ingredient = this.ingredient;
    this.recipe.getPage();
  }

  private collapsedAll(collapsed: boolean): void {
    this.collapsed = collapsed;
    this.meatIsCollapsed = true;
    this.vegetablesIsCollapsed = true;
    this.additionalIsCollapsed = true;
    this.promotionIsCollapsed = true;
    this.searchIsCollapsed = true;
  }
}
