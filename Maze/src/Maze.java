import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
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
			data = new String[dat.get(0).length()][dat.size()];
			for (int a=0;a<dat.size();a++)
			{
				String temp = dat.get(a);
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
					System.out.print(data[a][b]);
				}
				System.out.println("");
			}
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println(e);
		}
	}
	

}
