package railroad;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

class Semaphore extends Observable
{
	static private Semaphore _semaphore;
		
	private Semaphore(Observer observer)
	{
		addObserver(observer);
	}

	static public Semaphore getInstance(Observer observer)
	{
		if(_semaphore == null)
		{
			_semaphore = new Semaphore(observer);
		}
		
		return _semaphore;
	}
	
	public void stateChanged(String state)
	{
		Color left,right;
		left = right = null;
		switch (state) {
			case "OpenOpen":
				left = right = Color.GREEN;
				break;
			case "OpenClosed":
				left = Color.GREEN;
				right = Color.RED;
				break;
			case "ClosedOpen":
				left = Color.RED;
				right = Color.GREEN;
				break;
			default:
				break;
		}
		
		Object args[] = {(Object) 6, (Object) left, (Object) right}; 
		
		setChanged();
		notifyObservers(args);
	}
}
