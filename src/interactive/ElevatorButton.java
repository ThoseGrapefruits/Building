package interactive;

import java.awt.Graphics2D;

import constants.Constants;
import base.BuildingObject;
import base.Visible;

public class ElevatorButton extends BuildingObject implements Interactive, Visible, Runnable
{

	protected ElevatorButton( int x, int y, int width, int height )
	{
		super( x, y, Constants.ELEVATOR_BUTTON_WIDTH, Constants.ELEVATOR_BUTTON_HEIGHT );
	}

	@Override
	public void run()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void interact( BuildingObject interacter )
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void paint( Graphics2D g2d )
	{
		// TODO Auto-generated method stub

	}
}
