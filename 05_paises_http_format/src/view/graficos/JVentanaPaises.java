package view.graficos;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import model.Pais;
import service.PaisesServiceFactory;
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
		jtablePaises.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila = jtablePaises.getSelectedRow();
				// nombre del pais seleccionado
				String nombrePais = jtablePaises.getValueAt(fila, 0).toString();
				Optional<Pais> pais = PaisesServiceFactory.getPaisesService().paisPorNombre(nombrePais);
				pais.ifPresentOrElse(p -> {
					ZonedDateTime zdt = ZonedDateTime.of(LocalDate.now(), LocalTime.now(),
							ZoneId.of(p.getTimezones()[0]));
					Locale locale = Locale.of(p.getIdiomas()[0].getCodigo(), p.getCodigoPais());
					DateTimeFormatter format = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
					format = format.withLocale(locale);
					JOptionPane.showMessageDialog(JVentanaPaises.this, zdt.format(format));
				}, () -> JOptionPane.showMessageDialog(JVentanaPaises.this, "No existe ese pais")

				);
			}
		});
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
