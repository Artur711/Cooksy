import {Grammage} from "./grammage";

export interface Product {
  productId: number;
  name: string;
  price: number;
  productTypeId: number;
  marketId: number;
  grammage: Grammage;
}
