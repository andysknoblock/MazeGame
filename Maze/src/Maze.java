import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Maze 
{
	int row, col, rows, cols;
	int[][]data; 
	String path;
	Maze(String path)
	{
		this.path=path;
	}
	public void loadMaze()
	{
		Scanner input;
		try 
		{
			input = new Scanner(new File(path));
			ArrayList<String> dat = new ArrayList<String>();
			while(input.hasNextLine())
			{
				String temp = input.nextLine();
				dat.add(temp);
			}
			
			for (int a=0;a<dat.size();a++)
			{
				String temp = dat.get(a);
				for (int b=0; b<temp.length();b++)
				{
					data[a][b] = temp.charAt(b);
				}
			}
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println(e);
		}
	}
	

}
