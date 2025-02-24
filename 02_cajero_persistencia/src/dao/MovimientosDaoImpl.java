package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import locator.LocatorEntity;
import model.Movimiento;

class MovimientosDaoImpl implements MovimientosDao {

	@Override
	public List<Movimiento> findByCuenta(int idCuenta) {
		List<Movimiento> movimiento=new ArrayList<Movimiento>();
		try(Connection con=LocatorEntity.getConnection()) {
			String sql="select * from movimientos where idCuenta=? ";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, idCuenta);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				movimiento.add(new Movimiento(rs.getInt("idMovimiento"),
											rs.getInt("idCuenta"),
											rs.getTimestamp("fecha").toLocalDateTime(),
											rs.getDouble("cantidad"),
											rs.getString("operacion")));
			}
		}catch (SQLException ex) {
			ex.printStackTrace();
		}
		return movimiento;
	} 

	@Override
	public void save(Movimiento movimiento) {
		try (Connection con=LocatorEntity.getConnection()){
			String sql="insert into movimientos(idCuenta,fecha,cantidad,operacion) values(?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, movimiento.getIdCuenta());
			ps.setTimestamp(2, Timestamp.valueOf(movimiento.getFecha()));
			ps.setDouble(3, movimiento.getCantidad());
			ps.setString(4, movimiento.getOperacion());
			ps.execute();
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		
	} 

}
