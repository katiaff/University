package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import roulette.Bet;
import roulette.BetManager;
import roulette.Roulette;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnRoulette;
	private Roulette roulette;
	private JMenuBar menuBar;
	private JPanel pn0;
	private JPanel pn1;
	private JPanel pn2;

	private ClickButton clickButton = new ClickButton();
	private JTextField txNumber;
	private JTextPane txCruppier;
	private JLabel lblPlayerName;
	private StartDialog startDialog;
	private String playerName = "";
	private double totalMoney = 0;
	private BetManager bets = new BetManager(totalMoney);
	private JTextField txMoneyLeft;
	private JMenuItem mntmPlayerSettings;
	private String currentBet = "";
	private JButton btn0;
	private JPanel pnButtons;
	private JButton btn1Column;
	private JButton btn2Column;
	private JButton btn3Column;
	private JPanel pnColumn;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				MainWindow.class.getResource("/img/Roulette.png")));
		setTitle("The Roulette Game");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1229, 555);
		setLocationRelativeTo(null);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnGame = new JMenu("Game");
		mnGame.setMnemonic('G');
		menuBar.add(mnGame);

		JMenuItem mntmNewGame = new JMenuItem("New Game");
		mntmNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int answer = JOptionPane
						.showConfirmDialog(null,
								"Do you want to change your settings?",
								"Confirm name change",
								JOptionPane.YES_NO_CANCEL_OPTION);
				if (answer == JOptionPane.YES_OPTION) {
					showDialog(startDialog);
					startGame();

				}
				if (answer != JOptionPane.CANCEL_OPTION) {
					startGame();
				}
			}
		});
		mntmNewGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
		mntmNewGame.setMnemonic('N');
		mnGame.add(mntmNewGame);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setMnemonic('x');
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int reply = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to quit the game?", "Exit",
						JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		mnGame.add(getMntmPlayerSettings());

		JSeparator separator = new JSeparator();
		mnGame.add(separator);
		mnGame.add(mntmExit);

		JMenu mnHelp = new JMenu("Help");
		mnHelp.setMnemonic('H');
		menuBar.add(mnHelp);

		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane
						.showMessageDialog(
								null,
								"The Roulette Game.\nUniversity of Valenciennes and University of Oviedo collaboration project.\nNovember 2015.",
								"About the game",
								JOptionPane.INFORMATION_MESSAGE,
								new ImageIcon(getClass().getResource(
										"/img/logo.png")));
			}
		});
		mntmAbout.setMnemonic('b');
		mnHelp.add(mntmAbout);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnRoulette());
		contentPane.add(getPn0());
		contentPane.add(getPn1());
		contentPane.add(getPn2());

		btn0 = new JButton("0");
		btn0.setBorder(new LineBorder(new Color(255, 255, 255)));
		btn0.setBackground(new Color(0, 128, 0));
		btn0.setForeground(new Color(255, 255, 255));
		btn0.setBounds(342, 166, 89, 181);
		btn0.addActionListener(clickButton);
		contentPane.add(btn0);

		JLabel lblNumber = new JLabel("Number: ");
		lblNumber.setForeground(new Color(255, 255, 255));
		lblNumber.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblNumber.setBounds(34, 442, 100, 28);
		contentPane.add(lblNumber);

		txNumber = new JTextField();
		txNumber.setToolTipText("The number from the roulette");
		txNumber.setForeground(Color.WHITE);
		txNumber.setBorder(new LineBorder(new Color(255, 255, 255)));
		txNumber.setHorizontalAlignment(SwingConstants.CENTER);
		txNumber.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		txNumber.setBackground(new Color(0, 0, 0));
		txNumber.setEditable(false);
		txNumber.setBounds(191, 440, 89, 33);
		contentPane.add(txNumber);
		txNumber.setColumns(10);

		JLabel lblCruppierSays = new JLabel("Cruppier says: ");
		lblCruppierSays.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblCruppierSays.setForeground(Color.WHITE);
		lblCruppierSays.setBounds(353, 58, 168, 28);
		contentPane.add(lblCruppierSays);

		pnColumn = new JPanel();
		pnColumn.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnColumn.setBackground(Color.WHITE);
		pnColumn.setBounds(437, 358, 750, 33);
		contentPane.add(pnColumn);
		pnColumn.setLayout(new GridLayout(0, 3, 0, 0));

		btn1Column = new JButton("1st column");
		btn1Column.setForeground(Color.WHITE);
		btn1Column.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btn1Column.setBorder(new LineBorder(Color.WHITE));
		btn1Column.setBackground(Color.DARK_GRAY);
		btn1Column.addActionListener(clickButton);
		pnColumn.add(btn1Column);

		btn2Column = new JButton("2nd column");
		btn2Column.setForeground(Color.WHITE);
		btn2Column.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btn2Column.setBorder(new LineBorder(Color.WHITE));
		btn2Column.setBackground(Color.DARK_GRAY);
		btn2Column.addActionListener(clickButton);
		pnColumn.add(btn2Column);

		btn3Column = new JButton("3rd column");
		btn3Column.setForeground(Color.WHITE);
		btn3Column.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btn3Column.setBorder(new LineBorder(Color.WHITE));
		btn3Column.setBackground(Color.DARK_GRAY);
		btn3Column.addActionListener(clickButton);
		pnColumn.add(btn3Column);

		pnButtons = new JPanel();
		pnButtons.setBounds(437, 402, 750, 40);
		contentPane.add(pnButtons);
		pnButtons.setLayout(new GridLayout(1, 0, 0, 0));

		JButton btnFailed = new JButton(Bet.FAILED);
		btnFailed.setToolTipText("Numbers from 1 to 18");
		btnFailed.setForeground(Color.WHITE);
		btnFailed.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnFailed.setBorder(new LineBorder(Color.WHITE));
		btnFailed.setBackground(Color.DARK_GRAY);
		btnFailed.addActionListener(clickButton);
		pnButtons.add(btnFailed);

		JButton btnEven = new JButton(Bet.EVEN);
		btnEven.setToolTipText("Play to even numbers");
		btnEven.setForeground(Color.WHITE);
		btnEven.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnEven.setBorder(new LineBorder(Color.WHITE));
		btnEven.setBackground(Color.DARK_GRAY);
		btnEven.addActionListener(clickButton);
		pnButtons.add(btnEven);

		JButton btnRed = new JButton(Bet.RED);
		btnRed.setToolTipText("Play to red numbers");
		btnRed.setForeground(Color.WHITE);
		btnRed.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnRed.setBorder(new LineBorder(Color.WHITE));
		btnRed.setBackground(Color.DARK_GRAY);
		btnRed.addActionListener(clickButton);
		pnButtons.add(btnRed);

		JButton btnBlack = new JButton(Bet.BLACK);
		btnBlack.setToolTipText("Play to black numbers");
		btnBlack.setForeground(Color.WHITE);
		btnBlack.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnBlack.setBorder(new LineBorder(Color.WHITE));
		btnBlack.setBackground(Color.DARK_GRAY);
		btnBlack.addActionListener(clickButton);

		JButton btnCorner = new JButton("Corner");
		btnCorner.setBorder(new LineBorder(Color.WHITE));
		btnCorner.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnCorner.addActionListener(clickButton);
		btnCorner.setForeground(Color.WHITE);
		btnCorner.setBackground(Color.DARK_GRAY);
		btnCorner
				.setToolTipText("Bet to a set of 4 possible numbers making a square");
		pnButtons.add(btnCorner);
		pnButtons.add(btnBlack);

		JButton btnOdd = new JButton(Bet.ODD);
		btnOdd.setToolTipText("Play to odd numbers");
		btnOdd.setForeground(Color.WHITE);
		btnOdd.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnOdd.setBorder(new LineBorder(Color.WHITE));
		btnOdd.setBackground(Color.DARK_GRAY);
		btnOdd.addActionListener(clickButton);
		pnButtons.add(btnOdd);

		JButton btnPassed = new JButton(Bet.PASSED);
		btnPassed.setToolTipText("Numbers from 19 to 36");
		btnPassed.setForeground(Color.WHITE);
		btnPassed.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnPassed.setBorder(new LineBorder(Color.WHITE));
		btnPassed.setBackground(Color.DARK_GRAY);
		btnPassed.addActionListener(clickButton);
		pnButtons.add(btnPassed);

		JLabel lblMoneyLeft = new JLabel("Money left: ");
		lblMoneyLeft.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblMoneyLeft.setBackground(Color.BLACK);
		lblMoneyLeft.setForeground(Color.WHITE);
		lblMoneyLeft.setBounds(34, 85, 128, 28);
		contentPane.add(lblMoneyLeft);

		txMoneyLeft = new JTextField();
		txMoneyLeft.setHorizontalAlignment(SwingConstants.CENTER);
		txMoneyLeft.setForeground(Color.WHITE);
		txMoneyLeft.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		txMoneyLeft.setEditable(false);
		txMoneyLeft.setColumns(10);
		txMoneyLeft.setBorder(new LineBorder(new Color(255, 255, 255)));
		txMoneyLeft.setBackground(Color.BLACK);
		txMoneyLeft.setBounds(191, 83, 89, 33);
		contentPane.add(txMoneyLeft);

		lblPlayerName = new JLabel("Player name");
		lblPlayerName.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblPlayerName.setForeground(Color.WHITE);
		lblPlayerName.setBounds(37, 27, 243, 33);
		contentPane.add(lblPlayerName);

		txCruppier = new JTextPane();
		txCruppier.setBounds(531, 11, 655, 137);
		contentPane.add(txCruppier);
		txCruppier.setEditable(false);
		txCruppier.setBorder(new LineBorder(Color.WHITE));
		txCruppier.setForeground(Color.WHITE);
		txCruppier.setBackground(Color.BLACK);
		txCruppier.setFont(new Font("Times New Roman", Font.PLAIN, 22));

		createButtons();

		roulette = new Roulette();

		showDialog(startDialog);
		startGame();

	}

	private JButton getBtnRoulette() {
		if (btnRoulette == null) {
			btnRoulette = new JButton("");
			btnRoulette.setBackground(Color.BLACK);
			btnRoulette.setToolTipText("Click to spin the roulette");
			btnRoulette.setIcon(new ImageIcon(MainWindow.class
					.getResource("/img/roulette.jpg")));
			btnRoulette.setDisabledIcon(new ImageIcon(MainWindow.class
					.getResource("/img/roulette_disabled.jpg")));
			btnRoulette.setBorderPainted(false);
			btnRoulette.setBounds(34, 127, 270, 277);
			btnRoulette.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (bets.isOKBets() && btnRoulette.isEnabled()) {
						roulette.startGame();
						txNumber.setText(String.valueOf(roulette.getNumber()));
						bets.calculatePrize(roulette.getNumber());
						txCruppier.setText("Number: " + txNumber.getText()
								+ ". Winner bets: " + bets.getWinnerBets()
								+ ". Prize: " + bets.getPrize());
						btnRoulette.setEnabled(false);
						int answer = JOptionPane
								.showConfirmDialog(
										null,
										"Do you want to start a new game? (Choose 'No' to exit the game)",
										"Restart",
										JOptionPane.YES_NO_CANCEL_OPTION);
						if (answer == JOptionPane.YES_OPTION) {
							startGame();
						} else if (answer == JOptionPane.NO_OPTION) {
							System.exit(0);
						}

					} else {
						JOptionPane.showMessageDialog(null,
								"You can't turn the wheel now.");
					}
				}
			});

		}
		return btnRoulette;
	}

	private JPanel getPn0() {
		if (pn0 == null) {
			pn0 = new JPanel();
			pn0.setBackground(Color.WHITE);
			pn0.setBorder(new EmptyBorder(0, 0, 0, 0));
			pn0.setBounds(437, 166, 750, 53);
			pn0.setLayout(new GridLayout(1, 0, 0, 0));

		}
		return pn0;
	}

	private JPanel getPn1() {
		if (pn1 == null) {
			pn1 = new JPanel();
			pn1.setBackground(Color.WHITE);
			pn1.setBorder(new EmptyBorder(0, 0, 0, 0));
			pn1.setBounds(437, 230, 750, 53);
			pn1.setLayout(new GridLayout(1, 0, 0, 0));

		}
		return pn1;
	}

	private JPanel getPn2() {
		if (pn2 == null) {
			pn2 = new JPanel();
			pn2.setBackground(Color.WHITE);
			pn2.setBorder(new EmptyBorder(0, 0, 0, 0));
			pn2.setBounds(437, 294, 750, 53);
			pn2.setLayout(new GridLayout(1, 0, 0, 0));

		}
		return pn2;
	}

	private void createButtons() {
		// first panel
		for (int i = 3; i < 37; i = i + 3) {
			pn0.add(newButton(i));
		}
		// second panel
		for (int i = 2; i < 36; i = i + 3) {
			pn1.add(newButton(i));
		}
		// third panel
		for (int i = 1; i < 35; i = i + 3) {
			pn2.add(newButton(i));
		}
	}

	private JButton newButton(Integer i) {
		JButton button = new JButton();
		button.setText(String.valueOf(i));
		button.setBorder(new LineBorder(Color.WHITE, 1));

		button.setActionCommand(i.toString());
		button.addActionListener(clickButton);

		if (bets.isRed(i)) {
			button.setBackground(Color.RED);
			button.setForeground(Color.WHITE);
		} else {
			button.setBackground(Color.BLACK);
			button.setForeground(Color.WHITE);
		}

		return button;
	}

	class ClickButton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// if the button is not enabled, game finished, can't click buttons
			if (!btnRoulette.isEnabled()) {
				txCruppier.setText("You can't place more bets in this game.");
			}
			// if we have reached the maximum number of bets
			else if (bets.noMoreBets()) {
				txCruppier
						.setText("I will not accept any more bets. Turn the wheel!");
			} else {
				JButton button = (JButton) e.getSource();
				String betMoney = JOptionPane
						.showInputDialog("How much will you bet? 5, 10, 50, 100, 500 or 1000€ ?");
				// if the input is a number
				if (betMoney.matches("-?[0-9]+")) {
					double money = Double.parseDouble(betMoney);
					// if it is lower than the minimum bet
					if (money < 5) {
						JOptionPane.showMessageDialog(null,
								"You must bet at least 5€");
					} else if (!bets.isAcceptedValue(money)) {
						JOptionPane.showMessageDialog(null,
								"Please introduce a valid amount.");
					} else {
						JOptionPane.showMessageDialog(null, "Value accepted!");
						String betType = detectBet(button);

						Bet bet = new Bet(detectBet(button), money, null, null);

						if (betType == Bet.STRAIGHT_UP) {
							bet = new Bet(Bet.STRAIGHT_UP, money,
									Integer.parseInt(button.getText()), null);
							updateBet(button, money, bet);
						} else if (betType == Bet.COLUMN) {
							bet = createColumnBet(button, money, bet);
							updateBet(button, money, bet);
						} else if (betType == Bet.CORNER_BET) {
							bet = createCornerBet(button, money, bet);
						} else {
							bet = new Bet(detectBet(button), money, null, null);
							updateBet(button, money, bet);
						}
					}
				} else {
					// if we receive an invalid number (string)
					JOptionPane.showMessageDialog(null,
							"You can't introduce letters.");
				}
			}

		}

		private void updateBet(JButton button, double money, Bet bet) {
			String betType = bet.getType();
			bets.add(bet);
			setImage(button, "/img/" + money + ".png");
			txMoneyLeft.setText(String.valueOf(bets.getTotalMoney()));

			if (betType == Bet.COLUMN) {
				currentBet += money + "€ to " + bet.getType() + " "
						+ bet.getColumn() + ".\n";
			} else if (betType == Bet.STRAIGHT_UP) {
				currentBet += money + "€ to " + bet.getType() + " number  "
						+ bet.getNumber() + ".\n";
			} else if (betType == Bet.CORNER_BET) {
				currentBet += money + "€ to " + bet.getType() + ":  "
						+ bets.getCornerBet(bet.getNumber()) + ".\n";
			} else {
				currentBet += money + "€ to " + bet.getType() + ".\n";
			}

			txCruppier.setText(currentBet);

		}

		private Bet createCornerBet(JButton button, double money, Bet bet) {
			String corner = JOptionPane
					.showInputDialog("Tell me the number on the lower left corner.");
			if (corner.matches("-?[0-9]+")) {
				int cornerNumber = Integer.parseInt(corner);
				if (bets.isAcceptableCornerNumber(cornerNumber)) {
					bet = new Bet(Bet.CORNER_BET, money, cornerNumber, null);
					updateBet(button, money, bet);
				} else {
					JOptionPane
							.showMessageDialog(null,
									"Please introduce only numbers from the second or third column.");
				}
			} else {
				JOptionPane.showMessageDialog(null,
						"Please introduce only numbers");
			}
			return bet;
		}

		private Bet createColumnBet(JButton button, double money, Bet bet) {
			if (button.getText() == btn1Column.getText()) {
				bet = new Bet(Bet.COLUMN, money, null, 1);
			} else if (button.getText() == btn2Column.getText()) {
				bet = new Bet(Bet.COLUMN, money, null, 2);
			} else if (button.getText() == btn3Column.getText()) {
				bet = new Bet(Bet.COLUMN, money, null, 3);
			}
			return bet;
		}

		private void setImage(JButton button, String source) {
			Image originalImg = new ImageIcon(getClass().getResource(source))
					.getImage();
			Image adaptedImg = originalImg.getScaledInstance(50, 50,
					Image.SCALE_SMOOTH);
			button.setIcon(new ImageIcon(adaptedImg));
		}

		private String detectBet(JButton button) {
			String text = button.getText();
			if (text.contains("column")) {
				return Bet.COLUMN;
			}
			if (text.matches("[0-9]+")) {
				return Bet.STRAIGHT_UP;
			} else {
				return text;
			}
		}

	}

	private void startGame() {

		updateSettings();
		txCruppier.setText("Place your bets! You can place "
				+ BetManager.NUMBER_OF_BETS + " bets.");
		txNumber.setText("");
		currentBet = "";

		bets = new BetManager(totalMoney);

		removePictureButtons();
		btnRoulette.setEnabled(true);

	}

	private void removePictureButtons() {
		for (int i = 0; i < pn0.getComponentCount(); i++) {
			JButton button0 = (JButton) pn0.getComponents()[i];
			JButton button1 = (JButton) pn1.getComponents()[i];
			JButton button2 = (JButton) pn2.getComponents()[i];
			button0.setIcon(null);
			button1.setIcon(null);
			button2.setIcon(null);
		}
		for (int i = 0; i < pnButtons.getComponentCount(); i++) {
			JButton button = (JButton) pnButtons.getComponents()[i];
			button.setIcon(null);
		}

		for (int i = 0; i < pnColumn.getComponentCount(); i++) {
			JButton button = (JButton) pnColumn.getComponents()[i];
			button.setIcon(null);
		}

		btn0.setIcon(null);

	}

	private void showDialog(StartDialog startDialog) {
		startDialog = new StartDialog(this);
		startDialog.setLocationRelativeTo(null);
		startDialog.setVisible(true);

	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}

	private JMenuItem getMntmPlayerSettings() {
		if (mntmPlayerSettings == null) {
			mntmPlayerSettings = new JMenuItem("Player Settings");
			mntmPlayerSettings.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					showDialog(startDialog);
					updateSettings();
				}
			});
			mntmPlayerSettings.setMnemonic('P');
		}
		return mntmPlayerSettings;
	}

	public String getPlayerName() {
		return playerName;
	}

	public double getTotalMoney() {
		return totalMoney;
	}

	private void updateSettings() {
		txMoneyLeft.setText(String.valueOf(totalMoney));
		lblPlayerName.setText(playerName);
		bets = new BetManager(totalMoney);
	}
}
