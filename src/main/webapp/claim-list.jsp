<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ include file="header.jsp" %>

<div class="container mt-5">

  <h2>Claims List</h2>

  <div class="mb-3">
    <label>Search by Claimant Name</label>
    <input type="text"
           id="claimantSearch"
           class="form-control"
           placeholder="Enter claimant name">
  </div>

  <div class="mb-3">
    <label>Search by Claim Number</label>
    <input type="text"
           id="claimNumberSearch"
           class="form-control"
           placeholder="Enter claim number">
  </div>

  <table class="table table-bordered">
    <thead>
      <tr>
        <th>Claim Number</th>
        <th>Accident Date</th>
        <th>Accident Address</th>
        <th>Claimant</th>
        <th>Status</th>
        <th>Days Since Accident</th>
        <th>Action</th>
      </tr>
    </thead>
    <tbody id="claimsTable"></tbody>
  </table>

</div>

<s:url var="listClaimsUrl" action="listClaims"/>
<s:url var="submitClaimUrl" action="submitClaim"/>

<script>

let listClaimsUrl = "<s:property value='#listClaimsUrl'/>";
let submitClaimUrl = "<s:property value='#submitClaimUrl'/>";

$(document).ready(function(){
    loadClaims();
    setInterval(function(){
        loadClaims();
    }, 10000);
});

function loadClaims(){

    $.ajax({

        url: listClaimsUrl,
        method: "GET",

        success: function(response){

            let claims = response.claims || [];

            renderTable(claims);

        }

    });

}

function renderTable(claims){

    let rows = "";

    for(let i = 0; i < claims.length; i++){

        let claim = claims[i];

        let days = calculateDays(claim.accidentDate);

        rows += "<tr>";

        rows += "<td>" + claim.claimNumber + "</td>";
        rows += "<td>" + claim.accidentDate + "</td>";
        rows += "<td>" + claim.accidentAddress + "</td>";
        rows += "<td>" + claim.claimantName + "</td>";
        rows += "<td>" + claim.status + "</td>";
        rows += "<td>" + days + " days</td>";

        if(claim.status === "NEW"){

            rows += "<td>";
            rows += "<button onclick='submitClaim(" + claim.claimId + ")' class='btn btn-success'>Submit</button> ";
            rows += "<button onclick='deleteClaim(" + claim.claimId + ")' class='btn btn-danger'>Delete</button>";
            rows += "</td>";

        } else {

            rows += "<td><button class='btn btn-secondary' disabled>Submitted</button></td>";

        }

        rows += "</tr>";
    }

    $("#claimsTable").html(rows);
}

function calculateDays(accidentDate){

    if(!accidentDate) return "";

    let accident = new Date(accidentDate);
    let today = new Date();

    let diff = today - accident;

    let days = Math.floor(diff / (1000 * 60 * 60 * 24));

    return days;
}

$("#claimNumberSearch").keyup(function(){

    let num = $(this).val();

    if(num.trim() === ""){
        loadClaims();
        return;
    }

    $.ajax({

        url: "searchClaimsByNumber",
        method: "GET",
        data: { claimNumber: num },

        success: function(response){

            let claims = response.claims || [];

            renderTable(claims);

        }

    });

});

$("#claimantSearch").keyup(function(){

    let name = $(this).val();

    if(name.trim() === ""){
        loadClaims();
        return;
    }

    $.ajax({

        url: "searchClaimsByClaimant",
        method: "GET",
        data: { claimantName: name },

        success: function(response){

            let claims = response.claims || [];

            renderTable(claims);

        }

    });

});

function deleteClaim(claimId){

    if(!confirm("Are you sure you want to delete this claim?")){
        return;
    }

    $.ajax({

        url: "deleteClaim",
        method: "POST",
        data: { "claim.claimId": claimId },

        success: function(){
            loadClaims();
        }

    });

}

function submitClaim(claimId){

    if(!confirm("Are you sure you want to submit this claim?")){
        return;
    }

    $.ajax({

        url: submitClaimUrl,
        method: "GET",
        data: { "claim.claimId": claimId },

        success: function(){
            loadClaims();
        }

    });

}

</script>

<%@ include file="footer.jsp" %>