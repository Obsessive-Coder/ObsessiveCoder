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
		
		this.frame.getContentPane().add(this.welcomeText, BorderLayout.NORTH);
		
		JPanel needsPanel = new JPanel();
		JPanel statsPanel = new JPanel();
		needsPanel.setLayout(new BoxLayout(needsPanel, BoxLayout.PAGE_AXIS));
		statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.PAGE_AXIS));
		needsPanel.setMinimumSize(new Dimension(200, 200));
		statsPanel.setMinimumSize(new Dimension(200, 200));
		
		JLabel needsText = new JLabel("Needs", JLabel.CENTER);
		needsText.setMinimumSize(new Dimension(200, 25));
		needsText.setPreferredSize(new Dimension(200, 25));
		needsText.setMaximumSize(new Dimension(200, 25));
		needsPanel.add(needsText, JLabel.CENTER);
		Map<String, Integer> needs = this.coder.getNeeds();
		for(String need : needs.keySet()) {
			needsPanel.add(new JLabel(need + ": " + needs.get(need)));
		}
		
		JLabel statsText = new JLabel("Stats", JLabel.CENTER);
		statsText.setMinimumSize(new Dimension(200, 25));
		statsText.setPreferredSize(new Dimension(200, 25));
		statsText.setMaximumSize(new Dimension(200, 25));
		statsPanel.add(statsText, JLabel.CENTER);
		Map<String, Integer> statistics = this.coder.getStatistics();
		for(String stat : statistics.keySet()) {
			statsPanel.add(new JLabel(stat + ": " + statistics.get(stat)));
		}
		
		this.infoPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, needsPanel, statsPanel);
		this.infoPane.setDividerSize(0);
		this.infoPane.setDividerLocation(200);
		
		this.frame.add(this.infoPane);
		
		this.frame.setVisible(true);
	}
}
