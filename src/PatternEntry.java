import java.awt.Point;

public class PatternEntry {

	private Point location;
	private String value;
	
	public PatternEntry()
	{
		this.setLocation(0, 0);
		value = "";
	}
	
	public PatternEntry(Point initialLocation, int initialValue)
	{
		location = initialLocation;
		this.setValue(initialValue);
	}
	
	public void setLocation(int row, int col)
	{
		location = new Point(col, row);
	}
	
	public Point location()
	{
		return location;
	}
	
	public void setValue(int newValue)
	{
		value = String.valueOf(newValue);
	}
	
	public String value()
	{
		return value;
	}
}
