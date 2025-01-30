interface Modulo {
    id?: number;
    nome: string;
}

interface Perfil {
    id?: number;
    nome: string;
    descricao: string;
    role: string;
    modulos: Modulo[]
}

export { type Modulo, type Perfil };
