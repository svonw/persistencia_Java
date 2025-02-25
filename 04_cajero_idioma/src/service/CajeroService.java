package service;

import java.util.List;

import model.Cliente;
import model.Cuenta;
import model.Movimiento;

public interface CajeroService {
	//devuelve la cuenta asociada a dicho identificador, si no existe devuelve false
	Cuenta obtenerCuenta(int idCuenta);
	//extrae dinero de la cuenta indicada. Si la cuenta no existe
	//o no hay saldo suficiente la operación no se hace y se devuelve null
	Cuenta extraccion(int idCuenta, double cantidad);	
	//ingresa dinero en la cuenta indicada. Si la cuenta no existe
	//la operación no se hace y se devuelve null
	Cuenta ingreso(int idCuenta, double cantidad);
	//transfiere dinero entre las cuentas indicadas. Si alguna cuenta no existe
	//o no hay saldo suficiente la operación no se hace y se devuelve false
	boolean transferencia(int idCuentaOrigen, int idCuentaDestino, double cantidad);
	List<Cliente> obtenerTitulares(int idCuenta);
	List<Movimiento> obtenerMovimientos(int idCuenta);
	double obtenerSaldo(int idCuenta);
} 
