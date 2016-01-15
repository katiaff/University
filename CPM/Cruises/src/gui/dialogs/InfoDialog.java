package gui.dialogs;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import gui.MainWindow;
import logic.implementation.Agency;
import logic.tripelements.Cruise;
import logic.tripelements.Extra;
import logic.tripelements.Ship;

public class InfoDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private Locale locale;
	private ResourceBundle texts;
	private Cruise cruise;
	private JLabel lblPhoto1;
	private JLabel lblPhoto2;
	private JPanel pnPhotosSingle;
	private JLabel currentPhoto;
	private ResizePhoto resize = new ResizePhoto();
	private Agency agency;
	private JButton chooseButton;
	private boolean tooltips;
	private HelpBroker hb;
	private JPanel pnDescription;
	private JButton btnHelp;
	private JPanel pnPhotos;
	private JTabbedPane tabbedPane;
	private MainWindow mw;

	public InfoDialog(Locale locale, Cruise toShow, Agency agency, boolean tooltips, HelpBroker hb, MainWindow mw) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.mw = mw;
		this.locale = locale;
		this.texts = ResourceBundle.getBundle("resources.InfoDialog", this.locale);
		this.cruise = toShow;
		this.agency = agency;
		this.hb = hb;
		this.tooltips = tooltips;
		setBounds(100, 100, 1024, 768);
		setTitle(texts.getString("title"));
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		loadHelp();
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			contentPanel.add(tabbedPane, BorderLayout.CENTER);
			{
				pnPhotos = new JPanel();
				tabbedPane.addTab(texts.getString("tabPhotos"), null, pnPhotos, null);
				String mnemonic = texts.getString("mnemoTabPhotos");
				tabbedPane.setMnemonicAt(0, mnemonic.charAt(0));
				pnPhotos.setLayout(new BorderLayout(0, 0));
				{
					JButton btnNext = new JButton(texts.getString("btnNext"));
					btnNext.setFont(new Font("Tahoma", Font.PLAIN, 14));
					mnemonic = texts.getString("mnemoBtnNext");
					btnNext.setMnemonic(mnemonic.charAt(0));
					btnNext.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							nextPhoto();
						}
					});
					pnPhotos.add(btnNext, BorderLayout.EAST);
				}
				{
					JButton btnPrevious = new JButton(texts.getString("btnPrevious"));
					btnPrevious.setFont(new Font("Tahoma", Font.PLAIN, 14));
					mnemonic = texts.getString("mnemoBtnPrevious");
					btnPrevious.setMnemonic(mnemonic.charAt(0));
					btnPrevious.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							previousPhoto();
						}
					});
					pnPhotos.add(btnPrevious, BorderLayout.WEST);
				}
				{
					pnPhotosSingle = new JPanel();
					pnPhotos.add(pnPhotosSingle, BorderLayout.CENTER);
					pnPhotosSingle.setLayout(new CardLayout(0, 0));
					{
						JPanel photo1 = new JPanel();
						pnPhotosSingle.add(photo1, "photo1");
						photo1.setLayout(new BorderLayout(0, 0));
						{
							lblPhoto1 = new JLabel("");
							lblPhoto1.addComponentListener(resize);
							photo1.add(lblPhoto1);
						}
					}
					{
						JPanel photo2 = new JPanel();
						pnPhotosSingle.add(photo2, "photo2");
						photo2.setLayout(new BorderLayout(0, 0));
						{
							lblPhoto2 = new JLabel("");
							lblPhoto2.addComponentListener(resize);
							photo2.add(lblPhoto2);
						}
					}
				}
				loadPhotos();
			}
			{
				pnDescription = new JPanel();
				tabbedPane.addTab(texts.getString("tabDescription"), null, pnDescription, null);
				String mnemonic = texts.getString("mnemoTabDescription");
				tabbedPane.setMnemonicAt(1, mnemonic.charAt(0));
				pnDescription.setLayout(new GridLayout(3, 0, 0, 5));
				{
					JScrollPane spDescription = new JScrollPane();
					pnDescription.add(spDescription);
					{
						JTextArea taDescription = new JTextArea();
						taDescription.setFont(new Font("Serif", Font.PLAIN, 17));
						taDescription.setWrapStyleWord(true);
						taDescription.setMargin(new Insets(10, 10, 10, 5));
						taDescription.setLineWrap(true);
						taDescription.setBorder(getBorder());
						taDescription.setEditable(false);
						StringBuilder sb = loadDescription();
						taDescription.setText(sb.toString());
						spDescription.setViewportView(taDescription);
					}
				}
				{
					JScrollPane spPrices = new JScrollPane();
					pnDescription.add(spPrices);
					{
						JTextArea taPrices = new JTextArea();
						taPrices.setFont(new Font("Serif", Font.PLAIN, 17));
						taPrices.setWrapStyleWord(true);
						taPrices.setMargin(new Insets(10, 10, 10, 5));
						taPrices.setLineWrap(true);
						taPrices.setEditable(false);
						taPrices.setBorder(getBorder(texts.getString("borderPrices")));
						Ship ship = Agency.searchShips(cruise.getShip());
						taPrices.setText(ship.getAvailableCabinsString(locale));
						spPrices.setViewportView(taPrices);
					}
				}
				{
					JScrollPane spExtras = new JScrollPane();
					pnDescription.add(spExtras);
					{
						JTextArea taExtras = new JTextArea();
						taExtras.setFont(new Font("Serif", Font.PLAIN, 17));
						taExtras.setWrapStyleWord(true);
						taExtras.setMargin(new Insets(10, 10, 10, 5));
						taExtras.setLineWrap(true);
						taExtras.setEditable(false);
						taExtras.setBorder(getBorder(texts.getString("borderExtras")));
						StringBuilder sb = new StringBuilder();
						List<Extra> extras = agency.getExtras();
						for (int i = 0; i < extras.size(); i++) {
							Extra current = extras.get(i);
							sb.append(current.getName() + " - " + texts.getString("price") + current.getPrice() + "\n");
						}
						taExtras.setText(sb.toString());
						spExtras.setViewportView(taExtras);
					}
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				chooseButton = new JButton(texts.getString("chooseButton"));
				chooseButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
				String mnemonic = texts.getString("mnemoChooseButton");
				chooseButton.setMnemonic(mnemonic.charAt(0));
				chooseButton.setToolTipText(texts.getString("btnChooseTooltip"));
				chooseButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						openBooking(cruise);
					}
				});
				chooseButton.setActionCommand(texts.getString("chooseButton"));
				buttonPane.add(chooseButton);
				getRootPane().setDefaultButton(chooseButton);
			}
			{
				btnHelp = new JButton(texts.getString("btnHelp"));
				btnHelp.setFont(new Font("Tahoma", Font.PLAIN, 14));
				btnHelp.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
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

	}

	private StringBuilder loadDescription() {
		StringBuilder sb = new StringBuilder(texts.getString("cruise"));
		sb.append(" " + cruise.getName());
		sb.append("\n" + cruise.getDescription() + "\n");
		sb.append("\n" + texts.getString("itinerary"));
		sb.append("\n" + cruise.getItinerary() + "\n");
		sb.append("\n" + texts.getString("ship"));
		Ship ship = Agency.searchShips(cruise.getShip());
		sb.append(" " + ship.getName());
		sb.append("\n" + ship.getDescription() + "\n");
		if (cruise.getChildren()) {
			sb.append("\n" + texts.getString("kidsAllowed"));
		} else {
			sb.append("\n" + texts.getString("kidsNotAllowed"));
		}
		return sb;
	}

	private void loadHelp() {
		HelpSet hs;
		try {

			hs = hb.getHelpSet();
			hb.initPresentation();

			hb.enableHelpKey(getRootPane(), "info", hs);
			hb.enableHelp(tabbedPane.getTabComponentAt(0), "info", hs);
			hb.enableHelp(pnPhotos, "info", hs);
			hb.enableHelp(tabbedPane.getTabComponentAt(1), "info", hs);
			hb.enableHelp(pnDescription, "info", hs);

		}

		catch (Exception e) {

		}

	}

	protected void openBooking(Cruise cruise) {
		agency.setCurrentCruise(cruise);
		BookingDialog bd = new BookingDialog(locale, agency, tooltips, hb, mw);
		dispose();
		bd.setVisible(true);
	}

	class ResizePhoto extends ComponentAdapter {
		@Override
		public void componentResized(ComponentEvent arg0) {
			loadPhotos();
		}
	}

	private void loadPhotos() {
		setVisible(true);
		String codeCruise = cruise.getCode();
		String codeShip = cruise.getShip();
		setAdaptedImageIcon(lblPhoto1, "/img/cruises/" + codeCruise + ".jpg");
		currentPhoto = lblPhoto1;
		setAdaptedImageIcon(lblPhoto2, "/img/ships/" + codeShip + ".jpg");
	}

	private void setAdaptedImageIcon(JLabel lbl, String route) {
		URL url = getClass().getResource(route);
		if (url != null) {
			Image imgOriginal = new ImageIcon(url).getImage();
			Image imgScaled = imgOriginal.getScaledInstance((int) (lbl.getWidth()), (int) (lbl.getHeight()),
					Image.SCALE_FAST);
			lbl.setIcon(new ImageIcon(imgScaled));
		} else {
			JOptionPane.showMessageDialog(this, texts.getString("noImageFound"), texts.getString("noImageFoundTitle"),
					JOptionPane.ERROR_MESSAGE);
		}

	}

	private void nextPhoto() {
		if (currentPhoto == lblPhoto1) {
			currentPhoto = lblPhoto2;
			((CardLayout) pnPhotosSingle.getLayout()).show(pnPhotosSingle, "photo2");
		} else if (currentPhoto == lblPhoto2) {
			currentPhoto = lblPhoto1;
			((CardLayout) pnPhotosSingle.getLayout()).show(pnPhotosSingle, "photo1");
		}
	}

	private void previousPhoto() {
		if (currentPhoto == lblPhoto1) {
			currentPhoto = lblPhoto2;
			((CardLayout) pnPhotosSingle.getLayout()).show(pnPhotosSingle, "photo2");
		} else if (currentPhoto == lblPhoto2) {
			currentPhoto = lblPhoto1;
			((CardLayout) pnPhotosSingle.getLayout()).show(pnPhotosSingle, "photo1");
		}
	}

	public static Border getBorder() {
		return new CompoundBorder(new LineBorder(new Color(52, 52, 52), 5), new EmptyBorder(10, 10, 10, 5));
	}

	public static Border getBorder(String string) {
		return new CompoundBorder(new TitledBorder(new LineBorder(new Color(52, 52, 52), 5), string),
				new EmptyBorder(10, 10, 10, 5));
	}

	public void disableTooltips() {
		chooseButton.setToolTipText(null);
	}

	public void enableTooltips() {
		chooseButton.setToolTipText(texts.getString("btnChooseTooltip"));
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

}
