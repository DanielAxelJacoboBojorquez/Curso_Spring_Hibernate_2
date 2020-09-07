package es.pildoras.conexionHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EliminaDetallesCliente {

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
			DetallesCliente detallesDelCliente=miSession.get(DetallesCliente.class, 5);
			detallesDelCliente.getElCliente().setDetallesCliente(null);
			if(detallesDelCliente!=null) {
				//System.out.println("Voy a eliminar al cliente "+elCliente.getNombre());
				miSession.delete(detallesDelCliente);
			}
			miSession.getTransaction().commit();
			if(detallesDelCliente!=null) System.out.println("Registro eliminado correctamente en BBDD");
			else System.out.println("Nada que eliminar!");
			miSession.close();
		}finally {
			miFactory.close();
		}
	}

}
