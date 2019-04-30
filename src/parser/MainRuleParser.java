package parser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import template.imp.MainRuleTemplate;

public class MainRuleParser {
	Workbook wb =null;
	Sheet sheet = null;
	Row row = null;
	Row rowIRR = null;
	String cellData = null;
	Sheet sheetIRR = null;
	public MainRuleParser(String filePath){

		InputStream is = null;
		String extString = filePath.substring(filePath.lastIndexOf("."));
		try {
			is = new FileInputStream(filePath);
			if(".xls".equals(extString)){
				wb = new HSSFWorkbook(is);
			}else if(".xlsx".equals(extString)){
				wb = new XSSFWorkbook(is);
			}else{
				wb = null;
			}
			sheet =  (Sheet) wb.getSheetAt(1);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public MainRuleTemplate parser(){
		MainRuleTemplate mr = new MainRuleTemplate();
		row = (Row) ((org.apache.poi.ss.usermodel.Sheet) sheet).getRow(44);
		String C00 = row.getCell(5).toString();
		mr.setC00(C00);
		return mr;
	}
}
