
public class Runner {
	
	public static void main(String[] ignoredArgs)
	{
		Runner.spiral(0);
		System.out.println(); 
		Runner.spiral(16);
		System.out.println();
		Runner.spiral(-97);
		System.out.println();
		Runner.spiral(1234);
	}

	public static void spiral(int endValue)
	{
		SpiralPattern pattern = new SpiralPattern(endValue);
		PatternGrid spiralGrid = new PatternGrid();
		spiralGrid.add(pattern.currentResult());
		while ( ! pattern.isFinished())
			spiralGrid.add(pattern.next());
		
		Runner.plot(spiralGrid, endValue);
	}
	
	public static void plot(PatternGrid results, int endValue)
	{	
		int entryWidth = String.valueOf(endValue).length();
		String entryFormat = "%" + entryWidth + "s ";
		for (int row = results.firstRowIndex(); row <= results.lastRowIndex(); row++)
		{
			for (int col = results.firstColumnIndex(); col <= results.lastColumnIndex(); col++)
			{
				PatternEntry entry = results.at(row, col);
				System.out.printf(entryFormat, entry.value());
			}
			System.out.println();
		}		
	}
}
