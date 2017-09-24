package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class RailroadView extends JPanel
{
	private BufferedImage bgImage;
	
	private final int _leftInclinationX = 180;
	private final int _leftBridgeX = 400;
	
	private final int _rightInclinationX = 1180;
	private final int _rightBridgeX = 990;
	
	public RailroadView()
	{
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println(e.getX());
				System.out.println(e.getY());
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		try {
			this.bgImage = ImageIO.read(new File("resources\\Trem.jpg"));
		} catch (IOException e) {
			System.out.println("ERRO ao carregar imagem");
		}
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
		
		Graphics2D g2d = (Graphics2D) g;
		
		/***************** Left Traffic Signal ***********************/
		
		g2d.setPaint(Color.BLACK);
		g2d.fillRect(_leftInclinationX, 100, 35, 70);
		
		g2d.setPaint(Color.GREEN);
		g2d.fill(new Ellipse2D.Double(_leftInclinationX, 120, 34, 34));
		
		/*************************************************************/
		
		/***************** Right Traffic Signal **********************/
		
		g2d.setPaint(Color.BLACK);
		g2d.fillRect(_rightInclinationX, 100, 35, 70);
		
		g2d.setPaint(Color.GREEN);
		g2d.fill(new Ellipse2D.Double(_rightInclinationX, 120, 34, 34));
		
		/*************************************************************/
	}
}
