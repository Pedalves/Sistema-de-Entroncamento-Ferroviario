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
		System.out.println("Fechando esquerda");
		return new SemaphoreClosedOpen();
	}

	@Override
	public SemaphoreState CloseRight() 
	{
		System.out.println("Fechando direita");
		return new SemaphoreOpenClosed();
	}

}
