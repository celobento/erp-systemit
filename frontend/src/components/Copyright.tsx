import MuiLink from "@mui/material/Link";
import Typography from "@mui/material/Typography";

export default function Copyright() {
  return (
    <Typography
      variant="body2"
      align="center"
      sx={{
        color: "text.secondary",
      }}
    >
      {"Copyright © "}
      <MuiLink color="inherit" href="http://www.systemit.com.br/">
        www.systemit.com.br
      </MuiLink>{" "}
      {new Date().getFullYear()}.
    </Typography>
  );
}
