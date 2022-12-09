<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit-no">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://bootstrap-4.ru/docs/5.2/assets/css/docs.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>

  <title>Пользователь</title>
</head>
<body class="p-3 m-5 border-0 bd-example row-centered container mx-auto">

<c:set scope="request" var="id" value="${requestScope.user.id}"/>

<c:set var="action" value="${pageContext.request.contextPath.concat(requestScope.pageMode == 'edit' ?
'/editUser?id='.concat(param.id) : '/registerUser')}"/>

<form action="${action}" method="post">
  <div class="p-1 m-3 border-0 form-group row" >
    <label class="col-sm-2 col-form-label p-0">Имя пользователя:</label><br>
    <div class="col-sm-3 ">
      <c:set var="login" value="${requestScope.user.login != null ? requestScope.user.login : param.login}"/>
      <input maxlength="100" type="text" id="login" name="login" value="${fn:escapeXml(login)}" class="form-control"><br><br>
    </div>
  </div>

  <div class="p-1 m-3 border-0 row form-group">
    <div class="col-form-label col-sm-2">
    <label class ="col-form-label">Пароль:</label><br>
    </div>
    <div class="col-sm-3">
      <c:set var="password" value="${requestScope.user.password != null ? requestScope.user.password : param.password}"/>
      <input type="text" id="inputPassword6" name="password" value="${fn:escapeXml(password)}" class="form-control"><br><br>
    </div>
  </div>

  <input type="submit" class="btn btn-outline-primary col-sm-1 mx-auto text-truncate" value="Сохранить">
  <%--<input type="submit" value="Сохранить">--%>
</form>
</body>
</html>
