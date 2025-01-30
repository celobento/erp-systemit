"use client";

import {
  Box,
  FormControl,
  InputLabel,
  MenuItem,
  Select,
  SelectChangeEvent,
} from "@mui/material";
import { useRouter } from "next/navigation";
import { useState } from "react";

interface SelectGrupoComponentProps {
  modulos: any;
}

const SelectGrupoComponent = ({ modulos }: SelectGrupoComponentProps) => {
  const router = useRouter();
  const [age, setAge] = useState("");

  const handleChange = (event: SelectChangeEvent) => {
    setAge(event.target.value as string);
    console.log("selected: ", event.target.value);
    const params = new URLSearchParams();
    if (event.target.value) params.append("modulo", event.target.value);
    const query = params.size ? "?" + params.toString() : "";
    console.log("query::::", query);
    router.push("perfil" + query);
  };

  return (
    <Box sx={{ minWidth: 120 }}>
      <FormControl fullWidth>
        <InputLabel id="demo-simple-select-label">Modulo</InputLabel>
        <Select
          labelId="demo-simple-select-label"
          id="demo-simple-select"
          value={age}
          label="Age"
          onChange={handleChange}
        >
          <MenuItem value="">
            <em>--selecione--</em>
          </MenuItem>
          {modulos &&
            modulos.map((m: any) => <MenuItem value={m.id}>{m.nome}</MenuItem>)}
        </Select>
      </FormControl>
    </Box>
  );
};

export default SelectGrupoComponent;
