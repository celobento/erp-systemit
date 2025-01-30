"use client";
import ExpandMoreIcon from "@mui/icons-material/ExpandMore";
import {
  Accordion,
  AccordionDetails,
  AccordionSummary,
  Button,
  CardContent,
  TextField,
  Tooltip,
  Typography,
} from "@mui/material";
import Box from "@mui/material/Box";
import Container from "@mui/material/Container";
import Grid from "@mui/material/Grid2";
import Paper from "@mui/material/Paper";
import { IconSquarePlus } from "@tabler/icons-react";
import { useSearchParams } from "next/navigation";
import { useModulos } from "../../../../features/modulo/fetch-modulos";
import { usePerfis } from "../../../../features/perfis/fetch-perfis";
import PerfilComponent from "../../../components/PerfilComponent";
import SelectGrupoComponent from "../../../components/SelectModuloComponent";
//export const metadata: Metadata = {
//  title: "Página dos Perfis",
//};
const bull = (
  <Box
    component="span"
    sx={{ display: "inline-block", mx: "2px", transform: "scale(0.8)" }}
  >
    •
  </Box>
);

const PerfilPage = () => {
  //const router = useRouter();
  const searchParams = useSearchParams();
  const moduloId = searchParams.get("modulo") || "null";
  const query = moduloId != "null" ? `/modulo/${moduloId}` : "";

  const { data: perfis, isPending, isError } = usePerfis(query);
  const {
    data: modulos,
    isPending: moduloPending,
    isError: moduloErro,
  } = useModulos("");

  return (
    <Container maxWidth="lg">
      <Paper elevation={3}>
        <Box
          sx={{
            display: "flex",
            flexDirection: "row",
            padding: 2,
            alignItems: "center",
          }}
        >
          <Typography component="div" variant="h5" style={{ fontWeight: 700 }}>
            Perfis
          </Typography>
          <Tooltip title="Criar Novo">
            <IconSquarePlus
              stroke={2}
              style={{
                marginLeft: "0.25rem",
              }}
            />
          </Tooltip>
        </Box>

        <CardContent>
          <Accordion sx={{ marginBottom: 2 }}>
            <AccordionSummary
              expandIcon={<ExpandMoreIcon />}
              aria-controls="panel1-content"
              id="panel1-header"
            >
              <Typography>Filtros</Typography>
            </AccordionSummary>
            <AccordionDetails>
              <Grid container spacing={2}>
                <Grid size={3}>
                  <TextField
                    id="outlined-basic"
                    label="Nome"
                    variant="outlined"
                  />
                </Grid>
                <Grid size={3}>
                  <SelectGrupoComponent
                    modulos={modulos}
                  ></SelectGrupoComponent>
                </Grid>
                <Grid size={4}>
                  <Button
                    variant="contained"
                    sx={{ marginRight: 2, padding: 1.9 }}
                  >
                    Filtrar
                  </Button>
                  <Button variant="outlined" sx={{ padding: 1.9 }}>
                    Limpar
                  </Button>
                </Grid>
              </Grid>
            </AccordionDetails>
          </Accordion>

          <PerfilComponent perfis={perfis}></PerfilComponent>
        </CardContent>
      </Paper>
    </Container>
  );
};

export default PerfilPage;
