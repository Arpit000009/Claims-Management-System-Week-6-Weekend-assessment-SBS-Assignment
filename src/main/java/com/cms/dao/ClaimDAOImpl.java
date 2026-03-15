package com.cms.dao;

import com.cms.entity.Claim;
import com.cms.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClaimDAOImpl implements ClaimDAO {

    @Override
    public void saveClaim(Claim claim) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.save(claim);

        tx.commit();
        session.close();
    }

    @Override
    public List<Claim> getAllClaims() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Claim> claims =
                session.createQuery("from Claim", Claim.class).list();

        session.close();

        return claims;
    }
    
    @Override
    public void submitClaim(Long claimId) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Claim claim = session.get(Claim.class, claimId);

        claim.setStatus("OPEN");

        session.update(claim);

        tx.commit();
        session.close();
    }
    
    @Override
    public List<Claim> getOpenClaims() {

        Session session = HibernateUtil
                .getSessionFactory()
                .openSession();

        String hql = "FROM Claim WHERE status='OPEN'";

        List<Claim> claims =
                session.createQuery(hql, Claim.class).list();

        session.close();

        return claims;
    }
    
    
    @Override
    public Claim getClaimById(Long id) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Claim claim = session.get(Claim.class, id);

        session.close();

        return claim;
    }

    @Override
    public void updateClaim(Claim claim) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction tx = session.beginTransaction();

        session.update(claim);

        tx.commit();
        session.close();
    }
    
    
    @Override
    public List<Claim> getClaimsByUser(Long userId) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        String hql = "FROM Claim WHERE user.userId = :uid";

        List<Claim> claims = session.createQuery(hql, Claim.class)
                .setParameter("uid", userId)
                .list();

        session.close();

        return claims;
    }
    
    @Override
    public List<Claim> getClaimsByClaimantName(String claimantName) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        String hql = "FROM Claim WHERE lower(claimantName) LIKE :name";

        List<Claim> claims = session.createQuery(hql, Claim.class)
                .setParameter("name", "%" + claimantName.toLowerCase() + "%")
                .list();

        session.close();

        return claims;
    }
    
    @Override
    public List<Claim> getClaimsByClaimantNameAndUser(String name, Long userId) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        String hql = "FROM Claim WHERE user.userId = :uid AND lower(claimantName) LIKE :name";

        List<Claim> claims = session.createQuery(hql, Claim.class)
                .setParameter("uid", userId)
                .setParameter("name", "%" + name.toLowerCase() + "%")
                .list();

        session.close();

        return claims;
    }
    
    @Override
    public List<Claim> getClaimsByClaimNumber(String claimNumber) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        String hql = "FROM Claim WHERE claimNumber LIKE :num";

        List<Claim> claims = session.createQuery(hql, Claim.class)
                .setParameter("num", "%" + claimNumber + "%")
                .list();

        session.close();

        return claims;
    }
    
    @Override
    public List<Claim> getClaimsByClaimNumberAndUser(String claimNumber, Long userId) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        String hql = "FROM Claim WHERE user.userId = :uid AND claimNumber LIKE :num";

        List<Claim> claims = session.createQuery(hql, Claim.class)
                .setParameter("uid", userId)
                .setParameter("num", "%" + claimNumber + "%")
                .list();

        session.close();

        return claims;
    }
    
    @Override
    public void deleteClaim(Long claimId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Claim claim = session.get(Claim.class, claimId);
        session.delete(claim);
        tx.commit();
        session.close();
    }


	
	
	
	
}