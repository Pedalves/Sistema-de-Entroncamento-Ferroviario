package railroad;

import java.util.Observable;
import java.util.Observer;

class Train extends Observable
{
	private int[] _pos;
	private Integer _id;
	private int _speed = 20;
	
	private boolean _right;
	private boolean _normalMove;
	
	private final int _leftInclinationX = 140;
	private final int _rightInclinationX = 940;
	
	private final int _leftBridgeX = 305;
	private final int _rightBridgeX = 790;
	
	public Train(boolean right, Integer id, Observer observer)
	{
		addObserver(observer);
		_id = id;
		_pos = new int[2];
		_right = right;
		if(_right)
		{
			_pos[0] = 0;
			_pos[1] = 223;
		}
		else
		{
			_pos[0] = 1040;
			_pos[1] = 223;
		}
		_normalMove = true;
		Move();
	}
	
	public void Move()
	{
		if((_pos[0] > _leftInclinationX && _pos[0] < _leftBridgeX) || (_pos[0] > _rightBridgeX && _pos[0] < _rightInclinationX))
		{
			_normalMove = false;
		}
		else
		{
			_normalMove = true;
		}
		
		if(_right)
		{
			if(_normalMove)
			{
				_pos[0] += _speed;
			}
			else
			{
				_pos[0] += _speed*Math.sin(Math.toRadians(73));
				_pos[1] += _speed*Math.cos(Math.toRadians(73));
			}
		}
		else
		{
			if(_normalMove)
			{
				_pos[0] -= _speed;
			}
			else
			{
				_pos[0] -= _speed*Math.sin(Math.toRadians(73));
				_pos[1] += _speed*Math.cos(Math.toRadians(73));
			}
		}
		
		Object args[] = {(Object) 0, (Object) _id, (Object)_pos}; 
		
		setChanged();
		notifyObservers(args);
	}
}
