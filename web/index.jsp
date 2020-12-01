<%-- 
    Document   : index
    Created on : Sep 22, 2020, 8:25:26 PM
    Author     : Hau Huong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link href="css/bulma.css" rel="stylesheet" type="text/css"/>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <script src="js/additional-methods.js" type="text/javascript"></script>
        <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js" type="text/javascript"></script>
        <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js" type="text/javascript"></script>
    </head>
    <body>
        <div id="loginBox">
            <div class="columns is-vcentered is-desktop" style="margin: 0 auto;">
                <div id="loginForm" class="column is-three-fifths is-offset-one-fifth">
                    <div class="is-centered" id="articleIndex">
                        <label class="label is-large has-text-centered">Login Page</label>
                        <form method="POST" action="MainController" id="login">
                            <label class="label is-medium">Email</label>
                            <input class="input is-rounded" type="text" name="txtEmail" value="" /><br/>
                            <label class="label is-medium">Password</label>
                            <input class="input is-rounded" type="password" name="txtPassword" value="" /><br/>
                            <div class="has-text-centered">
                                <button class="button is-primary" type="submit" value="Login" name="action" style="margin-top: 12px; margin-bottom: 10px">Login</button>
                            </div>
                        </form>
                        <div class="has-text-centered">
                            <a href="register.jsp" >Register Account</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <c:if test="${not empty requestScope.SUCCESS}">
        <script>
            window.addEventListener("load", function () {
                alert("${requestScope.SUCCESS}");
            });
        </script>
    </c:if>
    <c:if test="${not empty requestScope.FAILED}">
        <script>
            window.addEventListener("load", function () {
                alert("${requestScope.FAILED}");
            });
        </script>
    </c:if>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#login").validate()({
                rules: {
                    txtPassword: {
                        required: true,
                    },
                    txtEmail: {
                        required: true,
                        email: true,
                    },
                },
                messages: {
                    txtEmail: {
                        required: "Email can't be blank",
                        email: "Email must be in the correct format [abx@xyz.com]",
                    },
                    txtPassword: {
                        required: "Password can't be blank",
                    },
                },
            });
        });
    </script>
</html>
