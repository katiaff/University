package gui.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import gui.MainWindow;

public class LanguageSelector extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private ResourceBundle texts;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private ArrayList<JRadioButton> buttons = new ArrayList<JRadioButton>();
	private JRadioButton rdbtnEnglish;
	private JRadioButton rdbtnSpanish;
	private JButton btnSpanish;
	private JButton btnEnglish;
	private JButton okButton;
	private MouseListener clickButton = new ClickButton();
	private JButton cancelButton;
	private JPanel pnSpanish;

	/**
	 * Create the dialog.
	 * 
	 * @param locale
	 */
	public LanguageSelector(MainWindow mw, Locale locale) {
		texts = ResourceBundle.getBundle("resources.LanguageSelector", locale);
		setTitle(texts.getString("title"));
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 450, 286);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JLabel lblChooseLanguage = new JLabel(texts.getString("message"));
			lblChooseLanguage.setHorizontalAlignment(SwingConstants.CENTER);
			lblChooseLanguage.setFont(new Font("Dialog", Font.PLAIN, 17));
			contentPanel.add(lblChooseLanguage, BorderLayout.NORTH);
		}
		{
			JPanel pnButtons = new JPanel();
			contentPanel.add(pnButtons, BorderLayout.CENTER);
			pnButtons.setLayout(new GridLayout(0, 2, 0, 0));
			{
				JPanel pnEnglish = new JPanel();
				pnButtons.add(pnEnglish);
				pnEnglish.setLayout(new BorderLayout(0, 0));
				{
					rdbtnEnglish = new JRadioButton(texts.getString("english"));
					String mnemonic = texts.getString("mnemoEnglish");
					rdbtnEnglish.setMnemonic(mnemonic.charAt(0));
					if (locale.getLanguage() == "en") {
						rdbtnEnglish.setSelected(true);
					}
					buttonGroup.add(rdbtnEnglish);
					buttons.add(rdbtnEnglish);
					rdbtnEnglish.setActionCommand("en");
					rdbtnEnglish.setHorizontalAlignment(SwingConstants.CENTER);
					pnEnglish.add(rdbtnEnglish, BorderLayout.SOUTH);
				}
				{
					btnEnglish = new JButton("");
					btnEnglish.setIcon(new ImageIcon(LanguageSelector.class.getResource("/img/languages/english.png")));
					btnEnglish.addMouseListener(clickButton);
					btnEnglish.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							rdbtnEnglish.setSelected(true);
						}
					});
					pnEnglish.add(btnEnglish, BorderLayout.CENTER);
				}
			}
			{
				pnSpanish = new JPanel();
				pnButtons.add(pnSpanish);
				pnSpanish.setLayout(new BorderLayout(0, 0));
				{
					rdbtnSpanish = new JRadioButton(texts.getString("spanish"));
					String mnemonic = texts.getString("mnemoSpanish");
					rdbtnSpanish.setMnemonic(mnemonic.charAt(0));
					if (locale.getLanguage() == "es") {
						rdbtnSpanish.setSelected(true);
					}
					buttonGroup.add(rdbtnSpanish);
					buttons.add(rdbtnSpanish);
					rdbtnSpanish.setActionCommand("es");
					rdbtnSpanish.setHorizontalAlignment(SwingConstants.CENTER);
					pnSpanish.add(rdbtnSpanish, BorderLayout.SOUTH);
				}
				{
					btnSpanish = new JButton("");
					btnSpanish.setIcon(new ImageIcon(LanguageSelector.class.getResource("/img/languages/spanish.png")));
					btnSpanish.addMouseListener(clickButton);
					btnSpanish.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							rdbtnSpanish.setSelected(true);
						}
					});
					pnSpanish.add(btnSpanish, BorderLayout.CENTER);
				}
			}

		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				String mnemonic = texts.getString("mnemoOK");
				okButton.setMnemonic(mnemonic.charAt(0));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						for (JRadioButton button : buttons) {
							if (button.isSelected()) {
								if (button.getActionCommand() == "en") {
									mw.updateLanguage(new Locale(button.getActionCommand(), "GB"));
								}
								if (button.getActionCommand() == "es") {
									mw.updateLanguage(new Locale(button.getActionCommand(), "ES"));
								}
								dispose();
							}
						}
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton(texts.getString("cancel"));
				String mnemonic = texts.getString("mnemoCancel");
				cancelButton.setMnemonic(mnemonic.charAt(0));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}

	}

	class ClickButton extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) {
				okButton.doClick();
			}
		}

	}

}
