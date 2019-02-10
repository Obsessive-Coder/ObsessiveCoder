import java.util.HashMap;
import java.util.Map;

/**
 * A Programmer class represents a user of the application.
 * <p>It holds the name of the user, their statistics, and currents needs.</p>
 */
public class Programmer {
	private final String EAT_ACTION_TEXT = "eating";
	private final String SLEEP_ACTION_TEXT = "sleeping";
	private final String CODE_ACTION_TEXT = "coding";
	
	private final long EAT_ACTION_LENGTH = 1000L;
	private final long SLEEP_ACTION_LENGTH = 9000L;
	private final long CODE_ACTION_LENGTH = 3000L;
	
	private final String[] BASIC_ACTIONS = new String[] {"eat", "sleep", "code", "show needs", "show stats"};
	private final String[] BASIC_NEEDS = new String[] {"satiation", "sleep", "mental health"};
	private final String[] BASIC_STATISTICS = new String[] {"hours coded"};
	
	// The programmer's name.
	private String name;
	public String getName() {
		return this.name;
	}
	
	// The programmer's available actions.
	private String[] actions;
	public String[] getActions() {
		return this.actions;
	}
	
	// The programmer's needs.
	private Map<String, Integer> needs;
	public Map<String, Integer> getNeeds(){
		return this.needs;
	}
	public void setNeeds(Map<String, Integer> needs) {
		this.needs = needs;
	}
	
	// The programmer's statistics.
	private Map<String, Integer> statistics;
	public Map<String, Integer> getStatistics() {
		return this.statistics;
	}
	public void setStatistics(Map<String, Integer> statistics) {
		this.statistics = statistics;
	}
	
	/**
	 * getInitialStatistics()
	 * <p>private Map<String, Integer> getInitialStatistics()</p>
	 * <p>Gets a Map of initial statistic keys and values.</p>
	 * @return Map<String, Integer>
	*/
	private Map<String, Integer> getInitialStatistics() {
		Map<String, Integer> stats = new HashMap<String, Integer>();
		for(String stat : BASIC_STATISTICS) {
			stats.put(stat, 0);
		}
		
		return stats;
	}
	
	/**
	 * getInitialNeeds()
	 * <p>private Map<String, Integer> getInitialNeeds()</p>
	 * <p>Gets a Map of initial needs keys and values.</p>
	 * @return Map<String, Integer>
	*/
	private Map<String, Integer> getInitialNeeds() {
		Map<String, Integer> needs = new HashMap<String, Integer>();
		for(String need : BASIC_NEEDS) {
			needs.put(need, 0);
		}
		
		return needs;
	}
	
	/**
	 * eatFood()
	 * <p>public CoderTask eatFood()</p>
	 * <p>Creates the task for taking a short break.</p>
	 * @return The CoderTask Object to be completed
	*/
	public CoderTask eatFood() {
		System.out.println(this.EAT_ACTION_TEXT + " ...");
		
		return new CoderTask(this.EAT_ACTION_TEXT, this.EAT_ACTION_LENGTH, this);
	}
	
	/**
	 * gotoSleep()
	 * <p>public CoderTask gotoSleep()</p>
	 * <p>Creates the task for going to sleep.</p>
	 * @return The CoderTask to be completed
	*/
	public CoderTask gotoSleep() {
		System.out.println(this.SLEEP_ACTION_TEXT + " ...");
		
		return new CoderTask(this.SLEEP_ACTION_TEXT, this.SLEEP_ACTION_LENGTH, this);
	}
	
	/**
	 * writeCode()
	 * <p>public CoderTask writeCode()</p>
	 * <p>Creates the task for writing code.</p>
	 * @return The CoderTask to be completed
	*/
	public CoderTask writeCode() {		
		System.out.println(this.CODE_ACTION_TEXT + " ...");
		
		return new CoderTask(this.CODE_ACTION_TEXT, this.CODE_ACTION_LENGTH, this);
	}
	
	/**
	 * Programmer(String name)
	 * <p>The user of the application.</p>
	 * <p>Creates a new user with the argument passed in.</p>
	 * @return The Programmer Object for the new user
	*/
	public Programmer(String name) {
		this.name = name;
		this.actions = BASIC_ACTIONS;
		this.statistics = getInitialStatistics();
		this.needs = getInitialNeeds();
	}
}
