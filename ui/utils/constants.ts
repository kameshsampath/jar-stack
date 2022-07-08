export type FruitsData = {
  fruitsData: any,
  isLoading: boolean,
  isError: boolean
}

export interface Props{
	fruits: Fruit[],
}

export type Fruit = {
	id: number,
	name: string,
	season: string,
	emoji?: string
}

export const FRUIT_SEASON_OPTIONS = [
  { value: 'spring', label: 'Spring', disabled: false },
  { value: 'summer', label: 'Summer', disabled: false },
  { value: 'fall', label: 'Fall', disabled: false },
  { value: 'winter', label: 'Winter', disabled: false },
  { value: 'any', label: 'Any', disabled: false }
];
