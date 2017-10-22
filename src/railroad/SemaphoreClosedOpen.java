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
		count--;
		if(count <= 0)
		{
			return new SemaphoreOpenOpen();
		}
		else
		{
			return null;
		}
	}

	@Override
	public SemaphoreState OpenRight()
	{
		return null;
	}

	@Override
	public SemaphoreState CloseLeft()
	{
		count++;
		return null;
	}

	@Override
	public SemaphoreState CloseRight() 
	{
		System.out.println("Tentando fechar direita, nao deveria");
		return null;
	}

}
