package adaptadores;

import javax.swing.DefaultComboBoxModel;

import model.Cliente;
import service.CajeroServiceFactory;

public class ComboBoxModelTitularesImpl extends DefaultComboBoxModel<Cliente> {

	public ComboBoxModelTitularesImpl(int numeroCuenta) {
		super(CajeroServiceFactory.getCajeroService().obtenerTitulares(numeroCuenta).toArray(new Cliente[0]));
	}

}
