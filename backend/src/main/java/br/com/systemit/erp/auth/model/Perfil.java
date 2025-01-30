package br.com.systemit.erp.auth.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "perfil", schema = "public")
@Getter
@Setter
@NamedQueries({
    @NamedQuery(    name = Perfil.FIND_ALL,
                    query = " SELECT o FROM Perfil o " +
                            " order by o.id"),
    @NamedQuery(    name = Perfil.FIND_BY_MODULO,
                    query = " SELECT o FROM Perfil o " +
                            " where o.modulo.id = :idModulo  " +
                            " order by o.nome"
        )
})
public class Perfil {

    public static final String FIND_ALL = "Perfil.findAllOrdered";
    public static final String FIND_BY_MODULO = "Perfil.findByModulo";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "discriminacao")
    @Size(max=500)
    private String discriminacao;

    @Column(name = "role")
    private String role;

//    @OneToMany(mappedBy = "perfil", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<UsuarioAutenticacao> usuariosAutenticacao;

    @ManyToOne
    @JoinColumn(name = "idmodulo", referencedColumnName = "id")
    private Modulo modulo;

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

    public @Size(max = 500) String getDiscriminacao() {
        return discriminacao;
    }

    public void setDiscriminacao(@Size(max = 500) String discriminacao) {
        this.discriminacao = discriminacao;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }
}
