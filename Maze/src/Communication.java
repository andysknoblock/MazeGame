
public class Communication 
{
	private int state;
	private int command;
	Communication(int state, int command)
	{
		this.state=state;
		this.command=command;
	}
	public int getGameState()
	{
		return state;
	}
	public int getCommand()
	{
		return command;
	}

}
