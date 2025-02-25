package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DatosBd {

	private static EntityManager em;

	public static EntityManager getEntityManager() {
		if (em == null) {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("escuelaPU");
			em = factory.createEntityManager();
		}
		return em;

	}
}
