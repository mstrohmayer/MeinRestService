package com.sabel.restservice;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class BahnhofService {
	private EntityManagerFactory emf;
	private EntityManager em;

	public BahnhofService(){
		emf = Persistence.createEntityManagerFactory("bahnhof");
		em = emf.createEntityManager();
	}
	
	public void save(Bahnhof bh){
		em.getTransaction().begin();
		em.persist(bh);
		em.getTransaction().commit();
	}
	
	public Bahnhof remove(int id){
		Bahnhof bh = find(id);
		em.getTransaction().begin();
		em.remove(bh);
		em.getTransaction().commit();
		return bh;
	}
	
	public Bahnhof find(int id){
		return em.find(Bahnhof.class, id);
	}
	
	public List<Bahnhof> receiveAll(){
		TypedQuery<Bahnhof> query = em.createQuery("Select h from Bahnhof h ",Bahnhof.class);
		return query.getResultList();
	}
	
	public void close(){
		if (em != null){
			em.close();
			em = null;
		}
		if (emf != null){
			emf.close();
			em = null;
		}
	}
}


