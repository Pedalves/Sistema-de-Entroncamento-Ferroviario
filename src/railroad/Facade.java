package railroad;

import java.util.Observer;

import view.RailroadView;

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
	
	public void AddRight()
	{	
		_controller.AddRight();
	}
	
	public void AddLeft()
	{	
		_controller.AddLeft();
	}
	
	public void setObserver(Observer observer)
	{
		_controller.setObserver(observer);
	}
}
