import java.awt.*;
import java.util.Map;

import javax.swing.*;

public class MainWindow {
	private final String MAIN_TITLE = "Obsessive Coder Disorder";
	private final String WELCOME_MESSAGE = "Welcome ";
	
	private JFrame frame;
	private JLabel welcomeText;
	private JSplitPane infoPane;
	
	private Programmer coder;
	private String username;
	
	MainWindow(Programmer coder) {
		this.coder = coder;
		this.username = this.coder.getName();
		
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
		
		this.frame.add(this.infoPane);
		
		this.frame.setVisible(true);
	}
	
	private JPanel buildInfoPanel(String type, Map<String, Integer> info) {		
		JLabel infoTitle = new JLabel(type, JLabel.CENTER);
		infoTitle.setMinimumSize(new Dimension(200, 25));
		infoTitle.setPreferredSize(new Dimension(200, 25));
		infoTitle.setMaximumSize(new Dimension(200, 25));
		
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		infoPanel.setMinimumSize(new Dimension(200, 200));
		
		infoPanel.add(infoTitle);
		
		for(String key : info.keySet()) {
			infoPanel.add(new JLabel(key + ": " + info.get(key)));
		}
		
		return infoPanel;
	}
}
