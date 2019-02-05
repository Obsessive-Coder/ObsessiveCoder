import java.util.Timer;
import java.util.TimerTask;

public class Task extends TimerTask {
	private String message;
	private Runnable next;
	
	public Task(String message, Runnable next) {
		this.message = message;
		this.next = next;
	}
	
	private void printMessage() {
		String message = this.message;
		int startIndex = message.length();
		for(int i = startIndex; i < 23; i++) {
			message += " ";
		}
		
		System.out.print("\r");
		System.out.print(message);
		System.out.print("\r");
	}
	
	public void start(long length) {
		this.printMessage();
		Timer timer = new Timer();
		timer.schedule(this, length);
	}
	
	public void run() {
		this.next.run();
	}
}