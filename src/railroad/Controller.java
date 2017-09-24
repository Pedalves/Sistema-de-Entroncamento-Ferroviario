package railroad;

class Controller 
{
	static private Controller _controller;
		
	private Controller()
	{
		
	}

	static public Controller getInstance()
	{
		if(_controller == null)
		{
			_controller = new Controller();
		}
		
		return _controller;
	}
}
