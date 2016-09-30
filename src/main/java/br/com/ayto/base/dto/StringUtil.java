package br.com.ayto.base.dto;

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

	public static void main(String[] args) {
		System.out.println(formatTelefone("982550529"));
		System.out.println(formatTelefone("82550529"));
		System.out.println(formatCpf("21975907825"));
		System.out.println(formatCnpj("30123456000185"));
		System.out.println(formatCep("06150090"));
		System.out.println(formatRg("302435906"));
		
		System.out.println(new SimpleDateFormat("HHmmDDDyy").format(new Date()));
		System.out.println(StringUtils.reverse(new SimpleDateFormat("HHmmDDDyy").format(new Date())));
	}
}
