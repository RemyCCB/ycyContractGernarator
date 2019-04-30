package template.imp;

import template.ITemplate;

public class ApprovalJZTableTemplate implements ITemplate{
	String renterName;//承租人（姓名）
	String phoneNumber;//
	String idAddress;//
	String id;//
	String realAddress;//
	String guarantor;//
	String promiseName;//
	String openAccountBankName;
	String cardNo;
	public String getPromiseName() {
		return promiseName;
	}
	public void setPromiseName(String promiseName) {
		this.promiseName = promiseName;
	}
	public String getOpenAccountBankName() {
		return openAccountBankName;
	}
	public void setOpenAccountBankName(String openAccountBankName) {
		this.openAccountBankName = openAccountBankName;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	
	@Override
	public String toString() {
		return "ApprovalJZTableTemplate [renterName=" + renterName
				+ ", phoneNumber=" + phoneNumber + ", idAddress=" + idAddress
				+ ", id=" + id + ", realAddress=" + realAddress
				+ ", guarantor=" + guarantor + ", promiseName=" + promiseName
				+ ", openAccountBankName=" + openAccountBankName + ", cardNo="
				+ cardNo + ", guarantorPhone=" + guarantorPhone
				+ ", guarantorIdAddress=" + guarantorIdAddress
				+ ", guarantorId=" + guarantorId + ", guarantorRealAddress="
				+ guarantorRealAddress + ", carBrand=" + carBrand
				+ ", carType=" + carType + ", carProducer=" + carProducer
				+ ", carSeller=" + carSeller + ", carCorlor=" + carCorlor
				+ ", engineNumber=" + engineNumber + ", contractId="
				+ contractId + ", VIN=" + VIN + ", carBoard=" + carBoard
				+ ", rentAmt=" + rentAmt + ", rentMonth=" + rentMonth
				+ ", beginDate=" + beginDate + ", deposit=" + deposit
				+ ", paperNum=" + paperNum + ", getPromiseName()="
				+ getPromiseName() + ", getOpenAccountBankName()="
				+ getOpenAccountBankName() + ", getCardNo()=" + getCardNo()
				+ ", getContractId()=" + getContractId() + ", getRenterName()="
				+ getRenterName() + ", getPhoneNumber()=" + getPhoneNumber()
				+ ", getIdAddress()=" + getIdAddress() + ", getId()=" + getId()
				+ ", getRealAddress()=" + getRealAddress()
				+ ", getGuarantor()=" + getGuarantor()
				+ ", getGuarantorPhone()=" + getGuarantorPhone()
				+ ", getGuarantorIdAddress()=" + getGuarantorIdAddress()
				+ ", getGuarantorId()=" + getGuarantorId()
				+ ", getGuarantorRealAddress()=" + getGuarantorRealAddress()
				+ ", getCarBrand()=" + getCarBrand() + ", getCarType()="
				+ getCarType() + ", getCarProducer()=" + getCarProducer()
				+ ", getCarSeller()=" + getCarSeller() + ", getCarCorlor()="
				+ getCarCorlor() + ", getEngineNumber()=" + getEngineNumber()
				+ ", getVIN()=" + getVIN() + ", getCarBoard()=" + getCarBoard()
				+ ", getRentAmt()=" + getRentAmt() + ", getRentMonth()="
				+ getRentMonth() + ", getBeginDate()=" + getBeginDate()
				+ ", getDeposit()=" + getDeposit() + ", getPaperNum()="
				+ getPaperNum() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

	String guarantorPhone;//
	String guarantorIdAddress ;//
	String guarantorId;//
	String guarantorRealAddress;//
	String carBrand;//
	String carType;//
	String carProducer;//
	String carSeller;//
	String carCorlor;//
	String engineNumber;//
	String contractId;//合同
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public String getRenterName() {
		return renterName;
	}
	public void setRenterName(String renterName) {
		this.renterName = renterName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getIdAddress() {
		return idAddress;
	}
	public void setIdAddress(String idAddress) {
		this.idAddress = idAddress;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRealAddress() {
		return realAddress;
	}
	public void setRealAddress(String realAddress) {
		this.realAddress = realAddress;
	}
	public String getGuarantor() {
		return guarantor;
	}
	public void setGuarantor(String guarantor) {
		this.guarantor = guarantor;
	}
	public String getGuarantorPhone() {
		return guarantorPhone;
	}
	public void setGuarantorPhone(String guarantorPhone) {
		this.guarantorPhone = guarantorPhone;
	}
	public String getGuarantorIdAddress() {
		return guarantorIdAddress;
	}
	public void setGuarantorIdAddress(String guarantorIdAddress) {
		this.guarantorIdAddress = guarantorIdAddress;
	}
	public String getGuarantorId() {
		return guarantorId;
	}
	public void setGuarantorId(String guarantorId) {
		this.guarantorId = guarantorId;
	}
	public String getGuarantorRealAddress() {
		return guarantorRealAddress;
	}
	public void setGuarantorRealAddress(String guarantorRealAddress) {
		this.guarantorRealAddress = guarantorRealAddress;
	}
	public String getCarBrand() {
		return carBrand;
	}
	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	public String getCarProducer() {
		return carProducer;
	}
	public void setCarProducer(String carProducer) {
		this.carProducer = carProducer;
	}
	public String getCarSeller() {
		return carSeller;
	}
	public void setCarSeller(String carSeller) {
		this.carSeller = carSeller;
	}
	public String getCarCorlor() {
		return carCorlor;
	}
	public void setCarCorlor(String carCorlor) {
		this.carCorlor = carCorlor;
	}
	public String getEngineNumber() {
		return engineNumber;
	}
	public void setEngineNumber(String engineNumber) {
		this.engineNumber = engineNumber;
	}
	public String getVIN() {
		return VIN;
	}
	public void setVIN(String vIN) {
		VIN = vIN;
	}
	public String getCarBoard() {
		return carBoard;
	}
	public void setCarBoard(String carBoard) {
		this.carBoard = carBoard;
	}
	public String getRentAmt() {
		return rentAmt;
	}
	public void setRentAmt(String rentAmt) {
		this.rentAmt = rentAmt;
	}
	public String getRentMonth() {
		return rentMonth;
	}
	public void setRentMonth(String rentMonth) {
		this.rentMonth = rentMonth;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getDeposit() {
		return deposit;
	}
	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}
	public String getPaperNum() {
		return paperNum;
	}
	public void setPaperNum(String paperNum) {
		this.paperNum = paperNum;
	}
	String VIN;//
	String carBoard;//
	String rentAmt;// 月租金
	String rentMonth;//租期 多少个月
	String beginDate;// 开始日期 年月日
	String deposit;//  押金
	String paperNum;// 协议份数
	
}
