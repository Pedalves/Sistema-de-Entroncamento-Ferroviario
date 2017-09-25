package railroad;

import java.util.Observable;

class Train extends Observable
{
	private int[] _pos;
	private Integer _id;
	
	private boolean _right;
	private boolean _normalMove;
	
	private final int _leftInclinationX = 200;
	private final int _rightInclinationX = 1350;
	
	private final int _leftBridgeX = 460;
	private final int _rightBridgeX = 1140;
	
	public Train(boolean right, Integer id)
	{
		//addObserver(Facade.getInstance(null).getView());
		_id = id;
		_pos = new int[2];
		_right = right;
		if(_right)
		{
			_pos[0] = 0;
			_pos[1] = 330;
		}
		else
		{
			_pos[0] = 0;
			_pos[1] = 1575;
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
				_pos[0] += 1;
			}
			else
			{
				_pos[0] += 1;
				_pos[1] -= 1;
			}
		}
		else
		{
			if(_normalMove)
			{
				_pos[0] -= 1;
			}
			else
			{
				_pos[0] -= 1;
				_pos[1] -= 1;
			}
		}
		
		Object args[] = {(Object) 0, (Object) _id, (Object)_pos}; 
		
		notifyObservers(args);
	}
}
