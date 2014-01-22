<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title></title>
	<meta charset="utf-8"> 
</head>
	<link rel="stylesheet" href="/css/style.css" />
<body>
<form id="compra" action="/novo" method="post">
	<c:forEach var="i" items="${campos}">
		<input name="items[${i}].produto" /> 
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

<ul class="total">
	<li class="dinheiro">Total em Dinheiro: ${totalDinheiro}</li>
	<li class="debito">Total em Debito: ${totalDebito}</li>
	<li class="credito">Total em Credito: ${totalCredito}</li>
	<li>Total em Transferencia: ${totalTransferencia}</li>
	<li>Total em Deposito: ${totalDeposito}</li>
	<li>Total em Pagamento Online: ${totalPagamentoOnline}</li>
	<li>Total em Cheque: ${totalCheque}</li>
</ul>

<c:forEach items="${lancamentos}" var="lancamento">
<li class="${lancamento.formaPagamento}">${lancamento.hora} - ${lancamento.total} em ${lancamento.formaPagamento.rep} - <a href="/remove/${lancamento.id}">Remover</a>
	<ul>
	<c:forEach items="${lancamento.items}" var="item">
		<li>${item.produto} - ${item.valor}</li>
	</c:forEach>
	</ul>
</li>
</c:forEach> </ul>
	<script src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
	<script src="/js/underscore-min.js"></script>
	<script src="/js/ong.js"></script>
</body>
</html>