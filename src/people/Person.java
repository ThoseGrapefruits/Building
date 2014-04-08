package people;

import interactive.Interactive;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Person extends Interactive
{
	String toBeSaid = "";

	int time = 0;

	private String name = "Anonymous" + ( 1 + ( int ) Math.random() * 100 );

	/**
	 * Method for person-to-person interaction
	 * 
	 * @param otherPerson
	 * @throws InterruptedException
	 */
	void interact( Person otherPerson ) throws InterruptedException
	{
		this.inUse = true;
		otherPerson.inUse = true;

		// TODO interaction between people

		this.inUse = false;
		otherPerson.inUse = false;
	}

	void say( String words )
	{
		this.toBeSaid = words;
		this.time = Integer.parseInt( ( new SimpleDateFormat( "HHmmss" ) ).format( new Date() ) );
		System.out.println( this.time );
	}

	@Override
	public void run()
	{

	}
}
