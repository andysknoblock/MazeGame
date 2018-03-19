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
	//private String[][] data; 
	private String path;
	private boolean configured = false;
	private Set<Block> blocks = new HashSet<>();
	private SpecialBlock[] specials = new SpecialBlock[2];
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
				temp = temp.toLowerCase();
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
						col = a;
						row = b;
						SpecialBlock blockBoi = new SpecialBlock(tempo[b].charAt(0), b, a, (int) (b*xDist+baseX), (int)(a*yDist+baseY),(int) xDist, (int)yDist, rows, cols);
						specials[0] = blockBoi;
					}
					else if (tempo[b].equals("e"))
					{
						SpecialBlock blockBoi = new SpecialBlock(tempo[b].charAt(0), b, a, (int)(b*xDist+baseX),(int) (a*yDist+baseY), (int)xDist,(int) yDist, rows, cols);
						specials[1] = blockBoi;
					}
					else if (tempo[b]!="1")
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
		for (SpecialBlock block: specials)
		{
			block.draw(g2);
		}
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
	/*public Set<Block> getNearby(int rowVal, int colVal)
	{
		Set <Block> blocklads = new HashSet<>();
		for (Block b:blocks)
		{
			if (isNextTo(b.getCoords(), new Point(rowVal, colVal)))
			{
				blocklads.add(b);
			}
		}
		return blocklads;
	}
	private boolean isNextTo(Point p1, Point p2)
	{
		if (p1.x==p2.x && isWithinOneOf(p1.y,p2.y))
			return true;
		else if (p1.y==p2.y && isWithinOneOf(p1.x,p2.x))
			return true;
		else
			return false;
	}
	private boolean isWithinOneOf(int n1, int n2)
	{
		if (Math.abs(n1-n2)<=1)
			return true;
		else
			return false;
	}*/

}
