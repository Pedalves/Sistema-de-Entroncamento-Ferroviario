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
		System.out.println("Abrindo direita");
		return new SemaphoreOpenOpen();
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
