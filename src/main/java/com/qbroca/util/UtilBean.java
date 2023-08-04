package com.qbroca.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.net.URI;
import java.util.Map;

public class UtilBean {

    /**
     * Recupera, do escopo da requisição, o valor do parâmetro com nome
     * informado.
     *
     * @param parametro nome do parâmetro que guarda o valor que se deseja
     * recuperar
     * @return
     */
    public static String obterParametro(String parametro) {
        if (parametro == null) {
            return null;
        } else {
            FacesContext currentInstance = FacesContext.getCurrentInstance();
            return currentInstance.getExternalContext().getRequestParameterMap().get(parametro);
        }
    }

    public static Object obterAtributo(String chave) {
        if (chave == null) {
            return null;
        } else {
            FacesContext currentInstance = FacesContext.getCurrentInstance();
            return currentInstance.getExternalContext().getRequestMap().get(chave);
        }
    }

    public static void adicionarValor(String parametro, String valor) {
        if (parametro != null && valor != null) {
            FacesContext currentInstance = FacesContext.getCurrentInstance();
            currentInstance.getExternalContext().getRequestParameterMap().put(parametro, valor);
        }
    }

    public static void criarMensagemDeInformacao(String resumo, String detalhe) {
        criarMensagem(FacesMessage.SEVERITY_INFO, resumo, detalhe);
    }

    public static void criarMensagemDeInformacao(String detalhe) {
        criarMensagem(FacesMessage.SEVERITY_INFO, "Sucesso", detalhe);
    }

    public static void criarMensagemDeAviso(String resumo, String detalhe) {
        criarMensagem(FacesMessage.SEVERITY_WARN, resumo, detalhe);
    }

    public static void criarMensagemDeAviso(String detalhe) {
        criarMensagem(FacesMessage.SEVERITY_WARN, "Alerta", detalhe);
    }

    public static void criarMensagemDeErro(String resumo, String detalhe) {
        criarMensagem(FacesMessage.SEVERITY_ERROR, resumo, detalhe);
    }

    public static void criarMensagemDeErro(String detalhe) {
        criarMensagem(FacesMessage.SEVERITY_ERROR, "Erro", detalhe);
    }

    private static void criarMensagem(FacesMessage.Severity tipo, String resumo, String detalhe) {
        FacesContext currentInstance = FacesContext.getCurrentInstance();
        FacesMessage fm = new FacesMessage(resumo, detalhe);
        fm.setSeverity(tipo);
        currentInstance.addMessage(null, fm);
    }

    public static void encerrarSessao() {
        FacesContext currentInstance = FacesContext.getCurrentInstance();
        if (currentInstance != null) {
            ExternalContext externalContext = currentInstance.getExternalContext();
            if (externalContext != null) {
                Object session = externalContext.getSession(false);
                if (session != null
                        && session instanceof HttpSession) {
                    ((HttpSession) session).invalidate();
                }
            }
        }
    }

    public static Object daSessao(String chave) {
        if (chave == null) {
            return null;
        } else {
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            Map<String, Object> sessao = externalContext.getSessionMap();
            return sessao.get(chave);
        }
    }

    public static void naSessao(String chave, Object valor) {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessao = externalContext.getSessionMap();
        //Preciosismo: remover antes de inserir
        sessao.remove(chave);
        sessao.put(chave, valor);
    }

    public static String obterLoginDaSessao() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext e = facesContext.getExternalContext();
        if (e != null) {
            return e.getRemoteUser();
        } else {
            return null;
        }
    }

    public static URI createURI(String recurso) {
        URI resposta = null;
        return resposta;
    }

    public static String obterURLAplicacao() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        return externalContext.getApplicationContextPath();
    }
}
