package es.pildoras.conexionHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EliminaCliente {

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
			Cliente elCliente=miSession.get(Cliente.class, 3);
			if(elCliente!=null) {
				miSession.delete(elCliente);
			}
			miSession.getTransaction().commit();
			System.out.println("Registro eliminado correctamente en BBDD");
			miSession.close();
		}finally {
			miFactory.close();
		}
	}

}
