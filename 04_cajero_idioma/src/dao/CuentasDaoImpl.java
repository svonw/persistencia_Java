package dao;

import static locator.LocatorEntity.getEntityManager;

import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import model.Cuenta;

class CuentasDaoImpl implements CuentasDao {

	@Override
	public Cuenta findById(int idCuenta) {
		return getEntityManager().find(Cuenta.class, idCuenta);
	}

	@Override
	public void updateSaldo(int idCuenta, double nuevoSaldo) {
		String jpql = "update Cuenta c set c.saldo=?1 where c.numeroCuenta=?2";
		Query query = getEntityManager().createQuery(jpql);
		query.setParameter(1, nuevoSaldo);
		query.setParameter(2, idCuenta);
		EntityTransaction tx = getEntityManager().getTransaction();
		tx.begin();
		query.executeUpdate();
		tx.commit();

	}

}
