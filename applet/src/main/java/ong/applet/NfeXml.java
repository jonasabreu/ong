package ong.applet;

public class NfeXml {

	private final String nome;
	private final String cpf;
	private final String numero;
	private final String logradouro;
	private final String bairro;
	private final String codigoMunicipio;
	private final String municipio;
	private final String uf;

	public NfeXml(final String cpf, final String nome, final String logradouro,
			final String numero, final String bairro,
			final String codigoMunicipio, final String municipio,
			final String uf) {
		this.cpf = cpf;
		this.nome = nome;
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
		this.codigoMunicipio = codigoMunicipio;
		this.municipio = municipio;
		this.uf = uf;
	}

	@Override
	public String toString() {
		final StringBuilder xml = new StringBuilder();
		xml.append("<?xml version=\"1.0\" encoding=\"utf-16\"?> "
				+ "<nfeRecepcao versao=\"2.00\" xmlns=\"http://www.portalfiscal.inf.br/nfe\">");
		xml.append("<NFe xmlns=\"http://www.portalfiscal.inf.br/nfe\">"
				+ "<infNFe Id=\"NFe35140868457308000102550010000000801500000797\" versao=\"2.00\">"
				+ "    <ide>                                                                  "
				+ "        <cUF>35</cUF>                                                      "
				+ "        <cNF>50000079</cNF>                                                "
				+ "        <natOp>VENDA DE PRODUTO</natOp>                                    "
				+ "        <indPag>0</indPag>                                                 "
				+ "        <mod>55</mod>                                                      "
				+ "        <serie>1</serie>                                                   "
				+ "        <nNF>80</nNF>                                                      "
				+ "        <dEmi>2014-08-01</dEmi>                                            "
				+ "        <tpNF>1</tpNF>                                                     "
				+ "        <cMunFG>3550308</cMunFG>                                           "
				+ "        <tpImp>1</tpImp>                                                   "
				+ "        <tpEmis>1</tpEmis>                                                 "
				+ "        <cDV>7</cDV>                                                       "
				+ "        <tpAmb>1</tpAmb>                                                   "
				+ "        <finNFe>1</finNFe>                                                 "
				+ "        <procEmi>3</procEmi>                                               "
				+ "        <verProc>2.2.25</verProc>                                          "
				+ "    </ide>                                                                 "
				+ "    <emit>                                                                 "
				+ "        <CNPJ>68457308000102</CNPJ>                                        "
				+ "        <xNome>Pet Centro Animal LTDA-ME</xNome>                           "
				+ "        <xFant>Pet Centro Animal</xFant>                                   "
				+ "        <enderEmit>                                                        "
				+ "            <xLgr>Rua General Jardim</xLgr>                                "
				+ "            <nro>240</nro>                                                 "
				+ "            <xBairro>Vila Buarque</xBairro>                                "
				+ "            <cMun>3550308</cMun>                                           "
				+ "            <xMun>Sao Paulo</xMun>                                         "
				+ "            <UF>SP</UF>                                                    "
				+ "            <CEP>01223010</CEP>                                            "
				+ "            <cPais>1058</cPais>                                            "
				+ "            <xPais>BRASIL</xPais>                                          "
				+ "            <fone>1131512536</fone>                                        "
				+ "        </enderEmit>                                                       "
				+ "        <IE>143103708117</IE>                                              "
				+ "        <CRT>3</CRT>                                                       "
				+ "    </emit>                                                                "
				+ "    <dest>                                                                 "
				+ "        <CPF>"
				+ cpf
				+ "</CPF>                                             "
				+ "        <xNome>"
				+ nome
				+ "</xNome>                             "
				+ "        <enderDest>                                                        "
				+ "            <xLgr>"
				+ logradouro
				+ "</xLgr>                               "
				+ "            <nro>"
				+ numero
				+ "</nro>                                                "
				+ "            <xBairro>"
				+ bairro
				+ "</xBairro>                                      "
				+ "            <cMun>"
				+ codigoMunicipio
				+ "</cMun>                                           "
				+ "            <xMun>"
				+ municipio
				+ "</xMun>                                         "
				+ "            <UF>"
				+ uf
				+ "</UF>                                                    "
				+ "            <cPais>1058</cPais>                                            "
				+ "            <xPais>BRASIL</xPais>                                          "
				+ "        </enderDest>                                                       "
				+ "        <IE />                                                             "
				+ "    </dest>                                                                "
				+ "    <det nItem=\"1\">                                                        "
				+ "        <prod>                                                             "
				+ "            <cProd>09</cProd>                                              "
				+ "            <cEAN />                                                       "
				+ "            <xProd>vacina</xProd>                                          "
				+ "            <NCM>30023090</NCM>                                            "
				+ "            <CFOP>5102</CFOP>                                              "
				+ "            <uCom>und</uCom>                                               "
				+ "            <qCom>1.0000</qCom>                                            "
				+ "            <vUnCom>45.0000000000</vUnCom>                                 "
				+ "            <vProd>45.00</vProd>                                           "
				+ "            <cEANTrib />                                                   "
				+ "            <uTrib>un</uTrib>                                              "
				+ "            <qTrib>1.0000</qTrib>                                          "
				+ "            <vUnTrib>45.0000000000</vUnTrib>                               "
				+ "            <indTot>1</indTot>                                             "
				+ "        </prod>                                                            "
				+ "        <imposto>                                                          "
				+ "            <ICMS>                                                         "
				+ "                <ICMS00>                                                   "
				+ "                    <orig>0</orig>                                         "
				+ "                    <CST>00</CST>                                          "
				+ "                    <modBC>3</modBC>                                       "
				+ "                    <vBC>45.00</vBC>                                       "
				+ "                    <pICMS>18.00</pICMS>                                   "
				+ "                    <vICMS>8.10</vICMS>                                    "
				+ "                </ICMS00>                                                  "
				+ "            </ICMS>                                                        "
				+ "            <IPI>                                                          "
				+ "                <clEnq>1</clEnq>                                           "
				+ "                <cEnq>1</cEnq>                                             "
				+ "                <IPINT>                                                    "
				+ "                    <CST>53</CST>                                          "
				+ "                </IPINT>                                                   "
				+ "            </IPI>                                                         "
				+ "            <PIS>                                                          "
				+ "                <PISNT>                                                    "
				+ "                    <CST>08</CST>                                          "
				+ "                </PISNT>                                                   "
				+ "            </PIS>                                                         "
				+ "            <COFINS>                                                       "
				+ "                <COFINSNT>                                                 "
				+ "                    <CST>08</CST>                                          "
				+ "                </COFINSNT>                                                "
				+ "            </COFINS>                                                      "
				+ "        </imposto>                                                         "
				+ "    </det>                                                                 "
				+ "    <total>                                                                "
				+ "        <ICMSTot>                                                          "
				+ "            <vBC>45.00</vBC>                                               "
				+ "            <vICMS>8.10</vICMS>                                            "
				+ "            <vBCST>0.00</vBCST>                                            "
				+ "            <vST>0.00</vST>                                                "
				+ "            <vProd>45.00</vProd>                                           "
				+ "            <vFrete>0.00</vFrete>                                          "
				+ "            <vSeg>0.00</vSeg>                                              "
				+ "            <vDesc>0.00</vDesc>                                            "
				+ "            <vII>0.00</vII>                                                "
				+ "            <vIPI>0.00</vIPI>                                              "
				+ "            <vPIS>0.00</vPIS>                                              "
				+ "            <vCOFINS>0.00</vCOFINS>                                        "
				+ "            <vOutro>0.00</vOutro>                                          "
				+ "            <vNF>45.00</vNF>                                               "
				+ "        </ICMSTot>                                                         "
				+ "    </total>                                                               "
				+ "    <transp>                                                               "
				+ "        <modFrete>9</modFrete>                                             "
				+ "    </transp>                                                              "
				+ "</infNFe>                                                                  "
				+ "<Signature xmlns=\"http://www.w3.org/2000/09/xmldsig#\">                     "
				+ "    <SignedInfo>                                                           "
				+ "        <CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\" /> "
				+ "        <SignatureMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#rsa-sha1\" />             "
				+ "        <Reference URI=\"#NFe35140868457308000102550010000000801500000797\">                     "
				+ "            <Transforms>                                                                       "
				+ "                <Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\" />"
				+ "                <Transform Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\" />      "
				+ "            </Transforms>                                                                      "
				+ "            <DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\" />                "
				+ "            <DigestValue>W3Pac5qKJv4xQhZgAFoj6RUFnuA=</DigestValue>                            "
				+ "        </Reference>                                                                           "
				+ "    </SignedInfo>                                                                              "
				+ "    <SignatureValue>YnkrZN3Jm6Mvm3LxsStL9/c9shUIl9MV27Qi57kIG79iTXRARiC+zWtn441d3i2fEYGL81u4gQXECU7KxrIFI94SQSHFPmDn4TWbRCjhFZ9oqQgGcy03f0WLBzJZzaGWGPuV6c8UyGHVaSO6kCxnY80PxKFEpWy1+GfebyJ/K3PzTUeLXe9GbXnEjzilTQMTjuWzKBUWWCU669yp2RRnDMd6OlFCmwxi1ZpNbxY7aKnedU1DA9/IS+YVl/dwczHAw/d5jgno3LB6slfxOVZoSoLllIFBexjvzTRicwcyg7MnCNfWzr6O9tWZwtn08azeIe2KHso1XvaL+ZBzCPinQg==</SignatureValue> "
				+ "    <KeyInfo>"
				+ "        <X509Data>"
				+ "            <X509Certificate>MIIIUDCCBjigAwIBAgIQF2AX5ZhOZYSbVG1ryFt1PDANBgkqhkiG9w0BAQsFADB0MQswCQYDVQQGEwJCUjETMBEGA1UEChMKSUNQLUJyYXNpbDEtMCsGA1UECxMkQ2VydGlzaWduIENlcnRpZmljYWRvcmEgRGlnaXRhbCBTLkEuMSEwHwYDVQQDExhBQyBDZXJ0aXNpZ24gTXVsdGlwbGEgRzUwHhcNMTQwMjI3MDAwMDAwWhcNMTcwMjI1MjM1OTU5WjCB1DELMAkGA1UEBhMCQlIxEzARBgNVBAoUCklDUC1CcmFzaWwxJzAlBgNVBAsUHkF1dGVudGljYWRvIHBvciAgQVIgVklMQSBWRUxIQTEbMBkGA1UECxQSQXNzaW5hdHVyYSBUaXBvIEEzMRUwEwYDVQQLFAxJRCAtIDU5NjMzMDAxJDAiBgNVBAMTG1BldCBDZW50cm8gQW5pbWFsIExUREEgLSBNRTEtMCsGCSqGSIb3DQEJARYeY29udGF0b0BuYXR1cmV6YWVtZm9ybWEub3JnLmJyMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwqJNbDfLyn6n2w2twNCWe0CQK8oATgVDPur7YLZSr1hSOsqspWzZ55L09Rg6QCwpJvdQTKae3HEtt3ymToBbWyOER3Be4zsrgJqhku6Fu8VIN+l21RkEMog6zGHWShlLICx3XRZNm1jSNCtoEgKuMyEO0rTz7fSSsf35VepY/uTT1rXZtpmWWU0tjQ/iZGUPB4MbRQPHoDudGEVQ/wBeQHNNwgEkNHTx8JcnWvYv1GAMAfQlEHolKVEXSz0nz65AJlTdk6rp+4MpOiXp/tTrBRrtG3Jb1Mhwd1+WN+SBs8EUNx///vSlE5SAveb7miMXubH3q+/snF5UCItu0lfLIQIDAQABo4IDezCCA3cwgcEGA1UdEQSBuTCBtqA9BgVgTAEDBKA0BDIyNDA3MTk2NjA1NjAzOTM4ODExMDAwMDAwMDAwMDAwMDAwMDAwMTU1NjE0NzRzc3BTUKAhBgVgTAEDAqAYBBZNYW51ZWwgRmVybmFuZGV6IE90ZXJvoBkGBWBMAQMDoBAEDjY4NDU3MzA4MDAwMTAyoBcGBWBMAQMHoA4EDDAwMDAwMDAwMDAwMIEeY29udGF0b0BuYXR1cmV6YWVtZm9ybWEub3JnLmJyMAkGA1UdEwQCMAAwHwYDVR0jBBgwFoAUnVDPvf8kyq+xM+sX4kJ6jmkqjlMwDgYDVR0PAQH/BAQDAgXgMIGJBgNVHSAEgYEwfzB9BgZgTAECAwUwczBxBggrBgEFBQcCARZlaHR0cDovL2ljcC1icmFzaWwuY2VydGlzaWduLmNvbS5ici9yZXBvc2l0b3Jpby9kcGMvQUNfQ2VydGlzaWduX011bHRpcGxhL0RQQ19BQ19DZXJ0aVNpZ25NdWx0aXBsYS5wZGYwggElBgNVHR8EggEcMIIBGDBcoFqgWIZWaHR0cDovL2ljcC1icmFzaWwuY2VydGlzaWduLmNvbS5ici9yZXBvc2l0b3Jpby9sY3IvQUNDZXJ0aXNpZ25NdWx0aXBsYUc1L0xhdGVzdENSTC5jcmwwW6BZoFeGVWh0dHA6Ly9pY3AtYnJhc2lsLm91dHJhbGNyLmNvbS5ici9yZXBvc2l0b3Jpby9sY3IvQUNDZXJ0aXNpZ25NdWx0aXBsYUc1L0xhdGVzdENSTC5jcmwwW6BZoFeGVWh0dHA6Ly9yZXBvc2l0b3Jpby5pY3BicmFzaWwuZ292LmJyL2xjci9DZXJ0aXNpZ24vQUNDZXJ0aXNpZ25NdWx0aXBsYUc1L0xhdGVzdENSTC5jcmwwHQYDVR0lBBYwFAYIKwYBBQUHAwIGCCsGAQUFBwMEMIGgBggrBgEFBQcBAQSBkzCBkDBkBggrBgEFBQcwAoZYaHR0cDovL2ljcC1icmFzaWwuY2VydGlzaWduLmNvbS5ici9yZXBvc2l0b3Jpby9jZXJ0aWZpY2Fkb3MvQUNfQ2VydGlzaWduX011bHRpcGxhX0c1LnA3YzAoBggrBgEFBQcwAYYcaHR0cDovL29jc3AuY2VydGlzaWduLmNvbS5icjANBgkqhkiG9w0BAQsFAAOCAgEALafbi6eCMze9/XxfD361/pL6X+JVxzik5Vb43qQedBAOMmKYGiHgNTcBbTNpUywLaUe1InspXkpL0SjxEVIb6H0aBza7m0gjhHQjDiokIGTQdv5agi03ES9IHlXUKv1gYRZ1VQXHHLPh7ujtgpL7APv2MPbCwL0rFwFwD/hnbyankLNzT/2Ats9rZZGW02//dL2ESt7nOMe4h8moz6rDrBazcZPEe3RwJVsYk5rUsAmjeWkl+l2Q5lxVJraNL8/HbX9Rll4z05POGo/BsYV5ljlti+62Dkt2/Wx55bUXOJxWAMmosSLl2MSVfZGxmg3ef6uYsEfWOcz5rqVhjmHxw8LRjnSnTqXe/iKYDFaJCTcWTkkRMGy7xWnSq08rcNYjKe512FbSmpK9PRJ+WqVPywQqbJA9z+49fmUcuLh+/exeFW0VL5EYAMgfOr0lBnrvt18MNMbjzQGCqdLNCVnbDPwxjnhijQw4YwWl+ynvseZcnJHNrvU9Sv4A0VQhbjgz5IEETknf6l2LVxJw35vmKqtfAa0uy0yOxXRyVRruKmzSVztjR+u/5jsSqN7vOz4VY2yWWc2O543g/NIYQuD+IbKcya2j3NQ+KB3LPIrTabVKl1ybeb46Xo5XcU2g6XPGpHwl+s/dS31PzqEqxmKFKFIg7/g1Voq+Oe4F+K8raSs=</X509Certificate>"
				+ "        </X509Data>"
				+ "    </KeyInfo>     "
				+ "</Signature>       " + "</NFe>" + "</nfeRecepcao>");
		return xml.toString();
	}

}
