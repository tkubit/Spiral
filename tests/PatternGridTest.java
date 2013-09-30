import static org.junit.Assert.*;
import org.junit.Test;

import java.awt.Point;

public class PatternGridTest {

	@Test
	public void PatternGridShouldHaveEmptyEntriesByDefault()
	{
		PatternGrid grid = new PatternGrid();
		assertFalse(grid.hasEntries());
	}
	
	@Test
	public void atShoudlReturnEmptyEntryIfNoneExistAtGivenLocation()
	{
		PatternGrid grid = new PatternGrid();
		PatternEntry entry = grid.at(23, 88);
		assertEquals("", entry.value());
		assertEquals("Location should still be the location requested", new Point(88, 23), entry.location());
	}
	
	@Test
	public void addShouldPutEntriesInSet()
	{
		PatternGrid grid = new PatternGrid();
		grid.add(new PatternEntry(new Point(1,2), 3));
		assertTrue(grid.hasEntries());
	}
	
	@Test
	public void atShoudGetEntryFromGivenLocation()
	{
		PatternGrid grid = new PatternGrid();
		PatternEntry first = new PatternEntry(new Point(1,2), 3);
		PatternEntry second = new PatternEntry(new Point(11,22), 33);
		grid.add(first);
		grid.add(second);
		assertSame(first, grid.at(2, 1));
		assertSame(second, grid.at(22, 11));
	}
	
	@Test
	public void addShouldReplaceExistingEntryIfNewEntryIsSameLocation()
	{
		PatternGrid grid = new PatternGrid();
		grid.add(new PatternEntry(new Point(1,2), 3));
		grid.add(new PatternEntry(new Point(1,2), 99));
		assertEquals("99", grid.at(2, 1).value());	
	}
	
	@Test
	public void allBoundaryIndexesShouldBeZeroForEmptyGrid()
	{
		PatternGrid grid = new PatternGrid();
		assertEquals(0, grid.firstRowIndex());
		assertEquals(0, grid.lastRowIndex());
		assertEquals(0, grid.firstColumnIndex());
		assertEquals(0, grid.lastColumnIndex());
	}
	
	private void addEntriesTo(PatternGrid grid)
	{
		grid.add(new PatternEntry(new Point(1,4), 3));
		grid.add(new PatternEntry(new Point(11,-22), 33));
		grid.add(new PatternEntry(new Point(5, -4), 1));
	}
	
	@Test 
	public void firstRowIndexShouldBeLowestYLocation()
	{
		PatternGrid grid = new PatternGrid();
		this.addEntriesTo(grid);
		assertEquals(-22, grid.firstRowIndex());
	}
	
	@Test 
	public void lastRowIndexShouldBeHighestYLocation()
	{
		PatternGrid grid = new PatternGrid();
		this.addEntriesTo(grid);
		assertEquals(4, grid.lastRowIndex());
	}
	
	@Test 
	public void firstColumnIndexShouldBeLowestXLocation()
	{
		PatternGrid grid = new PatternGrid();
		this.addEntriesTo(grid);
		assertEquals(1, grid.firstColumnIndex());
	}
	
	@Test 
	public void lastColumnIndexShouldBeHighestXLocation()
	{
		PatternGrid grid = new PatternGrid();
		this.addEntriesTo(grid);
		assertEquals(11, grid.lastColumnIndex());
	}
}
