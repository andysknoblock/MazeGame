import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Grid extends MouseOverObject
{
	private String strang;
	private int rows, cols;
	private float xStep, yStep;
	private boolean save=false, delete=false, needsUpdate=false;
	private FadingMessage message = null;
	private Color greyish = new Color(200,200,200,100);
	Set<OnScreenComponent> gridData = new HashSet<>();
	Grid(String strang, int x, int y, int width, int height, int rows, int cols)
	{
		super(x,y,width,height);
		this.rows=rows;
		this.cols=cols;
		xStep = (float)(w)/cols;
		yStep = (float)(h)/rows;
		if (strang==null)
		{
			this.strang=null;
		}
		else
		{
			this.strang=strang;
			String path = "mazes/CustomMazes/" + strang + ".maz";
			loadith(path);
		}
	}
	public void loadith(String path)
	{
		Scanner input;
		try 
		{
			input = new Scanner(new File(path));
			ArrayList<String> dat = new ArrayList<String>();
			int count=0;
			String temp = null;
			Point exit1 = null;
			Point enter = null;
			while(input.hasNextLine())
			{
				temp = input.nextLine();
				if (temp.charAt(0)=='S')
				{
					String dengue = temp.substring(2, temp.length()-1);
					String[] dank = dengue.split(",");
					enter = new Point(Integer.parseInt(dank[0]), Integer.parseInt(dank[1]));
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
				String temporary[] = dat.get(0).split(",");
				rows= count;
				cols = temporary.length;
			}
			else
			{
				rows=count;
				cols=1;
			}
			updateRowsCols(rows, cols);
			needsUpdate=true;
			OnScreenComponent temporary = new StartComponent(toX(enter.x), toY(enter.y),(int)xStep , (int)yStep, enter.x, enter.y);
			if (!isInData(temporary) && !temporary.limitCoordinates(temporary.getCol(), temporary.getRow()))
			{
				gridData.add(temporary);
			}
			temporary = new EndComponent(toX(exit1.x), toY(exit1.y),(int)xStep , (int)yStep, exit1.x, exit1.y);
			if (!isInData(temporary) && !temporary.limitCoordinates(temporary.getCol(), temporary.getRow()))
			{
				gridData.add(temporary);
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
					if (tempo[b]!="1")
					{
						Block blockBoi = new Block(tempo[b].charAt(0), b, a, 0, 0,0, 0);
						if (blockBoi.getLeft())
						{
							temporary = new LeftAlignedLine(toX(b), toY(a),(int)xStep , (int)yStep, b, a);
							if (!isInData(temporary) && !temporary.limitCoordinates(temporary.getCol(), temporary.getRow()))
							{
								gridData.add(temporary);
							}
						}
						if (blockBoi.getTop())
						{
							temporary = new TopAlignedLine(toX(b), toY(a),(int)xStep , (int)yStep, b, a);
							if (!isInData(temporary) && !temporary.limitCoordinates(temporary.getCol(), temporary.getRow()))
							{
								gridData.add(temporary);
							}
						}
					}
				}
			}
			input.close();
		}
		catch (FileNotFoundException e) 
		{
			System.out.println(e);
		}
	}
	public void draw (Graphics2D g2)
	{
		Color col=g2.getColor();
		g2.setColor(Color.WHITE);
		g2.drawLine(x, y, x+w, y);
		g2.drawLine(x, y+h, x+w, y+h);
		g2.drawLine(x, y, x, y+h);
		g2.drawLine(x+w, y, x+w, y+h);
		for (OnScreenComponent grd: gridData)
		{
			grd.draw(g2);
		}
		g2.setColor(greyish);
		for (int i=0; i<rows; i++)
		{
			g2.drawLine(x, toY(i), x+w, toY(i));
		}
		for (int i=0; i<cols; i++)
		{
			g2.drawLine(toX(i), y, toX(i), y+h);
		}
		g2.setColor(col);
		if (message!=null)
		{
			message.draw(g2);
			message.update();
			if (message.isLiving()==false)
				message=null;
		}
		if (save)
			doSave();
	}
	public void setDelete(boolean delete)
	{
		this.delete=delete;
	}
	public void save()
	{
		boolean start=false, exit=false;
		for (OnScreenComponent grd : gridData)
		{
			if (grd.getID()==2)
				start=true;
			if (grd.getID()==3)
				exit=true;
		}
		if (start&&exit)
		{
			save=true;
		}
		else
		{
			message = new FadingMessage("No start/exit",450, 30, 15);
		}
		if (save)
		{
			message = new FadingMessage("Saving",450, 30, 20);
		}
	}
	private void doSave()
	{
		char[][] toFile = new char[rows][cols];
		Point start = null, exit = null;
		for (int i=0; i<rows; i++)
		{
			for (int j=0; j<cols; j++)
			{ 
				boolean left = false, right=false, top=false, bottom=false;
				for (OnScreenComponent grd : gridData)
				{
					if (grd.getCol()==i && grd.getRow()==j)
					{
						if (grd.getID()==0)
						{
							left=true;
						}
						if (grd.getID()==1)
						{
							top=true;
						}
						if (grd.getID()==2)
						{
							start = new Point(j, i);
						}
						if (grd.getID()==3)
						{
							exit = new Point(j, i);
						}
					}
				}
				if (i==0)
				{
					top=true;
				}
				if (i==rows-1)
				{
					bottom=true;
				}
				if (j==0)
				{
					left=true;
				}
				if (j==cols-1)
				{
					right=true;
				}
				toFile[i][j] = CharList.getCharByID(left, right, top, bottom);
			}
		}
		try 
		{
			File folder = new File("mazes/CustomMazes/");
			File fileList[] = folder.listFiles(); 
			String path = null;
			if (strang==null)
			{
				String jeep  = String.format("%06d", fileList.length);
				path = "mazes/CustomMazes/" + jeep + ".maz";
				strang=jeep;
			}
			else
			{
				path = "mazes/CustomMazes/" + strang + ".maz";
			}
			
			PrintWriter pr = new PrintWriter(new File(path));
			for (int i=0; i<toFile.length; i++)
			{
				for (int j=0; j<toFile[0].length; j++)
				{
					pr.write(toFile[i][j]);
					if (j!=toFile[0].length-1)
					{
						pr.write(',');
					}
				}
				pr.println();
			}
			pr.println("S(" + start.x + "," + start.y + ")");
			pr.println("E(" + exit.x + "," + exit.y + ")");
			pr.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		save=false;
	}
	public Point mouseOver()
	{
		if (isOver())
		{
			Point p = MouseInfo.getPointerInfo().getLocation();
			double tempX=p.x;
			double tempY=p.y-25;
			tempX-=x;
			tempY-=y;
			tempX=tempX/xStep;
			tempY=tempY/yStep;
			p = new Point((int) tempX, (int) tempY);
			return p;
		}
		else
		{
			return null;
		}
	}
	public Point updateRowsCols(int r, int c)
	{
		if (needsUpdate)
		{
			needsUpdate = false;
			xStep = (float)(w)/cols;
			yStep = (float)(h)/rows;
			Set<OnScreenComponent> tempData = new HashSet<>();
			for (OnScreenComponent grd : gridData)
			{
				if (grd.getRow()<cols && grd.getCol()<rows)
				{
					OnScreenComponent temp = IDList.getByID(grd.getID(), toX(grd.getRow()), toY(grd.getCol()), (int) xStep, (int) yStep, grd.getRow(), grd.getCol());
					tempData.add(temp);
				}
			}
			gridData.clear();
			gridData=tempData;
			return new Point(rows, cols);
		}
		if (rows!=r || cols!=c)
		{
			rows=r;
			cols=c;
			xStep = (float)(w)/cols;
			yStep = (float)(h)/rows;
			Set<OnScreenComponent> tempData = new HashSet<>();
			for (OnScreenComponent grd : gridData)
			{
				if (grd.getRow()<cols && grd.getCol()<rows)
				{
					OnScreenComponent temp = IDList.getByID(grd.getID(), toX(grd.getRow()), toY(grd.getCol()), (int) xStep, (int) yStep, grd.getRow(), grd.getCol());
					tempData.add(temp);
				}
			}
			gridData.clear();
			gridData=tempData;
		}
		return null;
		
	}
	public int toX(int colX)
	{
		float ans = x+xStep*colX;
		return (int)ans;
	}
	public int toY(int colY)
	{
		float ans = y+yStep*colY;
		return (int)ans;
	}
	public void addGridComponent(GridComponent grdComp, int column, int row)
	{
		OnScreenComponent temp = IDList.getByID(grdComp.getID(), toX(column), toY(row), (int) xStep, (int) yStep, column, row);
		if (temp!=null)
		{
			if (!isInData(temp) && !temp.limitCoordinates(temp.getCol(), temp.getRow()))
			{
				gridData.add(temp);
			}
		}
	}
	public boolean getDelete()
	{
		return delete;
	}
	private boolean isInData(OnScreenComponent val)
	{
		for (OnScreenComponent temp : gridData)
		{
			if (temp.getLocation().equals(val.getLocation()) && temp.getID()==val.getID())
			{
				return true;
			}
		}
		return false;
	}
	public void deleteCoordinate(int column, int row)
	{
		Point p = new Point(column,row);
		Set<OnScreenComponent> tempData = new HashSet<>();
		for (OnScreenComponent temp : gridData)
		{
			tempData.add(temp);
		}
		for (OnScreenComponent temp : tempData)
		{
			if (temp.getLocation().equals(p.getLocation()))
			{
				gridData.remove(temp);
			}
		}
	}

}
