
public class CharList
{
	public static char getCharByID(boolean left, boolean right, boolean top, boolean bottom)
	{
		if (left && right && top && bottom)
		{
			return 'o';
		}
		else if (left && right && bottom)
		{
			return 'm';
		}
		else if (left && top && bottom)
		{
			return 'k';
		}
		else if (left && right && top)
		{
			return 'j';
		}
		else if (right && top && bottom)
		{
			return 'i';
		}
		else if (bottom && left)
		{
			return 'h';
		}
		else if (left && right)
		{
			return 'g';
		}
		else if (right && bottom)
		{
			return 'f';
		}
		else if (top && left)
		{
			return 'd';
		}
		else if (top && bottom)
		{
			return 'c';
		}
		else if (right && top)
		{
			return 'a';
		}
		else if (right)
		{
			return 'r';
		}
		else if (left)
		{
			return 'l';
		}
		else if (bottom)
		{
			return 'b';
		}
		else if (top)
		{
			return 't';
		}
		else
		{
			return 'n';
		}
	}

}
