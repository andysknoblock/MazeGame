import java.awt.Graphics2D;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Maze 
{
	private int row, col, rows, cols;
	private String[][] data; 
	private String path;
	Maze(String path)
	{
		this.path=path;
	}
	public boolean loadMaze()
	{
		Scanner input;
		try 
		{
			input = new Scanner(new File(path));
			ArrayList<String> dat = new ArrayList<String>();
			int count=0;
			String temp = null;
			while(input.hasNext())
			{
				temp = input.nextLine();
				dat.add(temp);
				count++;
			}
			if (temp.contains(","))
			{
				String temporary[] = temp.split(",");
				data = new String[count][temporary.length];
			}
			else
			{
				data = new String[1][count];
			}
			for (int a=0;a<dat.size();a++)
			{
				temp = dat.get(a);
				String[] tempo;
				if (temp.contains(","))
				{
					tempo = temp.split(",");
				}
				else
				{
					tempo = new String[1];
					tempo[0] = temp;
				}
				for (int b=0; b< tempo.length; b++)
				{
					data[a][b] = tempo[b];
					if (tempo[b].equals("s"))
					{
						row = a;
						col = b;
					}
				}
			}
			input.close();
			rows = data.length;
			cols = data[0].length;
			return true;
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println(e);
			return false;
		}
	}
	public void draw(Graphics2D g2, int baseX, int baseY, int width, int height)
	{
		int xDist = width/cols;
		int yDist = height/rows;
		for (int a=0; a<rows; a++)
		{
			for (int b=0; b<cols; b++)
			{
				if (a-1>=0)
				{
					if (data[a][b].equals("1") && data[a-1][b].equals("1"))
					{
						g2.drawLine(b*xDist+baseX, (a-1)*yDist+baseY, b*xDist+baseX, a*yDist+baseY);
					}
				}
				if (b-1>=0)
				{
					if (data[a][b].equals("1") && data[a][b-1].equals("1"))
					{
						g2.drawLine((b-1)*xDist+baseX, a*yDist+baseY, b*xDist+baseX, a*yDist+baseY);
					}
				}
				if (data[a][b].equals("1") && a==0)
				{
					g2.drawLine(b*xDist+baseX, a*yDist+baseY, b*xDist+baseX, (a-1)*yDist+baseY);
				}
				if (data[a][b].equals("1") && b==0)
				{
					g2.drawLine(b*xDist+baseX, a*yDist+baseY, (b+1)*xDist+baseX, a*yDist+baseY);
				}
			}
		}
	}
	public Point getCurrentCoords()
	{
		return new Point(row, col);
	}
	public void printArray()
	{
		for (String[] s : data)
		{
			for (String temp: s)
			{
				System.out.print(temp);
			}
			System.out.println("");
		}
	}
	

}
