package ong.applet;

import java.security.AccessController;
import java.security.KeyStore;
import java.security.PrivilegedAction;
import java.util.Collections;

import javax.swing.JApplet;
import javax.swing.JComboBox;

public class Nfe extends JApplet {

	private static final String nfeEndpoint = "https://homologacao.nfe.fazenda.sp.gov.br/nfeweb/services/NfeRecepcao2.asmx";

	private static final long serialVersionUID = 1L;
	private final JComboBox<String> certificates;

	private final KeyStore keyStore;

	public Nfe() throws Exception {
		try {
			keyStore = KeyStore.getInstance("Windows-MY");
			keyStore.load(null, null);

			final String[] aliases = Collections.list(keyStore.aliases())
					.toArray(new String[] {});

			certificates = new JComboBox<String>(aliases);
			if (aliases.length > 0)
				certificates.setSelectedIndex(0);
			add(certificates);
		} catch (final Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public String[] signPdfs(final String cpf, final String nome,
			final String logradouro, final String numero, final String bairro,
			final String codigoMunicipio, final String municipio,
			final String uf) {
		try {
			return AccessController
					.doPrivileged(new PrivilegedAction<String[]>() {

						@Override
						public String[] run() {
							final String xml = new NfeXml(cpf, nome,
									logradouro, numero, bairro,
									codigoMunicipio, municipio, uf).toString();

							return new String[] {};
						}
					});
		} catch (final Exception e) {
			e.printStackTrace();
			return new String[] { e.getCause().getMessage() };
		}
	}

}
