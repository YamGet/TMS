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

public class Excel_TransactionHistoryPerTruck {

	private List<FrightOrder> truckTransactionList;
	
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
		addTitle(sheet, 0, 1, truckTransactionList.get(0).getTruckInformation().getTruck_plate_no() + " Truck Transaction History", Alignment.CENTRE);
		addTitle(sheet, 0, 2, " ", Alignment.CENTRE);
		
		addCaption(sheet, 0, 3, "No");
		addCaption(sheet, 1, 3, "Trail Plate No.");
		addCaption(sheet, 2, 3, "Loading Date");
		addCaption(sheet, 3, 3, "Loading Type");
		addCaption(sheet, 4, 3, "FON");
		addCaption(sheet, 5, 3, "Origin Place");
		addCaption(sheet, 6, 3, "Destination");
		addCaption(sheet, 7, 3, "Quintal");
		addCaption(sheet, 8, 3, "Price");
		addCaption(sheet, 9, 3, "Client Org.");
		addCaption(sheet, 10, 3, "Advacne Payment");
		addCaption(sheet, 11, 3, "Coupon Amount");
		addCaption(sheet, 12, 3, "Current Status");
	}
	
	private void createContent(WritableSheet sheet) throws WriteException, RowsExceededException{
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = TodayDate_YYYYMMDD.getToday();
		
		for(int i = 0, j = 4; i < truckTransactionList.size(); i++, j++){ //row
			
			addNumber(sheet, 0, j, i+1);
			addLabel(sheet, 1, j, truckTransactionList.get(i).getTrailInformation().getTrail_plate_no());
			addLabel(sheet, 2, j, truckTransactionList.get(i).getDate_from());
			addLabel(sheet, 3, j, truckTransactionList.get(i).getFrightOrderTripDetail().getLoading_type());
			addLabel(sheet, 4, j, truckTransactionList.get(i).getFon());
			addLabel(sheet, 5, j, truckTransactionList.get(i).getFrightOrderTripDetail().getOrigin_place());
			addLabel(sheet, 6, j, truckTransactionList.get(i).getFrightOrderTripDetail().getDestination_place());
			addLabel(sheet, 7, j, String.valueOf(truckTransactionList.get(i).getFrightOrderTripDetail().getDelivered_quantity()));
			addLabel(sheet, 8, j,  String.valueOf(truckTransactionList.get(i).getFrightOrderTripDetail().getPrice()));
			addLabel(sheet, 9, j, truckTransactionList.get(i).getFrightOrderTripDetail().getClient_organization());
			addLabel(sheet, 10, j,  String.valueOf(truckTransactionList.get(i).getAdvancePayment().getAdvance_payment_amount()));
			addLabel(sheet, 11, j,  String.valueOf(truckTransactionList.get(i).getTotal_coupon_amount()));
			
			String fotd_status = truckTransactionList.get(i).getFrightOrderTripDetail().getFotd_status();
			
			if(fotd_status.equalsIgnoreCase("Active")){
				addLabel(sheet, 12, j, "On the way");
			} else {
				addLabel(sheet, 12, j, truckTransactionList.get(i).getFrightOrderTripDetail().getDestination_place());
			}	
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
	
	public static byte[] excelRequiredInfo(String filePath, List<FrightOrder> truckTransactionList){
		
		Excel_TransactionHistoryPerTruck test = new Excel_TransactionHistoryPerTruck();
		test.setOutPutFile(filePath);
		test.setTruckTransactionList(truckTransactionList);
		try {
			return test.write();
		} catch (WriteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<FrightOrder> getTruckTransactionList() {
		return truckTransactionList;
	}

	public void setTruckTransactionList(List<FrightOrder> truckTransactionList) {
		this.truckTransactionList = truckTransactionList;
	}
}
