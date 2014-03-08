import java.awt.EventQueue;

public class Building
{
	/**
	 * Launch the application.
	 */
	public static void main( String[] args )
	{
		EventQueue.invokeLater( new Runnable()
		{
			public void run()
			{
				try
				{
					View frame = new View();
					frame.setVisible( true );
				}
				catch ( Exception e )
				{
					e.printStackTrace();
				}
			}
		} );
	}
}
