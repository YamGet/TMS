package org.fidel.tms.report;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.fidel.tms.model.FrightOrder;
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

public class ReportPDF_PaymentNotCollectedAfterPaymentRequest {
	
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
	
	private static List<FrightOrder> inprocessedFrightOrder;
		
	public static byte[] generatePDFDocument(String FileName, List<FrightOrder> inprocessedFrightOrder){
			
			setInprocessedFrightOrder(inprocessedFrightOrder);
			
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
			
			float[] columnWidths = {4, 10, 10, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7};
			
			PdfPTable pdfContent = new PdfPTable(columnWidths);
			
			PdfPCell cell = new PdfPCell(PDFHeaderContent());
			cell.setColspan(13);
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pdfContent.addCell(cell);
			
			insertCell(pdfContent, "No", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
			insertCell(pdfContent, "Client Organization", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
			insertCell(pdfContent, "Association Name", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
			insertCell(pdfContent, "Fright Order No.", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
			insertCell(pdfContent, "Truck Plate No.", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
			
			insertCell(pdfContent, "Price", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
			insertCell(pdfContent, "Delivered Qty", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
			insertCell(pdfContent, "Commission", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
			insertCell(pdfContent, "Receivable", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
			
			insertCell(pdfContent, "Date Form", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
			insertCell(pdfContent, "Date To", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
			insertCell(pdfContent, "Payment Request Date", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
			insertCell(pdfContent, "No. of Days", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String today = TodayDate_YYYYMMDD.getToday();
			double total_receivable = 0.0;
			
			for(int i = 0; i < inprocessedFrightOrder.size(); i++){
				
				Date date_from = inprocessedFrightOrder.get(i).getFrightOrderTripDetail().getPayment_request_date();
				
				long dateDiff = 0;
				
				try {
					Date td = sdf.parse(today);					
					dateDiff = (td.getTime() - date_from.getTime())/(24*60*60*1000);
				} catch (ParseException e) {					
					e.printStackTrace();
				}
				
				int rowNum = i + 1;
				insertCell(pdfContent, String.valueOf(rowNum), Element.ALIGN_CENTER, 1, 1, tblContentFont);
				insertCell(pdfContent, inprocessedFrightOrder.get(i).getFrightOrderTripDetail().getClient_organization().toUpperCase(), Element.ALIGN_LEFT, 1, 1, tblContentFont);
				insertCell(pdfContent, inprocessedFrightOrder.get(i).getAssociations().getAssociation_name(), Element.ALIGN_LEFT, 1, 1, tblContentFont);
				insertCell(pdfContent, inprocessedFrightOrder.get(i).getFon(), Element.ALIGN_CENTER, 1, 1, tblContentFont);
				insertCell(pdfContent, inprocessedFrightOrder.get(i).getTruckInformation().getTruck_plate_no(), Element.ALIGN_CENTER, 1, 1, tblContentFont);
				
				insertCell(pdfContent, String.valueOf(inprocessedFrightOrder.get(i).getFrightOrderTripDetail().getPrice()), Element.ALIGN_RIGHT, 1, 1, tblContentFont);
				insertCell(pdfContent, String.valueOf(inprocessedFrightOrder.get(i).getFrightOrderTripDetail().getDelivered_quantity()), Element.ALIGN_CENTER, 1, 1, tblContentFont);
				insertCell(pdfContent, String.valueOf(inprocessedFrightOrder.get(i).getCommission()), Element.ALIGN_RIGHT, 1, 1, tblContentFont);
				
				double price = inprocessedFrightOrder.get(i).getFrightOrderTripDetail().getPrice();
				double del_qty = inprocessedFrightOrder.get(i).getFrightOrderTripDetail().getDelivered_quantity();
				double comm = inprocessedFrightOrder.get(i).getCommission();				
				double rec = (price*del_qty) - (price*del_qty*comm);
				
				total_receivable += rec;
				
				insertCell(pdfContent, String.valueOf(rec), Element.ALIGN_RIGHT, 1, 1, tblContentFont);
				
				insertCell(pdfContent, inprocessedFrightOrder.get(i).getDate_from(), Element.ALIGN_CENTER, 1, 1, tblContentFont);
				insertCell(pdfContent, inprocessedFrightOrder.get(i).getDate_to(), Element.ALIGN_CENTER, 1, 1, tblContentFont);
				insertCell(pdfContent, inprocessedFrightOrder.get(i).getFrightOrderTripDetail().getPayment_request_date().toString(), Element.ALIGN_CENTER, 1, 1, tblContentFont);
				insertCell(pdfContent, String.valueOf(dateDiff), Element.ALIGN_CENTER, 1, 1, tblContentFont);
				
			}
			
			insertCell(pdfContent, "Total Receivable ", Element.ALIGN_RIGHT, 8, 1, tblContentFont);
			insertCell(pdfContent, String.format("%,.2f", total_receivable), Element.ALIGN_RIGHT, 1, 1, tblContentFont);
			insertCell(pdfContent, "", Element.ALIGN_CENTER, 4, 1, tblContentFont);
			
			return pdfContent;			
		}
		
		private static void insertCell(PdfPTable table, String text, int align, int colspan, int rowspan, Font font){
			
			String val = text + "";
			
			PdfPCell cell = new PdfPCell(new Phrase(text == null?"":text, font));
			
			cell.setHorizontalAlignment(align);
			
			//cell.setBorder(0);
			cell.setRowspan(rowspan);
			
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
			
			cell = new PdfPCell(new Phrase("Paymnet Requested but Not Collected", headerFont));
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

		public static List<FrightOrder> getInprocessedFrightOrder() {
			return inprocessedFrightOrder;
		}

		public static void setInprocessedFrightOrder(List<FrightOrder> inprocessedFrightOrder) {
			ReportPDF_PaymentNotCollectedAfterPaymentRequest.inprocessedFrightOrder = inprocessedFrightOrder;
		}
}
