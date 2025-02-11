package br.com.systemit.erp.auth.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "usuarioautenticacao", schema = "public")
@Data
public class UsuarioAutenticacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "dataativacao")
    private Date dataAtivacao;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "usuarioativacao")
    private String usuarioAtivacao;

    @ManyToOne
    @JoinColumn(name = "idusuario", referencedColumnName = "id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "idsistema", referencedColumnName = "id")
    private Modulo sistema;

    @ManyToOne
    @JoinColumn(name = "idperfil", referencedColumnName = "id")
    private Perfil perfil;

}
