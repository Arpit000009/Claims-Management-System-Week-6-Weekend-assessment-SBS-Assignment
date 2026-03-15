package com.cms.action;

import com.cms.entity.Claim;
import com.cms.entity.User;
import com.cms.service.ClaimService;
import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

public class ClaimAction extends ActionSupport {

    @Autowired
    private ClaimService claimService;

    private Claim claim;

    private List<Claim> claims;
    
    private String claimantName;
    
    private String claimNumber;

    public String createClaim() {

        claimService.createClaim(claim);

        return SUCCESS;
    }

    public String listClaims() {

    	HttpSession session =
    	        ServletActionContext.getRequest().getSession();

    	User user = (User) session.getAttribute("user");

    	claims = claimService.getClaimsByUser(user.getUserId());

        return SUCCESS;
    }
    
    public List<Claim> getClaims() {
        return claims;
    }
    
    public String saveClaim() {

        HttpSession session =
                ServletActionContext.getRequest().getSession();

        User user = (User) session.getAttribute("user");

        claim.setUser(user);     

        claim.setStatus("NEW");

        claimService.saveClaim(claim);

        return SUCCESS;
    }
    
    @SkipValidation
    public String submitClaim() {

        claimService.submitClaim(claim.getClaimId());

        return SUCCESS;
    }
    
    public String managerDashboard() {

        claims = claimService.getOpenClaims();
        
//        System.out.println("CALL CaLL");
//        System.out.println("count: " + claimService.getOpenClaims().size());


        return SUCCESS;
    }

    @SkipValidation
    public String editClaim() {

        claim = claimService.getClaimById(claim.getClaimId());

        return SUCCESS;
    }
    
    
    public String updateClaim() {

        Claim existing = claimService.getClaimById(claim.getClaimId());

        existing.setClaimNumber(claim.getClaimNumber());
        existing.setAccidentDate(claim.getAccidentDate());
        existing.setAccidentAddress(claim.getAccidentAddress());
        existing.setClaimantName(claim.getClaimantName());
        existing.setStatus(claim.getStatus());
        
        claimService.updateClaim(existing);

        return SUCCESS;
    }
    
    public String searchClaimsByClaimant() {

        HttpSession session =
            ServletActionContext.getRequest().getSession();

        User user = (User) session.getAttribute("user");

        if ("MANAGER".equalsIgnoreCase(user.getRole())) {

           
            claims = claimService.getClaimsByClaimantName(claimantName);

        } else {

            claims = claimService.getClaimsByClaimantNameAndUser(
                    claimantName,
                    user.getUserId()
            );

        }

        return SUCCESS;
    }
    
    
    public String searchClaimsByNumber() {

        HttpSession session =
            ServletActionContext.getRequest().getSession();

        User user = (User) session.getAttribute("user");

        if ("MANAGER".equalsIgnoreCase(user.getRole())) {

            claims = claimService.getClaimsByClaimNumber(claimNumber);

        } else {

            claims = claimService.getClaimsByClaimNumberAndUser(
                    claimNumber,
                    user.getUserId()
            );
        }

        return SUCCESS;
    }
    
    @SkipValidation
    public String deleteClaim() {
        claimService.deleteClaim(claim.getClaimId());
        return SUCCESS;
    }




    public Claim getClaim() {
        return claim;
    }

    public void setClaim(Claim claim) {
        this.claim = claim;
    }

	public ClaimService getClaimService() {
		return claimService;
	}

	public void setClaimService(ClaimService claimService) {
		this.claimService = claimService;
	}

	public void setClaims(List<Claim> claims) {
		this.claims = claims;
	}
	
	public String getClaimantName() {
	    return claimantName;
	}

	public void setClaimantName(String claimantName) {
	    this.claimantName = claimantName;
	}
	
	public String getClaimNumber() {
	    return claimNumber;
	}

	public void setClaimNumber(String claimNumber) {
	    this.claimNumber = claimNumber;
	}
	
	@Override
	public void validate() {

	    if (claim == null) {
	        return;
	    }

	    Date today = new Date();


	    if (claim.getAccidentDate() != null && claim.getAccidentDate().after(today)) {
	        addFieldError("claim.accidentDate", "Accident date cannot be in the future");
	    }

	    if (claim.getClaimantDob() != null && claim.getClaimantDob().after(today)) {
	        addFieldError("claim.claimantDob", "Claimant DOB cannot be in the future");
	    }

	    if (claim.getClaimantDob() != null) {

	        Calendar dob = Calendar.getInstance();
	        dob.setTime(claim.getClaimantDob());

	        Calendar todayCal = Calendar.getInstance();

	        int age = todayCal.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

	        if (todayCal.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
	            age--;
	        }

	        if (age < 18) {
	            addFieldError("claim.claimantDob", "Claimant must be at least 18 years old");
	        }
	    }

	    if (claim.getClaimantName() == null || claim.getClaimantName().trim().isEmpty()) {
	        addFieldError("claim.claimantName", "Claimant name is required");
	    }

	    if (claim.getAccidentAddress() == null || claim.getAccidentAddress().trim().isEmpty()) {
	        addFieldError("claim.accidentAddress", "Accident address is required");
	    }
	}


    
    
}