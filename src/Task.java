import java.util.Timer;
import java.util.TimerTask;

public class Task extends TimerTask {
	private String message;
	private Runnable next;
	
	public Task(String message, Runnable next) {
		this.message = message;
		this.next = next;		
	}
	
	public void start(long length) {
		System.out.print("\r");
		System.out.print("Start " + this.message);
		Timer timer = new Timer();
		timer.schedule(this, length);
	}
	
	public void run() {
		this.next.run();
	}
}