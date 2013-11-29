<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title></title></head>
<body>
<form action="/novo" method="post">
	<input type="hidden" name="lancamento.id" value="0" />
	<input type="hidden" name="lancamento.items[0].id" value="0" />
	<input type="hidden" name="lancamento.items[0].lancamentoId" value="0" />
	<input name="lancamento.items[0].produto" /> 
	<input name="lancamento.items[0].valor" /><br>
	<input type="hidden" name="lancamento.items[1].id" value="0" />
	<input type="hidden" name="lancamento.items[1].lancamentoId" value="0" />
	<input name="lancamento.items[1].produto" />
	<input name="lancamento.items[1].valor" /><br>
	<input type="hidden" name="lancamento.items[2].id" value="0" />
	<input type="hidden" name="lancamento.items[2].lancamentoId" value="0" />
	<input name="lancamento.items[2].produto" />
	<input name="lancamento.items[2].valor" /><br>
	<input type="hidden" name="lancamento.items[3].id" value="0" />
	<input type="hidden" name="lancamento.items[3].lancamentoId" value="0" />
	<input name="lancamento.items[3].produto" />
	<input name="lancamento.items[3].valor" /><br>
	<input type="hidden" name="lancamento.items[4].id" value="0" />
	<input type="hidden" name="lancamento.items[4].lancamentoId" value="0" />
	<input name="lancamento.items[4].produto" />
	<input name="lancamento.items[4].valor" /><br>
	<select name="lancamento.formaPagamento">
		<option value="dinheiro">Dinheiro</option>
		<option value="debito">Débito</option>
		<option value="credito">Crédito</option>
	</select>
	<input type="submit" />
</form> 

<ul>
<c:forEach items="${lancamentos}" var="lancamento">
<li>
	<ul>
	<c:forEach items="${lancamento.items}" var="item">
		<li>${item.produto} - ${item.valor}</li>
	</c:forEach>
	</ul>
</li>
</c:forEach> </ul>
</body>
</html>