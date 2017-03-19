<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Index Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/dvd.css" type="text/css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <ul class="list-group" id="errorMessages"></ul>
            <div class="row" id="display-dvd-form">
                <div class="col-md-12">
                    <h1 id="display-title"><c:out value="${dvd.title}"/></h1>
                    <hr>
                    <div class="row">
                        <div class="col-md-3">
                            <h4>Release Year:</h4>
                        </div>
                        <div class="col-md-9">
                            <h4 id="display-releasedate"><c:out value="${dvd.releaseYear}"/></h4>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-3">
                            <h4>Director:</h4>
                        </div>
                        <div class="col-md-9">
                            <h4 id="display-director"><c:out value="${dvd.director}"/></h4>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-3">
                            <h4>Rating:</h4>
                        </div>
                        <div class="col-md-9">
                            <h4 id="display-rating"><c:out value="${dvd.rating}"/></h4>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-3">
                            <h4>Notes:</h4>
                        </div>
                        <div class="col-md-9">
                            <h4 id="display-notes"><c:out value="${dvd.notes}"/></h4>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-2">
                            <a href="${pageContext.request.contextPath}/">
                                <button type="button" id="back-button" class="btn btn-default">
                                    Back
                                </button>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
