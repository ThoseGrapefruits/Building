package people;

import base.Interactive;
import base.Visible;
import constants.Constants;
import main.Building;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Tai extends Person implements Interactive, Visible, Runnable {
	public Tai( Building building, double x, double y ) {
		super( building, x, y );

		try {
			head = ImageIO.read( getClass().getResource(
					"/resources/images/person/tai/head.png" ) );
			body = ImageIO.read( getClass().getResource(
					"/resources/images/person/standard/_body.png" ) );
		}
		catch ( IOException e ) {
			e.printStackTrace();
		}
	}

	@Override
	public void paint( Graphics2D g2d ) {
		// Head
		g2d.drawImage( head, null, ( int ) x, ( int ) y );
		g2d.setColor( Color.BLACK );
		if ( velocityX == 0 ) { // Standing still
			// Body
			animationStep[ 1 ] = 0;
			g2d.drawImage(
					body.getSubimage( 0, 30, 10, 15 ).getScaledInstance(
							Constants.PERSON_WIDTH, Constants.PERSON_BODY_HEIGHT, 0 ),
					( int ) x, ( int ) y + Constants.PERSON_HEAD_HEIGHT, null );
		}
		else if ( velocityX > 0 ) { // Moving to the right
			// Body
			g2d.drawImage(
					body.getSubimage(
							10 * ( animationStep[ 1 ] - ( animationStep[ 1 ] % 6 ) ) / 6,
							15, 10, 15 ).getScaledInstance( Constants.PERSON_WIDTH,
							Constants.PERSON_BODY_HEIGHT, 0 ), ( int ) x, ( int ) y
							+ Constants.PERSON_HEAD_HEIGHT, null );
			animationStep[ 1 ] = ( animationStep[ 1 ] + 1 ) % 36;
		}
		else { // Moving to the left
			// Body
			g2d.drawImage(
					body.getSubimage(
							10 * ( animationStep[ 1 ] - ( animationStep[ 1 ] % 6 ) ) / 6,
							0, 10, 15 ).getScaledInstance( Constants.PERSON_WIDTH,
							Constants.PERSON_BODY_HEIGHT, 0 ), ( int ) x, ( int ) y
							+ Constants.PERSON_HEAD_HEIGHT, null );
			animationStep[ 1 ] = ( animationStep[ 1 ] + 1 ) % 36;
		}

		say( g2d );
		drawBounds( g2d );
	}
}
