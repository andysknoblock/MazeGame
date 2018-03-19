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
	private Button save = new Button("Save", WIDTH-30, 30, 15);
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
		save.draw(g2);
	}

	public void update() 
	{
		rows=nums[0].update();
		cols=nums[1].update();
		save.update();
		grid.updateRowsCols(rows, cols);
		selector.update();
		selected=selector.getSelected();
	}

	public void notifyMouseReleased()
	{
		for (int i=0; i< nums.length; i++)
		{
			nums[i].notifyMouseReleased();
		}
		selector.notifyMouseReleased();
		if (grid.isOver() && selected!=null)
		{
			Point temp = grid.mouseOver();
			System.out.println(temp);
			grid.addGridComponent(selected, temp.x, temp.y);
		}
	}

	public int getGameState() 
	{
		return gameState;
	}
	public void notifyKeyReleased(KeyEvent e) 
	{
		
	}

}