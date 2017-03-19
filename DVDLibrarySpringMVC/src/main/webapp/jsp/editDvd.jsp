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
            <hr>
            <ul class="list-group" id="errorMessages"></ul>
            <div class="row" id="edit-dvd-form">
                <div class="col-md-12">
                    <h1>Edit DVD</h1>
                    <hr>
                    <ul class="list-group" id="errorMessages">

                    </ul>
                    <sf:form class="form-horizontal" role="form" modelAttribute="oldDvd" action="addEditedDvd" method="POST">
                        <div class="form-group">
                            <label for="add-title" class="col-md-3 control-label text-left">DVD Title:</label>
                            <div class="col-md-6">
                                <sf:input type="text" class="form-control" id="edit-dvd-title" path="title" placeholder="title" required="true"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="edit-releasedate" class="col-md-3 control-label text-left">Release Year:</label>
                            <div class="col-md-6">
                                <sf:input type="number" class="form-control" id="edit-dvd-date" path="releaseYear" placeholder="release year" required="true"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="edit-director" class="col-md-3 control-label text-left">Director:</label>
                            <div class="col-md-6">
                                <sf:input type="text" class="form-control" id="edit-dvd-director" path="director" placeholder="director" required="true"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="edit-rating" class="col-md-3 control-label text-left">Rating:</label>
                            <div class="col-md-6">
                                <sf:select class="selectpicker" id="edit-dvd-rating" path="rating">
                                    <sf:option value="G">G</sf:option>
                                    <sf:option value="PG">PG</sf:option>
                                    <sf:option value="PG-13">PG-13</sf:option>
                                    <sf:option value="R">R</sf:option>
                                    <sf:option value="X">X</sf:option>
                                </sf:select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="edit-notes" class="col-md-3 control-label text-left">Notes:</label>
                            <div class="col-md-6">
                                <sf:textarea type="text" class="form-control" rows="5" id="edit-dvd-notes" path="notes" placeholder="notes" required="true"></sf:textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-2">
                                <a href="${pageContext.request.contextPath}/">
                                    <button type="button" id="cancel" class="btn btn-default">
                                        Cancel
                                    </button>
                                </a>
                            </div>
                            <div class="col-md-2">
                                <input type="hidden" id="edit-dvd-id"/>
                                <sf:hidden path="dvdId"/>
                                <button type="submit" id="edit-button" class="btn btn-default" >
                                    Save Changes
                                </button>
                            </div>
                        </div>
                    </sf:form>
                </div>
            </div>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>