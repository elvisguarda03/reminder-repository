package br.com.guacom.lembrete.dao;

import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.EntityManager;

import br.com.guacom.lembrete.model.Lembrete;
import br.com.guacom.lembrete.util.JPAUtil;

public class LembreteDAO implements AutoCloseable {
	private EntityManager entity;

	public LembreteDAO() {
	}

	public void persist(Lembrete lembrete) {
		try {
			entity = new JPAUtil().getEntityManager();
			entity.getTransaction().begin();
			entity.persist(lembrete);
			entity.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public List<Lembrete> findAll() {
		try {
			entity = new JPAUtil().getEntityManager();
			entity.getTransaction().begin();
			List<Lembrete> lembretes = entity.createQuery("FROM " + Lembrete.class.getName()).getResultList();
			return lembretes;
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		throw new NoSuchElementException("Não existem lembretes armazenados!");
	}

	public void merge(Lembrete lembrete) {
		try {
			entity = new JPAUtil().getEntityManager();
			entity.getTransaction().begin();
			entity.merge(lembrete);
			entity.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	public Lembrete getById(final int id) {
		try {
			entity = new JPAUtil().getEntityManager();
			Lembrete find = entity.find(Lembrete.class, id);
			return find;
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		throw new NoSuchElementException("Não existem lembretes armazenados!");
	}

	public void remove(Lembrete lembrete) {
		try {
			entity = new JPAUtil().getEntityManager();
			entity.getTransaction().begin();
			entity.remove(entity.merge(lembrete));
			entity.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	@Override
	public void close() throws Exception {
		entity.close();
	}
}