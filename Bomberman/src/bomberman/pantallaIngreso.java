package bomberman;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import servidor.ThreadServer;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.awt.event.ActionEvent;
import bomberman.PantallaRegistro;

public class pantallaIngreso extends JFrame {

	private JPanel contentPane;
	public static JTextField textUsuario;
	public static JPasswordField textPassword;
	
	private String userName;
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public pantallaIngreso() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//conectar con el server		
		try {
			Bomberman.getInstancia().conectar();	
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se pudo conectar con el servidor");
		}
		
		//Bomberman.getInstancia().getCliente().sendData(data);
		
		JLabel lblUsuario = new JLabel("Usuario: ");
		lblUsuario.setBounds(60, 62, 106, 14);
		contentPane.add(lblUsuario);
		
		textUsuario = new JTextField();
		textUsuario.setBounds(199, 62, 147, 20);
		contentPane.add(textUsuario);
		textUsuario.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setBounds(60, 107, 106, 14);
		contentPane.add(lblPassword);
		
		textPassword = new JPasswordField();
		textPassword.setBounds(199, 107, 147, 20);
		contentPane.add(textPassword);
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String s = "{'header' : 'iniciar_sesion',";							
				try {
					s+="'user':"+textUsuario.getText()+",'password':"+ PantallaRegistro.hash(new String(textPassword.getPassword()))+"}";
				} catch (NoSuchAlgorithmException e1) {
					e1.printStackTrace();
				}
				Bomberman.getInstancia().getCliente().sendData(s.getBytes(Charset.forName("UTF-8")));
				userName = textUsuario.getText();
				while(!Bomberman.getInstancia().getCliente().isLogged() && !Bomberman.getInstancia().getCliente().isErrorLog()){
					try {
						Thread.sleep(1);
					} catch (Exception e) {
						
					}				
				}
				
				if(Bomberman.getInstancia().getCliente().isLogged()){
					Bomberman.getInstancia().getCliente().setUserName(textUsuario.getText());
					pantallaPrincipal principal = new pantallaPrincipal();
					principal.setVisible(true);										
					
					principal.textUsuario.setText(userName);
					principal.textPuntuacion.setText(""+Bomberman.getInstancia().getCliente().getPuntosJugador()); // El valor de la puntuacion deberia obtenerse de la base de datos
					
					pantallaIngreso.this.dispose();
				}else{
					JOptionPane.showMessageDialog(null, "Usuario o password incorrectos");
				}
				
				/*Conector con =  new Conector();
				con.connect();
				if ( con.confirmarLogin(textUsuario.getText(),new String(textPassword.getPassword())) )
				{	
					con.connect();
					con.modificarEstado(textUsuario.getText(),1);
					
					// Ademas el boton ingresar deberia actualizar el estado de conexion del usuario en la base de datos
				}else{
					JOptionPane.showMessageDialog(null, "Usuario o password incorrectos");
					
					textUsuario.setText("");
					textPassword.setText("");
				}
				
				pantallaIngreso.this.dispose();*/
			}	
		});
		btnIngresar.setBounds(140, 193, 100, 23);
		contentPane.add(btnIngresar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// Cerrar la aplicacion
				System.exit(0);
			}
		});
		btnSalir.setBounds(42, 193, 86, 23);
		contentPane.add(btnSalir);
	
	JButton btnRegistro = new JButton("Registrarse");
	btnRegistro.setBounds(251, 193, 130, 23);
	contentPane.add(btnRegistro);
	btnRegistro.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			
			PantallaRegistro registro = new PantallaRegistro();
			registro.setVisible(true);
			}
		});
	}

}
