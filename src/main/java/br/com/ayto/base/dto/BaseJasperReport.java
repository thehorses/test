package br.com.ayto.base.dto;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

public class BaseJasperReport {

	String jrxml;
	Map<String, Object> parametros;
	JasperPrint print;
	OutputStream saida;

	public BaseJasperReport(InputStream jasper, Map<String, Object> parametros) {
		try {
			print = JasperFillManager.fillReport(jasper, parametros);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao gerar relatório", e);
		}
	}

	public void gerarPdf(OutputStream saida) throws JRException {
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setExporterInput(new SimpleExporterInput(print));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(saida));

		SimplePdfExporterConfiguration conf = new SimplePdfExporterConfiguration();
		exporter.setConfiguration(conf);
		exporter.exportReport();
	}

}
