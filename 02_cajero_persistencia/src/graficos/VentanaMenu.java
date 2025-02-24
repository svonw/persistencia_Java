package graficos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import adaptadores.ComboBoxModelTitularesImpl;
import model.Cliente;
import model.Cuenta;
import javax.swing.JLabel;

public class VentanaMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	Cuenta cuenta;

	/**
	 * Create the frame.
	 */
	public VentanaMenu(Cuenta cuenta) {
		this.cuenta=cuenta;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Operaciones");
		menuBar.add(mnNewMenu);
		
		JMenuBar menuBar_1 = new JMenuBar();
		mnNewMenu.add(menuBar_1);
		
		JMenuItem menuIngresar = new JMenuItem("Ingresar");
		menuIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new VentanaIngresar(cuenta);
			}
		});
		mnNewMenu.add(menuIngresar);
		
		JMenuItem menuExtraer = new JMenuItem("Extraer");
		mnNewMenu.add(menuExtraer);
		
		JMenuItem menuTx = new JMenuItem("Transferencia");
		mnNewMenu.add(menuTx);
		
		JMenuItem menuMovs = new JMenuItem("Saldo y movimientos");
		menuMovs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new VentanaMovimientos(cuenta);
			}
		});
		mnNewMenu.add(menuMovs);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox<Cliente> comboTitulares = new JComboBox();
		comboTitulares.setBounds(257, 72, 136, 22);
		contentPane.add(comboTitulares);
		comboTitulares.setModel(new ComboBoxModelTitularesImpl(cuenta.getNumeroCuenta()));
		
		JLabel lblNewLabel = new JLabel("Titulares:");
		lblNewLabel.setBounds(259, 49, 85, 22);
		contentPane.add(lblNewLabel);
		
		this.setVisible(true);
	}
}
