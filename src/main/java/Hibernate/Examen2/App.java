package Hibernate.Examen2;

import controlador.LogicaNegocio;
import vista.MainWindow;

public class App 
{
    public static void main(String[] args)
    {
    	try {
    		MainWindow vista = new MainWindow();
        	LogicaNegocio controller = new LogicaNegocio(vista);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
