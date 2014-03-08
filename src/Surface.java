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

	private void doDrawing( Graphics g ) throws IOException
	{
		Graphics2D g2d = ( Graphics2D ) g;
		g2d.drawString( "Java 2D", 50, 50 );
		File taiFile = new File( "tai.jpg" );
		BufferedImage tai = ImageIO.read( taiFile );
		g2d.drawImage( tai, 0, 0, 143, 187, null );
	}

	@Override
	public void paintComponent( Graphics g )
	{
		super.paintComponent( g );
		try
		{
			doDrawing( g );
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
