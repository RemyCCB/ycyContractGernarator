package template.imp;

import java.util.Arrays;

import template.ITemplate;

public class IrrTemplate implements ITemplate{
	String irr[][] = new String[36][5];
	
	public IrrTemplate(){
		for(int i = 0; i < 36; i++)
			for(int j = 0; j < 5; j++){
				irr[i][j] = null;//negative value is incorrect
			}
	}
	public String[][] getIrr() {
		return irr;
	}
	public void setIrr(String[][] irr) {
		this.irr = irr;
	}
	@Override
	public String toString() {
		return "IrrTemplate [irr=" + Arrays.toString(irr) + "]";
	}
	
}
