"use client"
import { Perfil } from "@erp/core";
import { useQuery } from "@tanstack/react-query";
import { axios } from "../../app/lib/axios";


export function usePerfis(query: String) {
    return useQuery({
        queryKey: ["perfis", query],
        queryFn: () => fetchPerfis(query),
        staleTime: 30000, // 5 minutos
        //cacheTime: 60000 // 10 minutos
    })
}

export async function fetchPerfis(query: String): Promise<Perfil[]> {
    return await axios.get(`/perfis${query}`).then((res) => res.data);
}