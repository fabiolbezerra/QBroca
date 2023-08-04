package com.qbroca.bean.converter;

import com.qbroca.entity.Produto;
import com.qbroca.rn.ProdutoRN;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "produtoConverter")
public class ProdutoConverter implements Converter {

    private final ProdutoRN RN = new ProdutoRN();

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String string) {
        if (string == null || string.trim().isEmpty() || "null".equalsIgnoreCase(string)) {
            return null;
        }
        try {
            return RN.obter(Integer.valueOf(string));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object obj) {
        if (obj == null || !(obj instanceof Produto)) {
            return null;
        }
        Produto produto = (Produto) obj;
        if (produto.getId() == null) {
            return null;
        }
        return String.valueOf(produto.getId());
    }
}
