<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <%--    <link rel="stylesheet" href="/css/bootstrap.min.css">--%>
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
                <td><c:out value="${usernameinfo}"/></td>
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
            <form class="d-flex">
                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success" type="submit">Search</button>
            </form>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-lg-3">
            <div class="row">
                <div class="col-lg-12">
                    <p class="text-lg-start fs-6">Loại hình nơi ở</p>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <p class="text-lg-start fs-6">Homestay</p>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <p class="text-lg-start fs-6">Toàn bộ căn hộ (433)</p>
                </div>
            </div>
            <hr>

            <div class="row">
                <div class="col-lg-12">
                    <p class="text-lg-start fs-6">Khoảng cách đến trung tâm</p>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <p class="text-lg-start fs-6">Bên trong trung tâm thành phố (10)</p>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <p class="text-lg-start fs-6">cách trung tâm <2 km (141)</p>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <p class="text-lg-start fs-6">cách trung tâm 2-5 km (265)</p>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <p class="text-lg-start fs-6">cách trung tâm 5-10 km (25)</p>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <p class="text-lg-start fs-6">cách trung tâm >10 km (34)</p>
                </div>
            </div>
            <hr>

            <div class="row">
                <div class="col-lg-12">
                    <p class="text-lg-start fs-6">Làm gì ở địa bàn này</p>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <p class="text-lg-start fs-6">Thắng cảnh (462)</p>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <p class="text-lg-start fs-6">Bãi biển (401)</p>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <p class="text-lg-start fs-6">Ăn uống (66)</p>
                </div>
            </div>
            <hr>


            <div class="row">
                <div class="col-lg-12">
                    <p class="text-lg-start fs-6">Danh lam thắng cảnh lân cận</p>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <p class="text-lg-start fs-6">My Khe Beach (121)</p>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <p class="text-lg-start fs-6">Cầu sông Hàn (21)</p>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <p class="text-lg-start fs-6">Dragon Bridge (37)</p>
                </div>
            </div>
            <hr>

            <div class="row">
                <div class="col-lg-12">
                    <p class="text-lg-start fs-6">Bãi biển lân cận</p>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <p class="text-lg-start fs-6">Bãi biển công cộng (121)</p>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <p class="text-lg-start fs-6">Bãi biển riêng (16)</p>
                </div>
            </div>

            <hr>
        </div>

        <div class="col-lg-9">
            <div class="row ">
                <div class="col-lg-12 text-lg-start">
                    <img src="../../image/logo.jpg" alt="" width="80%" height="80%">
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12 text-lg-start">
                    <img src="../../image/logo.jpg" alt="" width="80%" height="80%">
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12 text-lg-start">
                    <img src="../../image/logo.jpg" alt="" width="80%" height="80%">
                </div>
            </div>
        </div>
    </div>
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
<%--<script src="/js/bootstrap.min.js"></script>--%>
</body>
</html>
