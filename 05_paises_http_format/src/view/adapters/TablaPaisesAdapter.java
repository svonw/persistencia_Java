package view.adapters;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Pais;
import service.PaisesServiceFactory;

public class TablaPaisesAdapter extends AbstractTableModel {
	private List<Pais> paises;
	public TablaPaisesAdapter(String continente) {
		var paisesService=PaisesServiceFactory.getPaisesService();
		paises=paisesService.paisesPorContinente(continente);
	}
	@Override
	public int getRowCount() {
		return paises.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Pais pais=paises.get(rowIndex);
		return switch(columnIndex) {
			case 0->pais.getName();
			case 1->pais.getContinente();
			case 2->pais.getCapital();
			case 3->pais.getHabitantes();
			default->"";
		};

	}

	@Override
	public String getColumnName(int column) {
		return switch(column) {
			case 0->"Nombre pais";
			case 1->"Continente";
			case 2->"Capital";
			case 3->"Habitantes";
			default->"";
		};
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return switch(columnIndex) {
			case 0->String.class;
			case 1->String.class;
			case 2->String.class;
			case 3->Long.class;
			default->String.class;
		};
	}
	
	

}
