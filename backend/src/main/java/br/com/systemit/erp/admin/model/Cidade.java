package br.com.systemit.erp.admin.model;

import jakarta.persistence.*;

@NamedQueries({
        @NamedQuery(name = "Cidade.findCidadeByEstado", 	query = " select c from Cidade c where  c.estado.id = :estadoID "),
        @NamedQuery(name = Cidade.BUSCA_POR_NOME, 			query = " select c from Cidade c where trim(lower(c.nome)) = :cidadeNome order by c.nome "),
        @NamedQuery(name = Cidade.BUSCA_POR_ID, 			query = " select c from Cidade c where c.id = :id "),
        @NamedQuery(name = Cidade.BUSCA_POR_NOME_UF_SIGLA, 	query = " select c from Cidade c where trim(lower(c.nome)) = :cidadeNome and c.estado.nome = :ufSigla "),
})
@Entity
@Table(name = "cidade", schema = "public")
public class Cidade {

    public static final String BUSCA_POR_NOME = "Cidade.findNome";

    public static final String BUSCA_POR_NOME_UF_SIGLA = "Cidade.findNomeUfSigla";

    public static final String BUSCA_POR_ID = "Cidade.findPorId";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estadoId", referencedColumnName = "id")
    private Uf estado;

    //@OneToMany(mappedBy = "naturalidade", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //private List<Pessoa> pessoas;

    //@OneToMany(mappedBy = "cidadeDestino", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //private List<Distancia> distancias;

    //@OneToMany(mappedBy = "cidade", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //private List<Bairro> bairros;

//    public Cidade(){
//        this.pessoas = new ArrayList<Pessoa>();
//        this.bairros = new ArrayList<Bairro>();
//        this.distancias = new ArrayList<Distancia>();
//    }

}