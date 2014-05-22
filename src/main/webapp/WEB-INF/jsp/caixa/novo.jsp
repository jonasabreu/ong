<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="/WEB-INF/fragments/start.jspf" %>

<nav>
	<h1>Natureza em Forma</h1>
<ul>
<li id="lancamentos-antigos">
Lan&ccedil;amentos Antigos: 
<form>
	<select name="">
		<c:forEach var="dia" items="${dias}">
			<option value="${dia}">${dia}</option>
		</c:forEach>
	</select>
	<button>Ver</button>
</form> 
</li>
<li id="fechamentos">
Fechamentos: 
<form>
	<select name="">
		<c:forEach var="mes" items="${meses}">
			<option value="${mes}">${mes}</option>
		</c:forEach>
	</select>
	<button>Baixar</button>
</form> 
</li>
</ul>
</nav>
<html>
<head>
<title></title>
<meta charset="utf-8"> 
</head>
<link rel="stylesheet" href="/css/style.css" />
<body>
<section class="novo">
	<form id="compra" action="/novo" method="post">
		<input placeholder="Atendente" name="atendente" required /> <br>
		<hr />

		<c:forEach var="i" items="${campos}">
		<input class="quantidade" placeholder="Quantidade" name="items[${i}].quantidade" pattern="[0-9]+" title="Use apenas n&uacute;meros" value="1" />
		<input class="produto" placeholder="Produto" name="items[${i}].produto" /> 
		<input class="valor" placeholder="Valor" name="items[${i}].valor" pattern="[-0-9\.]*" title="Use apenas n&uacute;meros e '.'"/><br>
		</c:forEach>
		<p class="finalizar"><label for="total">Total: R$</label>
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
		<input class="salvar" type="submit" value="Salvar"/>
		</p>
	</form> 
	<%@include file="/WEB-INF/fragments/lancamentos.jspf" %>
</section>

<%@include file="/WEB-INF/fragments/end.jspf" %>
	<script src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
	<script src="/js/underscore-min.js"></script>
	<script src="/js/ong.js"></script>
<script src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
<script src="/js/underscore-min.js"></script>
<script src="/js/ong.js"></script>
</body>
</html>
