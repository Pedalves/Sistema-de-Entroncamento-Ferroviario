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
	
	public void Simulate()
	{
		_controller.Simulate();
	}
	
	public void setObserver(Observer observer)
	{
		_controller.setObserver(observer);
	}
}
