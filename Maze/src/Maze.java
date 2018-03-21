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
	private int row, col, rows, cols, baseX, baseY, width, height;
	float xDist, yDist;
	private String path;
	private boolean configured = false;
	private Set<Block> blocks = new HashSet<>();
	private MazeExit exit = new MazeExit();
	//private SpecialBlock[] specials = new SpecialBlock[2];
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
			Point exit1 = null;
			while(input.hasNextLine())
			{
				temp = input.nextLine();
				if (temp.charAt(0)=='S')
				{
					String dengue = temp.substring(2, temp.length()-1);
					String[] dank = dengue.split(",");
					col = Integer.parseInt(dank[0]);
					row = Integer.parseInt(dank[1]);
				}
				else if (temp.charAt(0)=='E')
				{
					String dengue = temp.substring(2, temp.length()-1);
					String[] dank = dengue.split(",");
					exit1 = new Point(Integer.parseInt(dank[0]),Integer.parseInt(dank[1]));
				}
				else
				{
					dat.add(temp);
					count++;
				}
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
			exit.setEnd((int)(exit1.x*xDist+baseX), (int)(exit1.y*yDist+baseY), (int) xDist,(int)yDist , exit1.x, exit1.y);
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
					if (tempo[b]!="1")
					{
						Block blockBoi = new Block(tempo[b].charAt(0), b, a, (int)(b*xDist+baseX), (int)(a*yDist+baseY),(int) xDist, (int)yDist);
						blocks.add(blockBoi);
					}
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
		for (Block block: blocks)
		{
			block.draw(g2);
		}
		exit.draw(g2);
	}
	
	public Point getStartCoords()
	{
		return new Point(row, col);
	}
	public Point getDimensions()
	{
		return new Point(rows, cols);
	}
	public boolean canMoveRight(Point p)
	{
		boolean temp=true;
		for (Block b: blocks)
		{
			if (b.getCoords().equals(p))
			{
				if (b.getRight())
				{
					temp=false;
				}
			}
			
			if (temp && b.getCoords().equals(new Point(p.x+1, p.y)))
			{
				if (b.getLeft())
				{
					temp=false;
				}
			}
		}
		return temp;
	}
	public boolean canMoveLeft(Point p)
	{
		boolean temp=true;
		for (Block b: blocks)
		{
			if (b.getCoords().equals(p))
			{
				if (b.getLeft())
				{
					temp=false;
				}
			}
			if (temp && b.getCoords().equals(new Point(p.x-1, p.y)))
			{
				if (b.getRight())
					temp=false;
			}
		}
		return temp;
	}
	public boolean canMoveDown(Point p)
	{
		boolean temp=true;
		for (Block b: blocks)
		{
			if (b.getCoords().equals(p))
			{
				if (b.getBottom())
				{
					temp=false;
				}
			}
			if (temp && b.getCoords().equals(new Point(p.x, p.y+1)))
			{
				if (b.getTop())
					temp=false;
			}
		}
		return temp;
	}
	public boolean canMoveUp(Point p)
	{
		boolean temp=true;
		for (Block b: blocks)
		{
			if (b.getCoords().equals(p))
			{
				if (b.getTop())
				{
					temp=false;
				}
			}
			if (temp && b.getCoords().equals(new Point(p.x, p.y-1)))
			{
				if (b.getBottom())
					temp=false;
			}
		}
		return temp;
	}
	public boolean checkWin(Point player)
	{
		if (player.equals(exit.getCoords()))
		{
			return true;
		}
		else
			return false;
	}

}
