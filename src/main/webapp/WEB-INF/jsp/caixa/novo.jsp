<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="/WEB-INF/fragments/start.jspf" %>

<nav>
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
	<button>Ver</button>
</form> 
</li>
</ul>
</nav>

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

<%@include file="/WEB-INF/fragments/end.jspf" %>