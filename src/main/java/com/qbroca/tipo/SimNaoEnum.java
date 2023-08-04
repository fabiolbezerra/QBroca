package com.qbroca.tipo;

public enum SimNaoEnum {
    SIM('S', "Sim"),
    NAO('N', "Não");

    private char valor;
    private String descricao;

    private SimNaoEnum(char valor, String descricao) {
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

    public boolean isSim() {
        return this.valor == SIM.valor;
    }

    public static SimNaoEnum toEnum(char valor) {
        if (valor == SimNaoEnum.SIM.valor) {
            return SimNaoEnum.SIM;
        } else {
            return SimNaoEnum.NAO;
        }
    }

    @Override
    public String toString() {
        return this.descricao;
    }
}
