import {RecipeDetails} from "./recipeDetails";

export interface UserDto {

  userId: number;
  nick: string;
  firstName: string;
  lastName: string;
  email: string;
  password: string;
  photoUrl: string;
  userTypeId: number;
}

export interface FavoriteDto {

  favoriteId: number;
  user: UserDto;
  recipe: RecipeDetails;
}
