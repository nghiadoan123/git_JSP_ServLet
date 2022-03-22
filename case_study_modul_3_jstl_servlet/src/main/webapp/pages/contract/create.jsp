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
            <form class="d-flex" action="/contract?userAction=search" method="post">
                <input class="form-control me-2" name="searchName" type="text" placeholder="Search Service " aria-label="Search">
                <button class="btn btn-outline-success" type="submit">Search</button>
            </form>
        </div>
    </div>
</nav>
<br>

<div class="container-fluid">
    <form class="col-6  m-auto" method="post">
        <div>
            <h4>Create New Contract</h4>
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
                <c:if test='${requestScope["contractId"] != null}'>
                    <span class="message">${requestScope["contractId"]}</span>
                </c:if>
            </p>
            <p class="text-danger" >
                <c:if test='${requestScope["sameId"] != null}'>
                    <span class="message">${requestScope["sameId"]}</span>
                </c:if>
            </p>
        </div>

        <div class="form-group col-12">
            <label class="col-12 float-left">Check In:</label>
            <input type="date" name="checkIn" class="form-control col-12 float-left mt-2"
                   placeholder="Enter check in">

            <p class="text-danger" >
                <c:if test='${requestScope["checkInOut"] != null}'>
                    <span class="message">${requestScope["checkInOut"]}</span>
                </c:if>
            </p>
        </div>

        <div class="form-group col-12">
            <label class="col-12 float-left">Check Out:</label>
            <input type="date" name="checkOut" class="form-control col-12 float-left mt-2"
                   placeholder="Enter check out">
            <p class="text-danger" >
                <c:if test='${requestScope["checkInOut"] != null}'>
                    <span class="message">${requestScope["checkInOut"]}</span>
                </c:if>
            </p>
        </div>

        <div class="form-group col-12">
            <label class="col-12 float-left">Deposit:</label>
            <input type="text" name="deposit" class="form-control col-12 float-left mt-2"
                   placeholder="Enter deposit">
            <p class="text-danger" >
                <c:if test='${requestScope["deposit"] != null}'>
                    <span class="message">${requestScope["deposit"]}</span>
                </c:if>
            </p>
        </div>

        <div class="form-group col-12">
            <label class="col-12 float-left">Total Money:</label>
            <input type="text" name="totalMoney" class="form-control col-12 float-left mt-2"
                   placeholder="Enter total money">
            <p class="text-danger" >
                <c:if test='${requestScope["totalMoney"] != null}'>
                    <span class="message">${requestScope["totalMoney"]}</span>
                </c:if>
            </p>
        </div>


        <div class="form-group col-12">
            <label class="col-12 float-left mt-1">Customer:</label>
            <select name="customer" class="form-control col-12 float-left">
                <option>Chose option ...</option>
                <c:forEach var="customer" items="${customerList}">
                    <option value="${customer.id}">${customer.name}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group col-12">
            <label class="col-12 float-left mt-1">Employee:</label>
            <select name="employee" class="form-control col-12 float-left">
                <option>Chose option ...</option>
                <c:forEach var="employee" items="${employeeList}">
                    <option value="${employee.id}">${employee.id}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group col-12">
            <label class="col-12 float-left mt-1">Service:</label>
            <select name="service" class="form-control col-12 float-left">
                <option>Chose option ...</option>
                <c:forEach var="service" items="${serviceList}">
                    <option value="${service.id}">${service.name}</option>
                </c:forEach>
            </select>
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
