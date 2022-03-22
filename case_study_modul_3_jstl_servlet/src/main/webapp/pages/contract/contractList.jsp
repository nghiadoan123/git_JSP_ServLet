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
                <input class="form-control me-2" name="searchName" type="text" placeholder="Search Contract Name" aria-label="Search">
                <button class="btn btn-outline-success" type="submit">Search</button>
            </form>
        </div>
    </div>
</nav>
<br>
<div class="container-fluid ">
    <div class="row ">
        <div class="col-lg-12 text-left">
            <button type="button" class="btn btn-light">
                <a href="/contract?userAction=create" class="text-decoration-none">Create contract</a>
            </button>
        </div>
    </div>
</div>

<%--<form action="/employee?userAction=search" method="get">--%>
<%--    <table>--%>
<%--        <tr>--%>
<%--            <td><input type="text" name="searchName" id="name" placeholder="Enter name "></td>--%>
<%--            <td><button type="submit">Search</button></td>--%>
<%--        </tr>--%>
<%--    </table>--%>
<%--</form>--%>


<div>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Check In</th>
            <th scope="col">Check Out</th>
            <th scope="col">Deposit</th>
            <th scope="col">Total Money</th>
            <th scope="col">Customer Id</th>
            <th scope="col">Employee Id</th>
            <th scope="col">Service Id</th>
            <th scope="col">Edit</th>
            <th scope="col">Delete</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach var="contract" items="${contractList}">
            <tr>
                <td>${contract.id}</td>
                <td>${contract.checkIn}</td>
                <td>${contract.checkOut}</td>
                <td>${contract.deposit}</td>
                <td>${contract.totalMoney}</td>
                <td>${contract.customer.id}</td>
                <td>${contract.employee.id}</td>
                <td>${contract.service.id}</td>
                <td>
                    <button type="button" class="btn btn-light">
                        <a href="/contract?userAction=edit&id=${contract.getId()}"
                           class="text-decoration-none" onclick="return confirm('Do you want to edit ${contract.getId()} ?')">Edit</a>
                    </button>

                </td>
                <td>
                    <button type="button" class="btn btn-light">
                        <a href="/contract?userAction=delete&id=${contract.getId()}"
                           class="text-decoration-none"  onclick="return confirm('Do you want to delete ${contract.getId()} ?')">Delete</a>
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<br>
<div class="container-fluid">
    <div class="row">
        <div class="col-lg-12 bg-black">
            <br>
            <p class="text-light fs-6">Features and app availability may vary by region.</p>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
</body>
</html>
