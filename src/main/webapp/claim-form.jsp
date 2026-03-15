<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ include file="header.jsp" %>

<div class="container mt-5">

  <div class="row justify-content-center">
    <div class="col-md-7">
      <div class="card shadow p-4">

        <h2 class="mb-4">Create Claim</h2>

        <s:form action="saveClaim" id="claimForm">

          <div class="mb-3">
            <s:textfield name="claim.claimNumber"
                         label="Claim Number"
                         cssClass="form-control"
                         id="claimNumber"/>
            <div class="text-danger" id="claimNumberError"></div>
          </div>

          <div class="mb-3">
            <s:textfield name="claim.accidentDate"
                         label="Accident Date"
                         cssClass="form-control"
                         type="date"
                         id="accidentDate"/>
            <div class="text-danger" id="accidentDateError"></div>
          </div>

          <div class="mb-3">
            <s:textfield name="claim.accidentAddress"
                         label="Accident Address"
                         cssClass="form-control"
                         id="accidentAddress"/>
            <div class="text-danger" id="accidentAddressError"></div>
          </div>

          <div class="mb-3">
            <s:textfield name="claim.claimantName"
                         label="Claimant Name"
                         cssClass="form-control"
                         id="claimantName"/>
            <div class="text-danger" id="claimantNameError"></div>
          </div>

          <div class="mb-3">
            <s:textfield name="claim.claimantDob"
                         label="Claimant Date Of Birth"
                         cssClass="form-control"
                         type="date"
                         id="claimantDob"/>
            <div class="text-danger" id="claimantDobError"></div>
          </div>

          <s:submit value="Save Claim" cssClass="btn btn-success" id="submitBtn"/>

        </s:form>

      </div>
    </div>
  </div>

</div>

<script>

$(document).ready(function(){

    $("#claimForm").submit(function(e){

        if(!validateForm()){
            e.preventDefault();
        }

    });

    $("#claimNumber").on("input", function(){
        validateClaimNumber();
    });

    $("#accidentDate").on("change", function(){
        validateAccidentDate();
    });

    $("#accidentAddress").on("input", function(){
        validateAccidentAddress();
    });

    $("#claimantName").on("input", function(){
        validateClaimantName();
    });

    $("#claimantDob").on("change", function(){
        validateClaimantDob();
    });

});

function validateForm(){

    let valid = true;

    if(!validateClaimNumber()) valid = false;
    if(!validateAccidentDate()) valid = false;
    if(!validateAccidentAddress()) valid = false;
    if(!validateClaimantName()) valid = false;
    if(!validateClaimantDob()) valid = false;

    return valid;
}

function validateClaimNumber(){

    let val = $("#claimNumber").val().trim();

    if(val === ""){
        showError("claimNumberError", "Claim number is required");
        return false;
    }

    clearError("claimNumberError");
    return true;
}

function validateAccidentDate(){

    let val = $("#accidentDate").val();

    if(val === ""){
        showError("accidentDateError", "Accident date is required");
        return false;
    }

    let selected = new Date(val);
    let today = new Date();
    today.setHours(0, 0, 0, 0);

    if(selected > today){
        showError("accidentDateError", "Accident date cannot be in the future");
        return false;
    }

    clearError("accidentDateError");
    return true;
}

function validateAccidentAddress(){

    let val = $("#accidentAddress").val().trim();

    if(val === ""){
        showError("accidentAddressError", "Accident address is required");
        return false;
    }

    clearError("accidentAddressError");
    return true;
}

function validateClaimantName(){

    let val = $("#claimantName").val().trim();

    if(val === ""){
        showError("claimantNameError", "Claimant name is required");
        return false;
    }

    clearError("claimantNameError");
    return true;
}

function validateClaimantDob(){

    let val = $("#claimantDob").val();

    if(val === ""){
        showError("claimantDobError", "Claimant date of birth is required");
        return false;
    }

    let dob = new Date(val);
    let today = new Date();
    today.setHours(0, 0, 0, 0);

    if(dob > today){
        showError("claimantDobError", "Date of birth cannot be in the future");
        return false;
    }

    let age = today.getFullYear() - dob.getFullYear();
    let monthDiff = today.getMonth() - dob.getMonth();

    if(monthDiff < 0 || (monthDiff === 0 && today.getDate() < dob.getDate())){
        age--;
    }

    if(age < 18){
        showError("claimantDobError", "Claimant must be at least 18 years old");
        return false;
    }

    clearError("claimantDobError");
    return true;
}

function showError(id, message){
    $("#" + id).text(message);
}

function clearError(id){
    $("#" + id).text("");
}

</script>

<%@ include file="footer.jsp" %>