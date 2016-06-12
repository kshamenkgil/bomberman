package bomberman;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GameScreen extends JFrame {

	private JPanel contentPane;
	private DisplayMode oldMode;
	private GraphicsDevice gd; 
	/**
	 * Create the frame.
	 */
	public GameScreen() {
		setResizable(false);
		setBackground(Color.BLACK);
		setTitle("Bomberman");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//pantalla centrada respecto a la resolucion actual
		gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		setBounds(gd.getDisplayMode().getWidth()/2-Configuracion.getInstancia().getScreenX()/2, gd.getDisplayMode().getHeight()/2 - Configuracion.getInstancia().getScreenY()/2, Configuracion.getInstancia().getScreenX(), Configuracion.getInstancia().getScreenY());
		
		oldMode = gd.getDisplayMode();
  		DisplayMode newMode = new DisplayMode(Configuracion.getInstancia().getScreenX(), Configuracion.getInstancia().getScreenY(),oldMode.getBitDepth(),oldMode.getRefreshRate());
		
		if(Configuracion.getInstancia().isFullscreen()){
			this.setExtendedState(JFrame.MAXIMIZED_BOTH);		
			gd.setFullScreenWindow(this);
			gd.setDisplayMode(newMode);
			this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		}
			//gd.setDisplayMode(new DisplayMode(Configuracion.getInstancia().getScreenX(), Configuracion.getInstancia().getScreenY(), gd.getDisplayMode().getBitDepth(), gd.getDisplayMode().getRefreshRate()));
		
		contentPane = new JPanel() {
			@Override
			protected synchronized void paintComponent(Graphics g) {				
				super.paintComponent(g);
								
				Graphics2D g2d = (Graphics2D)g.create();				
				Engine.getInstancia().dibujar(g2d,this);
				g2d.dispose();				
			}
		};
		contentPane.setBackground(Color.BLACK);				
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setSize(new Dimension(Configuracion.getInstancia().getScreenX(), Configuracion.getInstancia().getScreenY()));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		gd.setDisplayMode(oldMode);
		super.dispose();		
	}
	
}
