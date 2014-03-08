import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class View extends JFrame
{
	/**
	 * Generated serial ID to make Eclipse happy.
	 */
	private static final long serialVersionUID = -8836966816243980019L;

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public View()
	{
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setBounds( 100, 100, 800, 600 );
		setTitle( "Building Simulation" );
		contentPane = new JPanel();
		contentPane.setBorder( new EmptyBorder( 5, 5, 5, 5 ) );
		contentPane.setLayout( new BorderLayout( 0, 0 ) );
		setContentPane( contentPane );

		Surface panel = new Surface();
		contentPane.add( panel, BorderLayout.CENTER );
	}
}
