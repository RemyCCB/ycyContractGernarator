package parser;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
//import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;






import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;

import parser.imp.ITemplateParser;
import template.ITemplate;
import template.imp.ApprovalTableTemplate;

public class ApprovalTableParser implements ITemplateParser{
	Workbook wb =null;
	Sheet sheet = null;
	Row row = null;
	Row rowIRR = null;
	String cellData = null;
	Sheet sheetIRR = null;
	/*
	 * 
	 */
	public  ApprovalTableParser(String filePath){
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
	/*
	 * 
	 */
	public ITemplate parse(){
		ApprovalTableTemplate att = new ApprovalTableTemplate();
		//获取第一个sheet
		sheet =  (Sheet) wb.getSheetAt(0);
		//		sheetIRR =  (Sheet) wb.getSheetAt(3);
		//获取最大行数
		int rownum = ((org.apache.poi.ss.usermodel.Sheet) sheet).getPhysicalNumberOfRows();
		//获取第一行

		//承租人信息
		row = (Row) ((org.apache.poi.ss.usermodel.Sheet) sheet).getRow(2);
		String renterName = row.getCell(1).toString();
		String phoneNumber = row.getCell(3).toString();
		String guarantor = row.getCell(6).toString();
		String guarantorPhone = row.getCell(9).toString();
		att.setRenterName(renterName);
		att.setPhoneNumber(phoneNumber);
		att.setGuarantor(guarantor);
		att.setGuarantorPhone(guarantorPhone);
		row = (Row) ((org.apache.poi.ss.usermodel.Sheet) sheet).getRow(3);
		String id = row.getCell(1).toString();
		String idAddress = row.getCell(3).toString();
		String guarantorId = row.getCell(6).toString();
		String guarantorIdAddress = row.getCell(8).toString();
		att.setId(id);
		att.setIdAddress(idAddress);
		att.setGuarantorId(guarantorId);
		att.setGuarantorIdAddress(guarantorIdAddress);
		row = (Row) ((org.apache.poi.ss.usermodel.Sheet) sheet).getRow(4);
		String realAddress = row.getCell(1).toString();
		String guarantorRealAddress = row.getCell(6).toString();
		att.setRealAddress(realAddress);
		att.setGuarantorRealAddress(guarantorRealAddress);

		row = (Row) ((org.apache.poi.ss.usermodel.Sheet) sheet).getRow(6);
		Cell cell = row.getCell(1);
		String applyId = "";
		if (null != cell) {
			switch(cell.getCellType()){
			case NUMERIC: // 数字
				DecimalFormat df = new DecimalFormat("0");
				applyId = df.format(cell.getNumericCellValue());
				break;
			default:
				applyId = cell.getStringCellValue();
				break;

			}
		}
		//		String applyId = row.getCell(1).toString();
		String contractId = row.getCell(6).toString();
		att.setContractId(applyId);
		row = (Row) ((org.apache.poi.ss.usermodel.Sheet) sheet).getRow(7);
		String carFee = df.format(Double.parseDouble(row.getCell(1).toString()));
		String ensureAmount = row.getCell(3).toString();//保险金额
		String purchaseTax = df.format(Double.parseDouble(row.getCell(6).toString()));//
		String otherFee = String.valueOf(df.format(Double.parseDouble(ensureAmount) + 1500));//保险金额+1500
		att.setCarFee(carFee);
		att.setPurchaseTax(purchaseTax);
		att.setOtherFee(otherFee);
		row = (Row) ((org.apache.poi.ss.usermodel.Sheet) sheet).getRow(9);
		String totalAmount = String.valueOf(df.format(Double.parseDouble(carFee)+Double.parseDouble(ensureAmount)+Double.parseDouble(purchaseTax)+1620));//
		String totalAmountInBig = digitUppercase(Double.parseDouble(df.format(Double.parseDouble(totalAmount))));//
		String initPayment = df.format(Double.parseDouble(row.getCell(3).toString()));//
		String initPaymentInBig = digitUppercase(Double.parseDouble(df.format(Double.parseDouble(initPayment))));//
		att.setTotalAmount(String.valueOf(df.format(Double.parseDouble(carFee)+Double.parseDouble(ensureAmount)+Double.parseDouble(purchaseTax)+1620)));
		att.setInitPayment(initPayment);
		att.setTotalAmountInBig(totalAmountInBig);
		att.setInitPaymentInBig(initPaymentInBig);

		row = (Row) ((org.apache.poi.ss.usermodel.Sheet) sheet).getRow(10);
		String financialAmount = row.getCell(1).toString();//
		att.setFinancialAmount(financialAmount);

		row = (Row) ((org.apache.poi.ss.usermodel.Sheet) sheet).getRow(20);
		//		String rentPerMon = df.format(Double.parseDouble(row.getCell(5).toString()));
		//		String rentPerMon = df.format(Double.parseDouble(row.getCell(5).toString()));//
		//		String rentPerMonInBig = digitUppercase(Double.parseDouble(df.format(Double.parseDouble(rentPerMon))));//
		//		att.setRentPerMon(rentPerMon);
		//		att.setRentPerMonInBig(rentPerMonInBig);
		att.setFinancialAmount(String.valueOf(df.format(Double.parseDouble(totalAmount) - Double.parseDouble(initPayment))));
		att.setFinancialAmountInBig((digitUppercase(Double.parseDouble(df.format(Double.parseDouble(att.getFinancialAmount()))))));
		row = (Row) ((org.apache.poi.ss.usermodel.Sheet) sheet).getRow(11);
		String carBrand = row.getCell(1).toString();//
		String carType = row.getCell(3).toString();//
		Cell engineCell = row.getCell(10);
		String engineNumber="";
		if(null != engineCell){
			switch(engineCell.getCellType()){
			case NUMERIC: // 数字
				DecimalFormat df = new DecimalFormat("0");
				engineNumber = df.format(engineCell.getNumericCellValue());
				break;
			default:
				engineNumber = engineCell.getStringCellValue();
				break;

			}
		}
		att.setCarBrand(carBrand);
		att.setCarType(carType);
		att.setEngineNumber(engineNumber);

		row = (Row) ((org.apache.poi.ss.usermodel.Sheet) sheet).getRow(12);
		String carCorlor  = row.getCell(1).toString();//
		String VIN  = row.getCell(3).toString();//
		String carBoard  = row.getCell(5).toString();//
		String carProducer   = row.getCell(10).toString();//
		att.setCarCorlor(carCorlor);
		att.setVIN(VIN);
		att.setCarBoard(carBoard);
		att.setCarProducer(carProducer);

		row = (Row) ((org.apache.poi.ss.usermodel.Sheet) sheet).getRow(15);
		String carSeller   = row.getCell(1).toString();//
		att.setCarSeller(carSeller);
		row = (Row) ((org.apache.poi.ss.usermodel.Sheet) sheet).getRow(18);
		String cardNo   = row.getCell(1).toString();//
		String openAccountBankName   = row.getCell(5).toString();//
		att.setCardNo(cardNo);
		att.setOpenAccountBankName(openAccountBankName);
		for(int i = 0; i < rownum; i++){/*
        	//获取每一行
        	row =  sheet.getRow(i);
        	if(row != null){
                //获取最大列数
                int colnum = row.getPhysicalNumberOfCells();

	                for (int j=0;j<colnum;j++){
//	                	System.out.println("第" + i + "行" + ",第" + j + "列");
	                	 Cell cell = row.getCell(j);
	                	 if(null != cell){
	                		 cellData =  row.getCell(j).toString();
	                		 System.out.println("第" + i + "行" + ",第" + j + "列" + cellData);
	                	 } else {
	                		 break;
	                	 }


	                }
        }
		 */}
		return (ITemplate) att;
	}
	private String getCellFormatValue(Cell cell) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String digitUppercase(double n) {
		String fraction[] = {"角", "分"};
		String digit[] = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
		String unit[][] = {{"元", "万", "亿"}, {"", "拾", "佰", "仟"}};

		String head = n < 0 ? "负" : "";
		n = Math.abs(n);

		String s = "";
		for (int i = 0; i < fraction.length; i++) {
			s += (digit[(int) (Math.floor(n * 10 * Math.pow(10, i)) % 10)] + fraction[i]).replaceAll("(零.)+", "");
		}
		if (s.length() < 1) {
			s = "整";
		}
		int integerPart = (int) Math.floor(n);

		for (int i = 0; i < unit[0].length && integerPart > 0; i++) {
			String p = "";
			for (int j = 0; j < unit[1].length && n > 0; j++) {
				p = digit[integerPart % 10] + unit[1][j] + p;
				integerPart = integerPart / 10;
			}
			s = p.replaceAll("(零.)*零$", "").replaceAll("^$", "零") + unit[0][i] + s;
		}
		return head + s.replaceAll("(零.)*零元", "元").replaceFirst("(零.)+", "").replaceAll("(零.)+", "零").replaceAll("^整$", "零元整");
	}
	// 复制文件
	public static void copyFile(File sourceFile, File targetFile) throws IOException {
		BufferedInputStream inBuff = null;
		BufferedOutputStream outBuff = null;
		try {
			// 新建文件输入流并对它进行缓冲
			inBuff = new BufferedInputStream(new FileInputStream(sourceFile));

			// 新建文件输出流并对它进行缓冲
			outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));

			// 缓冲数组
			byte[] b = new byte[1024 * 5];
			int len;
			while ((len = inBuff.read(b)) != -1) {
				for(int i = 0; i < 1024*5;i++){

				}
				outBuff.write(b, 0, len);
			}
			// 刷新此缓冲的输出流
			outBuff.flush();
		} finally {
			// 关闭流
			if (inBuff != null)
				inBuff.close();
			if (outBuff != null)
				outBuff.close();
		}
	}

	public static boolean replaceAndGenerateWord(String srcPath,String destPath, Map<String, String> map,boolean adjustTableFlag) {
		String[] sp = srcPath.split("\\.");
		String[] dp = destPath.split("\\.");
		if ((sp.length > 0) && (dp.length > 0)) {// 判断文件有无扩展名
			// 比较文件扩展名
			if (sp[sp.length - 1].equalsIgnoreCase("docx")) {
				try {
					XWPFDocument document = new XWPFDocument(POIXMLDocument.openPackage(srcPath));
					// 替换段落中的指定文字
					Iterator<XWPFParagraph> itPara = document.getParagraphsIterator();
					while (itPara.hasNext()) {
						XWPFParagraph paragraph = (XWPFParagraph) itPara.next();
						List<XWPFRun> runs = paragraph.getRuns();
							
						for (int i = 0; i < runs.size(); i++) {
							XWPFRun run = runs.get(i);
							if(null == run){
								continue;
							}
							int runpos = run.getTextPosition();
							if(runpos > 0){//越界  临时跳过
								continue;
							}
							String oneparaString = run.getText(runpos);
//							String oneparaString = runs.get(i).getText(runs.get(i).getTextPosition());
							if(oneparaString != null){
								List<Map.Entry<String, String>> infoIds =
										new ArrayList<Map.Entry<String, String>>(map.entrySet());


								//排序
								Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {   
									public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {      
										//return (o2.getValue() - o1.getValue()); 
										return ((o1.getKey()).toString().compareTo(o2.getKey()))*(-1);
									}
								}); 

								for (int infoIdsIndex = 0; infoIdsIndex < infoIds.size(); infoIdsIndex++) {
									String key = infoIds.get(infoIdsIndex).getKey() == null?" ":infoIds.get(infoIdsIndex).getKey();
									String value = infoIds.get(infoIdsIndex).getValue() == null?" ":infoIds.get(infoIdsIndex).getValue();
									oneparaString = oneparaString.replace(key, value);
								}
								runs.get(i).setText(oneparaString, 0);
							}
						}
					}


					// 替换表格中的指定文字
					Iterator<XWPFTable> itTable = document.getTablesIterator();
					while (itTable.hasNext()) {
						XWPFTable table = (XWPFTable) itTable.next();
						System.out.println("table width Type:" +  table.getWidthType() + "table width:"+table.getWidth());
						if(adjustTableFlag){
							table.setWidth(11000);
						}
						
						int rcount = table.getNumberOfRows();
						for (int i = 0; i < rcount; i++) {
							XWPFTableRow row = table.getRow(i);
							List<XWPFTableCell> cells = row.getTableCells();
							for (XWPFTableCell cell : cells) {
								String cellTextString = cell.getText();
								for (Entry<String, String> e : map.entrySet()) {
									if(e.getKey() == null || e.getValue() == null){
										continue;
									}
									if (cellTextString.contains(e.getKey()))
										cellTextString = cellTextString.replace(e.getKey(),e.getValue());
								}
								if(cellTextString.isEmpty()){//表格中的图片不需要替换
									continue;
								}
								//除去单元格原来的段落
								List<XWPFParagraph> listp = cell.getParagraphs();
								int lpsize = listp.size();
								for(int pi=0;pi<lpsize;pi++){
									cell.removeParagraph(0);
								}
								cell.setText(cellTextString);
							}
						}
					}
					FileOutputStream outStream = null;
					outStream = new FileOutputStream(destPath);
					document.write(outStream);
					outStream.close();
					return true;
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}


			} else
				// doc只能生成doc，如果生成docx会出错
				if ((sp[sp.length - 1].equalsIgnoreCase("doc"))
						&& (dp[dp.length - 1].equalsIgnoreCase("doc"))) {
					HWPFDocument document = null;
					try {
						document = new HWPFDocument(new FileInputStream(srcPath));
						Range range = document.getRange();
						for (Map.Entry<String, String> entry : map.entrySet()) {
							if(entry.getValue() != null){
								range.replaceText(entry.getKey(), entry.getValue());
							}
							
						}
						FileOutputStream outStream = null;
						outStream = new FileOutputStream(destPath);
						document.write(outStream);
						outStream.close();
						return true;
					} catch (FileNotFoundException e) {
						e.printStackTrace();
						return false;
					} catch (IOException e) {
						e.printStackTrace();
						return false;
					}
				} else {
					return false;
				}
		} else {
			return false;
		}
	}
}
