package es.pildoras.conexionHibernate;

import java.util.*;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class ObtenerPedidosCliente {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 1.- Crear SessionFactory
		SessionFactory miFactory=new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Cliente.class)
				.addAnnotatedClass(DetallesCliente.class)
				.addAnnotatedClass(Pedido.class)
				.buildSessionFactory();
		
		// 2.- Crear obj Session
		Session miSession=miFactory.openSession();
		
		// 3.- Crear obj (Clientes)
		try {
			
			// Asociar los objetos

			miSession.beginTransaction();
			// 4.- Ejecutar trasacción SQL
			// Obtener el Cliente de la tabla Clientes de la BBDD
			//Cliente elCliente=miSession.get(Cliente.class, 6);
			Query<Cliente> consulta=miSession.createQuery("SELECT CL FROM Cliente CL JOIN FETCH CL.pedidos WHERE CL.id=:elClienteId", Cliente.class);
			consulta.setParameter("elClienteId", 6);
			Cliente elCliente=consulta.getSingleResult();
			System.out.println("Cliente:"+elCliente);
			miSession.getTransaction().commit();	
			miSession.close();
			System.out.println("Pedidos: "+elCliente.getPedidos());	
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			miFactory.close();
		}
	}

}
