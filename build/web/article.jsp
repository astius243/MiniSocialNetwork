<%-- 
    Document   : view
    Created on : Sep 23, 2020, 6:16:01 PM
    Author     : Hau Huong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Article Page</title>
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
                                <c:param name="action" value="Back"/>
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
        <c:if test="${sessionScope.ARTICLE.statusID.statusID!=3}" var="check">
            <div class="columns is-desktop" style="margin: 0 auto;" id="mainArticle">
                <c:if test="${sessionScope.ARTICLE.image!=null}">
                    <c:if test="${not empty sessionScope.ARTICLE.image}">
                        <div class="column is-8">
                            <img src="images/${sessionScope.ARTICLE.image}">
                        </div>
                    </c:if>
                </c:if>
                <div class=" box column is-4" id="articlePost">
                    <label class="label is-large has-text-centered">${sessionScope.ARTICLE.title}</label>
                    <nav>
                        <div class="navbar-menu">
                            <div class="navbar-start">
                                <label class="label is-medium"> ${sessionScope.ARTICLE.email.name}</label>
                            </div>
                            <div class="navbar-end">
                                <label class="label is-medium"> ${sessionScope.ARTICLE.date}</label>
                            </div>
                        </div>
                    </nav>
                    <div class="label is-medium">
                        Content: ${sessionScope.ARTICLE.description}
                    </div>
                    <nav>
                        <div class="navbar-menu">
                            <div class="navbar-start">
                                <c:if test="${sessionScope.LIKES!=null}">
                                    ${sessionScope.LIKES} Likes
                                </c:if>
                                <c:if test="${sessionScope.DISLIKES!=null}">
                                    ${sessionScope.DISLIKES} Dislikes
                                </c:if>
                            </div>
                            <div class="navbar-end">
                                <c:if test="${(sessionScope.ARTICLE.email eq sessionScope.USER) || (sessionScope.USER.role eq 'Admin')}">
                                    <c:url var="DeleteArticle" value="MainController">
                                        <c:param name="action" value="DeleteArticle"></c:param>
                                    </c:url>
                                    <a href="${DeleteArticle}" class="has-text-right" id="deleteArticle"><i class="fas fa-times"></i></a>
                                    </c:if>
                            </div>
                        </div>
                    </nav>
                    <nav class="navbar is-info" id="navbar">
                        <div class="navbar-menu">
                            <div class="navbar-start">
                                <div class="navbar-item">
                                    <c:url var="Like" value="MainController">
                                        <c:param name="action" value="Emotion"></c:param>
                                        <c:param name="emotion" value="like"></c:param>
                                    </c:url>
                                    <a href="${Like}">Like</a>
                                </div>
                                <div class="navbar-item">
                                    <c:url var="Dislike" value="MainController">
                                        <c:param name="action" value="Emotion"></c:param>
                                        <c:param name="emotion" value="dislike"></c:param>
                                    </c:url>
                                    <a href="${Dislike}">Dislike</a>
                                </div>
                            </div>
                        </div>
                    </nav>

                    <c:if test="${sessionScope.COMMENTS!=null}">
                        <c:if test="${not empty sessionScope.COMMENTS}">
                            <div id="list">
                                <c:forEach var="comment" items="${sessionScope.COMMENTS}">
                                    <div class="box">
                                        ${comment.email.name}
                                        at ${comment.date}<br/>
                                        ${comment.content}
                                        <c:if test="${comment.email eq sessionScope.USER}">
                                            <c:url var="DeleteComment" value="MainController">
                                                <c:param name="action" value="DeleteComment"></c:param>
                                                <c:param name="commentID" value="${comment.commentID}"></c:param>
                                            </c:url>
                                            <a href="${DeleteComment}" id="deleteComment">Delete</a>
                                        </c:if>
                                    </div>
                                </c:forEach>
                            </div>
                        </c:if>
                    </c:if>
                    <form method="POST" action="MainController">
                        <label class="label is-medium" style="margin-top: 10px;">${sessionScope.USER.name}</label>
                        <textarea class="input is-rounded" name="txtContent" rows="4" cols="20">
                        </textarea>
                        <button class="button is-primary" type="submit" value="Comment" name="action" style="margin-top: 10px;">Comment</button>
                    </form>
                </div>
            </div>
        </c:if>
        <c:if test="${!check}">
            <div id="notFoundBox">
                <div class="columns is-vcentered is-desktop" style="margin: 0 auto;">
                    <div id="notFoundForm" class="column is-three-fifths is-offset-one-fifth has-text-centered">
                        <label class="label is-large">Post Not Found</label>
                    </div>
                </div>
            </div>
        </c:if>
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
            $("#deleteComment").click(function (event) {
                if (!confirm("Do you want to delete this comment")) {
                    event.preventDefault();
                }
            });
            $("#deleteArticle").click(function (event) {
                if (!confirm("Do you want to delete this article")) {
                    event.preventDefault();
                }
            });
        });
    </script>

</html>
