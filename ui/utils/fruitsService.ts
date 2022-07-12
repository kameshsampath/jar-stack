import useSWR from 'swr';
import { FruitsData } from './constants';

export function FetchFruits(): FruitsData {
  const fetcher = (url: string) => fetch(url).then((res) => res.json());

  const { data } = useSWR('/api/fruits', fetcher);

  //console.log('API Data %s', JSON.stringify(data));

  if (!data) {
    return {
      isLoading: true,
      fruitsData: null,
      isError: true
    };
  } else {
    return {
      isLoading: !data.fruits && !data.err,
      fruitsData: data,
      isError: data.err
    };
  }
}
