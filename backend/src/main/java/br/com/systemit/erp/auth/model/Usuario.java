package br.com.systemit.erp.auth.model;

import br.com.systemit.erp.admin.model.Empresa;
import br.com.systemit.erp.admin.model.Setor;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;


@Entity
@Table(name = "usuario", schema = "public")
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private String apelido;

    @Temporal(TemporalType.DATE)
    @Column(name = "datacadastro")
    private Date dataCadastro;

    @Column(name = "ultimoacesso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimoAcesso;

    @Size(min = 5)
    private String login;

    @Column
    @Size(min = 6)
    private String senha;

    @Column(name = "ultimasenha")
    @Size(min = 6)
    private String ultimaSenha;

    @Column(name = "primeiroacesso")
    private Boolean primeiroAcesso;

    private Boolean status;

    private Integer tentativas;

    @Email
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idsetor", referencedColumnName = "id")
    private Setor setor;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "idPessoa", referencedColumnName = "id")
//    private Pessoa pessoa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idempresa", referencedColumnName = "id")
    private Empresa empresa;

//    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<UsuarioAutenticacao> usuariosAutenticacao;
//
//    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<UsuarioGrupoAcesso> usuariosGrupoAcesso;

//    public Usuario() {
//        this.usuariosAutenticacao = new ArrayList<UsuarioAutenticacao>(0);
//        this.usuariosGrupoAcesso = new ArrayList<UsuarioGrupoAcesso>(0);
//    }
    public @Size(min = 5) String getLogin() {
        return login;
    }

    public void setLogin(@Size(min = 5) String login) {
        this.login = login;
    }

    public @Size(min = 6) String getSenha() {
        return senha;
    }

    public void setSenha(@Size(min = 6) String senha) {
        this.senha = senha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Date getUltimoAcesso() {
        return ultimoAcesso;
    }

    public void setUltimoAcesso(Date ultimoAcesso) {
        this.ultimoAcesso = ultimoAcesso;
    }

    public @Size(min = 6) String getUltimaSenha() {
        return ultimaSenha;
    }

    public void setUltimaSenha(@Size(min = 6) String ultimaSenha) {
        this.ultimaSenha = ultimaSenha;
    }

    public Boolean getPrimeiroAcesso() {
        return primeiroAcesso;
    }

    public void setPrimeiroAcesso(Boolean primeiroAcesso) {
        this.primeiroAcesso = primeiroAcesso;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getTentativas() {
        return tentativas;
    }

    public void setTentativas(Integer tentativas) {
        this.tentativas = tentativas;
    }

    public @Email String getEmail() {
        return email;
    }

    public void setEmail(@Email String email) {
        this.email = email;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
