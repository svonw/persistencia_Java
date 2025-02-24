package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import locator.LocatorEntity;
import model.Cliente;

class ClientesDaoImpl implements ClientesDao {

	@Override
	public List<Cliente> findByCuenta(int idCuenta) {
		List<Cliente> lista = new ArrayList<Cliente>();
		try (Connection con = LocatorEntity.getEntityManager()) {
			String sql = "select clientes.* from clientes, titulares  where titulares.idCuenta = ? and titulares.idCliente = clientes.dni";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, idCuenta);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				lista.add(new Cliente(rs.getInt("dni"), rs.getString("nombre"), rs.getString("direccion"),
						rs.getInt("telefono")));

			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return lista;
	}

}
