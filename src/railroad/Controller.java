package railroad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Timer;

class Controller implements Observer
{
	static private Controller _controller;
	private List<Train> _trains;
	private List<Observer> _observers;
	private SemaphoreState _semaphoreState;
		
	private Controller()
	{
		_trains = new ArrayList<Train>();
		_semaphoreState = SemaphoreState.getInitialState(this);
		
		_observers = new ArrayList<Observer>();
		_observers.add(this);
		
		setupTimer();
	}

	static public Controller getInstance()
	{
		if(_controller == null)
		{
			_controller = new Controller();
		}
		
		return _controller;
	}
	
	void setupTimer()
	{
		ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
        		for(Train t : _trains)
        		{
        			if(t.getCheckPermission())
        			{
        				if(t.getRight())
        				{
        					if(_semaphoreState.Status() != "ClosedOpen")
        					{
                    			t.Move();
        					}
        				}
        				else
        				{
        					if(_semaphoreState.Status() != "OpenClosed")
        					{
                    			t.Move();
        					}
        				}
        			}
        			else
        			{
            			t.Move();	
        			}
        		}
            }
        };
        Timer timer = new Timer(100 ,taskPerformer);
        timer.setRepeats(true);
        timer.start();
	}
	
	public void AddRight()
	{	
		_trains.add(new Train(false, _trains.size(),_observers));	
	}
	
	public void AddLeft()
	{	
		_trains.add(new Train(true, _trains.size(),_observers));
	}
	
	public void setObserver(Observer observer)
	{
		_observers.add(observer);
	}

	@Override
	public void update(Observable o, Object arg) 
	{
		Object args[] = (Object[])arg;
		SemaphoreState st;
		
		switch((int)args[0])
		{
			case 1:
				st = _semaphoreState.CloseRight();
				
				if(st != null)
				{
					_semaphoreState = st;
				}
				break;
			case 2:
				st = _semaphoreState.OpenRight();
				
				if(st != null)
				{
					_semaphoreState = st;
				}
				break;
			case 3:
				st = _semaphoreState.CloseLeft();
				
				if(st != null)
				{
					_semaphoreState = st;
				}
				break;
			case 4:
				st = _semaphoreState.OpenLeft();
				
				if(st != null)
				{
					_semaphoreState = st;
				}
				break;
			default:
				break;
		}		
	}
	
	
}
