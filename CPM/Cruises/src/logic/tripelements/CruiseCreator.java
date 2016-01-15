package logic.tripelements;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import logic.util.DateUtilities;

public class CruiseCreator {
	private List<Cruise> cruises;

	public CruiseCreator() {
		cruises = new ArrayList<Cruise>();
		parseFile();
	}

	private void parseFile() {
		String line = "";
		try {
			BufferedReader file = new BufferedReader(new FileReader("files/cruceros.dat"));
			while (file.ready()) {
				line = file.readLine();
				String[] parts = line.split(";");
				List<Date> dates = parseDates(parts[8]);
				boolean children = true;
				if (parts[6] == "N"){
					children = false;
				}			
				Cruise toAdd = new Cruise(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], children,
						parts[7], dates, parts[9]);
				cruises.add(toAdd);
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
	
	private List<Date> parseDates(String dates) {
		List<Date> ret = new ArrayList<Date>();
		String[] split = dates.split("%");
		for (String s : split) {
			Date date = DateUtilities.parseDate(s);
			ret.add(date);
		}
		return ret;

	}

	public List<Cruise> getCruises() {
		return cruises;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (Cruise c : cruises) {
			builder.append(c.toString());
			builder.append("\n");
		}
		return builder.toString();
	}

}
