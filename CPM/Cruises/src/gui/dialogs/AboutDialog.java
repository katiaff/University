package gui.dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class AboutDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private ResourceBundle texts;

	/**
	 * Create the dialog.
	 * 
	 * @param locale
	 */
	public AboutDialog(Locale locale) {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 725, 375);
		getContentPane().setLayout(new BorderLayout());
		this.texts = ResourceBundle.getBundle("resources.AboutDialog", locale);
		setTitle(texts.getString("title"));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 300, 388, 0 };
		gbl_contentPanel.rowHeights = new int[] { 300, 0 };
		gbl_contentPanel.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblImg = new JLabel("");
			lblImg.setIcon(new ImageIcon(AboutDialog.class.getResource("/img/icon.png")));
			GridBagConstraints gbc_lblImg = new GridBagConstraints();
			gbc_lblImg.anchor = GridBagConstraints.NORTHWEST;
			gbc_lblImg.insets = new Insets(0, 0, 0, 5);
			gbc_lblImg.gridx = 0;
			gbc_lblImg.gridy = 0;
			contentPanel.add(lblImg, gbc_lblImg);
		}
		{
			JTextArea textArea = new JTextArea();
			textArea.setBorder(new LineBorder(new Color(52, 52, 52), 2));
			textArea.setFocusable(false);
			textArea.setFocusTraversalKeysEnabled(false);
			textArea.setWrapStyleWord(true);
			textArea.setLineWrap(true);
			textArea.setEditable(false);
			textArea.setText(loadText());
			GridBagConstraints gbc_textArea = new GridBagConstraints();
			gbc_textArea.fill = GridBagConstraints.BOTH;
			gbc_textArea.gridx = 1;
			gbc_textArea.gridy = 0;
			contentPanel.add(textArea, gbc_textArea);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton closeButton = new JButton(texts.getString("close"));
				String mnemonic = texts.getString("mnemoClose");
				closeButton.setMnemonic(mnemonic.charAt(0));
				closeButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(closeButton);
			}
		}
	}

	private String loadText() {
		StringBuilder sb = new StringBuilder(texts.getString("about1"));
		sb.append("\n");
		sb.append(texts.getString("about2"));
		sb.append("\n");
		sb.append(texts.getString("about3"));
		return sb.toString();
	}

}
