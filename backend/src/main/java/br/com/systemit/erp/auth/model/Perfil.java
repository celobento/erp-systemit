package br.com.systemit.erp.auth.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Table(name = "perfil", schema = "public")
@Data
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
//@EntityListeners(AuditingEntityListener.class)
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

//    @CreatedDate
//    @Column(name = "data_cadatro")
//    private LocalDateTime dataCadastro;
//
//    @LastModifiedDate
//    @Column(name = "data_atualizacao")
//    private LocalDateTime dataAtualizacao;

}
