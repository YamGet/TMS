package org.fidel.tms.report;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.fidel.tms.model.FrightOrder;
import org.fidel.tms.utils.DateConvertor;
import org.fidel.tms.utils.SysConstant;
import org.fidel.tms.utils.TodayDate_YYYYMMDD;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ReportPDF_TruckDailyActivities {
	
	public static final String FONT = "C:/windows/fonts/ARIALUNI.TTF";
	public static final String CALIBRI_FONT = "C:/windows/fonts/calibri.ttf";
	
	private static Font customHeaderFont = FontFactory.getFont(CALIBRI_FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 15, Font.BOLD);
	BaseFont baseFont = customHeaderFont.getBaseFont();
	
	//private static Font headerFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
	private static Font headerFont = FontFactory.getFont(CALIBRI_FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 10, Font.BOLD);
		
	private static Font titleFont = FontFactory.getFont(CALIBRI_FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 9, Font.BOLD);
	
	//private static Font tblHeaderFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	private static Font tblHeaderFont = FontFactory.getFont(CALIBRI_FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 9, Font.BOLD);
	
	//private static Font tblContentFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
	private static Font tblContentFont = FontFactory.getFont(FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 8, Font.NORMAL); 
	
	private static List<FrightOrder> dailyActivityList;
	private static String truck_type;
		
	public static byte[] generatePDFDocument(String FileName, List<FrightOrder> dailyActivityList, String truck_type){
			
			setDailyActivityList(dailyActivityList);
			setTruck_type(truck_type);
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			try{
				
				Document document = new Document();
				
				PdfWriter.getInstance(document, baos);
				
				document.setPageSize(PageSize.A4.rotate());
				
				document.open();
				
				addPDFMetaData(document, FileName);
				
				addPDFContent(document);				
				
				document.close();

			} catch(Exception e){
				
				e.printStackTrace();
			}
			
			return baos.toByteArray();
		}
		
		public static void addPDFMetaData(Document document, String FileName){
			document.addTitle("NotClosedFrightOrderList");
			document.addSubject("TilahunMesafent");
			document.addKeywords("java, PDF, iText" + FileName);
			document.addAuthor("Yofetahe");
			document.addCreator("Fidel");		
		}
		
		public static void addPDFContent(Document document){
			
			PdfPTable reportContent = new PdfPTable(1);
			reportContent.setWidthPercentage(100);
			
			PdfPCell cell = new PdfPCell(PDFBodyContent());
			cell.setBorder(0);
			reportContent.addCell(cell);
			
			try {
				document.add(reportContent);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			
		}
		
		public static PdfPTable PDFBodyContent(){
			
			float[] columnWidths = {2, 5, 7, 13, 7, 3, 9, 5, 7, 7, 11, 7, 5, 7, 7};
			float[] columnWidths_fluid = {2, 5, 7, 13, 7, 3, 9, 5, 7, 7, 11, 7, 7};
			
			PdfPTable pdfContent = new PdfPTable(getTruck_type().equals("FLUID")?columnWidths_fluid:columnWidths);
			
			int size = getTruck_type().equals("FLUID")?columnWidths_fluid.length:columnWidths.length;
			
			PdfPCell cell = new PdfPCell(PDFHeaderContent());
			cell.setColspan(size);
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pdfContent.addCell(cell);
			
			insertCell(pdfContent, "No", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
			insertCell(pdfContent, "Truck Side No.", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
			insertCell(pdfContent, "Truck Plate No.", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
			insertCell(pdfContent, "Driver Name", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
			insertCell(pdfContent, "Loading Date", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
			insertCell(pdfContent, "No. of Days", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
			insertCell(pdfContent, "Loading Type", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
			insertCell(pdfContent, "FON", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
			insertCell(pdfContent, "Origin Place", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
			insertCell(pdfContent, "Destination", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
			
			insertCell(pdfContent, "Client Organization", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
			
			if(!getTruck_type().equals("FLUID")){
				insertCell(pdfContent, "Quintal Qty", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
				insertCell(pdfContent, "Price", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
				insertCell(pdfContent, "Advance Payment", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
			} else {
				insertCell(pdfContent, "Liter Qty", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
			}
			
			insertCell(pdfContent, "Current Status", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String today = TodayDate_YYYYMMDD.getToday();
			
			for(int i = 0; i < dailyActivityList.size(); i++){
				
				String date_from = dailyActivityList.get(i).getDate_from();
				long dateDiff = 0;
				
				try {
					Date td = sdf.parse(today);
					Date df = sdf.parse(date_from);
					dateDiff = (td.getTime() - df.getTime())/(24*60*60*1000);
				} catch (ParseException e) {					
					e.printStackTrace();
				}				
				
				int rowNum = i + 1;
				insertCell(pdfContent, String.valueOf(rowNum), Element.ALIGN_CENTER, 1, 1, tblContentFont);
				insertCell(pdfContent, dailyActivityList.get(i).getTruckInformation().getSide_no(), Element.ALIGN_LEFT, 1, 1, tblContentFont);
				insertCell(pdfContent, dailyActivityList.get(i).getTruckInformation().getTruck_plate_no(), Element.ALIGN_LEFT, 1, 1, tblContentFont);
				insertCell(pdfContent, dailyActivityList.get(i).getDrivers().getFullName(), Element.ALIGN_LEFT, 1, 1, tblContentFont);
				insertCell(pdfContent, dailyActivityList.get(i).getDate_from(), Element.ALIGN_CENTER, 1, 1, tblContentFont);
				insertCell(pdfContent, String.valueOf(dateDiff), Element.ALIGN_CENTER, 1, 1, tblContentFont);
				insertCell(pdfContent, dailyActivityList.get(i).getFrightOrderTripDetail().getLoading_type(), Element.ALIGN_LEFT, 1, 1, tblContentFont);
				insertCell(pdfContent, dailyActivityList.get(i).getFon(), Element.ALIGN_LEFT, 1, 1, tblContentFont);
				insertCell(pdfContent, dailyActivityList.get(i).getFrightOrderTripDetail().getOrigin_place(), Element.ALIGN_LEFT, 1, 1, tblContentFont);
				insertCell(pdfContent, dailyActivityList.get(i).getFrightOrderTripDetail().getDestination_place(), Element.ALIGN_LEFT, 1, 1, tblContentFont);
				insertCell(pdfContent, dailyActivityList.get(i).getFrightOrderTripDetail().getClient_organization(), Element.ALIGN_LEFT, 1, 1, tblContentFont);
				insertCell(pdfContent, dailyActivityList.get(i).getFrightOrderTripDetail().getLoading_quantity(), Element.ALIGN_CENTER, 1, 1, tblContentFont);
				
				if(!getTruck_type().equals("FLUID")){
					insertCell(pdfContent, String.format("%,.2f", dailyActivityList.get(i).getFrightOrderTripDetail().getPrice()), Element.ALIGN_RIGHT, 1, 1, tblContentFont);
					insertCell(pdfContent, String.format("%,.2f", dailyActivityList.get(i).getAdvancePayment().getAdvance_payment_amount()), Element.ALIGN_RIGHT, 1, 1, tblContentFont);
				}
				
				String status = dailyActivityList.get(i).getFrightOrderTripDetail().getFotd_status();
				
				if(status.equalsIgnoreCase("Active")){
					insertCell(pdfContent, "On the way", Element.ALIGN_LEFT, 1, 1, tblContentFont);
				} else {
					insertCell(pdfContent, dailyActivityList.get(i).getFrightOrderTripDetail().getDestination_place(), Element.ALIGN_LEFT, 1, 1, tblContentFont);
				}				
			}
			
			return pdfContent;			
		}
		
		private static void insertCell(PdfPTable table, String text, int align, int colspan, int rowspan, Font font){
			
			String val = text + "";
			
			PdfPCell cell = new PdfPCell(new Phrase(text == null?"":text, font));
			
			cell.setHorizontalAlignment(align);
			
			//cell.setBorder(0);
			cell.setRowspan(rowspan);
			//cell.setNoWrap(true);
			
			cell.setColspan(colspan);
			
			if(val.equalsIgnoreCase("")){
				cell.setMinimumHeight(15f);
			}
			
			cell.setMinimumHeight(15f);
			
			table.addCell(cell);
		}
		
		public static PdfPTable PDFHeaderContent(){
			
			PdfPTable pdfHeader = new PdfPTable(1);
			
			PdfPCell cell = new PdfPCell(new Phrase(SysConstant.ORGANIZATION_FULL_NAME, customHeaderFont));
			cell.setColspan(1);
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pdfHeader.addCell(cell);
			
			cell = new PdfPCell(new Phrase((getTruck_type().equals("FLUID")?"Fuel ":"Freight ")+"Truck Daily Acivity, " + TodayDate_YYYYMMDD.getTodayDayMonthYearWithMonthNameFormat() + " (" + DateConvertor.gregToEthioDateConvertor(TodayDate_YYYYMMDD.getDayMonthYearFormat()) + ")", headerFont));
			cell.setColspan(1);
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pdfHeader.addCell(cell);
			
			cell = new PdfPCell(new Phrase(" ", headerFont));
			cell.setColspan(1);
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pdfHeader.addCell(cell);

			
			return pdfHeader;
		}
		
		public static PdfPTable PDFFooterContent(){
			
			PdfPTable pdfFooter = new PdfPTable(1);
			
			return pdfFooter;
		}
		
//		private static void addEmptyLine(Paragraph paragraph, int number){
//			for(int i = 0; i < number; i++){
//				paragraph.add(new Paragraph(" "));
//			}
//		}

		public static List<FrightOrder> getDailyActivityList() {
			return dailyActivityList;
		}

		public static void setDailyActivityList(List<FrightOrder> dailyActivityList) {
			ReportPDF_TruckDailyActivities.dailyActivityList = dailyActivityList;
		}

		public static String getTruck_type() {
			return truck_type;
		}

		public static void setTruck_type(String truck_type) {
			ReportPDF_TruckDailyActivities.truck_type = truck_type;
		}
}
