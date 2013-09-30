import static org.junit.Assert.*;
import org.junit.Test;

import java.awt.Point;

public class SpiralPatternTest {
	@Test
	public void constructorShouldInitializeAlgorithmStartValues()
	{
		SpiralPattern spiral = new SpiralPattern(7676);
		assertEquals(7676, spiral.endValue());
		assertEquals(new Point(0,0), spiral.currentLocation());
		assertEquals(0, spiral.currentValue());
		assertEquals(SpiralPattern.Direction.East, spiral.currentDirection());
		assertEquals(1, spiral.directionCounter());
	}
	
	@Test
	public void constructorShouldSeedResultsWithZeroEntryAtOrigin()
	{
		SpiralPattern spiral = new SpiralPattern(8);
	}
	
	@Test 
	public void isFinishedShouldBeTrueIfEndValueIsZero()
	{
		SpiralPattern spiral = new SpiralPattern(0);
		assertTrue(spiral.isFinished());
	}
	
	@Test 
	public void isFinishedShouldBeFalseIfCurrentValueIsLessThanEndValue()
	{
		SpiralPattern spiral = new SpiralPattern(1);
		assertFalse(spiral.isFinished());
	}
	
	@Test 
	public void isFinishedShouldBeTrueIfCurrentValueEqualsEndValue()
	{
		SpiralPattern spiral = new SpiralPattern(2);
		assertFalse(spiral.isFinished());
		spiral.next();
		assertFalse(spiral.isFinished());
		spiral.next();
		assertTrue(spiral.isFinished());
	}
	
	@Test
	public void shouldChangeDirectionShouldBeFalseIfDirectionCounterGreaterThanZero()
	{
		SpiralPattern spiral = new SpiralPattern(8);
		assertFalse(spiral.shouldChangeDirection());
	}
	
	@Test
	public void shouldChangeDirectionShouldBeTrueIfDirectionCounterIsZero()
	{
		SpiralPattern spiral = new SpiralPattern(8);
		spiral.decrementDirectionCountdown();
		assertTrue(spiral.shouldChangeDirection());
	}
	
	@Test
	public void changeDirectionShouldProgressEastSouthWestNorthBackToEast()
	{
		SpiralPattern spiral = new SpiralPattern(8);
		assertEquals("Test setup: just making sure initial is as expected", SpiralPattern.Direction.East, spiral.currentDirection());
		
		spiral.changeDirection();
		assertEquals(SpiralPattern.Direction.South, spiral.currentDirection());
		
		spiral.changeDirection();
		assertEquals(SpiralPattern.Direction.West, spiral.currentDirection());
		
		spiral.changeDirection();
		assertEquals(SpiralPattern.Direction.North, spiral.currentDirection());
		
		spiral.changeDirection();
		assertEquals(SpiralPattern.Direction.East, spiral.currentDirection());
	}
	
	@Test
	public void nextLocationShouldAdjustProperLocationCoordinateByOne()
	{
		SpiralPattern spiral = new SpiralPattern(8);
		assertEquals("Test setup: just making sure initial is as expected", new Point(0,0), spiral.currentLocation());
		
		assertEquals(new Point(1, 0), spiral.nextLocation());
		assertEquals("Current location should not be affected", new Point(0,0), spiral.currentLocation());
		
		spiral.changeDirection();
		assertEquals(new Point(0, 1), spiral.nextLocation());
		
		spiral.changeDirection();
		assertEquals(new Point(-1, 0), spiral.nextLocation());
		
		spiral.changeDirection();
		assertEquals(new Point(0, -1), spiral.nextLocation());
	}
	
	
	@Test
	public void updateCountersShouldDecrementCountdownIfNotZero()
	{
		SpiralPattern spiral = new SpiralPattern(8);
		int startingStepValue = spiral.directionStepCount();
		int startingCountdown = spiral.directionCounter();
		spiral.updateCounters();
		assertEquals(startingCountdown-1, spiral.directionCounter());
		assertEquals(startingStepValue, spiral.directionStepCount());
	}
	
	@Test
	public void updateCountersShouldSetCountdownBackToStepValueMinusOneIfCountdownIsZeroAndCurrentDirectionIsSouthOrNorth()
	{
		// The minus 1 is because the current cell from previous direction is already on this row.
		
		SpiralPattern spiral = new SpiralPattern(8);
		spiral.decrementDirectionCountdown();
		spiral.changeDirection();
		assertEquals("Test setup", 0, spiral.directionCounter());
		assertEquals("Test setup", SpiralPattern.Direction.South, spiral.currentDirection());
		
		int stepValue = spiral.directionStepCount();
		spiral.updateCounters();
		assertEquals(stepValue-1, spiral.directionCounter());
		assertEquals(stepValue, spiral.directionStepCount());
		
		spiral.changeDirection();
		spiral.changeDirection();
		assertEquals("Test setup", 0, spiral.directionCounter());
		assertEquals("Test setup", SpiralPattern.Direction.North, spiral.currentDirection());

		spiral.updateCounters();
		assertEquals(stepValue-1, spiral.directionCounter());
		assertEquals(stepValue, spiral.directionStepCount());
	}
	
	@Test
	public void updateCountersShouldSetCountdownBackToIncrementedStepValueMinusOneIfCountdownIsZeroAndCurrentDirectionIsEastOrWest()
	{
		// The minus 1 is because the current cell from previous direction is already on this row.

		SpiralPattern spiral = new SpiralPattern(8);
		spiral.decrementDirectionCountdown();
		assertEquals("Test setup", 0, spiral.directionCounter());
		assertEquals("Test setup", SpiralPattern.Direction.East, spiral.currentDirection());
		
		int stepValue = spiral.directionStepCount();
		spiral.updateCounters();
		assertEquals(stepValue, spiral.directionCounter());
		assertEquals(stepValue + 1, spiral.directionStepCount());
		
		spiral.decrementDirectionCountdown();
		spiral.changeDirection();
		spiral.changeDirection();
		assertEquals("Test setup", 0, spiral.directionCounter());
		assertEquals("Test setup", SpiralPattern.Direction.West, spiral.currentDirection());

		stepValue = spiral.directionStepCount(); 
		spiral.updateCounters();
		assertEquals(stepValue, spiral.directionCounter());
		assertEquals(stepValue + 1, spiral.directionStepCount());
	}
	
	@Test
	public void nextShouldIncrementCurrentValueIfEndValueIsPositive()
	{
		SpiralPattern spiral = new SpiralPattern(8);
		spiral.next();
		assertEquals(1, spiral.currentValue());
		spiral.next();
		assertEquals(2, spiral.currentValue());
	}
	
	@Test
	public void nextShouldDecrementCurrentValueIfEndValueIsNegative()
	{
		SpiralPattern spiral = new SpiralPattern(-8);
		spiral.next();
		assertEquals(-1, spiral.currentValue());
		spiral.next();
		assertEquals(-2, spiral.currentValue());
	}
	
	@Test
	public void nextShouldDoNothingIfEndValueIsReached()
	{
		SpiralPattern spiral = new SpiralPattern(2);
		spiral.next();
		spiral.next();
		int counter = spiral.directionCounter();
		SpiralPattern.Direction direction = spiral.currentDirection();
		Point location = spiral.currentLocation();
		assertEquals("Test setup: should be at end value", 2, spiral.currentValue());
		
		spiral.next();
		assertEquals("Should still be at end value", 2, spiral.currentValue());
		assertEquals("Should still be at last location", location, spiral.currentLocation());
		assertEquals("Should still be at last counter", counter, spiral.directionCounter());
		assertEquals("Should still be at last direction", direction, spiral.currentDirection());
	}
	
	@Test
	public void nextShouldGetNextLocationInCurrentDirectionAndDecrementCountdownIfCounterNotZero()
	{
		SpiralPattern spiral = new SpiralPattern(8);
		assertEquals("Test setup: initial location", new Point(0, 0), spiral.currentLocation());
		assertEquals("Test setup: initial counter", 1, spiral.directionCounter());
		assertEquals("Test setup: initial direction", SpiralPattern.Direction.East, spiral.currentDirection());
		
		spiral.next();
		assertEquals(new Point(1, 0), spiral.currentLocation());
		assertEquals(0, spiral.directionCounter());
		assertEquals("Should still be heading east", SpiralPattern.Direction.East, spiral.currentDirection());
	}
	
	@Test
	public void nextShouldProduceExpectedResults()
	{
		SpiralPattern spiral = new SpiralPattern(5);
		PatternEntry current = spiral.next();
		assertEquals(new Point(1,0), current.location());
		assertEquals("1", current.value());

		current = spiral.next();
		assertEquals(new Point(1,1), current.location());
		assertEquals("2", current.value());

		current = spiral.next();
		assertEquals(new Point(0,1), current.location());
		assertEquals("3", current.value());

		current = spiral.next();
		assertEquals(new Point(-1,1), current.location());
		assertEquals("4", current.value());

		current = spiral.next();
		assertEquals(new Point(-1,0), current.location());
		assertEquals("5", current.value());
	}
}
