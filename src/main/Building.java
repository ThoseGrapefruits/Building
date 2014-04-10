package main;

import interactive.Door;
import interactive.Elevator;
import interactive.Light;
import interactive.LightSwitch;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import people.Person;
import constants.Constants;
import base.Visible;
import boundaries.Floor;
import boundaries.Wall;

public class Building implements Visible, Runnable
{

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
		for ( Door door : doors )
		{
			( new Thread( door ) ).start();
		}
		// TODO finish thread creations.
	}

	@Override
	public void paint( Graphics2D g2d )
	{
		g2d.drawRect( Constants.WINDOW_WIDTH / 12, Constants.WINDOW_HEIGHT / 12,
				Constants.WINDOW_WIDTH - Constants.WINDOW_WIDTH / 6, Constants.WINDOW_HEIGHT
						- Constants.WINDOW_HEIGHT / 6 );

	}
}
