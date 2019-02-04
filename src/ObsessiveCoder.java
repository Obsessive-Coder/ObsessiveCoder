import java.util.HashMap;

public class ObsessiveCoder {
	// Constants.
	private static final String MESSAGE_WRITE_CODE = "Writing code ...";
	private static final String MESSAGE_SHORT_BREAK = "Taking a break ...";
	private static final String MESSAGE_LONG_BREAK = "Taking a LONG break ...";
	private static final String MESSAGE_WAKE_UP = "Waking up ...";
	private static final String MESSAGE_SLEEP = "Sleeping ...";
	
	private int breakCount;
	private int longBreakCount;
	private HashMap<String, Integer> stats;
	
	private Task currentTask;
	private void setCurrentTask(String message, Runnable next) {
		this.currentTask = new Task(message, next);
	}
	
	public ObsessiveCoder() {
		this.breakCount = 0;
		this.longBreakCount = 0;
		
		HashMap<String, Integer> stats = new HashMap<String, Integer>();
		stats.put("sprintTotal", 0);
		this.stats = stats;
	}
	
	private void wakeUp() {
		this.setCurrentTask(MESSAGE_WAKE_UP, this.writeCode);
		this.currentTask.start(1000L);
	};
	
	Runnable writeCode = () -> {	    
	    this.setCurrentTask(MESSAGE_WRITE_CODE, this.takeBreak);
	    this.currentTask.start(6000L);
	};
	
	Runnable takeBreak = () -> {
	    String taskMessage = MESSAGE_SHORT_BREAK;	    
	    Integer breakCount = this.breakCount + 1;
	    Integer longBreakCount = this.longBreakCount;
	    long breakLength = 1000L;
	    
	    if(breakCount == 4) {
	    	int sprintTotal = this.stats.get("sprintTotal");
	    	this.stats.put("sprintTotal", sprintTotal + 1);
	    	taskMessage = MESSAGE_LONG_BREAK;
	    	breakCount = 0;
	    	longBreakCount += 1;
	    	breakLength = 3000L;
	    	if(longBreakCount == 3) {
	    		taskMessage = MESSAGE_SLEEP;
	    		longBreakCount = 0;
	    		breakLength = 10000L;
	    	}
	    }
	    
		this.breakCount = breakCount;
		this.longBreakCount = longBreakCount;
		
		this.setCurrentTask(taskMessage, this.writeCode);
	    this.currentTask.start(breakLength);
	};

	public static void main(String[] args) {
		ObsessiveCoder jared = new ObsessiveCoder();
		jared.wakeUp();
	}
}