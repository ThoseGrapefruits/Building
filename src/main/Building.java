package main;

import interactive.Door;
import interactive.Elevator;
import interactive.Light;
import interactive.LightSwitch;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Timer;

import people.Person;
import constants.Constants;
import base.Visible;
import boundaries.Floor;
import boundaries.Wall;

public class Building implements Visible, Runnable, ActionListener
{
	Building()
	{
		Timer timer = new Timer( 10, this );
		timer.setInitialDelay( 0 );
		timer.start();
	}

	// Interactive Objects
	public ArrayList < Light > lights = new ArrayList < Light >();
	public ArrayList < LightSwitch > lightSwitches = new ArrayList < LightSwitch >();
	public ArrayList < Door > doors = new ArrayList < Door >();
	public ArrayList < Elevator > elevators = new ArrayList < Elevator >();

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

		for ( Person person : people )
		{
			person.paint( g2d );
		}
		for ( Elevator elevator : elevators )
		{
			elevator.paint( g2d );
		}
		for ( Door door : doors )
		{
			door.paint( g2d );
		}
		for ( LightSwitch lightSwitch : lightSwitches )
		{
			lightSwitch.paint( g2d );
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
	 * Called each tick. Updates all positions based on the velocities of objects.
	 */
	@Override
	public void actionPerformed( ActionEvent e )
	{
		// Only need to update positions of the mobile objects.
		for ( Person person : this.people )
		{
			person.x += person.getVelocityX();
			person.y += person.getVelocityY();
		}
		for ( Elevator elevator : elevators )
		{
			elevator.y += elevator.getVelocityY();
		}
	}
}
