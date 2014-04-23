package main;

import interactive.Door;
import interactive.Elevator;
import interactive.ElevatorButton;
import interactive.Interactive;
import interactive.Light;
import interactive.LightSwitch;

import java.awt.Color;
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

import javafx.geometry.BoundingBox;
import people.Person;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import constants.Constants;
import base.BuildingObject;
import base.Visible;
import boundaries.Floor;
import boundaries.Wall;

public class Building implements Visible, Runnable, ActionListener
{
	boolean leftPressed = false;
	boolean rightPressed = false;
	boolean spacePressed = false;

	public Building()
	{
		try
		{
			this.audioStream = new AudioStream(
					new FileInputStream(
							"/Users/ThoseGrapefruits/Dropbox/School Work/Programming/Java/Building/resources/sounds/sa.wav" ) );
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

	void addLightAndSwitch( int xLight, int yLight, int xSwitch, int ySwitch )
	{
		Light newLight = new Light( xLight, yLight );
		lights.add( newLight );
		lightSwitches.add( new LightSwitch( xSwitch, ySwitch, newLight ) );
	}

	void addDoor( int x, int floor )
	{
		doors.add( new Door( x, floor * Constants.FLOOR_DISTANCE ) );
	}

	void addPerson( int x, int y )
	{
		people.add( new Person( x, y ) );
	}

	@Override
	public void run()
	{
		// TODO thread creations for individual objects (probs just elevators)
	}

	@Override
	public void paint( Graphics2D g2d )
	{
		g2d.setColor( new Color( 100, 100, 100 ) );

		g2d.fillRect( Constants.WINDOW_WIDTH / 12, Constants.WINDOW_HEIGHT / 12,
				Constants.WINDOW_WIDTH - Constants.WINDOW_WIDTH / 6, Constants.WINDOW_HEIGHT
						- Constants.WINDOW_HEIGHT / 6 );

		// Depth 3
		for ( LightSwitch lightSwitch : lightSwitches )
		{
			lightSwitch.paint( g2d );
		}
		for ( Elevator elevator : elevators )
		{
			elevator.paint( g2d );
		}

		// Depth 2
		for ( Person person : people )
		{
			person.paint( g2d );
		}

		// Depth 1
		for ( Door door : doors )
		{
			door.paint( g2d );
		}
		for ( Light light : lights )
		{
			light.paint( g2d );
		}
		for ( Wall wall : walls )
		{
			wall.paint( g2d );
		}
		for ( Floor floor : floors )
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
		Rectangle bounds = sourcePerson.getBounds();
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
		for ( Person person : this.people )
		{
			if ( person.getClass().toString().compareTo( "class people.Me" ) == 0 )
			{
				if ( this.leftPressed && person.velocityX > -( Constants.PERSON_MAX_VELOCITY ) )
				{
					person.velocityX -= 0.1;
				}
				else if ( this.rightPressed && person.velocityX < Constants.PERSON_MAX_VELOCITY )
				{
					person.velocityX += 0.1;
				}
				else if ( person.velocityX > -0.01 && person.velocityX < 0.01 )
				{
					person.velocityX = 0;
				}
				else
				{
					if ( person.velocityX < 0 )
					{
						person.velocityX += 0.1;
					}
					else if ( person.velocityX > 0 )
					{
						person.velocityX -= 0.1;
					}
				}
			}
			if ( this.spacePressed )
			{
				person.wantsToInteract = true;
			}
			else
			{
				person.wantsToInteract = false;
			}
			if ( person.wantsToInteract )
			{
				person.velocityX = 0;
				person.interactiveObjectWithinReach = this.getClosestInteractiveObject( person );
				if ( person.interactiveObjectWithinReach != null )
				{
					( ( Interactive ) person.interactiveObjectWithinReach ).interact( person );
				}
			}
			person.x += person.velocityX;
			person.y += person.velocityY;
		}
		for ( Elevator elevator : elevators )
		{
			elevator.y += elevator.velocityY;
		}
	}
}
