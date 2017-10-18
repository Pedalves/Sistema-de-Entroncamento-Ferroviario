package railroad;

public class SemaphoreOpenOpen extends SemaphoreState 
{

	@Override
	public String Status()
	{
		return "OpenOpen";
	}

	@Override
	public SemaphoreState OpenLeft() 
	{
		return null;
	}

	@Override
	public SemaphoreState OpenRight()
	{
		return null;
	}

	@Override
	public SemaphoreState CloseLeft()
	{
		return new SemaphoreClosedOpen();
	}

	@Override
	public SemaphoreState CloseRight() 
	{
		return new SemaphoreOpenClosed();
	}

}
