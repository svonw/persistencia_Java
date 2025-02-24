package graficos;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Cuenta;
import service.CajeroServiceFactory;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaIngresar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	Cuenta cuenta;
	private JTextField jtfCantidad;
	public VentanaIngresar(Cuenta cuenta) {
		this.cuenta=cuenta;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cantidad:");
		lblNewLabel.setBounds(78, 63, 82, 14);
		contentPane.add(lblNewLabel);
		
		jtfCantidad = new JTextField();
		jtfCantidad.setBounds(163, 60, 86, 20);
		contentPane.add(jtfCantidad);
		jtfCantidad.setColumns(10);
		
		JButton jbtIngresar = new JButton("Ingresar");
		jbtIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var service=CajeroServiceFactory.getCajeroService();
				service.ingreso(cuenta.getNumeroCuenta(), Double.parseDouble(jtfCantidad.getText()));
				VentanaIngresar.this.dispose();
			}
		});
		jbtIngresar.setBounds(123, 127, 89, 23);
		contentPane.add(jbtIngresar);
		this.setVisible(true);
	}

}
