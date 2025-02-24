package service;

import java.time.LocalDateTime;
import java.util.List;

import dao.CajeroDaoFactory;
import dao.ClientesDao;
import dao.CuentasDao;
import dao.MovimientosDao;
import model.Cliente;
import model.Cuenta;
import model.Movimiento;

class CajeroServiceImpl implements CajeroService {
	ClientesDao clientesDao;
	CuentasDao cuentasDao;
	MovimientosDao movimientosDao;
	
	

	public CajeroServiceImpl() {
		clientesDao=CajeroDaoFactory.getClientesDao();
		cuentasDao=CajeroDaoFactory.getCuentasDao();
		movimientosDao=CajeroDaoFactory.getMovimientosDao();
	}

	@Override
	public Cuenta obtenerCuenta(int idCuenta) {
		return cuentasDao.findById(idCuenta);
	}

	@Override
	public Cuenta extraccion(int idCuenta, double cantidad) {
		Cuenta cuenta = cuentasDao.findById(idCuenta);
		if (cuenta!=null&&cuenta.getSaldo() > cantidad) {
			cuentasDao.updateSaldo(idCuenta, (cuenta.getSaldo()-cantidad));
			movimientosDao.save(new Movimiento(0,idCuenta, LocalDateTime.now(), cantidad, "extracción"));
			cuenta.setSaldo(cuenta.getSaldo()-cantidad);
			return cuenta;
		}
		return null;
	}

	@Override
	public Cuenta ingreso(int idCuenta, double cantidad) {
		Cuenta cuenta = cuentasDao.findById(idCuenta);
		if (cuenta!=null) {
			cuentasDao.updateSaldo(idCuenta, cuenta.getSaldo()+cantidad);
			movimientosDao.save(new Movimiento(0,idCuenta, LocalDateTime.now(), cantidad, "ingreso"));
			cuenta.setSaldo(cuenta.getSaldo()+cantidad);
			return cuenta;
		}
		return null;
	}

	@Override
	public boolean transferencia(int idCuentaOrigen, int idCuentaDestino, double cantidad) {
		if(cuentasDao.findById(idCuentaOrigen)==null || 
				cuentasDao.findById(idCuentaDestino)==null ||
				cantidad>cuentasDao.findById(idCuentaOrigen).getSaldo()) {
			return false;
		}
		extraccion(idCuentaOrigen, cantidad);
		ingreso(idCuentaDestino, cantidad);
		return true;
		
	} 
	/*public boolean transferencia(int idCuentaOrigen, int idCuentaDestino, double cantidad) {
		Cuenta cuentaOri=cuentasDao.findById(idCuentaOrigen);
		Cuenta cuentaDes=cuentasDao.findById(idCuentaDestino);
		if(cuentaOri !=null&&cuentaOri.getSaldo()>=cantidad
				&&cuentaDes !=null) {
			cuentasDao.updateSaldo(idCuentaDestino, cuentaDes.getSaldo() + cantidad);
			cuentasDao.updateSaldo(idCuentaOrigen, cuentaDes.getSaldo() - cantidad);
			movimientosDao.save(new Movimiento(0,idCuentaDestino, LocalDateTime.now(), cantidad, "ingreso"));
			movimientosDao.save(new Movimiento(0,idCuentaOrigen, LocalDateTime.now(), cantidad, "extracción"));
			cuentaOri.setSaldo(cuentaOri.getSaldo() - cantidad);
			cuentaDes.setSaldo(cuentaDes.getSaldo() + cantidad);
			return true;
		}
		return false;
	} */

	@Override
	public List<Cliente> obtenerTitulares(int idCuenta) {
		return clientesDao.findByCuenta(idCuenta);
	}

	@Override
	public List<Movimiento> obtenerMovimientos(int idCuenta) {
		return movimientosDao.findByCuenta(idCuenta);
	}

	@Override
	public double obtenerSaldo(int idCuenta) {
		Cuenta cuenta=cuentasDao.findById(idCuenta);
		return cuenta!=null?cuenta.getSaldo():0;
	}

}
