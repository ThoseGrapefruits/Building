package objects;

import java.awt.Graphics2D;

import interactive.Interactive;
import base.BuildingObject;
import base.Visible;

public class Decoration extends BuildingObject implements Interactive, Visible, Runnable
{
	protected Decoration( int x, int y, int width, int height, String filePath )
	{
		super( x, y, width, height );
	}

	@Override
	public void run()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void paint( Graphics2D g2d )
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void interact( BuildingObject interacter )
	{
		// TODO Auto-generated method stub

	}
}
