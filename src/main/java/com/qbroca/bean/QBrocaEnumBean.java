package com.qbroca.bean;

import com.qbroca.tipo.EstadoDaFederacaoEnum;
import com.qbroca.tipo.SexoEnum;
import com.qbroca.tipo.SimNaoEnum;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class QBrocaEnumBean {
    public QBrocaEnumBean() {

    }

    public SimNaoEnum[] getStatusAtivo() {
        return SimNaoEnum.values();
    }


    public SexoEnum[] getSexos() {
        return SexoEnum.values();
    }

    public EstadoDaFederacaoEnum[] getEstadosDaFederacao() {
        return EstadoDaFederacaoEnum.values();
    }

    public SimNaoEnum toSimNaoEnum(char valor) {
        return SimNaoEnum.toEnum(valor);
    }

    public SexoEnum toSexoEnum(char valor) {
        return SexoEnum.toSexoEnum(valor);
    }

    public EstadoDaFederacaoEnum toEstadoDaFederacaoEnum(String sigla) {
        return EstadoDaFederacaoEnum.toEstadoDaFederacaoEnum(sigla);
    }
}
