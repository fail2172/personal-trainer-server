<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit-no">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://bootstrap-4.ru/docs/5.2/assets/css/docs.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>

    <title>Вход</title>
</head>
<header>

</header>
<body class="p-3 m-5 border-0 bd-example row-centered container mx-auto">
<form action="${pageContext.request.contextPath.concat('/LogIn')}" method="post">
    <div class="p-1 m-3 border-0 form-group row">
        <label class="col-sm-2 col-form-label p-0" for="login">Имя пользователя:</label><br>
        <div class="col-sm-3 ">
            <c:set var="login" value="${requestScope.user.login != null ? requestScope.user.login : param.login}"/>
            <input maxlength="100" type="text" id="login" name="login" value="${fn:escapeXml(login)}"
                   class="form-control"><br><br>
        </div>
    </div>
    <div class="p-1 m-3 border-0 row form-group">
        <div class="col-form-label col-sm-2">
            <label class="col-form-label" for="inputPassword6">Пароль:</label><br>
        </div>
        <div class="col-sm-3">
            <c:set var="password"
                   value="${requestScope.user.password != null ? requestScope.user.password : param.password}"/>
            <input type="password" id="inputPassword6" name="password" value="${fn:escapeXml(password)}"
                   class="form-control"><br><br>
        </div>
    </div>
    <div class="btn-group">
        <input type="submit" class="btn btn-outline-primary col-sm-1 mx-auto text-truncate" value="Войти">
        <div class="btn-group dropend">
            <button type="button" class="btn btn-secondary dropdown-toggle" data-bs-toggle="dropdown"
                    aria-expanded="false">
                Впервые здесь?
            </button>
            <ul class="dropdown-menu">
                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/registerUser">Зарегистрироваться</a></li>
            </ul>
        </div>
    </div>
</form>
</body>
</html>
