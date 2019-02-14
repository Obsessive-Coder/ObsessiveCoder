import java.util.concurrent.TimeUnit;
import javax.swing.*;

public class TaskTimer implements Runnable{
	JFrame frame;
	Thread thread = null;
	long hourCount;
	long minuteCount;
	long secondCount;
	String timeDisplay = "";
	JButton button;
	
	TaskTimer(long length) {
		this.hourCount = TimeUnit.MILLISECONDS.toHours(length);
		long totalMinuteCount = TimeUnit.MILLISECONDS.toMinutes(length);
		this.minuteCount = totalMinuteCount - (this.hourCount * 60);
		this.secondCount = TimeUnit.MILLISECONDS.toSeconds(length) - TimeUnit.MINUTES.toSeconds(totalMinuteCount);
		
		thread = new Thread(this);
		thread.start();
	}

	@Override	public void run() {
		try {
			while(true) {
				// If timer is over.
				if(secondCount == 0 && minuteCount == 0 && hourCount == 0) {
					System.out.println();
					System.out.println("\nTimes Up!");
					break;
				}
				
				// If seconds and minutes are at 0, but there's still at least 1 hour left.
				if(secondCount == 0 && minuteCount == 0 && hourCount >= 1) {
					hourCount -= 1;
					minuteCount = 59;
					secondCount = 60;					
				}
				
				// If seconds is at 0, but there's still at least 1 minute left.
				if(secondCount == 0 && minuteCount >= 1) {
					minuteCount -= 1;
					secondCount = 60;
				}
				
				secondCount -= 1;
				
	            timeDisplay = String.valueOf(String.format("%02d", hourCount)) + ":" + String.valueOf(String.format("%02d", minuteCount)) + ":" + String.valueOf(String.format("%02d", secondCount));
	           
				printTime();
				Thread.sleep(1);
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void printTime() {
		System.out.print("\r");
		System.out.print(timeDisplay);
	}
}
