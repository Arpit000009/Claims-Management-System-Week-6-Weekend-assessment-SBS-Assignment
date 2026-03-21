package com.cms.service;

import java.util.List;

import com.cms.entity.Claim;

public interface ClaimService {

    void createClaim(Claim claim);
    
    String generateClaimNumber();

    List<Claim> getAllClaims();
    
    void saveClaim(Claim claim);
    
    void submitClaim(Long claimId);
    
    List<Claim> getOpenClaims();
    
    Claim getClaimById(Long id);

    void updateClaim(Claim claim);
    
    List<Claim> getClaimsByUser(Long userId);
    
    List<Claim> getClaimsByClaimantName(String name);
    
    List<Claim> getClaimsByClaimantNameAndUser(String name, Long userId);
    
    List<Claim> getClaimsByClaimNumber(String claimNumber);
    
    List<Claim> getClaimsByClaimNumberAndUser(String claimNumber, Long userId);
    
    void deleteClaim(Long claimId);

}