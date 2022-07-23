package adhoc;

import java.io.*;
import java.util.List;

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

public class ExcelReadWrite {

	/*public static void main(String[] args) throws Throwable {
		ExcelReadWrite object = new ExcelReadWrite();
		object.writeExcel();
	}*/
	
	public void updateStatus(List<String> list) throws Throwable {
		try {
		
		File file = new File("C:\\Users\\cuten\\IDE-Handson\\nk-automation\\src\\test\\resources\\Status.xlsx");
		FileInputStream inputStream = new FileInputStream(file);
		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet sheet = workbook.getSheetAt(0);
		int lastRow = sheet.getLastRowNum()+1;
		System.out.println("Total No.of existing rows: "+lastRow);
		Row row = sheet.createRow(lastRow);
		int size = list.size();
		for (int i=0;i<size;i++) {
			Cell cell = row.createCell(i);
			cell.setCellValue(list.get(i));
			CellStyle style = workbook.createCellStyle();
			Font font = workbook.createFont();
			if(i==size-1) {
				if(list.get(i).toLowerCase().contains("pass")) {
					font.setColor(IndexedColors.GREEN.getIndex());
				}else {
					font.setColor(IndexedColors.RED.getIndex());
				}
			}
			style.setFont(font);
			style.setAlignment(HorizontalAlignment.CENTER);
			cell.setCellStyle(style);
		}
		inputStream.close();
		FileOutputStream outputStream = new FileOutputStream(file);
		workbook.write(outputStream);
		outputStream.close();
		workbook.close();
		
		}catch(Exception e) {
			System.out.println("Status Update failed");
			e.printStackTrace();
		}
	}
	
	public void readExcel() throws Throwable {
		try {
		
		File file = new File("C:\\Users\\cuten\\IDE-Handson\\nk-automation\\src\\test\\resources\\Demo.xlsx");
		FileInputStream inputStream = new FileInputStream(file);
		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet sheet = workbook.getSheetAt(0);
		Row row = sheet.getRow(0);
		int getTotalRows = sheet.getPhysicalNumberOfRows();
		int getTotalCellsinFirstRow = row.getPhysicalNumberOfCells();
		String firstCellValue = row.getCell(0).getStringCellValue();
		
		System.out.println("Total No.of rows: "+getTotalRows);
		System.out.println("Total No.of cells in first Row: "+getTotalCellsinFirstRow);
		System.out.println("First Cell Value: "+firstCellValue);
		workbook.close();
		inputStream.close();
		
		}catch(Exception e) {
			System.out.println("Read Excel failed");
		}
	}
	
	public void writeExcel() throws Throwable {
		try {
		
		File file = new File("C:\\Users\\cuten\\IDE-Handson\\nk-automation\\src\\test\\resources\\Demo.xlsx");
		FileInputStream inputStream = new FileInputStream(file);
		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet sheet = workbook.getSheetAt(0);
		int lastRow = sheet.getLastRowNum()+1;
		System.out.println("Total No.of rows: "+lastRow);
		Row row = sheet.createRow(lastRow);
		
		for (int i=0;i<lastRow;i++) {
			Cell cell = row.createCell(i);
			cell.setCellValue(i);
			CellStyle style = workbook.createCellStyle();
			Font font = workbook.createFont();
	        font.setColor(IndexedColors.RED.getIndex());
			if(i%2==0) {
				style.setFillForegroundColor(IndexedColors.LAVENDER.getIndex());
				style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				style.setAlignment(HorizontalAlignment.CENTER);
				cell.setCellStyle(style);
			}else {
				style.setFont(font);
				style.setAlignment(HorizontalAlignment.CENTER);
				cell.setCellStyle(style);
			}
		}
		inputStream.close();
		FileOutputStream outputStream = new FileOutputStream(file);
		workbook.write(outputStream);
		outputStream.close();
		workbook.close();
		
		}catch(Exception e) {
			System.out.println("Write Excel failed");
		}
	}

}
