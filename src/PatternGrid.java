import java.util.HashMap;
import java.awt.Point;

public class PatternGrid {

	private HashMap<Point, PatternEntry> entries;
	
	public PatternGrid()
	{
		entries = new HashMap<Point, PatternEntry>();
	}
	
	public boolean hasEntries()
	{
		return ! entries.isEmpty();
	}
	
	public PatternEntry at(int row, int column)
	{
		Point location = new Point(column, row);
		if ( ! entries.containsKey(location))
		{
			PatternEntry empty = new PatternEntry();
			empty.setLocation(row, column);
			return empty;
		}
		
		return entries.get(new Point(column, row));
	}
	
	public void add(PatternEntry newEntry)
	{
		entries.put(newEntry.location(), newEntry);
	}
	
	private interface ISelector { int chooseFrom(int val1, int val2); }
	private class getMin implements ISelector { public int chooseFrom(int val1, int val2) { return Math.min(val1, val2); } }
	private class getMax implements ISelector { public int chooseFrom(int val1, int val2) { return Math.max(val1, val2); } }
	
	private interface IAccessor { int getValueFrom(Point location); }
	private class getRow implements IAccessor { public int getValueFrom(Point location) { return location.y; } }
	private class getColumn implements IAccessor { public int getValueFrom(Point location) { return location.x; } }

	private int getExtremeValue(int seedValue, ISelector valueSelector, IAccessor pointValueSelector )
	{
		if ( ! this.hasEntries())
			return 0;
		
		int extreme = seedValue;
		for (Point location : entries.keySet())
			extreme = valueSelector.chooseFrom(extreme, pointValueSelector.getValueFrom(location));
		
		return extreme;
	}
	
	public int firstRowIndex()
	{
		return this.getExtremeValue(Integer.MAX_VALUE, new getMin(), new getRow());
	}
	
	public int lastRowIndex()
	{
		return this.getExtremeValue(Integer.MIN_VALUE, new getMax(), new getRow());
	}
	
	public int firstColumnIndex()
	{
		return this.getExtremeValue(Integer.MAX_VALUE, new getMin(), new getColumn());
	}
	
	public int lastColumnIndex()
	{
		return this.getExtremeValue(Integer.MIN_VALUE, new getMax(), new getColumn());
	}
}
