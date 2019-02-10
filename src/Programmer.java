import java.util.TimerTask;

public class Programmer {
	private String name;
	public String getName() {
		return this.name;
	}
	
	private String[] actions;
	public String[] getActions() {
		return this.actions;
	}
	
	public TimerTask eatFood() {
		System.out.println("Eating ...");
		
		return new CoderTask("Eating", 1000L, this);
	}
	
	public TimerTask gotoSleep() {
		System.out.println("Sleeping ...");
		
		return new CoderTask("Sleeping", 9000L, this);
	}
	
	public TimerTask writeCode() {		
		System.out.println("Writing code ...");
		
		return new CoderTask("Coding", 3000L, this);
	}
	
	public Programmer(String name) {
		this.name = name;
		this.actions = new String[] {"Eat", "Sleep", "Code"};
	}
}
