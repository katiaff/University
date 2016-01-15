package gui.dialogs;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import gui.MainWindow;
import logic.implementation.Agency;
import logic.implementation.Client;
import logic.tripelements.Cruise;
import logic.util.FileDataCorruptException;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PaymentDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static final int MIN_LENGTH = 8;
	public static final byte CASH = 0;
	public static final byte CARD = 1;
	private static final int MIN_MONTH = 1;
	private static final int MIN_YEAR = 16;
	private final JPanel contentPanel = new JPanel();
	private Locale locale;
	private ResourceBundle texts;
	private JTextField txtName;
	private JTextField txtSurname;
	private JTextField txtId;
	private JTextField txtPhone;
	private JTextArea taInfo;
	private Agency agency;
	private JDialog dialog;
	private NoNumbers noNumbers = new NoNumbers();
	private NoLetters noLetters = new NoLetters();
	private CVCTooMany tooMany = new CVCTooMany();
	private FewDigits fewDigits = new FewDigits();
	private JTextArea taAdditionalInfo;
	private boolean tooltips;
	private JButton btnCancel;
	private JButton btnProceed;
	private JButton btnModify;
	private HelpBroker hb;
	private JButton btnHelp;
	private JLabel lblName;
	private JLabel lblSurname;
	private JLabel lblID;
	private JLabel lblPhone;
	private JLabel lblInfo;
	private JLabel lblAdditionalInfo;
	private MainWindow mw;
	private JLabel lblPayment;
	private JPanel pnPayment;
	private JRadioButton rdbtnCash;
	private JRadioButton rdbtnCreditCard;
	private final ButtonGroup payment = new ButtonGroup();
	private JPanel pnCreditCardOptions;
	private JLabel lblCardNumber;
	private JLabel lblExpirationDate;
	private JLabel lblCvc;
	private JTextField txtCardNumber;
	private JTextField txtCvc;
	private JPanel pnExpiration;
	private JLabel lblMonth;
	private JSpinner spMonth;
	private JLabel lblYear;
	private JSpinner spYear;
	private JScrollPane scrollPane;
	private Client client;

	/**
	 * Create the dialog.
	 * 
	 * @param locale
	 * @param departure
	 * @param cruise
	 * @param agency
	 * @param objects
	 */
	public PaymentDialog(Locale locale, Agency agency, boolean tooltips, HelpBroker hb, MainWindow mw) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				btnCancel.doClick();
			}
		});
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setModal(true);
		setBounds(100, 100, 1024, 768);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		this.mw = mw;
		this.locale = locale;
		texts = ResourceBundle.getBundle("resources.PaymentDialog", this.locale);
		this.agency = agency;
		this.dialog = this;
		this.tooltips = tooltips;
		this.hb = hb;
		loadHelp();
		setTitle(texts.getString("title"));
		{
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(contentPanel);
			getContentPane().add(scrollPane, BorderLayout.CENTER);
		}
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			lblName = new JLabel(texts.getString("lblName"));
			lblName.setFont(new Font("Serif", Font.PLAIN, 17));
			String mnemonic = texts.getString("mnemoName");
			lblName.setDisplayedMnemonic(mnemonic.charAt(0));
			lblName.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_lblName = new GridBagConstraints();
			gbc_lblName.insets = new Insets(20, 10, 5, 5);
			gbc_lblName.gridx = 0;
			gbc_lblName.gridy = 0;
			contentPanel.add(lblName, gbc_lblName);
		}
		{
			txtName = new JTextField();
			txtName.setFont(new Font("Serif", Font.PLAIN, 17));
			lblName.setLabelFor(txtName);
			txtName.addKeyListener(noNumbers);
			GridBagConstraints gbc_txtName = new GridBagConstraints();
			gbc_txtName.insets = new Insets(20, 10, 5, 0);
			gbc_txtName.gridx = 1;
			gbc_txtName.gridy = 0;
			contentPanel.add(txtName, gbc_txtName);
			txtName.setColumns(10);
		}
		{
			lblSurname = new JLabel(texts.getString("lblSurname"));
			lblSurname.setFont(new Font("Serif", Font.PLAIN, 17));
			String mnemonic = texts.getString("mnemoSurname");
			lblSurname.setDisplayedMnemonic(mnemonic.charAt(0));
			lblSurname.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_lblSurname = new GridBagConstraints();
			gbc_lblSurname.insets = new Insets(10, 10, 5, 5);
			gbc_lblSurname.gridx = 0;
			gbc_lblSurname.gridy = 1;
			contentPanel.add(lblSurname, gbc_lblSurname);
		}
		{
			txtSurname = new JTextField();
			txtSurname.setFont(new Font("Serif", Font.PLAIN, 17));
			lblSurname.setLabelFor(txtSurname);
			txtSurname.addKeyListener(noNumbers);
			GridBagConstraints gbc_txtSurname = new GridBagConstraints();
			gbc_txtSurname.insets = new Insets(10, 10, 5, 0);
			gbc_txtSurname.gridx = 1;
			gbc_txtSurname.gridy = 1;
			contentPanel.add(txtSurname, gbc_txtSurname);
			txtSurname.setColumns(10);
		}
		{
			lblID = new JLabel(texts.getString("lblID"));
			lblID.setFont(new Font("Serif", Font.PLAIN, 17));
			String mnemonic = texts.getString("mnemoID");
			lblID.setDisplayedMnemonic(mnemonic.charAt(0));
			lblID.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_lblID = new GridBagConstraints();
			gbc_lblID.insets = new Insets(10, 10, 5, 5);
			gbc_lblID.gridx = 0;
			gbc_lblID.gridy = 2;
			contentPanel.add(lblID, gbc_lblID);
		}
		{
			txtId = new JTextField();
			txtId.setFont(new Font("Serif", Font.PLAIN, 17));
			txtId.addFocusListener(fewDigits);
			lblID.setLabelFor(txtId);
			GridBagConstraints gbc_txtId = new GridBagConstraints();
			gbc_txtId.insets = new Insets(10, 10, 5, 0);
			gbc_txtId.gridx = 1;
			gbc_txtId.gridy = 2;
			contentPanel.add(txtId, gbc_txtId);
			txtId.setColumns(10);
		}
		{
			lblPhone = new JLabel(texts.getString("lblPhone"));
			lblPhone.setFont(new Font("Serif", Font.PLAIN, 17));
			String mnemonic = texts.getString("mnemoPhone");
			lblPhone.setDisplayedMnemonic(mnemonic.charAt(0));
			lblPhone.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_lblPhone = new GridBagConstraints();
			gbc_lblPhone.insets = new Insets(10, 10, 5, 5);
			gbc_lblPhone.gridx = 0;
			gbc_lblPhone.gridy = 3;
			contentPanel.add(lblPhone, gbc_lblPhone);
		}
		{
			txtPhone = new JTextField();
			txtPhone.setFont(new Font("Serif", Font.PLAIN, 17));
			lblPhone.setLabelFor(txtPhone);
			txtPhone.addFocusListener(fewDigits);
			txtPhone.addKeyListener(noLetters);
			GridBagConstraints gbc_txtPhone = new GridBagConstraints();
			gbc_txtPhone.insets = new Insets(10, 10, 5, 0);
			gbc_txtPhone.gridx = 1;
			gbc_txtPhone.gridy = 3;
			contentPanel.add(txtPhone, gbc_txtPhone);
			txtPhone.setColumns(10);
		}

		{
			lblPayment = new JLabel(texts.getString("payOption"));
			lblPayment.setFont(new Font("Serif", Font.PLAIN, 17));
			GridBagConstraints gbc_lblPayment = new GridBagConstraints();
			gbc_lblPayment.insets = new Insets(0, 0, 5, 5);
			gbc_lblPayment.gridx = 0;
			gbc_lblPayment.gridy = 4;
			contentPanel.add(lblPayment, gbc_lblPayment);
		}
		{
			pnPayment = new JPanel();
			GridBagConstraints gbc_pnPayment = new GridBagConstraints();
			gbc_pnPayment.insets = new Insets(0, 0, 5, 0);
			gbc_pnPayment.fill = GridBagConstraints.BOTH;
			gbc_pnPayment.gridx = 1;
			gbc_pnPayment.gridy = 4;
			contentPanel.add(pnPayment, gbc_pnPayment);
		}
		pnCreditCardOptions = new JPanel();
		pnCreditCardOptions.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_pnCreditCardOptions = new GridBagConstraints();
		gbc_pnCreditCardOptions.insets = new Insets(10, 10, 5, 0);
		gbc_pnCreditCardOptions.gridx = 1;
		gbc_pnCreditCardOptions.gridy = 5;
		contentPanel.add(pnCreditCardOptions, gbc_pnCreditCardOptions);
		GridBagLayout gbl_pnCreditCardOptions = new GridBagLayout();
		gbl_pnCreditCardOptions.columnWidths = new int[] { 0, 0, 0 };
		gbl_pnCreditCardOptions.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_pnCreditCardOptions.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_pnCreditCardOptions.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		pnCreditCardOptions.setLayout(gbl_pnCreditCardOptions);
		lblCardNumber = new JLabel(texts.getString("cardNumber"));
		String mnemonic = texts.getString("mnemoCardNumber");
		lblCardNumber.setDisplayedMnemonic(mnemonic.charAt(0));
		GridBagConstraints gbc_lblCardNumber = new GridBagConstraints();
		gbc_lblCardNumber.insets = new Insets(10, 30, 5, 5);
		gbc_lblCardNumber.gridx = 0;
		gbc_lblCardNumber.gridy = 0;
		{
			rdbtnCash = new JRadioButton(texts.getString("radioCash"));
			rdbtnCash.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					showCreditCardOptionsCash();
				}
			});
			rdbtnCash.setFont(new Font("Serif", Font.PLAIN, 17));
			payment.add(rdbtnCash);
			mnemonic = texts.getString("mnemoCash");
			rdbtnCash.setMnemonic(mnemonic.charAt(0));
			rdbtnCash.setSelected(true);
			pnPayment.add(rdbtnCash);
		}
		{
			rdbtnCreditCard = new JRadioButton(texts.getString("radioCreditCard"));
			rdbtnCreditCard.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					showCreditCardOptionsCreditCard();
				}
			});
			rdbtnCreditCard.setFont(new Font("Serif", Font.PLAIN, 17));
			payment.add(rdbtnCreditCard);
			mnemonic = texts.getString("mnemoCreditCard");
			rdbtnCreditCard.setMnemonic(mnemonic.charAt(0));
			rdbtnCreditCard.setSelected(false);
			pnPayment.add(rdbtnCreditCard);
		}
		pnCreditCardOptions.add(lblCardNumber, gbc_lblCardNumber);
		{
			txtCardNumber = new JTextField();
			txtCardNumber.addKeyListener(noLetters);
			txtCardNumber.addFocusListener(fewDigits);
			lblCardNumber.setLabelFor(txtCardNumber);
			GridBagConstraints gbc_txtCardNumber = new GridBagConstraints();
			gbc_txtCardNumber.insets = new Insets(10, 10, 5, 5);
			gbc_txtCardNumber.gridx = 1;
			gbc_txtCardNumber.gridy = 0;
			pnCreditCardOptions.add(txtCardNumber, gbc_txtCardNumber);
			txtCardNumber.setColumns(10);
		}
		{
			lblExpirationDate = new JLabel(texts.getString("expiration"));
			GridBagConstraints gbc_lblExpirationDate = new GridBagConstraints();
			gbc_lblExpirationDate.insets = new Insets(0, 30, 5, 5);
			gbc_lblExpirationDate.gridx = 0;
			gbc_lblExpirationDate.gridy = 1;
			pnCreditCardOptions.add(lblExpirationDate, gbc_lblExpirationDate);
		}
		pnExpiration = new JPanel();
		GridBagConstraints gbc_pnExpiration = new GridBagConstraints();
		gbc_pnExpiration.insets = new Insets(0, 10, 5, 5);
		gbc_pnExpiration.gridx = 1;
		gbc_pnExpiration.gridy = 1;
		pnCreditCardOptions.add(pnExpiration, gbc_pnExpiration);
		GridBagLayout gbl_pnExpiration = new GridBagLayout();
		gbl_pnExpiration.columnWidths = new int[] { 90, 90, 90, 90, 0 };
		gbl_pnExpiration.rowHeights = new int[] { 120, 0 };
		gbl_pnExpiration.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_pnExpiration.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		pnExpiration.setLayout(gbl_pnExpiration);
		lblMonth = new JLabel(texts.getString("month"));
		mnemonic = texts.getString("mnemoMonth");
		lblMonth.setDisplayedMnemonic(mnemonic.charAt(0));
		GridBagConstraints gbc_lblMonth = new GridBagConstraints();
		gbc_lblMonth.insets = new Insets(10, 10, 5, 5);
		gbc_lblMonth.gridx = 0;
		gbc_lblMonth.gridy = 0;
		pnExpiration.add(lblMonth, gbc_lblMonth);
		{
			spMonth = new JSpinner();
			spMonth.setModel(new SpinnerNumberModel(MIN_MONTH, MIN_MONTH, 12, 1));
			lblMonth.setLabelFor(spMonth);
			GridBagConstraints gbc_spMonth = new GridBagConstraints();
			gbc_spMonth.insets = new Insets(10, 10, 5, 5);
			gbc_spMonth.gridx = 1;
			gbc_spMonth.gridy = 0;
			pnExpiration.add(spMonth, gbc_spMonth);
		}
		lblYear = new JLabel(texts.getString("year"));
		mnemonic = texts.getString("mnemoYear");
		lblYear.setDisplayedMnemonic(mnemonic.charAt(0));
		GridBagConstraints gbc_lblYear = new GridBagConstraints();
		gbc_lblYear.insets = new Insets(10, 10, 5, 5);
		gbc_lblYear.gridx = 2;
		gbc_lblYear.gridy = 0;
		pnExpiration.add(lblYear, gbc_lblYear);
		{
			spYear = new JSpinner();
			spYear.setModel(new SpinnerNumberModel(MIN_YEAR, MIN_YEAR, 21, 1));
			lblYear.setLabelFor(spYear);
			GridBagConstraints gbc_spYear = new GridBagConstraints();
			gbc_spYear.insets = new Insets(10, 10, 5, 5);
			gbc_spYear.fill = GridBagConstraints.HORIZONTAL;
			gbc_spYear.gridx = 3;
			gbc_spYear.gridy = 0;
			pnExpiration.add(spYear, gbc_spYear);
		}
		lblCvc = new JLabel(texts.getString("cvc"));
		mnemonic = texts.getString("mnemoCVC");
		lblCvc.setDisplayedMnemonic(mnemonic.charAt(0));
		GridBagConstraints gbc_lblCvc = new GridBagConstraints();
		gbc_lblCvc.insets = new Insets(0, 30, 0, 5);
		gbc_lblCvc.gridx = 0;
		gbc_lblCvc.gridy = 2;
		pnCreditCardOptions.add(lblCvc, gbc_lblCvc);
		{
			txtCvc = new JTextField();
			txtCvc.addKeyListener(noLetters);
			txtCvc.addKeyListener(tooMany);
			txtCvc.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent arg0) {
					if (txtCvc.getText().length() < 3) {
						JOptionPane.showMessageDialog(dialog, texts.getString("cvcError"),
								texts.getString("cvcErrorTitle"), JOptionPane.INFORMATION_MESSAGE);
						txtCvc.grabFocus();
					}
				}
			});
			lblCvc.setLabelFor(txtCvc);
			GridBagConstraints gbc_txtCvc = new GridBagConstraints();
			gbc_txtCvc.insets = new Insets(0, 10, 5, 5);
			gbc_txtCvc.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtCvc.gridx = 1;
			gbc_txtCvc.gridy = 2;
			pnCreditCardOptions.add(txtCvc, gbc_txtCvc);
			txtCvc.setColumns(10);
		}
		{
			lblInfo = new JLabel(texts.getString("lblInfo"));
			lblInfo.setFont(new Font("Serif", Font.PLAIN, 17));
			lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_lblInfo = new GridBagConstraints();
			gbc_lblInfo.insets = new Insets(10, 10, 5, 5);
			gbc_lblInfo.gridx = 0;
			gbc_lblInfo.gridy = 6;
			contentPanel.add(lblInfo, gbc_lblInfo);
		}
		taInfo = new JTextArea();
		taInfo.setWrapStyleWord(true);
		taInfo.setLineWrap(true);
		taInfo.setFont(new Font("Serif", Font.PLAIN, 17));
		lblInfo.setLabelFor(taInfo);
		taInfo.setEditable(false);
		loadBookingInfo();
		GridBagConstraints gbc_txaInfo = new GridBagConstraints();
		gbc_txaInfo.insets = new Insets(10, 10, 5, 0);
		gbc_txaInfo.gridx = 1;
		gbc_txaInfo.gridy = 4;
		{
			JScrollPane scrollInfo = new JScrollPane();
			scrollInfo.setViewportView(taInfo);
			GridBagConstraints gbc_scrollInfo = new GridBagConstraints();
			gbc_scrollInfo.insets = new Insets(10, 10, 5, 0);
			gbc_scrollInfo.fill = GridBagConstraints.BOTH;
			gbc_scrollInfo.gridx = 1;
			gbc_scrollInfo.gridy = 6;
			contentPanel.add(scrollInfo, gbc_scrollInfo);
			{
				lblAdditionalInfo = new JLabel(texts.getString("btnAdditionalInfo"));
				lblAdditionalInfo.setFont(new Font("Serif", Font.PLAIN, 17));
				mnemonic = texts.getString("mnemoAdditionalInfo");
				lblAdditionalInfo.setDisplayedMnemonic(mnemonic.charAt(0));
				lblAdditionalInfo.setHorizontalAlignment(SwingConstants.CENTER);
				GridBagConstraints gbc_lblAdditionalInfo = new GridBagConstraints();
				gbc_lblAdditionalInfo.insets = new Insets(10, 10, 5, 5);
				gbc_lblAdditionalInfo.gridx = 0;
				gbc_lblAdditionalInfo.gridy = 7;
				contentPanel.add(lblAdditionalInfo, gbc_lblAdditionalInfo);
			}
		}
		{
			JScrollPane scrollAdditionalInfo = new JScrollPane();
			GridBagConstraints gbc_scrollAdditionalInfo = new GridBagConstraints();
			gbc_scrollAdditionalInfo.insets = new Insets(10, 10, 5, 0);
			gbc_scrollAdditionalInfo.fill = GridBagConstraints.BOTH;
			gbc_scrollAdditionalInfo.gridx = 1;
			gbc_scrollAdditionalInfo.gridy = 7;
			contentPanel.add(scrollAdditionalInfo, gbc_scrollAdditionalInfo);
			{
				taAdditionalInfo = new JTextArea(texts.getString("taAdditionalInfo"));
				taAdditionalInfo.setWrapStyleWord(true);
				taAdditionalInfo.setLineWrap(true);
				taAdditionalInfo.setFont(new Font("Serif", Font.PLAIN, 17));
				lblAdditionalInfo.setLabelFor(taAdditionalInfo);
				scrollAdditionalInfo.setViewportView(taAdditionalInfo);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnModify = new JButton(texts.getString("btnModify"));
				btnModify.setFont(new Font("Tahoma", Font.PLAIN, 14));
				mnemonic = texts.getString("mnemoModify");
				btnModify.setMnemonic(mnemonic.charAt(0));
				btnModify.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						saveClientInfo();
						openBookingDialog(locale, agency.getCurrentCruise(), agency);
					}

				});
				{
					btnProceed = new JButton(texts.getString("btnProceed"));
					btnProceed.setFont(new Font("Tahoma", Font.PLAIN, 14));
					mnemonic = texts.getString("mnemoProceed");
					btnProceed.setMnemonic(mnemonic.charAt(0));
					btnProceed.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (noEmptyField()) {
								int answer = JOptionPane.showConfirmDialog(dialog, texts.getString("proceedMsg"),
										texts.getString("btnProceed"), JOptionPane.YES_NO_OPTION);
								if (answer == JOptionPane.YES_OPTION) {
									proceed();
								}

							} else {
								JOptionPane.showMessageDialog(dialog, texts.getString("emptyField"),
										texts.getString("emptyFieldTitle"), JOptionPane.ERROR_MESSAGE);

							}

						}
					});
					btnProceed.setActionCommand("OK");
					buttonPane.add(btnProceed);
					getRootPane().setDefaultButton(btnProceed);
				}
				buttonPane.add(btnModify);
			}
			{
				btnCancel = new JButton(texts.getString("btnCancel"));
				btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 14));
				mnemonic = texts.getString("mnemoCancel");
				btnCancel.setMnemonic(mnemonic.charAt(0));
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int answer = JOptionPane.showConfirmDialog(dialog, texts.getString("cancelMsg"),
								texts.getString("btnCancel"), JOptionPane.YES_NO_OPTION);
						if (answer == JOptionPane.YES_OPTION) {
							deleteClientInfo();
							dispose();
						}
					}
				});
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
		if (tooltips) {
			enableTooltips();
		} else {
			disableTooltips();
		}
		loadClientInfo();
	}
	
	private boolean noEmptyField() {
		boolean mainFields = !txtName.getText().isEmpty() && !txtSurname.getText().isEmpty() && !txtId.getText().isEmpty() && !txtPhone.getText().isEmpty();
		boolean creditCardFields = true;
		if (rdbtnCreditCard.isSelected()){
			creditCardFields = creditCardFields && !txtCardNumber.getText().isEmpty() && !txtCvc.getText().isEmpty();
		}
		return mainFields && creditCardFields;
	}

	private void loadClientInfo() {
		File file = new File("files/client.dat");
		if (file.exists()) {
			this.client = new Client(null, null, null, null, null, 0, 0, null, null);
			this.client.loadInfo();
			selectPaymentMethod();
			setTexts();
		}
	}

	private void selectPaymentMethod() {
		if (!this.client.getCardNumber().isEmpty() || !this.client.getCVC().isEmpty()
				|| !(this.client.getMonth() == MIN_MONTH) || !(this.client.getYear() == MIN_YEAR)) {
			rdbtnCreditCard.setSelected(true);
		}
	}

	public void deleteClientInfo() {
		File file = new File("files/client.dat");
		if (file.exists()) {
			file.delete();
		}
	}

	private void setTexts() {
		txtName.setText(this.client.getName());
		txtSurname.setText(this.client.getSurname());
		txtId.setText(this.client.getID());
		txtPhone.setText(this.client.getPhone());
		txtCardNumber.setText(this.client.getCardNumber());
		spMonth.setValue(this.client.getMonth());
		spYear.setValue(this.client.getYear());
		txtCvc.setText(this.client.getCVC());
		taAdditionalInfo.setText(this.client.getAdditionalInfo());
	}

	protected void showCreditCardOptionsCreditCard() {
		if (rdbtnCreditCard.isSelected()) {
			pnCreditCardOptions.setVisible(true);
		}

	}

	protected void showCreditCardOptionsCash() {
		if (rdbtnCash.isSelected()) {
			pnCreditCardOptions.setVisible(false);
		}

	}

	private void loadHelp() {
		HelpSet hs;
		try {

			hs = hb.getHelpSet();
			hb.initPresentation();

			hb.enableHelpKey(getRootPane(), "data", hs);
			hb.enableHelp(txtName, "data", hs);
			hb.enableHelp(txtId, "data", hs);
			hb.enableHelp(txtPhone, "data", hs);
			hb.enableHelp(txtSurname, "data", hs);
			hb.enableHelp(taInfo, "data", hs);
			hb.enableHelp(taAdditionalInfo, "data", hs);

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

	private void disableTooltips() {
		btnModify.setToolTipText(null);
		btnCancel.setToolTipText(null);
		rdbtnCash.setToolTipText(null);
		txtCvc.setToolTipText(null);
	}

	private void enableTooltips() {
		btnModify.setToolTipText(texts.getString("btnModifyTooltip"));
		btnCancel.setToolTipText(texts.getString("btnCancelTooltip"));
		rdbtnCash.setToolTipText(texts.getString("tooltipCash"));
		txtCvc.setToolTipText(texts.getString("tooltipCVC"));
	}

	private void saveClientInfo() {
		Client c = new Client(txtName.getText(), txtSurname.getText(), txtId.getText(), txtPhone.getText(),
				txtCardNumber.getText(), (int) spMonth.getValue(), (int) spYear.getValue(), txtCvc.getText(),
				taAdditionalInfo.getText());
		c.saveInfo();
		this.client = c;
	}

	class NoNumbers extends KeyAdapter {

		@Override
		public void keyTyped(KeyEvent e) {
			char c = e.getKeyChar();
			if ((c >= '0' && c <= '9')) {
				e.consume();
			}
		}

	}

	class NoLetters extends KeyAdapter {
		@Override
		public void keyTyped(KeyEvent e) {
			char c = e.getKeyChar();
			if ((c < '0' || c > '9')) {
				e.consume();
			}
		}
	}

	class CVCTooMany extends KeyAdapter {
		@Override
		public void keyTyped(KeyEvent e) {
			if (txtCvc.getText().length() >= 3) {
				e.consume();
			}
		}
	}

	class FewDigits extends FocusAdapter {
		@Override
		public void focusLost(FocusEvent arg0) {
			JTextField text = (JTextField) arg0.getSource();
			if (text.getText().length() < MIN_LENGTH) {
				JOptionPane.showMessageDialog(dialog, texts.getString("lengthError"),
						texts.getString("lengthErrorTitle"), JOptionPane.INFORMATION_MESSAGE);
				text.grabFocus();
			}
		}
	}

	private void proceed() {
		saveClientInfo();
		byte payment = CASH;
		if (rdbtnCreditCard.isSelected()) {
			payment = CARD;
		}
		ReceiptDialog popup = new ReceiptDialog(locale, agency, client, tooltips, mw, payment);
		dispose();
		popup.setVisible(true);

	}

	protected void loadBookingInfo() {
		String info;
		try {
			info = agency.getBookingInfo(locale);
			taInfo.setText(info);
		} catch (FileDataCorruptException e) {
			JOptionPane.showMessageDialog(this, texts.getString("fileCorruptError"),
					texts.getString("fileCorruptErrorTitle"), JOptionPane.ERROR_MESSAGE);
		}		

	}

	private void openBookingDialog(Locale locale, Cruise cruise, Agency agency) {
		BookingDialog booking = new BookingDialog(locale, agency, tooltips, hb, mw);
		booking.loadList(locale);
		booking.loadCabin(0);
		booking.deselectExtras();
		dispose();
		booking.setVisible(true);
	}

}
