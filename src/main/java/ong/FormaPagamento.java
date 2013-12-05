package ong;

public enum FormaPagamento {
	dinheiro("Dinheiro"), credito("Crédito"), debito("Débito");

	private final String rep;

	private FormaPagamento(final String rep) {
		this.rep = rep;
	}

	public String getRep() {
		return rep;
	}
}
