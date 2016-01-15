package logic.implementation;

import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import logic.tripelements.Cruise;
import logic.tripelements.Extra;
import logic.tripelements.Ship;
import logic.util.FileDataCorruptException;
import logic.util.DateUtilities;

public class BookedCabin {
	private static final int ADULT_AGE = 18;
	public final static String FORMAT_SEPARATOR = ";";
	private String typeCabin;
	private int numPeople;
	private Boolean[] booleans;
	private Date[] dates;
	private List<Extra> extras;
	private Locale locale;
	private ResourceBundle texts;
	private Cruise cruise;
	private Currency currency;
	private String currencySymbol;

	public BookedCabin(String typeCabin, int numPeople, Boolean[] booleans, Date[] dates, List<Extra> selExtras,
			Locale locale, Cruise cruise) {
		super();
		this.typeCabin = typeCabin;
		this.numPeople = numPeople;
		this.booleans = booleans;
		this.dates = dates;
		this.extras = selExtras;
		this.locale = locale;
		texts = ResourceBundle.getBundle("resources.BookedCabin", this.locale);
		currency = Currency.getInstance(locale);
		currencySymbol = currency.getSymbol();
		this.cruise = cruise;
	}

	public Cruise getCruise() {
		return cruise;
	}

	public String getTypeCabin() {
		return typeCabin;
	}

	public void setTypeCabin(String typeCabin) {
		this.typeCabin = typeCabin;
	}

	public int getNumPeople() {
		return numPeople;
	}

	public void setNumPeople(int numPeople) {
		this.numPeople = numPeople;
	}

	public Boolean[] getBooleans() {
		return booleans;
	}

	public void setBooleans(Boolean[] booleans) {
		this.booleans = booleans;
	}

	public Date[] getDates() {
		return dates;
	}

	public void setDates(Date[] dates) {
		this.dates = dates;
	}

	public List<Extra> getExtras() {
		return extras;
	}

	public void setExtras(List<Extra> extras) {
		this.extras = extras;
	}

	public ResourceBundle getTexts() {
		return texts;
	}

	public void setTexts(ResourceBundle texts) {
		this.texts = texts;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(typeCabin + " ");
		builder.append(texts.getString("separator"));
		builder.append(numPeople + " ");
		if (numPeople == 1) {
			builder.append(texts.getString("person"));
		} else {
			builder.append(texts.getString("people"));
		}
		return builder.toString();
	}

	public String description() throws FileDataCorruptException {
		StringBuilder builder = new StringBuilder();
		builder.append(texts.getString("typeCabin") + typeCabin + "\n");
		builder.append(texts.getString("numPeople") + numPeople + "\n");
		boolean extraBed = booleans[4];
		appendDates(builder, extraBed);
		builder.append(texts.getString("extras") + getExtraNames() + "\n");
		builder.append(texts.getString("totalPrice") + getTotalPrice() + " " + currencySymbol + "\n");

		return builder.toString();
	}

	private void appendDates(StringBuilder builder, boolean extraBed) {
		String[] formattedDates = new String[5];
		for (int i = 0; i < dates.length; i++) {
			String serialized = DateUtilities.serialize(dates[i]);
			formattedDates[i] = serialized;
		}

		if (numPeople == 1) {
			builder.append(texts.getString("age1") + formattedDates[0] + "\n");
		}
		if (numPeople == 2) {
			if (extraBed) {
				builder.append(texts.getString("age1") + formattedDates[0] + "\n");
				builder.append(texts.getString("extraBed") + formattedDates[4] + "\n");
			} else {
				builder.append(texts.getString("age1") + formattedDates[0] + "\n");
				builder.append(texts.getString("age2") + formattedDates[1] + "\n");
			}
		}
		if (numPeople == 3) {
			if (extraBed) {
				builder.append(texts.getString("age1") + formattedDates[0] + "\n");
				builder.append(texts.getString("age2") + formattedDates[1] + "\n");
				builder.append(texts.getString("extraBed") + formattedDates[4] + "\n");
			} else {
				builder.append(texts.getString("age1") + formattedDates[0] + "\n");
				builder.append(texts.getString("age2") + formattedDates[1] + "\n");
				builder.append(texts.getString("age3") + formattedDates[2] + "\n");
			}
		}
		if (numPeople == 4) {
			if (extraBed) {
				builder.append(texts.getString("age1") + formattedDates[0] + "\n");
				builder.append(texts.getString("age2") + formattedDates[1] + "\n");
				builder.append(texts.getString("age3") + formattedDates[2] + "\n");
				builder.append(texts.getString("extraBed") + formattedDates[4] + "\n");
			} else {
				builder.append(texts.getString("age1") + formattedDates[0] + "\n");
				builder.append(texts.getString("age2") + formattedDates[1] + "\n");
				builder.append(texts.getString("age3") + formattedDates[2] + "\n");
				builder.append(texts.getString("age4") + formattedDates[3] + "\n");
			}
		}
		if (numPeople == 5) {
			builder.append(texts.getString("age1") + formattedDates[0] + "\n");
			builder.append(texts.getString("age2") + formattedDates[1] + "\n");
			builder.append(texts.getString("age3") + formattedDates[2] + "\n");
			builder.append(texts.getString("age4") + formattedDates[3] + "\n");
			builder.append(texts.getString("extraBed") + formattedDates[4] + "\n");
		}
	}

	public String formattedCabin() {
		StringBuilder ret = new StringBuilder();
		ret.append(cruise.getCode() + FORMAT_SEPARATOR);
		ret.append(typeCabin + FORMAT_SEPARATOR);
		ret.append(numPeople + FORMAT_SEPARATOR);
		for (Boolean bool : booleans) {
			ret.append(bool + FORMAT_SEPARATOR);
		}
		for (Date date : dates) {
			ret.append(date.getTime() + FORMAT_SEPARATOR);
		}
		for (Extra extra : extras) {
			ret.append(extra.getCode() + FORMAT_SEPARATOR);
		}
		return ret.toString();
	}

	private String getExtraNames() {
		StringBuilder ret = new StringBuilder();
		for (int i = 0; i < extras.size(); i++) {
			Extra extra = extras.get(i);
			if (i != extras.size() - 1) {
				ret.append(extra.getName() + ", ");
			} else {
				ret.append(extra.getName() + ".");
			}
		}
		if (ret.length() == 0) {
			ret.append(texts.getString("noExtras"));
		}
		return ret.toString();
	}

	public int getTotalPrice() throws FileDataCorruptException {
		int priceRoom = getPriceCabin();
		int priceExtra = getPriceExtras();

		return priceRoom + priceExtra;
	}

	public int getPriceCabin() throws FileDataCorruptException {
		Ship ship = Agency.searchShips(cruise.getShip());
		int priceRoom = 0;
		if (typeCabin.equals(texts.getString("interiorDoubleCabin"))) {
			priceRoom = ship.getPriceCabins()[0];
		} else if (typeCabin.equals(texts.getString("exteriorDoubleCabin"))) {
			priceRoom = ship.getPriceCabins()[1];
		} else if (typeCabin.equals(texts.getString("interiorFamilyCabin"))) {
			priceRoom = ship.getPriceCabins()[2];
		} else if (typeCabin.equals(texts.getString("exteriorFamilyCabin"))) {
			priceRoom = ship.getPriceCabins()[3];
		} else {
			throw new FileDataCorruptException();
		}
		return priceRoom;
	}

	public int getPriceExtras() {
		int priceExtra = 0;
		for (Extra e : extras) {
			priceExtra += e.getPrice();
		}

		return priceExtra;
	}

	public String simpleDescription() {
		StringBuilder builder = new StringBuilder();
		if (!extras.isEmpty()) {
			builder.append(typeCabin + " / ");
			for (int i = 0; i < extras.size(); i++) {
				Extra e = extras.get(i);
				if (i != extras.size() - 1) {
					builder.append(e.getName() + ", ");
				} else {
					builder.append(e.getName() + "; ");
				}
			}
		} else {
			builder.append(typeCabin + "; ");
		}
		return builder.toString();
	}

	public boolean isAdult() {
		for (int i = 0; i < booleans.length; i++) {
			if (booleans[i]) {
				Date date = dates[i];
				Date minimum = new Date(
						System.currentTimeMillis() - TimeUnit.MILLISECONDS.convert(365 * ADULT_AGE, TimeUnit.DAYS));
				if (date.getTime() <= minimum.getTime()) {
					return true;
				}
			}
		}
		return false;
	}

}
