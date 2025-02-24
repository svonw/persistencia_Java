package dao;

public class CajeroDaoFactory {
	public static ClientesDao getClientesDao() {
		return new ClientesDaoImpl();
	}
	public static CuentasDao getCuentasDao() {
		return new CuentasDaoImpl();
	}
	public static MovimientosDao getMovimientosDao() {
		return new MovimientosDaoImpl();
	}
}
