package service;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import model.Contacto;

public class ContactoService {
	EntityManager em;
	{
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("agendaPU");
		em = factory.createEntityManager();
	}

	public void altaContacto(Contacto contacto) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(contacto);
		tx.commit();
	}

	public void eliminarContacto(int idContacto) {
		Contacto contacto = buscarContacto(idContacto);
		if (contacto != null) {
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.remove(contacto);
			tx.commit();
		}

	}

	public Contacto buscarContacto(int idContacto) {

		return em.find(Contacto.class, idContacto);
	}

	public List<Contacto> recuperarTodos() {
		String jpql = "select c from Contacto c";
		TypedQuery<Contacto> query = em.createQuery(jpql, Contacto.class);
		return query.getResultList();
	}

	public Optional<Contacto> buscarContactoEmail(String email) {

		String jpql = "select c from Contacto c where c.email=?1";
		TypedQuery<Contacto> query = em.createQuery(jpql, Contacto.class);
		query.setParameter(1, email);

		// si hay resultado devuelva el resultado en el Optional,
		// y si no devuelva Optional vacío
		/*
		 * try { return Optional.of(query.getSingleResult()); }catch(NoResultException
		 * ex) { return Optional.empty(); } catch(NonUniqueResultException ex) { return
		 * Optional.of(query.getResultList().get(0)); }
		 */

		List<Contacto> contactos = query.getResultList();
		return contactos.isEmpty() ? Optional.empty() : Optional.of(query.getResultList().get(0));

	}

	public void eliminarContacto(String nombre) {
		String jpql = "delete from Contacto c where c.nombre=?1";
		Query query = em.createQuery(jpql);

		query.setParameter(1, nombre);
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		query.executeUpdate();
		tx.commit();
	}

}
