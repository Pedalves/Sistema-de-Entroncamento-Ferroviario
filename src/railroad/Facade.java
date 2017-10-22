package railroad;

import java.util.Observer;

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
	
	public void AddRight(int speed)
	{	
		_controller.AddRight(speed);
	}
	
	public void AddLeft(int speed)
	{	
		_controller.AddLeft(speed);
	}
	
	public void setObserver(Observer observer)
	{
		_controller.setObserver(observer);
	}
}
