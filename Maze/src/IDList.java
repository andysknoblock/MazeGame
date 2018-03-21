
public class IDList 
{
	public static OnScreenComponent getByID(int ID, int x, int y, int w, int h, int row, int column)
	{
		if (ID==0)
		{
			return new LeftAlignedLine(x, y, w, h, row, column);
		}
		else if (ID==1)
		{
			return new TopAlignedLine(x, y, w, h, row, column);
		}
		else if (ID==2)
		{
			return new StartComponent(x, y, w, h, row, column);
		}
		else if (ID==3)
		{
			return new EndComponent(x, y, w, h, row, column);
		}
		else 
		{
			System.out.println("Unknown OnScreenComponent");
			return null;
		}
	}

}
