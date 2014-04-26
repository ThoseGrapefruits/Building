package people;

import interactive.Door;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import main.Building;
import base.BuildingObject;
import base.Interactive;
import base.Visible;
import boundaries.Floor;
import boundaries.Wall;
import constants.Constants;

public class Person extends BuildingObject implements Interactive, Visible, Runnable
{
	public Person( int x, int y )
	{
		super( x, y, Constants.PERSON_WIDTH, Constants.PERSON_HEIGHT );

		try
		{
			this.head = ImageIO.read( new File( "resources/images/person/default/_head.png" ) );
			this.body = ImageIO.read( new File( "resources/images/person/default/_body.png" ) );
		}
		catch ( IOException e )
		{
			System.out.println( "Could not read character graphics." );
		}
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
	BufferedImage head;
	/**
	 * Image used for the body of the given person.
	 */
	BufferedImage body;

	public BufferedImage getHead()
	{
		return head;
	}

	String toBeSaid = "";

	int time = 0;

	protected String name = "Anonymous" + ( 1 + ( int ) Math.random() * 100 );

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
		boolean direction = true;
		while ( true )
		{
			if ( this.velocityX == 0 )
			{
				if ( direction )
				{
					direction = false;
					this.velocityX = -1;
				}
				else
				{
					direction = true;
					this.velocityX = 1;
				}
			}
			try
			{
				Thread.sleep( 1000 );
			}
			catch ( InterruptedException e )
			{
				e.printStackTrace();
			}
		}
	}

	@Override
	public void interact( BuildingObject object )
	{
		this.inUse = true;
		this.say( "No touching." );
		this.inUse = false;
	}

	public boolean canMoveX( Building b )
	{
		boolean canMoveX = true;
		Rectangle nextLocation = new Rectangle( ( int ) ( this.x + this.velocityX ),
				( int ) ( this.y + this.velocityY ), this.width, this.height );

		for ( Wall wall : b.walls )
		{
			if ( nextLocation.intersects( wall.getBounds() ) )
			{
				canMoveX = false;
			}
		}
		for ( Door door : b.doors )
		{
			if ( door.open == 0 && nextLocation.intersects( door.getBounds() ) )
			{
				canMoveX = false;
			}
		}
		return canMoveX;
	}

	public boolean canMoveY( Building b )
	{
		boolean canMoveY = true;
		Rectangle nextLocation = new Rectangle( ( int ) ( this.x + this.velocityX ),
				( int ) ( this.y + this.velocityY ), this.width, this.height );
		for ( Floor floor : b.floors )
		{
			if ( nextLocation.intersects( floor.getBounds() ) )
			{
				canMoveY = false;
			}
		}
		return canMoveY;
	}

	public void move( Building b )
	{
		if ( this.canMoveX( b ) )
		{
			this.x += this.velocityX;
		}
		else
		{
			this.velocityX = 0;
		}

		if ( this.canMoveY( b ) )
		{
			this.y += this.velocityY;
		}
		else
		{
			this.velocityY = 0;
		}
	}

	@Override
	public void paint( Graphics2D g2d )
	{
		if ( this.velocityX == 0 )
		{ // Standing still
			// Head
			this.animationStep[ 0 ] = 0;
			g2d.drawImage(
					this.head.getSubimage( 90, 0, 10, 10 ).getScaledInstance(
							Constants.PERSON_WIDTH, Constants.PERSON_HEAD_HEIGHT, 0 ),
					( int ) this.x, ( int ) this.y, null );

			// Body
			this.animationStep[ 1 ] = 0;
			g2d.drawImage(
					this.body.getSubimage( 60, 0, 10, 15 ).getScaledInstance(
							Constants.PERSON_WIDTH, Constants.PERSON_BODY_HEIGHT, 0 ),
					( int ) this.x, ( int ) this.y + Constants.PERSON_HEAD_HEIGHT, null );
		}
		else if ( this.velocityX > 0 )
		{ // Moving to the right
			// Head
			g2d.drawImage( this.head.getSubimage( 10 * this.animationStep[ 0 ], 10, 10, 10 )
					.getScaledInstance( Constants.PERSON_WIDTH, Constants.PERSON_HEAD_HEIGHT, 0 ),
					( int ) this.x, ( int ) this.y, null );
			this.animationStep[ 0 ] = ( this.animationStep[ 0 ] + 1 ) % 9;

			// Body
			g2d.drawImage(
					this.body.getSubimage(
							10 * ( this.animationStep[ 1 ] - ( this.animationStep[ 1 ] % 6 ) ) / 6,
							15, 10, 15 ).getScaledInstance( Constants.PERSON_WIDTH,
							Constants.PERSON_BODY_HEIGHT, 0 ), ( int ) this.x, ( int ) this.y
							+ Constants.PERSON_HEAD_HEIGHT, null );
			this.animationStep[ 1 ] = ( this.animationStep[ 1 ] + 1 ) % 36;
		}
		else
		{ // Moving to the left
			// Head
			g2d.drawImage( this.head.getSubimage( 10 * this.animationStep[ 0 ], 0, 10, 10 )
					.getScaledInstance( Constants.PERSON_WIDTH, Constants.PERSON_HEAD_HEIGHT, 0 ),
					( int ) this.x, ( int ) this.y, null );
			this.animationStep[ 0 ] = ( this.animationStep[ 0 ] + 1 ) % 9;

			// Body
			g2d.drawImage(
					this.body.getSubimage(
							10 * ( this.animationStep[ 1 ] - ( this.animationStep[ 1 ] % 6 ) ) / 6,
							0, 10, 15 ).getScaledInstance( Constants.PERSON_WIDTH,
							Constants.PERSON_BODY_HEIGHT, 0 ), ( int ) this.x, ( int ) this.y
							+ Constants.PERSON_HEAD_HEIGHT, null );
			this.animationStep[ 1 ] = ( this.animationStep[ 1 ] + 1 ) % 36;
		}
	}
}
