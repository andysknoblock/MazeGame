import java.awt.MouseInfo;
import java.awt.Point;

public class MouseOverObject 
{
	int x, y, w, h;
	MouseOverObject(int x, int y, int w, int h)
	{
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
	}
	protected boolean isOver()
	{
		Point temp = MouseInfo.getPointerInfo().getLocation();
		int mouseX=(int) temp.getX(), mouseY=(int) temp.getY();
		if (mouseX>x && mouseX<x+w && mouseY>y && mouseY<y+h)
			return true;
		else
			return false;
	}

}