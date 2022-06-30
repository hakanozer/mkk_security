<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <div class="row">
    <div class="col-sm-4"></div>
        <div class="col-sm-4">
            <h2>User Login</h2>
            ${ email }
            <f:form method="post" modelAttribute="userModel" action="/login">
                <div class="mb-3">
                    <label for="exampleInputEmail1" class="form-label">Email address</label>
                    <f:input path="email" required="true" name="email" type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp"></f:input>
                    <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
                    <f:errors class="invalid" path="email"></f:errors>
                </div>
                <div class="mb-3">
                    <label for="exampleInputPassword1" class="form-label">Password</label>
                    <f:input path="password" required="true" name="password" type="password" class="form-control" id="exampleInputPassword1"></f:input>
                    <f:errors class="invalid" path="password"></f:errors>
                </div>
                <div class="mb-3 form-check">
                    <input name="remember" type="checkbox" class="form-check-input" id="exampleCheck1">
                    <label class="form-check-label" for="exampleCheck1">Check me out</label>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </f:form>
        </div>
        <div class="col-sm-4"></div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
</body>
</html>