package igu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import player.MusicPlayer;

import com.jtattoo.plaf.hifi.HiFiLookAndFeel;

public class VentanaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final int BUTTON_FONT_SIZE = 23;
	private static final int LABEL_FONT_SIZE = 32;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnNorte;
	private JPanel pnCentro;
	private JPanel pnLibreria;
	private JPanel pnLista;
	private JPanel pnVolumen;
	private JScrollPane scLista1;
	private JPanel pnBotonesLibreria;
	private JPanel pnBotonesPlay;
	private JLabel lblIcon;
	private JSlider slVolumen;
	private JLabel lblVol;
	private JTextField txtVol;
	private JLabel lblLibreria;
	private JList lista1;
	private JButton btDelete;
	private JButton btAdd;
	private JLabel lblPlaylist;
	private JScrollPane scLista2;
	private JList lista2;
	private JButton btPlay;
	private JButton btStop;
	private JButton btSiguiente;
	private JButton btAnterior;
	private JButton btDel;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenu mnPlay;
	private JMenu mnOptions;
	private JMenu mnHelp;
	private JMenuItem mntmOpen;
	private JMenuItem mntmPlay;
	private JMenuItem mntmStop;
	private JSeparator separator;
	private JMenuItem mntmExit;

	private DefaultListModel modeloLista1 = null;
	private DefaultListModel modeloLista2 = null;

	private JFileChooser selector = null;

	private MusicPlayer mP = new MusicPlayer();

	private Font fuenteDigital;
	private JMenuItem mntmRandom;
	private JSeparator separator_1;
	private JMenuItem mntmPause;
	private JMenuItem mntmClearLibrary;
	private JMenuItem mntmClearPlaylist;
	private JButton btPause;
	private JMenuItem mntmContents;
	private JMenuItem mntmAbout;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					Properties props = new Properties();
					props.put("logoString", "");
					HiFiLookAndFeel.setCurrentTheme(props);
					UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");

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
		cargarFuente();

		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/logoTitulo.png")));
		setTitle("EII Music Player");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 976, 427);
		setLocationRelativeTo(null);
		setJMenuBar(getMenuBar_1());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPnNorte(), BorderLayout.NORTH);
		contentPane.add(getPanel_1(), BorderLayout.CENTER);
		
		cargaAyuda();
	}

	private JPanel getPnNorte() {
		if (pnNorte == null) {
			pnNorte = new JPanel();
			pnNorte.setLayout(new GridLayout(1, 0, 0, 0));
			pnNorte.add(getLblIcon());
			pnNorte.add(getSlVolumen());
			pnNorte.add(getPanel_4());
		}
		return pnNorte;
	}

	private JPanel getPanel_1() {
		if (pnCentro == null) {
			pnCentro = new JPanel();
			pnCentro.setLayout(new GridLayout(1, 0, 0, 0));
			pnCentro.add(getPanel_2());
			pnCentro.add(getPanel_3());
		}
		return pnCentro;
	}

	private JPanel getPanel_2() {
		if (pnLibreria == null) {
			pnLibreria = new JPanel();
			pnLibreria.setLayout(new BorderLayout(0, 0));
			pnLibreria.add(getLblLibreria(), BorderLayout.NORTH);
			pnLibreria.add(getScLista1(), BorderLayout.CENTER);
			pnLibreria.add(getPanel_5(), BorderLayout.SOUTH);
		}
		return pnLibreria;
	}

	private JPanel getPanel_3() {
		if (pnLista == null) {
			pnLista = new JPanel();
			pnLista.setLayout(new BorderLayout(0, 0));
			pnLista.add(getLblPlaylist(), BorderLayout.NORTH);
			pnLista.add(getScLista2(), BorderLayout.CENTER);
			pnLista.add(getPanel_6(), BorderLayout.SOUTH);
		}
		return pnLista;
	}

	private JPanel getPanel_4() {
		if (pnVolumen == null) {
			pnVolumen = new JPanel();
			pnVolumen.add(getLblVol());
			pnVolumen.add(getTxtVol());
		}
		return pnVolumen;
	}

	private JScrollPane getScLista1() {
		if (scLista1 == null) {
			scLista1 = new JScrollPane();
			scLista1.setViewportView(getLista1());
		}
		return scLista1;
	}

	private JPanel getPanel_5() {
		if (pnBotonesLibreria == null) {
			pnBotonesLibreria = new JPanel();
			pnBotonesLibreria.setLayout(new GridLayout(0, 2, 0, 0));
			pnBotonesLibreria.add(getBtAdd());
			pnBotonesLibreria.add(getBtDelete());
		}
		return pnBotonesLibreria;
	}

	private JPanel getPanel_6() {
		if (pnBotonesPlay == null) {
			pnBotonesPlay = new JPanel();
			pnBotonesPlay.setLayout(new GridLayout(0, 6, 0, 0));
			pnBotonesPlay.add(getBtAnterior());
			pnBotonesPlay.add(getBtPlay());
			pnBotonesPlay.add(getBtPause());
			pnBotonesPlay.add(getBtStop());
			pnBotonesPlay.add(getBtSiguiente());
			pnBotonesPlay.add(getBtDel());
		}
		return pnBotonesPlay;
	}

	private JLabel getLblIcon() {
		if (lblIcon == null) {
			lblIcon = new JLabel("");
			lblIcon.setForeground(Color.GREEN);
			lblIcon.setHorizontalAlignment(SwingConstants.CENTER);
			lblIcon.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/logo.png")));
		}
		return lblIcon;
	}

	private JSlider getSlVolumen() {
		if (slVolumen == null) {
			slVolumen = new JSlider();
			slVolumen.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					int vol = slVolumen.getValue();
					txtVol.setText(String.valueOf(vol));
					mP.setVolume(vol, slVolumen.getMaximum());
				}
			});
			slVolumen.setBorder(null);
			slVolumen.setForeground(Color.GREEN);
			setMyfont(slVolumen, BUTTON_FONT_SIZE);
			slVolumen.setPaintTicks(true);
			slVolumen.setPaintLabels(true);
			slVolumen.setMinorTickSpacing(10);
			slVolumen.setMajorTickSpacing(20);
		}
		return slVolumen;
	}

	private JLabel getLblVol() {
		if (lblVol == null) {
			lblVol = new JLabel("Vol:");
			lblVol.setForeground(Color.GREEN);
			lblVol.setLabelFor(getTxtVol());
			setMyfont(lblVol, LABEL_FONT_SIZE);
		}
		return lblVol;
	}

	private JTextField getTxtVol() {
		if (txtVol == null) {
			txtVol = new JTextField();
			txtVol.setForeground(Color.GREEN);
			txtVol.setHorizontalAlignment(SwingConstants.CENTER);
			txtVol.setEditable(false);
			setMyfont(txtVol, LABEL_FONT_SIZE);
			txtVol.setText("0");
			txtVol.setColumns(3);
		}
		return txtVol;
	}

	private void cargarFuente() {
		try {
			InputStream myStream = new BufferedInputStream(new FileInputStream("bin/ttf/DS-DIGIB.ttf"));
			fuenteDigital = Font.createFont(Font.TRUETYPE_FONT, myStream);
		} catch (Exception e) {
			System.err.println("No se puede cargar la fuente");
		}
	}

	private JLabel getLblLibreria() {
		if (lblLibreria == null) {
			lblLibreria = new JLabel("Library:");
			lblLibreria.setHorizontalAlignment(SwingConstants.CENTER);
			lblLibreria.setForeground(Color.GREEN);
			setMyfont(lblLibreria, LABEL_FONT_SIZE);
		}
		return lblLibreria;
	}

	private JList getLista1() {
		if (lista1 == null) {
			modeloLista1 = new DefaultListModel();
			lista1 = new JList(modeloLista1);
		}
		return lista1;
	}

	private JButton getBtDelete() {
		if (btDelete == null) {
			btDelete = new JButton("Delete");
			btDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					List<Object> seleccionados = lista1.getSelectedValuesList();
					for (Object o : seleccionados) {
						modeloLista1.removeElement(o);
					}
				}
			});
			btDelete.setForeground(Color.GREEN);
			setMyfont(btDelete, BUTTON_FONT_SIZE);
		}
		return btDelete;
	}

	private JButton getBtAdd() {
		if (btAdd == null) {
			btAdd = new JButton("Add File");
			btAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					List<Object> seleccionados = lista1.getSelectedValuesList();
					for (Object o : seleccionados) {
						if (!modeloLista2.contains(o)) {
							modeloLista2.addElement(o);
						}
					}
				}
			});
			btAdd.setForeground(Color.GREEN);
			setMyfont(btAdd, BUTTON_FONT_SIZE);
		}
		return btAdd;
	}

	private void setMyfont(Component comp, int size) {
		Font myFont = fuenteDigital.deriveFont(Font.PLAIN, size);
		comp.setFont(myFont);
	}

	private JLabel getLblPlaylist() {
		if (lblPlaylist == null) {
			lblPlaylist = new JLabel("Playlist:");
			lblPlaylist.setHorizontalAlignment(SwingConstants.CENTER);
			lblPlaylist.setForeground(Color.GREEN);
			setMyfont(lblPlaylist, LABEL_FONT_SIZE);
		}
		return lblPlaylist;
	}

	private JScrollPane getScLista2() {
		if (scLista2 == null) {
			scLista2 = new JScrollPane();
			scLista2.setViewportView(getLista2());
		}
		return scLista2;
	}

	private JList getLista2() {
		if (lista2 == null) {
			modeloLista2 = new DefaultListModel();
			lista2 = new JList(modeloLista2);
			lista2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		return lista2;
	}

	private JButton getBtPlay() {
		if (btPlay == null) {
			btPlay = new JButton("\u25BA");
			btPlay.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					MyFile myFile = (MyFile) lista2.getSelectedValue();
					mP.play((myFile.getF()));
				}
			});
			btPlay.setForeground(Color.GREEN);
		}
		return btPlay;
	}

	private JButton getBtStop() {
		if (btStop == null) {
			btStop = new JButton("\u25A0");
			btStop.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mP.stop();
				}
			});
			btStop.setForeground(Color.GREEN);
		}
		return btStop;
	}

	private JButton getBtSiguiente() {
		if (btSiguiente == null) {
			btSiguiente = new JButton("\u25BA\u25BA");
			btSiguiente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int currentIndex = lista2.getSelectedIndex();
					if (currentIndex != modeloLista2.getSize() - 1) {
						lista2.setSelectedIndex(currentIndex + 1);
						MyFile myFile = (MyFile) lista2.getSelectedValue();
						mP.play((myFile.getF()));
					}
				}
			});
			btSiguiente.setForeground(Color.GREEN);
		}
		return btSiguiente;
	}

	private JButton getBtAnterior() {
		if (btAnterior == null) {
			btAnterior = new JButton("\u25C4\u25C4");
			btAnterior.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int currentIndex = lista2.getSelectedIndex();
					if (currentIndex != 0) {
						lista2.setSelectedIndex(currentIndex - 1);
						MyFile myFile = (MyFile) lista2.getSelectedValue();
						mP.play((myFile.getF()));
					}
				}
			});
			btAnterior.setForeground(Color.GREEN);
		}
		return btAnterior;
	}

	private JButton getBtDel() {
		if (btDel == null) {
			btDel = new JButton("Del");
			btDel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Object toRemove = lista2.getSelectedValue();
					modeloLista2.removeElement(toRemove);
				}
			});
			btDel.setForeground(Color.GREEN);
			setMyfont(btDel, BUTTON_FONT_SIZE);
		}
		return btDel;
	}

	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnFile());
			menuBar.add(getMnPlay());
			menuBar.add(getMnOptions());
			menuBar.add(getMnHelp());
		}
		return menuBar;
	}

	private JMenu getMnFile() {
		if (mnFile == null) {
			mnFile = new JMenu("File");
			mnFile.setForeground(Color.GREEN);
			mnFile.setMnemonic('F');
			mnFile.add(getMntmOpen());
			mnFile.add(getSeparator());
			mnFile.add(getMntmExit());
		}
		return mnFile;
	}

	private JMenu getMnPlay() {
		if (mnPlay == null) {
			mnPlay = new JMenu("Play");
			mnPlay.setForeground(Color.GREEN);
			mnPlay.setMnemonic('P');
			mnPlay.add(getMntmPlay());
			mnPlay.add(getMntmPause());
			mnPlay.add(getMntmStop());
			mnPlay.add(getSeparator_1());
			mnPlay.add(getMntmRandom());
		}
		return mnPlay;
	}

	private JMenu getMnOptions() {
		if (mnOptions == null) {
			mnOptions = new JMenu("Options");
			mnOptions.setForeground(Color.GREEN);
			mnOptions.setMnemonic('t');
			mnOptions.add(getMntmClearLibrary());
			mnOptions.add(getMntmClearPlaylist());
		}
		return mnOptions;
	}

	private JMenu getMnHelp() {
		if (mnHelp == null) {
			mnHelp = new JMenu("Help");
			mnHelp.setForeground(Color.GREEN);
			mnHelp.setMnemonic('H');
			mnHelp.add(getMntmContents());
			mnHelp.add(getMntmAbout());
		}
		return mnHelp;
	}

	private JMenuItem getMntmOpen() {
		if (mntmOpen == null) {
			mntmOpen = new JMenuItem("Open");
			mntmOpen.setMnemonic('p');
			mntmOpen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int respuesta = getSelector().showOpenDialog(null);
					if (respuesta == JFileChooser.APPROVE_OPTION) {
						for (int i = 0; i < selector.getSelectedFiles().length; i++) {
							MyFile myFile = new MyFile(selector.getSelectedFiles()[i]);
							modeloLista1.addElement(myFile);
						}
					}
				}
			});
		}
		return mntmOpen;
	}

	public JFileChooser getSelector() {
		if (selector == null) {
			selector = new JFileChooser();
			selector.setFileFilter(new FileNameExtensionFilter("Archivos mp3", "mp3"));
			String path = System.getProperty("user.home") + "/Desktop";
			selector.setCurrentDirectory(new File(path));
			selector.setMultiSelectionEnabled(true);
		}

		return selector;
	}

	private JMenuItem getMntmPlay() {
		if (mntmPlay == null) {
			mntmPlay = new JMenuItem("Play");
			mntmPlay.setMnemonic('l');
			mntmPlay.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MyFile myFile = (MyFile) lista2.getSelectedValue();
					mP.play((myFile.getF()));
				}
			});
		}
		return mntmPlay;
	}

	private JMenuItem getMntmStop() {
		if (mntmStop == null) {
			mntmStop = new JMenuItem("Stop");
			mntmStop.setMnemonic('S');
			mntmStop.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mP.stop();
				}
			});
		}
		return mntmStop;
	}

	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
		}
		return separator;
	}

	private JMenuItem getMntmExit() {
		if (mntmExit == null) {
			mntmExit = new JMenuItem("Exit");
			mntmExit.setMnemonic('x');
			mntmExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.exit(0);
				}
			});
		}
		return mntmExit;
	}
	private JMenuItem getMntmRandom() {
		if (mntmRandom == null) {
			mntmRandom = new JMenuItem("Random song");
			mntmRandom.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (!modeloLista2.isEmpty()){
						int size = modeloLista2.size();
						Random generator = new Random();
						int randomIndex = generator.nextInt(size);
						while (randomIndex == lista2.getSelectedIndex()){
							randomIndex = generator.nextInt(size);
						}
						lista2.setSelectedIndex(randomIndex);
					}
				}
			});
			mntmRandom.setMnemonic('R');
		}
		return mntmRandom;
	}
	private JSeparator getSeparator_1() {
		if (separator_1 == null) {
			separator_1 = new JSeparator();
		}
		return separator_1;
	}
	private JMenuItem getMntmPause() {
		if (mntmPause == null) {
			mntmPause = new JMenuItem("Pause");
			mntmPause.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mP.pause();
				}
			});
			mntmPause.setMnemonic('a');
		}
		return mntmPause;
	}
	private JMenuItem getMntmClearLibrary() {
		if (mntmClearLibrary == null) {
			mntmClearLibrary = new JMenuItem("Clear library");
			mntmClearLibrary.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					modeloLista1.clear();
				}
			});
			mntmClearLibrary.setMnemonic('C');
		}
		return mntmClearLibrary;
	}
	private JMenuItem getMntmClearPlaylist() {
		if (mntmClearPlaylist == null) {
			mntmClearPlaylist = new JMenuItem("Clear playlist");
			mntmClearPlaylist.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					modeloLista2.clear();
				}
			});
			mntmClearPlaylist.setMnemonic('r');
		}
		return mntmClearPlaylist;
	}
	private JButton getBtPause() {
		if (btPause == null) {
			btPause = new JButton("\u275A\u275A");
			btPause.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mP.pause();
				}
			});
			btPause.setForeground(Color.GREEN);
		}
		return btPause;
	}
	private JMenuItem getMntmContents() {
		if (mntmContents == null) {
			mntmContents = new JMenuItem("Contents");
			mntmContents.setMnemonic('C');
		}
		return mntmContents;
	}
	private JMenuItem getMntmAbout() {
		if (mntmAbout == null) {
			mntmAbout = new JMenuItem("About");
			mntmAbout.setMnemonic('b');
		}
		return mntmAbout;
	}
	
	private void cargaAyuda(){

		   URL hsURL;
		   HelpSet hs;

		   try {
			    File fichero = new File("help/Ayuda.hs");
			    hsURL = fichero.toURI().toURL();
			    hs = new HelpSet(null, hsURL);
		}

		    catch (Exception e){
		      System.out.println("Ayuda no encontrada");
		     return;
		   }

		   HelpBroker hb = hs.createHelpBroker();
		   hb.initPresentation();

		   // content pane: unless you press specific component, 
		   // it will show intro
		   hb.enableHelpKey(getRootPane(),"intro", hs);
		   hb.enableHelpOnButton(mntmContents, "intro", hs);
		   hb.enableHelp(lista1, "add", hs);
		   hb.enableHelp(lista2, "play", hs);
		   hb.enableHelp(slVolumen, "volume", hs);
		   
		 }
}
