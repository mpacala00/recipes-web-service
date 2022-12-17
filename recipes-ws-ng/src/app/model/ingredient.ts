import { UnitOfMeasure } from "./unit-of-measure";

export interface Ingredient {
    id: string;
    name: string;
    quantity: number;
    unitOfMeasure: UnitOfMeasure; 
}
