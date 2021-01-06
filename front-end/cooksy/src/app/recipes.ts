import {Recipe} from "./recipe";

export interface Recipes {

  limit: number;

  totalResults: number;

  offset: number;

  recipes: Recipe[];
}
