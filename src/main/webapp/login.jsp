<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Login</title>

<link rel="stylesheet"
href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

<style>
body {
    background: #f8f9fa;
}
.footer {
    background: #212529;
    color: white;
    padding: 10px;
    text-align: center;
    margin-top: 50px;
}
</style>

</head>

<body>

<nav class="navbar navbar-dark bg-dark shadow">
  <div class="container">
    <a class="navbar-brand fw-bold" href="#"> CLAIM MANAGEMENT SYSTEM</a>
  </div>
</nav>

<div class="container mt-5">

  <div class="row justify-content-center">
    <div class="col-md-4">
      <div class="card shadow p-4">

        <h2 class="mb-3">Login</h2>

        <div id="errorMsg" class="alert alert-danger d-none"></div>

        <div class="mb-3">
          <label>Username</label>
          <input type="text" id="username" class="form-control" placeholder="Enter username">
        </div>

        <div class="mb-3">
          <label>Password</label>
          <input type="password" id="password" class="form-control" placeholder="Enter password">
        </div>

        <button id="loginBtn" class="btn btn-primary w-100">Login</button>

        <br><br>
        <a href="register.jsp">Register</a>

      </div>
    </div>
  </div>

</div>

<footer class="footer">
  <div class="container">
    <p class="mb-0">© 2026 Insurance Claim Management System</p>
  </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

<script>

$("#loginBtn").click(function(){

    let username = $("#username").val().trim();
    let password = $("#password").val().trim();

    if(username === "" || password === ""){
        $("#errorMsg").text("Username and password are required").removeClass("d-none");
        return;
    }

    $.ajax({

        url: "login",
        method: "POST",
        data: {
            username: username,
            password: password
        },

        success: function(response){
            window.location.href = "checkRole";
        },

        error: function(){
            $("#errorMsg").text("Invalid username or password").removeClass("d-none");
        }

    });

});

</script>

</body>
</html>