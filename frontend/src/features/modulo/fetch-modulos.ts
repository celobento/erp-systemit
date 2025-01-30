"use client"
import { Perfil } from "@erp/core";
import { useQuery } from "@tanstack/react-query";
import { axios } from "../../app/lib/axios";


export function useModulos(query: String) {
    return useQuery({
        queryKey: ["modulos", query],
        queryFn: () => fetchModulos(query),
        staleTime: 30000, // 5 minutos
        //cacheTime: 60000 // 10 minutos
    })
}

export async function fetchModulos(query: String): Promise<Perfil[]> {
    return await axios.get(`/modulos${query}`).then((res) => res.data);
}