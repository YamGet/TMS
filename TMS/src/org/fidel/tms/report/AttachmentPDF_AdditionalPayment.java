package org.fidel.tms.report;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.List;

import org.fidel.tms.model.FrightOrder;
import org.fidel.tms.utils.SysConstant;

import com.itextpdf.text.BaseColor;
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
//import com.sun.prism.paint.Color;

public class AttachmentPDF_AdditionalPayment {

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
	
	private static Font footerFont = FontFactory.getFont(CALIBRI_FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 10, Font.NORMAL);
	
	private static List<FrightOrder> frightOrderInfo;
	
	private static double return_amount;
		
	public static byte[] generatePDFDocument(String FileName, List<FrightOrder> frightOrderInfo, double return_amount){
			
			setFrightOrderInfo(frightOrderInfo);
			
			setReturn_amount(return_amount);
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			try{
				
				Document document = new Document();
				
				PdfWriter.getInstance(document, baos);
				
				document.setPageSize(PageSize.A5);
				
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
			
			float[] columnWidths = {2, 15, 15};
			
			PdfPTable pdfContent = new PdfPTable(columnWidths);
			
			PdfPCell cell = new PdfPCell(PDFHeaderContent());
			cell.setColspan(3);
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pdfContent.addCell(cell);
			
			insertCell(pdfContent, " ", Element.ALIGN_LEFT, 1, 1, tblContentFont);
			insertCell(pdfContent, " ", Element.ALIGN_LEFT, 1, 1, tblContentFont);
			insertCell(pdfContent, " ", Element.ALIGN_LEFT, 1, 1, tblContentFont);
						
			insertCell(pdfContent, "1. ", Element.ALIGN_LEFT, 1, 1, tblContentFont);
			insertCell(pdfContent, "Drivers Name - ", Element.ALIGN_LEFT, 1, 1, tblContentFont);
			insertCell(pdfContent, frightOrderInfo.get(0).getDrivers().getFname() + " " + frightOrderInfo.get(0).getDrivers().getMname(), Element.ALIGN_LEFT, 1, 1, tblContentFont);
			
			insertCell(pdfContent, "2. ", Element.ALIGN_LEFT, 1, 1, tblContentFont);
			insertCell(pdfContent, "Truck Plate No. - ", Element.ALIGN_LEFT, 1, 1, tblContentFont);
			insertCell(pdfContent, frightOrderInfo.get(0).getTruckInformation().getTruck_plate_no(), Element.ALIGN_LEFT, 1, 1, tblContentFont);
			
			insertCell(pdfContent, "3. ", Element.ALIGN_LEFT, 1, 1, tblContentFont);
			insertCell(pdfContent, "Trail Plate No. - ", Element.ALIGN_LEFT, 1, 1, tblContentFont);
			insertCell(pdfContent, frightOrderInfo.get(0).getTrailInformation().getTrail_plate_no(), Element.ALIGN_LEFT, 1, 1, tblContentFont);
			
			insertCell(pdfContent, "4. ", Element.ALIGN_LEFT, 1, 1, tblContentFont);
			insertCell(pdfContent, "Freight Order Number - ", Element.ALIGN_LEFT, 1, 1, tblContentFont);
			insertCell(pdfContent, frightOrderInfo.get(0).getFon(), Element.ALIGN_LEFT, 1, 1, tblContentFont);
			
			insertCell(pdfContent, "5. ", Element.ALIGN_LEFT, 1, 1, tblContentFont);
			insertCell(pdfContent, "Loading Date - ", Element.ALIGN_LEFT, 1, 1, tblContentFont);
			insertCell(pdfContent, frightOrderInfo.get(0).getDate_from(), Element.ALIGN_LEFT, 1, 1, tblContentFont);
			
			insertCell(pdfContent, "6. ", Element.ALIGN_LEFT, 1, 1, tblContentFont);
			insertCell(pdfContent, "Additional Payment Amount - ", Element.ALIGN_LEFT, 1, 1, tblContentFont);
			insertCell(pdfContent, String.format("%,.2f", getReturn_amount()), Element.ALIGN_LEFT, 1, 1, tblContentFont);
			
			cell = new PdfPCell(PDFFooterContent());
			cell.setColspan(3);
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pdfContent.addCell(cell);
			
			return pdfContent;			
		}
		
		private static void insertCell(PdfPTable table, String text, int align, int colspan, int rowspan, Font font){
			
			String val = text + "";
			
			PdfPCell cell = new PdfPCell(new Phrase(text == null?"":text, font));
			
			cell.setHorizontalAlignment(align);
			
			cell.setBorder(0);
			cell.setRowspan(rowspan);
						
			cell.setColspan(colspan);
			
			if(val.equalsIgnoreCase("")){
				cell.setMinimumHeight(20f);
			}
			
			cell.setMinimumHeight(20f);
			
			table.addCell(cell);
		}
		
		public static PdfPTable PDFHeaderContent(){
			
			PdfPTable pdfHeader = new PdfPTable(1);
			
			PdfPCell cell = new PdfPCell(new Phrase(SysConstant.ORGANIZATION_FULL_NAME, customHeaderFont));
			cell.setColspan(1);
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pdfHeader.addCell(cell);
			
			cell = new PdfPCell(new Phrase("Advance Payment Additional Payment Form", headerFont));
			cell.setColspan(1);
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pdfHeader.addCell(cell);
			
			cell = new PdfPCell(new Phrase(" ", headerFont));
			cell.setColspan(1);
			cell.setBorder(0);
			cell.setBorderWidthBottom(0.1f);
			cell.setBorderColorBottom(BaseColor.BLACK);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pdfHeader.addCell(cell);
			
			return pdfHeader;
		}
		
		public static PdfPTable PDFFooterContent(){
			
			PdfPTable pdfFooter = new PdfPTable(2);
			
			PdfPCell cell = new PdfPCell(new Phrase(" ", headerFont));
			cell.setColspan(2);
			cell.setBorder(0);
			cell.setBorderWidthBottom(0.1f);
			cell.setBorderColorBottom(BaseColor.BLACK);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pdfFooter.addCell(cell);
			
			cell = new PdfPCell(new Phrase(" ", tblContentFont));
			cell.setColspan(2);
			cell.setBorder(0);			
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			pdfFooter.addCell(cell);
			
			cell = new PdfPCell(new Phrase(" ", tblContentFont));
			cell.setColspan(2);
			cell.setBorder(0);			
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			pdfFooter.addCell(cell);
			
			cell = new PdfPCell(new Phrase("DRIVER SIGN. ___________________ ", footerFont));
			cell.setColspan(1);
			cell.setBorder(0);			
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			pdfFooter.addCell(cell);
			
			cell = new PdfPCell(new Phrase("  PAID BY. ___________________ ", footerFont));
			cell.setColspan(1);
			cell.setBorder(0);			
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			pdfFooter.addCell(cell);
			
			
			return pdfFooter;
		}
		
//		private static void addEmptyLine(Paragraph paragraph, int number){
//			for(int i = 0; i < number; i++){
//				paragraph.add(new Paragraph(" "));
//			}
//		}

		public static List<FrightOrder> getFrightOrderInfo() {
			return frightOrderInfo;
		}

		public static void setFrightOrderInfo(List<FrightOrder> frightOrderInfo) {
			AttachmentPDF_AdditionalPayment.frightOrderInfo = frightOrderInfo;
		}

		public static double getReturn_amount() {
			return return_amount;
		}

		public static void setReturn_amount(double return_amount) {
			AttachmentPDF_AdditionalPayment.return_amount = return_amount;
		}
}
