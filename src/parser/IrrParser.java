package parser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import parser.imp.ITemplateParser;
import template.ITemplate;
import template.imp.IrrTemplate;

public class IrrParser implements ITemplateParser{
	Workbook wb =null;
	Sheet sheet = null;
	Row row = null;
	String cellData = null;
	String irr[][];
	String a4 = null;
	public IrrParser(String filePath){
//		this.super(filePath);
		irr = new String[36][5];
		
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

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	
	}
	
	public void setA4(String a4){
		this.a4 = a4;
	}
	@Override
	public ITemplate parse() {
		// TODO Auto-generated method stub
		sheet =  (Sheet) wb.getSheetAt(4);
		row = (Row) ((org.apache.poi.ss.usermodel.Sheet) sheet).getRow(3);
//		String a4 = row.getCell(0).toString();
		double rate = Double.parseDouble(((org.apache.poi.ss.usermodel.Sheet) sheet).getRow(1).getCell(1).toString());
		double pv = Double.parseDouble(a4);
		double nper = Double.parseDouble(((org.apache.poi.ss.usermodel.Sheet) sheet).getRow(1).getCell(4).toString())*12;
		for(int i = 5; i < 41; i++){
			row = (Row) ((org.apache.poi.ss.usermodel.Sheet) sheet).getRow(i);
			for(int j = 0; j < 5; j++){
				if(5==i&&1==j){
					irr[i-5][j] = a4;
				} else {
					if(1==j){
						irr[i-5][j] = String.valueOf(df.format(Double.parseDouble(irr[i-6][1]) - Double.parseDouble(irr[i-6][4])));
					} else if(2==j){
						if(0 == Double.parseDouble(df.format(Double.parseDouble(irr[i-5][j-1])))){
							irr[i-5][j] = "0";
						} else {
							irr[i-5][j] = String.valueOf(df.format(calculatePMT(rate,nper,pv)));
						}
					} else if(3==j){
						irr[i-5][j] = String.valueOf(df.format(Double.parseDouble(irr[i-5][1])*rate/12));
					} else if(4==j){
						
						irr[i-5][j] = String.valueOf(df.format(Double.parseDouble(irr[i-5][2]) - Double.parseDouble(irr[i-5][3])));
					} else {
						irr[i-5][j] = row.getCell(j).toString();//negative value is incorrect
					}
				}
				//irr[i][j] =  Double.parseDouble(row.getCell(j).toString());//negative value is incorrect
			}
		}
		IrrTemplate irrt = new IrrTemplate();
		irrt.setIrr(irr);
		return irrt;
	}
	
    public double calculatePMT(double rate, double nper, double pv) {
        double v = (1 + (rate / 12));
        double t = (-(nper / 12) * 12);
        double result = (pv * (rate / 12)) / (1 - Math.pow(v, t));
        return result;
    }
	
}
