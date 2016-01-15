package logic.implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

import gui.dialogs.PaymentDialog;
import logic.tripelements.Cruise;
import logic.tripelements.CruiseCreator;
import logic.tripelements.Extra;
import logic.tripelements.ExtraCreator;
import logic.tripelements.Ship;
import logic.tripelements.ShipCreator;
import logic.util.FileDataCorruptException;
import logic.util.DateUtilities;

public class Agency {
	private List<Cruise> cruises;
	private static List<Ship> ships;
	private List<Extra> extras;
	private List<Cruise> discountedCruises;
	private List<BookedCabin> bookedCabins;
	private String departure;
	private Cruise currentCruise;

	public Agency() {
		loadInfo();
		randomDiscount();
		bookedCabins = new ArrayList<BookedCabin>();
	}

	public void randomDiscount() {
		ArrayList<Cruise> discounted = new ArrayList<Cruise>();
		int limit = cruises.size();

		Random generator = new Random();
		int random = generator.nextInt(limit);

		discounted.add(cruises.get(random));
		do {
			random = generator.nextInt(limit);
		} while (discounted.contains(cruises.get(random)));
		discounted.add(cruises.get(random));

		for (Cruise c : discounted) {
			Ship ship = searchShips(c.getShip());
			int[] prices = ship.getPriceCabins();
			int[] newPrices = new int[4];
			for (int i = 0; i < prices.length; i++) {
				newPrices[i] = (int) (prices[i] * 0.85);
			}
			ship.setPriceCabins(newPrices);
		}

		this.discountedCruises = discounted;
	}

	private void loadInfo() {
		CruiseCreator cc = new CruiseCreator();
		cruises = cc.getCruises();
		ShipCreator sc = new ShipCreator();
		ships = sc.getShips();
		ExtraCreator ec = new ExtraCreator();
		extras = ec.getExtras();
	}

	public List<Cruise> getDiscountedCruises() {
		return discountedCruises;
	}

	public List<Cruise> getCruises() {
		return cruises;
	}

	public List<Ship> getShips() {
		return ships;
	}

	public List<Extra> getExtras() {
		return extras;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public Cruise getCurrentCruise() {
		return currentCruise;
	}

	public void setCurrentCruise(Cruise currentCruise) {
		this.currentCruise = currentCruise;
	}

	public Cruise getBookedCabinsCurrentCruise() throws FileNotFoundException, IOException {
		String fileName = "files/bookedCabins.dat";
		try {
			BufferedReader file = new BufferedReader(new FileReader(fileName));
			String line = file.readLine();
			String[] parts = line.split(BookedCabin.FORMAT_SEPARATOR);
			Cruise cruise = searchCruises(parts[0]);
			file.close();
			return cruise;
		} catch (FileNotFoundException fnfe) {
			throw new FileNotFoundException();
		} catch (IOException ioe) {
			throw new IOException();
		}

	}

	public Cruise searchCruises(String code) {
		for (Cruise c : cruises) {
			if (c.getCode().equals(code)) {
				return c;
			}
		}
		return null;
	}


	public Cruise searchCruisesName(String name) {
		for (Cruise c : cruises) {
			if (c.getName().equals(name)) {
				return c;
			}
		}
		return null;
	}
	
	public static Ship searchShips(String code) {
		for (Ship s : ships) {
			if (s.getCode().equals(code)) {
				return s;
			}
		}
		return null;
	}

	public Extra searchExtras(String name) {
		for (Extra e : extras) {
			if (e.getName().equals(name)) {
				return e;
			}
		}
		return null;
	}
	
	private Extra searchExtrasCode(String code) {
		for (Extra e : extras) {
			if (e.getCode().equals(code)) {
				return e;
			}
		}
		return null;
	}


	public List<BookedCabin> getBookedCabins() {
		return bookedCabins;
	}

	public void addBookedCabin(BookedCabin bc) {
		bookedCabins.add(bc);
	}

	public void removeBookedCabin(BookedCabin bc) {
		bookedCabins.remove(bc);
	}

	public int createFile(List<BookedCabin> list) {
		String fileName = "files/bookedCabins.dat";
		try {
			BufferedWriter file = new BufferedWriter(new FileWriter(fileName));
			for (BookedCabin bc : list) {
				file.write(bc.formattedCabin());
				file.write("\n");
			}
			file.close();
			return 0;

		} catch (FileNotFoundException fnfe) {
			System.out.println("El archivo no se ha podido guardar");
			return -1;
		} catch (IOException ioe) {
			new RuntimeException("Error de entrada/salida.");
			return -1;
		}
	}

	public List<BookedCabin> restoreFile(Locale locale) {
		String fileName = "files/bookedCabins.dat";
		List<BookedCabin> ret = new ArrayList<BookedCabin>();
		try {
			BufferedReader file = new BufferedReader(new FileReader(fileName));
			while (file.ready()) {
				String line = file.readLine();
				String[] parts = line.split(BookedCabin.FORMAT_SEPARATOR);
				Cruise cruise = searchCruises(parts[0]);
				String typeCabin = parts[1];
				int numPeople = Integer.parseInt(parts[2]);
				Boolean[] bools = new Boolean[5];
				for (int i = 0; i < 5; i++) {
					bools[i] = Boolean.parseBoolean(parts[3 + i]);
				}
				Date[] dates = new Date[5];
				for (int i = 0; i < 5; i++) {
					dates[i] = new Date(Long.parseLong(parts[8 + i]));
				}
				List<Extra> ext = new ArrayList<Extra>();
				if (parts.length > 12) {
					for (int i = 13; i < parts.length; i++) {
						ext.add(searchExtrasCode(parts[i]));
					}
				}
				BookedCabin bc = new BookedCabin(typeCabin, numPeople, bools, dates, ext, locale, cruise);
				ret.add(bc);
			}
			file.close();
			return ret;

		} catch (FileNotFoundException fnfe) {
			System.err.println("FileNotFound Exception");
			fnfe.printStackTrace();
			return null;
		} catch (IOException ioe) {
			new RuntimeException("IOException");
			ioe.printStackTrace();
			return null;
		}
	}

	public int getNumPeople() {
		int num = 0;
		for (BookedCabin cabin : getBookedCabins()) {
			num += cabin.getNumPeople();
		}
		return num;
	}

	public int getTotalPrice() throws FileDataCorruptException {
		int price = 0;
		for (int i = 0; i < bookedCabins.size(); i++) {
			BookedCabin bc = bookedCabins.get(i);
			price += bc.getTotalPrice();
		}
		return price;
	}

	public int getPriceCabins() throws FileDataCorruptException {
		int price = 0;
		for (int i = 0; i < bookedCabins.size(); i++) {
			BookedCabin bc = bookedCabins.get(i);
			price += bc.getPriceCabin();
		}
		return price;
	}

	public int getPriceExtras() {
		int price = 0;
		for (int i = 0; i < bookedCabins.size(); i++) {
			BookedCabin bc = bookedCabins.get(i);
			price += bc.getPriceExtras();
		}
		return price;
	}

	public String getBookingInfo(Locale locale) throws FileDataCorruptException {
		ResourceBundle texts = ResourceBundle.getBundle("resources.Agency", locale);
		Currency currency = Currency.getInstance(locale);
		String currencySymbol = currency.getSymbol();
		StringBuilder sb = new StringBuilder();
		sb.append(texts.getString("cruise"));
		sb.append(currentCruise.getName());
		sb.append("/" + currentCruise.getCode());
		sb.append("\n");
		sb.append(texts.getString("ship"));
		sb.append(Agency.searchShips(currentCruise.getShip()).getName());
		sb.append("\n");
		sb.append(texts.getString("departure"));
		sb.append(departure);
		sb.append("\n");
		sb.append(texts.getString("days"));
		sb.append(currentCruise.getDuration());
		sb.append("\n");
		sb.append(texts.getString("start"));
		sb.append(currentCruise.getStart());
		sb.append("\n");
		sb.append(texts.getString("numPeople"));
		sb.append(getNumPeople());
		sb.append("\n");
		sb.append(texts.getString("cabinInfo") + " ");
		for (BookedCabin cabin : getBookedCabins()) {
			sb.append(cabin.simpleDescription() + " ");
		}
		sb.append("\n");
		sb.append(texts.getString("totalPrice"));
		sb.append(getTotalPrice() + " " + currencySymbol);
		sb.append("\n");

		return sb.toString();

	}

	public String getReceipt(String name, String ID, String phone, Locale locale, String additionalInfo, byte payment)
			throws FileNotFoundException, IOException, FileDataCorruptException {
		ResourceBundle texts = ResourceBundle.getBundle("resources.Agency", locale);
		Currency currency = Currency.getInstance(locale);
		String currencySymbol = currency.getSymbol();
		StringBuilder sb = new StringBuilder();
		appendHeader(texts, sb);
		appendCruiseData(texts, sb);
		appendPaid(texts, currencySymbol, sb);
		appendPaymentMehotd(texts, payment, sb);

		if (additionalInfo != null) {
			appendAdditionalInfo(additionalInfo, texts, sb);
		}

		createReceiptFile(sb, ID);

		return sb.toString();

	}

	private void appendPaymentMehotd(ResourceBundle texts, byte payment, StringBuilder sb) {
		sb.append("\n");
		if (payment == PaymentDialog.CASH) {
			sb.append(texts.getString("cash"));
		} else {
			sb.append(texts.getString("card"));
		}
	}

	private void createReceiptFile(StringBuilder sb, String ID) throws FileNotFoundException, IOException {
		Date now = new Date(System.currentTimeMillis());
		String fileName = ID + "_" + DateUtilities.serializeWithDashes(now);
		String completeFileName = "files/" + fileName + ".txt";
		File file = new File(completeFileName);
		int num = 1;
		while (file.isFile()) {
			completeFileName = "files/" + fileName + "_" + num + ".txt";
			file = new File(completeFileName);
			num++;
		}
		try {
			BufferedWriter buff = new BufferedWriter(new FileWriter(completeFileName));
			buff.write(sb.toString());
			buff.close();
		} catch (FileNotFoundException fnfe) {
			throw new FileNotFoundException();
		} catch (IOException ioe) {
			throw new IOException();
		}

	}

	private void appendAdditionalInfo(String additionalInfo, ResourceBundle texts, StringBuilder sb) {
		sb.append("\n");
		sb.append("\n");
		sb.append(texts.getString("additionalInfo"));
		sb.append("\n");
		sb.append("\n");
		sb.append(additionalInfo);
	}

	private void appendPaid(ResourceBundle texts, String currencySymbol, StringBuilder sb)
			throws FileDataCorruptException {
		sb.append(texts.getString("paid"));
		sb.append("\n");
		sb.append("\n");
		sb.append(texts.getString("cabins"));
		sb.append("\n");
		sb.append("\t");
		sb.append(getOriginalPriceCabins() + " " + currencySymbol);
		sb.append("\n");
		sb.append(texts.getString("extras"));
		sb.append("\n");
		sb.append("\t");
		sb.append(getPriceExtras() + " " + currencySymbol);
		sb.append("\n");
		if (discountedCruises.contains(currentCruise)) {
			sb.append(texts.getString("discount"));
			sb.append("\n");
			sb.append("\t");
			sb.append(getPriceDiscounted() + " " + currencySymbol);
			sb.append("\n");
		}
		sb.append(texts.getString("totalPrice"));
		sb.append("\n");
		sb.append("\t");
		sb.append("(" + getOriginalPriceCabins() + " - " + getPriceDiscounted() + ")" + " + " + getPriceExtras() + " = "
				+ getTotalPrice() + " " + currencySymbol);
	}

	private void appendCruiseData(ResourceBundle texts, StringBuilder sb) {
		sb.append(texts.getString("cruise"));
		sb.append(currentCruise.getName());
		sb.append("/" + currentCruise.getCode());
		sb.append("\n");
		sb.append(texts.getString("ship"));
		sb.append(Agency.searchShips(currentCruise.getShip()).getName());
		sb.append("\n");
		sb.append(texts.getString("departure"));
		sb.append(departure);
		sb.append("\n");
		sb.append(texts.getString("days"));
		sb.append(currentCruise.getDuration());
		sb.append("\n");
		sb.append(texts.getString("start"));
		sb.append(currentCruise.getStart());
		sb.append("\n");
		sb.append(texts.getString("numPeople"));
		sb.append(getNumPeople());
		sb.append("\n");
		sb.append(texts.getString("cabinInfo") + " ");
		for (BookedCabin cabin : getBookedCabins()) {
			sb.append(cabin.simpleDescription() + " ");
		}
		sb.append("\n");
		sb.append("\n");
	}

	private void appendHeader(ResourceBundle texts, StringBuilder sb) {
		sb.append(texts.getString("receipt"));
		Date now = new Date(System.currentTimeMillis());
		sb.append(DateUtilities.serialize(now));
		sb.append("\n");
		sb.append(texts.getString("dashes"));
		sb.append("\n");
		sb.append("\n");
		sb.append(texts.getString("data"));
		sb.append("\n");
		sb.append("\n");
	}

	private int getOriginalPriceCabins() throws FileDataCorruptException {
		int price = 0;
		for (int i = 0; i < bookedCabins.size(); i++) {
			BookedCabin bc = bookedCabins.get(i);
			price += (bc.getPriceCabin() * 1.15);
		}
		return price;
	}

	private int getPriceDiscounted() throws FileDataCorruptException {
		int priceDiscounted = 0;
		for (int i = 0; i < bookedCabins.size(); i++) {
			BookedCabin bc = bookedCabins.get(i);
			int price = bc.getPriceCabin();
			priceDiscounted += Math.floor(price * 0.15);
		}
		return priceDiscounted;
	}

	public void setBookedCabins(List<BookedCabin> list) {
		this.bookedCabins = list;

	}

	public List<Cruise> filterCruises(String dateString, int rangeLow, int rangeHigh, String area) {
		List<Cruise> ret = new ArrayList<Cruise>();
		Date date = DateUtilities.parseDate(dateString);
		for (int i = 0; i < cruises.size(); i++) {
			Cruise cruise = cruises.get(i);
			if (cruise.getArea().equals(area)) {
				if (cruise.getDates().contains(date)) {
					Ship ship = searchShips(cruise.getShip());
					int minPrice = Integer.MAX_VALUE;
					for (int j = 0; j < ship.getPriceCabins().length; j++) {
						int currentPrice = ship.getPriceCabins()[j];
						if (currentPrice < minPrice && currentPrice != 0) {
							minPrice = currentPrice;
						}
					}
					if (minPrice >= rangeLow && minPrice <= rangeHigh) {
						ret.add(cruise);
					}
				}
			}
		}

		return ret;

	}

	public int getMaxPriceCabin() {
		int max = 0;
		for (int i = 0; i < ships.size(); i++) {
			Ship ship = ships.get(i);
			for (int j = 0; j < ship.getPriceCabins().length; j++) {
				int currentPrice = ship.getPriceCabins()[j];
				if (currentPrice > max) {
					max = currentPrice;
				}
			}
		}
		return max;
	}

	public String[] getAvailableDates() {
		List<String> list = new ArrayList<String>();
		for (Cruise cruise : cruises) {
			List<Date> dates = cruise.getDates();
			for (Date date : dates) {
				String serialized = DateUtilities.serialize(date);
				if (!list.contains(serialized)) {
					list.add(serialized);
				}
			}
		}
		String[] ret = new String[list.size()];
		ret = list.toArray(ret);
		return ret;
	}

	public String[] getAvailableDatesCruise() {
		List<Date> dates = currentCruise.getDates();
		String[] options = new String[dates.size()];
		for (int i = 0; i < options.length; i++) {
			Date date = dates.get(i);
			options[i] = DateUtilities.serialize(date);
		}
		return options;
	}

	public String[] getAvailableAreas() {
		List<String> list = new ArrayList<String>();
		for (Cruise cruise : cruises) {
			String area = cruise.getArea();
			if (!list.contains(area)) {
				list.add(area);
			}
		}
		String[] ret = new String[list.size()];
		ret = list.toArray(ret);
		return ret;
	}

}
