package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField txtLinea;
	public JTextField txtEstacion;
	public JTextField txtOrden;
	public JButton btnGuardar;
	public JButton btnCancelar;

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblLinea = new JLabel("Número de línea:");
		lblLinea.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLinea.setBounds(20, 20, 236, 24);
		contentPane.add(lblLinea);

		JLabel lblEstacion = new JLabel("Número de estación:");
		lblEstacion.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEstacion.setBounds(20, 60, 236, 24);
		contentPane.add(lblEstacion);

		JLabel lblOrden = new JLabel("Posición de la estación en la línea:");
		lblOrden.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblOrden.setBounds(20, 100, 236, 24);
		contentPane.add(lblOrden);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnGuardar.setBounds(22, 155, 100, 23);
		contentPane.add(btnGuardar);

		txtLinea = new JTextField();
		txtLinea.setBounds(266, 20, 86, 20);
		contentPane.add(txtLinea);
		txtLinea.setColumns(10);

		txtEstacion = new JTextField();
		txtEstacion.setColumns(10);
		txtEstacion.setBounds(266, 60, 86, 20);
		contentPane.add(txtEstacion);

		txtOrden = new JTextField();
		txtOrden.setColumns(10);
		txtOrden.setBounds(266, 100, 86, 20);
		contentPane.add(txtOrden);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCancelar.setBounds(147, 155, 100, 23);
		contentPane.add(btnCancelar);
	}
}