import java.util.HashMap;

public class ObsessiveCoder {
	// Constants.
	private static final String MESSAGE_WRITE_CODE = "Writing code ...";
	private static final String MESSAGE_SHORT_BREAK = "Taking a break ...";
	private static final String MESSAGE_LONG_BREAK = "Taking a LONG break ...";
	private static final String MESSAGE_WAKE_UP = "Waking up ...";
	private static final String MESSAGE_SLEEP = "Sleeping ...";
	public static final long LENGTH_WAKE_UP = 60L * 1000L * 60L; // 60 minutes.
	public static final long LENGTH_WRITE_CODE = 60L * 1000L * 60L; // 60 minutes.
	public static final long LENGTH_SHORT_BREAK = 60L * 1000L * 10L; // 10 minutes.
	public static final long LENGTH_LONG_BREAK = 60L * 1000L * 30L; // 30 minutes.
	public static final long LENGTH_SLEEP = 60L * 1000L *  60L * 6L; // 6 hours;
	
	private int breakCount;
	private int longBreakCount;
	private boolean isJustTesting = true;
	
	private Task currentTask;
	private void setCurrentTask(String message, Runnable next) {
		this.currentTask = new Task(message, next);
	}
	
	public ObsessiveCoder() {
		this.breakCount = 0;
		this.longBreakCount = 0;
	}
	
	Runnable wakeUp = () -> {
		System.out.print("Good Morning!");
		this.setCurrentTask(MESSAGE_WAKE_UP, this.writeCode);
		
		long taskLength = this.getTaskLength(LENGTH_WAKE_UP);
		this.currentTask.start(taskLength);
	};
	
	Runnable writeCode = () -> {	    
	    this.setCurrentTask(MESSAGE_WRITE_CODE, this.takeBreak);
	    
	    long taskLength = this.getTaskLength(LENGTH_WRITE_CODE);
	    this.currentTask.start(taskLength);
	};
	
	Runnable takeBreak = () -> {
	    String taskMessage = MESSAGE_SHORT_BREAK;	    
	    Integer breakCount = this.breakCount + 1;
	    Integer longBreakCount = this.longBreakCount;
	    long taskLength = LENGTH_SHORT_BREAK;
	    Runnable next = this.writeCode;
	    
	    // Reset breakCount and set length to long break.
	    if(breakCount == 4) {
	    	taskMessage = MESSAGE_LONG_BREAK;
	    	breakCount = 0;
	    	longBreakCount += 1;
	    	taskLength = LENGTH_LONG_BREAK;
	    }
	    
	    // Reset longBreakCount and make next action sleep
	    if(longBreakCount == 3) {
	    	// The message will change, and the break will be longer to simulate sleep.
	    	taskMessage = MESSAGE_SLEEP;
	    	longBreakCount = 0;
	    	taskLength = LENGTH_SLEEP;
	    	next = this.wakeUp;
    	}
	    
	    // Update break counts.
		this.breakCount = breakCount;
		this.longBreakCount = longBreakCount;
		
		// Update and start task.
		this.setCurrentTask(taskMessage, next);
		taskLength = this.getTaskLength(taskLength);
	    this.currentTask.start(taskLength);
	};
	
	private long getTaskLength(long length) {
		long taskLength = length;
		return (this.isJustTesting == true) ? (taskLength / 1000L) : taskLength;
	}

	public static void main(String[] args) {
		ObsessiveCoder jared = new ObsessiveCoder();
		jared.wakeUp.run();
	}
}