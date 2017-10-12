package railroad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.swing.Timer;

class Controller implements Observer
{
	static private Controller _controller;
	private List<Train> _trains;
	private List<Observer> _observers;
	private SemaphoreState _semaphoreState;
	private Semaphore _semaphore;
	private int[] _speeds;
	private Random _rand;
		
	private Controller()
	{
		_trains = new ArrayList<Train>();
		_semaphoreState = SemaphoreState.getInitialState(this);
		
		_observers = new ArrayList<Observer>();
		_observers.add(this);
		
		_speeds = new int[]{60,80,100};
		_rand = new Random();
				
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
    					for(Train t_collision : _trains)
						{
							if(t_collision.isMovingRight())
	        				{
								if(t.getPos()[0] > t_collision.getPos()[0] && (t.getPos()[0] - t_collision.getPos()[0]) < 50)
								{
									// If speed is greater you need to reduce it
									if(t.getSpeed() < t_collision.getSpeed())
									{
										t_collision.setSpeed(t.getSpeed());
									}
								}
	        				}
						}
        				
        				// Move
    					t.Move();
    					
    					// Check if it need to stop
    					if(t.getCheckPermission() && _semaphoreState.Status() == "ClosedOpen")
    					{
    						t.stop();
    					}
    					else 
    					{
    						t.go();
    					}

    				}
    				else
    				{
    					// Check Collision
    					for(Train t_collision : _trains)
						{
							if(!t_collision.isMovingRight())
	        				{
								if(t.getPos()[0] < t_collision.getPos()[0] && (t_collision.getPos()[0] - t.getPos()[0]) < 50)
								{
									// If speed is greater you need to reduce it
									if(t.getSpeed() < t_collision.getSpeed())
									{
										t_collision.setSpeed(t.getSpeed());
									}
								}
	        				}
						}
        				
        				// Move
    					t.Move();
    					
    					// Check if it need to stop
    					if(t.getCheckPermission() && _semaphoreState.Status() == "OpenClosed")
    					{
    						t.stop();
    					}
    					else 
    					{
    						t.go();
    					}
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
		// Check if there's space for a new train
		if(_canAdd(1))
		{
			_trains.add(new Train(false, _trains.size(),_observers,_speeds[_rand.nextInt(3)]));
		}
	}
	
	public void AddLeft()
	{	
		// Check if there's space for a new train
		if(_canAdd(0))
		{
			_trains.add(new Train(true, _trains.size(),_observers,_speeds[_rand.nextInt(3)]));
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
