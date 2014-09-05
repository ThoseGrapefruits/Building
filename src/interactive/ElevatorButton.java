package interactive;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.Building;
import base.BuildingObject;
import base.Interactive;
import base.Visible;
import constants.Constants;

/**
 * The buttons you press to make the big metal box go up or down.
 * 
 * @author Logan Moore
 */
public class ElevatorButton extends BuildingObject implements Interactive, Visible, Runnable,
		ActionListener
{
	/**
	 * The elevator to be called when the button is pressed.
	 */
	Elevator linkedElevator;

	/**
	 * The floor location of the button.
	 */
	public int floor;

	/**
	 * Whether the elevator is on the same floor as the button
	 */
	public boolean elevatorOnCurrentFloor = false;

	/**
	 * Creates a new button with a linked elevator and a floor index.
	 *  
	 * @param building
	 * @param x
	 * @param y
	 * @param linkedElevator
	 * @param floor
	 */
	public ElevatorButton( Building building, double x, double y, Elevator linkedElevator, int floor )
	{
		super( building, x, y, Constants.ELEVATOR_BUTTON_BACKING_WIDTH,
				Constants.ELEVATOR_BUTTON_BACKING_HEIGHT );
		this.linkedElevator = linkedElevator;
		this.floor = floor;
	}

	@Override
	public void run()
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void interact( BuildingObject interacter )
	{
		this.linkedElevator.call( this );
	}

	@Override
	public void paint( Graphics2D g2d )
	{
		g2d.setColor( Constants.BUTTON_BACKING_COLOR );
		g2d.fillRect( ( int ) this.x, ( int ) this.y, this.width, this.height );
		if ( this.elevatorOnCurrentFloor )
		{
			g2d.setColor( Color.RED );
		}
		else
		{
			g2d.setColor( Constants.BUTTON_COLOR );
		}
		g2d.fillRoundRect( ( int ) this.x + this.width / 4, ( int ) this.y + this.height / 8,
				Constants.ELEVATOR_BUTTON_WIDTH, Constants.ELEVATOR_BUTTON_HEIGHT,
				Constants.ELEVATOR_BUTTON_WIDTH / 2, Constants.ELEVATOR_BUTTON_HEIGHT / 2 );
		g2d.fillRoundRect( ( int ) this.x + this.width / 4, ( int ) this.y + this.height
				- Constants.ELEVATOR_BUTTON_HEIGHT - 2, Constants.ELEVATOR_BUTTON_WIDTH,
				Constants.ELEVATOR_BUTTON_HEIGHT, Constants.ELEVATOR_BUTTON_WIDTH / 2,
				Constants.ELEVATOR_BUTTON_HEIGHT / 2 );
		if ( this.drawBounds )
		{
			this.drawBounds( g2d );
		}
	}

	@Override
	public void actionPerformed( ActionEvent e )
	{
		this.linkedElevator.call( this );
	}
}
