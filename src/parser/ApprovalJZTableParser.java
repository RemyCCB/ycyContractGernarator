package parser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import parser.imp.ITemplateParser;
import template.ITemplate;
import template.imp.ApprovalJZTableTemplate;
import template.imp.ApprovalTableTemplate;

public class ApprovalJZTableParser implements ITemplateParser{
	Workbook wb =null;
	Sheet sheet = null;
	Row row = null;
	Row rowIRR = null;
	String cellData = null;
	Sheet sheetIRR = null;
	public  ApprovalJZTableParser(String filePath){
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
	@Override
	public ITemplate parse() {
		ApprovalJZTableTemplate ajztt = new ApprovalJZTableTemplate();
		//获取第一个sheet
		sheet =  (Sheet) wb.getSheetAt(0);
		//		sheetIRR =  (Sheet) wb.getSheetAt(3);
		//获取最大行数
		int rownum = ((org.apache.poi.ss.usermodel.Sheet) sheet).getPhysicalNumberOfRows();
		//获取第一行

		//车辆信息
		row = (Row) ((org.apache.poi.ss.usermodel.Sheet) sheet).getRow(2);
		String carBrand = row.getCell(1).toString();
		String carType = row.getCell(3).toString();
		String carCorlor = row.getCell(7).toString();
		ajztt.setCarBrand(carBrand);
		ajztt.setCarType(carType);
		ajztt.setCarCorlor(carCorlor);
		row = (Row) ((org.apache.poi.ss.usermodel.Sheet) sheet).getRow(3);
		String VIN = row.getCell(1).toString();
		String engineNumber = row.getCell(3).toString();
		String carBoard = row.getCell(5).toString();
		ajztt.setVIN(VIN);
		ajztt.setEngineNumber(engineNumber);
		ajztt.setCarBoard(carBoard);
		row = (Row) ((org.apache.poi.ss.usermodel.Sheet) sheet).getRow(11);
		String contractNo = row.getCell(1).toString();
		Cell cell = row.getCell(1);
		if (null != cell) {
			switch(cell.getCellType()){
			case NUMERIC: // 数字
				DecimalFormat df = new DecimalFormat("0");
				contractNo = df.format(cell.getNumericCellValue());
				break;
			default:
				contractNo = cell.getStringCellValue();
				break;

			}
		}
		ajztt.setContractId(contractNo);
		row = (Row) ((org.apache.poi.ss.usermodel.Sheet) sheet).getRow(12);
		String deposit = row.getCell(1).toString();
		String rentAmt = row.getCell(6).toString();
		ajztt.setDeposit(deposit);
		ajztt.setRentAmt(rentAmt);
		row = (Row) ((org.apache.poi.ss.usermodel.Sheet) sheet).getRow(13);
		String rentMonth = row.getCell(1).toString();
		ajztt.setRentMonth(rentMonth);
		//承租人及保证人信息
		row = (Row) ((org.apache.poi.ss.usermodel.Sheet) sheet).getRow(15);
		String renterName = row.getCell(1).toString();
		String phoneNumber = row.getCell(3).toString();
		String promiseName = row.getCell(6).toString();
		String guarantorPhone = row.getCell(9).toString();
		ajztt.setRenterName(renterName);
		ajztt.setPhoneNumber(phoneNumber);
		ajztt.setPromiseName(promiseName);
		ajztt.setGuarantorPhone(guarantorPhone);
		row = (Row) ((org.apache.poi.ss.usermodel.Sheet) sheet).getRow(16);
		String id = row.getCell(1).toString();
		String idAddress = row.getCell(3).toString();
		String guarantorId = row.getCell(6).toString();
		String guarantorIdAddress = row.getCell(8).toString();
		ajztt.setGuarantorId(guarantorId);
		ajztt.setGuarantorIdAddress(guarantorIdAddress);
		ajztt.setId(id);
		ajztt.setIdAddress(idAddress);
		row = (Row) ((org.apache.poi.ss.usermodel.Sheet) sheet).getRow(17);
		String realAddress = row.getCell(1).toString();
		String guarantorRealAddress = row.getCell(6).toString();
		ajztt.setGuarantorRealAddress(guarantorRealAddress);
		ajztt.setRealAddress(realAddress);
		//银行卡信息
		row = (Row) ((org.apache.poi.ss.usermodel.Sheet) sheet).getRow(19);
		String cardNo = row.getCell(1).toString();
		String openAccountBankName = row.getCell(5).toString();
		ajztt.setCardNo(cardNo);
		ajztt.setOpenAccountBankName(openAccountBankName);
		return (ITemplate) ajztt;
	}
}