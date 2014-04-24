package interactive;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import base.BuildingObject;
import base.Interactive;
import base.Visible;
import constants.Constants;

public class ElevatorButton extends BuildingObject implements Interactive, Visible, Runnable,
		ActionListener
{
	Elevator linkedElevator;

	int floor;

	public ElevatorButton( int x, int y, Elevator linkedElevator, int floor )
	{
		super( x, y, Constants.ELEVATOR_BUTTON_WIDTH, Constants.ELEVATOR_BUTTON_HEIGHT );
		this.linkedElevator = linkedElevator;
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

	@Override
	public void actionPerformed( ActionEvent e )
	{
		this.linkedElevator.call( this.floor );
	}
}
