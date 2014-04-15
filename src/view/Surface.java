package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;

import javax.swing.JPanel;

import main.Building;

public class Surface extends JPanel implements ActionListener
{
	public Surface( Building building )
	{
		this.building = building;
	}

	/**
	 * Generated serial ID to make Eclipse happy.
	 */
	private static final long serialVersionUID = -7441446934043006335L;

	Building building;

	@Override
	public void paintComponent( Graphics g )
	{
		super.paintComponent( g );
		Graphics2D g2d = ( Graphics2D ) g;
		this.building.paint( g2d );
	}

	@Override
	public void actionPerformed( ActionEvent e )
	{
		this.building.actionPerformed( e );
		this.repaint();
	}
}
