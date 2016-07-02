package bomberman;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.TextArea;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import database.Conector;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

	

public class pantallaRanking extends JFrame {

	private JPanel contentPane;
	private ArrayList<String>listado= new ArrayList<String>();
	private Conector con;
	/** 
	 * Create the frame.
	 */
	public pantallaRanking() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		TextArea textArea = new TextArea();
		textArea.setBounds(48, 67, 320, 121);
		con =  new Conector();
		con.connect();
		listado = con.ranking(); 
		 for (int i = 4; i > -1;i--){
			 textArea.setText(listado.get(i)+"\n"+textArea.getText());   
		  }
		 contentPane.add(textArea);

		JLabel lblRanking = new JLabel("Ranking");
		lblRanking.setBounds(180, 34, 56, 16);
		contentPane.add(lblRanking);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnVolver.setBounds(157, 194, 97, 25);
		contentPane.add(btnVolver);
	}
}
