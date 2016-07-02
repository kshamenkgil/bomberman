package bomberman;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import database.Conector;
import database.DatosJugador;
import servidor.Protocolo;

public class pantallaPrincipal extends JFrame {

	private JPanel contentPane;
	public JTextField textUsuario;
	public JTextField textPuntuacion;
	private Conector con;

	/**
	 * Create the frame.
	 */
	public pantallaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario: ");
		lblUsuario.setBounds(10, 11, 76, 14);
		contentPane.add(lblUsuario);
		
		textUsuario = new JTextField();
		textUsuario.setEditable(false);
		textUsuario.setBounds(82, 8, 86, 20);
		contentPane.add(textUsuario);
		textUsuario.setColumns(10);
		
		JLabel lblPuntuacion = new JLabel("Puntuacion: ");
		lblPuntuacion.setBounds(240, 11, 94, 14);
		contentPane.add(lblPuntuacion);
		
		// La puntuacion del usuario deberia obtenerse de la base de datos
		textPuntuacion = new JTextField();
		textPuntuacion.setEditable(false);
		textPuntuacion.setBounds(346, 8, 86, 20);
		contentPane.add(textPuntuacion);
		textPuntuacion.setColumns(10);
		
		JButton btnEmpezar = new JButton("Empezar");
		btnEmpezar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						byte[] data = new byte[1];
						data[0] = Protocolo.READY;
						Bomberman.getInstancia().getCliente().sendData(data);
						Bomberman.getInstancia().run();
					}
				}).start();
				
				
				/*Engine.getInstancia().setJuego(new GameScreen());
				Engine.getInstancia().getJuego().setVisible(true);*/
				pantallaPrincipal.this.dispose();  
			}
		});
		btnEmpezar.setBounds(287, 97, 123, 23);
		contentPane.add(btnEmpezar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				byte data[] = new byte[1];
				data[0] = Protocolo.DESCONEXION_USER;								
				Bomberman.getInstancia().getCliente().sendData(data);
								
				System.exit(0);
			}
		});
		btnSalir.setBounds(287, 199, 123, 23);
		contentPane.add(btnSalir);
		
		
		// El comboBox de usuarios en linea debe traer a todos los usuarios de la base de datos que tengan el estado de conexion en "true"
		JComboBox comboUsuariosEnLinea = new JComboBox();
		comboUsuariosEnLinea.setToolTipText("");
		comboUsuariosEnLinea.setBounds(10, 97, 158, 22);
		contentPane.add(comboUsuariosEnLinea);
		
		JLabel lblUsuariosEnLinea = new JLabel("Usuarios en linea:");
		lblUsuariosEnLinea.setBounds(10, 74, 158, 14);
		contentPane.add(lblUsuariosEnLinea);
		
		JButton btnRanking = new JButton("Ranking");
		btnRanking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				pantallaRanking pRanking = new pantallaRanking();
				pRanking.setVisible(true);				
			}
		});
		btnRanking.setBounds(287, 165, 123, 23);
		contentPane.add(btnRanking);
		
		JButton btnConfiguracion = new JButton("Configuracion");
		btnConfiguracion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				pantallaConfiguracion2 pConfiguracion = new pantallaConfiguracion2();
				pConfiguracion.setVisible(true);
			}
		});
		btnConfiguracion.setBounds(287, 131, 123, 23);
		contentPane.add(btnConfiguracion);
	}
}
