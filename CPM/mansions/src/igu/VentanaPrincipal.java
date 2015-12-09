package igu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logica.Inmobiliaria;
import logica.Mansion;

public class VentanaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txEntrada;
	private JTextArea taDescripcion;

	private Inmobiliaria inmob = new Inmobiliaria();
	private JTabbedPane tpMansiones;
	private JPanel pnVentas;
	private JPanel pnAlquileres;
	private JScrollPane spVentas;
	private JTable tVentas;
	private DefaultTableModel modeloTablas;
	private DefaultListModel modeloLista;
	private JList list;
	private JSpinner spinner;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
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
	public VentanaPrincipal() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 538);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblImage = new JLabel("");
		lblImage.setIcon(new ImageIcon(VentanaPrincipal.class
				.getResource("/img/llave.JPG")));
		lblImage.setBounds(535, 11, 95, 89);
		contentPane.add(lblImage);

		JLabel lblAgencia = new JLabel("AGENCIA INMOBILIARIA EII");
		lblAgencia.setForeground(new Color(210, 105, 30));
		lblAgencia.setFont(new Font("Felix Titling", Font.BOLD, 34));
		lblAgencia.setBounds(10, 11, 515, 66);
		contentPane.add(lblAgencia);

		JPanel pnLista = new JPanel();
		pnLista.setBorder(new TitledBorder(null, "Mansiones a visitar",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnLista.setBounds(418, 111, 212, 272);
		contentPane.add(pnLista);
		pnLista.setLayout(null);

		JScrollPane spLista = new JScrollPane();
		spLista.setBounds(199, 221, -187, -202);
		pnLista.add(spLista);

		modeloLista = new DefaultListModel();

		JButton btnAdd = new JButton("A\u00F1adir");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int fila = tVentas.getSelectedRow();
				if (fila != -1) {
					modeloLista.addElement(tVentas.getValueAt(fila, 0));
				}
			}
		});
		pnLista.add(getList());
		btnAdd.setBounds(10, 238, 89, 23);
		pnLista.add(btnAdd);

		JButton btnDel = new JButton("Eliminar");
		btnDel.setBounds(113, 238, 89, 23);
		pnLista.add(btnDel);

		JPanel pnEntrada = new JPanel();
		pnEntrada.setBorder(new TitledBorder(null, "Entrada",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnEntrada.setBounds(418, 394, 212, 93);
		contentPane.add(pnEntrada);
		pnEntrada.setLayout(null);

		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(15, 0, 20, 1));
		spinner.setBounds(10, 29, 42, 20);
		pnEntrada.add(spinner);

		JButton btnCalcular = new JButton("Calcular");
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int fila = tVentas.getSelectedRow();

				if (fila != -1) {
					Integer porcentaje = (Integer) spinner.getValue();
					Float price = (Float) tVentas.getValueAt(fila, 3);
					Float entrada = inmob.calcularEntrada(porcentaje, price);
					txEntrada.setText(entrada + "€");
				}
			}
		});
		btnCalcular.setBounds(113, 28, 89, 23);
		pnEntrada.add(btnCalcular);

		JLabel lblPercent = new JLabel("%");
		lblPercent.setBounds(57, 32, 20, 14);
		pnEntrada.add(lblPercent);

		txEntrada = new JTextField();
		txEntrada.setEditable(false);
		txEntrada.setBounds(10, 62, 192, 20);
		pnEntrada.add(txEntrada);
		txEntrada.setColumns(10);

		taDescripcion = new JTextArea();
		taDescripcion.setEditable(false);
		taDescripcion.setBorder(new BevelBorder(BevelBorder.LOWERED, null,
				null, null, null));
		taDescripcion.setBounds(10, 373, 398, 98);
		contentPane.add(taDescripcion);

		JScrollPane spDescripcion = new JScrollPane();
		spDescripcion.setBounds(10, 373, 398, 93);
		contentPane.add(spDescripcion);
		contentPane.add(getTpMansiones());

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inicializar();
			}
		});
		btnCancelar.setBounds(254, 477, 89, 23);
		contentPane.add(btnCancelar);
		
		JButton btnSiguiente = new JButton("Siguiente");
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				grabarVisitas();
			}
		});
		btnSiguiente.setBounds(133, 482, 89, 23);
		contentPane.add(btnSiguiente);

		addRows();
	}

	private JTabbedPane getTpMansiones() {
		if (tpMansiones == null) {
			tpMansiones = new JTabbedPane(JTabbedPane.TOP);
			tpMansiones.setBounds(10, 108, 398, 252);
			tpMansiones.addTab("Venta de Mansiones", null, getPnVentas(), null);
			tpMansiones.addTab("Alquiler de Mansiones", null,
					getPnAlquileres(), null);
		}
		return tpMansiones;
	}

	private JPanel getPnVentas() {
		if (pnVentas == null) {
			pnVentas = new JPanel();
			pnVentas.setLayout(new BorderLayout(0, 0));
			pnVentas.add(getSpVentas(), BorderLayout.CENTER);
		}
		return pnVentas;
	}

	private JPanel getPnAlquileres() {
		if (pnAlquileres == null) {
			pnAlquileres = new JPanel();
		}
		return pnAlquileres;
	}

	private JScrollPane getSpVentas() {
		if (spVentas == null) {
			spVentas = new JScrollPane();
			spVentas.setViewportView(getTVentas());
		}
		return spVentas;
	}

	private JTable getTVentas() {
		if (tVentas == null) {
			String[] nombreColumnas = { "Código", "Zona", "Localidad", "Precio" };
			modeloTablas = new DefaultTableModel(nombreColumnas, 0);
			tVentas = new JTable(modeloTablas);
			tVentas.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					JTable table = (JTable) arg0.getSource();
					taDescripcion.setText(inmob.getDescripcionMansion(table
							.getSelectedRow()));
				}
			});
			tVentas.getTableHeader().setReorderingAllowed(false);

		}
		return tVentas;
	}

	private void addRows() {
		Object[] nuevaFila = new Object[4];
		for (Mansion m : inmob.getRelacionMansiones()) {
			nuevaFila[0] = m.getCodigo();
			nuevaFila[1] = m.getZona();
			nuevaFila[2] = m.getLocalidad();
			nuevaFila[3] = m.getPrecio();

			modeloTablas.addRow(nuevaFila);
		}
	}

	private JList getList() {
		if (list == null) {
			list = new JList(modeloLista);
			list.setBounds(10, 21, 192, 206);
		}
		return list;
	}

	private void inicializar() {
		tpMansiones.setSelectedIndex(0);
		tVentas.clearSelection();
		modeloLista.clear();
		taDescripcion.setText("");
		txEntrada.setText("");
		spinner.setValue(15);
		spVentas.getViewport().setViewPosition(new Point(0, 0));
	}

	private void grabarVisitas() {
		String line = "";
		for (Object s : modeloLista.toArray()) {
			line = line + " " + s.toString();
		}

		if (inmob.grabarFichero(line) == 0) {
			JOptionPane
					.showMessageDialog(this, "Su petición ha sido procesada");
		}
	}
}
