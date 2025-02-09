package br.com.systemit.erp.auth.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "modulo", schema = "public")
@Data
public class Modulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome")
    private String nome;

//    @OneToMany(mappedBy = "modulo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Perfil> perfis;

}
