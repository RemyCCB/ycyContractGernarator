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
	String approvalTableName = filePath + "�ν���Լ�������������ˣ�.xlsx";
	String templatePath = "D:\\ycy\\1-�������޺�ͬ(�εΰ棩4����.docx";
	String payContractTemplatePath = "D:\\ycy\\3-�¹�����ί����Ȩ�飨�̻���ֿ��ˣ�.docx";
	String destFilePath = "D:\\ycy\\�ν���ͬ.doc";
	String payDestFilePath = "D:\\ycy\\�ν�����Э��.doc";
	String pwdPath = System.getProperty("user.dir");
	String fkshbPath = "";
	String htscPath = "";
	
	//������
	String pwdTemplate1 = pwdPath + "\\1-�������޺�ͬ(�εΰ�)4����.docx";
	String pwdTemplate2 = pwdPath + "\\3-�¹�����ί����Ȩ�飨�̻���ֿ��ˣ�.docx";
	String pwdTemplate3 = pwdPath + "\\2-�������޺�ͬ����Э�飨�εΰ棩3��.docx";
	String pwdTemplate4 = pwdPath + "\\4-����������-��Դ��ڿͻ����εΰ���Լ����1��.doc";
	String pwdTemplate5 = pwdPath + "\\5-�� Ȩ �飨�����ݣ�1��.docx";
	String pwdTemplate6 = pwdPath + "\\6-�ͻ������ƽ��嵥.doc";
	String pwdTemplate7 = pwdPath + "\\7-����թ.docx";
	//������
	String pwdJZTemplate1 = pwdPath + "\\1-�����ͬ�����ࣩ-4��.docx";
	String pwdJZTemplate2 = pwdPath + "\\2-��Ӫ��������Լ���油���ͬ2��.docx";
//	File file = new File(pwdPath);
	File file = new File(pwdPath);
	static File userChooseDir;
	String vercode = "";
	JTextField varCodeText;
//	double rateTable[][] = [[0.14,0.14,0.14,0.14,0.14,0.14,5],[]];
	public static void main(String[] args) throws IOException{


		// TODO Auto-generated method stub
		YcyContractGernarator cg = new YcyContractGernarator();
		System.out.println("��ͬģ�������������ʼ----------");
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println(System.getProperty("user.dir"));//user.dirָ���˵�ǰ��·�� 
        System.out.println(df.format(0));
		cg.init();
		System.out.println("��ͬģ��������������----------");

	}
	
	private void init() throws IOException {
		
	// TODO Auto-generated method stub
	     // 1. ����һ���������������ڣ�
        final JFrame jf = new JFrame("���ģ�������ͬ����");          // ��������
        jf.setSize(600, 500);                       // ���ô��ڴ�С
        jf.setLocationRelativeTo(null);             // �Ѵ���λ�����õ���Ļ����
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // ��������ڵĹرհ�ťʱ�˳�����û����һ�䣬���򲻻��˳���
        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER,100,100);
        // 2. �����м����������������
        JPanel panel = new JPanel();                // �������������ʹ��Ĭ�ϵĲ��ֹ�����
        panel.setLayout(flowLayout);
        // 3. ����һ�������������ť��������ӵ� ������� ��
        JButton btn1 = new JButton("ѡ�����ļ���");
        JButton btn2 = new JButton("ѡ���ͬ�����ļ���");
        JButton btn3 = new JButton("��ʼ����");
        JLabel varCodeLabel = new JLabel("��������֤��");
        varCodeText = new JTextField("��֤��",10);
        btn1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
		        JFileChooser fileChooser = new JFileChooser();
		        FileSystemView fsv = FileSystemView.getFileSystemView();  //ע���ˣ�������Ҫ��һ��
		        System.out.println(fsv.getHomeDirectory());                //�õ�����·��
		        fileChooser.setCurrentDirectory(fsv.getHomeDirectory());
		        fileChooser.setDialogTitle("��ѡ��Ҫ�ϴ����ļ�...");
		        fileChooser.setApproveButtonText("ȷ��");
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
		        FileSystemView fsv = FileSystemView.getFileSystemView();  //ע���ˣ�������Ҫ��һ��
		        System.out.println(fsv.getHomeDirectory());                //�õ�����·��
		        fileChooser.setCurrentDirectory(fsv.getHomeDirectory());
		        fileChooser.setDialogTitle("��ѡ��Ҫ�ϴ����ļ�...");
		        fileChooser.setApproveButtonText("ȷ��");
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
        // 4. �� ������� ��Ϊ���ڵ�������� ���õ� ����
        jf.setContentPane(panel);
        // 5. ��ʾ���ڣ�ǰ�洴������Ϣ�����ڴ��У�ͨ�� jf.setVisible(true) ���ڴ��еĴ�����ʾ����Ļ�ϡ�
        jf.setVisible(true);
	
}

	/*
	 * �������
	 */
	public void start() throws IOException{
		String varCode = requestVerCode().trim();
		String inputVarCode = this.varCodeText.getText().trim();
		System.out.println("request code: "+varCode+"input code:"+inputVarCode);
		System.out.println(varCode.equals(inputVarCode));
		if(!varCode.equals(inputVarCode)){
			//��֤�����
			JOptionPane.showMessageDialog(null,"��֤���������ϵ����Ա");
			return;
		}
    	if(file.exists()){
    		
    		File[] files = file.listFiles();
	    	for(File fileName:files){
	    		if(fileName.getName().endsWith(".xlsx")){//��������� ���� ��Ӫ������˱�
	    			String[] ss = fileName.getAbsolutePath().replace("��Լ��������", "").replace("�������ˣ�", "").split("\\.");
	    			
	    			String userName = fileName.getAbsolutePath().replace("��Լ��������", "").replace("�������ˣ�", "").replace("����","").trim().split("\\.")[0];
	    			System.out.println(userName);
	    			File userFilePath = new File(userName);
	    			if(!userFilePath.exists()){
	    				userFilePath.mkdir();
	    			}
	    			String[] userNameArray = userName.split("\\\\");
	    			userName = userNameArray[userNameArray.length-1];
	    			String userNamePath = userFilePath.getAbsolutePath()+File.separator;
//	    			System.out.println("1-".concat((userNamePath+"����ͬ.doc")));
	    			if(fileName.getName().contains("����")){
	    				approvalJZTableParse(fileName.getAbsolutePath(),(userNamePath+"1-"+userName+"�����ͬ.doc"),(userNamePath+"3-"+userName+"����Э��.doc"),(userNamePath+"2-"+userName+"���ⲹ���ͬ.doc"),(userNamePath+"4-"+userName+"����������.doc"),(userNamePath+"5-"+userName+"������.doc"),(userNamePath+"6-"+userName+"�����ƽ��嵥.doc"),userNamePath+"7-����թ.docx");
	    			} else {
	    				approvalTableParse(fileName.getAbsolutePath(),(userNamePath+"1-"+userName+"����ͬ.doc"),(userNamePath+"3-"+userName+"����Э��.doc"),(userNamePath+"2-"+userName+"����Э��.doc"),(userNamePath+"4-"+userName+"����������.doc"),(userNamePath+"5-"+userName+"������.doc"),(userNamePath+"6-"+userName+"�����ƽ��嵥.doc"),userNamePath+"7-����թ.docx");
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
				map.put("totalNum", "��");
				map.put("tokenPromiseNum", "��");
				map.put("addedContractNum", "��");
			} else {
				map.put("totalNum", "��");
				map.put("tokenPromiseNum", "Ҽ");
				map.put("addedContractNum", "��");
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
			String beginData = calendar.get(Calendar.YEAR) + " �� " + (calendar.get(Calendar.MONTH)+1) + " �� " + calendar.get(Calendar.DATE) + " �� ";
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
			System.out.println("��ͬ���������ڣ�·����" + approvaljzTableName);
		}
		return;
	}
	/*
	 * ����������
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
			
//			atp.copyFile(new File("D:\\ycy\\1-�������޺�ͬ(�εΰ棩4��.docx"), new File("D:\\ycy\\temp.doc"));
			String[][] irr = irrt.getIrr();
			String[][] wyj = calcWYJ(irr);
//			System.out.println(irrt.toString());
			Map<String,String> map = new HashMap();
			Map<String,String> map2 = new HashMap();
			if(att.getGuarantor().isEmpty()){
				map.put("totalNum", "��");
				map.put("tokenPromiseNum", "��");
				map.put("addedContractNum", "��");
			} else {
				map.put("totalNum", "��");
				map.put("tokenPromiseNum", "Ҽ");
				map.put("addedContractNum", "��");
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
			System.out.println("��ͬ���������ڣ�·����" + approvalTableName);
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
