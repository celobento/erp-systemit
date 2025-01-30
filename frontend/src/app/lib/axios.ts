import Axios from "axios";

const token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjIiLCJpYXQiOjE3Mjk2NDEwNjQsImV4cCI6MTcyOTcyNzQ2NH0.cWcRQAZYVSVs2Jjf4MSLoBwexQoHoGsHJN8BkTzvB3M"

const URL_BASE = process.env.NEXT_PUBLIC_URL_BASE

export const axios = Axios.create({
    baseURL: URL_BASE,
    timeout: 5000,
    headers: { Authorization: `Bearer ${token}` }
})