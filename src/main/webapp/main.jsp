<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>My Flea Market</title>
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
<hr width="100%"/>
<div>
    <p class="text1"><img src="images/4.jpg" align="absmiddle"/> <a href="controller?action=list">Goods List</a></p>
    <p class="text2"> You can browse the products you like in from the product list to purchase. </p>
</div>
<hr width="100%"/>
<div>
    <p class="text1"><img src="images/mycar1.jpg" align="absmiddle"/> <a href="controller?action=cart">Shooping Cart</a></p>
    <p class="text2"> You can put the goods you are interested in in the shopping cart. </p>
</div>
<div class="footer">
    <hr width="100%"/>
    2020
</div>
</body>
</html>
