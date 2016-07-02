package bomberman;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class pantallaConfiguracion extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pantallaConfiguracion frame = new pantallaConfiguracion();
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
	public pantallaConfiguracion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		// Si este checkBox esta marcado se debe ajustar la pantalla del juego a pantalla completa
		JCheckBox chPantallaCompleta = new JCheckBox("Pantalla completa");
		chPantallaCompleta.setBounds(36, 49, 114, 23);
		contentPane.add(chPantallaCompleta);
		
		// Si este checkBox esta desmarcado se debe anular el volumen del juego
		JCheckBox chSonido = new JCheckBox("Sonido");
		chSonido.setBounds(339, 49, 97, 23);
		contentPane.add(chSonido);
		
		// Si este checkBox esta desmarcado se debe anular la musica del juego
		JCheckBox chMusica = new JCheckBox("Musica");
		chMusica.setBounds(339, 75, 97, 23);
		contentPane.add(chMusica);
		
		JLabel lblResolucionPantalla = new JLabel("Resolucion de la pantalla:");
		lblResolucionPantalla.setBounds(36, 79, 133, 14);
		contentPane.add(lblResolucionPantalla);
		
		
		// El comboBox de resolucion de pantalla debe mostrar y permitir seleccion distintas resoluciones para la pantalla del juego
		JComboBox comboResolucionPantalla = new JComboBox();
	
		comboResolucionPantalla.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
			}
		});
		comboResolucionPantalla.setBounds(36, 104, 133, 22);
		contentPane.add(comboResolucionPantalla);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// El boton aceptar deberia guardar los cambios en el XML de configuracion y luego cierra la pantalla
				pantallaConfiguracion.this.dispose();
			}
		});
		btnAceptar.setBounds(78, 210, 91, 23);
		contentPane.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// El boton cancelar deberia cerrar la pantalla de configuracion sin guardar los cambios en el XML de configuracion
				pantallaConfiguracion.this.dispose();
			}
		});
		btnCancelar.setBounds(284, 210, 91, 23);
		contentPane.add(btnCancelar);
	}
}
