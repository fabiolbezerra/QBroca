package com.qbroca.bean.converter;

import com.qbroca.entity.Categoria;
import com.qbroca.rn.CategoriaRN;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "categoriaConverter")
public class CategoriaConverter implements Converter {

    private final CategoriaRN RN = new CategoriaRN();

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
        if (obj == null || !(obj instanceof Categoria)) {
            return null;
        }
        Categoria categoria = (Categoria) obj;
        if (categoria.getId() == null) {
            return null;
        }
        return String.valueOf(categoria.getId());
    }
}
