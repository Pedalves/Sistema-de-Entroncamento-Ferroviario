package railroad;

public abstract class SemaphoreState 
{
	static private Controller _controller;
	
	public static SemaphoreState getInitialState(Controller c)
	{
		_controller = c;
		return new SemaphoreOpenOpen();
	}
	
	public abstract String Status();
	public abstract SemaphoreState OpenLeft();
	public abstract SemaphoreState OpenRight();
	public abstract SemaphoreState CloseLeft();
	public abstract SemaphoreState CloseRight();
	
}
