<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Finish order</title>
<link rel="stylesheet" type="text/css" href="css/public.css">
<style type="text/css">
a:link {
    font-size: 18px;
    color: #DB8400;
    text-decoration: none;
    font-weight: bolder;
}
a:visited {
    font-size: 18px;
    color: #DB8400;
    text-decoration: none;
    font-weight: bolder;
}
a:hover {
    font-size: 18px;
    color: #DB8400;
    text-decoration: underline;
    font-weight: bolder;
}
</style>
</head>

<body>
<div class="header">My Flea Market</div>
<hr width="100%" />
<div align="center" >
  <p class="text7"> Thank you for your shopping！ </p>
  <p class="text7"> Your order ID：${ordersid} </p>
  <p class="text7"> You can continue to shopping！ </p>
   <p class="text7">
       <a href="controller?action=main">Back to home</a>
  </p>
</div>
<%@include file="footer.jsp" %>

</body>
</html>
