package view;

import java.awt.BorderLayout;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import constants.Constants;

public class View extends JFrame
{
	/**
	 * Generated serial ID to make Eclipse happy.
	 */
	private static final long serialVersionUID = -8836966816243980019L;

	/**
	 * Window on which everything is drawn.
	 */
	private JPanel contentPane;

	private Surface surface;

	public BufferStrategy strategy;

	/**
	 * Create the frame.
	 */
	public View( Surface newSurface )
	{
		this.surface = newSurface;
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setBounds( 100, 100, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT );
		setResizable( false );
		setTitle( "Building Simulator 2014" );
		setBackground( Constants.SKY_COLOR );
		surface.setBackground( Constants.SKY_COLOR );
		contentPane = new JPanel();
		contentPane.setBorder( new EmptyBorder( 5, 5, 5, 5 ) );
		contentPane.setLayout( new BorderLayout( 0, 0 ) );
		contentPane.setBackground( Constants.SKY_COLOR );
		setContentPane( contentPane );

		contentPane.add( surface, BorderLayout.CENTER );
	}
}
