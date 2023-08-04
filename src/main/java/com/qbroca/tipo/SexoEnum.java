package com.qbroca.tipo;

public enum SexoEnum {
    MASCULINO('M', "Masculino"),
    FEMININO('F', "Feminino");

    private char valor;
    private String descricao;

    SexoEnum(char valor, String descricao) {
        this.valor = valor;
        this.descricao = descricao;
    }

    public char getValor() {
        return valor;
    }

    public void setValor(char valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public static SexoEnum toSexoEnum(char valor) {
        if (valor == SexoEnum.MASCULINO.valor) {
            return SexoEnum.MASCULINO;
        } else {
            return SexoEnum.FEMININO;
        }
    }

    @Override
    public String toString() {
        return this.descricao;
    }
}
