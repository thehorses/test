package br.com.ayto.base.util;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExportUtil {

	public static void exportXLSX(List<List<Object>> dados, OutputStream os) throws IOException {
		Workbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet("Resultado");

		CellStyle styleTitulo = wb.createCellStyle();
		styleTitulo.setFillBackgroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
		styleTitulo.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		Font font = wb.createFont();
		font.setFontHeightInPoints((short) 9);
		font.setFontName("Tahoma");
		font.setBold(true);
		font.setColor(IndexedColors.WHITE.getIndex());
		styleTitulo.setFont(font);

		CellStyle styleValor = wb.createCellStyle();
		styleTitulo.setFillBackgroundColor(IndexedColors.WHITE.getIndex());
		font = wb.createFont();
		font.setFontHeightInPoints((short) 9);
		font.setFontName("Tahoma");
		font.setColor(IndexedColors.BLACK.getIndex());
		styleValor.setFont(font);

		try {
			int c = 0;
			int r = 0;
			Row row = null;
			Cell cell = null;
			for (List<Object> linha : dados) {
				row = sheet.createRow(r++);
				c = 0;
				for (Object valor : linha) {
					cell = row.createCell(c++);
					if (cell.getRowIndex() == 0) {
						cell.setCellStyle(styleTitulo);
					} else {
						cell.setCellStyle(styleValor);
					}
					if (valor != null) {
						if (valor instanceof Date) {
							cell.setCellValue((Date) valor);
						}
						cell.setCellValue(valor.toString());
					}
				}
			}
			wb.write(os);
		} finally {
			wb.close();
			os.close();
		}
	}

}
