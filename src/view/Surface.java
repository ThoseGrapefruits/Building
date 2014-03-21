package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

class Surface extends JPanel
{
	/**
	 * Generated serial ID to make Eclipse happy.
	 */
	private static final long serialVersionUID = -7441446934043006335L;

	private void drawBuilding( Graphics2D g2d )
	{

	}

	private void drawElevators( Graphics2D g2d )
	{

	}

	private void drawLights( Graphics2D g2d )
	{

	}

	private void drawDoors( Graphics2D g2d )
	{

	}

	private void drawPeople( Graphics2D g2d ) throws IOException
	{
		g2d.drawString( "Java 2D", 50, 50 );
		File taiFile = new File( "resources/tai.jpg" );
		BufferedImage tai = ImageIO.read( taiFile );
		g2d.drawImage( tai, 100, 100, 143, 187, null );
	}

	@Override
	public void paintComponent( Graphics g )
	{
		super.paintComponent( g );
		try
		{
			Graphics2D g2d = ( Graphics2D ) g;
			drawPeople( g2d );
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
	}

	public static void main( String[] args )
	{
		SwingUtilities.invokeLater( new Runnable()
		{
			@Override
			public void run()
			{
				View sk = new View();
				sk.setVisible( true );
			}
		} );
	}
}
