package interactive;

import java.awt.Graphics2D;

import base.BuildingObject;
import base.Interactive;
import base.Visible;
import constants.Constants;

public class Door extends BuildingObject implements Interactive, Visible, Runnable
{
	public Door( int x, int y )
	{
		super( x, y, Constants.DOOR_WIDTH, Constants.DOOR_HEIGHT );
	}

	public boolean open = false;

	public void open()
	{
		open = true;
	}

	public void close()
	{
		open = false;
	}

	public void toggleOpen()
	{
		if ( this.open )
		{
			this.close();
		}
		else
		{
			this.open();
		}
	}

	@Override
	public void run()
	{
		// TODO
	}

	@Override
	public void interact( BuildingObject interacter )
	{
		this.inUse = true;
		this.toggleOpen();
		this.inUse = false;
	}

	@Override
	public void paint( Graphics2D g2d )
	{
		// TODO Auto-generated method stub

	}
}
