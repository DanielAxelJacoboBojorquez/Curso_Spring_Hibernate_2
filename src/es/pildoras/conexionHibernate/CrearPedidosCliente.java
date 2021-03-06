package es.pildoras.conexionHibernate;

import java.util.*;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class CrearPedidosCliente {

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
			Cliente elCliente=miSession.get(Cliente.class, 6);
			// Crear Pedidos del Cliente
			Pedido pedido1=new Pedido(new GregorianCalendar(2020,9,4));
			Pedido pedido2=new Pedido(new GregorianCalendar(2020,10,3));
			Pedido pedido3=new Pedido(new GregorianCalendar(2020,11,2));
		
			// Agregar Pedidos creados al Cliente creado
			elCliente.agregarPedidos(pedido1);
			elCliente.agregarPedidos(pedido2);
			elCliente.agregarPedidos(pedido3);
			// Guardar los Pedidos en la BBDD en la tabla Pedido
			miSession.save(pedido1);
			miSession.save(pedido2);
			miSession.save(pedido3);
			miSession.getTransaction().commit();
			System.out.println("Registros insertado correctamente en BBDD");		
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			miSession.close();
			miFactory.close();
		}
	}

}
