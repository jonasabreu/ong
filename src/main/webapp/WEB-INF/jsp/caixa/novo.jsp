<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title></title>
	<meta charset="utf-8"> 
</head>
	<link rel="stylesheet" href="/css/style.css" />
<body>
<form action="/novo" method="post">
	<input name="items[0].produto" /> 
	<input name="items[0].valor" /><br>
	<input name="items[1].produto" /> 
	<input name="items[1].valor" /><br>
	<input name="items[2].produto" /> 
	<input name="items[2].valor" /><br>
	<input name="items[3].produto" /> 
	<input name="items[3].valor" /><br>
	<input name="items[4].produto" /> 
	<input name="items[4].valor" /><br>
	<input name="items[5].produto" /> 
	<input name="items[5].valor" /><br>
	<input name="items[6].produto" /> 
	<input name="items[6].valor" /><br>
	<input name="items[7].produto" /> 
	<input name="items[7].valor" /><br>
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
<ul>

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
<li class="${lancamento.formaPagamento}">${lancamento.hora} - ${lancamento.formaPagamento.rep}
	<ul>
	<c:forEach items="${lancamento.items}" var="item">
		<li>${item.produto} - ${item.valor}</li>
	</c:forEach>
	</ul>
</li>
</c:forEach> </ul>
</body>
</html>