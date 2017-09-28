package railroad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import javax.swing.Timer;

class Controller
{
	static private Controller _controller;
	private List<Train> _trains;
	private Observer _observer;
		
	private Controller()
	{
		_trains = new ArrayList<Train>();
	}

	static public Controller getInstance()
	{
		if(_controller == null)
		{
			_controller = new Controller();
		}
		
		return _controller;
	}
	
	public void Simulate()
	{	
		_trains.add(new Train(true, 0,_observer));
		_trains.add(new Train(false, 1,_observer));
		
		ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
        		for(Train t : _trains)
        		{
        			t.Move();
        		}
            }
        };
        Timer timer = new Timer(100 ,taskPerformer);
        timer.setRepeats(true);
        timer.start();
	}
	
	public void setObserver(Observer observer)
	{
		_observer = observer;
	}
	
	
}
