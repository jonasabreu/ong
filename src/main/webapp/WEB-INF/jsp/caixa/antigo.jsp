<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title></title>
	<meta charset="utf-8"> 
</head>
	<link rel="stylesheet" href="/css/style.css" />
<body>

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
</body>
</html>