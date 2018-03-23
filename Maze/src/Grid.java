import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class Grid extends MouseOverObject
{
	private int rows, cols;
	private float xStep, yStep;
	private boolean save=false, delete=false;
	private FadingMessage message = null;
	private Color greyish = new Color(200,200,200,100);
	Set<OnScreenComponent> gridData = new HashSet<>();
	Grid(int x, int y, int width, int height, int rows, int cols)
	{
		super(x,y,width,height);
		this.rows=rows;
		this.cols=cols;
		xStep = (float)(w)/cols;
		yStep = (float)(h)/rows;
		
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
			String jeep  = String.format("%06d", fileList.length);
			String path = "mazes/CustomMazes/" + jeep + ".maz";
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
	public void updateRowsCols(int r, int c)
	{
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
