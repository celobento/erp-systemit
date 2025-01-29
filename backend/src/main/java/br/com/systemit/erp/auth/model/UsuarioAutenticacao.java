package br.com.systemit.erp.auth.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "usuarioautenticacao", schema = "public")
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataAtivacao() {
        return dataAtivacao;
    }

    public void setDataAtivacao(Date dataAtivacao) {
        this.dataAtivacao = dataAtivacao;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getUsuarioAtivacao() {
        return usuarioAtivacao;
    }

    public void setUsuarioAtivacao(String usuarioAtivacao) {
        this.usuarioAtivacao = usuarioAtivacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Modulo getSistema() {
        return sistema;
    }

    public void setSistema(Modulo sistema) {
        this.sistema = sistema;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }


}
