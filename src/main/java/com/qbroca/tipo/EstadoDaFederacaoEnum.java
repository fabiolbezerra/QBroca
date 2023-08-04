package com.qbroca.tipo;

public enum EstadoDaFederacaoEnum {
    PARA("PA", "Pará"),
    AMAZONAS("AM", "Amazonas"),
    AMAPA("AP", "Amapá"),
    MARANHA("MA", "Maranhão");

    private String sigla;
    private String nome;

    EstadoDaFederacaoEnum(String sigla, String nome) {
        this.sigla = sigla;
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public static EstadoDaFederacaoEnum toEstadoDaFederacaoEnum(String sigla) {
        EstadoDaFederacaoEnum resposta = EstadoDaFederacaoEnum.PARA;

        for (EstadoDaFederacaoEnum estado : EstadoDaFederacaoEnum.values()) {
            if (estado.sigla.equals(sigla)) {
                resposta = estado;
                break;
            }
        }

        return resposta;
    }

    @Override
    public String toString() {
        return nome;
    }
}
