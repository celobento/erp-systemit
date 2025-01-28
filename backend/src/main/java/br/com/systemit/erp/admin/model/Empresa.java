package br.com.systemit.erp.admin.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name="Empresa.findByCodIntergrall", query="select e from Empresa e where e.codigoIntergrall = :codIntergrall "),
        @NamedQuery(name= Empresa.BUSCA_TODAS_COD_INTERGRAL, query="select e from Empresa e where e.codigoIntergrall is not null "),
        @NamedQuery(name= Empresa.BUSCA_TODAS, query="select e from Empresa e ORDER BY e.razaoSocial"),
        @NamedQuery(name= Empresa.BUSCA_POR_ID, query="select e from Empresa e WHERE e.id = :idEmpresa")
})
@Table(name = "empresa", schema = "public")
public class Empresa {

    public static final String BUSCA_TODAS_COD_INTERGRAL = "Empresa.findByCodIntergrallAll";

    public static final String BUSCA_TODAS = "Empresa.findByAll";

    public static final String BUSCA_POR_ID = "Empresa.findById";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String razaoSocial;

    private String nomeFantasia;

    private String codigoFortes;

    private String cnpj;

    private String inscricaoEstadual;

    private String responsavel;

    private String CodigoBradescoPagueFor;

    private String codigoIntergrall;

    public String Logradouro;

    public String complemento;

    public String numero;

    public String email;

    public String cep;

    public String bairroContato;

    public String celular;

    public String foneFixo;

    public String responsavelSinergia;

    public String foneResponsavelSinergia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ufIdLogradouro", referencedColumnName = "id")
    private Uf ufLogradouro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCidade", referencedColumnName = "id")
    private Cidade cidade;

    //@OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //private List<ContaEmpresa> contasEmpresa;

    //public Empresa() {
    //    this.contasEmpresa = new ArrayList<ContaEmpresa>();
    //}

}