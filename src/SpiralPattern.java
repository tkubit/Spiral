import java.awt.Point;


public class SpiralPattern {
	
	public enum Direction {
		East, South, West, North;
		
		public Direction next()
		{
			switch (this)
			{
			case East: return South;
			case South: return West;
			case West: return North;
			case North: return East;
			}
			return East;
		}	
	};
	
	private int endValue;
	private Point currentLocation;
	private int currentValue;
	private Direction currentDirection;
	private int directionStepCount;
	private int directionCountdown;
	
	public SpiralPattern(int theEndValue)
	{
		endValue = theEndValue;
		currentLocation = new Point(0,0);
		currentValue = 0;
		currentDirection = Direction.East;
		directionStepCount = 1;
		directionCountdown = 1;
	}
	
	public PatternEntry currentResult()
	{
		return new PatternEntry(currentLocation(), currentValue());
	}
	
	public Point currentLocation() { return currentLocation; }
	public int endValue() { return endValue; }
	public int currentValue() { return currentValue; }
	public Direction currentDirection() { return currentDirection; }
	public int directionCounter() { return directionCountdown; }
	public int directionStepCount() { return directionStepCount; }
	
	public void decrementDirectionCountdown()
	{
		directionCountdown--;
	}
	
	public void updateCounters()
	{
		if (directionCountdown > 0)
		{
			this.decrementDirectionCountdown();
			return;
		}
		
		if (this.currentDirection() == Direction.East || this.currentDirection() == Direction.West)
			directionStepCount++;
		
		directionCountdown = directionStepCount-1;

	}
	
	public boolean shouldChangeDirection()
	{
		return directionCountdown == 0;
	}
	
	public void changeDirection()
	{
		currentDirection = currentDirection.next();
	}
	
	public boolean isFinished()
	{
		return this.currentValue() == endValue;
	}
	
	public PatternEntry next()
	{
		if ( ! this.isFinished())
		{
			if (this.shouldChangeDirection())
				this.changeDirection();
			this.updateCounters();
			currentValue = (endValue > 0) ? currentValue + 1 : currentValue - 1;
			currentLocation = this.nextLocation();
		}
		return this.currentResult();
	}
	
	public Point nextLocation()
	{
		switch (this.currentDirection)
		{
		case East: return new Point(this.currentLocation().x + 1, this.currentLocation().y);
		case South: return new Point(this.currentLocation().x, this.currentLocation().y + 1);
		case West: return new Point(this.currentLocation().x - 1, this.currentLocation().y);
		case North: return new Point(this.currentLocation().x, this.currentLocation().y - 1);
		default: return this.currentLocation();
		}
	}
}
