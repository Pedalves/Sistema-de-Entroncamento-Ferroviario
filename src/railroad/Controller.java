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
	private Semaphore _semaphore;
		
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
    				// Left
    				if(t.isMovingRight())
    				{
    					// Check Collision
    					for(Train t_colission : _trains)
						{
							if(t_colission.isMovingRight())
	        				{
								if((t.getPos()[0] - t_colission.getPos()[0]) < 50)
								{
									// If speed is greater you need to reduce it
									if(t.getSpeed() < t_colission.getSpeed())
									{
										t_colission.setSpeed(t.getSpeed());
									}
								}
	        				}
						}
    					
    					// Check if it need to stop
    					if(_semaphoreState.Status() != "ClosedOpen")
    					{
    						t.go();
    					}
    					else if(t.getCheckPermission())
    					{
    						t.stop();
    					}
    				}
    				else
    				{
    					// Check Collision
    					for(Train t_colission : _trains)
						{
							if(!t_colission.isMovingRight())
	        				{
								if(t.getPos()[0] < t_colission.getPos()[0] && (t_colission.getPos()[0] - t.getPos()[0]) < 5)
								{
									// If speed is greater you need to reduce it
									if(t.getSpeed() < t_colission.getSpeed())
									{
										t_colission.setSpeed(t.getSpeed());
									}
								}
	        				}
						}
    					
    					// Check if it need to stop
    					if(_semaphoreState.Status() != "OpenClosed")
    					{
    						t.go();
    					}
    					else if(t.getCheckPermission())
    					{
    						t.stop();
    					}
    				}
    				
    				// Move
					t.Move();
        		}
            }
        };
        Timer timer = new Timer(100 ,taskPerformer);
        timer.setRepeats(true);
        timer.start();
	}
	
	public void AddRight()
	{	
		// Check if there's space for a new train
		if(_canAdd(1))
		{
			_trains.add(new Train(false, _trains.size(),_observers));
		}
	}
	
	public void AddLeft()
	{	
		// Check if there's space for a new train
		if(_canAdd(0))
		{
			_trains.add(new Train(true, _trains.size(),_observers));
		}
	}
	
	// 0 -> Left | 1-> Right
	private boolean _canAdd(int side)
	{
		for(Train t: _trains)
		{
			// If there's no space for a new train return false
			switch(side)
			{
			case 0: // Left
				if(t.isMovingRight() && t.getPos()[0] <= 36)
				{
					return false;
				}
				break;
			case 1: // Right
				if(!t.isMovingRight() && t.getPos()[0] >= 1000)
				{
					return false;
				}
				break;
			default:
				return false;
			}
		}
		
		return true;
	}
	
	public void setObserver(Observer observer)
	{
		_observers.add(observer);
		_semaphore = Semaphore.getInstance(observer);
	}

	@Override
	public void update(Observable o, Object arg) 
	{
		Object args[] = (Object[])arg;
		SemaphoreState st = null;
		
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
			case 5:
				Train t = (Train)args[1];
//				if(_trains.contains(t))
//				{
//					_trains.remove(t);
//				}
				break;
			default:
				break;
		}		
		
		if(st != null)
		{
			_semaphore.stateChanged(_semaphoreState.Status());
		}
	}
	
	
}
