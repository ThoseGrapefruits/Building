package people;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import interactive.Interactive;
import constants.Constants;
import base.BuildingObject;
import base.Visible;

public class Person extends BuildingObject implements Interactive, Visible, Runnable
{
	public Person( int x, int y )
	{
		super( x, y, Constants.PERSON_WIDTH, Constants.PERSON_HEIGHT );

		// try
		// {
		// this.face = ImageIO.read( new File( "resources/images/person/face/default.png" ) );
		// }
		// catch ( IOException e )
		// {
		// e.printStackTrace();
		// }
	}

	public BuildingObject interactiveObjectWithinReach;

	public double velocityX = 0, velocityY = 0;

	public boolean wantsToInteract = false;

	/**
	 * List of listeners
	 */
	List < Interactive > listeners = new ArrayList < Interactive >();

	/**
	 * Adds a new listener to the list of listeners.
	 * 
	 * @param toAdd is the listener being added.
	 */
	public void addListener( Interactive toAdd )
	{
		listeners.add( toAdd );
	}

	public void interactWith()
	{
		// Notify all possibly relevant objects.
		for ( Interactive object : listeners )
		{
			object.interact( this );
		}
	}

	/**
	 * Image used for the face of the given person.
	 */
	BufferedImage face;
	/**
	 * Image used for the body of the given person.
	 */
	BufferedImage body; // TODO set this in the instantiation

	public BufferedImage getFace()
	{
		return face;
	}

	String toBeSaid = "";

	int time = 0;

	private String name = "Anonymous" + ( 1 + ( int ) Math.random() * 100 );

	public String getName()
	{
		return this.name;
	}

	/**
	 * Method for person-to-person interaction
	 * 
	 * @param otherPerson in the person interacting with the current person.
	 * @throws InterruptedException
	 */
	public void interact( Person otherPerson )
	{
		this.inUse = true;
		otherPerson.inUse = true;

		// TODO interaction between people

		this.inUse = false;
		otherPerson.inUse = false;
	}

	void say( String words )
	{
		this.toBeSaid = words;
		this.time = Integer.parseInt( ( new SimpleDateFormat( "HHmmss" ) ).format( new Date() ) );
		System.out.println( this.time );
	}

	@Override
	public void run()
	{

	}

	@Override
	public void interact( BuildingObject object )
	{
		this.inUse = true;
		this.say( "No touching." );
		this.inUse = false;
	}

	@Override
	public void paint( Graphics2D g2d )
	{
		g2d.drawImage( this.face.getScaledInstance( 40, 40, 0 ), ( int ) this.x, ( int ) this.y
				+ Constants.PERSON_BODY_HEIGHT, null );
	}
}
