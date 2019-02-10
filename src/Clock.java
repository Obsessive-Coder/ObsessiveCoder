import javax.swing.*;

public class Clock implements Runnable{
	JFrame frame;
	Thread thread = null;
	int hourCount;
	int minuteCount;
	int secondCount;
	String timeDisplay = "";
	JButton button;
	
	Clock(int hourCount, int minuteCount, int secondCount) {
		this.hourCount = hourCount;
		this.minuteCount = minuteCount;
		this.secondCount = secondCount;
		
		frame = new JFrame();
		thread = new Thread(this);
		thread.start();
		button = new JButton();
		button.setBounds(50, 150, 200, 50);
		
		frame.add(button);
		frame.setSize(300, 400);
		frame.setLayout(null);
		frame.setVisible(true);
	}

	@Override
	public void run() {
		try {
			while(true) {
				// If timer is over.
				if(secondCount == 0 && minuteCount == 0 && hourCount == 0) {
					System.out.println("Times Up!");
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
	            System.out.println(timeDisplay);
				printTime();
				Thread.sleep(1);
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void printTime() {
		button.setText(timeDisplay);
	}
}
