package dao;

import static dao.DatosBd.getEntityManager;

import java.util.List;

import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import model.Centro;
import model.CentrosPK;

public class CentrosDao {
	public void save(Centro centro) {
		EntityTransaction tx = getEntityManager().getTransaction();
		tx.begin();
		getEntityManager().persist(centro);
		tx.commit();
	}

	public Centro findById(CentrosPK pk) {
		return getEntityManager().find(Centro.class, pk);
	}

	public List<Centro> findByCodigoPostal(int codigoPostal) {
		String jpql = "select c from Centro c where c.id.codigoPostal=?1";
		TypedQuery<Centro> query = getEntityManager().createQuery(jpql, Centro.class);
		query.setParameter(1, codigoPostal);
		return query.getResultList();
	}
}
