package com.cms.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.dao.ClaimDAO;
import com.cms.entity.Claim;

import java.util.List;

@Service
public class ClaimServiceImpl implements ClaimService {

    @Autowired
    private ClaimDAO claimDAO;

    @Override
    public void createClaim(Claim claim) {

        claim.setStatus("NEW");

        claimDAO.saveClaim(claim);
    }

    @Override
    public String generateClaimNumber() {
        return claimDAO.generateClaimNumber();
    }
    
    @Override
    public List<Claim> getAllClaims() {

        return claimDAO.getAllClaims();
    }
    
    @Override
    public void saveClaim(Claim claim) {
    	
    	String generatedNumber = claimDAO.generateClaimNumber(); 
        claim.setClaimNumber(generatedNumber);  

        claimDAO.saveClaim(claim);

    }
    
    @Override
    public void submitClaim(Long claimId) {

        claimDAO.submitClaim(claimId);

    }
    
    @Override
    public List<Claim> getOpenClaims() {

        return claimDAO.getOpenClaims();
    }
    
    @Override
    public Claim getClaimById(Long id) {

        return claimDAO.getClaimById(id);
    }

    @Override
    public void updateClaim(Claim claim) {

        claimDAO.updateClaim(claim);
    }
    
    
    @Override
    public List<Claim> getClaimsByUser(Long userId) {

        return claimDAO.getClaimsByUser(userId);

    }
    
    @Override
    public void deleteClaim(Long claimId) {
        claimDAO.deleteClaim(claimId);
    }

    
    @Override
    public List<Claim> getClaimsByClaimantName(String name) {
        return claimDAO.getClaimsByClaimantName(name);
    }
    
    @Override
    public List<Claim> getClaimsByClaimantNameAndUser(String name, Long userId) {
        return claimDAO.getClaimsByClaimantNameAndUser(name, userId);
    }
    
    @Override
    public List<Claim> getClaimsByClaimNumber(String claimNumber) {
        return claimDAO.getClaimsByClaimNumber(claimNumber);
    }
    
    @Override
    public List<Claim> getClaimsByClaimNumberAndUser(String claimNumber, Long userId) {
        return claimDAO.getClaimsByClaimNumberAndUser(claimNumber, userId);
    }


    
}