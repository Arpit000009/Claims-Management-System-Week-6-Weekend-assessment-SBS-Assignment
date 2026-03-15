<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ include file="header.jsp" %>

<div class="container mt-5">

  <div class="card shadow">

    <div class="card-header bg-primary text-white">
      <h4 class="mb-0">Edit Claim</h4>
    </div>

    <div class="card-body">

      <s:form action="updateClaim" cssClass="row g-3">

        <s:hidden name="claim.claimId"/>

        <div class="col-md-6">
          <s:textfield label="Claim Number"
                       name="claim.claimNumber"
                       cssClass="form-control"/>
        </div>

        <div class="col-md-6">
          <s:textfield label="Accident Date"
                       name="claim.accidentDate"
                       type="date"
                       cssClass="form-control"
                       value="%{claim.accidentDate.toString().substring(0,10)}"/>
        </div>

        <div class="col-md-12">
          <s:textfield label="Accident Address"
                       name="claim.accidentAddress"
                       cssClass="form-control"/>
        </div>

        <div class="col-md-6">
          <s:textfield label="Claimant Name"
                       name="claim.claimantName"
                       cssClass="form-control"/>
        </div>

        <div class="col-md-6">
          <s:textfield label="Status"
                       name="claim.status"
                       cssClass="form-control"/>
        </div>

        <s:hidden name="claim.claimantDob"/>

        <div class="col-12 text-end">
          <s:submit value="Update Claim" cssClass="btn btn-success"/>
        </div>

      </s:form>

    </div>
  </div>

</div>

<%@ include file="footer.jsp" %>