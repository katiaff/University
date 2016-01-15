package logic.tripelements;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShipCreator {
	private List<Ship> ships;

	public ShipCreator() {
		ships = new ArrayList<Ship>();
		parseFile();
	}

	private void parseFile() {
		String line = "";
		try {
			BufferedReader file = new BufferedReader(new FileReader("files/barcos.dat"));
			while (file.ready()) {
				line = file.readLine();
				String[] parts = line.split(";");
				String[] stringCabins = new String[] { parts[3], parts[4], parts[5], parts[6] };
				String[] stringPriceCabins = new String[] { parts[7], parts[8], parts[9], parts[10] };
				int[] numCabins = new int[stringCabins.length];
				int[] priceCabins = new int[stringPriceCabins.length];
				for (int i = 0; i < stringCabins.length; i++) {
					numCabins[i] = Integer.parseInt(stringCabins[i]);
					priceCabins[i] = Integer.parseInt(stringPriceCabins[i]);
				}
				Ship toAdd = new Ship(parts[0], parts[1], parts[2], numCabins, priceCabins);
				ships.add(toAdd);
			}
			file.close();
		} catch (FileNotFoundException e) {
			e.getMessage();
			e.printStackTrace();
		} catch (IOException e) {
			e.getMessage();
			e.printStackTrace();
		}
	}

	public List<Ship> getShips() {
		return ships;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (Ship s : ships) {
			builder.append(s.toString());
			builder.append("\n");
		}
		return builder.toString();
	}

}
