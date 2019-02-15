import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.*;

public class MainWindow {
	private final String MAIN_TITLE = "Obsessive Coder Disorder";
	private final String WELCOME_MESSAGE = "Welcome ";
	
	private JFrame frame;
	private JLabel welcomeText;
	private JSplitPane mainPane;
	private JSplitPane infoPane;
	
	private Programmer coder;
	private String username;
	
	private TaskTimer timer;
	
	MainWindow(Programmer coder) {
		this.coder = coder;
		this.username = this.coder.getName();
		
		this.buildFrame();
	}
	
	private void buildFrame() {
		if(this.frame != null) {
			this.frame.setVisible(false);
			this.frame.getContentPane().removeAll();
		}
		
		this.frame = new JFrame(MAIN_TITLE);
		this.welcomeText = new JLabel(WELCOME_MESSAGE + this.username, JLabel.CENTER);
		
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setSize(new Dimension(400, 400));
		this.frame.setLocationRelativeTo(null);
		
		this.frame.add(this.welcomeText, BorderLayout.NORTH);
		
		JPanel needsPanel = this.buildInfoPanel("Needs", this.coder.getNeeds());
		JPanel statsPanel = this.buildInfoPanel("Statistics", this.coder.getStatistics());
		
		this.infoPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, needsPanel, statsPanel);
		this.infoPane.setDividerSize(0);
		this.infoPane.setDividerLocation(200);
		
		JPanel actionPanel = this.buildActionPanel();
		
		this.mainPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, infoPane, actionPanel);
		this.mainPane.setDividerSize(0);
		this.mainPane.setDividerLocation(250);
		
		this.frame.getContentPane().add(this.mainPane);
		
		this.frame.setVisible(true);
		this.frame.repaint();
	}
	
	private JPanel buildInfoPanel(String type, Map<String, Integer> info) {		
		JLabel infoTitle = new JLabel(type, JLabel.CENTER);
		infoTitle.setMinimumSize(new Dimension(200, 25));
		infoTitle.setPreferredSize(new Dimension(200, 25));
		infoTitle.setMaximumSize(new Dimension(200, 25));
		
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		infoPanel.setMinimumSize(new Dimension(200, 150));
		
		infoPanel.add(infoTitle);
		
		for(String key : info.keySet()) {
			infoPanel.add(new JLabel(key + ": " + info.get(key)));
		}
		
		return infoPanel;
	}
	
	private JPanel buildActionPanel() {
		JPanel actionPanel = new JPanel();
		actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.X_AXIS));
		actionPanel.setMinimumSize(new Dimension(200, 150));
		
		String[] actions = this.coder.getActions();
		for(int i = 0; i < actions.length; i++) {
			String action = actions[i];
			JButton actionButton = new JButton(action);
			MainWindow self = this;
			
			actionButton.addActionListener(new ActionListener()
			{
			  public void actionPerformed(ActionEvent e)
			  {
				  CoderTask coderTask = null;
				  switch(action) {
				  case "Eat":
					  coderTask = coder.eatFood();
					  break;
				  case "Code":
					  coderTask = coder.writeCode();
					  break;
				  case "Sleep":
					  coderTask = coder.gotoSleep();
					  break;
				  default:
					  System.out.println("Unknown command.");
					  break;
				  }
				  
				  if(coderTask != null) {
					  timer = new TaskTimer(coderTask.getLength(), coderTask.getName() + " ...", self.frame);
				  }
				  buildFrame();
			  }
			});
			
			actionPanel.add(actionButton);	
		}

		return actionPanel;
	}
}
