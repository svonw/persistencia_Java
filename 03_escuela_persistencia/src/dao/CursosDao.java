package dao;

import static dao.DatosBd.getEntityManager;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import model.Curso;

public class CursosDao {
	public double averagePrecio() {
		String jpql = "select avg(c.precio) from Curso c";
		TypedQuery<Double> query = getEntityManager().createQuery(jpql, Double.class);
		return query.getSingleResult();
	}

	public void save(Curso curso) {
		EntityTransaction tx = getEntityManager().getTransaction();
		tx.begin();
		getEntityManager().persist(curso);
		tx.commit();
	}

	public Curso findByIdCurso(int codigo) {
		return getEntityManager().find(Curso.class, codigo);
	}

	public Curso findByDenominacionAndFechaInicio(String denominacion, LocalDate fechaInicio) {
		String jpql = "select c from Curso c where c.denominacion=?1 and c.fechaInicio=?2";
		TypedQuery<Curso> query = getEntityManager().createQuery(jpql, Curso.class);
		query.setParameter(1, denominacion);
		query.setParameter(2, fechaInicio);
		List<Curso> cursos = query.getResultList();
		return cursos.size() == 0 ? null : cursos.get(0);
	}

	public void delete(int idCurso) {
		String jpql = "delete from Curso c where curso.idCurso=?1";
		Query query = getEntityManager().createQuery(jpql);
		query.setParameter(1, idCurso);
		query.executeUpdate();
	}

	// Curso en el que está matriculado un alumno cuyo dni se recibe como parámetro

	public Curso cursoAlumno(String dni) {
		String jpql = "select c from Curso c join c.alumnos a where a.dni=?1";
		TypedQuery<Curso> query = getEntityManager().createQuery(jpql, Curso.class);
		query.setParameter(1, dni);
		List<Curso> cursos = query.getResultList();
		return cursos.size() == 0 ? null : cursos.get(0);
	}
}
