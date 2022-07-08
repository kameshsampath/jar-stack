import { NextApiRequest, NextApiResponse } from "next";

export default async function handler(req:NextApiRequest, res:NextApiResponse):Promise<void> {
  const uri = process.env.FRUITS_API_URI ? process.env.FRUITS_API_URI : 'http://localhost:8080/api';
  //TODO handle errors
  const extRes = await fetch(`${uri}/fruits/`).then(res => res);
  const apiResponse = await extRes.json();

  // if (!fruits) {
  //   res.status(200).json( {
  //     fruits: [],
  //     err: extRes.statusText,
  //     errorCode: extRes.status
  //   });
  // }

  // console.log(`URI:${uri} response is ${JSON.stringify(apiResponse)}`);

  if (apiResponse.status >= 400){
    res.status(extRes.status)
      .json({
        fruits: [],
        err: extRes.statusText,
        errorCode: extRes.status
      });
  } else {
    res.status(200)
      .json({
        fruits: apiResponse,
        err: "",
        errorCode: 200
      });
  }
}
