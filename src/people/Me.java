package people;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import base.Interactive;
import base.Visible;
import constants.Constants;

public class Me extends Person implements Interactive, Visible, Runnable
{
	public Me( int x, int y )
	{
		super( x, y );
		this.name = "Logan";
		try
		{
			this.head = ImageIO.read( new File( "resources/images/person/me/_head.png" ) );
			this.body = ImageIO.read( new File( "resources/images/person/me/_body.png" ) );
		}
		catch ( IOException e )
		{
			System.out.println( "Could not read character graphics." );
		}
	}

	/**
	 * Animated drawing of user-controlled person.
	 */
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
