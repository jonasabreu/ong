<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title></title>
	<meta charset="utf-8"> 
</head>
	<link rel="stylesheet" href="/css/style.css" />
<body>
<a href="/antigos">Lan&ccedil;amentos Antigos</a> <br />
<form id="compra" action="/novo" method="post">
	<label>Atendente</label>
	<input name="atendente" required /> <br>
	
	<label class="quantidade">Qtd</label>
	<label class="produto">Produto</label>
	<label class="valor">Valor Unidade</label> <br>
	
	<c:forEach var="i" items="${campos}">
		<input class="quantidade" name="items[${i}].quantidade" pattern="[0-9]+" title="Use apenas n&uacute;meros" value="1" />
		<input class="produto" name="items[${i}].produto" /> 
		<input class="valor" name="items[${i}].valor" pattern="[-0-9\.]*" title="Use apenas n&uacute;meros e '.'"/><br>
	</c:forEach>
	<label for="total">Total: R$</label>
	<input name="total" id="total" value="0" /><br>
	<select name="formaPagamento">
		<option value="dinheiro">Dinheiro</option>
		<option value="debito">Debito</option>
		<option value="credito">Credito</option>
		<option value="transferencia">Transferencia</option>
		<option value="deposito">Deposito</option>
		<option value="pagamentoOnline">Pagamento Online</option>
		<option value="cheque">Cheque</option>
	</select>
	<input type="submit" />
</form> 

<%@include file="/WEB-INF/fragments/lancamentos.jspf" %>

	<script src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
	<script src="/js/underscore-min.js"></script>
	<script src="/js/ong.js"></script>
</body>
</html>