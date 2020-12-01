<%-- 
    Document   : verify.jsp
    Created on : Sep 28, 2020, 11:20:12 PM
    Author     : Hau Huong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Verification Page</title>
        <link href="css/bulma.css" rel="stylesheet" type="text/css"/>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <header>
            <nav class="navbar is-info" id="navbar">
                <div class="navbar-menu">
                    <div class="navbar-start ">
                        <div class="navbar-item">
                            <c:url var="GetArticle" value="MainController">
                                <c:param name="action" value="GetArticle"/>
                            </c:url>
                            <a href="${GetArticle}">HOME</a>
                        </div>
                    </div>
                    <div class="navbar-end">
                        <div class="navbar-item">
                            <c:url var="Verify" value="verify.jsp">
                            </c:url>
                            <a href="${Verify}">VERIFY</a>
                        </div>
                        <div class="navbar-item">
                            <c:url var="Logout" value="MainController">
                                <c:param name="action" value="Logout"/>
                            </c:url>
                            <a href="${Logout}">LOG OUT</a>
                        </div>
                    </div>
                </div>
            </nav>
        </header>
        <div id="verificationBox">
            <div id="verificationForm" class="column is-three-fifths is-offset-one-fifth is-centered has-text-centered">
                <label class="label is-large">Input captcha Code</label>
                <form method="POST" action="MainController">
                    <label class="label is-medium">Verification code</label> 
                    <c:if test="${sessionScope.USER.status.statusID==1}" var="status">
                        <input class="input is-rounded has-text-centered" type="text" name="txtCode" value="" />
                        <div class="has-text-centered">
                            <button class="button is-primary" type="submit" value="Verify" name="action" style="margin-top: 10px">Verify</button>
                        </div>
                    </c:if> 
                    <c:if test="${!status}">
                        <input class="input is-rounded has-text-centered" type="text" name="txtCode" value="Your Account is verified" readonly="true" />
                        <div class="has-text-centered">
                            <button class="button is-primary" type="submit" value="Verify" name="action" style="margin-top: 10px" disabled="true">Verify</button>
                        </div>
                    </c:if>
                </form>
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
</html>
