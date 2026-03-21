package com.cms.dao;


import java.util.List;

import com.cms.entity.Claim;

public interface ClaimDAO {

    void saveClaim(Claim claim);

//    void updateClaim(Claim claim);

//    Claim getClaim(Long id);
    
    public String generateClaimNumber();

    List<Claim> getAllClaims();
    
    void submitClaim(Long claimId);
    
    List<Claim> getOpenClaims();

    public Claim getClaimById(Long id);
    
    public void updateClaim(Claim claim);
    
    public List<Claim> getClaimsByUser(Long userId);
    
    List<Claim> getClaimsByClaimantName(String claimantName);
    
    List<Claim> getClaimsByClaimantNameAndUser(String name, Long userId);
    
    List<Claim> getClaimsByClaimNumber(String claimNumber);
    
    List<Claim> getClaimsByClaimNumberAndUser(String claimNumber, Long userId);
    
    void deleteClaim(Long claimId);
}