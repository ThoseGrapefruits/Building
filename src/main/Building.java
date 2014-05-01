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

public class Building implements Visible, Runnable, ActionListener
{
	boolean leftPressed = false;
	boolean rightPressed = false;
	boolean spacePressed = false;
	boolean altPressed = false;

	public Building()
	{
		/*
		 * try
		 * {
		 * this.audioStream = new AudioStream( new FileInputStream( "resources/sounds/sa.wav" ) );
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
									else if ( keyCode == KeyEvent.VK_SPACE )
									{
										spacePressed = true;
									}
									else if ( keyCode == KeyEvent.VK_ALT )
									{
										altPressed = true;
									}
									break;

								case KeyEvent.KEY_RELEASED:
									if ( ke.getKeyCode() == KeyEvent.VK_LEFT )
									{
										leftPressed = false;
										// AudioPlayer.player.stop( audioStream );
									}
									else if ( ke.getKeyCode() == KeyEvent.VK_RIGHT )
									{
										rightPressed = false;
										// AudioPlayer.player.stop( audioStream );
									}
									else if ( keyCode == KeyEvent.VK_SPACE )
									{
										spacePressed = false;
									}
									else if ( keyCode == KeyEvent.VK_ALT )
									{
										altPressed = false;
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
		Light newLight = new Light( this, xLight, yLight );
		this.lights.add( newLight );
		this.lightSwitches.add( new LightSwitch( this, xSwitch, ySwitch, newLight ) );
	}

	void addDoor( int x, int floor )
	{
		this.doors.add( new Door( this, x, floor * Constants.FLOOR_DISTANCE ) );
	}

	void addPerson( int x, int y )
	{
		Person person = new Person( this, x, y );
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

		g2d.fillRect( 50, 50, Constants.WINDOW_WIDTH - 100, Constants.WINDOW_HEIGHT - 100 );

		for ( LightSwitch lightSwitch : this.lightSwitches )
		{
			lightSwitch.paint( g2d );
		}
		for ( Elevator elevator : this.elevators )
		{
			elevator.paint( g2d );
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
	 * Called each tick. Updates all positions based on the velocities of objects.
	 */
	@Override
	public void actionPerformed( ActionEvent e )
	{ // Only need to update positions of the mobile objects.

		// User controlled person "Me"
		if ( !this.me.getBounds().intersects(
				new Rectangle( 0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT ) ) )
		{
			this.me.x = 400;
			this.me.y = 200;
		}

		if ( this.altPressed )
		{
			this.me.velocityX = 0;
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
		else
		{
			if ( this.spacePressed && this.me.isFloorBelow() )
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
			me.move( this );
		}

		// AI people
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

		// Elevators
		for ( Elevator elevator : elevators )
		{
			elevator.move( this );
		}
	}
}
