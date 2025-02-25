package dao;

import static locator.LocatorEntity.getEntityManager;

import java.util.List;

import jakarta.persistence.TypedQuery;
import model.Cliente;

class ClientesDaoImpl implements ClientesDao {

	@Override
	public List<Cliente> findByCuenta(int idCuenta) {
		String jpql = "select c from Cliente c join c.cuentas a where a.numeroCuenta=?1";
		TypedQuery<Cliente> query = getEntityManager().createQuery(jpql, Cliente.class);
		query.setParameter(1, idCuenta);
		return query.getResultList();
	}

}
