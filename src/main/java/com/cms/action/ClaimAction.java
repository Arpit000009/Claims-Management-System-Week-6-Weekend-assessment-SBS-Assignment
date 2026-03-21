package com.cms.action;
import com.opensymphony.xwork2.Preparable;

import com.cms.DTO.ApiResponse;
import com.cms.entity.Claim;
import com.cms.entity.User;
import com.cms.service.ClaimService;
import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

public class ClaimAction extends ActionSupport {

    @Autowired
    private ClaimService claimService;

    private Claim claim = new Claim();

    private List<Claim> claims;
    
    private String claimantName;
    
    private String claimNumber;
    
    
    private String accidentDateStr;
    private String claimantDobStr;
    
    private ApiResponse response = new ApiResponse();
//    private boolean success;
//    private String message;

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
    	
    	if (hasFieldErrors()) {
            response.setSuccess(false);
            response.setMessage("Validation failed");
            response.setFieldErrors(getFieldErrors());
            return SUCCESS; // return JSON
        }
    	
        try {
            // Parse date strings FIRST so validate() sees populated dates
            

            HttpSession session = ServletActionContext.getRequest().getSession();
            User user = (User) session.getAttribute("user");

            claim.setUser(user);
            claim.setStatus("NEW");
            claimService.saveClaim(claim);

            response.setSuccess(true);
            response.setMessage("Claim saved successfully");
            response.setData(claim);
           
        } catch (Exception e) {
            e.printStackTrace();
            response.setSuccess(false);
            response.setMessage("Error: " + e.getMessage());
            
        }
        return SUCCESS;
    }
    
// // Add this method to your ClaimAction
//    public void prepare() {
//        if (claim == null) claim = new Claim();
//        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            if (accidentDateStr != null && !accidentDateStr.isEmpty())
//                claim.setAccidentDate(sdf.parse(accidentDateStr));
//            if (claimantDobStr != null && !claimantDobStr.isEmpty())
//                claim.setClaimantDob(sdf.parse(claimantDobStr));
//        } catch (Exception e) {
//            // leave dates null — validate() will catch them
//        }
//    }

    
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
    
 
	public void setResponse(ApiResponse response) {
		this.response = response;
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
	
	public String getAccidentDateStr() {
	    return accidentDateStr;
	}

	public void setAccidentDateStr(String accidentDateStr) {
	    this.accidentDateStr = accidentDateStr;
	}

	public String getClaimantDobStr() {
	    return claimantDobStr;
	}

	public void setClaimantDobStr(String claimantDobStr) {
	    this.claimantDobStr = claimantDobStr;
	}
	  
    public ApiResponse getResponse() {
		return response;
	}

   
    public void validateSaveClaim() {

        if (claim == null) return;

        // Parse dates HERE, before validating them
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (accidentDateStr != null && !accidentDateStr.isEmpty())
                claim.setAccidentDate(sdf.parse(accidentDateStr));
        } catch (Exception e) {
            addFieldError("claim.accidentDate", "Invalid date format");
        }

        try {
            if (claimantDobStr != null && !claimantDobStr.isEmpty())
                claim.setClaimantDob(sdf.parse(claimantDobStr));
        } catch (Exception e) {
            addFieldError("claim.claimantDob", "Invalid date format");
        }

//        if (claim.getClaimNumber() == null || claim.getClaimNumber().trim().isEmpty())
//            addFieldError("claim.claimNumber", "Claim Number is required");

        if (claim.getClaimantName() == null || claim.getClaimantName().trim().isEmpty())
            addFieldError("claim.claimantName", "Claimant Name is required");

        if (claim.getAccidentAddress() == null || claim.getAccidentAddress().trim().isEmpty())
            addFieldError("claim.accidentAddress", "Accident Address is required");

        if (claim.getAccidentDate() == null) {
            addFieldError("claim.accidentDate", "Accident Date is required");
        } else if (claim.getAccidentDate().after(new Date())) {
            addFieldError("claim.accidentDate", "Future accident date is not allowed");
        }

        if (claim.getClaimantDob() == null) {
            addFieldError("claim.claimantDob", "Claimant DOB is required");
        } else {
            Calendar today = Calendar.getInstance();
            Calendar dob = Calendar.getInstance();
            dob.setTime(claim.getClaimantDob());

            int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
            if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) age--;

            if (age < 18)
                addFieldError("claim.claimantDob", "Claimant must be at least 18 years old");
        }

        if (hasFieldErrors()) {
            response.setSuccess(false);
            response.setMessage("Validation failed");
            response.setFieldErrors(getFieldErrors());
        }
    }
    
    public void validateUpdateClaim() {

        if (claim == null) return;

        // Parse dates HERE, before validating them
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (accidentDateStr != null && !accidentDateStr.isEmpty())
                claim.setAccidentDate(sdf.parse(accidentDateStr));
        } catch (Exception e) {
            addFieldError("claim.accidentDate", "Invalid date format");
        }

        try {
            if (claimantDobStr != null && !claimantDobStr.isEmpty())
                claim.setClaimantDob(sdf.parse(claimantDobStr));
        } catch (Exception e) {
            addFieldError("claim.claimantDob", "Invalid date format");
        }

        if (claim.getClaimNumber() == null || claim.getClaimNumber().trim().isEmpty())
            addFieldError("claim.claimNumber", "Claim Number is required");

        if (claim.getClaimantName() == null || claim.getClaimantName().trim().isEmpty())
            addFieldError("claim.claimantName", "Claimant Name is required");

        if (claim.getAccidentAddress() == null || claim.getAccidentAddress().trim().isEmpty())
            addFieldError("claim.accidentAddress", "Accident Address is required");

        if (claim.getAccidentDate() == null) {
            addFieldError("claim.accidentDate", "Accident Date is required");
        } else if (claim.getAccidentDate().after(new Date())) {
            addFieldError("claim.accidentDate", "Future accident date is not allowed");
        }

//        if (claim.getClaimantDob() == null) {
//            addFieldError("claim.claimantDob", "Claimant DOB is required");
//        } else {
//            Calendar today = Calendar.getInstance();
//            Calendar dob = Calendar.getInstance();
//            dob.setTime(claim.getClaimantDob());
//
//            int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
//            if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) age--;
//
//            if (age < 18)
//                addFieldError("claim.claimantDob", "Claimant must be at least 18 years old");
//        }

        if (hasFieldErrors()) {
            response.setSuccess(false);
            response.setMessage("Validation failed");
            response.setFieldErrors(getFieldErrors());
        }
    }
    
    
}