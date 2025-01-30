import { NextRequest } from "next/server";
import { axios } from "../../lib/axios";

export async function GET(request: NextRequest) {
    const data = await axios
    .get('/grupo')
    .then((response: any) => response.data)
    return Response.json(data)
}