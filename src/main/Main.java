package main;

import boundaries.Wall;
import constants.Constants;
import interactive.Door;
import people.Chunk;
import people.ChunkObject;
import people.Me;
import view.Surface;
import view.View;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Main controller for the program.
 *
 * @author Logan Moore
 *
 */
public class Main
{
	/**
	 * Launch the application.
	 */
	public static void main( String[] args )
	{
		// Create a new building
		final Building building = new Building();

		// Add people
		building.addMe( new Me( building, 100, 450 ) );
		building.addPerson( 500, 450 );

		// Outer doors/walls
		building.addWall( new Wall( building, 750, 50, 390 ) );
		building.addWall( new Wall( building, 50, 50, 390 ) );
		building.addDoor( new Door( building, 750, 440 ) );
		building.addDoor( new Door( building, 50, 440 ) );

		// Inner doors/walls
		building.addDoor( new Door( building, 375, 440 ) );
		building.addWall( new Wall( building, 375, 440 - Constants.FLOOR_HEIGHT,
				Constants.FLOOR_DISTANCE - Constants.DOOR_HEIGHT - Constants.FLOOR_HEIGHT ) );
		building.addDoor( new Door( building, 375, 440 - Constants.FLOOR_DISTANCE ) );
		building.addWall( new Wall( building, 375, 440 - Constants.FLOOR_DISTANCE
				- Constants.FLOOR_HEIGHT, Constants.FLOOR_DISTANCE - Constants.DOOR_HEIGHT
				- Constants.FLOOR_HEIGHT ) );
		building.addDoor( new Door( building, 375, 440 - 2 * Constants.FLOOR_DISTANCE ) );
		building.addWall( new Wall( building, 375, 440 - 2 * Constants.FLOOR_DISTANCE
				- Constants.FLOOR_HEIGHT, Constants.FLOOR_DISTANCE - Constants.DOOR_HEIGHT
				- Constants.FLOOR_HEIGHT ) );
		building.addDoor( new Door( building, 375, 440 - 3 * Constants.FLOOR_DISTANCE ) );
		building.addWall( new Wall( building, 375, 440 - 3 * Constants.FLOOR_DISTANCE
				- Constants.FLOOR_HEIGHT, Constants.FLOOR_DISTANCE - Constants.DOOR_HEIGHT
				- Constants.FLOOR_HEIGHT ) );

		// Floors
		building.addFloor( 50, 550, 800 );
		building.addFloor( 50, 550 - Constants.FLOOR_DISTANCE, 700 );
		building.addFloor( 50, 550 - Constants.FLOOR_DISTANCE * 2, 700 );
		building.addFloor( 50, 550 - Constants.FLOOR_DISTANCE * 3, 700 );
		building.addFloor( 50, 550 - Constants.FLOOR_DISTANCE * 4, 700 );

		// Lights
		// building.addLightAndSwitch( 100, 420, 100, 450 );

		// Elevators
		building.addElevator( 200.0, 50.0, new int[]
				{ 0, 1, 2, 3 } );
		building.addElevator( 500.0, 50.0, new int[]
				{ 0, 1, 2, 3 } );

		// Chunks
		ChunkObject c = new ChunkObject( building, 300, 450 );

		ArrayList<Chunk> cs = new ArrayList<Chunk>();
		cs.add( new Chunk( building, 300, 450, 5, 5, c, 10 ) );
		cs.add( new Chunk( building, 320, 450, 5, 5, c, 10 ) );
		cs.add( new Chunk( building, 280, 450, 5, 5, c, 10 ) );
		cs.add( new Chunk( building, 300, 440, 5, 5, c, 10 ) );
		cs.add( new Chunk( building, 300, 460, 5, 5, c, 10 ) );

		for ( Chunk cur : cs )
		{
			c.addChunk( cur );
			ArrayList<Chunk> a = new ArrayList<Chunk>(cs);
			a.remove( cur );
			cur.connect( a );
		}
		System.out.println( c );
		building.addChunkObject( c );

		// Create the visuals
		SwingUtilities.invokeLater( new Runnable()
		{
			@Override
			public void run()
			{
				Surface panel = new Surface( building );
				View view = new View( panel );
				view.setVisible( true );
				Timer timer = new Timer( Constants.TICK, panel );
				timer.setInitialDelay( 0 );
				timer.start();
			}
		} );
	}
}
