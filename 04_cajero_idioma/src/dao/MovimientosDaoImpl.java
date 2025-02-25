package dao;

import static locator.LocatorEntity.getEntityManager;

import java.util.List;

import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import model.Movimiento;

class MovimientosDaoImpl implements MovimientosDao {

	@Override
	public List<Movimiento> findByCuenta(int idCuenta) {
		String jpql = "select m from Movimiento m where m.cuenta.numeroCuenta=?1";
		TypedQuery<Movimiento> query = getEntityManager().createQuery(jpql, Movimiento.class);
		query.setParameter(1, idCuenta);
		return query.getResultList();
	}

	@Override
	public void save(Movimiento movimiento) {
		EntityTransaction tx = getEntityManager().getTransaction();
		tx.begin();
		getEntityManager().persist(movimiento);
		tx.commit();
	}

	@Override
	public List<Movimiento> findByCliente(int dni) {
		String jpql = "select m from Movimiento m join m.cuenta c join c.clientes cl where cl.dni=?1";
		TypedQuery<Movimiento> query = getEntityManager().createQuery(jpql, Movimiento.class);
		query.setParameter(1, dni);
		return query.getResultList();
	}

}
