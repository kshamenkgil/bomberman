package bomberman;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class pantallaRanking extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pantallaRanking frame = new pantallaRanking();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public pantallaRanking() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		// El comboBox del ranking debe traer a todos los usuarios de la base de datos ordenados de manera descendente por puntuacion
		JComboBox comboRanking = new JComboBox();
		comboRanking.setBounds(132, 101, 166, 22);
		contentPane.add(comboRanking);
		
		JLabel lblRanking = new JLabel("Ranking de usuarios:");
		lblRanking.setBounds(132, 76, 166, 14);
		contentPane.add(lblRanking);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				pantallaRanking.this.dispose();
			}
		});
		btnVolver.setBounds(168, 201, 91, 23);
		contentPane.add(btnVolver);
	}
}
