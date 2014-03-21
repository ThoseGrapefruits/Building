package main;

public class Test
{
	public static void main( String[] args )
	{
		Test test = new Test();
	}

	Test()
	{
	}

	boolean building()
	{
		Building myBuilding = new Building( 700, 2 );

		return true;
	}

	// Interactables

	boolean person()
	{
		return true;
	}

	boolean elevator()
	{
		return true;
	}

	boolean door()
	{
		return true;
	}

	boolean lightSwitch()
	{
		return true;
	}
}
