package org.fidel.tms.excel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fidel.tms.model.Expense;
import org.fidel.tms.model.FrightOrder;
import org.fidel.tms.utils.TodayDate_YYYYMMDD;

import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class Excel_RevenueExpensePerFon {

	private List<Expense> expRevPerTruckReport;
	
	private WritableCellFormat timesBoldUnderLine;
	private WritableCellFormat times;
	private String inputFile;
	
	public void setOutPutFile(String inputFile){
		String infile = inputFile.replace("\\", "\\\\");		
		this.inputFile = infile;
	}
	
	public byte[] write() throws IOException, WriteException{
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
						 
		WritableWorkbook workbook = Workbook.createWorkbook(baos);
		workbook.createSheet("excel_report", 0);
		
		WritableSheet excelSheet = workbook.getSheet(0);
		
		createLabel(excelSheet);
		createContent(excelSheet);

		workbook.write();
		workbook.close();
		
		return baos.toByteArray();
	}
	
	private void createLabel(WritableSheet sheet) throws WriteException{
		
		WritableFont time10pt = new WritableFont(WritableFont.ARIAL, 10);
		times = new WritableCellFormat(time10pt);
		//times.setWrap(true);
		
		WritableFont times10ptBoldUnderline = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE);
		timesBoldUnderLine = new WritableCellFormat(times10ptBoldUnderline);
		//timesBoldUnderLine.setWrap(true);
		
		CellView cv = new CellView();
		cv.setFormat(times);
		cv.setFormat(timesBoldUnderLine);
		cv.setAutosize(true);
		
		addHeader(sheet, 0, 0, "TILAHUN MESAFENT FRIGHT TRANSPORT", Alignment.CENTRE);
		addTitle(sheet, 0, 1, "Revenue and Expense Per Fright Order", Alignment.CENTRE);
		addTitle(sheet, 0, 2, " ", Alignment.CENTRE);
		
		addCaption(sheet, 0, 3, "No");
		addCaption(sheet, 1, 3, "Fright Order Number");
		addCaption(sheet, 2, 3, "Revenue");
		addCaption(sheet, 3, 3, "Expense");
		addCaption(sheet, 4, 3, "Net Difference");
	}
	
	private void createContent(WritableSheet sheet) throws WriteException, RowsExceededException{
		
		double revenueGrandTotal = 0.0, expenseGrandTotal = 0.0, netDiffTotal = 0.0;
		int counter = 0;
		
		for(int i = 0, j = 4; i < expRevPerTruckReport.size(); i++, j++){ //row
			
			counter = j + 1;
			
			addNumber(sheet, 0, j, i+1);
			addLabel(sheet, 1, j, expRevPerTruckReport.get(i).getFrightOrder().getFon());
			addLabel(sheet, 2, j, String.format("%,.2f", expRevPerTruckReport.get(i).getTotal_revenue()));
			addLabel(sheet, 3, j, String.format("%,.2f", expRevPerTruckReport.get(i).getTotal_expense()));
			addLabel(sheet, 4, j, String.format("%,.2f", expRevPerTruckReport.get(i).getTotal_revenue() - expRevPerTruckReport.get(i).getTotal_expense()));
			
			revenueGrandTotal += expRevPerTruckReport.get(i).getTotal_revenue();
			expenseGrandTotal += expRevPerTruckReport.get(i).getTotal_expense();
			netDiffTotal = netDiffTotal + (expRevPerTruckReport.get(i).getTotal_revenue() - expRevPerTruckReport.get(i).getTotal_expense());			
		}
		
		addLabel(sheet, 0, counter, "");
		addLabel(sheet, 1, counter, "Total");
		addLabel(sheet, 2, counter, String.format("%,.2f", revenueGrandTotal));
		addLabel(sheet, 3, counter, String.format("%,.2f", expenseGrandTotal));
		addLabel(sheet, 4, counter, String.format("%,.2f", netDiffTotal));
		
	}
	
	public void addHeader(WritableSheet sheet, int column, int row, String s, Alignment alignment) throws WriteException, RowsExceededException{
		
		WritableCellFormat cellFormat = new WritableCellFormat();
		cellFormat.setAlignment(alignment);
		
		Label label;
		label = new Label(column, row, s, cellFormat);
		sheet.mergeCells(0, 0, 4, 0);		
		sheet.addCell(label);
	}
	
	public void addTitle(WritableSheet sheet, int column, int row, String s, Alignment alignment) throws WriteException, RowsExceededException{
		
		WritableCellFormat cellFormat = new WritableCellFormat();
		cellFormat.setAlignment(alignment);
		
		Label label;
		label = new Label(column, row, s, cellFormat);
		sheet.mergeCells(0, 1, 4, 1);		
		sheet.addCell(label);
	}
	
	private void addCaption(WritableSheet sheet, int column, int row, String s) throws WriteException, RowsExceededException{
		
		Label label;
		label = new Label(column, row, s, timesBoldUnderLine);
		sheet.addCell(label);
	}
	
	private void addNumber(WritableSheet sheet, int column, int row, Integer integer) throws WriteException, RowsExceededException{
		
		Number number;
		number = new Number(column, row, integer, times);
		sheet.addCell(number);
	}
	
	private void addLabel(WritableSheet sheet, int column, int row, String s) throws WriteException, RowsExceededException{
		
		Label label;
		label = new Label(column, row, s, times);		
		sheet.addCell(label);
		
		int widthInChars = 0;
		
		if(column == 0){
			widthInChars = 4;
		} else {
			widthInChars = 18;
		}
		
		sheet.setColumnView(column, widthInChars);
	}
	
	public static byte[] excelRequiredInfo(String filePath, List<Expense> expRevPerTruckReport){
		
		Excel_RevenueExpensePerFon test = new Excel_RevenueExpensePerFon();
		test.setOutPutFile(filePath);
		test.setExpRevPerTruckReport(expRevPerTruckReport);
		
		try {
			return test.write();
		} catch (WriteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public List<Expense> getExpRevPerTruckReport() {
		return expRevPerTruckReport;
	}

	public void setExpRevPerTruckReport(List<Expense> expRevPerTruckReport) {
		this.expRevPerTruckReport = expRevPerTruckReport;
	}

}
