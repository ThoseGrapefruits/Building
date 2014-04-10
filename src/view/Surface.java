package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import main.Building;
import people.Person;

class Surface extends JPanel
{
	/**
	 * Generated serial ID to make Eclipse happy.
	 */
	private static final long serialVersionUID = -7441446934043006335L;

	Building building = new Building();

	@Override
	public void paintComponent( Graphics g )
	{
		super.paintComponent( g );
		Graphics2D g2d = ( Graphics2D ) g;
		this.building.paint( g2d );
	}
}
