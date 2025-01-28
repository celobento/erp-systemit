package br.com.systemit.erp.auth.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "perfil", schema = "public")
@Getter
@Setter
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @Size(max=500)
    private String discriminacao;

    private String role;

//    @OneToMany(mappedBy = "perfil", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<UsuarioAutenticacao> usuariosAutenticacao;

    @ManyToOne
    @JoinColumn(name = "idmodulo", referencedColumnName = "id")
    private Modulo modulo;

}
