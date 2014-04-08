package boundaries;

import base.Visible;

public class Boundary extends Visible
{
	private boolean passable = false;

	public boolean isPassable()
	{
		return passable;
	}

	private boolean interactable = false;

	public boolean isInteractable()
	{
		return interactable;
	}
}
