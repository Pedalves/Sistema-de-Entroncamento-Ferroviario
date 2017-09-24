package view;

import javax.swing.JFrame;

public class MainWindow
{
	JFrame _window;

	public MainWindow() 
	{				
		_window = new JFrame("Sistema de Entrocamento Ferroviario");
		
		_window.setSize(1575, 787);
		_window.setLocationRelativeTo(null);
		_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		_window.setContentPane(new RailroadView());
		_window.setVisible(true);
	}
	
	public static void main(String[] args)
	{
		MainWindow w = new MainWindow();
	}

}
