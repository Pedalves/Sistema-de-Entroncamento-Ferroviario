package railroad;

import java.awt.Color;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

class Train extends Observable
{
	private int[] _pos;
	private Integer _id;
	private float _speed;
	private float _inititalSpeed;
	
	private boolean _right;
	private boolean _normalMove;
	
	private final int _leftInclinationX = 137;
	private final int _rightInclinationX = 940;
	
	private final int _leftBridgeX = 305;
	private final int _rightBridgeX = 790;
	
	private final int _angle = 73;
	
	private boolean _enter;
	private boolean _end;
	
	private boolean _checkingPermission;
	
	public Train(boolean right, Integer id, List<Observer> observers, int speed)
	{
		for(Observer o : observers)
		{
			addObserver(o);	
		}
		_id = id;
		_pos = new int[2];
		_right = right;
		
		if(_right)
		{
			_pos[0] = 0;
			_pos[1] = 214;
		}
		else
		{
			_pos[0] = 1040;
			_pos[1] = 221;
		}
		
		_normalMove = true;
		_enter = _end = _checkingPermission = false;
		
		float conversion = 3.6f;
		float vel = 0;
		float timer = 5;
		int screenWidth = 1078;
		int screenDistance = 1000;
		vel = speed/conversion;
		//vel /= timer;
		_speed = _inititalSpeed = ((vel*screenWidth)/screenDistance);
	}
	
	public void Move()
	{
		if((_right && _pos[0] >= 1150) || (!_right && _pos[0] <= -200))
		{
			Object args[] = {(Object) 5, (Object) this}; 
			
			setChanged();
			notifyObservers(args);
			
			return;
		}
		
		boolean leftInclination = (_pos[0] > _leftInclinationX && _pos[0] < _leftBridgeX);
		boolean rightInclination = (_pos[0] > _rightBridgeX && _pos[0] < _rightInclinationX);
		
		if(leftInclination || rightInclination)
		{
			_normalMove = false;
		}
		else
		{
			_normalMove = true;
		}
		
		if(_right)
		{
			if(_pos[0] < _leftInclinationX && _pos[0] > _leftInclinationX - 100)
			{
				_checkingPermission = true;
			}
			else
			{
				_checkingPermission = false;
			}
			
			if(_normalMove)
			{
				_pos[0] += _speed;
				
				if(_enter && _end)
				{
					_end = false;
					
					Object args[] = {(Object) 2}; 
					
					setChanged();
					notifyObservers(args);
				}
			}
			else
			{
				_pos[0] += _speed*Math.sin(Math.toRadians(_angle));
				_pos[1] += _speed*Math.cos(Math.toRadians(_angle));
				
				if(leftInclination && !_enter)
				{
					_enter = true;

					Object args[] = {(Object) 1}; 
					
					setChanged();
					notifyObservers(args);
				}
				if(rightInclination && !_end)
				{
					_end = true;
				}
			}
		}
		else
		{
			if(_pos[0] > _rightInclinationX && _pos[0] < _rightInclinationX + 100)
			{
				_checkingPermission = true;
			}
			else
			{
				_checkingPermission = false;
			}
			
			if(_normalMove)
			{
				_pos[0] -= _speed;
				
				if(_enter && _end)
				{
					_end = false;
					
					Object args[] = {(Object) 4}; 
					
					setChanged();
					notifyObservers(args);
				}
			}
			else
			{
				_pos[0] -= _speed*Math.sin(Math.toRadians(_angle));
				_pos[1] += _speed*Math.cos(Math.toRadians(_angle));
				
				if(rightInclination && !_enter)
				{
					_enter = true;

					Object args[] = {(Object) 3}; 
					
					setChanged();
					notifyObservers(args);
				}
				if(leftInclination && !_end)
				{
					_end = true;
				}
			}
		}
		
		Object args[] = {(Object) 0, (Object) _id, (Object) _pos, _right?(Object) Color.BLACK:(Object) Color.RED}; 
		
		setChanged();
		notifyObservers(args);
	}
	
	public boolean getCheckPermission()
	{
		return _checkingPermission;
	}
	
	public boolean isMovingRight()
	{
		return _right;
	}
	
	public int[] getPos()
	{
		return _pos;
	}
	
	public float getSpeed()
	{
		return _speed;
	}
	
	public void setSpeed(float speed)
	{
		_speed = speed;
	}
	
	public void stop()
	{
		_speed = 0;
	}
	
	public void go()
	{
		_speed = _inititalSpeed;
	}
}
