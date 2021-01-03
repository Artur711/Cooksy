import {Product} from "./product";

export interface Recipe {
  recipeId: number;
  name: string;
  recipeProducts: Product[];
  description: string;
  photoUrl: string;
  author: string;
  sumPrices: number;
}
