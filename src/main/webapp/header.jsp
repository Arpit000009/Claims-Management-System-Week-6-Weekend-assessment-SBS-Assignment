<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<title>CLAIM MANAGEMENT SYSTEM</title>
<style>
body {
    background: #f8f9fa;
    min-height: 100vh;
    display: flex;
    flex-direction: column;
}
.navbar-brand {
    font-weight: bold;
}
.footer {
    background: #212529;
    color: white;
    padding: 10px;
    text-align: center;
    margin-top: auto;
}
.card {
    border-radius: 10px;
}
.table {
    background: white;
}
.errorMessage {
    color: red !important;
    font-weight: bold;
}

.fieldError {
    color: red !important;
    font-weight: bold;
}

.fieldError label{
    color: red !important;
}

.fieldError input{
    border: 1px solid red;
}
</style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark shadow">
  <div class="container">
    <a class="navbar-brand" href="#">Insurance Claim System</a>
    <div class="ms-auto">
      <a href="logout" class="btn btn-danger btn-sm">
        <i class="bi bi-box-arrow-right"></i> Logout
      </a>
    </div>
  </div>
</nav>