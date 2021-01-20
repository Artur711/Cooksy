import { Component, OnInit } from '@angular/core';
import {RecipeComponent} from "../recipe/recipe.component";

@Component({
  selector: 'app-select',
  templateUrl: './select.component.html',
  styleUrls: ['./select.component.css']
})
export class SelectComponent implements OnInit {

  ingredients: string[] = [];
  ingredient = '';
  equipments: string[] = [];
  equipment = '';
  isCollapsed = true;

  constructor(private recipe: RecipeComponent) { }

  ngOnInit(): void { }

  collapsed(): void {
    this.isCollapsed = !this.isCollapsed;
  }

  search(): void {
    this.recipe.ingredients = this.ingredients;
    this.recipe.equipments = this.equipments;
    this.recipe.getPage();
  }

  addIngredient(ingredient: string): void {
    if (ingredient != '') {
      this.ingredients.push(ingredient);
    }
  }

  removeIngredient(ingredient: string): void {
    const index: number = this.ingredients.indexOf(ingredient);
    if (index !== -1) {
      this.ingredients.splice(index, 1);
    }
  }

  addEquipment(equipment: string): void {
    if (equipment != '') {
      this.equipments.push(equipment);
    }
  }

  removeEquipment(equipment: string): void {
    const index: number = this.equipments.indexOf(equipment);
    if (index !== -1) {
      this.equipments.splice(index, 1);
    }
  }

  removeAll(): void {
    this.ingredients = [];
    this.equipments = [];
  }
}
