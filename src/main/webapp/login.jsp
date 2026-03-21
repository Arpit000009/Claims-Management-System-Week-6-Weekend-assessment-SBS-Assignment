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

<!-- NAVBAR (unchanged) -->
<nav class="navbar navbar-dark bg-dark shadow">
  <div class="container">
    <a class="navbar-brand fw-bold" href="#"> CLAIM MANAGEMENT SYSTEM</a>
  </div>
</nav>

<!-- LOGIN UI (ONLY DESIGN CHANGED) -->
<div class="container mt-5">
  <div class="row justify-content-center">
    <div class="col-md-5">
      <div class="card shadow p-4">

        <h2 class="mb-4 text-center">Login</h2>

        <form id="loginForm">

            <div class="mb-3">
                <label class="form-label">Username</label>
                <input name="user.username" id="username" class="form-control">
            </div>

            <div class="mb-3">
                <label class="form-label">Password</label>
                <input type="password" name="user.password" id="password" class="form-control">
            </div>

            <button type="submit" class="btn btn-primary w-100">Login</button>

        </form>

      </div>
    </div>
  </div>
</div>

<!-- FOOTER (unchanged) -->
<footer class="footer">
  <div class="container">
    <p class="mb-0">© 2026 Insurance Claim Management System</p>
  </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

<!--  ORIGINAL SCRIPT KEPT EXACTLY SAME -->
<script>

$("#loginForm").submit(function(e){
    e.preventDefault(); // stop normal submit

    $.ajax({
        url: "login.action",
        type: "POST",
        data: $(this).serialize(), //  best practice
        success: function(){
            window.location.href = "checkRole.action";
        }
    });
});

</script>

</body>
</html>