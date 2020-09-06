package es.pildoras.conexionHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class InsertaCliente {

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
			// INSERT en tabla Cliente y tabla Detalles_Cliente
			Cliente cliente1=new Cliente("Sandra","Delgado","Goya");
			DetallesCliente detallescliente1=new DetallesCliente("www.pildorasinformaticas.es","54654654","Primer Cliente");
			// Asociar los objetos
			cliente1.setDetallesCliente(detallescliente1);
			miSession.beginTransaction();
			// 4.- Ejecutar trasacción SQL
			miSession.save(cliente1);
			miSession.getTransaction().commit();
			System.out.println("Registro insertado correctamente en BBDD");
			miSession.close();
		}finally {
			miFactory.close();
		}
	}

}
