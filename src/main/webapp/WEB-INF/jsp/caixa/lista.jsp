<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<ul>
<c:forEach var="dia" items="${dias}">
	<li><a href="/antigo/${dia}">${dia}</a></li>
</c:forEach>
</ul>