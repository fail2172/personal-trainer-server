<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
  <title>Вход</title>
</head>
<header>

</header>
<body>
<form action="${pageContext.request.contextPath.concat('/LogIn')}" method="post">
  <label for="login">Логин</label><br>

  <input type="text" id="login" name="login" value="${fn:escapeXml(param.login)}"><br><br>

  <label for="password">Пароль</label><br>

  <input type="text" id="password" name="password" value="${fn:escapeXml(param.password)}"><br><br>

  <input type="submit" value="Войти">
</form>

<a class="button" href="${pageContext.request.contextPath}/registerUser">Зарегистрироваться</a>

</body>
</html>
