package dao;

import java.util.List;

import model.Movimiento;

public interface MovimientosDao {
	List<Movimiento> findByCuenta(int idCuenta);

	void save(Movimiento movimiento);

	// movimientos en todas sus cuentas de un determinado cliente
	List<Movimiento> findByCliente(int dni);
}
