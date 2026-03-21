<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="header.jsp" %>

<div class="container mt-5">
  <div class="row justify-content-center">
    <div class="col-md-7">
      <div class="card shadow p-4">

        <h2 class="mb-4">Create Claim</h2>

        <form id="claimForm">

          <div class="mb-3">
    <label class="form-label">Claim Number</label>
    <input type="text" class="form-control" id="claimNumber" 
           name="claim.claimNumber" readonly 
           style="background-color: #e9ecef;">
    <div class="text-muted small">Auto-generated on save</div>
</div>
          <div class="mb-3">
            <label class="form-label">Accident Date</label>
            <input type="date" class="form-control" id="accidentDate" name="accidentDateStr">
            <div class="text-danger small" id="accidentDateError"></div>
          </div>

          <div class="mb-3">
            <label class="form-label">Accident Address</label>
            <input type="text" class="form-control" id="accidentAddress" name="claim.accidentAddress">
            <div class="text-danger small" id="accidentAddressError"></div>
          </div>

          <div class="mb-3">
            <label class="form-label">Claimant Name</label>
            <input type="text" class="form-control" id="claimantName" name="claim.claimantName">
            <div class="text-danger small" id="claimantNameError"></div>
          </div>

          <div class="mb-3">
            <label class="form-label">Claimant Date Of Birth</label>
            <input type="date" class="form-control" id="claimantDob" name="claimantDobStr">
            <div class="text-danger small" id="claimantDobError"></div>
          </div>

          <button type="submit" class="btn btn-success" id="saveBtn">Save Claim</button>

        </form>

      </div>
    </div>
  </div>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script>
$(document).ready(function () {

    const errorDivIds = [
        "claimNumberError",
        "accidentDateError",
        "accidentAddressError",
        "claimantNameError",
        "claimantDobError"
    ];

    const serverFieldMap = {
        "claim.claimNumber":     "claimNumberError",
        "claim.accidentDate":    "accidentDateError",
        "claim.accidentAddress": "accidentAddressError",
        "claim.claimantName":    "claimantNameError",
        "claim.claimantDob":     "claimantDobError"
    };

    function clearAllErrors() {
        $.each(errorDivIds, function(i, id) {
            $("#" + id).text("");
        });
    }

    function showFieldErrors(fieldErrors) {
        $.each(fieldErrors, function(key, msgs) {
            const divId = serverFieldMap[key];
            if (divId) {
                $("#" + divId).text(msgs[0]);
            }
        });
    }

    function setLoading(loading) {
        $("#saveBtn").prop("disabled", loading);
        $("#saveBtn").text(loading ? "Saving..." : "Save Claim");
    }

    $("#claimForm").submit(function(e) {
        e.preventDefault();

        clearAllErrors();
        setLoading(true);

        $.ajax({
            url: "saveClaim.action",
            type: "POST",
            data: $("#claimForm").serialize(), 

            success: function(result) {
                console.log("Response:", result);

                if (result.success) {
                    window.location.href = "listClaimsPage";
                }
                else if (result.fieldErrors && Object.keys(result.fieldErrors).length > 0) {
                    showFieldErrors(result.fieldErrors);
                } 
                else {
                    alert(result.message || "An error occurred.");
                }
            },

            error: function(xhr) {
                console.error("Error:", xhr.responseText);
                alert("Server error");
            },

            complete: function() {
                setLoading(false);
            }
        });
    });
});
</script>

<%@ include file="footer.jsp" %>