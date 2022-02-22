package controlador;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.Transaction;

import modelo.Conection;
import modelo.Estaciones;
import modelo.Lineas;
import modelo.LineasEstaciones;
import modelo.LineasEstacionesId;
import vista.MainWindow;

public class LogicaNegocio implements ActionListener {
	MainWindow vista;
	private Session session;

	public LogicaNegocio(MainWindow vista) {
		this.session = Conection.getConnection().openSession();
		this.vista = vista;
		setActions();
		openWindow();
	}

	public void setActions() {
		vista.btnGuardar.addActionListener(this);
		vista.btnCancelar.addActionListener(v -> {
			vista.txtLinea.setText("");
			vista.txtLinea.requestFocusInWindow();
			vista.txtEstacion.setText("");
			vista.txtOrden.setText("");
		});
	}

	public void openWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vista.setTitle("Examen2");
					vista.setLocationRelativeTo(null);
					vista.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public int insertarEstacionEnLinea(int codLinea, int codEstacion, int posicion) {
		Lineas lineas = session.get(Lineas.class, codLinea);
		if (lineas == null)
			return 1;

		Estaciones estaciones = session.get(Estaciones.class, codEstacion);
		if (estaciones == null)
			return 2;

		if (session.get(LineasEstaciones.class, new LineasEstacionesId(codLinea, codEstacion)) != null)
			return 3;

		String query = "FROM LineasEstaciones WHERE lineas.codLinea = " + codLinea + " AND orden = " + posicion;
		if (session.createQuery(query, LineasEstaciones.class).getResultList().size() > 0)
			return 4;

		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			LineasEstaciones lineasEstaciones = new LineasEstaciones(new LineasEstacionesId(codLinea, codEstacion),
					estaciones, lineas, posicion);
			session.save(lineasEstaciones);
			transaction.commit();

			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 4;
		}

	}

	public void actionPerformed(ActionEvent v) {
		try {
			int codLinea = Integer.parseInt(vista.txtLinea.getText());
			int codEstacion = Integer.parseInt(vista.txtEstacion.getText());
			int posicion = Integer.parseInt(vista.txtOrden.getText());

			switch (insertarEstacionEnLinea(codLinea, codEstacion, posicion)) {
			case 0:
				JOptionPane.showMessageDialog(vista, "Estacíon insertada con éxito");
				break;
			case 1:
				JOptionPane.showMessageDialog(vista, "La linea a la que se pretende incorporar la estacion no existe");
				break;
			case 2:
				JOptionPane.showMessageDialog(vista, "La estacion que se pretende incorporar a la linea no existe");
				break;
			case 3:
				JOptionPane.showMessageDialog(vista, "La estacion ya existe en la linea");
				break;
			case 4:
				JOptionPane.showMessageDialog(vista,
						"La posicion indicada ya esta ocupada por otra estacion en la linea");
				break;
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(vista, "Error, los datos introducidos deben ser numeros");
		}
	}
}
