package railroad;

public class SemaphoreOpenClosed extends SemaphoreState 
{
	@Override
	public String Status()
	{
		return "OpenClosed";
	}

	@Override
	public SemaphoreState OpenLeft() 
	{
		return null;
	}

	@Override
	public SemaphoreState OpenRight()
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
	public SemaphoreState CloseLeft()
	{
		// System.out.println("Tentando fechar esquerda, nao deveria");
		return null;
	}

	@Override
	public SemaphoreState CloseRight() 
	{
		count++;
		return null;
	}

}
