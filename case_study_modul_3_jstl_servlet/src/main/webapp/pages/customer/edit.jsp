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
            <form class="d-flex" action="/employee?userAction=search" method="post">
                <input class="form-control me-2" name="searchName" type="text" placeholder="Search Employee Name" aria-label="Search">
                <button class="btn btn-outline-success" type="submit">Search</button>
            </form>
        </div>
    </div>
</nav>
<br>

<div class="container-fluid">
    <form class="col-6  m-auto" method="post">
        <div>
            <h4>Edit customer</h4>
        </div>
        <div class="form-group col-12">
            <label class="col-12 float-left">Id:</label>
            <input type="text" name="id" class="form-control col-12 float-left mt-2"
                   value="${customer.getId()}" readonly >
        </div>
        <div class="form-group col-12">
            <label class="col-12 float-left">Name:</label>
            <input type="text" name="name" class="form-control col-12 float-left mt-2"
                   value="${customer.getName()}">
        </div>

        <div class="form-group col-12">
            <label class="col-12 float-left">Birth day:</label>
            <input type="date" name="birthday" class="form-control col-12 float-left mt-2"
                   value="${customer.getBirthDay()}">
        </div>

        <div class="form-group col-12">
            <label class="col-12 float-left">Gender:</label>
<%--            <input type="text" name="gender" class="form-control col-12 float-left mt-2"--%>
<%--                   value="${customer.getGender()}">--%>

            <c:set var = "gender" scope = "session" value = "${customer.getGender()}"/>
            <c:if test='${"gender" == null or "gender"!= null}'>
                <select name="gender" class="form-control col-12 float-left">
                    <option value="Male">Male</option>
                    <option value="Female">Female</option>
                </select>
            </c:if>
        </div>


        <div class="form-group col-12">
            <label class="col-12 float-left">Id card:</label>
            <input type="text" name="id_card" class="form-control col-12 float-left mt-2"
                   value="${customer.getIdCard()}">
            <p class="text-danger" >
                <c:if test='${requestScope["personalIDMess"]!= null}'>
                    <span class="message">${requestScope["personalIDMess"]}</span>
                </c:if>
            </p>
        </div>

        <div class="form-group col-12">
            <label class="col-12 float-left">Phone:</label>
            <input type="text" name="phone" class="form-control col-12 float-left mt-2"
                   value="${customer.getPhone()}">
            <p class="text-danger" >
                <c:if test='${requestScope["phoneNumberMess"]!= null}'>
                    <span class="message">${requestScope["phoneNumberMess"]}</span>
                </c:if>
            </p>
        </div>

        <div class="form-group col-12">
            <label class="col-12 float-left">Email:</label>
            <input type="text" name="email" class="form-control col-12 float-left mt-2"
                   value="${customer.getEmail()}">
            <p class="text-danger" >
                <c:if test='${requestScope["emailMess"]!= null}'>
                    <span class="message">${requestScope["emailMess"]}</span>
                </c:if>
            </p>
        </div>

        <div class="form-group col-12">
            <label class="col-12 float-left">Address:</label>
            <input type="text" name="address" class="form-control col-12 float-left mt-2"
                   value="${customer.getAddress()}" >
        </div>
        <div class="form-group col-12">
            <label class="col-12 float-left mt-1">Customer Type:</label>
            <select name="customerType" class="form-control col-12 float-left">
                <option>Chose option ...</option>
                <c:forEach var="customerType" items="${customerTypeList}">
                    <c:choose>
                        <c:when test="${customerType.id == customer.getCustomerType().getId()}">
                            <option value="${customerType.id}" selected> ${customerType.name}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${customerType.id}">${customerType.name}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
        </div>
        <div class="col-12 " style="padding: 2% 0%">
            <button type="submit" class="btn btn-primary float-right">Edit</button>
        </div>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
</body>
</html>
