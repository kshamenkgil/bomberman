package bomberman;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.JLabel;

import database.Conector;
import database.DatosJugador;

public class PantallaRegistro extends JFrame {
	private JPanel contentPane;
	private JTextField usuario;
	private JPasswordField pass;
	private JPasswordField confirmpass;
	private DatosJugador jugador;
	private Conector con;

	
	public PantallaRegistro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 333, 189);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setBounds(216, 115, 89, 23);
		contentPane.add(btnRegistrarse);
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String password = new String(pass.getPassword());
				String confirm = new String(pass.getPassword());
				String user = new String(usuario.getText());
				if(password.equals(confirm)){
					con = new Conector();
					con.connect();
					while(con.usuarioRepetido(user))
						new UsuarioRepetido().setVisible(true);
					jugador = new DatosJugador(user,password);
					con.connect();
					con.grabarJugador(jugador);
					new Registrado().setVisible(true);
					dispose();
					}
			}
		});
		
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setBounds(10, 115, 89, 23);
		contentPane.add(btnSalir);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		
		usuario = new JTextField();
		usuario.setBounds(163, 11, 117, 20);
		contentPane.add(usuario);
		usuario.setColumns(10);
		
		pass = new JPasswordField();
		pass.setBounds(163, 42, 117, 20);
		contentPane.add(pass);
		
		confirmpass = new JPasswordField();
		confirmpass.setBounds(163, 73, 117, 20);
		contentPane.add(confirmpass);
		
		JLabel usuarionuevo = new JLabel("Usuario nuevo:");
		usuarionuevo.setBounds(29, 14, 95, 14);
		contentPane.add(usuarionuevo);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setBounds(29, 45, 95, 14);
		contentPane.add(lblContrasea);
		
		JLabel lblConfirmarContrasea = new JLabel("Confirmar contrase\u00F1a");
		lblConfirmarContrasea.setBounds(29, 76, 117, 14);
		contentPane.add(lblConfirmarContrasea);
	}
}
