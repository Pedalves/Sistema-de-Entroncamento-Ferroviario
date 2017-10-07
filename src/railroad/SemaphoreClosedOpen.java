package railroad;

public class SemaphoreClosedOpen extends SemaphoreState 
{

	@Override
	public String Status()
	{
		return "ClosedOpen";
	}

	@Override
	public SemaphoreState OpenLeft() 
	{
		return new SemaphoreOpenOpen();
	}

	@Override
	public SemaphoreState OpenRight()
	{
		return null;
	}

	@Override
	public SemaphoreState CloseLeft()
	{
		System.out.println("Nao deveria entrar aqui");
		return null;
	}

	@Override
	public SemaphoreState CloseRight() 
	{
		System.out.println("Nao deveria entrar aqui");
		return null;
	}

}
