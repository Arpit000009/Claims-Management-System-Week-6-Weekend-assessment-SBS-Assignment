package com.cms.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="claims")
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long claimId;

    @Column(name="claim_number")
    private String claimNumber;

    @Temporal(TemporalType.DATE)
    @Column(name="accident_date")
    private Date accidentDate;

    @Column(name="accident_address")
    private String accidentAddress;

    @Column(name="claimant_name")
    private String claimantName;

    @Temporal(TemporalType.DATE)
    @Column(name="claimant_dob")
    private Date claimantDob;

    @Column(name="status")
    private String status;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

	public Long getClaimId() {
		return claimId;
	}

	public void setClaimId(Long claimId) {
		this.claimId = claimId;
	}

	public String getClaimNumber() {
		return claimNumber;
	}

	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}

	public Date getAccidentDate() {
		return accidentDate;
	}

	public void setAccidentDate(Date accidentDate) {
		this.accidentDate = accidentDate;
	}

	public String getAccidentAddress() {
		return accidentAddress;
	}

	public void setAccidentAddress(String accidentAddress) {
		this.accidentAddress = accidentAddress;
	}

	public String getClaimantName() {
		return claimantName;
	}

	public void setClaimantName(String claimantName) {
		this.claimantName = claimantName;
	}

	public Date getClaimantDob() {
		return claimantDob;
	}

	public void setClaimantDob(Date claimantDob) {
		this.claimantDob = claimantDob;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
    

}