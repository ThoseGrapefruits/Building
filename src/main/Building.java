package main;

import interactive.Door;
import interactive.Elevator;
import interactive.ElevatorButton;
import interactive.Light;
import interactive.LightSwitch;

import java.awt.Graphics2D;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import people.Me;
import people.Person;
import base.Interactive;
import base.Visible;
import boundaries.Floor;
import boundaries.Wall;
import constants.Constants;

/**
 * Building, in which all <pre>BuildingObject</pre>s exist. Manages drawing of the individual objects. User-controlled character is controlled entirely through this class.
 * 
 * @author Logan Moore
 */
public class Building implements Visible, ActionListener
{
	boolean leftPressed = false;
	boolean rightPressed = false;
	boolean upPressed = false;
	boolean spacePressed = false;
	boolean shiftPressed = false;

	public Building()
	{
		/*
		 * try
		 * {
		 * this.audioStream = new AudioStream( new FileInputStream(
		 * "resources/sounds/sa.wav" ) );
		 * }
		 * catch ( FileNotFoundException e )
		 * {
		 * e.printStackTrace();
		 * }
		 * catch ( IOException e )
		 * {
		 * e.printStackTrace();
		 * }
		 */

		// Keyboard input for user-controlled person.
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(
				new KeyEventDispatcher()
				{
					@Override
					public boolean dispatchKeyEvent( KeyEvent ke )
					{
						synchronized ( Building.class )
						{
							int keyCode = ke.getKeyCode();
							switch ( ke.getID() )
							{
								case KeyEvent.KEY_PRESSED:
									if ( keyCode == KeyEvent.VK_LEFT )
									{
										leftPressed = true;
										// AudioPlayer.player.start( audioStream );
									}
									else if ( keyCode == KeyEvent.VK_RIGHT )
									{
										rightPressed = true;
										// AudioPlayer.player.start( audioStream );
									}
									else if ( keyCode == KeyEvent.VK_UP )
									{
										upPressed = true;
									}
									else if ( keyCode == KeyEvent.VK_SPACE )
									{
										spacePressed = true;
										interactMe();
									}
									else if ( keyCode == KeyEvent.VK_SHIFT )
									{
										shiftPressed = true;
									}
									break;

								case KeyEvent.KEY_RELEASED:
									if ( keyCode == KeyEvent.VK_LEFT )
									{
										leftPressed = false;
										// AudioPlayer.player.stop( audioStream
										// );
									}
									else if ( keyCode == KeyEvent.VK_RIGHT )
									{
										rightPressed = false;
										// AudioPlayer.player.stop( audioStream
										// );
									}
									else if ( keyCode == KeyEvent.VK_UP )
									{
										upPressed = false;
									}
									else if ( keyCode == KeyEvent.VK_SPACE )
									{
										spacePressed = false;
									}
									else if ( keyCode == KeyEvent.VK_SHIFT )
									{
										shiftPressed = false;
									}
									break;
							}
							return false;
						}
					}
				} );
	}

	// Interactive Objects
	public ArrayList < Light > lights = new ArrayList < Light >();
	public ArrayList < LightSwitch > lightSwitches = new ArrayList < LightSwitch >();
	public ArrayList < Door > doors = new ArrayList < Door >();
	public ArrayList < Elevator > elevators = new ArrayList < Elevator >();
	public ArrayList < ElevatorButton > elevatorButtons = new ArrayList < ElevatorButton >();

	// Boundaries
	public ArrayList < Wall > walls = new ArrayList < Wall >();
	public ArrayList < Floor > floors = new ArrayList < Floor >();

	// People
	public ArrayList < Person > people = new ArrayList < Person >();
	public Me me;

	/**
	 * Adds associated lights and switches, with individual position control.
	 * 
	 * @param xLight is the x-coordinate of the <code>Light</code>.
	 * @param yLight is the y-coordinate of the <code>Light</code>.
	 * @param xSwitch is the x-coordinate of the <code>LightSwitch</code>.
	 * @param ySwitch is the y-coordinate of the <code>LightSwitch</code>.
	 */
	void addLightAndSwitch( int xLight, int yLight, int xSwitch, int ySwitch )
	{
		Light newLight = new Light( this, xLight, yLight );
		this.lights.add( newLight );
		this.lightSwitches.add( new LightSwitch( this, xSwitch, ySwitch, newLight ) );
	}

	/**
	 * Called when the user presses the Interact button. Finds the closest interactive
	 * <code>BuildingObject</code> or <code>Person</code> and calls their <code>interact()</code>
	 * function.
	 */
	void interactMe()
	{
		this.me.interactiveObjectWithinReach = this.me.getClosestInteractiveObject();
		if ( this.me.interactiveObjectWithinReach != null )
		{
			( ( Interactive ) this.me.interactiveObjectWithinReach ).interact( this.me );
		}
		for ( Person person : this.people )
		{
			if ( person.getBounds().intersects( this.me.getBounds() ) )
			{
				person.interact( this.me );
			}
		}
	}

	void addDoor( int x, int floor )
	{
		this.doors.add( new Door( this, x, floor * Constants.FLOOR_DISTANCE ) );
	}

	void addElevator( double x, double y, int[] floors )
	{
		Elevator newElevator = new Elevator( this, x, y, floors );
		this.elevators.add( newElevator );
		new Thread( newElevator ).start();

	}

	private int floorCount = 0;

	/**
	 * Returns the number of <code>Floor</code>s in the current <code>Building</code>.
	 * 
	 * @return <code>floorCount</code>
	 */
	public int getFloorCount()
	{
		return this.floorCount;
	}

	/**
	 * Adds a floor to the building at the given coordinates.
	 * 
	 * @param x is the x-coordinate of the upper-left corner of the <code>Floor</code>'s bounds.
	 * @param y is the y-coordinate of the upper-left corner of the <code>Floor</code>'s bounds.
	 * @param width is the width of the <code>Floor</code>.
	 */
	void addFloor( int x, int y, int width )
	{
		this.floors.add( new Floor( this, x, y, width, floorCount ) );
		this.floorCount++;
	}

	/**
	 * Adds a person to the building at the given coordinates.
	 * 
	 * @param x is the x-coordinate of the upper-left corner of the person's bounds.
	 * @param y is the y-coordinate of the upper-left corner of the person's bounds.
	 */
	void addPerson( int x, int y )
	{
		Person person = new Person( this, x, y );
		this.people.add( person );
		new Thread( person ).start();
	}

	/**
	 * Adds a person to the building.
	 * 
	 * This is used mostly if the <code>person</code> needs to be customized beforehand or if
	 * a special subclass of <code>person</code> is being used instead.
	 * @param person is the already-made 
	 */
	void addPerson( Person person )
	{
		this.people.add( person );
		new Thread( person ).start();
	}

	/**
	 * Calls all of the <code>paint()</code> functions of all the objects in the building, as
	 * well as a base rectangle for the building itself.
	 */
	@Override
	public void paint( Graphics2D g2d )
	{
		g2d.setColor( Constants.BUILDING_COLOR );

		g2d.fillRect( 50, 50, Constants.WINDOW_WIDTH - 100, Constants.WINDOW_HEIGHT - 100 );

		for ( LightSwitch lightSwitch : this.lightSwitches )
		{
			lightSwitch.paint( g2d );
		}
		for ( Elevator elevator : this.elevators )
		{
			elevator.paint( g2d );
		}
		for ( ElevatorButton elevatorButton : this.elevatorButtons )
		{
			elevatorButton.paint( g2d );
		}
		for ( Door door : this.doors )
		{
			door.paint( g2d );
		}
		for ( Light light : this.lights )
		{
			light.paint( g2d );
		}
		for ( Wall wall : this.walls )
		{
			wall.paint( g2d );
		}
		for ( Floor floor : this.floors )
		{
			floor.paint( g2d );
		}
		this.me.paint( g2d );
		for ( Person person : this.people )
		{
			person.paint( g2d );
		}
	}

	// AudioStream audioStream;

	/**
	 * Called each tick. Calls the <code>move</code> function on each of the moveable objects.
	 * 
	 * Also updates velocity of user-controlled person.
	 */
	@Override
	public void actionPerformed( ActionEvent e )
	{ // Only need to update positions of the mobile objects

		// Determine user-controlled person's next movement
		if ( !this.me.getBounds().intersects(
				new Rectangle( 0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT ) ) )
		{
			this.me.x = 400;
			this.me.y = 200;
		}

		if ( this.upPressed && this.me.isFloorBelow() )
		{
			this.me.velocityY = -5;
		}
		else if ( !this.me.canMoveY( this ) )
		{
			this.me.velocityY = 0;
		}
		else if ( this.me.velocityY < Constants.TERMINAL_VELOCITY )
		{
			this.me.velocityY += 0.1;
		}

		if ( this.leftPressed && this.me.velocityX > -( Constants.PERSON_MAX_VELOCITY ) )
		{
			this.me.velocityX -= 0.1;
		}
		else if ( this.rightPressed && this.me.velocityX < Constants.PERSON_MAX_VELOCITY )
		{
			this.me.velocityX += 0.1;
		}
		else if ( this.me.velocityX > -0.01 && this.me.velocityX < 0.01 )
		{
			this.me.velocityX = 0;
		}
		else
		{
			if ( this.me.velocityX < 0 )
			{
				this.me.velocityX += 0.1;
			}
			else if ( this.me.velocityX > 0 )
			{
				this.me.velocityX -= 0.1;
			}
		}

		// Update AI people positions
		for ( Person person : this.people )
		{
			person.move( this );
			if ( !person.getBounds().intersects(
					new Rectangle( 0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT ) ) )
			{
				person.x = 400;
				person.y = 200;
			}
		}

		// Update elevator & elevator passenger positions
		for ( Elevator elevator : elevators )
		{
			elevator.move( this );
			if ( elevator.passenger != null )
			{
				elevator.passenger.velocityY = 0;
				elevator.passenger.y = elevator.getCarHeight() + Constants.ELEVATOR_CAR_HEIGHT
						- elevator.passenger.getHeight();
				if ( elevator.passenger.x < elevator.x )
				{
					elevator.passenger.x = elevator.x;
				}
				else if ( elevator.passenger.x + elevator.passenger.getWidth() > elevator.x
						+ elevator.getWidth() )
				{
					elevator.passenger.x = elevator.x + elevator.getWidth()
							- elevator.passenger.getWidth();
				}
			}
		}

		// Update user-controlled person's position
		me.move( this );
	}
}
