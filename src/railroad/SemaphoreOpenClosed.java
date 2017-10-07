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
			System.out.println("Abrindo direita");
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
		System.out.println("Nao deveria entrar aqui");
		return null;
	}

	@Override
	public SemaphoreState CloseRight() 
	{
		count++;
		return null;
	}

}
