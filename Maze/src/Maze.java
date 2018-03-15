import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Maze 
{
	private int row, col, rows, cols, baseX, baseY, width, height, xDist, yDist;
	//private String[][] data; 
	private String path;
	private boolean configured = false;
	private Set<Block> blocks = new HashSet<>();
	Maze(String path, int baseX, int baseY, int width, int height)
	{
		this.path=path;
		this.baseX=baseX;
		this.baseY=baseY;
		this.width=width;
		this.height=height;
		
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
			while(input.hasNextLine())
			{
				temp = input.nextLine();
				dat.add(temp);
				count++;
			}
			if (temp.contains(","))
			{
				String temporary[] = temp.split(",");
				rows= count;
				cols = temporary.length;
			}
			else
			{
				rows=count;
				cols=1;
			}
			xDist = width/cols;
			yDist = height/rows;
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
					if (tempo[b].equals("s"))
					{
						row = a;
						col = b;
					}
					Block blockBoi = new Block(tempo[b].charAt(0), b*xDist+baseX, a*yDist+baseY, xDist, yDist);
					blocks.add(blockBoi);
				}
			}
			input.close();
			return true;
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println(e);
			return false;
		}
	}
	public void draw(Graphics2D g2)
	{
		if (!configured)
		{
			g2.setColor(Color.RED);
			g2.drawString("loading maze", width/2, 20);
			configured=true;
		}
		g2.drawLine(baseX, baseY, baseX+width, baseY);
		g2.drawLine(baseX, baseY+height, baseX+width, baseY+height);
		g2.drawLine(baseX, baseY+height, baseX+width, baseY+height);
		g2.drawLine(baseX, baseY+height, baseX, baseY);
		g2.drawLine(baseX+width, baseY+height, baseX+width, baseY);
		for (Block block: blocks)
		{
			block.draw(g2);
		}
	}
	public Point getCurrentCoords()
	{
		return new Point(row, col);
	}

}
