import javax.swing.JOptionPane;
import java.util.Timer;

/**
 * The main application controller.
 * <p>The controller class creates the Programmer object and manages user decisions.</p>
*/
public class Controller {
	private static final String PROMPT_NAME_MESSAGE = "What is your name?";
	private static final String PROMPT_ACTION_TITLE = "Welcome ";
	private static final String PROMPT_ACTION_MESSAGE = "What would you like to do?";
	private static final String WARNING_NAME_TITLE = "WARNING";
	private static final String WARNING_NAME_MESSAGE = "The name field is required.";
	private static final String QUIT_ACTION_TEXT = "Quitting ...";
	
	/**
	 * promptCoderName()
	 * <p>private static String promptCoderName()</p>
	 * <p>Prompts the user to input a name.</p>
	 * <p>This function will continue to prompt until the user "cancels" or inputs a valid string of characters.</p>
	 * @return The String name of the user
	*/
	private static String promptCoderName() {
		String coderName = "";
		int timesPrompted = 0;
		do {			
			if(timesPrompted >= 3) {
				timesPrompted = 0;
				JOptionPane.showMessageDialog(null, WARNING_NAME_MESSAGE, WARNING_NAME_TITLE, JOptionPane.ERROR_MESSAGE);
			}
			
			coderName = JOptionPane.showInputDialog(PROMPT_NAME_MESSAGE);
			timesPrompted += 1;
			
			if(coderName == null) {
				quit();
			}
		} while (coderName.length() == 0);
		
		return coderName;
	}
	
	/**
	 * promptAction()
	 * <p>public static void promptAction(Programmer coder)</p>
	 * <p>Prompts the user to select an action to perform.</p>
	 * @param coder - The Programmer Object that is performing the action
	*/
	public static void promptAction(Programmer coder) {
		String coderName = coder.getName();
		String[] coderActions = coder.getActions();
			
		String action = (String) JOptionPane.showInputDialog(null, PROMPT_ACTION_MESSAGE, PROMPT_ACTION_TITLE + coderName, JOptionPane.QUESTION_MESSAGE, null, coderActions, coderActions[2]);
		CoderTask coderTask;
		
		if (action == null) {
			quit();
			return;
		}
		
		switch(action) {
		case "eat":
			coderTask = (CoderTask) coder.eatFood();
			break;
		case "sleep":
			coderTask = (CoderTask) coder.gotoSleep();
			break;
		case "code":
			coderTask = (CoderTask) coder.writeCode();
			break;
		default:
			quit();
			return;
		}
		
		Timer timer = new Timer();
		
		timer.schedule(coderTask, coderTask.getLength());
	}
	
	/**
	 * quit()
	 * <p>private static void quit()</p>
	 * <p>Exits the application.</p>
	*/
	private static void quit() {
		System.out.println(QUIT_ACTION_TEXT);
		System.exit(0);
	}

	/**
	 * main(String[] args)
	 * <p>public static void main(String[] args)</p>
	 * <p>The entry point for the application.</p>
	 * <p>Prompts the user for a name, creates a new Programmer Object, then begins prompting the user for actions.</p>
	*/
	public static void main(String[] args) {
		String coderName = promptCoderName();
		Programmer coder = new Programmer(coderName);
		
		promptAction(coder);
	}
	
	
}
