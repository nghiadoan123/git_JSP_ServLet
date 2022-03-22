<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
</head>
<body>
<div class="container-fluid bg-light">
    <div class="row bg-light">
        <div class="col-lg-6 text-left">
            <img src="../../image/logo.jpg" alt="" width="15%" height="100%">
            <h6>Fumara Resort</h6>
        </div>
        <div class="col-lg-6 text-end pt-4">
            <tr>
                <td ><c:out value="${usernameinfo}"/></td>
            </tr>
            <%--            <input type="text" disabled value="${usernameinfo}" name="username">--%>
            <%--            <h5> Nguyễn Văn A </h5>--%>

        </div>
    </div>
</div>
<br>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/home">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/employee">Employee</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="/customer">Customer</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="/service">Service</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="/contract">Contract</a>
                </li>
            </ul>
            <form class="d-flex" action="/service?userAction=search" method="post">
                <input class="form-control me-2" name="searchName" type="text" placeholder="Search Service Name" aria-label="Search">
                <button class="btn btn-outline-success" type="submit">Search</button>
            </form>
        </div>
    </div>
</nav>
<br>

<div class="container-fluid">
    <form class="col-6  m-auto" method="post">
        <div>
            <h4>Create New Service</h4>
        </div>
        <p class="text-danger" >
            <c:if test='${requestScope["empty"] != null}'>
                <span class="message">${requestScope["empty"]}</span>
            </c:if>
        </p>
        <div class="form-group col-12">
            <label class="col-12 float-left">Id:</label>
            <input type="text" name="id" class="form-control col-12 float-left mt-2"
                   placeholder="Enter Id" >
            <p class="text-danger" >
                <c:if test='${requestScope["serviceId"] != null}'>
                    <span class="message">${requestScope["serviceId"]}</span>
                </c:if>
            </p>
            <p class="text-danger" >
                <c:if test='${requestScope["sameId"] != null}'>
                    <span class="message">${requestScope["sameId"]}</span>
                </c:if>
            </p>
        </div>

        <div class="form-group col-12">
            <label class="col-12 float-left">Name:</label>
            <input type="text" name="name" class="form-control col-12 float-left mt-2"
                   placeholder="Enter Name">
        </div>

        <div class="form-group col-12">
            <label class="col-12 float-left">Area:</label>
            <input type="text" name="area" class="form-control col-12 float-left mt-2"
                   placeholder="Enter area">
            <p class="text-danger" >
                <c:if test='${requestScope["area"] != null}'>
                    <span class="message">${requestScope["area"]}</span>
                </c:if>
            </p>
        </div>

        <div class="form-group col-12">
            <label class="col-12 float-left">Cost:</label>
            <input type="text" name="cost" class="form-control col-12 float-left mt-2"
                   placeholder="Enter cost">
            <p class="text-danger" >
                <c:if test='${requestScope["cost"] != null}'>
                    <span class="message">${requestScope["cost"]}</span>
                </c:if>
            </p>
        </div>

        <div class="form-group col-12">
            <label class="col-12 float-left">Number Of Person:</label>
            <input type="text" name="numberOfPerson" class="form-control col-12 float-left mt-2"
                   placeholder="Enter number of person">
            <p class="text-danger" >
                <c:if test='${requestScope["numberOfPerson"] != null}'>
                    <span class="message">${requestScope["numberOfPerson"]}</span>
                </c:if>
            </p>
        </div>


        <div class="form-group col-12">
            <label class="col-12 float-left mt-1">Rental Type:</label>
            <select name="rentalType" class="form-control col-12 float-left">
                <option>Chose option ...</option>
                <c:forEach var="rentalType" items="${rentalTypeList}">
                    <option value="${rentalType.id}">${rentalType.name}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group col-12">
            <label class="col-12 float-left mt-1">Service Type:</label>
            <select name="serviceType" class="form-control col-12 float-left">
                <option>Chose option ...</option>
                <c:forEach var="serviceType" items="${serviceTypeList}">
                    <option value="${serviceType.id}">${serviceType.name}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group col-12">
            <label class="col-12 float-left">Standard Room:</label>
            <input type="text" name="standardRoom" class="form-control col-12 float-left mt-2"
                   placeholder="Enter standard room">

        </div>

        <div class="form-group col-12">
            <label class="col-12 float-left">Description:</label>
            <input type="text" name="description" class="form-control col-12 float-left mt-2"
                   placeholder="Enter Description">

        </div>

        <div class="form-group col-12">
            <label class="col-12 float-left">Pool Area:</label>
            <input type="text" name="poolArea" class="form-control col-12 float-left mt-2"
                   placeholder="Enter Pool Area">
            <p class="text-danger" >
                <c:if test='${requestScope["poolArea"] != null}'>
                    <span class="message">${requestScope["poolArea"]}</span>
                </c:if>
            </p>
        </div>

        <div class="form-group col-12">
            <label class="col-12 float-left">Number Of Floor:</label>
            <input type="text" name="numberOfFloor" class="form-control col-12 float-left mt-2"
                   placeholder="Enter Number Of Floor">
            <p class="text-danger" >
                <c:if test='${requestScope["numberOfFloor"] != null}'>
                    <span class="message">${requestScope["numberOfFloor"]}</span>
                </c:if>
            </p>
        </div>


        <div class="col-12 " style="padding: 2% 0%">
            <button type="submit" class="btn btn-primary float-right">Create</button>
        </div>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
</body>
</html>
