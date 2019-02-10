/**
 * A Programmer class represents a user of the application.
 * <p>It holds the name of the user, their statistics, and currents needs.</p>
 */
public class Programmer {
	private String name;
	/**
	 * getName()
	 * <p>public String getName()</p>
	 * <p>Gets the name of the user.</p>
	 * @return The stored String name of the user
	*/
	public String getName() {
		return this.name;
	}
	
	private String[] actions;
	/**
	 * getActions()
	 * <p>public String getActions()</p>
	 * <p>Gets the array of actions that the user can perform.</p>
	 * @return The String[] array of actions that the user can perform
	*/
	public String[] getActions() {
		return this.actions;
	}
	
	/**
	 * eatFood()
	 * <p>public CoderTask eatFood()</p>
	 * <p>Creates the task for taking a short break.</p>
	 * @return The CoderTask Object to be completed
	*/
	public CoderTask eatFood() {
		System.out.println("Eating ...");
		
		return new CoderTask("Eating", 1000L, this);
	}
	
	/**
	 * gotoSleep()
	 * <p>public CoderTask gotoSleep()</p>
	 * <p>Creates the task for going to sleep.</p>
	 * @return The CoderTask to be completed
	*/
	public CoderTask gotoSleep() {
		System.out.println("Sleeping ...");
		
		return new CoderTask("Sleeping", 9000L, this);
	}
	
	/**
	 * writeCode()
	 * <p>public CoderTask writeCode()</p>
	 * <p>Creates the task for writing code.</p>
	 * @return The CoderTask to be completed
	*/
	public CoderTask writeCode() {		
		System.out.println("Writing code ...");
		
		return new CoderTask("Coding", 3000L, this);
	}
	
	/**
	 * Programmer(String name)
	 * <p>The user of the application.</p>
	 * <p>Creates a new user with the argument passed in.</p>
	 * @return The Programmer Object for the new user
	*/
	public Programmer(String name) {
		this.name = name;
		this.actions = new String[] {"Eat", "Sleep", "Code"};
	}
}
