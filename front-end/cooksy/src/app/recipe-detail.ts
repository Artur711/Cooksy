import {RecipeProduct} from "./recipe-product";

export interface RecipeDetail {
  recipeId: number;

  tittle: string;

  description: string;

  pricePerServing: number;

  sourceUrl: number;

  products: RecipeProduct[];
}
