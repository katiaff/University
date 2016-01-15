package gui.dialogs;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.toedter.calendar.JDateChooser;

import gui.MainWindow;
import logic.implementation.Agency;
import logic.implementation.BookedCabin;
import logic.tripelements.Cruise;
import logic.tripelements.Extra;
import logic.tripelements.Ship;
import logic.util.FileDataCorruptException;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class BookingDialog extends JDialog {
	public final static int MAX_AGE_TRAVELER = 110;
	public final static int MAX_AGE_EXTRA_BED = 16;
	private static final long serialVersionUID = 1L;
	private Locale locale;
	private Agency agency;
	private List<Extra> extras;
	private ResourceBundle texts;
	private JSplitPane splitPane;
	private JSplitPane splitPaneList;
	private JTextArea taCabinInfo;
	private JList<BookedCabin> listCabin;
	private DateFormat dateFormat;
	private Ship ship;
	int[] numCabins;
	private JDateChooser dateAge1;
	private JDateChooser dateAge2;
	private JDateChooser dateAge3;
	private JDateChooser dateAge4;
	private JSpinner spPeople;
	private JDateChooser dateAgeExtraBed;
	private JToggleButton tglbtnExtraBed;
	private JLabel lblAgeExtraBed;
	private JComboBox<String> cbCabins;
	private JLabel lblAge1;
	private JLabel lblAge2;
	private JLabel lblAge3;
	private JLabel lblAge4;
	private JSeparator spTotal;
	private JLabel lblRoomTotal;
	private JLabel lblExtraTotal;
	private JTextField txRoomTotal;
	private JTextField txExtraTotal;
	private JPanel pnExtras;
	private JLabel lblCruise;
	private DefaultListModel<BookedCabin> listModel = new DefaultListModel<BookedCabin>();
	private JPanel pnList;
	private JButton btnRemove;
	private JPanel pnButtons;
	private JButton btnModify;
	private JButton btnSave;
	private JLabel lblPrice;
	public Cruise cruise;
	private ClickCheckBox clickCheckBox = new ClickCheckBox();
	private JButton btnLoad;
	private JButton btnProceed;
	private JLabel lblDeparture;
	private JComboBox<String> cbDeparture;
	private JSeparator spDate;
	private JDialog dialog;
	private Currency currency;
	private String currencySymbol;
	private boolean tooltips;
	private JButton btnBook;
	private JButton btnCancel;
	private int minimumAge;
	private JLabel lblExtraBed;
	private HelpBroker hb;
	private JButton btnHelp;
	private JLabel lblCabin;
	private JLabel lblPeople;
	private JLabel lblExtras;
	private MainWindow mw;
	private JLabel lblTotal;
	private JTextField txTotal;
	private boolean validBooking = true;

	/**
	 * Create the dialog.
	 * 
	 * @param agency
	 * @param cruise
	 * @param locale
	 * @param tooltips
	 * @param hb
	 */
	public BookingDialog(Locale locale, Agency agency, boolean tooltips, HelpBroker hb, MainWindow mw) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				btnCancel.doClick();
			}
		});
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setModal(true);
		setBounds(100, 100, 1375, 768);
		setLocationRelativeTo(null);
		this.mw = mw;
		this.locale = locale;
		texts = ResourceBundle.getBundle("resources.BookingDialog", this.locale);
		this.currency = Currency.getInstance(locale);
		this.currencySymbol = currency.getSymbol();
		dateFormat = DateFormat.getDateInstance(DateFormat.LONG, locale);
		this.agency = agency;
		this.cruise = agency.getCurrentCruise();
		extras = agency.getExtras();
		this.ship = Agency.searchShips(cruise.getShip());
		dialog = this;
		this.numCabins = ship.getCabins();
		this.tooltips = tooltips;
		this.hb = hb;
		if (cruise.getChildren()) {
			minimumAge = 0;
		} else {
			minimumAge = 18;
		}
		loadHelp();
		getContentPane().setLayout(new BorderLayout());
		setTitle(texts.getString("title"));
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnCancel = new JButton(texts.getString("btnCancel"));
				btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 14));
				String mnemonic = texts.getString("mnemoBtnCancel");
				btnCancel.setMnemonic(mnemonic.charAt(0));
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						int answer = JOptionPane.showConfirmDialog(dialog, texts.getString("cancelMsg"),
								texts.getString("btnCancel"), JOptionPane.YES_NO_OPTION);
						if (answer == JOptionPane.YES_OPTION) {
							deleteClientInfo();
							dispose();
						}
					}
				});
				{
					btnProceed = new JButton(texts.getString("btnProceed"));
					btnProceed.setFont(new Font("Tahoma", Font.PLAIN, 14));
					mnemonic = texts.getString("mnemoBtnProceed");
					btnProceed.setMnemonic(mnemonic.charAt(0));
					btnProceed.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (!listModel.isEmpty()) {
								if (validBooking) {
									if (checkAdult()) {
										int answer = JOptionPane.showConfirmDialog(dialog,
												texts.getString("proceedMsg"), texts.getString("btnProceed"),
												JOptionPane.YES_NO_OPTION);
										if (answer == JOptionPane.YES_OPTION) {
											proceed(locale, agency);
										}
									} else {
										JOptionPane.showMessageDialog(dialog, texts.getString("noAdult"),
												texts.getString("noAdultTitle"), JOptionPane.ERROR_MESSAGE);
									}
								} else {
									JOptionPane.showMessageDialog(dialog, texts.getString("notValidBooking"),
											texts.getString("notValidBookingTitle"), JOptionPane.ERROR_MESSAGE);

								}
							} else {
								JOptionPane.showMessageDialog(dialog, texts.getString("btnProceedError"));
							}
						}

						private void proceed(Locale locale, Agency agency) {
							saveList();
							agency.setDeparture((String) cbDeparture.getSelectedItem());
							showPaymentDialog(locale, cruise);
						}
					});
					buttonPane.add(btnProceed);
				}
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
			}
			{
				btnHelp = new JButton(texts.getString("btnHelp"));
				btnHelp.setFont(new Font("Tahoma", Font.PLAIN, 14));
				btnHelp.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						clickF1();
					}
				});
				buttonPane.add(btnHelp);
			}
		}
		{
			splitPane = new JSplitPane();
			splitPane.setOneTouchExpandable(true);
			splitPane.setMinimumSize(new Dimension(0, 0));
			getContentPane().add(splitPane, BorderLayout.CENTER);
			{
				JPanel pnBook = new JPanel();
				JScrollPane spBook = new JScrollPane();
				spBook.setViewportView(pnBook);
				splitPane.setLeftComponent(spBook);
				spBook.setBorder(new CompoundBorder(
						new TitledBorder(new LineBorder(new Color(52, 52, 52), 5), texts.getString("borderBooking")),
						new EmptyBorder(10, 10, 10, 5)));

				GridBagLayout gbl_pnBook = new GridBagLayout();
				gbl_pnBook.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0 };
				gbl_pnBook.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
						0 };
				gbl_pnBook.columnWeights = new double[] { 1.0, 0.0, 1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
				gbl_pnBook.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
						0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
				pnBook.setLayout(gbl_pnBook);
				{
					lblCruise = new JLabel(texts.getString("lblCruise") + cruise.getName());
					lblCruise.setFont(new Font("Serif", Font.PLAIN, 20));
					GridBagConstraints gbc_lblCruise = new GridBagConstraints();
					gbc_lblCruise.insets = new Insets(10, 10, 5, 5);
					gbc_lblCruise.gridx = 2;
					gbc_lblCruise.gridy = 0;
					pnBook.add(lblCruise, gbc_lblCruise);
				}
				{
					lblDeparture = new JLabel(texts.getString("lblDeparture"));
					lblDeparture.setFont(new Font("Serif", Font.PLAIN, 17));
					String mnemonic = texts.getString("mnemoLblDeparture");
					{
						txTotal = new JTextField();
						txTotal.setFont(new Font("Serif", Font.PLAIN, 17));
						txTotal.setEditable(false);
						GridBagConstraints gbc_txTotal = new GridBagConstraints();
						gbc_txTotal.fill = GridBagConstraints.HORIZONTAL;
						gbc_txTotal.insets = new Insets(10, 10, 5, 5);
						gbc_txTotal.gridx = 2;
						gbc_txTotal.gridy = 21;
						pnBook.add(txTotal, gbc_txTotal);
						txTotal.setColumns(10);
					}
					{
						lblTotal = new JLabel(texts.getString("absoluteTotal"));
						lblTotal.setFont(new Font("Serif", Font.PLAIN, 17));
						GridBagConstraints gbc_lblTotal = new GridBagConstraints();
						gbc_lblTotal.insets = new Insets(10, 10, 5, 5);
						gbc_lblTotal.gridx = 1;
						gbc_lblTotal.gridy = 21;
						pnBook.add(lblTotal, gbc_lblTotal);
					}
					lblDeparture.setDisplayedMnemonic(mnemonic.charAt(0));
					GridBagConstraints gbc_lblDeparture = new GridBagConstraints();
					gbc_lblDeparture.insets = new Insets(10, 10, 5, 5);
					gbc_lblDeparture.gridx = 1;
					gbc_lblDeparture.gridy = 1;
					pnBook.add(lblDeparture, gbc_lblDeparture);
				}
				cbDeparture = new JComboBox<String>();
				cbDeparture.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblDeparture.setLabelFor(cbDeparture);
				loadDateDeparture();

				GridBagConstraints gbc_cbDeparture = new GridBagConstraints();
				gbc_cbDeparture.insets = new Insets(10, 10, 5, 5);
				gbc_cbDeparture.fill = GridBagConstraints.HORIZONTAL;
				gbc_cbDeparture.gridx = 2;
				gbc_cbDeparture.gridy = 1;
				pnBook.add(cbDeparture, gbc_cbDeparture);
				{
					spDate = new JSeparator();
					GridBagConstraints gbc_spDate = new GridBagConstraints();
					gbc_spDate.gridwidth = 6;
					gbc_spDate.fill = GridBagConstraints.HORIZONTAL;
					gbc_spDate.insets = new Insets(10, 10, 5, 0);
					gbc_spDate.gridx = 0;
					gbc_spDate.gridy = 2;
					pnBook.add(spDate, gbc_spDate);
				}
				{
					lblCabin = new JLabel(texts.getString("lblCabin"));
					lblCabin.setFont(new Font("Serif", Font.PLAIN, 17));
					String mnemonic = texts.getString("mnemoLblCabin");
					lblCabin.setDisplayedMnemonic(mnemonic.charAt(0));
					GridBagConstraints gbc_lblCabin = new GridBagConstraints();
					gbc_lblCabin.insets = new Insets(20, 10, 5, 5);
					gbc_lblCabin.gridx = 1;
					gbc_lblCabin.gridy = 3;
					pnBook.add(lblCabin, gbc_lblCabin);
				}
				{
					{
						lblPeople = new JLabel(texts.getString("lblPeople"));
						lblPeople.setFont(new Font("Serif", Font.PLAIN, 17));
						String mnemonic = texts.getString("mnemoLblNumPeople");
						lblPeople.setDisplayedMnemonic(mnemonic.charAt(0));
						GridBagConstraints gbc_lblPeople = new GridBagConstraints();
						gbc_lblPeople.insets = new Insets(10, 10, 5, 5);
						gbc_lblPeople.gridx = 1;
						gbc_lblPeople.gridy = 4;
						pnBook.add(lblPeople, gbc_lblPeople);
					}
				}
				{
					tglbtnExtraBed = new JToggleButton(texts.getString("toggleExtraBedNo"));
					tglbtnExtraBed.setFont(new Font("Tahoma", Font.PLAIN, 14));
					tglbtnExtraBed.setSelected(false);
					tglbtnExtraBed.addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent arg0) {
							clickToggle();
						}
					});

					GridBagConstraints gbc_tglbtnExtrabed = new GridBagConstraints();
					gbc_tglbtnExtrabed.insets = new Insets(10, 10, 5, 5);
					gbc_tglbtnExtrabed.gridx = 2;
					gbc_tglbtnExtrabed.gridy = 9;
					pnBook.add(tglbtnExtraBed, gbc_tglbtnExtrabed);
				}
				spPeople = new JSpinner();
				spPeople.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblPeople.setLabelFor(spPeople);
				spPeople.setModel(new SpinnerNumberModel(1, 1, 5, 1));
				spPeople.addChangeListener(new ChangeListener() {
					public void stateChanged(ChangeEvent arg0) {
						changeSpinner();
					}
				});
				GridBagConstraints gbc_spPeople = new GridBagConstraints();
				gbc_spPeople.insets = new Insets(10, 10, 5, 5);
				gbc_spPeople.gridx = 2;
				gbc_spPeople.gridy = 4;
				pnBook.add(spPeople, gbc_spPeople);
				{
					cbCabins = new JComboBox<String>();
					cbCabins.setFont(new Font("Tahoma", Font.PLAIN, 14));
					lblCabin.setLabelFor(cbCabins);
					loadCbCabins(cbCabins);
					{
						lblPrice = new JLabel(texts.getString("lblPrice") + String.valueOf(ship.getPriceCabins()[0])
								+ " " + currencySymbol);
						lblPrice.setFont(new Font("Serif", Font.PLAIN, 17));
						GridBagConstraints gbc_lblPrice = new GridBagConstraints();
						gbc_lblPrice.insets = new Insets(20, 10, 5, 5);
						gbc_lblPrice.gridx = 3;
						gbc_lblPrice.gridy = 3;
						pnBook.add(lblPrice, gbc_lblPrice);
					}
					cbCabins.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							writePrice();
						}
					});

					GridBagConstraints gbc_comboBox = new GridBagConstraints();
					gbc_comboBox.insets = new Insets(20, 10, 5, 5);
					gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
					gbc_comboBox.gridx = 2;
					gbc_comboBox.gridy = 3;
					pnBook.add(cbCabins, gbc_comboBox);
				}
				{
					lblAge1 = new JLabel(texts.getString("lblAge1"));
					lblAge1.setFont(new Font("Serif", Font.PLAIN, 17));
					String mnemonic = texts.getString("mnemoLblAge1");
					lblAge1.setDisplayedMnemonic(mnemonic.charAt(0));
					lblAge1.setVisible(true);
					GridBagConstraints gbc_lblAge1 = new GridBagConstraints();
					gbc_lblAge1.insets = new Insets(10, 10, 5, 5);
					gbc_lblAge1.gridx = 1;
					gbc_lblAge1.gridy = 5;
					pnBook.add(lblAge1, gbc_lblAge1);
				}
				{
					dateAge1 = new JDateChooser(new Date(System.currentTimeMillis()), dateFormat.toString());
					dateAge1.setFont(new Font("Tahoma", Font.PLAIN, 14));
					lblAge1.setLabelFor(dateAge1);
					setDateProperties(dateAge1);
					GridBagConstraints gbc_spAge1 = new GridBagConstraints();
					gbc_spAge1.insets = new Insets(10, 10, 5, 5);
					gbc_spAge1.gridx = 2;
					gbc_spAge1.gridy = 5;
					pnBook.add(dateAge1, gbc_spAge1);
				}
				{
					lblAge2 = new JLabel(texts.getString("lblAge2"));
					lblAge2.setFont(new Font("Serif", Font.PLAIN, 17));
					String mnemonic = texts.getString("mnemoLblAge2");
					lblAge2.setDisplayedMnemonic(mnemonic.charAt(0));
					lblAge2.setVisible(false);
					GridBagConstraints gbc_lblAge2 = new GridBagConstraints();
					gbc_lblAge2.insets = new Insets(10, 10, 5, 5);
					gbc_lblAge2.gridx = 1;
					gbc_lblAge2.gridy = 6;
					pnBook.add(lblAge2, gbc_lblAge2);
				}
				{
					dateAge2 = new JDateChooser(new Date(System.currentTimeMillis()), dateFormat.toString());
					dateAge2.setFont(new Font("Tahoma", Font.PLAIN, 14));
					lblAge2.setLabelFor(dateAge2);
					setDateProperties(dateAge2);
					GridBagConstraints gbc_spAge2 = new GridBagConstraints();
					gbc_spAge2.insets = new Insets(10, 10, 5, 5);
					gbc_spAge2.gridx = 2;
					gbc_spAge2.gridy = 6;
					pnBook.add(dateAge2, gbc_spAge2);
				}
				{
					lblAge3 = new JLabel(texts.getString("lblAge3"));
					lblAge3.setFont(new Font("Serif", Font.PLAIN, 17));
					String mnemonic = texts.getString("mnemoLblAge3");
					lblAge3.setDisplayedMnemonic(mnemonic.charAt(0));
					lblAge3.setVisible(false);
					GridBagConstraints gbc_lblAge3 = new GridBagConstraints();
					gbc_lblAge3.insets = new Insets(10, 10, 5, 5);
					gbc_lblAge3.gridx = 1;
					gbc_lblAge3.gridy = 7;
					pnBook.add(lblAge3, gbc_lblAge3);
				}
				{
					dateAge3 = new JDateChooser(new Date(System.currentTimeMillis()), dateFormat.toString());
					dateAge3.setFont(new Font("Tahoma", Font.PLAIN, 14));
					lblAge3.setLabelFor(dateAge3);
					setDateProperties(dateAge3);
					GridBagConstraints gbc_spAge3 = new GridBagConstraints();
					gbc_spAge3.insets = new Insets(10, 10, 5, 5);
					gbc_spAge3.gridx = 2;
					gbc_spAge3.gridy = 7;
					pnBook.add(dateAge3, gbc_spAge3);
				}
				{
					lblAge4 = new JLabel(texts.getString("lblAge4"));
					lblAge4.setFont(new Font("Serif", Font.PLAIN, 17));
					String mnemonic = texts.getString("mnemoLblAge4");
					lblAge4.setDisplayedMnemonic(mnemonic.charAt(0));
					lblAge4.setVisible(false);
					GridBagConstraints gbc_lblAge4 = new GridBagConstraints();
					gbc_lblAge4.insets = new Insets(10, 10, 5, 5);
					gbc_lblAge4.gridx = 1;
					gbc_lblAge4.gridy = 8;
					pnBook.add(lblAge4, gbc_lblAge4);
				}
				{
					dateAge4 = new JDateChooser(new Date(System.currentTimeMillis()), dateFormat.toString());
					dateAge4.setFont(new Font("Tahoma", Font.PLAIN, 14));
					lblAge4.setLabelFor(dateAge4);
					setDateProperties(dateAge4);
					GridBagConstraints gbc_spAge4 = new GridBagConstraints();
					gbc_spAge4.insets = new Insets(10, 10, 5, 5);
					gbc_spAge4.gridx = 2;
					gbc_spAge4.gridy = 8;
					pnBook.add(dateAge4, gbc_spAge4);
				}
				{
					lblExtraBed = new JLabel(texts.getString("lblExtraBed"));
					lblExtraBed.setFont(new Font("Serif", Font.PLAIN, 17));
					lblExtraBed.setLabelFor(tglbtnExtraBed);
					String mnemonic = texts.getString("mnemoLblExtraBed");
					lblExtraBed.setDisplayedMnemonic(mnemonic.charAt(0));
					if (!cruise.getChildren()) {
						lblExtraBed.setVisible(false);
						tglbtnExtraBed.setVisible(false);
					}
					GridBagConstraints gbc_lblExtraBed = new GridBagConstraints();
					gbc_lblExtraBed.insets = new Insets(10, 10, 5, 5);
					gbc_lblExtraBed.gridx = 1;
					gbc_lblExtraBed.gridy = 9;
					pnBook.add(lblExtraBed, gbc_lblExtraBed);
				}
				{
				}
				{
					lblAgeExtraBed = new JLabel(texts.getString("lblAgeExtraBed"));
					lblAgeExtraBed.setFont(new Font("Serif", Font.PLAIN, 17));
					lblAgeExtraBed.setVisible(false);
					GridBagConstraints gbc_lblAgeExtraBed = new GridBagConstraints();
					gbc_lblAgeExtraBed.insets = new Insets(10, 10, 5, 5);
					gbc_lblAgeExtraBed.gridx = 1;
					gbc_lblAgeExtraBed.gridy = 10;
					pnBook.add(lblAgeExtraBed, gbc_lblAgeExtraBed);
				}
				dateAgeExtraBed = new JDateChooser(new Date(System.currentTimeMillis()), dateFormat.toString());
				dateAgeExtraBed.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblAgeExtraBed.setLabelFor(dateAgeExtraBed);
				setDatePropertiesExtraBed(dateAgeExtraBed);
				dateAgeExtraBed.setVisible(false);
				GridBagConstraints gbc_dateAgeExtraBed = new GridBagConstraints();
				gbc_dateAgeExtraBed.insets = new Insets(10, 10, 5, 5);
				gbc_dateAgeExtraBed.gridx = 2;
				gbc_dateAgeExtraBed.gridy = 10;
				pnBook.add(dateAgeExtraBed, gbc_dateAgeExtraBed);
				{
					lblExtras = new JLabel(texts.getString("lblExtras"));
					lblExtras.setFont(new Font("Serif", Font.PLAIN, 17));
					String mnemonic = texts.getString("mnemoLblExtras");
					lblExtras.setDisplayedMnemonic(mnemonic.charAt(0));
					GridBagConstraints gbc_lblExtras = new GridBagConstraints();
					gbc_lblExtras.insets = new Insets(10, 10, 5, 5);
					gbc_lblExtras.gridx = 1;
					gbc_lblExtras.gridy = 11;
					pnBook.add(lblExtras, gbc_lblExtras);
				}
				{
					btnBook = new JButton(texts.getString("btnBook"));
					btnBook.setFont(new Font("Tahoma", Font.PLAIN, 14));
					String mnemonic = texts.getString("mnemoBtnBook");
					btnBook.setMnemonic(mnemonic.charAt(0));
					btnBook.addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent arg0) {
							if (cbCabins.getSelectedItem() != texts.getString("noOptions")) {
								bookRoom(agency);
							} else {
								JOptionPane.showMessageDialog(null, texts.getString("bookNoOptions"));
							}
						}
					});
					{
						pnExtras = new JPanel();
						lblExtras.setLabelFor(pnExtras);
						showExtras(pnExtras);
						pnExtras.setLayout(new GridLayout(extras.size() / 2, 1));
						GridBagConstraints gbc_pnExtras = new GridBagConstraints();
						gbc_pnExtras.insets = new Insets(10, 10, 5, 10);
						gbc_pnExtras.gridwidth = 4;
						gbc_pnExtras.gridheight = 5;
						gbc_pnExtras.fill = GridBagConstraints.BOTH;
						gbc_pnExtras.gridx = 2;
						gbc_pnExtras.gridy = 11;
						pnBook.add(pnExtras, gbc_pnExtras);
					}
					GridBagConstraints gbc_btnBook = new GridBagConstraints();
					gbc_btnBook.insets = new Insets(0, 0, 5, 5);
					gbc_btnBook.gridx = 3;
					gbc_btnBook.gridy = 20;
					pnBook.add(btnBook, gbc_btnBook);
				}
				{
					spTotal = new JSeparator();
					GridBagConstraints gbc_spTotal = new GridBagConstraints();
					gbc_spTotal.gridwidth = 6;
					gbc_spTotal.fill = GridBagConstraints.HORIZONTAL;
					gbc_spTotal.insets = new Insets(10, 10, 10, 10);
					gbc_spTotal.gridx = 0;
					gbc_spTotal.gridy = 18;
					pnBook.add(spTotal, gbc_spTotal);
				}
				{
					lblRoomTotal = new JLabel(texts.getString("lblRoomTotal"));
					lblRoomTotal.setFont(new Font("Serif", Font.PLAIN, 17));
					GridBagConstraints gbc_lblRoomTotal = new GridBagConstraints();
					gbc_lblRoomTotal.insets = new Insets(20, 10, 5, 5);
					gbc_lblRoomTotal.gridx = 1;
					gbc_lblRoomTotal.gridy = 19;
					pnBook.add(lblRoomTotal, gbc_lblRoomTotal);
				}
				{
					txRoomTotal = new JTextField();
					txRoomTotal.setFont(new Font("Serif", Font.PLAIN, 17));
					txRoomTotal.setFocusable(false);
					txRoomTotal.setEditable(false);
					GridBagConstraints gbc_txRoomTotal = new GridBagConstraints();
					gbc_txRoomTotal.fill = GridBagConstraints.HORIZONTAL;
					gbc_txRoomTotal.insets = new Insets(20, 10, 5, 5);
					gbc_txRoomTotal.gridx = 2;
					gbc_txRoomTotal.gridy = 19;
					pnBook.add(txRoomTotal, gbc_txRoomTotal);
					txRoomTotal.setColumns(10);
				}
				{
					lblExtraTotal = new JLabel(texts.getString("lblExtraTotal"));
					lblExtraTotal.setFont(new Font("Serif", Font.PLAIN, 17));
					GridBagConstraints gbc_lblExtraTotal = new GridBagConstraints();
					gbc_lblExtraTotal.insets = new Insets(10, 10, 5, 5);
					gbc_lblExtraTotal.gridx = 1;
					gbc_lblExtraTotal.gridy = 20;
					pnBook.add(lblExtraTotal, gbc_lblExtraTotal);
				}
				{
					txExtraTotal = new JTextField();
					txExtraTotal.setFont(new Font("Serif", Font.PLAIN, 17));
					txExtraTotal.setFocusable(false);
					txExtraTotal.setEditable(false);
					writePrice();
					GridBagConstraints gbc_txExtraTotal = new GridBagConstraints();
					gbc_txExtraTotal.fill = GridBagConstraints.HORIZONTAL;
					gbc_txExtraTotal.insets = new Insets(10, 10, 5, 5);
					gbc_txExtraTotal.gridx = 2;
					gbc_txExtraTotal.gridy = 20;
					pnBook.add(txExtraTotal, gbc_txExtraTotal);
					txExtraTotal.setColumns(10);
				}
			}

			{
				JScrollPane spCabin = new JScrollPane();
				splitPane.setRightComponent(spCabin);
				{
					splitPaneList = new JSplitPane();
					splitPaneList.setOneTouchExpandable(true);
					splitPaneList.setMinimumSize(new Dimension(0, 0));
					splitPaneList.setOrientation(JSplitPane.VERTICAL_SPLIT);
					spCabin.setViewportView(splitPaneList);
					{
						JScrollPane spCabinInfo = new JScrollPane();
						taCabinInfo = new JTextArea();
						taCabinInfo.setFont(new Font("Serif", Font.PLAIN, 17));
						taCabinInfo.setWrapStyleWord(true);
						taCabinInfo.setLineWrap(true);
						taCabinInfo.setBackground(null);
						taCabinInfo
								.setBorder(
										new CompoundBorder(
												new TitledBorder(new LineBorder(new Color(52, 52, 52), 5),
														texts.getString("borderTextArea")),
												new EmptyBorder(10, 10, 10, 5)));
						taCabinInfo.setEditable(false);
						spCabinInfo.setViewportView(taCabinInfo);
						splitPaneList.setBottomComponent(spCabinInfo);
					}
					{
						pnList = new JPanel();
						splitPaneList.setLeftComponent(pnList);
						pnList.setLayout(new BorderLayout(0, 0));
						{
							JScrollPane spList = new JScrollPane();
							listCabin = new JList<BookedCabin>(listModel);
							listCabin.setFont(new Font("Serif", Font.PLAIN, 17));
							listCabin.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseClicked(MouseEvent arg0) {
									if (arg0.getClickCount() == 2) {
										btnModify.doClick();
									}
									setCabinInfo();
								}
							});
							listCabin.setToolTipText(texts.getString("listCabinTooltip"));
							listCabin.setBackground(null);
							listCabin
									.setBorder(
											new CompoundBorder(
													new TitledBorder(new LineBorder(new Color(52, 52, 52), 5),
															texts.getString("borderList")),
													new EmptyBorder(10, 10, 10, 5)));
							spList.setViewportView(listCabin);
							pnList.add(spList, BorderLayout.CENTER);
						}

						{
							pnButtons = new JPanel();
							pnList.add(pnButtons, BorderLayout.SOUTH);
							{
								btnModify = new JButton(texts.getString("btnModify"));
								btnModify.setFont(new Font("Tahoma", Font.PLAIN, 14));
								if (listModel.isEmpty()) {
									btnModify.setVisible(false);
								}
								btnModify.setToolTipText(texts.getString("btnModifyTooltip"));
								String mnemonic = texts.getString("mnemoBtnModify");
								btnModify.setMnemonic(mnemonic.charAt(0));
								btnModify.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent arg0) {
										if (listCabin.getSelectedIndex() != -1) {
											int answer = JOptionPane.showConfirmDialog(dialog,
													texts.getString("modifyMsg"), texts.getString("btnModify"),
													JOptionPane.YES_NO_OPTION);
											if (answer == JOptionPane.YES_OPTION) {
												loadCabin();
											}
										}
									}
								});
								{
									btnSave = new JButton(texts.getString("btnSave"));
									btnSave.setFont(new Font("Tahoma", Font.PLAIN, 14));
									mnemonic = texts.getString("mnemoBtnSave");
									btnSave.setMnemonic(mnemonic.charAt(0));
									btnSave.addActionListener(new ActionListener() {
										public void actionPerformed(ActionEvent arg0) {
											saveList();
										}
									});
									btnSave.setToolTipText(texts.getString("btnSaveTooltip"));
									if (listModel.isEmpty()) {
										btnSave.setVisible(false);
									}
									pnButtons.add(btnSave);
								}
								pnButtons.add(btnModify);

								btnRemove = new JButton(texts.getString("btnRemove"));
								btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 14));
								mnemonic = texts.getString("mnemoBtnRemove");
								btnRemove.setMnemonic(mnemonic.charAt(0));
								{
									if (listModel.isEmpty()) {
										btnRemove.setVisible(false);
									}
									btnRemove.addActionListener(new ActionListener() {
										public void actionPerformed(ActionEvent arg0) {
											if (listCabin.getSelectedIndex() != -1) {
												int answer = JOptionPane.showConfirmDialog(dialog,
														texts.getString("removeMsg"), texts.getString("btnRemove"),
														JOptionPane.YES_NO_OPTION);
												if (answer == JOptionPane.YES_OPTION) {
													removeCabin(agency);
												}
											}
										}

									});
								}
								pnButtons.add(btnRemove);
							}
							{
								btnLoad = new JButton(texts.getString("btnLoad"));
								btnLoad.setFont(new Font("Tahoma", Font.PLAIN, 14));
								String mnemonic = texts.getString("mnemoBtnLoad");
								btnLoad.setMnemonic(mnemonic.charAt(0));
								btnLoad.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent arg0) {
										try {
											checkForLoadList(locale, agency);
										}

										catch (FileNotFoundException e1) {
											JOptionPane.showMessageDialog(dialog,
													texts.getString("fileNotFoundException"),
													texts.getString("fileNotFoundExceptionTitle"),
													JOptionPane.ERROR_MESSAGE);
										} catch (IOException e2) {
											JOptionPane.showMessageDialog(dialog, texts.getString("IOException"),
													texts.getString("IOExceptionTitle"), JOptionPane.ERROR_MESSAGE);
										}
									}
								});
								pnButtons.add(btnLoad);
							}

						}
					}
				}
			}

		}
		if (tooltips) {
			enableTooltips();
		} else {
			disableTooltips();
		}
		showDateChoosers((int) spPeople.getValue());
	}

	public void deleteClientInfo() {
		File file = new File("files/client.dat");
		if (file.exists()) {
			file.delete();
		}
	}

	private void loadHelp() {
		HelpSet hs;
		try {

			hs = hb.getHelpSet();
			hb.initPresentation();

			hb.enableHelpKey(getRootPane(), "booking", hs);
			hb.enableHelp(splitPane, "booking", hs);
			hb.enableHelp(splitPaneList, "booking", hs);

		}

		catch (Exception e) {

		}

	}

	private void clickF1() {
		try {
			Robot robot = new Robot();
			// Simulate a key press
			robot.keyPress(KeyEvent.VK_F1);
		} catch (AWTException e) {
			JOptionPane.showMessageDialog(this, texts.getString("noHelp"), texts.getString("noHelpTitle"),
					JOptionPane.ERROR_MESSAGE);
		}
	}

	protected boolean checkAdult() {
		for (int i = 0; i < listModel.size(); i++) {
			BookedCabin bc = listModel.getElementAt(i);
			if (bc.isAdult()) {
				return true;
			}
		}
		return false;
	}

	private void disableTooltips() {
		btnBook.setToolTipText(null);
		btnRemove.setToolTipText(null);
		btnModify.setToolTipText(null);
		btnSave.setToolTipText(null);
		btnLoad.setToolTipText(null);
		btnCancel.setToolTipText(null);
		btnProceed.setToolTipText(null);
	}

	private void enableTooltips() {
		btnBook.setToolTipText(texts.getString("btnBookTooltip"));
		btnRemove.setToolTipText(texts.getString("btnRemoveTooltip"));
		btnModify.setToolTipText(texts.getString("btnModifyTooltip"));
		btnSave.setToolTipText(texts.getString("btnSaveTooltip"));
		btnLoad.setToolTipText(texts.getString("btnLoadTooltip"));
		btnCancel.setToolTipText(texts.getString("btnCancelTooltip"));
		btnProceed.setToolTipText(texts.getString("btnProceedTooltip"));
	}

	private void loadDateDeparture() {
		String[] options = agency.getAvailableDatesCruise();
		cbDeparture.setModel(new DefaultComboBoxModel<String>(options));
		DefaultListCellRenderer renderer = new DefaultListCellRenderer();
		renderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
		cbDeparture.setRenderer(renderer);

	}

	protected void loadList(Locale locale) {
		listModel.clear();
		List<BookedCabin> cabins = agency.restoreFile(locale);
		for (BookedCabin bc : cabins) {
			listModel.addElement(bc);
		}
		splitPaneList.resetToPreferredSizes();
		showButtons();

	}

	private void saveList() {
		List<BookedCabin> list = new ArrayList<BookedCabin>();
		for (int i = 0; i < listModel.size(); i++) {
			list.add(listModel.elementAt(i));
		}
		agency.setBookedCabins(list);
		if (agency.createFile(list) == 0) {
			JOptionPane.showMessageDialog(this, texts.getString("saveMsg"), texts.getString("btnSave"),
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void writePrice() {
		int priceCabin = 0;
		if (cbCabins.getSelectedItem().equals(texts.getString("interiorDoubleCabin"))) {
			priceCabin = ship.getPriceCabins()[0];
		}
		if (cbCabins.getSelectedItem().equals(texts.getString("exteriorDoubleCabin"))) {
			priceCabin = ship.getPriceCabins()[1];
		}
		if (cbCabins.getSelectedItem().equals(texts.getString("interiorFamilyCabin"))) {
			priceCabin = ship.getPriceCabins()[2];
		}
		if (cbCabins.getSelectedItem().equals(texts.getString("exteriorFamilyCabin"))) {
			priceCabin = ship.getPriceCabins()[3];
		}
		String text = texts.getString("lblPrice") + String.valueOf(priceCabin);
		lblPrice.setText(text + " " + currencySymbol);
		txRoomTotal.setText(String.valueOf(priceCabin) + " " + currencySymbol);
		int priceExtras = 0;
		if (!txExtraTotal.getText().isEmpty()) {
			String textExtras = txExtraTotal.getText().replace(currencySymbol, "").trim();
			priceExtras = Integer.parseInt(textExtras);
		}
		txTotal.setText(String.valueOf(priceCabin + priceExtras) + " " + currencySymbol);
	}

	private void updatePrice() {
		int priceExtras = 0;
		int indexRoom = cbCabins.getSelectedIndex();
		int priceRoom = ship.getPriceCabins()[indexRoom];
		for (Component comp : pnExtras.getComponents()) {
			JCheckBox check = (JCheckBox) comp;
			if (check.isSelected()) {
				Extra extra = getExtra(check);
				priceExtras += extra.getPrice();
			}
		}
		txRoomTotal.setText(String.valueOf(priceRoom) + " " + currencySymbol);
		txExtraTotal.setText(String.valueOf(priceExtras) + " " + currencySymbol);
		txTotal.setText(String.valueOf(priceRoom + priceExtras) + " " + currencySymbol);

	}

	private Extra getExtra(JCheckBox check) {
		String text = check.getText();
		String withoutCurrency = text.replace(currencySymbol, "").trim();
		String name = withoutCurrency.split("-")[0];
		Extra extra = agency.searchExtras(name.trim());
		return extra;
	}

	private void showExtraBedDateChooser() {
		if (cruise.getChildren() && tglbtnExtraBed.isSelected()) {
			dateAgeExtraBed.setVisible(true);
			lblAgeExtraBed.setVisible(true);
		} else {
			dateAgeExtraBed.setVisible(false);
			lblAgeExtraBed.setVisible(false);
		}
	}

	private void showExtras(JPanel pnExtras) {
		for (Extra e : extras) {
			String name = e.getName();
			int price = e.getPrice();
			JCheckBox check = new JCheckBox(name + " - " + price + " " + currencySymbol);
			check.setFont(new Font("Serif", Font.PLAIN, 17));
			check.addActionListener(clickCheckBox);
			check.setActionCommand(name);
			pnExtras.add(check);
		}
		splitPane.resetToPreferredSizes();

	}

	class ClickEnter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent arg0) {
			if (arg0.equals(KeyEvent.VK_ENTER)) {
				JCheckBox check = (JCheckBox) arg0.getSource();
				check.setSelected(true);
			}
		}

	}

	private void showExtras(JPanel pnExtras, List<Extra> ext) {
		for (Extra e : extras) {
			String name = e.getName();
			int price = e.getPrice();
			JCheckBox check = new JCheckBox(name + " - " + price + " " + currencySymbol);
			check.setFont(new Font("Serif", Font.PLAIN, 17));
			check.addActionListener(clickCheckBox);
			if (ext.contains(e)) {
				check.setSelected(true);
			}
			check.setActionCommand(name);
			pnExtras.add(check);
		}
	}

	class ClickCheckBox implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JCheckBox check = (JCheckBox) e.getSource();
			Extra extra = getExtra(check);
			int price = extra.getPrice();
			int priceExtras = 0;
			String currentExtras = txExtraTotal.getText();
			if (!currentExtras.isEmpty()) {
				String withoutCurrency = currentExtras.replace(currencySymbol, "").trim();
				priceExtras = Integer.parseInt(withoutCurrency);
			}
			updateTxExtras(check, price, priceExtras);

			String totalText = txTotal.getText().replace(currencySymbol, "").trim();
			int currentTotal = Integer.parseInt(totalText);
			updateTxTotal(check, price, currentTotal);
		}

		private void updateTxTotal(JCheckBox check, int price, int currentTotal) {
			int newTotal;
			if (check.isSelected()) {
				newTotal = currentTotal + price;
			} else {
				newTotal = currentTotal - price;
			}
			txTotal.setText(String.valueOf(newTotal) + " " + currencySymbol);
		}

		private void updateTxExtras(JCheckBox check, int price, int priceExtras) {
			int newPrice = 0;
			if (check.isSelected()) {
				newPrice = priceExtras + price;
			} else {
				newPrice = priceExtras - price;
			}
			txExtraTotal.setText(String.valueOf(newPrice) + " " + currencySymbol);
		}

	}

	private void loadCbCabins(JComboBox<String> comboBox) {
		List<String> options = new ArrayList<String>();
		int num = (int) spPeople.getValue();
		if (tglbtnExtraBed.isSelected()) {
			num -= 1;
		}
		if (num < 3) {
			if (numCabins[0] != 0) {
				options.add(texts.getString("interiorDoubleCabin"));
			}
			if (numCabins[1] != 0) {
				options.add(texts.getString("exteriorDoubleCabin"));
			}
		}
		if (numCabins[2] != 0) {
			options.add(texts.getString("interiorFamilyCabin"));
		}
		if (numCabins[3] != 0) {
			options.add(texts.getString("exteriorFamilyCabin"));
		}
		if (options.isEmpty()) {
			options.add(texts.getString("noOptions"));
		}
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		for (String s : options) {
			model.addElement(s);
		}
		comboBox.setModel(model);
		DefaultListCellRenderer renderer = new DefaultListCellRenderer();
		renderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
		comboBox.setRenderer(renderer);
	}

	private void showDateChoosers(int num) {
		if (tglbtnExtraBed.isSelected()) {
			num -= 1;
		}
		if (num == 1 && lblAge1.isVisible()) {
			dateAge1.setVisible(true);
			dateAge2.setVisible(false);
			dateAge3.setVisible(false);
			dateAge4.setVisible(false);
		} else if (num == 2 && lblAge2.isVisible()) {
			dateAge1.setVisible(true);
			dateAge2.setVisible(true);
			dateAge3.setVisible(false);
			dateAge4.setVisible(false);
		} else if (num == 3 && lblAge3.isVisible()) {
			dateAge1.setVisible(true);
			dateAge2.setVisible(true);
			dateAge3.setVisible(true);
			dateAge4.setVisible(false);
		} else if (num >= 4 && lblAge4.isVisible()) {
			dateAge1.setVisible(true);
			dateAge2.setVisible(true);
			dateAge3.setVisible(true);
			dateAge4.setVisible(true);
		} else {
			dateAge1.setVisible(false);
			dateAge2.setVisible(false);
			dateAge3.setVisible(false);
			dateAge4.setVisible(false);
		}

	}

	private void setDateProperties(JDateChooser dateAge) {
		Calendar min = Calendar.getInstance();
		min.add(Calendar.YEAR, -MAX_AGE_TRAVELER);
		dateAge.setMinSelectableDate(new Date(min.getTimeInMillis()));
		Calendar max = Calendar.getInstance();
		max.add(Calendar.YEAR, -minimumAge);
		dateAge.setMaxSelectableDate(new Date(max.getTimeInMillis()));
		dateAge.setDate(new Date(max.getTimeInMillis()));
		dateAge.setLocale(locale);
	}

	private void setDatePropertiesExtraBed(JDateChooser dateAge) {
		// min = today - 16; max = today
		Calendar min = Calendar.getInstance();
		min.add(Calendar.YEAR, -MAX_AGE_EXTRA_BED);
		dateAge.setMinSelectableDate(new Date(min.getTimeInMillis()));
		dateAge.setMaxSelectableDate(new Date(System.currentTimeMillis()));
		dateAge.setToolTipText(texts.getString("dateExtraBedTooltip"));

	}

	private void changeSpinner() {
		int value = (int) spPeople.getValue();
		loadCbCabins(cbCabins);
		if (cbCabins.getItemAt(0).equals(texts.getString("noOptions"))) {
			disableEverything();
		} else {
			showLabels(value);
			showDateChoosers(value);
			if (tglbtnExtraBed.isSelected() && value == 1) {
				spPeople.setValue(2);
			}
			if (value == 5) {
				tglbtnExtraBed.setText(texts.getString("toggleExtraBedYes"));
				tglbtnExtraBed.setSelected(true);
				tglbtnExtraBed.setEnabled(false);
				showExtraBedDateChooser();
				if (tooltips) {
					tglbtnExtraBed.setToolTipText(texts.getString("extraBedTooltip"));
				}
			}
			if (value < 5) {
				tglbtnExtraBed.setEnabled(true);
				tglbtnExtraBed.setToolTipText(null);
			}
		}
	}

	private void showLabels(int value) {
		tglbtnExtraBed.setVisible(true);
		if (tglbtnExtraBed.isSelected()) {
			value -= 1;
		}
		if (value == 1) {
			lblPrice.setVisible(true);
			lblAge1.setVisible(true);
			dateAge1.setVisible(true);
			lblAge2.setVisible(false);
			lblAge3.setVisible(false);
			lblAge4.setVisible(false);
		}
		if (value == 2) {
			lblPrice.setVisible(true);
			lblAge1.setVisible(true);
			dateAge1.setVisible(true);
			lblAge2.setVisible(true);
			dateAge2.setVisible(true);
			lblAge3.setVisible(false);
			lblAge4.setVisible(false);
		}
		if (value == 3) {
			lblPrice.setVisible(true);
			lblAge1.setVisible(true);
			dateAge1.setVisible(true);
			lblAge2.setVisible(true);
			dateAge2.setVisible(true);
			lblAge3.setVisible(true);
			dateAge3.setVisible(true);
			lblAge4.setVisible(false);
		}
		if (value == 4) {
			lblPrice.setVisible(true);
			lblAge1.setVisible(true);
			dateAge1.setVisible(true);
			lblAge2.setVisible(true);
			dateAge2.setVisible(true);
			lblAge3.setVisible(true);
			dateAge3.setVisible(true);
			lblAge4.setVisible(true);
			dateAge4.setVisible(true);
		}
	}

	private void disableEverything() {
		tglbtnExtraBed.setVisible(false);
		lblPrice.setVisible(false);
		lblAge1.setVisible(false);
		dateAge1.setVisible(false);
		lblAge2.setVisible(false);
		dateAge2.setVisible(false);
		lblAge3.setVisible(false);
		dateAge3.setVisible(false);
		lblAge4.setVisible(false);
		dateAge4.setVisible(false);

	}

	private void clickToggle() {
		if (tglbtnExtraBed.isSelected()) {
			tglbtnExtraBed.setText(texts.getString("toggleExtraBedYes"));
		} else {
			tglbtnExtraBed.setText(texts.getString("toggleExtraBedNo"));
		}
		showExtraBedDateChooser();
		if (tglbtnExtraBed.isEnabled()) {
			spPeople.setValue((int) spPeople.getValue() + 1);
		}
	}

	private BookedCabin createBookedCabin(Agency agency) {
		List<Extra> selExtras = new ArrayList<Extra>();
		for (Component comp : pnExtras.getComponents()) {
			JCheckBox check = (JCheckBox) comp;
			if (check.isSelected()) {
				Extra e = getExtra(check);
				selExtras.add(e);
			}
		}
		Boolean[] booleans = new Boolean[] { lblAge1.isVisible(), lblAge2.isVisible(), lblAge3.isVisible(),
				lblAge4.isVisible(), lblAgeExtraBed.isVisible() };
		Date[] dates = new Date[] { dateAge1.getDate(), dateAge2.getDate(), dateAge3.getDate(), dateAge4.getDate(),
				dateAgeExtraBed.getDate() };
		BookedCabin bc = new BookedCabin((String) cbCabins.getSelectedItem(), (int) spPeople.getValue(), booleans,
				dates, selExtras, locale, cruise);
		return bc;
	}

	private void loadCabin() {
		BookedCabin bc = listCabin.getSelectedValue();
		cbCabins.setSelectedItem(bc.getTypeCabin());
		Boolean[] bools = bc.getBooleans();
		Date[] dates = bc.getDates();
		tglbtnExtraBed.setSelected(bools[4]);
		loadToggle();
		disableEverything();
		int numPeople = bc.getNumPeople();
		spPeople.setValue(numPeople);
		loadDates(dates);
		List<Extra> ext = bc.getExtras();
		pnExtras.removeAll();
		showDateChoosers((int) spPeople.getValue());
		showExtras(pnExtras, ext);
		updatePrice();
	}

	protected void loadCabin(int index) {
		BookedCabin bc = listModel.getElementAt(index);
		cbCabins.setSelectedItem(bc.getTypeCabin());
		Boolean[] bools = bc.getBooleans();
		Date[] dates = bc.getDates();
		tglbtnExtraBed.setSelected(bools[4]);
		loadToggle();
		disableEverything();
		int numPeople = bc.getNumPeople();
		spPeople.setValue(numPeople);
		loadDates(dates);
		showDateChoosers((int) spPeople.getValue());
		List<Extra> ext = bc.getExtras();
		pnExtras.removeAll();
		showExtras(pnExtras, ext);
	}

	private void loadDates(Date[] dates) {
		dateAge1.setDate(dates[0]);
		dateAge2.setDate(dates[1]);
		dateAge3.setDate(dates[2]);
		dateAge4.setDate(dates[3]);
		dateAgeExtraBed.setDate(dates[4]);
	}

	private void loadToggle() {
		if (tglbtnExtraBed.isSelected()) {
			tglbtnExtraBed.setText(texts.getString("toggleExtraBedYes"));
		} else {
			tglbtnExtraBed.setText(texts.getString("toggleExtraBedNo"));
		}
		showExtraBedDateChooser();
	}

	private void showButtons() {
		if (listModel.isEmpty()) {
			btnRemove.setVisible(false);
			btnModify.setVisible(false);
			btnSave.setVisible(false);
		} else {
			if (!btnRemove.isVisible()) {
				btnRemove.setVisible(true);
			}
			if (!btnModify.isVisible()) {
				btnModify.setVisible(true);
			}
			if (!btnSave.isVisible()) {
				btnSave.setVisible(true);
			}
		}
		btnLoad.setVisible(!btnSave.isVisible());
		splitPaneList.resetToPreferredSizes();
	}

	private void showPaymentDialog(Locale locale, Cruise cruise) {
		PaymentDialog pay = new PaymentDialog(locale, agency, tooltips, hb, mw);
		pay.loadBookingInfo();
		dispose();
		pay.setVisible(true);
	}

	private void setCabinInfo() {
		if (!listModel.isEmpty()) {
			try {
				BookedCabin cabin = listCabin.getSelectedValue();
				taCabinInfo.setText(cabin.description());
			} catch (FileDataCorruptException e) {
				JOptionPane.showMessageDialog(this, texts.getString("fileCorruptError"),
						texts.getString("fileCorruptErrorTitle"), JOptionPane.ERROR_MESSAGE);
				validBooking = false;
			}
		}
	}

	private void removeCabin(Agency agency) {
		List<BookedCabin> selected = listCabin.getSelectedValuesList();
		for (BookedCabin o : selected) {
			listModel.removeElement(o);
			agency.removeBookedCabin(o);
			splitPaneList.resetToPreferredSizes();
		}
		showButtons();
		taCabinInfo.setText("");
	}

	private void checkForLoadList(Locale locale, Agency agency) throws FileNotFoundException, IOException {
		Cruise bookedCabinCruise = agency.getBookedCabinsCurrentCruise();
		if (agency.getCurrentCruise().getCode().equals(bookedCabinCruise.getCode())) {
			if (!listModel.isEmpty()) {
				int answer = JOptionPane.showConfirmDialog(dialog, texts.getString("loadMsg"),
						texts.getString("btnLoad"), JOptionPane.YES_NO_OPTION);
				if (answer == JOptionPane.YES_OPTION) {
					loadList(locale);
				}
			} else {
				loadList(locale);
			}
		} else {
			JOptionPane.showMessageDialog(dialog, texts.getString("differentCruise"),
					texts.getString("differentCruiseTitle"), JOptionPane.ERROR_MESSAGE);
		}
	}

	private void resetData() {
		tglbtnExtraBed.setSelected(false);
		tglbtnExtraBed.setText(texts.getString("toggleExtraBedNo"));
		spPeople.setValue(1);
		showExtraBedDateChooser();
		showLabels((int) spPeople.getValue());
		showDateChoosers((int) spPeople.getValue());
		cbCabins.setSelectedIndex(0);
		deselectExtras();
		updatePrice();
	}

	protected void deselectExtras() {
		for (Component c : pnExtras.getComponents()) {
			JCheckBox check = (JCheckBox) c;
			check.setSelected(false);
		}

	}

	private void bookRoom(Agency agency) {
		updatePrice();
		BookedCabin bc = createBookedCabin(agency);
		listModel.addElement(bc);
		saveList(); // automatic when adding, non
					// automatic when deleting from
		agency.addBookedCabin(bc);
		validBooking = true;
		resetData();
		showButtons();
		splitPaneList.resetToPreferredSizes();
	}

}
