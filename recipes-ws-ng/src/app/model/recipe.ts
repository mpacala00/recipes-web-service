import { Ingredient } from "./ingredient";

export interface Recipe {
    id: string;
    name: string;
    category: string;
    ingredients: Ingredient[];
    description: string;
    instructions: string;
}
