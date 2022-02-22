package modelo;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class Conection {
	private static SessionFactory instance;

	public static SessionFactory getConnection() {
		if (instance == null) {
			instance = (SessionFactory) new Configuration().configure().buildSessionFactory(
					new StandardServiceRegistryBuilder().configure().build());
			return instance;
		} else {
			return instance;
		}
	}

	public static void closeConnection() {
		instance.close();
	}
}