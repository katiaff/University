package logic.implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Client {
	private final static String SEPARATOR = ";";
	private String name;
	private String surname;
	private String ID;
	private String phone;
	private String cardNumber;
	private int month;
	private int year;
	private String CVC;
	private String additionalInfo;

	public Client(String name, String surname, String iD, String phone, String cardNumber, int month, int year,
			String cVC, String additionalInfo) {
		super();
		this.name = name;
		this.surname = surname;
		ID = iD;
		this.phone = phone;
		this.cardNumber = cardNumber;
		this.month = month;
		this.year = year;
		CVC = cVC;
		this.additionalInfo = additionalInfo;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getID() {
		return ID;
	}

	public String getPhone() {
		return phone;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}

	public String getCVC() {
		return CVC;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void saveInfo() {
		String fileName = "files/client.dat";
		try {
			BufferedWriter file = new BufferedWriter(new FileWriter(fileName));
			file.write(name + SEPARATOR);
			file.write(surname + SEPARATOR);
			file.write(ID + SEPARATOR);
			file.write(phone + SEPARATOR);
			file.write(cardNumber + SEPARATOR);
			file.write(month + SEPARATOR);
			file.write(year + SEPARATOR);
			file.write(CVC + SEPARATOR);
			file.write(additionalInfo + SEPARATOR);
			file.close();

		} catch (FileNotFoundException fnfe) {

		} catch (IOException ioe) {

		}
	}

	public void loadInfo() {
		String fileName = "files/client.dat";
		try {
			BufferedReader file = new BufferedReader(new FileReader(fileName));
			while (file.ready()) {
				String line = file.readLine();
				String[] parts = line.split(SEPARATOR);
				this.name = parts[0];
				this.surname = parts[1];
				this.ID = parts[2];
				this.phone = parts[3];
				this.cardNumber = parts[4];
				this.month = Integer.parseInt(parts[5]);
				this.year = Integer.parseInt(parts[6]);
				this.CVC = parts[7];
				this.additionalInfo = parts[8];
			}
			file.close();

		} catch (FileNotFoundException fnfe) {

		} catch (IOException ioe) {

		}
	}
}
