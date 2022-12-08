<!DOCTYPE html> <%--s--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Пользователь</title>
</head>
<body>

<c:set scope="request" var="id" value="${requestScope.user.id}"/>

<c:set var="action" value="${pageContext.request.contextPath.concat(requestScope.pageMode == 'edit' ?
'/editUser?id='.concat(param.id) : '/registerUser')}"/>

<form action="${action}" method="post">
  <label for="login">Имя пользователя:</label><br>

  <c:set var="login" value="${requestScope.user.login != null ? requestScope.user.login : param.login}"/>

  <input maxlength="100" type="text" id="login" name="login" value="${fn:escapeXml(login)}"><br><br>

  <label for="password">Пароль:</label><br>

  <c:set var="password" value="${requestScope.user.password != null ? requestScope.user.password : param.password}"/>

  <input type="text" id="password" name="password" value="${fn:escapeXml(password)}"><br><br>

  <input type="submit" value="Сохранить">
</form>
</body>
</html>
