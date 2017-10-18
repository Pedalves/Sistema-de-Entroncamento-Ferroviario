package view;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import railroad.Facade;

public class SpeedWindow {
	JFrame _window;
	
	public SpeedWindow() 
	{				
		_window = new JFrame("Adicionador de trem");
		
		_window.setSize(300, 140);
		_window.setLocationRelativeTo(null);
		_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		_setupView();
		
		_window.setVisible(true);
		_window.setResizable(false);
	}

	
	private void _setupView()
	{
		JPanel panel = new JPanel();
		
		/************************ 60 **************************/
		
		JButton left60Button = new JButton("Add 60 esquerda");
		left60Button.setAlignmentX(Component.CENTER_ALIGNMENT);
		left60Button.addActionListener(e -> {
			Facade.getInstance().AddLeft(60);
		});
		panel.add(left60Button);
		
		JButton right60Button = new JButton("Add 60 direita");
		right60Button.setAlignmentX(Component.CENTER_ALIGNMENT);
		right60Button.addActionListener(e -> {
			Facade.getInstance().AddRight(60);
		});
		panel.add(right60Button);
		
		/************************ 80 **************************/
		
		JButton left80Button = new JButton("Add 80 esquerda");
		left80Button.setAlignmentX(Component.CENTER_ALIGNMENT);
		left80Button.addActionListener(e -> {
			Facade.getInstance().AddLeft(80);
		});
		panel.add(left80Button);
		
		JButton right80Button = new JButton("Add 80 direita");
		right80Button.setAlignmentX(Component.CENTER_ALIGNMENT);
		right80Button.addActionListener(e -> {
			Facade.getInstance().AddRight(80);
		});
		panel.add(right80Button);
		
		/************************ 100 **************************/
				
		JButton left100Button = new JButton("Add 100 esquerda");
		left100Button.setAlignmentX(Component.CENTER_ALIGNMENT);
		left100Button.addActionListener(e -> {
			Facade.getInstance().AddLeft(100);
		});
		panel.add(left100Button);
		
		JButton right100Button = new JButton("Add 100 direita");
		right100Button.setAlignmentX(Component.CENTER_ALIGNMENT);
		right100Button.addActionListener(e -> {
			Facade.getInstance().AddRight(100);
		});
		panel.add(right100Button);
		
		_window.setContentPane(panel);
	}
}
