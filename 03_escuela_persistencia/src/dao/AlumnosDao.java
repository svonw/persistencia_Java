package dao;

import static dao.DatosBd.getEntityManager;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import model.Alumno;

public class AlumnosDao {

	public void save(Alumno alumno) {
		EntityTransaction tx = getEntityManager().getTransaction();
		tx.begin();
		getEntityManager().persist(alumno);
		tx.commit();
	}

	public List<Alumno> findByCurso(String denominacion) {
		String jpql = "select a from Alumno a where a.curso.denominacion=?1";
		TypedQuery<Alumno> query = getEntityManager().createQuery(jpql, Alumno.class);
		query.setParameter(1, denominacion);
		return query.getResultList();
	}

	public Alumno findByDni(String dni) {
		return getEntityManager().find(Alumno.class, dni);
	}

	// listado de alumnos matriculados en cursos cuya fecha de inicio está
	// comprendida entre dos fechas recibidas como parámetro
	public List<Alumno> findByFechasCurso(LocalDate f1, LocalDate f2) {
		String jpql = "select a from Alumno a where a.curso.fechaInicio>=?1 and a.curso.fechaInicio<=?2";
		TypedQuery<Alumno> query = getEntityManager().createQuery(jpql, Alumno.class);
		query.setParameter(1, f1);
		query.setParameter(1, f2);
		return query.getResultList();
	}

	// método que elimine los alumnos del curso cuyo código se recibe como parámetro
	public void deleteByCurso(int curso) {
		String jpql = "delete from Alumno a where a.curso.idCurso=?1";
		Query query = getEntityManager().createQuery(jpql);
		query.setParameter(1, curso);
		query.executeUpdate();
	}

}
