package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class StartDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int MINIMUM_MONEY = 5;
	public static final int MAXIMUM_MONEY = 5000;

	private String name;
	private double money;

	private final JPanel contentPanel = new JPanel();
	private JTextField txName;

	/**
	 * Create the dialog.
	 */
	public StartDialog(MainWindow mainWindow) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				StartDialog.class.getResource("/img/Roulette.png")));
		setTitle("The Roulette Game: player settings");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 750, 375);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.BLACK);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblCruppierSays = new JLabel("Cruppier says: ");
		lblCruppierSays.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblCruppierSays.setForeground(Color.WHITE);
		lblCruppierSays.setBounds(299, 34, 192, 61);
		contentPanel.add(lblCruppierSays);

		JLabel lblName = new JLabel("What's your name?");
		lblName.setBackground(Color.BLACK);
		lblName.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblName.setForeground(Color.WHITE);
		lblName.setBounds(210, 141, 208, 27);
		contentPanel.add(lblName);

		JLabel lblMoney = new JLabel("How much money will you bet?");
		lblMoney.setToolTipText("");
		lblMoney.setBackground(Color.BLACK);
		lblMoney.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblMoney.setForeground(Color.WHITE);
		lblMoney.setBounds(97, 226, 317, 27);
		contentPanel.add(lblMoney);

		txName = new JTextField();
		txName.setToolTipText("Write your name here");
		txName.setHorizontalAlignment(SwingConstants.CENTER);
		txName.setForeground(Color.WHITE);
		txName.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		txName.setBackground(Color.BLACK);
		txName.setBounds(446, 141, 277, 27);
		contentPanel.add(txName);
		txName.setColumns(10);

		JSpinner spinnerMoney = new JSpinner();
		spinnerMoney
				.setToolTipText("Choose the money to start playing (between "
						+ MINIMUM_MONEY + "-" + MAXIMUM_MONEY + ")");
		spinnerMoney.setModel(new SpinnerNumberModel(5, MINIMUM_MONEY,
				MAXIMUM_MONEY, 5));
		spinnerMoney.setForeground(Color.WHITE);
		spinnerMoney.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		spinnerMoney.setBackground(Color.BLACK);
		spinnerMoney.setBounds(446, 226, 277, 27);
		contentPanel.add(spinnerMoney);

		JLabel lblCruppierImg = new JLabel("");
		lblCruppierImg.setIcon(new ImageIcon(StartDialog.class
				.getResource("/img/Croupier_200.png")));
		lblCruppierImg.setBounds(0, 11, 200, 184);
		contentPanel.add(lblCruppierImg);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.BLACK);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						name = txName.getText();
						money = (int) spinnerMoney.getValue();

						if (name.isEmpty()) {
							JOptionPane.showMessageDialog(null,
									"Name field cannot be empty");
							txName.requestFocus();
						} else if (money < MINIMUM_MONEY
								|| money > MAXIMUM_MONEY || money % 5 != 0) {
							JOptionPane.showMessageDialog(null,
									"Money must be in range [" + MINIMUM_MONEY
											+ ", " + MAXIMUM_MONEY
											+ "] and be a multiple of 5");
							spinnerMoney.setValue(MINIMUM_MONEY);
							spinnerMoney.requestFocus();

						} else {
							mainWindow.setPlayerName(name);
							mainWindow.setTotalMoney(money);
							dispose();
						}

					}
				});
				okButton.setForeground(Color.WHITE);
				okButton.setBackground(Color.BLACK);
				okButton.setFont(new Font("Times New Roman", Font.PLAIN, 22));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (mainWindow.getPlayerName() == ""
								|| mainWindow.getTotalMoney() == 0) {
							JOptionPane
									.showMessageDialog(null,
											"You can't cancel. Some options has not been configured.");
						} else {
							dispose();
						}
					}
				});
				cancelButton.setForeground(Color.WHITE);
				cancelButton.setBackground(Color.BLACK);
				cancelButton
						.setFont(new Font("Times New Roman", Font.PLAIN, 22));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}

			JButton exitButton = new JButton("Exit");
			exitButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			exitButton.setForeground(Color.WHITE);
			exitButton.setFont(new Font("Times New Roman", Font.PLAIN, 22));
			exitButton.setBackground(Color.BLACK);
			exitButton.setActionCommand("Cancel");
			buttonPane.add(exitButton);
		}
	}
}
