import java.util.TimerTask;

public class CoderTask extends TimerTask {
	private Programmer coder;
	private String name;
	
	private long length;
	public long getLength() {
		return this.length;
	}
	
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
