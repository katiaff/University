package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URL;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableCellRenderer;

import gui.customelements.NonEditableTable;
import gui.customelements.TableRenderer;
import gui.dialogs.AboutDialog;
import gui.dialogs.InfoDialog;
import gui.dialogs.LanguageSelector;
import logic.implementation.Agency;
import logic.tripelements.Cruise;

import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Agency agency;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnHelp;
	private JMenuItem mntmAbout;
	private JLabel lblWelcome;
	private ResourceBundle texts;
	private JMenu mnEdit;
	private JMenuItem mntmLanguage;
	private Locale locale;
	private JMenu mnCruiseAgency;
	private JMenuItem mntmExit;
	private JTabbedPane tpCruises;
	private JPanel pnDiscounts;
	private JPanel pnAll;
	private JButton btnShowDiscounts;
	private JPanel discount0;
	private JPanel discount1;
	private JButton btnDiscount0;
	private JButton btnDiscount1;
	private JLabel lblDiscount0;
	private JLabel lblDiscount1;
	private JPanel pnButtonsDiscounts;
	private JPanel pnLabels0;
	private JLabel lblOff0;
	private JPanel pnLabels1;
	private JLabel lblOff1;
	private JScrollPane spAll;
	private JTable tbAll;
	private JButton btnShowAll;
	private List<Cruise> discounted;
	public List<Cruise> allCruises;
	private JPanel pnSearch;
	private JButton btnSearch;
	private JPanel pnSearchFilter;
	private JLabel lblFrom;
	private JLabel lblPriceRange;
	private JComboBox<String> cbPriceRange;
	private JScrollPane spSearch;
	private JTable tbSearch;
	private JButton btnShowSearch;
	private int max;
	private int min;
	private JComboBox<String> cbDates;
	private Currency currency;
	private String currencySymbol;
	private int interval;
	private boolean tooltips = true;
	private JMenuItem mntmTooltips;
	private JMenuItem mntmHelpSupport;
	private HelpBroker hb;
	private JLabel lblArea;
	private JComboBox<String> cbArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
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
	public MainWindow() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				mntmExit.doClick();
			}
		});
		JDialog.setDefaultLookAndFeelDecorated(true);
		JFrame.setDefaultLookAndFeelDecorated(true);
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		agency = new Agency();
		discounted = agency.getDiscountedCruises();
		allCruises = agency.getCruises();
		locale = Locale.getDefault();
		texts = ResourceBundle.getBundle("resources.MainWindow", locale);
		currency = Currency.getInstance(locale);
		currencySymbol = currency.getSymbol();
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/img/icon.png")));
		setTitle(texts.getString("title"));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1024, 768);
		setLocationRelativeTo(null);
		setJMenuBar(getMenuBar_1());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getLblWelcome(), BorderLayout.NORTH);
		contentPane.add(getTpCruises(), BorderLayout.CENTER);

		loadHelp(this.locale);
		showDiscounts();

	}

	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnCruiseAgency());
			menuBar.add(getMnEdit());
			menuBar.add(getMnHelp());
		}
		return menuBar;
	}

	private JMenu getMnHelp() {
		if (mnHelp == null) {
			mnHelp = new JMenu(texts.getString("menuHelp"));
			String mnemonic = texts.getString("mnemoMnHelp");
			mnHelp.setMnemonic(mnemonic.charAt(0));
			mnHelp.add(getMntmHelpSupport());
			mnHelp.add(getMntmAbout());
		}
		return mnHelp;
	}

	private JMenuItem getMntmAbout() {
		if (mntmAbout == null) {
			mntmAbout = new JMenuItem(texts.getString("menuItemAbout"));
			mntmAbout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					showAboutDialog();
				}
			});
			String mnemonic = texts.getString("mnemoMntmAbout");
			mntmAbout.setMnemonic(mnemonic.charAt(0));
		}
		return mntmAbout;
	}

	private void showAboutDialog() {
		AboutDialog about = new AboutDialog(locale);
		about.setVisible(true);

	}

	private JLabel getLblWelcome() {
		if (lblWelcome == null) {
			lblWelcome = new JLabel(texts.getString("lblWelcome"));
			lblWelcome.setFont(new Font("Imprint MT Shadow", Font.BOLD, 44));
			lblWelcome.setHorizontalAlignment(0);
		}
		return lblWelcome;
	}

	private JMenu getMnEdit() {
		if (mnEdit == null) {
			mnEdit = new JMenu(texts.getString("menuEdit"));
			String mnemonic = texts.getString("mnemoMnEdit");
			mnEdit.setMnemonic(mnemonic.charAt(0));
			mnEdit.add(getMntmLanguage());
			mnEdit.add(getMntmTooltips());
		}
		return mnEdit;
	}

	private JMenuItem getMntmLanguage() {
		if (mntmLanguage == null) {
			mntmLanguage = new JMenuItem(texts.getString("menuItemLanguage"));
			String mnemonic = texts.getString("mnemoMntmLanguage");
			mntmLanguage.setMnemonic(mnemonic.charAt(0));
			mntmLanguage.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					openLanguageSelector();
				}
			});
		}
		return mntmLanguage;
	}

	private void openLanguageSelector() {
		LanguageSelector ls = new LanguageSelector(this, locale);
		ls.setVisible(true);
	}

	public void updateLanguage(Locale newLocale) {
		this.locale = newLocale;
		this.texts = ResourceBundle.getBundle("resources.MainWindow", locale);
		updateTexts();
		updateMnemonics();
		updateCurrency();
		loadHelp(locale);	
		deleteBookedCabinsFile();
	}

	private void deleteBookedCabinsFile() {
		File file = new File("files/bookedCabins.dat");
		if (file.exists()) {
			file.delete();
		}
	}

	private void updateCurrency() {
		currency = Currency.getInstance(locale);
		currencySymbol = currency.getSymbol();
		String[] intervals = new String[] { min + "-" + (min + interval) + currencySymbol,
				(min + interval) + "-" + (min + 2 * interval) + currencySymbol,
				(min + 2 * interval) + "-" + max + currencySymbol };
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(intervals);
		cbPriceRange.setModel(model);
	}

	private void updateMnemonics() {
		String mnemonic = texts.getString("mnemoMnCruiseAgency");
		mnCruiseAgency.setMnemonic(mnemonic.charAt(0));
		mnemonic = texts.getString("mnemoMnHelp");
		mnHelp.setMnemonic(mnemonic.charAt(0));
		mnemonic = texts.getString("mnemoMntmAbout");
		mntmAbout.setMnemonic(mnemonic.charAt(0));
		mnemonic = texts.getString("mnemoMnEdit");
		mnEdit.setMnemonic(mnemonic.charAt(0));
		mnemonic = texts.getString("mnemoMntmLanguage");
		mntmLanguage.setMnemonic(mnemonic.charAt(0));
		mnemonic = texts.getString("mnemoMntmExit");
		mntmExit.setMnemonic(mnemonic.charAt(0));
		mnemonic = texts.getString("mnemomntmHelpSupport");
		mntmHelpSupport.setMnemonic(mnemonic.charAt(0));
		if (mntmTooltips.isSelected()) {
			mnemonic = texts.getString("mntmTooltipDisable");
			mntmTooltips.setMnemonic(mnemonic.charAt(0));
		} else {
			mnemonic = texts.getString("mntmTooltipEnable");
			mntmTooltips.setMnemonic(mnemonic.charAt(0));
		}
		mnemonic = texts.getString("mnemoTabDiscounts");
		tpCruises.setMnemonicAt(0, mnemonic.charAt(0));
		mnemonic = texts.getString("mnemoTabSearch");
		tpCruises.setMnemonicAt(1, mnemonic.charAt(0));
		mnemonic = texts.getString("mnemoTabAll");
		tpCruises.setMnemonicAt(2, mnemonic.charAt(0));
		mnemonic = texts.getString("mnemoBtnShow");
		btnShowDiscounts.setMnemonic(mnemonic.charAt(0));
		btnShowSearch.setMnemonic(mnemonic.charAt(0));
		btnShowAll.setMnemonic(mnemonic.charAt(0));
		mnemonic = texts.getString("mnemoBtnSearch");
		btnSearch.setMnemonic(mnemonic.charAt(0));
		mnemonic = texts.getString("mnemoArea");
		lblArea.setDisplayedMnemonic(mnemonic.charAt(0));
		mnemonic = texts.getString("mnemoStart");
		lblFrom.setDisplayedMnemonic(mnemonic.charAt(0));
		mnemonic = texts.getString("mnemoPriceRange");
		lblPriceRange.setDisplayedMnemonic(mnemonic.charAt(0));
	}

	private void updateTexts() {
		setTitle(texts.getString("title"));
		mnCruiseAgency.setText(texts.getString("menuCruiseAgency"));
		mnHelp.setText(texts.getString("menuHelp"));
		mntmAbout.setText(texts.getString("menuItemAbout"));
		lblWelcome.setText(texts.getString("lblWelcome"));
		mnEdit.setText(texts.getString("menuEdit"));
		mntmLanguage.setText(texts.getString("menuItemLanguage"));
		mntmExit.setText(texts.getString("menuItemExit"));
		mntmHelpSupport.setText(texts.getString("mntmHelpSupport"));
		tpCruises.setTitleAt(0, texts.getString("tabDiscounts"));
		tpCruises.setTitleAt(1, texts.getString("tabSearch"));
		tpCruises.setTitleAt(2, texts.getString("tabAll"));
		btnShowDiscounts.setText(texts.getString("btnShow"));
		btnShowAll.setText(texts.getString("btnShow"));
		btnShowSearch.setText(texts.getString("btnShow"));
		lblOff0.setText(texts.getString("lblOff"));
		lblOff1.setText(texts.getString("lblOff"));
		lblArea.setText(texts.getString("area"));
		updateColumnsTable();
		btnSearch.setText(texts.getString("btnSearch"));
		lblFrom.setText(texts.getString("lblFrom"));
		lblPriceRange.setText(texts.getString("lblPriceRange"));
		spSearch.setBorder(new TitledBorder(null, texts.getString("borderTbSearch"), TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		if (mntmTooltips.isSelected()) {
			mntmTooltips.setText(texts.getString("mntmTooltipDisable"));
		} else {
			mntmTooltips.setText(texts.getString("mntmTooltipEnable"));
		}
	}

	private void updateColumnsTable() {
		String[] newColumns = { texts.getString("column0"), texts.getString("column1"), texts.getString("column2"),
				texts.getString("column3") };
		NonEditableTable model = (NonEditableTable) tbAll.getModel();
		model.setColumnIdentifiers(newColumns);

		model = (NonEditableTable) tbSearch.getModel();
		model.setColumnIdentifiers(newColumns);

	}

	private JMenu getMnCruiseAgency() {
		if (mnCruiseAgency == null) {
			mnCruiseAgency = new JMenu(texts.getString("menuCruiseAgency"));
			String mnemonic = texts.getString("mnemoMnCruiseAgency");
			mnCruiseAgency.setMnemonic(mnemonic.charAt(0));
			mnCruiseAgency.add(getMntmExit());
		}
		return mnCruiseAgency;
	}

	private JMenuItem getMntmExit() {
		if (mntmExit == null) {
			mntmExit = new JMenuItem(texts.getString("menuItemExit"));
			String mnemonic = texts.getString("mnemoMntmExit");
			mntmExit.setMnemonic(mnemonic.charAt(0));
			mntmExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int exit = JOptionPane.showConfirmDialog(null, texts.getString("exitMsg"),
							texts.getString("exitTitle"), JOptionPane.YES_NO_OPTION);
					if (exit == JOptionPane.YES_OPTION) {
						exit();
					}
				}
			});
		}
		return mntmExit;
	}

	private JTabbedPane getTpCruises() {
		if (tpCruises == null) {
			tpCruises = new JTabbedPane(JTabbedPane.TOP);
			tpCruises.addTab(texts.getString("tabDiscounts"), null, getPnDiscounts(), null);
			tpCruises.addTab(texts.getString("tabSearch"), null, getPnSearch(), null);
			tpCruises.addTab(texts.getString("tabAll"), null, getPnAll(), null);
			String mnemonic = texts.getString("mnemoTabDiscounts");
			tpCruises.setMnemonicAt(0, mnemonic.charAt(0));
			mnemonic = texts.getString("mnemoTabSearch");
			tpCruises.setMnemonicAt(1, mnemonic.charAt(0));
			mnemonic = texts.getString("mnemoTabAll");
			tpCruises.setMnemonicAt(2, mnemonic.charAt(0));
		}
		return tpCruises;
	}

	private JPanel getPnAll() {
		if (pnAll == null) {
			pnAll = new JPanel();
			pnAll.setLayout(new BorderLayout(0, 0));
			pnAll.add(getSpAll(), BorderLayout.CENTER);
			pnAll.add(getBtnShowAll(), BorderLayout.SOUTH);
			pnAll.setFocusTraversalPolicy(
					new FocusTraversalOnArray(new Component[] { getSpAll(), getTbAll(), getBtnShowAll() }));
		}
		return pnAll;
	}

	private JPanel getPnDiscounts() {
		if (pnDiscounts == null) {
			pnDiscounts = new JPanel();
			pnDiscounts.setLayout(new BorderLayout(0, 0));
			pnDiscounts.add(getBtnShowDiscounts(), BorderLayout.SOUTH);
			pnDiscounts.add(getPnButtonsDiscounts(), BorderLayout.CENTER);
			pnDiscounts.setFocusTraversalPolicy(new FocusTraversalOnArray(
					new Component[] { getBtnDiscount0(), getBtnDiscount1(), getBtnShowDiscounts() }));
		}
		return pnDiscounts;
	}

	private JButton getBtnShowDiscounts() {
		if (btnShowDiscounts == null) {
			btnShowDiscounts = new JButton(texts.getString("btnShow"));
			btnShowDiscounts.setFont(new Font("Tahoma", Font.PLAIN, 14));
			String mnemonic = texts.getString("mnemoBtnShow");
			btnShowDiscounts.setMnemonic(mnemonic.charAt(0));
			btnShowDiscounts.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Cruise toShow = agency.searchCruises(btnShowDiscounts.getActionCommand());
					openInfoDialog(toShow);
				}
			});
			btnShowDiscounts.setEnabled(false);
		}
		return btnShowDiscounts;
	}

	private void openInfoDialog(Cruise toShow) {
		InfoDialog info = new InfoDialog(locale, toShow, agency, tooltips, this.hb, this);
		info.setVisible(true);
	}

	private JButton getBtnDiscount0() {
		if (btnDiscount0 == null) {
			btnDiscount0 = new JButton("");
			btnDiscount0.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					btnDiscount0Click();
				}
			});
			btnDiscount0.setToolTipText(texts.getString("click"));
			btnDiscount0.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 1) {
						btnDiscount0Click();
					}
					if (e.getClickCount() > 1) {
						openInfoDialog(discounted.get(0));
					}
				}
			});
			btnDiscount0.addComponentListener(new ComponentAdapter() {
				@Override
				public void componentResized(ComponentEvent arg0) {
					showDiscounts();
				}
			});
		}
		return btnDiscount0;
	}

	private JPanel getDiscount0() {
		if (discount0 == null) {
			discount0 = new JPanel();
			discount0.setLayout(new BorderLayout(0, 0));
			discount0.add(getPnLabels0(), BorderLayout.SOUTH);
			discount0.add(getBtnDiscount0(), BorderLayout.CENTER);
		}
		return discount0;
	}

	private void showDiscounts() {
		btnDiscount0.setActionCommand(discounted.get(0).getCode());
		setAdaptedImage(btnDiscount0, "/img/cruises/" + discounted.get(0).getCode() + ".jpg");
		lblDiscount0.setText("1. " + discounted.get(0).getName());
		String mnemonic = "1";
		lblDiscount0.setDisplayedMnemonic(mnemonic.charAt(0));
		btnDiscount1.setActionCommand(discounted.get(1).getCode());
		lblDiscount1.setText("2. " + discounted.get(1).getName());
		mnemonic = "2";
		lblDiscount1.setDisplayedMnemonic(mnemonic.charAt(0));
		setAdaptedImage(btnDiscount1, "/img/cruises/" + discounted.get(1).getCode() + ".jpg");

	}

	private void setAdaptedImage(JButton button, String route) {
		setVisible(true);
		Image imgOriginal = new ImageIcon(getClass().getResource(route)).getImage();
		Image imgScaled = imgOriginal.getScaledInstance((int) (button.getWidth()), (int) (button.getHeight()),
				Image.SCALE_FAST);
		button.setIcon(new ImageIcon(imgScaled));
	}

	private JPanel getDiscount1() {
		if (discount1 == null) {
			discount1 = new JPanel();
			discount1.setLayout(new BorderLayout(0, 0));
			discount1.add(getBtnDiscount1(), BorderLayout.CENTER);
			discount1.add(getPnLabels1(), BorderLayout.SOUTH);
		}
		return discount1;
	}

	private JButton getBtnDiscount1() {
		if (btnDiscount1 == null) {
			btnDiscount1 = new JButton("");
			btnDiscount1.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					btnDiscount1Click();
				}
			});
			btnDiscount1.setToolTipText(texts.getString("click"));
			btnDiscount1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 1) {
						btnDiscount1Click();
					}
					if (e.getClickCount() > 1) {
						openInfoDialog(discounted.get(1));
					}
				}
			});

		}
		return btnDiscount1;
	}

	private JLabel getLblDiscount0() {
		if (lblDiscount0 == null) {
			lblDiscount0 = new JLabel("");
			lblDiscount0.setLabelFor(getBtnDiscount0());
			lblDiscount0.setFont(new Font("Imprint MT Shadow", Font.BOLD, 21));
			lblDiscount0.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblDiscount0;
	}

	private JLabel getLblDiscount1() {
		if (lblDiscount1 == null) {
			lblDiscount1 = new JLabel("");
			lblDiscount1.setLabelFor(getBtnDiscount1());
			lblDiscount1.setFont(new Font("Imprint MT Shadow", Font.BOLD, 21));
			lblDiscount1.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblDiscount1;
	}

	private JPanel getPnButtonsDiscounts() {
		if (pnButtonsDiscounts == null) {
			pnButtonsDiscounts = new JPanel();
			pnButtonsDiscounts.setLayout(new GridLayout(0, 2, 0, 0));
			pnButtonsDiscounts.add(getDiscount0());
			pnButtonsDiscounts.add(getDiscount1());
		}
		return pnButtonsDiscounts;
	}

	private JPanel getPnLabels0() {
		if (pnLabels0 == null) {
			pnLabels0 = new JPanel();
			pnLabels0.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			pnLabels0.add(getLblDiscount0());
			pnLabels0.add(getLblOff0());
		}
		return pnLabels0;
	}

	private JLabel getLblOff0() {
		if (lblOff0 == null) {
			lblOff0 = new JLabel(texts.getString("lblOff"));
			lblOff0.setForeground(Color.RED);
			lblOff0.setFont(new Font("Imprint MT Shadow", Font.BOLD, 21));
		}
		return lblOff0;
	}

	private JPanel getPnLabels1() {
		if (pnLabels1 == null) {
			pnLabels1 = new JPanel();
			pnLabels1.add(getLblDiscount1());
			pnLabels1.add(getLblOff1());
		}
		return pnLabels1;
	}

	private JLabel getLblOff1() {
		if (lblOff1 == null) {
			lblOff1 = new JLabel(texts.getString("lblOff"));
			lblOff1.setForeground(Color.RED);
			lblOff1.setFont(new Font("Imprint MT Shadow", Font.BOLD, 21));
		}
		return lblOff1;
	}

	private JScrollPane getSpAll() {
		if (spAll == null) {
			spAll = new JScrollPane();
			spAll.setViewportView(getTbAll());
		}
		return spAll;
	}

	private JTable getTbAll() {
		if (tbAll == null) {
			String[] columns = { texts.getString("column0"), texts.getString("column1"), texts.getString("column2"),
					texts.getString("column3") };
			NonEditableTable model = new NonEditableTable(columns, 0);
			tbAll = new JTable(model);
			tbAll.setFont(new Font("Serif", Font.PLAIN, 17));
			tbAll.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tbAll.getColumnModel().getColumn(2).setPreferredWidth(200);
			loadTbAll(model);
			tbAll.setToolTipText(texts.getString("click"));
			tbAll.setDefaultRenderer(Object.class, (TableCellRenderer) new TableRenderer());
			tbAll.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int row = tbAll.getSelectedRow();
					if (row != -1) {
						if (e.getClickCount() == 1) {
							btnShowAll.setEnabled(true);
						}
						if (e.getClickCount() > 1) {
							Cruise cruise = getCruiseTbAll(row);
							openInfoDialog(cruise);
						}
					}
				}

			});

		}
		return tbAll;
	}

	private void loadTbAll(NonEditableTable model) {
		Object[] newRow = new Object[4];
		for (Cruise c : allCruises) {
			newRow[0] = c.getName();
			newRow[1] = c.getArea();
			newRow[2] = c.getItinerary();
			newRow[3] = c.getDuration();
			model.addRow(newRow);
		}
	}

	private JButton getBtnShowAll() {
		if (btnShowAll == null) {
			btnShowAll = new JButton(texts.getString("btnShow"));
			btnShowAll.setFont(new Font("Tahoma", Font.PLAIN, 14));
			String mnemonic = texts.getString("mnemoBtnShow");
			btnShowAll.setMnemonic(mnemonic.charAt(0));
			btnShowAll.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int row = tbAll.getSelectedRow();
					if (row != -1) {
						Cruise cruise = getCruiseTbAll(row);
						openInfoDialog(cruise);
					}

				}

			});
			btnShowAll.setEnabled(false);
		}
		return btnShowAll;
	}

	private JPanel getPnSearch() {
		if (pnSearch == null) {
			pnSearch = new JPanel();
			pnSearch.setLayout(new BorderLayout(0, 0));
			pnSearch.add(getPnSearchFilter(), BorderLayout.NORTH);
			pnSearch.add(getSpSearch(), BorderLayout.CENTER);
			pnSearch.add(getBtnShowSearch(), BorderLayout.SOUTH);
			pnSearch.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { getPnSearchFilter(),
					getLblArea(), getCbArea(), getLblFrom(), getCbDates(), getLblPriceRange(), getCbPriceRange(),
					getBtnSearch(), getSpSearch(), getTbSearch(), getBtnShowSearch() }));
		}
		return pnSearch;
	}

	private JButton getBtnSearch() {
		if (btnSearch == null) {
			btnSearch = new JButton(texts.getString("btnSearch"));
			btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
			String mnemonic = texts.getString("mnemoBtnSearch");
			btnSearch.setMnemonic(mnemonic.charAt(0));
			btnSearch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					search();
				}
			});
		}
		return btnSearch;
	}

	private void loadTbSearch(NonEditableTable model, List<Cruise> filtered) {
		model.getDataVector().clear();
		model.fireTableDataChanged();
		Object[] newRow = new Object[4];
		for (Cruise c : filtered) {
			newRow[0] = c.getName();
			newRow[1] = c.getArea();
			newRow[2] = c.getItinerary();
			newRow[3] = c.getDuration();
			model.addRow(newRow);
		}
	}

	private JPanel getPnSearchFilter() {
		if (pnSearchFilter == null) {
			pnSearchFilter = new JPanel();
			pnSearchFilter.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 5));
			pnSearchFilter.add(getLblArea());
			pnSearchFilter.add(getCbArea());
			pnSearchFilter.add(getLblFrom());
			pnSearchFilter.add(getCbDates());
			pnSearchFilter.add(getLblPriceRange());
			pnSearchFilter.add(getCbPriceRange());
			pnSearchFilter.add(getBtnSearch());
			pnSearchFilter.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { getLblArea(),
					getCbArea(), getLblFrom(), getCbDates(), getLblPriceRange(), getCbPriceRange(), getBtnSearch() }));
		}
		return pnSearchFilter;
	}

	private JLabel getLblFrom() {
		if (lblFrom == null) {
			lblFrom = new JLabel(texts.getString("lblFrom"));
			String mnemonic = texts.getString("mnemoStart");
			lblFrom.setDisplayedMnemonic(mnemonic.charAt(0));
			lblFrom.setLabelFor(getCbDates());
			lblFrom.setFont(new Font("Imprint MT Shadow", Font.BOLD, 21));
		}
		return lblFrom;
	}

	private JLabel getLblPriceRange() {
		if (lblPriceRange == null) {
			lblPriceRange = new JLabel(texts.getString("lblPriceRange"));
			String mnemonic = texts.getString("mnemoPriceRange");
			lblPriceRange.setDisplayedMnemonic(mnemonic.charAt(0));
			lblPriceRange.setLabelFor(getCbPriceRange());
			lblPriceRange.setFont(new Font("Imprint MT Shadow", Font.BOLD, 21));
		}
		return lblPriceRange;
	}

	private JComboBox<String> getCbPriceRange() {
		if (cbPriceRange == null) {
			cbPriceRange = new JComboBox<String>();
			cbPriceRange.setFont(new Font("Tahoma", Font.PLAIN, 14));
			min = 1;
			max = agency.getMaxPriceCabin();
			interval = (max - min) / 4;
			String[] intervals = new String[] { min + "-" + (min + interval) + currencySymbol,
					(min + interval) + "-" + (min + 2 * interval) + currencySymbol,
					(min + 2 * interval) + "-" + max + currencySymbol };
			DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(intervals);
			cbPriceRange.setModel(model);
			// center elements
			((JLabel) cbPriceRange.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

		}
		return cbPriceRange;
	}

	private JScrollPane getSpSearch() {
		if (spSearch == null) {
			spSearch = new JScrollPane();
			spSearch.setViewportView(getTbSearch());
			spSearch.setBorder(new TitledBorder(new LineBorder(new Color(52, 52, 52), 2),
					texts.getString("borderTbSearch"), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		}
		return spSearch;
	}

	private JTable getTbSearch() {
		if (tbSearch == null) {
			String[] columns = { texts.getString("column0"), texts.getString("column1"), texts.getString("column2"),
					texts.getString("column3") };
			NonEditableTable model = new NonEditableTable(columns, 0);
			tbSearch = new JTable(model);
			tbSearch.setFont(new Font("Serif", Font.PLAIN, 17));
			tbSearch.getColumnModel().getColumn(2).setPreferredWidth(200);
			tbSearch.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tbSearch.setDefaultRenderer(Object.class, (TableCellRenderer) new TableRenderer());
			tbSearch.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int row = tbSearch.getSelectedRow();
					if (row != -1) {
						if (e.getClickCount() == 1) {
							btnShowSearch.setEnabled(true);
						}
						if (e.getClickCount() > 1) {
							Cruise cruise = getCruiseTbSearch(row);
							openInfoDialog(cruise);
						}
					}
				}

			});
			tbSearch.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					btnShowSearch.setEnabled(true);
				}
			});

		}

		return tbSearch;
	}

	private JButton getBtnShowSearch() {
		if (btnShowSearch == null) {
			btnShowSearch = new JButton(texts.getString("btnShow"));
			btnShowSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
			String mnemonic = texts.getString("mnemoBtnShow");
			btnShowSearch.setMnemonic(mnemonic.charAt(0));
			btnShowSearch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int row = tbSearch.getSelectedRow();
					if (row != -1) {
						Cruise cruise = getCruiseTbSearch(row);
						openInfoDialog(cruise);
					}
				}
			});
			btnShowSearch.setEnabled(false);
		}
		return btnShowSearch;
	}

	private JComboBox<String> getCbDates() {
		if (cbDates == null) {
			cbDates = new JComboBox<String>();
			cbDates.setFont(new Font("Tahoma", Font.PLAIN, 14));
			String[] dates = agency.getAvailableDates();
			DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(dates);
			cbDates.setModel(model);
		}
		return cbDates;
	}

	private void search() {
		String date = (String) cbDates.getSelectedItem();
		String dashedRange = (String) cbPriceRange.getSelectedItem();
		String withoutSymbol = dashedRange.replace(currencySymbol, "");
		int rangeLow = Integer.valueOf(withoutSymbol.split("-")[0]);
		int rangeHigh = Integer.valueOf(withoutSymbol.split("-")[1]);
		String area = (String) cbArea.getSelectedItem();
		List<Cruise> filtered = agency.filterCruises(date, rangeLow, rangeHigh, area);
		NonEditableTable model = (NonEditableTable) tbSearch.getModel();
		if (!filtered.isEmpty()) {
			if (tooltips) {
				tbSearch.setToolTipText(texts.getString("click"));
			}
			loadTbSearch(model, filtered);
		} else {
			model.getDataVector().clear();
			model.fireTableDataChanged();
			Object[] newRow = new Object[4];
			newRow[0] = texts.getString("noResults");
			newRow[1] = texts.getString("noResults");
			newRow[2] = texts.getString("noResults");
			newRow[3] = texts.getString("noResults");
			model.addRow(newRow);
		}

	}

	protected void disableTooltips() {
		if (tooltips) {
			tooltips = false;
			mntmTooltips.setText(texts.getString("mntmTooltipEnable"));
			String mnemonic = texts.getString("mnemomntmTooltipEnable");
			mntmTooltips.setMnemonic(mnemonic.charAt(0));
			btnDiscount0.setToolTipText(null);
			btnDiscount1.setToolTipText(null);
			tbAll.setToolTipText(null);
			tbSearch.setToolTipText(null);
		}
	}

	protected void enableTooltips() {
		if (!tooltips) {
			tooltips = true;
			mntmTooltips.setText(texts.getString("mntmTooltipDisable"));
			String mnemonic = texts.getString("mnemomntmTooltipDisable");
			mntmTooltips.setMnemonic(mnemonic.charAt(0));
			btnDiscount0.setToolTipText(texts.getString("click"));
			btnDiscount1.setToolTipText(texts.getString("click"));
			tbAll.setToolTipText(texts.getString("click"));
		}
	}

	private JMenuItem getMntmTooltips() {
		if (mntmTooltips == null) {
			mntmTooltips = new JMenuItem(texts.getString("mntmTooltipDisable"));
			String mnemonic = texts.getString("mnemomntmTooltipDisable");
			mntmTooltips.setMnemonic(mnemonic.charAt(0));
			mntmTooltips.setSelected(true);
			mntmTooltips.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (mntmTooltips.isSelected()) {
						disableTooltips();
						mntmTooltips.setSelected(false);
					} else {
						enableTooltips();
						mntmTooltips.setSelected(true);
					}
				}
			});

		}
		return mntmTooltips;
	}

	private void loadHelp(Locale locale) {

		URL hsURL;
		HelpSet hs;

		try {
			File file = null;
			if (locale.getLanguage() == "en") {
				file = new File("help/en/HelpSet.hs");
			}
			if (locale.getLanguage() == "es") {
				file = new File("help/es/HelpSet.hs");
			}
			hsURL = file.toURI().toURL();
			hs = new HelpSet(null, hsURL);
			hb = hs.createHelpBroker();
			hb.initPresentation();

			hb.enableHelpKey(getRootPane(), "welcome", hs);
			hb.enableHelpOnButton(mntmHelpSupport, "welcome", hs);
			hb.enableHelp(pnDiscounts, "choose", hs);
			hb.enableHelp(pnSearchFilter, "search", hs);
			hb.enableHelp(tbAll, "choose", hs);
		}

		catch (Exception e) {
			JOptionPane.showMessageDialog(this, texts.getString("noHelp"), texts.getString("noHelpTitle"),
					JOptionPane.ERROR_MESSAGE);
		}

	}

	private JMenuItem getMntmHelpSupport() {
		if (mntmHelpSupport == null) {
			mntmHelpSupport = new JMenuItem(texts.getString("mntmHelpSupport"));
			String mnemonic = texts.getString("mnemomntmHelpSupport");
			mntmHelpSupport.setMnemonic(mnemonic.charAt(0));
			mntmHelpSupport.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		}
		return mntmHelpSupport;
	}

	private void btnDiscount0Click() {
		btnShowDiscounts.setEnabled(true);
		btnDiscount0.setBorder(new LineBorder(new Color(52, 52, 52), 5));
		btnDiscount0.setBorderPainted(true);
		btnShowDiscounts.setActionCommand(discounted.get(0).getCode());
		btnDiscount1.setBorderPainted(false);
	}

	private void btnDiscount1Click() {
		btnShowDiscounts.setEnabled(true);
		btnDiscount1.setBorder(new LineBorder(new Color(52, 52, 52), 5));
		btnDiscount1.setBorderPainted(true);
		btnShowDiscounts.setActionCommand(discounted.get(1).getCode());
		btnDiscount0.setBorderPainted(false);
	}

	public void restart() {
		tpCruises.setSelectedIndex(0);
		NonEditableTable model = (NonEditableTable) tbSearch.getModel();
		model.getDataVector().clear();
		model.fireTableDataChanged();
		agency = new Agency();
		discounted = agency.getDiscountedCruises();
		allCruises = agency.getCruises();
		showDiscounts();
		btnDiscount0.setSelected(false);
		btnDiscount1.setSelected(false);
		btnDiscount0.setBorderPainted(false);
		btnDiscount1.setBorderPainted(false);
		btnShowDiscounts.setEnabled(false);
		enableTooltips();
	}

	private Cruise getCruiseTbSearch(int row) {
		String name = (String) tbSearch.getValueAt(row, 0);
		Cruise cruise = agency.searchCruisesName(name);
		return cruise;
	}

	private Cruise getCruiseTbAll(int row) {
		String name = (String) tbAll.getValueAt(row, 0);
		Cruise cruise = agency.searchCruisesName(name);
		return cruise;
	}

	private JLabel getLblArea() {
		if (lblArea == null) {
			lblArea = new JLabel(texts.getString("area"));
			lblArea.setLabelFor(getCbArea());
			lblArea.setFont(new Font("Imprint MT Shadow", Font.BOLD, 21));
			String mnemonic = texts.getString("mnemoArea");
			lblArea.setDisplayedMnemonic(mnemonic.charAt(0));
		}
		return lblArea;
	}

	private JComboBox<String> getCbArea() {
		if (cbArea == null) {
			cbArea = new JComboBox<String>();
			String[] options = agency.getAvailableAreas();
			cbArea.setModel(new DefaultComboBoxModel<String>(options));
			((JLabel) cbArea.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		}
		return cbArea;
	}

	private void exit() {
		deleteBookedCabinsFile();
		System.exit(0);
	}
}
