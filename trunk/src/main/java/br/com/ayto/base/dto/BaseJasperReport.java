package br.com.ayto.base.dto;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

public class BaseJasperReport {

	JasperPrint print;

	public BaseJasperReport(InputStream jasper, Map<String, Object> parametros, JRDataSource jrDataSource) {
		try {
			// JasperReport jr = JasperCompileManager.compileReport(jrxml);
			print = JasperFillManager.fillReport(jasper, parametros, jrDataSource);
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
