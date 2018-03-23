import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;

public class LevelEditor implements GameState
{
	private int gameState, rows=2, cols=2;
	private NumberSelector[] nums = new NumberSelector[2];
	private Color col = new Color(255,255,255,100);
	private GridSelector selector = new GridSelector(0, HEIGHT-70, WIDTH, 40);
	private GridComponent selected = null;
	private Grid grid = new Grid(50, 50, WIDTH-150, HEIGHT-150, rows, cols);
	private Button save = new Button("Save", WIDTH-35, 30, 15);
	private Button menu = new Button("Return To Menu", 60, 30, 15);
	private Button edit = new Button("Edit", WIDTH-35, 60, 15);
	private Button delete = new Button("Delete", WIDTH-35, 80, 15);
	private PressTriangle mode = new PressTriangle(false, WIDTH-75, 64, 8);
	LevelEditor (int gameState)
	{
		this.gameState=gameState;
		nums[0] = new NumberSelector(rows, "Rows: ", 200, 30);
		nums[1] = new NumberSelector(cols, "Cols: ", 320, 30);
	}
	public void draw(Graphics2D g2) 
	{
		g2.setColor(col);
		for (int i=0; i< nums.length; i++)
		{
			nums[i].draw(g2);
		}
		grid.draw(g2);
		selector.draw(g2);
		menu.draw(g2);
		save.draw(g2);
		edit.draw(g2);
		delete.draw(g2);
		mode.draw(g2);
	}

	public void update() 
	{
		rows=nums[0].update();
		cols=nums[1].update();
		save.update();
		grid.updateRowsCols(rows, cols);
		selector.update();
		selected=selector.getSelected();
		menu.update();
		edit.update();
		delete.update();
	}

	public void notifyMouseReleased()
	{
		for (int i=0; i< nums.length; i++)
		{
			nums[i].notifyMouseReleased();
		}
		selector.notifyMouseReleased();
		if (grid.isOver() && selected!=null && !grid.getDelete())
		{
			Point temp = grid.mouseOver();
			grid.addGridComponent(selected, temp.x, temp.y);
		}
		if (grid.getDelete() && grid.isOver())
		{
			Point temp = grid.mouseOver();
			grid.deleteCoordinate(temp.x, temp.y);
		}
		if (save.isReleased())
		{
			grid.save();
		}
		if (menu.isReleased())
		{
			gameState=MENU;
		}
		if (edit.isReleased())
		{
			mode.updateY(edit.y+4);
			grid.setDelete(false);
		}
		if (delete.isReleased())
		{
			mode.updateY(delete.y+4);
			grid.setDelete(true);
		}
	}

	public int getGameState() 
	{
		return gameState;
	}
	public void notifyKeyReleased(KeyEvent e) 
	{
		
	}
	@Override
	public void setGameState(int gameState) 
	{
		this.gameState=gameState;
		
	}
	@Override
	public boolean hasCommunication() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Communication getCommunication() 
	{
		return null;
	}
	@Override
	public void load(int message) 
	{
		
	}

}