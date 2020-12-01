<%-- 
    Document   : register
    Created on : Sep 22, 2020, 10:09:23 PM
    Author     : Hau Huong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Account Page</title>
        <link href="css/bulma.css" rel="stylesheet" type="text/css"/>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <script src="js/additional-methods.js" type="text/javascript"></script>
        <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js" type="text/javascript"></script>
        <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js" type="text/javascript"></script>
    </head>
    <body>  
        <div id="registerBox">
            <div id="registerForm" class="column is-three-fifths is-offset-one-fifth is-centered has-text-centered">
                <label class="label is-large">Register Account</label>
                <form method="POST" action="MainController" id="register">
                    <label class="label is-medium">Email</label> 
                    <input class="input is-rounded" type="text" name="txtEmail" value="" /><br/>
                    <label class="label is-medium">Name:</label> 
                    <input class="input is-rounded" type="text" name="txtName" value="" /><br/>
                    <label class="label is-medium">Password:</label> 
                    <input class="input is-rounded" type="password" name="txtPassword" value="" id="password"/><br/>
                    <label class="label is-medium">Confirm:</label> 
                    <input class="input is-rounded" type="password" name="txtConfirm" value="" /><br/>
                    <button class="button is-primary" type="submit" value="Register" name="action" style="margin-top: 12px; margin-bottom: 10px">Register</button>
                </form>
                <a href="index.jsp">Log In</a>
            </div>
        </div>
    </body>
    <c:if test="${not empty requestScope.FAILED}">
        <script>
            window.addEventListener("load", function () {
                alert("${requestScope.FAILED}");
            });
        </script>
    </c:if>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#register").validate({
                rules: {
                    txtEmail: {
                        required: true,
                        email: true,
                    },
                    txtName: {
                        required: true,
                    },
                    txtPassword: {
                        required: true,
                        rangelength: [8, 30],
                    },
                    txtConfirm: {
                        equalTo: "#password",
                    },
                },
                messages: {
                    txtEmail: {
                        required: "Email can't be blank",
                        email: "Email must be in the correct format [abc@xyz.com]",
                    },
                    txtName: {
                        required: "Name can't be blank",
                    },
                    txtPassword: {
                        required: "Password can't be blank",
                        rangelength: "Password must be from 8 - 30 characters",
                    },
                    txtConfirm: {
                        equalTo: "Confirm must match Password"
                    },
                }
            })
        });
    </script>
</html>
