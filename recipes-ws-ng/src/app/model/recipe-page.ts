import { Recipe } from './recipe';

export interface RecipePage {
  content: Recipe[];
  totalElements: number;
  totalPages: number;
}
