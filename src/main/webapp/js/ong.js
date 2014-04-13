function sumInputs(){
	$("#total").attr("value",  
	_.chain($("#compra input.valor")).
		foldl(function(soma, e){
			return soma + (e.value * e.previousElementSibling.previousElementSibling.value)
		},0).value().toFixed(2))
}

$(document).ready(function() {
	
	var inputs = $("#compra .valor");
	inputs.each(function(i, e){
		$(e).blur(sumInputs);
	});
});