package logic.tripelements;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExtraCreator {
	private List<Extra> extras;

	public ExtraCreator() {
		extras = new ArrayList<Extra>();
		parseFile();
	}

	private void parseFile() {
		String line = "";
		try {
			BufferedReader file = new BufferedReader(new FileReader("files/extras.dat"));
			while (file.ready()) {
				line = file.readLine();
				String[] parts = line.split(";");
				Extra toAdd = new Extra(parts[0], parts[1], Integer.parseInt(parts[2]));
				extras.add(toAdd);
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

	public List<Extra> getExtras() {
		return extras;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (Extra e : extras) {
			builder.append(e.toString());
			builder.append("\n");
		}
		return builder.toString();
	}
}
