<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <title>User Login</title>
  <link rel="stylesheet" type="text/css" href="css/public.css">
</head>

<body>
<div class="header">My Flea Market</div>
<hr width="100%"/>
<ul>
  <c:forEach var="error" items="${errors}">
    <li class="error">${error}</li>
  </c:forEach>
</ul>

<form action="controller" method="post">
  <table width="100%" align="center">
    <tr height="40">
      <td colspan="2" align="center"><strong>Please login</strong></td>
    </tr>
    <tr height="40">
      <td width="50%" align="right"><img src="images/3.jpg" align="absmiddle"/>&nbsp;&nbsp;User ID：</td>
      <td><input type="text" name="userid" class="textfield"/></td>
    </tr>
    <tr height="40">
      <td width="50%" align="right"><img src="images/2.jpg" align="absmiddle"/>&nbsp;&nbsp;Pasword：</td>
      <td><input type="password" name="password" class="textfield"/></td>
    </tr>
    <tr height="40">
      <td align="right">&nbsp;</td>
      <td><button value="login" onclick="document.forms[0].fn.value='login'"/>Login</button>
        &nbsp;&nbsp;&nbsp;&nbsp; <button><a href="controller?action=reg_init">Register</a></button>
      </td>
    </tr>
  </table>
  <input type="hidden" name="action" value="login">
</form>
<%@include file="footer.jsp" %>
</body>
</html>