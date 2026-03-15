<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ include file="header.jsp" %>

<div class="container mt-5">

  <h2>Dashboard</h2>

  <div class="d-flex gap-2">

    <a href="createClaimPage" class="btn btn-primary">
      <i class="bi bi-plus-circle"></i> Create Claim
    </a>

    <a href="listClaimsPage" class="btn btn-info">
      <i class="bi bi-list-ul"></i> View Claims
    </a>

  </div>

</div>

<%@ include file="footer.jsp" %>