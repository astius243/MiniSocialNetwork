<%-- 
    Document   : admin
    Created on : Sep 22, 2020, 10:02:02 PM
    Author     : Hau Huong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
        <link href="css/bulma.css" rel="stylesheet" type="text/css"/>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.14.0/css/all.css" integrity="sha384-HzLeBuhoNPvSl5KYnjx0BT+WB0QEEqLprO+NBkkk5gbc67FTaL7XIGa2w1L0Xbgc" crossorigin="anonymous">
        <script src="js/additional-methods.js" type="text/javascript"></script>
        <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js" type="text/javascript"></script>
        <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js" type="text/javascript"></script>
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
                        <div class="navbar-item">
                            <form method="POST" action="MainController" class="search">
                                <input type="text" name="txtSearch" value="" placeholder="Search"/>
                                <button class="search-btn" type="submit" value="Search" name="action"><i class="fa fa-search fa-lg"></i></button>
                            </form>
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
        <div class="columns is-desktop is-centered" style="margin: 0 auto;">
            <div class="column is-3">
                <div class="box" id="post">
                    <form method="POST" action="MainController" id="postArticle"
                          enctype="multipart/form-data">
                        <label class="label is-medium">Title</label><input class="input is-rounded" type="text" name="txtTitle" value="" />
                        <label class="label is-medium">Description</label><textarea class="input is-rounded" name="txtDescription" rows="4" cols="20">
                        </textarea>
                        <label class="label is-medium">File</label> <input class="input is-rounded" type="file" name="fileUp" value="" />
                        <div class="has-text-centered">
                            <button class="button is-primary is-small" type="submit" value="Post" name="action" style="margin-top: 4px">Post</button>
                        </div>
                    </form>
                </div>
                <div class="box" id="noti">
                    <p>
                        <c:if test="${sessionScope.NOTI != null}">
                            <c:if test="${not empty sessionScope.NOTI}">
                                <c:forEach var="noti" items="${sessionScope.NOTI}">
                                    <c:url var="viewNoti" value="MainController">
                                        <c:param name="action" value="ViewArticle"></c:param>
                                        <c:param name="articleID" value="${noti.articleID.articleID}"></c:param>
                                    </c:url>
                                    <a class="box" href="${viewNoti}" style="margin: 0 auto;">${noti.content} at ${noti.date}</a><br/>
                                </c:forEach>
                            </c:if>
                        </c:if>
                    </p>
                </div>
            </div>
            <div id="articleList" class="column is-9 is-center">
                <c:if test="${requestScope.LIST != null}">
                    <c:if test="${not empty requestScope.LIST}">
                        <c:forEach var="dto" items="${requestScope.LIST}">   
                            <c:url var="viewArticle" value="MainController">
                                <c:param name="action" value="ViewArticle"></c:param>
                                <c:param name="articleID" value="${dto.articleID}"></c:param>
                            </c:url>
                            <div class="box has-text-centered" id="article">
                                <a href="${viewArticle}">
                                    <c:if test="${dto.image != null}">
                                        <img src="images/${dto.image}" width="460" height="345"/><br/>
                                    </c:if>
                                    <label id="date" class="label is-medium">${dto.title}</label>
                                    <label id="date" class="label is-small">Author: ${dto.email.name}</label>
                                    <label id="date" class="label is-small">${dto.date}</label>
                                    <p class="description">
                                        ${dto.description}
                                    </p>
                                </a>
                            </div>
                        </c:forEach>
                    </c:if>
                </c:if>
            </div>
        </div>
        <div class="column is-desktop has-text-centered">
            <div class="column is-three-fifths is-offset-one-fifth">
                <div style="display: flex; flex-direction: row">
                    <c:if test="${sessionScope.TOTALPAGES != null}">
                        <c:if test="${not empty sessionScope.TOTALPAGES}">
                            <c:forEach begin="1" end="${sessionScope.TOTALPAGES}" var="pageNo" step="1">
                                <form method="POST" action="MainController">
                                    <input type="hidden" name="pageNo" value="${pageNo}" /> 
                                    <c:if test="${requestScope.SEARCH==null}" var="search">
                                        <button type="submit" name="action" value="GetArticle">${pageNo}</button>
                                    </c:if>
                                    <c:if test="${!search}">
                                        <input type="hidden" name="txtSearch" value="${requestScope.SEARCH}" />
                                        <button type="submit" name="action" value="Search">${pageNo}</button>
                                    </c:if>
                                </form>
                            </c:forEach>
                        </c:if>
                    </c:if>
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
        $(function () {
            $("#postArticle").validate({
                rules: {
                    txtTitle: {
                        required: true,
                        rangelength: [5, 100],
                    },
                    txtDescription: {
                        required: true,
                        rangelength: [5, 500],
                    },
                    fileUp: {
                        accept: "bmp|png|jpg",
                    }
                },
                messages: {
                    required: "This field can't be blank",
                    txtTitle: {
                        rangelength: "Title must be 5 - 100 characters"
                    },
                    txtDescription: {
                        rangelength: "Description must be 5 - 500 characters",
                    },
                }
            });
        });
    </script>
</html>
