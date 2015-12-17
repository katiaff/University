package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
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
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import logica.Inmobiliaria;
import logica.Mansion;

import org.jvnet.substance.SubstanceLookAndFeel;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;
	private JLabel lblAgencia;
	private JLabel lblIcon;
	private JPanel pnLista;
	private JPanel pnEntrada;
	private JButton btnAcep;
	private JButton btnCanc;
	private JButton btnCalc;
	private JLabel lblPer;
	private JSpinner spinner;
	private JTextField txtEntrada;
	private JScrollPane spDescripcion;
	private JTextArea taDescripcion;
	private JScrollPane spLista;

	private Inmobiliaria imb = new Inmobiliaria();
	private JButton btnAadir;
	private JButton btnEliminar;
	private JTabbedPane tpMansiones;
	private JPanel pnVentas;
	private JPanel pnAlquileres;
	private JScrollPane spVentas;
	private JTable tbVentas;
	private DefaultTableModel modeloTabla;
	private JList listaVisitas;
	private DefaultListModel modeloLista;
	private JPanel pnFiltro;
	private JCheckBox chckbxNorte;
	private JCheckBox chckbxCentro;
	private JCheckBox chckbxSur;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JDialog.setDefaultLookAndFeelDecorated(true);
					JFrame.setDefaultLookAndFeelDecorated(true);
					SubstanceLookAndFeel
							.setSkin("org.jvnet.substance.skin.MistSilverSkin");
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
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				VentanaPrincipal.class.getResource("/img/llave.JPG")));
		setTitle("Agencia Inmobiliaria");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 677, 510);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblAgencia());
		contentPane.add(getLblIcon());
		contentPane.add(getPnLista());
		contentPane.add(getPnEntrada());
		contentPane.add(getBtnAcep());
		contentPane.add(getBtnCanc());
		contentPane.add(getSpDescripcion());
		contentPane.add(getTpMansiones());
		addRows(true, true, true);
	}

	private JLabel getLblAgencia() {
		if (lblAgencia == null) {
			lblAgencia = new JLabel("AGENCIA INMOBILIARIA EII");
			lblAgencia.setForeground(Color.ORANGE);
			lblAgencia.setFont(new Font("Times New Roman", Font.BOLD, 30));
			lblAgencia.setBounds(24, 23, 443, 80);
		}
		return lblAgencia;
	}

	private JLabel getLblIcon() {
		if (lblIcon == null) {
			lblIcon = new JLabel("");
			lblIcon.setIcon(new ImageIcon(VentanaPrincipal.class
					.getResource("/img/llave.JPG")));
			lblIcon.setBounds(503, 23, 95, 92);
		}
		return lblIcon;
	}

	private JPanel getPnLista() {
		if (pnLista == null) {
			pnLista = new JPanel();
			pnLista.setBorder(new TitledBorder(null, "Mansiones a visitar",
					TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnLista.setBounds(450, 130, 211, 233);
			pnLista.setLayout(null);
			pnLista.add(getScrollPane_1());
			pnLista.add(getBtnAadir());
			pnLista.add(getBtnEliminar());
		}
		return pnLista;
	}

	private JPanel getPnEntrada() {
		if (pnEntrada == null) {
			pnEntrada = new JPanel();
			pnEntrada.setBorder(new TitledBorder(null, "Entrada",
					TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnEntrada.setBounds(450, 368, 211, 80);
			pnEntrada.setLayout(null);
			pnEntrada.add(getBtnCalc());
			pnEntrada.add(getLblPer());
			pnEntrada.add(getSpinner());
			pnEntrada.add(getTxtEntrada());
		}
		return pnEntrada;
	}

	private JButton getBtnAcep() {
		if (btnAcep == null) {
			btnAcep = new JButton("Aceptar");
			btnAcep.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					grabarVisitas();
					inicializar();
				}
			});
			btnAcep.setBounds(457, 448, 89, 23);
		}
		return btnAcep;
	}

	private JButton getBtnCanc() {
		if (btnCanc == null) {
			btnCanc = new JButton("Cancelar");
			btnCanc.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					inicializar();
				}
			});
			btnCanc.setBounds(561, 448, 89, 23);
		}
		return btnCanc;
	}

	private JButton getBtnCalc() {
		if (btnCalc == null) {
			btnCalc = new JButton("Calcular");
			btnCalc.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int fila = tbVentas.getSelectedRow();
					if (fila != -1) {
						int perc = (int) spinner.getValue();
						Float price = (Float) tbVentas.getValueAt(fila, 3);
						Float entrada = imb.calcularEntrada(perc, price);
						txtEntrada.setText(entrada + " $");
					}
				}
			});
			btnCalc.setBounds(106, 18, 89, 23);
		}
		return btnCalc;
	}

	private JLabel getLblPer() {
		if (lblPer == null) {
			lblPer = new JLabel("%");
			lblPer.setBounds(74, 22, 22, 14);
		}
		return lblPer;
	}

	private JSpinner getSpinner() {
		if (spinner == null) {
			spinner = new JSpinner();
			spinner.setModel(new SpinnerNumberModel(15, 0, 20, 1));
			spinner.setBounds(22, 19, 46, 20);
		}
		return spinner;
	}

	private JTextField getTxtEntrada() {
		if (txtEntrada == null) {
			txtEntrada = new JTextField();
			txtEntrada.setEditable(false);
			txtEntrada.setBounds(22, 50, 173, 19);
			txtEntrada.setColumns(10);
		}
		return txtEntrada;
	}

	private JScrollPane getSpDescripcion() {
		if (spDescripcion == null) {
			spDescripcion = new JScrollPane();
			spDescripcion.setBounds(24, 366, 416, 105);
			spDescripcion.setViewportView(getTaDescripcion());
		}
		return spDescripcion;
	}

	private JTextArea getTaDescripcion() {
		if (taDescripcion == null) {
			taDescripcion = new JTextArea();
			taDescripcion.setEditable(false);
		}
		return taDescripcion;
	}

	private JScrollPane getScrollPane_1() {
		if (spLista == null) {
			spLista = new JScrollPane();
			spLista.setBounds(10, 21, 193, 181);
			spLista.setViewportView(getListaVisitas());
		}
		return spLista;
	}

	private JButton getBtnAadir() {
		if (btnAadir == null) {
			btnAadir = new JButton("A\u00F1adir");
			btnAadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int fila = tbVentas.getSelectedRow();
					if (fila != -1) {
						modeloLista.addElement(tbVentas.getValueAt(fila, 0));
					}
				}
			});
			btnAadir.setBounds(10, 203, 89, 23);
		}
		return btnAadir;
	}

	private JButton getBtnEliminar() {
		if (btnEliminar == null) {
			btnEliminar = new JButton("Eliminar");
			btnEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int fila = tbVentas.getSelectedRow();
					if (fila != -1) {
						modeloLista.removeElement(tbVentas.getValueAt(fila, 0));
					}
				}
			});
			btnEliminar.setBounds(114, 203, 89, 23);
		}
		return btnEliminar;
	}

	private JTabbedPane getTpMansiones() {
		if (tpMansiones == null) {
			tpMansiones = new JTabbedPane(JTabbedPane.TOP);
			tpMansiones.setBounds(24, 113, 416, 250);
			tpMansiones.addTab("Venta Mansiones", null, getPnVentas(), null);
			tpMansiones.addTab("Alquiler Mansiones", null, getPnAlquileres(),
					null);
		}
		return tpMansiones;
	}

	private JPanel getPnVentas() {
		if (pnVentas == null) {
			pnVentas = new JPanel();
			pnVentas.setLayout(new BorderLayout(0, 0));
			pnVentas.add(getSpVentas(), BorderLayout.CENTER);
			pnVentas.add(getPnFiltro(), BorderLayout.NORTH);
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
			spVentas.setViewportView(getTbVentas());
		}
		return spVentas;
	}

	private JTable getTbVentas() {
		if (tbVentas == null) {
			String[] nombreColumnas = { "Código", "Zona", "Localidad", "Precio" };
			modeloTabla = new ModeloNoEditable(nombreColumnas, 0);
			tbVentas = new JTable(modeloTabla);
			tbVentas.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if (arg0.getClickCount() == 2) {
						int fila = tbVentas.getSelectedRow();
						if (fila != -1) {
							modeloLista.addElement(tbVentas.getValueAt(fila, 0));
						}
					}

					JTable table = (JTable) arg0.getSource();
					taDescripcion.setText(imb.getDescripcionMansion(table
							.getSelectedRow()));

				}
			});
			tbVentas.getTableHeader().setReorderingAllowed(false);
			tbVentas.setDefaultRenderer(Object.class,
					(TableCellRenderer) new RendererSubstance());
		}
		return tbVentas;
	}

	private JList getListaVisitas() {
		if (listaVisitas == null) {
			modeloLista = new DefaultListModel();

			listaVisitas = new JList(modeloLista);
		}
		return listaVisitas;
	}

	private void addRows(boolean norte, boolean centro, boolean sur) {
		Object[] nuevaFila = new Object[4];
		for (Mansion m : imb.getRelacionMansiones()) {
			if ((norte && m.getZona().equals("Norte") || (centro
					&& m.getZona().equals("Centro") || (sur && m.getZona()
					.equals("Sur"))))) {
				nuevaFila[0] = m.getCodigo();
				nuevaFila[1] = m.getZona();
				nuevaFila[2] = m.getLocalidad();
				nuevaFila[3] = m.getPrecio();

				modeloTabla.addRow(nuevaFila);
			}
		}
	}

	// JTable <-- DefaultTableModel <-- DataVector
	private void mostrarMansionesZona() {
		modeloTabla.getDataVector().clear();
		modeloTabla.fireTableDataChanged();
		addRows(chckbxNorte.isSelected(), chckbxCentro.isSelected(),
				chckbxSur.isSelected());
	}

	private void inicializar() {
		tpMansiones.setSelectedIndex(0);
		tbVentas.clearSelection();
		modeloLista.clear();
		taDescripcion.setText("");
		txtEntrada.setText("");
		spinner.setValue(15);
		spVentas.getViewport().setViewPosition(new Point(0, 0));
	}

	private void grabarVisitas() {
		String line = "";
		for (Object s : modeloLista.toArray()) {
			line = line + " " + s.toString();
		}
		if (imb.grabarFichero(line) == 0) {
			JOptionPane.showMessageDialog(this, "Hitler no era tan malo.");
		}
	}

	private JPanel getPnFiltro() {
		if (pnFiltro == null) {
			pnFiltro = new JPanel();
			pnFiltro.add(getChckbxNorte());
			pnFiltro.add(getChckbxCentro());
			pnFiltro.add(getChckbxSur());
		}
		return pnFiltro;
	}

	private JCheckBox getChckbxNorte() {
		if (chckbxNorte == null) {
			chckbxNorte = new JCheckBox("Norte");
			chckbxNorte.setSelected(true); // must be changed before, otherwise
											// NullPointerException (no default
											// state)
			chckbxNorte.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					mostrarMansionesZona();
				}
			});

		}
		return chckbxNorte;
	}

	private JCheckBox getChckbxCentro() {
		if (chckbxCentro == null) {
			chckbxCentro = new JCheckBox("Centro");
			chckbxCentro.setSelected(true);
			chckbxCentro.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					mostrarMansionesZona();
				}
			});

		}
		return chckbxCentro;
	}

	private JCheckBox getChckbxSur() {
		if (chckbxSur == null) {
			chckbxSur = new JCheckBox("Sur");
			chckbxSur.setSelected(true);
			chckbxSur.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					mostrarMansionesZona();
				}
			});

		}
		return chckbxSur;
	}
}
