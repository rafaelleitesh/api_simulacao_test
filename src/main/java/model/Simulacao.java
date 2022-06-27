package model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;

public class Simulacao {
    @JsonIgnore
    private long id;
    private String nome;
    private String cpf;
    private String email;
    private BigDecimal valor;
    private Integer parcelas;
    private Boolean seguro;

    public Simulacao(String nome, String cpf, String email, BigDecimal valor, Integer parcelas, Boolean seguro) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.valor = valor;
        this.parcelas = parcelas;
        this.seguro = seguro;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Integer getParcelas() {
        return parcelas;
    }

    public Boolean getSeguro() {
        return seguro;
    }
}
