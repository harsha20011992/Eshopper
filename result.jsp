<%@ page import="java.util.*" %>

<html>

<body>
<h1> Test </h1>

<%
String result = (String) request.getAttribute("message");
out.print(result);

%>

</body>

</html>