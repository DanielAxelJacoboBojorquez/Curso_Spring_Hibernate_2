package es.pildoras.conexionHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ObtenerCliente {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 1.- Crear SessionFactory
		SessionFactory miFactory=new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Cliente.class)
				.addAnnotatedClass(DetallesCliente.class)
				.buildSessionFactory();
		
		// 2.- Crear obj Session
		Session miSession=miFactory.openSession();
		
		// 3.- Crear obj (Clientes)
		try {
			miSession.beginTransaction();
			// Obtener DetallesCliente
			DetallesCliente detallesDeCliente=miSession.get(DetallesCliente.class, 1);
			System.out.println(detallesDeCliente);
			System.out.println(detallesDeCliente.getElCliente());
			miSession.getTransaction().commit();
			miSession.close();
		}finally {
			miFactory.close();
		}
	}

}
