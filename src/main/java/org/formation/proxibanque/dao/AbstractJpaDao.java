package org.formation.proxibanque.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.formation.proxibanque.util.Config;

/**
 * Class abstract DAO qui prepare certains requetes CRUD en utilisant
 *
 * @author JW
 */


public abstract class AbstractJpaDao<T> {

	@PersistenceContext(unitName = Config.JPA_UNIT_NAME)
	private EntityManager em;

	public AbstractJpaDao() {

	}

	/**
	 * ajouter un objet dans une persistence
	 * 
	 * @param t
	 *            : un objet generique
	 */
	public void addElement(T t) throws DaoException {
		try {
			em.persist(t);
		} catch (Exception ex) {
			throw new DaoException("Ajout de l'element " + t , ex);
		}
	}

	/**
	 * modifier un objet dans une persistence
	 * 
	 * @param t
	 *            : l'objet modifie et enresigtrer
	 */
	public T updateElement(T t) throws DaoException {
		try {
			return em.merge(t);
		} catch (Exception ex) {
			throw new DaoException("Fusion de l'element " + t , ex);
		}
	}

	/**
	 * supprimer un objet dans une persistence
	 * 
	 * @param t
	 *            : objetclientsFound a supprimer
	 */
	public void deleteElement(T t) throws DaoException {
		try {
			em.remove(em.merge(t));
		} catch (Exception ex) {
			throw new DaoException("Suppression de l'element " + t , ex);
		}
	}
	

//	public T getElementById(Long k) throws DaoException {
//			
//		
//			T t = em.find(T.class, k);

//
//	}
//
//
//	public List<T> getAllElement() throws DaoException {
//		
//		
//			List<T> t = em.find(T.class);
//		
//	}

}
