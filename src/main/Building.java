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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import people.Me;
import people.Person;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import base.BuildingObject;
import base.Interactive;
import base.Visible;
import boundaries.Floor;
import boundaries.Wall;
import constants.Constants;

public class Building implements Visible, Runnable, ActionListener
{
	boolean leftPressed = false;
	boolean rightPressed = false;
	boolean spacePressed = false;

	public Building()
	{
		try
		{
			this.audioStream = new AudioStream( new FileInputStream( "resources/sounds/sa.wav" ) );
		}
		catch ( FileNotFoundException e )
		{
			e.printStackTrace();
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}

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
										AudioPlayer.player.start( audioStream );
									}
									else if ( keyCode == KeyEvent.VK_RIGHT )
									{
										rightPressed = true;
										AudioPlayer.player.start( audioStream );
									}
									else if ( keyCode == KeyEvent.VK_SPACE )
									{
										spacePressed = true;
									}
									break;

								case KeyEvent.KEY_RELEASED:
									if ( ke.getKeyCode() == KeyEvent.VK_LEFT )
									{
										leftPressed = false;
										AudioPlayer.player.stop( audioStream );
									}
									else if ( ke.getKeyCode() == KeyEvent.VK_RIGHT )
									{
										rightPressed = false;
										AudioPlayer.player.stop( audioStream );
									}
									else if ( keyCode == KeyEvent.VK_SPACE )
									{
										spacePressed = false;
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

	void addLightAndSwitch( int xLight, int yLight, int xSwitch, int ySwitch )
	{
		Light newLight = new Light( xLight, yLight );
		this.lights.add( newLight );
		this.lightSwitches.add( new LightSwitch( xSwitch, ySwitch, newLight ) );
	}

	void addDoor( int x, int floor )
	{
		this.doors.add( new Door( x, floor * Constants.FLOOR_DISTANCE ) );
	}

	void addPerson( int x, int y )
	{
		Person person = new Person( x, y );
		this.people.add( person );
		new Thread( person ).start();

	}

	@Override
	public void run()
	{
		// TODO thread creations for individual objects (probs just elevators)
	}

	@Override
	public void paint( Graphics2D g2d )
	{
		g2d.setColor( Constants.BUILDING_COLOR );

		g2d.fillRect( Constants.WINDOW_WIDTH / 12, Constants.WINDOW_HEIGHT / 12,
				Constants.WINDOW_WIDTH - Constants.WINDOW_WIDTH / 6, Constants.WINDOW_HEIGHT
						- Constants.WINDOW_HEIGHT / 6 );

		// Depth 3
		for ( LightSwitch lightSwitch : this.lightSwitches )
		{
			lightSwitch.paint( g2d );
		}
		for ( Elevator elevator : this.elevators )
		{
			elevator.paint( g2d );
		}

		// Depth 2
		this.me.paint( g2d );
		for ( Person person : this.people )
		{
			person.paint( g2d );
		}

		// Depth 1
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
	}

	/**
	 * Finds the closest object to a person within its BoundingBox.
	 * 
	 * @param sourcePerson is the person trying to interact.
	 * @return the interactive object closest to the person
	 */
	private BuildingObject getClosestInteractiveObject( Person sourcePerson )
	{
		Rectangle origBounds = sourcePerson.getBounds();
		Rectangle bounds = new Rectangle( origBounds.x - 5, origBounds.y - 5,
				origBounds.width + 10, origBounds.height + 10 );
		for ( LightSwitch lightSwitch : lightSwitches )
		{
			if ( lightSwitch.getBounds().intersects( bounds ) )
			{
				return lightSwitch;
			}
		}

		for ( ElevatorButton elevatorButton : elevatorButtons )
		{
			if ( elevatorButton.getBounds().intersects( bounds ) )
			{
				return elevatorButton;
			}
		}

		for ( Door door : doors )
		{
			if ( door.getBounds().intersects( bounds ) )
			{
				return door;
			}
		}
		return null;
	}

	AudioStream audioStream;

	/**
	 * Called each tick. Updates all positions based on the velocities of objects.
	 */
	@Override
	public void actionPerformed( ActionEvent e )
	{
		// Only need to update positions of the mobile objects.

		// User controlled person "Me"
		if ( this.spacePressed )
		{
			this.me.velocityX = 0;
			this.me.interactiveObjectWithinReach = this.getClosestInteractiveObject( this.me );
			if ( this.me.interactiveObjectWithinReach != null )
			{
				( ( Interactive ) this.me.interactiveObjectWithinReach ).interact( this.me );
			}
		}
		else
		{
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
			me.move( this );
		}

		// AI people
		for ( Person person : this.people )
		{
			person.move( this );
		}

		// Elevators
		for ( Elevator elevator : elevators )
		{
			elevator.move( this );
		}
	}
}
