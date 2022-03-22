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
        </tbody>
    </table>
</div>
</body>