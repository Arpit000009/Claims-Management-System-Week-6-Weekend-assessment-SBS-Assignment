<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ include file="header.jsp" %>

<div class="container mt-5">

  <h2>Manager Dashboard</h2>

  <div class="mb-3">
    <label>Search by Claimant Name</label>
    <input type="text" id="claimantSearch"
           class="form-control"
           placeholder="Enter claimant name">
  </div>

  <div class="mb-3">
    <label>Search by Claim Number</label>
    <input type="text" id="claimNumberSearch"
           class="form-control"
           placeholder="Enter claim number">
  </div>

  <table class="table table-bordered">
    <thead>
      <tr>
        <th>Claim Number</th>
        <th>Accident Date</th>
        <th>Address</th>
        <th>Claimant</th>
        <th>Status</th>
        <th>Days Since Accident</th>
        <th>Action</th>
      </tr>
    </thead>
    <tbody id="managerTable"></tbody>
  </table>

</div>

<s:url var="managerDashboardUrl" action="managerDashboard"/>
<s:url var="editClaimUrl" action="editClaim"/>

<script>

let managerDashboardUrl = "<s:property value='#managerDashboardUrl'/>";
let editClaimUrl = "<s:property value='#editClaimUrl'/>";

$(document).ready(function(){
    loadManagerClaims();
    setInterval(function(){
        loadManagerClaims();
    }, 10000);
});


function calculateDays(accidentDate){

    if(!accidentDate) return "";

    let accident = new Date(accidentDate);
    let today = new Date();

    let diff = today - accident;

    let days = Math.floor(diff / (1000 * 60 * 60 * 24));

    return days;
}

$("#claimantSearch").keyup(function(){

    let name = $(this).val();

    if(name.trim() === ""){
        loadManagerClaims();
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

$("#claimNumberSearch").keyup(function(){

    let num = $(this).val();

    if(num.trim() === ""){
        loadManagerClaims();
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


function loadManagerClaims(){

    $.ajax({

        url: managerDashboardUrl,
        method: "GET",

        success: function(response){

            let claims = response.claims || [];

            renderTable(claims);

        }

    });

}
function renderTable(claims){

    let rows = "";

    for(let i=0;i<claims.length;i++){

        let claim = claims[i];

        let days = calculateDays(claim.accidentDate);

        rows += "<tr>";

        rows += "<td>"+claim.claimNumber+"</td>";
        rows += "<td>"+claim.accidentDate+"</td>";
        rows += "<td>"+claim.accidentAddress+"</td>";
        rows += "<td>"+claim.claimantName+"</td>";
        rows += "<td>"+claim.status+"</td>";
        rows += "<td>"+days+" days</td>";

        rows += "<td>";
        rows += "<a href='"+editClaimUrl+"?claim.claimId="+claim.claimId+"' class='btn btn-warning'>Edit</a>";
        rows += "</td>";

        rows += "</tr>";
    }

    $("#managerTable").html(rows);
}

</script>

<%@ include file="footer.jsp" %>