package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import utils.CPFRandom;
import utils.NomeRandom;
import utils.RangeNumeroRandom;

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

    public Simulacao() throws Exception {
        String cpf = CPFRandom.geraCPFRandom();
        String nome = NomeRandom.geraNome();

        this.nome = nome;
        this.cpf = cpf;
        this.email = RangeNumeroRandom.geraNumeroRandom(1, 9999)+"@email.com";
        this.valor = new BigDecimal(RangeNumeroRandom.geraNumeroRandom(1000, 40000));
        this.parcelas = RangeNumeroRandom.geraNumeroRandom(2, 48);
        this.seguro = RangeNumeroRandom.geraBoolRandom();
    }

    public Simulacao(BigDecimal valor, Integer parcelas, Boolean seguro) throws Exception {
        String cpf = CPFRandom.geraCPFRandom();
        String nome = NomeRandom.geraNome();

        this.nome = nome;
        this.cpf = cpf;
        this.email = nome.substring(0, nome.indexOf(" "))+"@email.com";
        this.valor = valor;
        this.parcelas = parcelas;
        this.seguro = seguro;
    }

    public Simulacao(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.valor = new BigDecimal(RangeNumeroRandom.geraNumeroRandom(1000, 40000));
        this.parcelas = RangeNumeroRandom.geraNumeroRandom(2, 48);
        this.seguro = RangeNumeroRandom.geraBoolRandom();
    }

    public long getId() {
        return id;
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
