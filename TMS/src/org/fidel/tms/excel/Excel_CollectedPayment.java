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

public class Excel_CollectedPayment {

	private List<FrightOrder> collectedPyamentList;
	
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
		addTitle(sheet, 0, 1, "Collected Payment", Alignment.CENTRE);
		addTitle(sheet, 0, 2, " ", Alignment.CENTRE);
		
		addCaption(sheet, 0, 3, "No");
		addCaption(sheet, 1, 3, "Client Organization");
		addCaption(sheet, 2, 3, "Fright Order No.");
		addCaption(sheet, 3, 3, "Truck Plate No.");
		
		addCaption(sheet, 4, 3, "Price");
		addCaption(sheet, 5, 3, "Delivered Quantity");
		
		addCaption(sheet, 6, 3, "Commission");
		addCaption(sheet, 7, 3, "Paymnet Amount");
		addCaption(sheet, 8, 3, "Payment Type");
		addCaption(sheet, 9, 3, "Payment Doc. Ref. No");
		addCaption(sheet, 10, 3, "CRSI Number");
		addCaption(sheet, 11, 3, "Payment Date");
		
		addCaption(sheet, 12, 3, "Place of Origin");
		addCaption(sheet, 13, 3, "Destination Place");
	}
	
	private void createContent(WritableSheet sheet) throws WriteException, RowsExceededException{
		
		for(int i = 0, j = 4; i < collectedPyamentList.size(); i++, j++){ //row
						
			addNumber(sheet, 0, j, i+1);
			addLabel(sheet, 1, j, collectedPyamentList.get(i).getFrightOrderTripDetail().getClient_organization());
			addLabel(sheet, 2, j, collectedPyamentList.get(i).getFon());
			addLabel(sheet, 3, j, collectedPyamentList.get(i).getTruckInformation().getTruck_plate_no());
			
			addLabel(sheet, 4, j, String.valueOf(collectedPyamentList.get(i).getFrightOrderTripDetail().getPrice()));
			addLabel(sheet, 5, j, String.valueOf(collectedPyamentList.get(i).getFrightOrderTripDetail().getDelivered_quantity()));
			
			addLabel(sheet, 6, j, String.valueOf(collectedPyamentList.get(i).getCommission()));
			addLabel(sheet, 7, j, String.format("%,.2f", collectedPyamentList.get(i).getPayment().getPayment_amount()));
			addLabel(sheet, 8, j, collectedPyamentList.get(i).getPayment().getPayment_type());
			addLabel(sheet, 9, j, collectedPyamentList.get(i).getPayment().getPayment_doc_ref_no());
			addLabel(sheet, 10, j, collectedPyamentList.get(i).getFrightOrderTripDetail().getCrv_number());
			addLabel(sheet, 11, j, collectedPyamentList.get(i).getPayment().getPayment_date());
			
			addLabel(sheet, 12, j, collectedPyamentList.get(i).getFrightOrderTripDetail().getOrigin_place());
			addLabel(sheet, 13, j, collectedPyamentList.get(i).getFrightOrderTripDetail().getDestination_place());
							
		}
	}
	
	public void addHeader(WritableSheet sheet, int column, int row, String s, Alignment alignment) throws WriteException, RowsExceededException{
		
		WritableCellFormat cellFormat = new WritableCellFormat();
		cellFormat.setAlignment(alignment);
		
		Label label;
		label = new Label(column, row, s, cellFormat);
		sheet.mergeCells(0, 0, 13, 0);		
		sheet.addCell(label);
		
	}
	
	public void addTitle(WritableSheet sheet, int column, int row, String s, Alignment alignment) throws WriteException, RowsExceededException{
		
		WritableCellFormat cellFormat = new WritableCellFormat();
		cellFormat.setAlignment(alignment);
		
		Label label;
		label = new Label(column, row, s, cellFormat);
		sheet.mergeCells(0, 1, 13, 1);		
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
	
	public static byte[] excelRequiredInfo(String filePath, List<FrightOrder> collectedPyamentList){
		
		Excel_CollectedPayment test = new Excel_CollectedPayment();
		test.setOutPutFile(filePath);
		test.setCollectedPyamentList(collectedPyamentList);
		try {
			return test.write();
		} catch (WriteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public List<FrightOrder> getCollectedPyamentList() {
		return collectedPyamentList;
	}

	public void setCollectedPyamentList(List<FrightOrder> collectedPyamentList) {
		this.collectedPyamentList = collectedPyamentList;
	}

}
