package packVista1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import packControlador1.Controlador1;
import packModelo1.Jugador1;
import javax.swing.JTextArea;
import java.awt.Color;

public class vistaExamen1 extends JFrame {

	private JPanel contentPane;
	private JTextField ALTURA;
	private JTextField DORSAL;
	private JTextField NOMBRE;
	private JTextField ID;
	private Controlador1 controlador;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vistaExamen1 frame = new vistaExamen1();
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

	// Método para limpiar los campos del formulario
	private void limpiarCampos() {
		ID.setText("");
		NOMBRE.setText("");
		DORSAL.setText("");
		ALTURA.setText("");
	}
	
	JTextArea consola = new JTextArea();

	public vistaExamen1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 559);
		setLocation(600, 300); // para situar el frame en la mitad de la pantalla para verla mejor.
		controlador = new Controlador1();
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel variable_ID = new JLabel("         ID");
		variable_ID.setBounds(10, 33, 137, 54);
		variable_ID.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		contentPane.add(variable_ID);

		JLabel variable_NOMBRE = new JLabel("   NOMBRE");
		variable_NOMBRE.setBounds(10, 114, 137, 54);
		variable_NOMBRE.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		contentPane.add(variable_NOMBRE);

		JLabel variable_DORSAL = new JLabel("     DORSAL");
		variable_DORSAL.setBounds(10, 203, 137, 54);
		variable_DORSAL.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		contentPane.add(variable_DORSAL);

		JLabel variable_ALTURA = new JLabel("   ALTURA");
		variable_ALTURA.setBounds(10, 292, 137, 54);
		variable_ALTURA.setBackground(new Color(255, 255, 0));
		variable_ALTURA.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		contentPane.add(variable_ALTURA);

		ALTURA = new JTextField();
		ALTURA.setBounds(171, 292, 186, 54);
		ALTURA.setBackground(new Color(255, 255, 255));
		ALTURA.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		contentPane.add(ALTURA);
		ALTURA.setColumns(10);

		DORSAL = new JTextField();
		DORSAL.setBounds(171, 203, 186, 54);
		DORSAL.setBackground(new Color(255, 255, 255));
		DORSAL.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		DORSAL.setColumns(10);
		contentPane.add(DORSAL);

		NOMBRE = new JTextField();
		NOMBRE.setBounds(171, 114, 186, 54);
		NOMBRE.setBackground(new Color(255, 255, 255));
		NOMBRE.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		NOMBRE.setColumns(10);
		contentPane.add(NOMBRE);

		ID = new JTextField();
		ID.setBounds(171, 33, 186, 54);
		ID.setBackground(new Color(255, 255, 255));
		ID.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		ID.setColumns(10);
		contentPane.add(ID);

		// controlar como se introducen los datos de los jugadores :
		
		                                                                      //  BOTON AÑADIR   :

		JButton btnAÑADIR = new JButton("AÑADIR");
		btnAÑADIR.setBounds(460, 57, 144, 54);
		btnAÑADIR.setBackground(new Color(0, 255, 64));
		btnAÑADIR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String nombre = NOMBRE.getText();
					int dorsal = Integer.parseInt(DORSAL.getText());
					double altura = Double.parseDouble(ALTURA.getText());
					nombre = nombre.toUpperCase();

					// Obtener el último ID de la base de datos :
					int ultimoID = controlador.obtenerUltimoID();

					// Generar el nuevo ID automáticamente :
					int nuevoID = ultimoID + 1;

					// Verificar si el jugador ya existe en la base de datos por nombre dorsal y  altura
					
					boolean jugadorExiste = controlador.existeEnBaseDeDatos(nombre, dorsal, altura);

					if (jugadorExiste) {
						JOptionPane.showMessageDialog(null, "Error, este jugador ya existe en la base de datos");
					} else {
						// Crear objeto Jugador1 con los datos ingresados
						Jugador1 jugador = new Jugador1(nuevoID, nombre, dorsal, altura);

						// Llamar al método guardarEnBaseDatos() en el controlador
						controlador.guardarEnBaseDatos(jugador);
						JOptionPane.showMessageDialog(null, "Jugador añadido correctamente");
						
						// Construir el mensaje con los datos del jugador añadido
			            String mensaje ="\n    Nombre  :  " + nombre + 
			            		        "\n      Dorsal  :  " + dorsal +"º"+
			            		        "\n      Altura  :  " + altura + "  cm";

			            // Mostrar los datos del jugador añadido en el JTextArea
			            consola.setText(mensaje);
						
						

					}
				} catch (NumberFormatException ex) {
					// Si se produce una excepción al convertir el dorsal o la altura a int o double
					// respectivamente
					JOptionPane.showMessageDialog(null,
							"Error !! el nombre del jugador es invalido \n o el número del dorsal es inválido \n o la altura del jugador es inválida");
				} catch (Exception ex) {
					// Cualquier otra excepción que pueda ocurrir
					JOptionPane.showMessageDialog(null, "Error, se ha producido un problema al guardar el jugador");

				}
				
			}

		});

		//

		btnAÑADIR.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		contentPane.add(btnAÑADIR);
		
		                                                                        //  BOTON ELIMINAR  :

		JButton btnELIMINAR = new JButton("ELIMINAR");
		btnELIMINAR.setBounds(460, 191, 144, 54);
		btnELIMINAR.setBackground(new Color(255, 0, 0));
		
		
		btnELIMINAR.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Verificar si los campos necesarios están vacíos
		        if (ID.getText().isEmpty() || NOMBRE.getText().isEmpty() || DORSAL.getText().isEmpty() || ALTURA.getText().isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
		            return;
		        }

		        // Convertir los valores solo si los campos no están vacíos
		        int id, dorsal;
		        double altura;
		        try {
		            id = Integer.parseInt(ID.getText());
		            dorsal = Integer.parseInt(DORSAL.getText());
		            altura = Double.parseDouble(ALTURA.getText());
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, "Los campos de ID, Dorsal y Altura deben ser números válidos.");
		            return;
		        }

		        String nombre = NOMBRE.getText();
		        nombre = nombre.toUpperCase();
		        
		        // Verificar si el jugador existe en la base de datos antes de eliminarlo
		        boolean jugadorExiste = controlador.existeEnBaseDeDatos(nombre, dorsal, altura);

		        if (jugadorExiste) {
		            // Obtener el ID del jugador existente con esos datos específicos
		            int idEnBaseDeDatos = controlador.obtenerIDJugador(nombre, dorsal, altura);
		            
		            if (idEnBaseDeDatos == id) {
		                // El jugador existe y el ID coincide, proceder a eliminarlo
		                controlador.eliminarDeBaseDatos(id);
		                JOptionPane.showMessageDialog(null, "Jugador eliminado correctamente");
		            } else {
		                JOptionPane.showMessageDialog(null, "Los datos proporcionados no coinciden con el jugador que se quiere eliminar.");
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "El jugador no existe en la base de datos");
		        }

		        limpiarCampos();
		    }
		});

		
		
		btnELIMINAR.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		contentPane.add(btnELIMINAR);
		
		                                                                  //  BOTON  LISTAR    :

		JButton btnLISTAR = new JButton("LISTAR");
		btnLISTAR.setBounds(460, 326, 144, 54);
		btnLISTAR.setBackground(new Color(0, 0, 255));
		btnLISTAR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String listaJugadores = controlador.listarBaseDeDatos();
				JOptionPane.showMessageDialog(null, listaJugadores);
				limpiarCampos();
			}
		});
		btnLISTAR.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		contentPane.add(btnLISTAR);
		consola.setBounds(94, 388, 263, 132);
		
		
		consola.setBackground(new Color(128, 128, 255));
		consola.setFont(new Font("Ebrima", Font.BOLD | Font.ITALIC, 20));
		contentPane.add(consola);
		
		consola.setEditable(false); 
		
		setTitle(
				".                                                      BASE DE DATOS JUGADORES                                 ");

	}
}
