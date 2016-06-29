package servidor;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class ServerScreen extends JFrame {

	private JPanel contentPane;
	private Server server = null;
	public JTextArea consola;
	/**
	 * Create the frame.
	 */
	public ServerScreen() {
		final ServerScreen sc = this;
		setResizable(false);
		setTitle("Bomberman server");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Iniciar servidor");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				server = new Server(sc);
				new Thread(server, "Servidor").start();
			}
		});
		btnNewButton.setBounds(42, 220, 140, 40);
		contentPane.add(btnNewButton);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(server != null)
					//cerrar server
				System.exit(0);
			}
		});
		btnSalir.setBounds(252, 220, 140, 40);
		contentPane.add(btnSalir);
		
		JTextArea textArea = new JTextArea();		
		textArea.setEditable(false);
		textArea.setBounds(32, 12, 378, 186);
		contentPane.add(textArea);
		consola = textArea;
	}
}
