package template.imp;

import template.ITemplate;

public class ApprovalTableTemplate implements ITemplate{
	String renterName;//³Ð×âÈË£¨ÐÕÃû£©
	String phoneNumber;//
	String idAddress;//
	String id;//
	String realAddress;//
	String guarantor;//
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
	String VIN;//
	String carBoard;//
	String totalAmount;//
	String totalAmountInBig;//
	String financialAmount;//
	String financialAmountInBig;//
	String rentPerMon;//
	String rentPerMonInBig;//
	String openAccountBankName;//
	String cardNo;//
	String contractId;//
	String carFee;
	String purchaseTax;
	String otherFee;
	String initPayment;
	String initPaymentInBig;
	public String getInitPaymentInBig() {
		return initPaymentInBig;
	}
	public void setInitPaymentInBig(String initPaymentInBig) {
		this.initPaymentInBig = initPaymentInBig;
	}
	public String getInitPayment() {
		return initPayment;
	}
	public void setInitPayment(String initPayment) {
		this.initPayment = initPayment;
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
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getTotalAmountInBig() {
		return totalAmountInBig;
	}
	public void setTotalAmountInBig(String totalAmountInBig) {
		this.totalAmountInBig = totalAmountInBig;
	}
	public String getFinancialAmount() {
		return financialAmount;
	}
	public void setFinancialAmount(String financialAmount) {
		this.financialAmount = financialAmount;
	}
	public String getFinancialAmountInBig() {
		return financialAmountInBig;
	}
	public void setFinancialAmountInBig(String financialAmountInBig) {
		this.financialAmountInBig = financialAmountInBig;
	}
	public String getRentPerMon() {
		return rentPerMon;
	}
	public void setRentPerMon(String rentPerMon) {
		this.rentPerMon = rentPerMon;
	}
	public String getRentPerMonInBig() {
		return rentPerMonInBig;
	}
	public void setRentPerMonInBig(String rentPerMonInBig) {
		this.rentPerMonInBig = rentPerMonInBig;
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
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public String getCarFee() {
		return carFee;
	}
	public void setCarFee(String carFee) {
		this.carFee = carFee;
	}
	public String getPurchaseTax() {
		return purchaseTax;
	}
	public void setPurchaseTax(String purchaseTax) {
		this.purchaseTax = purchaseTax;
	}
	@Override
	public String toString() {
		return "ApprovalTableTemplate [renterName=" + renterName
				+ ", phoneNumber=" + phoneNumber + ", idAddress=" + idAddress
				+ ", id=" + id + ", realAddress=" + realAddress
				+ ", guarantor=" + guarantor + ", guarantorPhone="
				+ guarantorPhone + ", guarantorIdAddress=" + guarantorIdAddress
				+ ", guarantorId=" + guarantorId + ", guarantorRealAddress="
				+ guarantorRealAddress + ", carBrand=" + carBrand
				+ ", carType=" + carType + ", carProducer=" + carProducer
				+ ", carSeller=" + carSeller + ", carCorlor=" + carCorlor
				+ ", engineNumber=" + engineNumber + ", VIN=" + VIN
				+ ", carBoard=" + carBoard + ", totalAmount=" + totalAmount
				+ ", totalAmountInBig=" + totalAmountInBig
				+ ", financialAmount=" + financialAmount
				+ ", financialAmountInBig=" + financialAmountInBig
				+ ", rentPerMon=" + rentPerMon + ", rentPerMonInBig="
				+ rentPerMonInBig + ", openAccountBankName="
				+ openAccountBankName + ", cardNo=" + cardNo + ", contractId="
				+ contractId + ", carFee=" + carFee + ", purchaseTax="
				+ purchaseTax + ", otherFee=" + otherFee + ", initPayment="
				+ initPayment + ", initPaymentInBig=" + initPaymentInBig + "]";
	}
	public String getOtherFee() {
		return otherFee;
	}
	public void setOtherFee(String otherFee) {
		this.otherFee = otherFee;
	}
	
	
}
