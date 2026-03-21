<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ include file="header.jsp" %>

<div class="container mt-5">
    <h2>Register</h2>

    <form id="registerForm">
        <input type="text" name="user.username" placeholder="Username" class="form-control mb-2"/>
        <input type="password" name="user.password" placeholder="Password" class="form-control mb-2"/>
        <input type="text" name="user.fullName" placeholder="Full Name" class="form-control mb-2"/>
        <input type="email" name="user.email" placeholder="Email" class="form-control mb-2"/>

        <button type="submit" class="btn btn-primary">Register</button>
    </form>
</div>

<<script >
$("#registerForm").submit(function(e) {
    e.preventDefault();

    $.ajax({
        url: "register.action",
        type: "POST",
        data: $("#registerForm").serialize(),

        success: function(res) {
            if (res.success) {
                alert("Registered successfully!");
                window.location.href = "login.jsp";
            } else {
                alert(res.message);
            }
        }
    });
});
</script>

<%@ include file="footer.jsp" %>