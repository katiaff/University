package logic.tripelements;

import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

public class Ship {
	private String code;
	private String name;
	private String description;
	private int[] numCabins;
	private int[] priceCabins;

	public Ship(String code, String name, String description, int[] cabins, int[] priceCabins) {
		this.code = code;
		this.name = name;
		this.description = description;

		numCabins = new int[4];
		for (int i = 0; i < cabins.length; i++) {
			numCabins[i] = cabins[i];
		}

		this.priceCabins = new int[4];
		for (int i = 0; i < cabins.length; i++) {
			this.priceCabins[i] = priceCabins[i];
		}
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int[] getCabins() {
		return numCabins;
	}

	public int[] getPriceCabins() {
		return priceCabins;
	}

	public void setPriceCabins(int[] priceCabins) {
		this.priceCabins = priceCabins;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Ship [code=");
		builder.append(code);
		builder.append(", name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append(", numCabins=");
		builder.append(Arrays.toString(numCabins));
		builder.append(", priceCabins=");
		builder.append(Arrays.toString(priceCabins));
		builder.append("]");
		return builder.toString();
	}

	public String getAvailableCabinsString(Locale locale) {
		StringBuilder sb = new StringBuilder();
		ResourceBundle texts = ResourceBundle.getBundle("resources.Ship", locale);
		int price = getPriceCabins()[0];
		if (price != 0) {
			sb.append(texts.getString("interiorDoubleCabin") + " - " + texts.getString("price") + price + "\n");
		}
		price = getPriceCabins()[1];
		if (price != 0) {
			sb.append(texts.getString("exteriorDoubleCabin") + " - " + texts.getString("price") + +price + "\n");
		}
		price = getPriceCabins()[2];
		if (price != 0) {
			sb.append(texts.getString("interiorFamilyCabin") + " - " + texts.getString("price") + +price + "\n");
		}
		price = getPriceCabins()[3];
		if (price != 0) {
			sb.append(texts.getString("exteriorFamilyCabin") + " - " + texts.getString("price") + +price + "\n");
		}
		return sb.toString();
	}

}
