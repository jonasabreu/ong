package ong;

public enum FormaPagamento {
	dinheiro("Dinheiro"), credito("Credito"), debito("Debito"), transferencia("Transferencia"), deposito("Deposito"), pagamentoOnline(
			"Pagamento Online"), cheque("Cheque");

	private final String rep;

	private FormaPagamento(final String rep) {
		this.rep = rep;
	}

	public String getRep() {
		return rep;
	}
}
