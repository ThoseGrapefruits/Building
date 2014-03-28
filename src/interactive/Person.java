package interactive;

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

		otherPerson.say( "Hello." );
		Thread.sleep( 500 );
		this.say( "Hi." );
		Thread.sleep( 500 );
		otherPerson.say( "My name is " + otherPerson.name );
		Thread.sleep( 1500 );
		this.say( "Nice to meet you, " + otherPerson.name );
		Thread.sleep( 1500 );
		this.say( "My name is " + this.name );
		Thread.sleep( 1500 );
		otherPerson.say( "Nice to meet you as well, " + this.name + "." );
		Thread.sleep( 1000 );
		this.say( "Gotta go. Bye!" );
		Thread.sleep( 500 );
		otherPerson.say( "Bye!" );
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
		if ( !( this.toBeSaid.isEmpty() ) )
		{
			if ( this.time <= Integer.parseInt( ( new SimpleDateFormat( "HHmmss" ) )
					.format( new Date() ) ) + 2 )
			{
				// TODO display dialogue box above person
			}
			else
			{
				this.toBeSaid = "";
				this.time = 0;
			}
		}
	}
}
