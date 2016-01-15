package logic.tripelements;

import java.util.Date;
import java.util.List;

public class Cruise {
	private String code;
	private String area;
	private String name;
	private String start;
	private String itinerary;
	private String description;
	private Boolean children;
	private int duration;
	private List<Date> dates;
	private String ship;

	public Cruise(String code, String area, String name, String start, String itinerary, String description,
			boolean children, String duration, List<Date> dates, String ship) {
		super();
		this.code = code;
		this.area = area;
		this.name = name;
		this.start = start;
		this.description = description;
		this.itinerary = itinerary;
		this.children = true;
		this.duration = Integer.parseInt(duration);
		this.dates = dates;
		this.ship = ship;
	}

	public String getCode() {
		return code;
	}

	public String getArea() {
		return area;
	}

	public String getName() {
		return name;
	}

	public String getStart() {
		return start;
	}

	public String getItinerary() {
		return itinerary;
	}

	public String getDescription() {
		return description;
	}

	public boolean getChildren() {
		return children;
	}

	public int getDuration() {
		return duration;
	}

	public String getShip() {
		return ship;
	}

	public void setShip(String ship) {
		this.ship = ship;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Cruise [code=");
		builder.append(code);
		builder.append(", area=");
		builder.append(area);
		builder.append(", name=");
		builder.append(name);
		builder.append(", start=");
		builder.append(start);
		builder.append(", itinerary=");
		builder.append(itinerary);
		builder.append(", description=");
		builder.append(description);
		builder.append(", children=");
		builder.append(children);
		builder.append(", duration=");
		builder.append(duration);
		builder.append(", dates=");
		builder.append(dates);
		builder.append(", ship=");
		builder.append(ship);
		builder.append("]");
		return builder.toString();
	}

	public List<Date> getDates() {
		return dates;
	}

}