package railroad;

import view.RailroadView;

public class Facade 
{
	static private Facade _facade;
	
	private Controller _controller;
	private RailroadView _view;
	
	private Facade(RailroadView view)
	{
		_controller = Controller.getInstance();
		_view = view;
	}

	static public Facade getInstance(RailroadView view)
	{
		if(_facade == null)
		{
			_facade = new Facade(view);
		}
		
		return _facade;
	}
	
	public RailroadView getView()
	{
		return _view;
	}
}
