package dao;

import java.util.List;

import model.Cliente;

public interface ClientesDao {
	List<Cliente> findByCuenta(int idCuenta);
}
