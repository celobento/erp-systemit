package br.com.systemit.erp.auth.model;

import br.com.systemit.erp.admin.model.Empresa;
import br.com.systemit.erp.admin.model.Setor;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Entity
@Table(name = "perfil", schema = "public")
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private String apelido;

    @Temporal(TemporalType.DATE)
    private Date dataCadastro;

    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimoAcesso;

    @Size(min = 5)
    private String login;

    @Size(min = 6)
    private String senha;

    @Size(min = 6)
    private String ultimaSenha;

    private Boolean primeiroAcesso;

    private Boolean status;

    private Integer tentativas;

    @Email
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idSetor", referencedColumnName = "id")
    private Setor setor;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "idPessoa", referencedColumnName = "id")
//    private Pessoa pessoa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEmpresa", referencedColumnName = "id")
    private Empresa empresa;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "idFilial", referencedColumnName = "id")
//    private Filial filial;

//    @OneToMany(mappedBy = "chefia", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Setor> setores;
//
//    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<UsuarioAutenticacao> usuariosAutenticacao;
//
//    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<UsuarioGrupoAcesso> usuariosGrupoAcesso;

//    public Usuario() {
//        this.usuariosAutenticacao = new ArrayList<UsuarioAutenticacao>(0);
//        this.usuariosGrupoAcesso = new ArrayList<UsuarioGrupoAcesso>(0);
//    }

}
