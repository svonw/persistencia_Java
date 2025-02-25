package graficos;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import adaptadores.TableModelMovimientosImpl;
import model.Cuenta;
import service.CajeroServiceFactory;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaMovimientos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableMovimientos;
	Cuenta cuenta;
	

	/**
	 * Create the frame.
	 */
	public VentanaMovimientos(Cuenta cuenta) {
		this.cuenta=cuenta;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Saldo:");
		lblNewLabel.setBounds(100, 25, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblSaldo = new JLabel("");
		lblSaldo.setBounds(156, 25, 46, 14);
		contentPane.add(lblSaldo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 73, 384, 131);
		contentPane.add(scrollPane);
		
		tableMovimientos = new JTable();
		scrollPane.setViewportView(tableMovimientos);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMovimientos.this.dispose();
			}
		});
		btnSalir.setBounds(156, 227, 89, 23);
		contentPane.add(btnSalir);
		
		var service=CajeroServiceFactory.getCajeroService();
		lblSaldo.setText(""+service.obtenerSaldo(cuenta.getNumeroCuenta()));
		tableMovimientos.setModel(new TableModelMovimientosImpl(cuenta.getNumeroCuenta()));
		
		
		this.setVisible(true);
	}
}
