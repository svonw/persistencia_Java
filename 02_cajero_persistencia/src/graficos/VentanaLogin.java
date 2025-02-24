package graficos;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.Cuenta;
import service.CajeroServiceFactory;

public class VentanaLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField jtfCuenta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaLogin frame = new VentanaLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nº Cuenta:");
		lblNewLabel.setBounds(57, 51, 72, 23);
		contentPane.add(lblNewLabel);
		
		jtfCuenta = new JTextField();
		jtfCuenta.setBounds(151, 52, 127, 20);
		contentPane.add(jtfCuenta);
		jtfCuenta.setColumns(10);
		
		JButton jbtEntrar = new JButton("Entrar");
		jbtEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var service=CajeroServiceFactory.getCajeroService();
				Cuenta cuenta=service.obtenerCuenta(Integer.parseInt(jtfCuenta.getText()));
				
				if(cuenta!=null) {
					new VentanaMenu(cuenta);
					VentanaLogin.this.dispose();
				}else {
					String mensaje="La cuenta no existe";
					JOptionPane.showMessageDialog(VentanaLogin.this, mensaje);
				}
				
			}
		});
		jbtEntrar.setBounds(150, 128, 89, 23);
		contentPane.add(jbtEntrar);
	}
}
