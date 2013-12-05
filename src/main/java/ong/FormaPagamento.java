package ong;

public enum FormaPagamento {
	dinheiro("Dinheiro"), credito("Cr&eacute;dito"), debito("D&eacute;bito");

	private final String rep;

	private FormaPagamento(final String rep) {
		this.rep = rep;
	}

	public String getRep() {
		return rep;
	}
}
