package view;

import javax.swing.JFrame;

import railroad.Facade;

public class MainWindow
{
	JFrame _window;

	public MainWindow() 
	{				
		_window = new JFrame("Sistema de Entrocamento Ferroviario");
		
		_window.setSize(1078, 539);
		_window.setLocationRelativeTo(null);
		_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		_window.setContentPane(new RailroadView());
		_window.setVisible(true);
		_window.setResizable(false);
	}
	
	public static void main(String[] args)
	{
		MainWindow w = new MainWindow();
		SpeedWindow s = new SpeedWindow();
	}

}
