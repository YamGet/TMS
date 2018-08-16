package org.fidel.tms.excel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

public class Excel_PaymentNotCollectedAfterPaymentRequest {

	private List<FrightOrder> inprocessedFrightOrder;
	
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
		addTitle(sheet, 0, 1, "Payment Requested but Not Collected", Alignment.CENTRE);
		addTitle(sheet, 0, 2, " ", Alignment.CENTRE);
		
		addCaption(sheet, 0, 3, "No");
		addCaption(sheet, 1, 3, "Client Organization");
		addCaption(sheet, 2, 3, "Association Name");
		addCaption(sheet, 3, 3, "Fright Order No.");
		addCaption(sheet, 4, 3, "Truck Plate No.");
		
		addCaption(sheet, 5, 3, "Price");
		addCaption(sheet, 6, 3, "Delivered Qty");
		addCaption(sheet, 7, 3, "Commission");
		addCaption(sheet, 8, 3, "Receivable");
		
		addCaption(sheet, 9, 3, "Date From");
		addCaption(sheet, 10, 3, "Date To");
		addCaption(sheet, 11, 3, "Fright Order Close Date");
		addCaption(sheet, 12, 3, "No. of Days");
	}
	
	private void createContent(WritableSheet sheet) throws WriteException, RowsExceededException{
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = TodayDate_YYYYMMDD.getToday();
		
		for(int i = 0, j = 4; i < inprocessedFrightOrder.size(); i++, j++){ //row
			
			String date_from = inprocessedFrightOrder.get(i).getFrightOrderTripDetail().getPayment_request_date().toString();
			long dateDiff = 0;
			
			try {
				Date td = sdf.parse(today);
				Date df = sdf.parse(date_from);
				dateDiff = (td.getTime() - df.getTime())/(24*60*60*1000);
			} catch (ParseException e) {					
				e.printStackTrace();
			}
			
			addNumber(sheet, 0, j, i+1);
			addLabel(sheet, 1, j, inprocessedFrightOrder.get(i).getFrightOrderTripDetail().getClient_organization());
			addLabel(sheet, 2, j, inprocessedFrightOrder.get(i).getAssociations().getAssociation_name().toUpperCase());
			addLabel(sheet, 3, j, inprocessedFrightOrder.get(i).getFon());
			addLabel(sheet, 4, j, inprocessedFrightOrder.get(i).getTruckInformation().getTruck_plate_no());
			
			addLabel(sheet, 5, j, String.valueOf(inprocessedFrightOrder.get(i).getFrightOrderTripDetail().getPrice()));
			addLabel(sheet, 6, j, String.valueOf(inprocessedFrightOrder.get(i).getFrightOrderTripDetail().getDelivered_quantity()));
			addLabel(sheet, 7, j, String.valueOf(inprocessedFrightOrder.get(i).getCommission()));
			addLabel(sheet, 8, j, String.valueOf( (inprocessedFrightOrder.get(i).getFrightOrderTripDetail().getPrice() * inprocessedFrightOrder.get(i).getFrightOrderTripDetail().getDelivered_quantity()) - (inprocessedFrightOrder.get(i).getFrightOrderTripDetail().getPrice() * inprocessedFrightOrder.get(i).getFrightOrderTripDetail().getDelivered_quantity() * inprocessedFrightOrder.get(i).getCommission()) ));
			
			addLabel(sheet, 9, j, inprocessedFrightOrder.get(i).getDate_from());
			addLabel(sheet, 10, j, inprocessedFrightOrder.get(i).getDate_to());
			addLabel(sheet, 11, j, inprocessedFrightOrder.get(i).getFrightOrderTripDetail().getClose_date().toString());
			addLabel(sheet, 12, j, String.valueOf(dateDiff));
							
		}
	}
	
	public void addHeader(WritableSheet sheet, int column, int row, String s, Alignment alignment) throws WriteException, RowsExceededException{
		
		WritableCellFormat cellFormat = new WritableCellFormat();
		cellFormat.setAlignment(alignment);
		
		Label label;
		label = new Label(column, row, s, cellFormat);
		sheet.mergeCells(0, 0, 12, 0);		
		sheet.addCell(label);
	}
	
	public void addTitle(WritableSheet sheet, int column, int row, String s, Alignment alignment) throws WriteException, RowsExceededException{
		
		WritableCellFormat cellFormat = new WritableCellFormat();
		cellFormat.setAlignment(alignment);
		
		Label label;
		label = new Label(column, row, s, cellFormat);
		sheet.mergeCells(0, 1, 12, 1);		
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
	
	public static byte[] excelRequiredInfo(String filePath, List<FrightOrder> inprocessedFrightOrder){
		
		Excel_PaymentNotCollectedAfterPaymentRequest test = new Excel_PaymentNotCollectedAfterPaymentRequest();
		test.setOutPutFile(filePath);
		test.setInprocessedFrightOrder(inprocessedFrightOrder);
		try {
			return test.write();
		} catch (WriteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<FrightOrder> getInprocessedFrightOrder() {
		return inprocessedFrightOrder;
	}

	public void setInprocessedFrightOrder(List<FrightOrder> inprocessedFrightOrder) {
		this.inprocessedFrightOrder = inprocessedFrightOrder;
	}

}
