package ycyContractGernarator;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileSystemView;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import parser.ApprovalJZTableParser;
import parser.ApprovalTableParser;
import parser.IrrParser;
import parser.MainRuleParser;
import parser.imp.ITemplateParser;
import template.imp.ApprovalTableTemplate;
import template.imp.ApprovalJZTableTemplate;
import template.imp.IrrTemplate;
import template.imp.MainRuleTemplate;
public class YcyContractGernarator {
	DecimalFormat    df   = new DecimalFormat("#.##");
	Double[][] yyjRate = {{0.14,0.14,0.14,0.14,0.14,0.145},
						  {0.23,0.24,0.25,0.26,0.27,0.28},
						  {0.22,0.23,0.24,0.25,0.26,0.27},
						  {0.16,0.18,0.20,0.25,0.3,0.6},
						  {0.30,0.31,0.33,0.38,0.52,0.6},
						  {0.28,0.29,0.30,0.35,0.46,0.6}};
	String filePath = "D:\\ycy\\";
	String approvalTableName = filePath + "廖江网约车审批表（风控审核）.xlsx";
	String templatePath = "D:\\ycy\\1-融资租赁合同(滴滴版）4份修.docx";
	String payContractTemplatePath = "D:\\ycy\\3-月供代扣委托授权书（商户与持卡人）.docx";
	String destFilePath = "D:\\ycy\\廖江合同.doc";
	String payDestFilePath = "D:\\ycy\\廖江代扣协议.doc";
	String pwdPath = System.getProperty("user.dir");
	String fkshbPath = "";
	String htscPath = "";
	
	//风控审核
	String pwdTemplate1 = pwdPath + "\\1-融资租赁合同(滴滴版)4份修.docx";
	String pwdTemplate2 = pwdPath + "\\3-月供代扣委托授权书（商户与持卡人）.docx";
	String pwdTemplate3 = pwdPath + "\\2-融资租赁合同补充协议（滴滴版）3份.docx";
	String pwdTemplate4 = pwdPath + "\\4-承租人申明-针对大宗客户（滴滴版网约车）1份.doc";
	String pwdTemplate5 = pwdPath + "\\5-授 权 书（大数据）1份.docx";
	String pwdTemplate6 = pwdPath + "\\6-客户资料移交清单.doc";
	String pwdTemplate7 = pwdPath + "\\7-反欺诈.docx";
	//经租风控
	String pwdJZTemplate1 = pwdPath + "\\1-经租合同（单班）-4份.docx";
	String pwdJZTemplate2 = pwdPath + "\\2-经营性租赁网约车版补充合同2份.docx";
//	File file = new File(pwdPath);
	File file = new File(pwdPath);
	static File userChooseDir;
	String vercode = "";
	JTextField varCodeText;
//	double rateTable[][] = [[0.14,0.14,0.14,0.14,0.14,0.14,5],[]];
	public static void main(String[] args) throws IOException{


		// TODO Auto-generated method stub
		YcyContractGernarator cg = new YcyContractGernarator();
		System.out.println("合同模板解析生成器开始----------");
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println(System.getProperty("user.dir"));//user.dir指定了当前的路径 
        System.out.println(df.format(0));
		cg.init();
		System.out.println("合同模板解析生成器完成----------");

	}
	
	private void init() throws IOException {
		
	// TODO Auto-generated method stub
	     // 1. 创建一个顶层容器（窗口）
        final JFrame jf = new JFrame("风控模板解析合同生成");          // 创建窗口
        jf.setSize(600, 500);                       // 设置窗口大小
        jf.setLocationRelativeTo(null);             // 把窗口位置设置到屏幕中心
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // 当点击窗口的关闭按钮时退出程序（没有这一句，程序不会退出）
        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER,100,100);
        // 2. 创建中间容器（面板容器）
        JPanel panel = new JPanel();                // 创建面板容器，使用默认的布局管理器
        panel.setLayout(flowLayout);
        // 3. 创建一个基本组件（按钮），并添加到 面板容器 中
        JButton btn1 = new JButton("选择风控文件夹");
        JButton btn2 = new JButton("选择合同生成文件夹");
        JButton btn3 = new JButton("开始生成");
        JLabel varCodeLabel = new JLabel("请输入验证码");
        varCodeText = new JTextField("验证码",10);
        btn1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
		        JFileChooser fileChooser = new JFileChooser();
		        FileSystemView fsv = FileSystemView.getFileSystemView();  //注意了，这里重要的一句
		        System.out.println(fsv.getHomeDirectory());                //得到桌面路径
		        fileChooser.setCurrentDirectory(fsv.getHomeDirectory());
		        fileChooser.setDialogTitle("请选择要上传的文件...");
		        fileChooser.setApproveButtonText("确定");
		        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		        int result = fileChooser.showOpenDialog(jf);
		        if (JFileChooser.APPROVE_OPTION == result) {
		        	fkshbPath = fileChooser.getSelectedFile().getPath();
		            	   System.out.println("fkshbPath: "+fkshbPath);
		            	   file = new File(fkshbPath);
		        }
			}
        	
        });
        
        btn2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
		        JFileChooser fileChooser = new JFileChooser();
		        FileSystemView fsv = FileSystemView.getFileSystemView();  //注意了，这里重要的一句
		        System.out.println(fsv.getHomeDirectory());                //得到桌面路径
		        fileChooser.setCurrentDirectory(fsv.getHomeDirectory());
		        fileChooser.setDialogTitle("请选择要上传的文件...");
		        fileChooser.setApproveButtonText("确定");
		        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		        int result = fileChooser.showOpenDialog(jf);
		        if (JFileChooser.APPROVE_OPTION == result) {
		        	htscPath=fileChooser.getSelectedFile().getPath();
		            	   System.out.println("htscPath: "+htscPath);
		        }
			}
        	
        });
        
        btn3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					start();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        	
        });
        panel.add(btn1);
        panel.add(btn2);
        
        panel.add(btn3);
        panel.add(varCodeLabel);
        panel.add(varCodeText);
        // 4. 把 面板容器 作为窗口的内容面板 设置到 窗口
        jf.setContentPane(panel);
        // 5. 显示窗口，前面创建的信息都在内存中，通过 jf.setVisible(true) 把内存中的窗口显示在屏幕上。
        jf.setVisible(true);
	
}

	/*
	 * 程序入口
	 */
	public void start() throws IOException{
		String varCode = requestVerCode().trim();
		String inputVarCode = this.varCodeText.getText().trim();
		System.out.println("request code: "+varCode+"input code:"+inputVarCode);
		System.out.println(varCode.equals(inputVarCode));
		if(!varCode.equals(inputVarCode)){
			//验证码错误
			JOptionPane.showMessageDialog(null,"验证码错误，请联系管理员");
			return;
		}
    	if(file.exists()){
    		
    		File[] files = file.listFiles();
	    	for(File fileName:files){
	    		if(fileName.getName().endsWith(".xlsx")){//风控审批表 或者 经营租赁审核表
	    			String[] ss = fileName.getAbsolutePath().replace("网约车审批表", "").replace("（风控审核）", "").split("\\.");
	    			
	    			String userName = fileName.getAbsolutePath().replace("网约车审批表", "").replace("（风控审核）", "").replace("经租","").trim().split("\\.")[0];
	    			System.out.println(userName);
	    			File userFilePath = new File(userName);
	    			if(!userFilePath.exists()){
	    				userFilePath.mkdir();
	    			}
	    			String[] userNameArray = userName.split("\\\\");
	    			userName = userNameArray[userNameArray.length-1];
	    			String userNamePath = userFilePath.getAbsolutePath()+File.separator;
//	    			System.out.println("1-".concat((userNamePath+"主合同.doc")));
	    			if(fileName.getName().contains("经租")){
	    				approvalJZTableParse(fileName.getAbsolutePath(),(userNamePath+"1-"+userName+"经租合同.doc"),(userNamePath+"3-"+userName+"代扣协议.doc"),(userNamePath+"2-"+userName+"经租补充合同.doc"),(userNamePath+"4-"+userName+"承租人声明.doc"),(userNamePath+"5-"+userName+"大数据.doc"),(userNamePath+"6-"+userName+"资料移交清单.doc"),userNamePath+"7-反欺诈.docx");
	    			} else {
	    				approvalTableParse(fileName.getAbsolutePath(),(userNamePath+"1-"+userName+"主合同.doc"),(userNamePath+"3-"+userName+"代扣协议.doc"),(userNamePath+"2-"+userName+"补充协议.doc"),(userNamePath+"4-"+userName+"承租人声明.doc"),(userNamePath+"5-"+userName+"大数据.doc"),(userNamePath+"6-"+userName+"资料移交清单.doc"),userNamePath+"7-反欺诈.docx");
	    			}
	    			
	    		}
	    	}
    	}
	}
	
	/*
	 * 
	 * 
	 */
	public void approvalJZTableParse(String approvaljzTableName,String zht,String dkxy,String bcxy,String czsm,String dsj,String yjqd,String fqz){
		File f = new File(approvaljzTableName);
		if(f.exists()){

			ITemplateParser atp = new ApprovalJZTableParser(approvaljzTableName);
			ApprovalJZTableTemplate ajztt = (ApprovalJZTableTemplate) atp.parse();
			System.out.println(ajztt.toString());
			Map<String,String> map = new HashMap();
			Map<String,String> map2 = new HashMap();
			if(ajztt.getGuarantor() == null || ajztt.getGuarantor().isEmpty()){
				map.put("totalNum", "肆");
				map.put("tokenPromiseNum", "零");
				map.put("addedContractNum", "贰");
			} else {
				map.put("totalNum", "伍");
				map.put("tokenPromiseNum", "壹");
				map.put("addedContractNum", "叁");
			}
			map.put("contractNo", ajztt.getContractId());
			map.put("renterName", ajztt.getRenterName());
			map.put("phoneNumber", ajztt.getPhoneNumber());
			map.put("idAddress", ajztt.getIdAddress());
			map.put("id", ajztt.getId());
			map.put("realAddress", ajztt.getRealAddress());
			map.put("guarantorPhone", ajztt.getGuarantorPhone());
			map.put("guarantorIdAddress", ajztt.getGuarantorIdAddress());
			map.put("guarantorPhone", ajztt.getGuarantorPhone());
			map.put("guarantorId", ajztt.getGuarantorId());
			map.put("guarantorPhone", ajztt.getGuarantorPhone());
			map.put("guarantorRealAddress", ajztt.getGuarantorRealAddress());
			map.put("promiseName", ajztt.getGuarantor());
			map.put("carBrand", ajztt.getCarBrand());
			map.put("carType", ajztt.getCarType());
			map.put("carProducer", ajztt.getCarProducer());
			map.put("carSeller", ajztt.getCarSeller());
			map.put("carProducer", ajztt.getCarProducer());
			map.put("carCorlor", ajztt.getCarCorlor());
			map.put("carProducer", ajztt.getCarProducer());
			map.put("engineNumber", ajztt.getEngineNumber());
			map.put("carProducer", ajztt.getCarProducer());
			map.put("VIN", ajztt.getVIN());
			map.put("carBoard", ajztt.getCarBoard());
			map.put("rentAmt", df.format(Double.parseDouble(ajztt.getRentAmt())));
			map.put("rentMonth", ajztt.getRentMonth());
			map.put("deposit", ajztt.getDeposit());
			map.put("contractNo", ajztt.getContractId());
			map.put("openAccountBankName", ajztt.getOpenAccountBankName());
			map.put("cardNo", ajztt.getCardNo());
			map.put("guarantorIdAddress", ajztt.getGuarantorIdAddress());
			map.put("guarantorId", ajztt.getGuarantorId());
			map.put("guarantorPhone", ajztt.getGuarantorPhone());
			map.put("guarantorRealAddress", ajztt.getGuarantorRealAddress());
			map.put("promiseName", ajztt.getPromiseName());
			Calendar calendar = Calendar.getInstance();
			String beginData = calendar.get(Calendar.YEAR) + " 年 " + (calendar.get(Calendar.MONTH)+1) + " 月 " + calendar.get(Calendar.DATE) + " 日 ";
			map.put("beginDate", beginData);
			//map.put("cardNo", ajztt.getCardNo());
			
			System.out.print(map.toString());
			ApprovalTableParser.replaceAndGenerateWord(pwdJZTemplate1, zht, map,true);
			ApprovalTableParser.replaceAndGenerateWord(pwdTemplate2, dkxy, map,false);
			ApprovalTableParser.replaceAndGenerateWord(pwdJZTemplate2, bcxy, map,false);
			ApprovalTableParser.replaceAndGenerateWord(pwdTemplate4, czsm, map,false);
			ApprovalTableParser.replaceAndGenerateWord(pwdTemplate5, dsj, map,false);
			ApprovalTableParser.replaceAndGenerateWord(pwdTemplate6, yjqd, map,false);
			ApprovalTableParser.replaceAndGenerateWord(pwdTemplate7, fqz, map2,false);

		
		} else {
			System.out.println("合同审批表不存在，路径：" + approvaljzTableName);
		}
		return;
	}
	/*
	 * 审批表格解析
	 */
	public void approvalTableParse(String approvalTableName,String zht,String dkxy,String bcxy,String czsm,String dsj,String yjqd,String fqz) throws IOException{
		File f = new File(approvalTableName);
		if(f.exists()){
			ITemplateParser atp = new ApprovalTableParser(approvalTableName);
			ApprovalTableTemplate att = (ApprovalTableTemplate) atp.parse();
			
			MainRuleParser mrp = new MainRuleParser(approvalTableName);
			MainRuleTemplate mr = mrp.parser();
			
			IrrParser  irrtp = new IrrParser(approvalTableName);
			irrtp.setA4(att.getFinancialAmount());
			IrrTemplate irrt = (IrrTemplate) irrtp.parse();
			
//			atp.copyFile(new File("D:\\ycy\\1-融资租赁合同(滴滴版）4份.docx"), new File("D:\\ycy\\temp.doc"));
			String[][] irr = irrt.getIrr();
			String[][] wyj = calcWYJ(irr);
//			System.out.println(irrt.toString());
			Map<String,String> map = new HashMap();
			Map<String,String> map2 = new HashMap();
			if(att.getGuarantor().isEmpty()){
				map.put("totalNum", "肆");
				map.put("tokenPromiseNum", "零");
				map.put("addedContractNum", "贰");
			} else {
				map.put("totalNum", "伍");
				map.put("tokenPromiseNum", "壹");
				map.put("addedContractNum", "叁");
			}
//			map.put("C00", );
			for(int i=0;i<wyj.length;i++){
				for(int j=0;j<wyj[i].length;j++){
					map.put("C"+i+j, wyj[i][j]);
				}
			}
			map.put("contractNo", att.getContractId());
			map.put("renterName", att.getRenterName());
			map.put("phoneNumber", att.getPhoneNumber());
			map.put("idAddress", att.getIdAddress());
			map.put("id", att.getId());
			map.put("realAddress", att.getRealAddress());
			map.put("guarantorPhone", att.getGuarantorPhone());
			map.put("guarantorIdAddress", att.getGuarantorIdAddress());
			map.put("guarantorPhone", att.getGuarantorPhone());
			map.put("guarantorId", att.getGuarantorId());
			map.put("guarantorPhone", att.getGuarantorPhone());
			map.put("guarantorRealAddress", att.getGuarantorRealAddress());
			map.put("promiseName", att.getGuarantor());
			map.put("carBrand", att.getCarBrand());
			map.put("carType", att.getCarType());
			map.put("carProducer", att.getCarProducer());
			map.put("carSeller", att.getCarSeller());
			map.put("carProducer", att.getCarProducer());
			map.put("carCorlor", att.getCarCorlor());
			map.put("carProducer", att.getCarProducer());
			map.put("engineNumber", att.getEngineNumber());
			map.put("carProducer", att.getCarProducer());
			map.put("VIN", att.getVIN());
			map.put("carBoard", att.getCarBoard());
			map.put("totalAmount", att.getTotalAmount());
			map.put("totalAmountInBig", att.getTotalAmountInBig());
			map.put("carFee", att.getCarFee());
			map.put("purchaseTax", att.getPurchaseTax());
			map.put("financialAmount", att.getFinancialAmount());
			map.put("financialAmountInBig", att.getFinancialAmountInBig());
			map.put("otherFee", att.getOtherFee());
			map.put("financialAmountInBig", att.getFinancialAmountInBig());
			map.put("initPayment", att.getInitPayment());
			map.put("initPaymentInBig", att.getInitPaymentInBig());
			map.put("rentPerMon", df.format(Double.parseDouble(irr[0][2])));
			System.out.println(irr[0][2]);
			map.put("rentPerMonInBig", ApprovalTableParser.digitUppercase(Double.valueOf(df.format(Double.parseDouble(irr[0][2])))));
			map.put("openAccountBankName", att.getOpenAccountBankName());
			map.put("cardNo", att.getCardNo());
			System.out.print(map.toString());
			ApprovalTableParser.replaceAndGenerateWord(pwdTemplate1, zht, map,true);
			ApprovalTableParser.replaceAndGenerateWord(pwdTemplate2, dkxy, map,false);
			ApprovalTableParser.replaceAndGenerateWord(pwdTemplate3, bcxy, map,false);
			ApprovalTableParser.replaceAndGenerateWord(pwdTemplate4, czsm, map,false);
			ApprovalTableParser.replaceAndGenerateWord(pwdTemplate5, dsj, map,false);
			ApprovalTableParser.replaceAndGenerateWord(pwdTemplate6, yjqd, map,false);
			ApprovalTableParser.replaceAndGenerateWord(pwdTemplate7, fqz, map2,false);

		} else {
			System.out.println("合同审批表不存在，路径：" + approvalTableName);
		}
	}
	
	public String[][] calcWYJ(String[][] irr){
			String[][] yyj = new String[6][6];
			for(int i=0;i<6;i++){
				for(int j=0;j<6;j++){
					if(i>=3&&j==5){
						yyj[i][j]=df.format(Double.parseDouble(yyj[i][j-1])*yyjRate[i][j]);
					} else {
						yyj[i][j] = df.format(sum(irr,3*(j+1)+(i>=3?18:0),35)*yyjRate[i][j]);
					}
					
				}
			}
			return yyj;
	}
	
	public double sum(String[][] irr,int start,int end){
		double ret = 0.0;
		for(int i=start;i<=end;i++){
			ret += Double.parseDouble(irr[i][4]);
		}
		return ret;
	}
	
	public String requestVerCode() throws IOException{
		
		CloseableHttpClient client = null;
		CloseableHttpResponse response = null;
		try{
			client = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet("http://39.105.145.217:8080" + "/DynamicVerificationCode/GenerateCode");
			response = client.execute(httpGet);
			HttpEntity entity = response.getEntity();
			vercode = EntityUtils.toString(entity);
			
		}catch (Exception e){
			e.printStackTrace();
		} finally{
            if (response != null) {
                response.close();
            }
            if (client != null) {
                client.close();
            }
		} 
		return vercode;
	}
}
