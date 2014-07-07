function sumInputs(){
	$("#total").attr("value",  
	_.chain($("#compra input.valor")).
		foldl(function(soma, e){
			return soma + (e.value * e.previousElementSibling.previousElementSibling.value)
		},0).value().toFixed(2))
}

function carregaLancamentos(e) {
	e.preventDefault();
	var dia = $("#lancamentos-antigos select").val();
	window.location = "/antigo/" + dia;
}

function carregaFechamento(e) {
	e.preventDefault();
	var mes = $("#fechamentos select").val();
	window.location = "/fechamento/" + mes;
}

function mudaFormaPagamento(e) {
    e.preventDefault();
    $.ajax({
      type: "POST",
      url: "/mudaFormaPagamento",
      data: {id: $(this).attr("data-id"), formaPagamento: this.value},
      async: false
    });
    window.location.reload();
}


$(document).ready(function() {
	
	var inputs = $("#compra .valor");
	inputs.each(function(i, e){
		$(e).blur(sumInputs);
	});
	$("#lancamentos-antigos button").click(carregaLancamentos);
	$("#fechamentos button").click(carregaFechamento);
    $(".lancamento select").each(function(index, e) {
      $(e).change(mudaFormaPagamento);
    });

    var produtos = new Bloodhound({
        datumTokenizer: function (d) {
            return Bloodhound.tokenizers.whitespace(d.produto);
        },
        queryTokenizer: Bloodhound.tokenizers.whitespace,
        local: autocompleteData
    });
    produtos.initialize();

    $(".produto").each(function(index, e){

        $(e).typeahead({
            highlight: true
        }, {
            name: 'produto',
            limit: 10,
            displayKey: 'produto',
            source: produtos.ttAdapter(),
            templates: {
              empty: '',
              suggestion: function(e) { return '<p><strong>' + e.produto + '</strong> - R$ ' + e.valor + '</p>'}
            }
        });

        var produtoSelectedHandler = function (eventObject, suggestionObject, suggestionDataset) {
            $("input[name='items[" + index + "].valor']").val(suggestionObject.valor);
        };

        $(e).on('typeahead:selected', produtoSelectedHandler);
    
    });

});
