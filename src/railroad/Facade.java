package railroad;

public class Facade 
{
	static private Facade _facade;
	
	private Controller _controller;
	
	private Facade()
	{
		_controller = Controller.getInstance();
	}

	static public Facade getInstance()
	{
		if(_facade == null)
		{
			_facade = new Facade();
		}
		
		return _facade;
	}
}
