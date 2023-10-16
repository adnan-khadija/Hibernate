/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.service;

import java.util.List;
import ma.projet.classes.Employe;
import ma.projet.classes.Employe;
import ma.projet.classes.Projet;
import ma.projet.classes.Tache;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author lenovo
 */
public class EmployeService implements IDao<Employe>{

   
    @Override
    public boolean create(Employe o) {
        Session session =null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(o);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
        } finally {
            if(session != null)
                session.close();
        }
        return false;
    }

    @Override
    public Employe getById(int id) {
     Employe employe = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            employe = (Employe) session.get(Employe.class, id);
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
        } finally {
            if(session != null)
                session.close();
        }
        return employe;     }

    @Override
    public List<Employe> getAll() {
        List<Employe> employes = null;
        Session session = null;
        Transaction tx = null;
        try {
            session =HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            employes = session.createQuery("from Employe").list();
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
        } finally {
            if(session != null)
                session.close();
        }
        return employes; 
    }
 
    public List<Tache> listerTachesRealiseesParEmploye(Employe employe) {
        List<Tache> taches=null;
       Session session = null;
        Transaction tx = null;
        try {
            session =HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            taches =session.getNamedQuery("tacheEmploye").setParameter("id",employe.getId() ).list();
           
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
        } finally {
            if(session != null)
                session.close();
        }
        return taches; 
    
    }
    
    public List<Projet> listerProjetsGeresParEmploye(Employe employe) {
        List<Projet> projets=null;
        Session session = null;
        Transaction tx = null;
        try {
            session =HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            projets =session.getNamedQuery("projetEmploye").setParameter("id",employe.getId() ).list();
           
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
        } finally {
            if(session != null)
                session.close();
        }
        return projets; 
    }  
}
