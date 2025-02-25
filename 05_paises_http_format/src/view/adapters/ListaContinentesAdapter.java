package view.adapters;

import java.util.List;

import javax.swing.DefaultComboBoxModel;

import service.PaisesServiceFactory;

public class ListaContinentesAdapter extends DefaultComboBoxModel<String> {
	List<String> continentes;
	public ListaContinentesAdapter()  {
		var paisesService=PaisesServiceFactory.getPaisesService();
		continentes=paisesService.continentes();
	}
	@Override
	public int getSize() {
		return continentes.size();
	}
	@Override
	public String getElementAt(int index) {
		return continentes.get(index);
	}
	
}
