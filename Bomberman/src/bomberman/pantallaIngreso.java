package bomberman;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import database.Conector;
import database.DatosJugador;

public class pantallaIngreso extends JFrame {

	private JPanel contentPane;
	public static JTextField textUsuario;
	public static JPasswordField textPassword;

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
		
		JLabel lblUsuario = new JLabel("Usuario: ");
		lblUsuario.setBounds(75, 62, 46, 14);
		contentPane.add(lblUsuario);
		
		textUsuario = new JTextField();
		textUsuario.setBounds(159, 59, 86, 20);
		contentPane.add(textUsuario);
		textUsuario.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setBounds(75, 107, 63, 14);
		contentPane.add(lblPassword);
		
		textPassword = new JPasswordField();
		textPassword.setBounds(159, 104, 86, 20);
		contentPane.add(textPassword);
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 Conector con =  new Conector();
					con.connect();
				if ( con.confirmarLogin(textUsuario.getText(),new String(textPassword.getPassword())) )
				{	
					con.connect();
					con.modificarEstado(textUsuario.getText(),1);
					pantallaPrincipal principal = new pantallaPrincipal();
					principal.setVisible(true);
					
					principal.textUsuario.setText( textUsuario.getText());
					principal.textPuntuacion.setText(""+con.puntosJugador(textUsuario.getText())); // El valor de la puntuacion deberia obtenerse de la base de datos 

					// Ademas el boton ingresar deberia actualizar el estado de conexion del usuario en la base de datos
				}else{
					JOptionPane.showMessageDialog(null, "Usuario o password incorrectos");
					
					textUsuario.setText("");
					textPassword.setText("");
				}
				
				pantallaIngreso.this.dispose();
			}	
		});
		btnIngresar.setBounds(159, 157, 86, 23);
		contentPane.add(btnIngresar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// Cerrar la aplicacion
				System.exit(0);
			}
		});
		btnSalir.setBounds(60, 157, 86, 23);
		contentPane.add(btnSalir);
	
	JButton btnRegistro = new JButton("Registrarse");
	btnRegistro.setBounds(260, 157, 130, 23);
	contentPane.add(btnRegistro);
	btnRegistro.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			
			PantallaRegistro registro = new PantallaRegistro();
			registro.setVisible(true);
			}
		});
	}

}
