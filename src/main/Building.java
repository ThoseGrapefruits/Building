package main;

import interactive.Door;
import interactive.Elevator;
import interactive.Light;
import interactive.LightSwitch;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
	ArrayList < Light > lights = new ArrayList < Light >();
	ArrayList < LightSwitch > lightSwitches = new ArrayList < LightSwitch >();
	ArrayList < Door > doors = new ArrayList < Door >();
	ArrayList < Elevator > elevators = new ArrayList < Elevator >();

	// Boundaries
	ArrayList < Wall > walls = new ArrayList < Wall >();
	ArrayList < Floor > floors = new ArrayList < Floor >();

	// People
	ArrayList < Person > people = new ArrayList < Person >();

	public ArrayList < Light > getLights()
	{
		return this.lights;
	}

	public ArrayList < LightSwitch > getLightSwitches()
	{
		return this.lightSwitches;
	}

	public ArrayList < Door > getDoors()
	{
		return this.doors;
	}

	public ArrayList < Elevator > getElevators()
	{
		return this.elevators;
	}

	public ArrayList < Wall > getWalls()
	{
		return this.walls;
	}

	public ArrayList < Floor > getFloors()
	{
		return this.floors;
	}

	public ArrayList < Person > getPeople()
	{
		return this.people;
	}

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

	@Override
	public void run()
	{
		// TODO thread creations for individual objects
	}

	@Override
	public void paint( Graphics2D g2d )
	{
		g2d.setColor( new Color( 100, 100, 100, 255 ) );
		g2d.fillRect( Constants.WINDOW_WIDTH / 12, Constants.WINDOW_HEIGHT / 12,
				Constants.WINDOW_WIDTH - Constants.WINDOW_WIDTH / 6, Constants.WINDOW_HEIGHT
						- Constants.WINDOW_HEIGHT / 6 );

	}

	/**
	 * Called each tick. Updates all positions based on the velocities of objects.
	 */
	@Override
	public void actionPerformed( ActionEvent e )
	{
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
