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
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;


public class pantallaConfiguracion2 extends JFrame {

	private JPanel contentPane;
	private String resolucionX = "1280";
	private String resolucionY = "800";  //valores por defecto de configuracion
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pantallaConfiguracion2 frame = new pantallaConfiguracion2();
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
	public pantallaConfiguracion2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		// Si este checkBox esta marcado se debe ajustar la pantalla del juego a pantalla completa
		 final JCheckBox chPantallaCompleta = new JCheckBox("Pantalla completa");
		chPantallaCompleta.setBounds(36, 49, 114, 23);
		try {
			File archivoConfiguracion = new File("config.xml");
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(archivoConfiguracion);
			Node configuracion = doc.getFirstChild();
			Element eElement = (Element) configuracion;
			
			if(eElement.getElementsByTagName("fullscreen").item(0).getTextContent().equals("true")) {
				chPantallaCompleta.setSelected(true);
			}
		}
		catch (Exception e3) {
			e3.printStackTrace();
		}
		contentPane.add(chPantallaCompleta);
		
		// Si este checkBox esta desmarcado se debe anular el volumen del juego
		final JCheckBox chSonido = new JCheckBox("Sonido");
		chSonido.setBounds(339, 49, 97, 23);
		try {
			File archivoConfiguracion = new File("config.xml");
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(archivoConfiguracion);
			Node configuracion = doc.getFirstChild();
			Element eElement = (Element) configuracion;
			
			if(eElement.getElementsByTagName("sound").item(0).getTextContent().equals("true")) {
				chSonido.setSelected(true);
			}
		} 
		catch (Exception e3) {
			e3.printStackTrace();
		}
		contentPane.add(chSonido);
		
		// Si este checkBox esta desmarcado se debe anular la musica del juego
		final JCheckBox chMusica = new JCheckBox("Musica");
		chMusica.setBounds(339, 75, 97, 23);
		try {
			File archivoConfiguracion = new File("config.xml");
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(archivoConfiguracion);
			Node configuracion = doc.getFirstChild();
			Element eElement = (Element) configuracion;
			
			if(eElement.getElementsByTagName("music").item(0).getTextContent().equals("true")) {
				chMusica.setSelected(true);
			}
		}
		catch (Exception e3) {
			e3.printStackTrace();
		}
		contentPane.add(chMusica);
		
		JLabel lblResolucionPantalla = new JLabel("Resolucion de la pantalla:");
		lblResolucionPantalla.setBounds(36, 79, 133, 14);
		contentPane.add(lblResolucionPantalla);
		
		
		// El comboBox de resolucion de pantalla debe mostrar y permitir seleccion distintas resoluciones para la pantalla del juego
		final JComboBox comboResolucionPantalla = new JComboBox();
		comboResolucionPantalla.setBounds(36, 104, 133, 22);
		comboResolucionPantalla.addItem("1280 x 800");
		comboResolucionPantalla.addItem("1280 x 768");
		comboResolucionPantalla.addItem("1280 x 720");
		comboResolucionPantalla.addItem("1280 x 600");
		comboResolucionPantalla.addItem("1024 x 768");
		comboResolucionPantalla.addItem("800 x 600");
		comboResolucionPantalla.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				String resolucion = (String) comboResolucionPantalla.getSelectedItem();
				String[] datos = resolucion.split(" x ");
				resolucionX = datos[0];
				resolucionY = datos[1];
			}
		});
		
		contentPane.add(comboResolucionPantalla);
		
	
		// El boton aceptar deberia guardar los cambios en el XML de configuracion y luego cierra la pantalla
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					File archivoConfiguracion = new File("config.xml");
					DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
					Document doc = docBuilder.parse(archivoConfiguracion);
					Node configuracion = doc.getFirstChild();
				
					Element eElement = (Element) configuracion;
					
					if(chPantallaCompleta.isSelected()) {
						eElement.getElementsByTagName("fullscreen").item(0).setTextContent("true");
					}
					else{
						eElement.getElementsByTagName("fullscreen").item(0).setTextContent("false");
					}
				
					if(chSonido.isSelected()) {
						eElement.getElementsByTagName("sound").item(0).setTextContent("true");
					}
					else{
						eElement.getElementsByTagName("sound").item(0).setTextContent("false");
					}
					
					if(chMusica.isSelected()) {
						eElement.getElementsByTagName("music").item(0).setTextContent("true");
					}
					else{
						eElement.getElementsByTagName("music").item(0).setTextContent("false");
					}
					
					eElement.getElementsByTagName("x").item(0).setTextContent(resolucionX);
					eElement.getElementsByTagName("y").item(0).setTextContent(resolucionY);
						
					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					DOMSource source = new DOMSource(doc);
					
					StreamResult consoleResult = new StreamResult("config.xml");
					transformer.transform(source, consoleResult);
				}
				catch (Exception e2) {
					e2.printStackTrace();
				}
				
				pantallaConfiguracion2.this.dispose();
			}
		});
		btnAceptar.setBounds(78, 210, 91, 23);
		contentPane.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				pantallaConfiguracion2.this.dispose();
			}
		});
		btnCancelar.setBounds(284, 210, 91, 23);
		contentPane.add(btnCancelar);
	}
}
