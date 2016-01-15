package gui.dialogs;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import gui.MainWindow;
import logic.implementation.Agency;
import logic.implementation.Client;
import logic.util.FileDataCorruptException;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ReceiptDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private Locale locale;
	private ResourceBundle texts;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private String name;
	private String ID;
	private String phone;
	private Agency agency;
	private String additionalInfo;
	private JButton btnClose;
	private boolean tooltips;
	private MainWindow mw;
	private byte payment;

	/**
	 * Create the dialog.
	 * 
	 * @param payment
	 */
	public ReceiptDialog(Locale locale, Agency agency, Client client, boolean tooltips, MainWindow mw, byte payment) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				btnClose.doClick();
			}
		});
		setBounds(100, 100, 800, 800);
		this.mw = mw;
		this.locale = locale;
		this.texts = ResourceBundle.getBundle("resources.ReceiptDialog", this.locale);
		this.agency = agency;
		this.name = client.getName() + " " + client.getSurname();
		this.ID = client.getID();
		this.phone = client.getPhone();
		this.additionalInfo = client.getAdditionalInfo();
		if (additionalInfo.equals(texts.getString("taAdditionalInfo"))) {
			this.additionalInfo = null;
		}
		this.tooltips = tooltips;
		this.payment = payment;
		setTitle(texts.getString("title"));
		getContentPane().add(getBtnClose(), BorderLayout.SOUTH);
		getContentPane().add(getScrollPane(), BorderLayout.CENTER);

	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTextArea());
		}
		return scrollPane;
	}

	private JTextArea getTextArea() {
		if (textArea == null) {
			try {
				textArea = new JTextArea();
				textArea.setEditable(false);
				textArea.setFont(new Font("Serif", Font.PLAIN, 17));
				textArea.setWrapStyleWord(true);
				textArea.setLineWrap(true);
				String receipt;
				try {
					receipt = agency.getReceipt(name, ID, phone, locale, additionalInfo, payment);
					textArea.setText(receipt);
					deleteClientInfo();
				} catch (FileDataCorruptException e) {
					JOptionPane.showMessageDialog(this, texts.getString("fileCorruptError"),
							texts.getString("fileCorruptErrorTitle"), JOptionPane.ERROR_MESSAGE);
				}

			} catch (FileNotFoundException e1) {
				JOptionPane.showMessageDialog(this, texts.getString("fileNotFound"),
						texts.getString("fileNotFoundTitle"), JOptionPane.ERROR_MESSAGE);
			} catch (IOException e2) {
				JOptionPane.showMessageDialog(this, texts.getString("IOException"), texts.getString("IOExceptionTitle"),
						JOptionPane.ERROR_MESSAGE);
			}
		}
		return textArea;
	}

	public void deleteClientInfo() {
		File file = new File("files/client.dat");
		if (file.exists()) {
			file.delete();
		}
	}

	private JButton getBtnClose() {
		if (btnClose == null) {
			btnClose = new JButton(texts.getString("btnClose"));
			btnClose.setFont(new Font("Tahoma", Font.PLAIN, 14));
			String mnemonic = texts.getString("mnemoClose");
			btnClose.setMnemonic(mnemonic.charAt(0));
			btnClose.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					mw.restart();
					dispose();
				}
			});
			if (tooltips) {
				btnClose.setToolTipText(texts.getString("btnCloseTooltip"));
			}
		}
		return btnClose;
	}
}
