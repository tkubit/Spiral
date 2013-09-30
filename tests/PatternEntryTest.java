import static org.junit.Assert.*;

import org.junit.Test;

import java.awt.Point;

public class PatternEntryTest {

	@Test
	public void patternEntryShouldBeInitializedEmptyAtOrigin() {
		
		PatternEntry entry = new PatternEntry();
		assertEquals("", entry.value());
		assertEquals(new Point(0,0), entry.location());
	}
	
	@Test
	public void patternEntryShouldInitializeLocationAndValue()
	{
		PatternEntry entry = new PatternEntry(new Point(1,2), 3);
		assertEquals("3", entry.value());
		assertEquals(new Point(1,2), entry.location());
	}
	
	@Test
	public void locationShouldBePointSet()
	{
		PatternEntry entry = new PatternEntry();
		entry.setLocation(23, -7);
		assertEquals(new Point(-7,23), entry.location());		
	}
	
	@Test
	public void valueShouldBeStringizedInteger()
	{
		PatternEntry entry = new PatternEntry();
		entry.setValue(-5);
		assertEquals("-5", entry.value());
	}
}
