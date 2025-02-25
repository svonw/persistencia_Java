package dao;

import model.Cuenta;

public interface CuentasDao {
	Cuenta findById(int idCuenta);
	void updateSaldo(int idCuenta, double nuevoSaldo);
}
