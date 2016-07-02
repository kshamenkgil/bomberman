package servidor;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class ServerScreen extends JFrame {

	private JPanel contentPane;
	private Server server = null;
	public JTextArea consola;
	private JTextField textField;
	private JButton btnNewButton_1;
	/**
	 * Create the frame.
	 */
	public ServerScreen() {
		final ServerScreen sc = this;
		setResizable(false);
		setTitle("Bomberman server");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 433, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JButton btnNewButton = new JButton("Iniciar servidor");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(server == null){
					server = new Server(sc);
					new Thread(server, "Servidor").start();
					btnNewButton.setText("Parar servidor");
					while(!server.isRunning()){
						try {
							Thread.sleep(1);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					consola.append("Cantidad de jugadores actual: "+ server.getCantPlayers() +"\n");
					consola.append("Ingrese /help para mas informacion\n");					
				}else{
					server.dispose();
					server = null;
					btnNewButton.setText("Iniciar servidor");
					//consola.append("Ya hay una instancia del servidor corriendo!\n");
				}					
			}
		});
		btnNewButton.setBounds(32, 247, 182, 40);
		contentPane.add(btnNewButton);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//if(server != null)
					//cerrar server
				System.exit(0);
			}
		});
		btnSalir.setBounds(238, 247, 172, 40);
		contentPane.add(btnSalir);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 12, 378, 186);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();		
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		consola = textArea;
		
		textField = new JTextField();
		textField.setBounds(32, 210, 277, 25);
		contentPane.add(textField);
		textField.setColumns(10);
						
		btnNewButton_1 = new JButton("Enviar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String cmd[] = textField.getText().split(" ");
				
				switch(cmd[0]){
					case "/help":
						consola.append("Para setear numero de jugadores: /setjugadores cantidad\n");
						consola.append("Para ver los usuarios conectados: /online cantidad\n");
						break;
					case "/online":
						if(Mundo.getInstance().getConnections() != null)
							for (ThreadServer ts : Mundo.getInstance().getConnections())
								consola.append("Jugador x con id" + ts.getJugador().getId()+ "\n");
						break;
					case "/setjugadores":						
						try{
							server.setCantPlayers(Integer.parseInt(cmd[1]));
							consola.append("Cantidad de jugadores ahora es :" + server.getCantPlayers() + "\n");
						}catch(Exception e){
							consola.append("Comando incorrecto pruebe /help\n");
						}												
						break;	
				}
				textField.setText("");
			}
		});
		
		textField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					btnNewButton_1.doClick();
				}
			}
		});
		
		btnNewButton_1.setBounds(321, 210, 89, 25);
		contentPane.add(btnNewButton_1);
	}
}
