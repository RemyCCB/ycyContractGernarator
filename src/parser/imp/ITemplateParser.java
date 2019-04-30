package parser.imp;

import java.text.DecimalFormat;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import template.ITemplate;

public interface ITemplateParser {
	DecimalFormat    df   = new DecimalFormat("#.##");
	public abstract ITemplate parse();
}
