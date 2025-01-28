package br.com.systemit.erp.admin.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = "Uf.findUfTodos", query = " select e from Uf e order by e.nome "),
        @NamedQuery(name = Uf.BUSCA_POR_NOME, query = " select e from Uf e where e.nome = :ufNome "),
        @NamedQuery(name = Uf.BUSCA_POR_ID,   query = " select c from Uf c where c.id = :id ")
})
@Entity
@Table(name = "uf", schema = "public")
@Getter
@Setter
public class Uf {

    public static final String BUSCA_POR_NOME = "Uf.findNome";

    public static final String BUSCA_POR_ID = "Uf.findPorId";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private String descricao;

    //@OneToMany(mappedBy = "uf", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //private List<Pessoa> pessoas;

    @OneToMany(mappedBy = "estado", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cidade> cidades;

    //@OneToMany(mappedBy = "uf", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //private List<SQI> sqi;

//    public Uf() {
//        this.pessoas = new ArrayList<Pessoa>();
//        this.cidades = new ArrayList<Cidade>();
//    }

}