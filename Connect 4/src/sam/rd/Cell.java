package sam.rd;

public class Cell {
	private ColorState color;
	
	public Cell(ColorState cs) {
		color = cs;
	}
	
	
	/**
	 * set the color of any cell selected
	 * @param cs
	 */
	public void setColor (ColorState cs){
		color = cs;
	}
	
	/**
	 * gets the color of any cell selected
	 * @return color (returns a ColorState enum)
	 */
	public ColorState getColor() {
		return color;
	}

	/**
	 * debug method
	 */
	public String toString() {
		switch (color) {
		case RED:
			return "R";
		case BLUE:
			return "B";
		case WHITE:
			return "W";
		
		default:
			return "-";
		}
	}
}

