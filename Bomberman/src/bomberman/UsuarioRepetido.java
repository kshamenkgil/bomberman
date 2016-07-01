package bomberman;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class UsuarioRepetido extends JFrame {

	private JPanel contentPane;


	public UsuarioRepetido() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 238, 154);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuarioRegistrado = new JLabel("Usuario Repetido");
		lblUsuarioRegistrado.setFont(new Font("Arial", Font.PLAIN, 14));
		lblUsuarioRegistrado.setBounds(49, 21, 126, 23);
		contentPane.add(lblUsuarioRegistrado);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(66, 55, 89, 23);
		contentPane.add(btnAceptar);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
	}

}
