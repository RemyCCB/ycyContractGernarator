package template.imp;

import template.ITemplate;

public class MainRuleTemplate implements ITemplate{
	String C00;

	public String getC00() {
		return C00;
	}

	public void setC00(String c00) {
		C00 = c00;
	}

	@Override
	public String toString() {
		return "MainRule [C00=" + C00 + "]";
	}
}