package railroad;

import java.util.List;

class Controller 
{
	static private Controller _controller;
	private List<Train> _trains;
		
	private Controller()
	{
		Simulate();
	}

	static public Controller getInstance()
	{
		if(_controller == null)
		{
			_controller = new Controller();
		}
		
		return _controller;
	}
	
	private void Simulate()
	{
		//_trains.add(new Train(true, 0));
		//_trains.add(new Train(false, 1));
		
//		while(true)
//		{
//			for(Train t : _trains)
//			{
//				t.Move();
//			}
			
			try {
			    Thread.sleep(20);                 
			} catch(InterruptedException ex) {
			    System.out.print("Error: sleep()");//Thread.currentThread().interrupt();
			}
//		}
	}
}
