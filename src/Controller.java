import javax.swing.JOptionPane;
import java.util.Timer;

public class Controller {
	
	private static String getCoderName() {
		String coderName = "";
		
		do {
			coderName = JOptionPane.showInputDialog("What is your name?");
			
			if(coderName == null) {
				quit();
			}
		} while (coderName.length() == 0);
		
		return coderName;
	}
	
	public static void promptAction(Programmer coder) {
		String coderName = coder.getName();
		String[] coderActions = coder.getActions();
			
		String action = (String) JOptionPane.showInputDialog(null, "What would you like to do?", "Welcome " + coderName, JOptionPane.QUESTION_MESSAGE, null, coderActions, coderActions[2]);
		CoderTask coderTask;
		
		if (action == null) {
			quit();
			return;
		}
		
		switch(action) {
		case "Eat":
			coderTask = (CoderTask) coder.eatFood();
			break;
		case "Sleep":
			coderTask = (CoderTask) coder.gotoSleep();
			break;
		case "Code":
			coderTask = (CoderTask) coder.writeCode();
			break;
		default:
			quit();
			return;
		}
		
		Timer timer = new Timer();
		
		timer.schedule(coderTask, coderTask.getLength());
	}
	
	private static void quit() {
		System.out.println("Quitting ...");
		System.exit(0);
	}

	public static void main(String[] args) {
		String coderName = getCoderName();
		Programmer coder = new Programmer(coderName);
		
		promptAction(coder);
	}
	
	
}
