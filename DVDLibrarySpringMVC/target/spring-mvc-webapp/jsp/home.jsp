<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
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
            <div class="row">
            </div>
            <div class="row">
                <div class="col-md-4">
                    <a href="DvdLibraryController/createNewDvd">
                        <button type="button" class="btn btn-defualt" id="create-dvd" onclick="showCreateDVD()">
                            Create DVD
                        </button>
                    </a>
                </div>
                <sf:form class="form-horizontal" role="form" modelAttribute="newSearch" action="${pageContext.request.contextPath}/DvdLibraryController/searchDvds" method="GET">
                    <div class="col-md-2">
                        <button type="submit" class="btn btn-defualt" id="search-dvd">
                            Search
                        </button>
                    </div>
                    <div class="col-md-2">
                        <sf:select class="selectpicker" path="searchTerm">
                            <option value="TITLE">Title</option>>
                            <option value="RELEASE_YEAR">Release Year</option>
                            <option value="DIRECTOR">Director</option>
                            <option value="RATING">Rating</option>
                        </sf:select>
                    </div>
                    <div class="col-md-4">
                        <sf:input type="text" class="form-control" placeholder="search term" path="criteria" required="true"/>
                    </div>
                </sf:form>
            </div>
            <hr>
            <ul class="list-group" id="errorMessages"></ul>
            <div class="row" id="movies-viewable">
                <div class="col-md-12">
                    <table class="table">
                        <thead>
                            <tr>
                                <th width="25%">Title</th>
                                <th width="25%">Release Date</th>
                                <th width="15%">Director</th>
                                <th width="10%">Rating</th>
                                <th width="12%"></th>
                                <th width="13%"></th>
                            </tr>
                        </thead>
                        <tbody id="movies-table">
                            <c:forEach var="currentDvd" items="#{dvdList}">
                                <tr>
                                    <td>
                                        <a href="DvdLibraryController/details?dvdId=${currentDvd.dvdId}">
                                            <c:out value="${currentDvd.title}"/>
                                        </a>
                                    </td>
                                    <td>
                                        <c:out value="${currentDvd.releaseYear}"/>
                                    </td>
                                    <td>
                                        <c:out value="${currentDvd.director}"/>
                                    </td>
                                    <td>
                                        <c:out value="${currentDvd.rating}"/>
                                    </td>
                                    <td>
                                        <a href="DvdLibraryController/editDvd?dvdId=${currentDvd.dvdId}">
                                            Edit
                                        </a>
                                    </td>
                                    <td>
                                        <a href="DvdLibraryController/removeDvd?dvdId=${currentDvd.dvdId}">
                                            Delete
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>

