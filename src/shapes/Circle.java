package shapes;

import main.Building;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Circle extends Shape {

	public Circle( Building building, double x, double y, int width, int height ) {
		super( building, x, y, width, height );
	}

	@Override
	public void paint( Graphics2D g2d ) {
		Ellipse2D.Double circle = new Ellipse2D.Double( x, y, width, height );
		g2d.draw( circle );
	}

}
