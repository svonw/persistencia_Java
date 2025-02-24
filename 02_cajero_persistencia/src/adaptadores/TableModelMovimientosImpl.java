package adaptadores;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Movimiento;
import service.CajeroService;
import service.CajeroServiceFactory;

public class TableModelMovimientosImpl extends AbstractTableModel {
	List<Movimiento> movimientos;
	public TableModelMovimientosImpl(int numeroCuenta) {
		CajeroService service=CajeroServiceFactory.getCajeroService();
		movimientos=service.obtenerMovimientos(numeroCuenta);
	}
	
	@Override
	public int getRowCount() {
		return movimientos.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public Object getValueAt(int row, int column) {
		return switch(column) {
		case 0-> movimientos.get(row).getOperacion();
		case 1-> movimientos.get(row).getCantidad();
		case 2-> movimientos.get(row).getFecha();
		
		default->"";
		}; 
	}

	@Override
	public String getColumnName(int column) {
		return switch(column) {
		case 0->"Operación";
		case 1->"Cantidad";
		case 2->"Fecha";

		default->"";
		}; 
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return switch(columnIndex)  {
		case 0-> Integer.class;
		case 1-> Double.class;
		case 2-> String.class;

		default->String.class;
		}; 
	}

	
}
