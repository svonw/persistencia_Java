package adaptadores;

import java.util.List;

import javax.swing.DefaultComboBoxModel;

import model.Cliente;
import service.CajeroServiceFactory;

public class ComboBoxModelTitularesImpl extends DefaultComboBoxModel<Cliente> {
	/*List<Cliente> clientes;
	public ComboBoxModelTitularesImpl(int numeroCuenta) {
		var service=CajeroServiceFactory.getCajeroService();
		clientes=service.obtenerTitulares(numeroCuenta);
	}
	@Override
	public int getSize() {
		return clientes.size();
	}
	@Override
	public Cliente getElementAt(int index) {
		return clientes.get(index);
	}*/
	
	public ComboBoxModelTitularesImpl(int numeroCuenta) {
		super(CajeroServiceFactory.getCajeroService().obtenerTitulares(numeroCuenta).toArray(new Cliente[0]));
	}
	
}
