import {Grammage} from "./grammage";

export interface Product {
  productId: number;
  name: string;
  amount : number;
  measuresAmount : number;
  productTypeId: number;
  isChecked: boolean;
  measuresUnitShort: string;
  marketId: number;
  grammage: Grammage;
}
