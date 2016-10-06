package br.com.ayto.base.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class StringUtil {

	public static String formatTelefone(String telefone) {
		if (telefone == null) {
			return null;
		}
		telefone = removerMascara(telefone);
		telefone = telefone.replaceFirst("(\\d*)(\\d{4}$)", "$1-$2");
		return telefone;
	}

	public static String removerMascara(String valor) {
		if (valor == null) {
			return null;
		}
		return valor.replaceAll("[^0-9]", "");
	}

	public static String formatCpf(String cpf) {
		if (cpf == null) {
			return null;
		}
		cpf = removerMascara(cpf);
		cpf = cpf.replaceFirst("(\\d*)(\\d{3})(\\d{3})(\\d{2}$)", "$1.$2.$3-$4");
		return cpf;
	}

	public static String formatRg(String rg) {
		if (rg == null) {
			return null;
		}
		rg = removerMascara(rg);
		rg = rg.replaceFirst("(\\d*)(\\d{3})(\\d{3})(\\d{1}$)", "$1.$2.$3-$4");
		return rg;
	}

	public static String formatCnpj(String cnpj) {
		if (cnpj == null) {
			return null;
		}
		cnpj = removerMascara(cnpj);
		cnpj = cnpj.replaceFirst("(\\d*)(\\d{3})(\\d{3})(\\d{4})(\\d{2}$)", "$1.$2.$3/$4-$5");
		return cnpj;
	}

	public static String formatCep(String cep) {
		if (cep == null) {
			return null;
		}
		cep = removerMascara(cep);
		cep = cep.replaceFirst("(\\d*)(\\d{3}$)", "$1-$2");
		return cep;
	}

	public static String formatMoeda(Double moeda) {
		if (moeda == null) {
			return null;
		}
		return DecimalFormat.getCurrencyInstance().format(moeda);
	}

	public static String formatData(Date date) {
		if (date == null) {
			return null;
		}
		return SimpleDateFormat.getDateInstance().format(date);
	}

	public static String formatDataHora(Date date) {
		if (date == null) {
			return null;
		}
		return SimpleDateFormat.getDateTimeInstance().format(date);
	}

	public static String moedaPorExtenso(double vlr) {
		// http://www.devmedia.com.br/valor-por-extenso-em-uma-aplicacao-java/21897
		if (vlr == 0)
			return ("zero");

		long inteiro = (long) Math.abs(vlr); // parte inteira do valor
		double resto = vlr - inteiro; // parte fracionária do valor

		String vlrS = String.valueOf(inteiro);
		if (vlrS.length() > 15)
			return ("Erro: valor superior a 999 trilhões.");

		String s = "", saux, vlrP;
		String centavos = String.valueOf((int) Math.round(resto * 100));

		String[] unidade = { "", "um", "dois", "três", "quatro", "cinco", "seis", "sete", "oito", "nove", "dez", "onze", "doze", "treze", "quatorze", "quinze", "dezesseis",
				"dezessete", "dezoito", "dezenove" };
		String[] centena = { "", "cento", "duzentos", "trezentos", "quatrocentos", "quinhentos", "seiscentos", "setecentos", "oitocentos", "novecentos" };
		String[] dezena = { "", "", "vinte", "trinta", "quarenta", "cinquenta", "sessenta", "setenta", "oitenta", "noventa" };
		String[] qualificaS = { "", "mil", "milhão", "bilhão", "trilhõo" };
		String[] qualificaP = { "", "mil", "milhões", "bilhões", "trilhões" };

		// definindo o extenso da parte inteira do valor
		int n, unid, dez, cent, tam, i = 0;
		boolean umReal = false, tem = false;
		while (!vlrS.equals("0")) {
			tam = vlrS.length();
			// retira do valor a 1a. parte, 2a. parte, por exemplo, para
			// 123456789:
			// 1a. parte = 789 (centena)
			// 2a. parte = 456 (mil)
			// 3a. parte = 123 (milhões)
			if (tam > 3) {
				vlrP = vlrS.substring(tam - 3, tam);
				vlrS = vlrS.substring(0, tam - 3);
			} else { // última parte do valor
				vlrP = vlrS;
				vlrS = "0";
			}
			if (!vlrP.equals("000")) {
				saux = "";
				if (vlrP.equals("100"))
					saux = "cem";
				else {
					n = Integer.parseInt(vlrP, 10); // para n = 371, tem-se:
					cent = n / 100; // cent = 3 (centena trezentos)
					dez = (n % 100) / 10; // dez = 7 (dezena setenta)
					unid = (n % 100) % 10; // unid = 1 (unidade um)
					if (cent != 0)
						saux = centena[cent];
					if ((dez != 0) || (unid != 0)) {
						if ((n % 100) <= 19) {
							if (saux.length() != 0)
								saux = saux + " e " + unidade[n % 100];
							else
								saux = unidade[n % 100];
						} else {
							if (saux.length() != 0)
								saux = saux + " e " + dezena[dez];
							else
								saux = dezena[dez];
							if (unid != 0) {
								if (saux.length() != 0)
									saux = saux + " e " + unidade[unid];
								else
									saux = unidade[unid];
							}
						}
					}
				}
				if (vlrP.equals("1") || vlrP.equals("001")) {
					if (i == 0) // 1a. parte do valor (um real)
						umReal = true;
					else
						saux = saux + " " + qualificaS[i];
				} else if (i != 0)
					saux = saux + " " + qualificaP[i];
				if (s.length() != 0)
					s = saux + ", " + s;
				else
					s = saux;
			}
			if (((i == 0) || (i == 1)) && s.length() != 0)
				tem = true; // tem centena ou mil no valor
			i = i + 1; // prÃ³ximo qualificador: 1- mil, 2- milhão, 3- bilhão,
						// ...
		}

		if (s.length() != 0) {
			if (umReal)
				s = s + " real";
			else if (tem)
				s = s + " reais";
			else
				s = s + " de reais";
		}

		// definindo o extenso dos centavos do valor
		if (!centavos.equals("0")) { // valor com centavos
			if (s.length() != 0) // se não Ã© valor somente com centavos
				s = s + " e ";
			if (centavos.equals("1"))
				s = s + "um centavo";
			else {
				n = Integer.parseInt(centavos, 10);
				if (n <= 19)
					s = s + unidade[n];
				else { // para n = 37, tem-se:
					unid = n % 10; // unid = 37 % 10 = 7 (unidade sete)
					dez = n / 10; // dez = 37 / 10 = 3 (dezena trinta)
					s = s + dezena[dez];
					if (unid != 0)
						s = s + " e " + unidade[unid];
				}
				s = s + " centavos";
			}
		}
		return (s);
	}

	public static Object getLineSeparator() {
		return System.getProperty("line.separator");
	}

	public static void main(String[] args) {
		System.out.println(formatTelefone("982550529"));
		System.out.println(formatTelefone("82550529"));
		System.out.println(formatCpf("21975907825"));
		System.out.println(formatCnpj("30123456000185"));
		System.out.println(formatCep("06150090"));
		System.out.println(formatRg("302435906"));

		System.out.println(new SimpleDateFormat("HHmmDDDyy").format(new Date()));
		System.out.println(StringUtils.reverse(new SimpleDateFormat("HHmmDDDyy").format(new Date())));
		System.out.println(moedaPorExtenso(1523.54));
		System.out.println(moedaPorExtenso(4512231523.5452));
		System.out.println(moedaPorExtenso(500));
		System.out.println(moedaPorExtenso(500.1));
		System.out.println(moedaPorExtenso(500.23));
		System.out.println(moedaPorExtenso(10));
		System.out.println(moedaPorExtenso(5));
		System.out.println(moedaPorExtenso(5.54));
	}

}
