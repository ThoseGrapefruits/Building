package view;

import main.Building;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class Surface extends JPanel implements ActionListener {
	/**
	 * Generated serial ID to make Eclipse happy.
	 */
	private static final long serialVersionUID = -7441446934043006335L;
	Building building;

	public Surface( Building building ) {
		this.building = building;
	}

	@Override
	public void paintComponent( Graphics g ) {
		super.paintComponent( g );
		this.building.paint( ( Graphics2D ) g );
	}

	@Override
	public void actionPerformed( ActionEvent e ) {
		this.building.actionPerformed( e );
		this.repaint();
	}
}
