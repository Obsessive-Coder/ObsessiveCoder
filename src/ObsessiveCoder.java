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
		this.currentTask.start(6000L);
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
	    Runnable next = this.writeCode;
	    
	    if(breakCount == 4) {
	    	taskMessage = MESSAGE_LONG_BREAK;
	    	breakCount = 0;
	    	longBreakCount += 1;
	    	breakLength = 3000L;
	    }
	    
	    // Reset longBreakCount and make next action sleep
	    if(longBreakCount == 3) {
	    	// The message will change, and the break will be longer to simulate sleep.
	    	taskMessage = MESSAGE_SLEEP;
	    	longBreakCount = 0;
	    	breakLength = 10000L;
	    	next = this.wakeUp;
    	}
	    
	    // Update break counts.
		this.breakCount = breakCount;
		this.longBreakCount = longBreakCount;
		
		// Update and start .
		this.setCurrentTask(taskMessage, next);
	    this.currentTask.start(breakLength);
	};

	public static void main(String[] args) {
		ObsessiveCoder jared = new ObsessiveCoder();
		jared.wakeUp.run();
	}
}