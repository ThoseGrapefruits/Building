package people;

import interactive.Door;
import interactive.ElevatorButton;
import interactive.LightSwitch;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
	public Person( Building building, int x, int y )
	{
		super( building, x, y, Constants.PERSON_WIDTH, Constants.PERSON_HEIGHT );

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

	/**
	 * People that the given person has already talked to (so that conversation
	 * isn't repetitive).
	 */
	ArrayList < Person > interactedWith = new ArrayList < Person >();

	public BuildingObject interactiveObjectWithinReach;

	public double velocityX = 0, velocityY = 0;

	public boolean wantsToInteract = false;

	public boolean isFloorBelow()
	{
		Rectangle bounds = new Rectangle( ( int ) this.x, ( int ) this.y + this.height, this.width,
				0 );
		for ( Floor floor : this.building.floors )
		{
			if ( bounds.intersects( floor.getBounds() ) )
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Finds the closest object to a person within its BoundingBox.
	 * 
	 * @param sourcePerson is the person trying to interact.
	 * @return the interactive object closest to the person
	 */
	public BuildingObject getClosestInteractiveObject()
	{
		Rectangle origBounds = this.getBounds();
		Rectangle bounds = new Rectangle( origBounds.x - 5, origBounds.y - 5,
				origBounds.width + 10, origBounds.height + 10 );
		for ( Person person : this.building.people )
		{
			if ( person.getBounds().intersects( bounds ) )
			{
				return person;
			}
		}
		for ( LightSwitch lightSwitch : this.building.lightSwitches )
		{
			if ( lightSwitch.getBounds().intersects( bounds ) )
			{
				return lightSwitch;
			}
		}

		for ( ElevatorButton elevatorButton : this.building.elevatorButtons )
		{
			if ( elevatorButton.getBounds().intersects( bounds ) )
			{
				return elevatorButton;
			}
		}

		for ( Door door : this.building.doors )
		{
			if ( door.getBounds().intersects( bounds ) )
			{
				return door;
			}
		}
		return null;
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

	String toBeSaid;

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
		this.animationStep[ 2 ] = 255;
		otherPerson.animationStep[ 2 ] = 255;

		if ( this.interactedWith.contains( otherPerson ) )
		{ // Already interacted with
			otherPerson.toBeSaid = "Hi.";
			this.toBeSaid = "Hi.";
		}
		else
		{ // Seen for the first time
			try
			{
				this.toBeSaid = "Hi, my name is " + this.name;
				Thread.sleep( 1500 );
				otherPerson.toBeSaid = "Nice to meet you " + this.name;
				Thread.sleep( 1500 );
				otherPerson.animationStep[ 2 ] = 255;
				otherPerson.toBeSaid = "My name is " + otherPerson.name;
			}
			catch ( InterruptedException e )
			{
				System.out.println( "Person was too impatient to wait." );
			}
		}

		this.inUse = false;
		otherPerson.inUse = false;
	}

	@Override
	public void run()
	{
		boolean LEFT = false;
		boolean RIGHT = true;
		boolean direction = true;
		while ( true )
		{
			if ( this.velocityX == 0 )
			{
				if ( direction == RIGHT )
				{
					direction = LEFT;
					this.velocityX = -1;
				}
				else
				{
					direction = RIGHT;
					this.velocityX = 1;
				}
			}
			try
			{
				Thread.sleep( Constants.AI_CYCLE );
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
		this.toBeSaid = "This shouldn't even happen, because that would imply some object is interacting with a person. Only people should interact with other people.";
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
			if ( this.velocityY < Constants.TERMINAL_VELOCITY )
			{
				this.velocityY += 0.1;
			}
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
		if ( this.toBeSaid != null && this.toBeSaid != "" && this.animationStep[ 2 ] != 0 )
		{
			g2d.setColor( new Color( 100, 100, 100, this.animationStep[ 2 ] ) );
			g2d.fillRect( ( int ) this.x, ( int ) this.y, this.width, this.height );
			this.animationStep[ 2 ]--;
		}
	}
}
