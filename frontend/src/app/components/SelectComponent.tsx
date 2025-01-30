"use client";

import {
  Box,
  FormControl,
  InputLabel,
  MenuItem,
  Select,
  SelectChangeEvent,
} from "@mui/material";
import { useState } from "react";

interface SelectComponentProps {
  grupos: any;
}

const SelectComponent = () => {
  const [age, setAge] = useState("");
  const handleChange = (event: SelectChangeEvent) => {
    setAge(event.target.value as string);
  };

  return (
    <Box sx={{ minWidth: 120 }}>
      <FormControl fullWidth>
        <InputLabel id="demo-simple-select-label">Status</InputLabel>
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
          <MenuItem value={1}>Ativo</MenuItem>
          <MenuItem value={0}>Inativo</MenuItem>
        </Select>
      </FormControl>
    </Box>
  );
};

export default SelectComponent;
