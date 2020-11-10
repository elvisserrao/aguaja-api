package com.aguaja.api.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TB_TESTE")
public class Teste implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "idade")
    private Integer idade;

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Teste() {

    }

    public Teste(String nome, Integer idade) {
        this.nome = nome;
        this.idade = idade;
    }
}
