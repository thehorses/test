package br.com.ayto.base.util;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExportUtil {

	public static void exportXLSX(List<List<Object>> dados, OutputStream os) throws IOException {
		Workbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet("Resultado");
		sheet.setDisplayGridlines(false);

		CellStyle styleTitulo = wb.createCellStyle();
		styleTitulo.setFillForegroundColor(IndexedColors.GREY_80_PERCENT.getIndex());
		styleTitulo.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		styleTitulo.setBorderBottom(BorderStyle.THIN);
		styleTitulo.setBorderTop(BorderStyle.THIN);
		styleTitulo.setBorderLeft(BorderStyle.THIN);
		styleTitulo.setBorderRight(BorderStyle.THIN);
		styleTitulo.setTopBorderColor(IndexedColors.BLACK.getIndex());
		styleTitulo.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		styleTitulo.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		styleTitulo.setRightBorderColor(IndexedColors.BLACK.getIndex());
		styleTitulo.setAlignment(HorizontalAlignment.CENTER);

		Font font = wb.createFont();
		font.setFontHeightInPoints((short) 8);
		font.setFontName("Tahoma");
		font.setBold(true);
		font.setColor(IndexedColors.WHITE.getIndex());
		styleTitulo.setFont(font);

		CellStyle styleValor = wb.createCellStyle();
		styleTitulo.setFillBackgroundColor(IndexedColors.WHITE.getIndex());
		font = wb.createFont();
		font.setFontHeightInPoints((short) 8);
		font.setFontName("Tahoma");
		font.setColor(IndexedColors.BLACK.getIndex());
		styleValor.setFont(font);
		styleValor.setBorderBottom(BorderStyle.THIN);
		styleValor.setBorderTop(BorderStyle.THIN);
		styleValor.setBorderLeft(BorderStyle.THIN);
		styleValor.setBorderRight(BorderStyle.THIN);
		styleValor.setTopBorderColor(IndexedColors.BLACK.getIndex());
		styleValor.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		styleValor.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		styleValor.setRightBorderColor(IndexedColors.BLACK.getIndex());

		try {
			int primC = 1;
			int primR = 1;
			int c = primC;
			int r = primR;
			Row row = null;
			Cell cell = null;
			for (List<Object> linha : dados) {
				row = sheet.createRow(r++);
				c = primC;
				for (Object valor : linha) {
					cell = row.createCell(c++);
					if (cell.getRowIndex() == primR) {
						cell.setCellStyle(styleTitulo);
					} else {
						cell.setCellStyle(styleValor);
					}
					if (valor != null) {
						if (valor instanceof Date) {
							Date valorD = (Date) valor;
							if (valorD.after(DateUtils.truncate(valorD, Calendar.DAY_OF_MONTH))) {
								cell.setCellValue(StringUtil.formatDataHora(valorD));
							} else {
								cell.setCellValue(StringUtil.formatData(valorD));
							}
						} else if (valor instanceof Double) {
							cell.setCellValue(StringUtil.formatDecimal((Double) valor));
						} else if (valor instanceof Integer) {
							cell.setCellValue((Integer) valor);
						} else {
							cell.setCellValue(valor.toString());
						}
					}
				}
			}
			for (int i = 0; i <= row.getLastCellNum(); i++) {
				sheet.autoSizeColumn(i);
			}
			wb.write(os);
		} finally {
			wb.close();
			os.close();
		}
	}

}
