package dao;

import java.util.List;

import model.Movimiento;

public interface MovimientosDao {
	List<Movimiento> findByCuenta(int idCuenta);
	void save(Movimiento movimiento);
}
