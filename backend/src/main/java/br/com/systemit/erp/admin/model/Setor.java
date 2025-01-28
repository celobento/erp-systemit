package br.com.systemit.erp.admin.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@NamedQueries({
        @NamedQuery(name = "Setor.listaSetor", query = "SELECT s FROM Setor s order by s.nome")
})
@Table(name = "setor", schema = "public")
@Getter
@Setter
public class Setor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private String cod;

    //@OneToMany(mappedBy = "setor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //private List<Usuario> usuarios;

    //@OneToMany(mappedBy = "setor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //private List<CentroCusto> centrosCusto;

    @ManyToOne
    @JoinColumn(name = "idEmpresa", referencedColumnName = "id")
    private Empresa empresa;

    //@OneToMany(mappedBy = "setor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //private List<Funcionario> funcionarios;

    //@ManyToOne
    //@JoinColumn(name = "idChefia", referencedColumnName = "id")
    //private Usuario chefia;

//    public Setor() {
//        this.usuarios = new ArrayList<Usuario>();
//        this.centrosCusto = new ArrayList<CentroCusto>();
//        this.funcionarios  = new ArrayList<Funcionario>();
//    }

}
