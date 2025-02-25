package view.graficos;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import view.adapters.ListaContinentesAdapter;
import view.adapters.TablaPaisesAdapter;

public class JVentanaPaises extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable jtablePaises;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JVentanaPaises frame = new JVentanaPaises();
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
	public JVentanaPaises() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seleccione continente:");
		lblNewLabel.setBounds(59, 32, 126, 14);
		contentPane.add(lblNewLabel);
		
		JComboBox<String> jcomboContinentes = new JComboBox();
		jcomboContinentes.setBounds(195, 28, 107, 22);
		contentPane.add(jcomboContinentes);
		
		JButton jbtnPaises = new JButton("Ver paises");
		jbtnPaises.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtablePaises.setModel(new TablaPaisesAdapter(jcomboContinentes.getSelectedItem().toString()));
			}
		});
		jbtnPaises.setBounds(122, 84, 131, 23);
		contentPane.add(jbtnPaises);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(59, 142, 309, 70);
		contentPane.add(scrollPane);
		
		jtablePaises = new JTable();
		scrollPane.setViewportView(jtablePaises);
		jcomboContinentes.setModel(new ListaContinentesAdapter());
		
		
		JButton btnNewButton = new JButton("Salir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JVentanaPaises.this.dispose();
			}
		});
		btnNewButton.setBounds(144, 223, 89, 23);
		contentPane.add(btnNewButton);
	}
}
