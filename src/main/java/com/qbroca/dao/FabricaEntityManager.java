package com.qbroca.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author fabio
 */
public class FabricaEntityManager {

    private static final String PERSISTENCE_UNIT = "QBrocaPU";
    private static EntityManagerFactory fabrica = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);

    ;

    private FabricaEntityManager() {
    }

    /**
     * Cria um objeto EntityManager a partir de uma unidade de persistência
     * padrão.
     *
     * @return Obj EntityManager
     */
    public static EntityManager criarEntityManager() {
        return fabrica.createEntityManager();
    }

    /**
     * Cria um objeto EntityManager a partir da unidade de persistência
     * informada em unidadePersistencia.
     *
     * @param unidadePersistencia
     * @return Obj EntityManager
     */
    public static EntityManager criarEntityManager(String unidadePersistencia) {
        if (fabrica != null && fabrica.isOpen()) {
            fabrica.close();
        }
        fabrica = Persistence.createEntityManagerFactory(unidadePersistencia);
        return criarEntityManager();
    }

}
