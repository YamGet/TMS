package org.fidel.tms.excel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import org.fidel.tms.model.FrightOrder;

import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.UnderlineStyle;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class Excel_NotClosedFrightOrderList {
	
	private List<FrightOrder> frightOrderList;
	
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
		addTitle(sheet, 0, 1, "Not Closed Fright Order", Alignment.CENTRE);
		addTitle(sheet, 0, 2, " ", Alignment.CENTRE);
		
		addCaption(sheet, 0, 3, "No");
		addCaption(sheet, 1, 3, "Client Organization");
		addCaption(sheet, 2, 3, "Fright Order No.");
		addCaption(sheet, 3, 3, "Truck Plate No.");
		addCaption(sheet, 4, 3, "Trail Plate No.");
		addCaption(sheet, 5, 3, "Place of Origin");
		addCaption(sheet, 6, 3, "Destination Place");
		addCaption(sheet, 7, 3, "Quintal");
		addCaption(sheet, 8, 3, "Price");
		addCaption(sheet, 9, 3, "Total Receivable");
		addCaption(sheet, 10, 3, "Loading Date");
	}
	
	private void createContent(WritableSheet sheet) throws WriteException, RowsExceededException{
		
		for(int i = 0, j = 4; i < frightOrderList.size(); i++, j++){ //row
			
			addNumber(sheet, 0, j, i+1);
			addLabel(sheet, 1, j, frightOrderList.get(i).getFrightOrderTripDetail().getClient_organization());
			addLabel(sheet, 2, j, frightOrderList.get(i).getFon());
			addLabel(sheet, 3, j, frightOrderList.get(i).getTruckInformation().getTruck_plate_no());
			addLabel(sheet, 4, j, frightOrderList.get(i).getTrailInformation().getTrail_plate_no());
			addLabel(sheet, 5, j, frightOrderList.get(i).getFrightOrderTripDetail().getOrigin_place());
			addLabel(sheet, 6, j, frightOrderList.get(i).getFrightOrderTripDetail().getDestination_place());
			addLabel(sheet, 7, j, frightOrderList.get(i).getFrightOrderTripDetail().getLoading_quantity());
			addLabel(sheet, 8, j, String.valueOf(frightOrderList.get(i).getFrightOrderTripDetail().getPrice()));
			addLabel(sheet, 9, j, String.valueOf(Double.parseDouble(frightOrderList.get(i).getFrightOrderTripDetail().getLoading_quantity()) * frightOrderList.get(i).getFrightOrderTripDetail().getPrice()));
			addLabel(sheet, 10, j, frightOrderList.get(i).getDate_from());
		}
		
//		StringBuffer buf = new StringBuffer();
//		buf.append("SUM(B2:B10)");		
//		Formula f = new Formula(0, 10, buf.toString());
//		sheet.addCell(f);
//		
//		buf = new StringBuffer();
//		buf.append("SUM(B2:B10)");
//		f = new Formula(1, 10, buf.toString());
//		sheet.addCell(f);
		
//		for(int i = 12; i < 20; i++){
//			addLabel(sheet, 0, i, "text "+i );
//			addLabel(sheet, 1, i, "text");
//		}
	}
	
	public void addHeader(WritableSheet sheet, int column, int row, String s, Alignment alignment) throws WriteException, RowsExceededException{
		
		WritableCellFormat cellFormat = new WritableCellFormat();
		cellFormat.setAlignment(alignment);
		
		Label label;
		label = new Label(column, row, s, cellFormat);
		sheet.mergeCells(0, 0, 10, 0);		
		sheet.addCell(label);
	}
	
	public void addTitle(WritableSheet sheet, int column, int row, String s, Alignment alignment) throws WriteException, RowsExceededException{
		
		WritableCellFormat cellFormat = new WritableCellFormat();
		cellFormat.setAlignment(alignment);
		
		Label label;
		label = new Label(column, row, s, cellFormat);
		sheet.mergeCells(0, 1, 10, 1);		
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
	
	public static byte[] excelRequiredInfo(String filePath, List<FrightOrder> frightOrderList){
		
		Excel_NotClosedFrightOrderList test = new Excel_NotClosedFrightOrderList();
		test.setOutPutFile(filePath);
		test.setFrightOrderList(frightOrderList);
		try {
			return test.write();
		} catch (WriteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<FrightOrder> getFrightOrderList() {
		return frightOrderList;
	}

	public void setFrightOrderList(List<FrightOrder> frightOrderList) {
		this.frightOrderList = frightOrderList;
	}
	
//	public static void main(String[] arg) throws WriteException, IOException{
//		Excel_report test = new Excel_report();
//		test.setOutPutFile("c:/tms_system_file/test.xls");
//		test.write();
//	}

}
