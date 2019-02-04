import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class ObsessiveCoder {
	// Constants.
	private static final String MESSAGE_WRITE_CODE = "Writing code ...";
	private static final String MESSAGE_SHORT_BREAK = "Taking a break ...";
	private static final String MESSAGE_LONG_BREAK = "Taking a LONG break ...";
	private static final String MESSAGE_SLEEP = "Sleeping ...";
	
	private String currentAction;
	private HashMap<String, Integer> stats;
	
	public ObsessiveCoder() {
		HashMap<String, Integer> stats = new HashMap<String, Integer>();
		stats.put("breakCount", 0);
		stats.put("longBreakCount", 0);
		this.stats = stats;
	}
	
	public void writeCode() {
		this.currentAction = MESSAGE_WRITE_CODE;
		System.out.println(this.currentAction);
		
		ObsessiveCoder self = this;
		
		TimerTask task = new TimerTask() {
	        public void run() {
	            System.out.println("Finished: " + self.currentAction);
	            System.out.println("");
	            self.takeBreak();
	        }
	    };
	    
		Timer pomodoro = new Timer();
		pomodoro.schedule(task, 3000L);
	}
	
	public void takeBreak() {
		this.currentAction = MESSAGE_SHORT_BREAK;
		Integer breakCount = this.stats.get("breakCount") + 1;
		Integer longBreakCount = this.stats.get("longBreakCount");
		long breakLength = 3000L;
		if(breakCount == 4) {
			this.currentAction = MESSAGE_LONG_BREAK;
			breakCount = 0;
			longBreakCount += 1;
			breakLength = 5000L;
			if(longBreakCount == 3) {
				this.currentAction = MESSAGE_SLEEP;
				longBreakCount = 0;
				breakLength = 10000L;
			}
		}
		
		this.stats.put("breakCount", breakCount);
		this.stats.put("longBreakCount", longBreakCount);
		
		System.out.println(this.currentAction);
		System.out.println(this.stats);
		
		ObsessiveCoder self = this;
		
		TimerTask task = new TimerTask() {
	        public void run() {
	            System.out.println("Finished: " + self.currentAction);
	            System.out.println("");
	            self.writeCode();
	        }
	    };
	    
		Timer pomodoro = new Timer();
		pomodoro.schedule(task, breakLength);
	}
	
	public static void main(String[] args) {
		ObsessiveCoder jared = new ObsessiveCoder();
		jared.writeCode();
	}
}