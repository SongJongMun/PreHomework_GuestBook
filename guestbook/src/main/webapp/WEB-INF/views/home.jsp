<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>
<!-- 비즈니스로직에서 전송한 데이터를 EL(Expression Language)를 이용하여 표현-${} -->
<P>  The time on the server is ${serverTime}. </P>
</body>
</html>
