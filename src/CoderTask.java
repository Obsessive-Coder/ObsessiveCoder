import java.util.TimerTask;

/**
 * A custom timer task.
 */
public class CoderTask extends TimerTask {
	private Programmer coder;
	
	private String name;
	public String getName() {
		return this.name;
	}
	
	private long length;
	/**
	 * <b><i>getLength</i></b>
	 * <p>public long getLength()
	 * <p>Gets the length of the time in milliseconds before the next prompt.
	 * @return The long value that represents the time in milliseconds for this task
	 * 
	*/
	public long getLength() {
		return this.length;
	}
	
	/**
	 * A custom timer task.
	 * 
	 * @param name - The name of the task {@code String}.
	 * @param length - The length of the task in milliseconds {@code long}.
	 * @param coder - The Programmer Object this task belongs to {@code Programmer}.
	 * @return The CoderTask for this task.
	 */
	public CoderTask(String name, long length, Programmer coder) {
		this.name = name;
		this.length = length;
		this.coder = coder;
	}

	@Override
	public void run() {
		System.out.println("Finished " + this.name + ".");
		Controller.promptAction(coder);
	}
}
