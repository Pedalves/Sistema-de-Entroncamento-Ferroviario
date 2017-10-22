package railroad;

import java.awt.Color;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

class Train extends Observable
{
	private double[] _pos;
	private Integer _id;
	private float _speed;
	private float _initialSpeed;
	
	private boolean _right;
	private boolean _normalMove;
	
	private int _leftInclinationX;
	private int _rightInclinationX;
	
	private int _leftBridgeX;
	private int _rightBridgeX;
	
	private double _angleUp;
	private double _angleDown;
	
	
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
		_pos = new double[2];
		_right = right;
		
		if(_right)
		{
			_leftInclinationX = 140;
			_rightInclinationX = 923;
			
			_leftBridgeX = 300;
			_rightBridgeX = 778;
			
			_angleUp = 13f;
			_angleDown = 19.7f;
			
			_pos[0] = 0;
			_pos[1] = 226;
		}
		else
		{
			_leftInclinationX = 139;
			_rightInclinationX = 940;
			
			_leftBridgeX = 325;
			_rightBridgeX = 799;
			
			_angleUp = 13.2f;
			_angleDown = 17f;
			
			_pos[0] = 1040;
			_pos[1] = 226;
		}
		
		_normalMove = true;
		_enter = _end = _checkingPermission = false;
		
		float conversion = 3.6f;
		float vel = 0;
		float timer = 2;
		int screenWidth = 1078;
		int screenDistance = 1000;
		vel = speed/conversion;
		vel /= timer;
		_speed = _initialSpeed = ((vel*screenWidth)/screenDistance);
		
	}
	
	public void Move()
	{	
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
		
		if(_right) // Left to Right
		{
			if(_pos[0] < _leftInclinationX && _pos[0] > _leftInclinationX - 100)
			{
				_checkingPermission = true;
			}
			else
			{
				_checkingPermission = false;
			}
			
			if(_normalMove) // Straight
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
			else if(leftInclination) // Inclined enter
			{
				_pos[0] += _speed*Math.cos(Math.toRadians(_angleUp));
				_pos[1] += _speed*Math.sin(Math.toRadians(_angleUp));
				
				if(!_enter)
				{
					_enter = true;

					Object args[] = {(Object) 1}; 
					
					setChanged();
					notifyObservers(args);
				}
			}
			else if(rightInclination)
			{
				_pos[0] += _speed*Math.cos(Math.toRadians(_angleDown));
				_pos[1] += _speed*Math.sin(Math.toRadians(_angleDown));
				
				if(!_end)
				{
					_end = true;
				}
			}
		}
		else // Right to Left
		{
			if(_pos[0] > _rightInclinationX && _pos[0] < _rightInclinationX + 100)
			{
				_checkingPermission = true;
			}
			else
			{
				_checkingPermission = false;
			}
			
			if(_normalMove) // Straight
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
			else if(rightInclination)
			{
				_pos[0] -= _speed*Math.cos(Math.toRadians(_angleUp));
				_pos[1] += _speed*Math.sin(Math.toRadians(_angleUp));
				
				if(!_enter)
				{
					_enter = true;

					Object args[] = {(Object) 3}; 
					
					setChanged();
					notifyObservers(args);
				}
			}
			else if(leftInclination)
			{
				_pos[0] -= _speed*Math.cos(Math.toRadians(_angleDown));
				_pos[1] += _speed*Math.sin(Math.toRadians(_angleDown));
				
				if(!_end)
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
	
	public double[] getPos()
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
	
	public float getMaxSpeed()
	{
		return _initialSpeed;
	}
	
	public void stop()
	{
		_speed = 0;
	}
	
	public void go()
	{
		_speed = _initialSpeed;
	}
}
