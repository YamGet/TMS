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

public class AttachmentPDF_PaymentRequestForm {

	public static final String FONT = "C:/windows/fonts/ARIALUNI.TTF";
	public static final String CALIBRI_FONT = "C:/windows/fonts/calibri.ttf";
	
	private static Font customHeaderFont = FontFactory.getFont(CALIBRI_FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 15, Font.BOLD);
	BaseFont baseFont = customHeaderFont.getBaseFont();
	
	//private static Font headerFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
	private static Font headerFont = FontFactory.getFont(CALIBRI_FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 10, Font.BOLD);
	
	private static Font footerFont = FontFactory.getFont(CALIBRI_FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 7, Font.NORMAL);
		
	private static Font titleFont = FontFactory.getFont(CALIBRI_FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 9, Font.BOLD);
	
	//private static Font tblHeaderFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	private static Font tblHeaderFont = FontFactory.getFont(CALIBRI_FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 9, Font.BOLD);
	
	//private static Font tblContentFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
	private static Font tblContentFont = FontFactory.getFont(FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 8, Font.NORMAL); 
	
	private static List<FrightOrder> frightOrderInfo;
	
	private static String coupon_status;
		
	public static byte[] generatePDFDocument(String FileName, List<FrightOrder> frightOrderInfo, String coupon_status){
			
			setFrightOrderInfo(frightOrderInfo);
			
			setCoupon_status(coupon_status);
			
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
			
			float[] columnWidths = {15, 2, 15};
			
			PdfPTable reportContent = new PdfPTable(columnWidths);
			reportContent.setWidthPercentage(100);
									
			PdfPCell cell = new PdfPCell(PDFBodyContent());
			cell.setBorder(0);
			reportContent.addCell(cell);
			
			insertCell(reportContent, " ", Element.ALIGN_LEFT, 1, 1, tblContentFont);
			
			cell = new PdfPCell(PDFBodyContent());
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
			
			double delivered_qty = frightOrderInfo.get(0).getFrightOrderTripDetail().getDelivered_quantity();
			double price = frightOrderInfo.get(0).getFrightOrderTripDetail().getPrice();
			double total_price = delivered_qty * price;
			double net_diff = 0.0;
			
			if(getCoupon_status().equals("yes")){
				net_diff = total_price - frightOrderInfo.get(0).getTotal_coupon_amount() - (frightOrderInfo.get(0).getCommission()*total_price);
			} else {
				net_diff = total_price - (frightOrderInfo.get(0).getCommission()*total_price);
			}
			
			BigDecimal bd = new BigDecimal(net_diff);
			bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
			net_diff = bd.doubleValue();
			
			insertCell(pdfContent, "1. ", Element.ALIGN_LEFT, 1, 1, tblContentFont);
			insertCell(pdfContent, "Owner Name - ", Element.ALIGN_LEFT, 1, 1, tblContentFont);
			insertCell(pdfContent, frightOrderInfo.get(0).getTruckInformation().getTruck_owner(), Element.ALIGN_LEFT, 1, 1, tblContentFont);
			
			insertCell(pdfContent, "2. ", Element.ALIGN_LEFT, 1, 1, tblContentFont);
			insertCell(pdfContent, "Truck Plate No. - ", Element.ALIGN_LEFT, 1, 1, tblContentFont);
			insertCell(pdfContent, frightOrderInfo.get(0).getTruckInformation().getTruck_plate_no(), Element.ALIGN_LEFT, 1, 1, tblContentFont);
			
			insertCell(pdfContent, "3. ", Element.ALIGN_LEFT, 1, 1, tblContentFont);
			insertCell(pdfContent, "Trail Plate No. - ", Element.ALIGN_LEFT, 1, 1, tblContentFont);
			insertCell(pdfContent, frightOrderInfo.get(0).getTrailInformation().getTrail_plate_no(), Element.ALIGN_LEFT, 1, 1, tblContentFont);
			
			insertCell(pdfContent, "4. ", Element.ALIGN_LEFT, 1, 1, tblContentFont);
			insertCell(pdfContent, "Loading Date - ", Element.ALIGN_LEFT, 1, 1, tblContentFont);
			insertCell(pdfContent, frightOrderInfo.get(0).getDate_from(), Element.ALIGN_LEFT, 1, 1, tblContentFont);
			
			insertCell(pdfContent, "5. ", Element.ALIGN_LEFT, 1, 1, tblContentFont);
			insertCell(pdfContent, "Fright Order Number - ", Element.ALIGN_LEFT, 1, 1, tblContentFont);
			insertCell(pdfContent, frightOrderInfo.get(0).getFon(), Element.ALIGN_LEFT, 1, 1, tblContentFont);
			
			insertCell(pdfContent, "6. ", Element.ALIGN_LEFT, 1, 1, tblContentFont);
			insertCell(pdfContent, "Dispatcher Document Ref. Number - ", Element.ALIGN_LEFT, 1, 1, tblContentFont);
			insertCell(pdfContent, frightOrderInfo.get(0).getFrightOrderTripDetail().getDispatch_doc_ref_no(), Element.ALIGN_LEFT, 1, 1, tblContentFont);
			
			insertCell(pdfContent, "7. ", Element.ALIGN_LEFT, 1, 1, tblContentFont);
			insertCell(pdfContent, "Delivery Document Ref. Number - ", Element.ALIGN_LEFT, 1, 1, tblContentFont);
			insertCell(pdfContent, frightOrderInfo.get(0).getFrightOrderTripDetail().getDelivery_doc_ref_no(), Element.ALIGN_LEFT, 1, 1, tblContentFont);
			
			insertCell(pdfContent, "8. ", Element.ALIGN_LEFT, 1, 1, tblContentFont);
			insertCell(pdfContent, "Origin Place - ", Element.ALIGN_LEFT, 1, 1, tblContentFont);
			insertCell(pdfContent, frightOrderInfo.get(0).getFrightOrderTripDetail().getOrigin_place(), Element.ALIGN_LEFT, 1, 1, tblContentFont);
			
			insertCell(pdfContent, "9. ", Element.ALIGN_LEFT, 1, 1, tblContentFont);
			insertCell(pdfContent, "Destination Place - ", Element.ALIGN_LEFT, 1, 1, tblContentFont);
			insertCell(pdfContent, frightOrderInfo.get(0).getFrightOrderTripDetail().getDestination_place(), Element.ALIGN_LEFT, 1, 1, tblContentFont);
			
			insertCell(pdfContent, "10. ", Element.ALIGN_LEFT, 1, 1, tblContentFont);
			insertCell(pdfContent, "Client Organization - ", Element.ALIGN_LEFT, 1, 1, tblContentFont);
			insertCell(pdfContent, frightOrderInfo.get(0).getFrightOrderTripDetail().getClient_organization(), Element.ALIGN_LEFT, 1, 1, tblContentFont);
			
			insertCell(pdfContent, "11. ", Element.ALIGN_LEFT, 1, 1, tblContentFont);
			insertCell(pdfContent, "Delivered Quantity - ", Element.ALIGN_LEFT, 1, 1, tblContentFont);
			insertCell(pdfContent, String.valueOf(frightOrderInfo.get(0).getFrightOrderTripDetail().getDelivered_quantity()), Element.ALIGN_LEFT, 1, 1, tblContentFont);
			
			insertCell(pdfContent, "12. ", Element.ALIGN_LEFT, 1, 1, tblContentFont);
			insertCell(pdfContent, "Price - ", Element.ALIGN_LEFT, 1, 1, tblContentFont);
			insertCell(pdfContent, String.valueOf(frightOrderInfo.get(0).getFrightOrderTripDetail().getPrice()), Element.ALIGN_LEFT, 1, 1, tblContentFont);
			
			insertCell(pdfContent, "13. ", Element.ALIGN_LEFT, 1, 1, tblContentFont);
			insertCell(pdfContent, "Total Price - ", Element.ALIGN_LEFT, 1, 1, tblContentFont);
			insertCell(pdfContent, String.valueOf(total_price), Element.ALIGN_LEFT, 1, 1, tblContentFont);
			
			insertCell(pdfContent, "14. ", Element.ALIGN_LEFT, 1, 1, tblContentFont);
			
			if(getCoupon_status().equals("yes")){
				
				insertCell(pdfContent, "Coupon - ", Element.ALIGN_LEFT, 1, 1, tblContentFont);
				insertCell(pdfContent, "(" + String.valueOf(frightOrderInfo.get(0).getTotal_coupon_amount()) + ")", Element.ALIGN_LEFT, 1, 1, tblContentFont);
				
				insertCell(pdfContent, "15. ", Element.ALIGN_LEFT, 1, 1, tblContentFont);
			}
			insertCell(pdfContent, "Commission - ", Element.ALIGN_LEFT, 1, 1, tblContentFont);
			insertCell(pdfContent, "(" + String.valueOf(frightOrderInfo.get(0).getCommission()*total_price) + ")", Element.ALIGN_LEFT, 1, 1, tblContentFont);
						
			insertCell(pdfContent, "16. ", Element.ALIGN_LEFT, 1, 1, tblContentFont);
			insertCell(pdfContent, "Net Difference - ", Element.ALIGN_LEFT, 1, 1, tblContentFont);
			insertCell(pdfContent, String.valueOf(net_diff), Element.ALIGN_LEFT, 1, 1, tblContentFont);
			
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
			
			cell = new PdfPCell(new Phrase("Fright Order Payment Request Information", headerFont));
			cell.setColspan(1);
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pdfHeader.addCell(cell);
			
			cell = new PdfPCell(new Phrase("+251 930-36-8089, +251 93-040-5852, +251 11-156-0496", tblContentFont));
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
			
			cell = new PdfPCell(new Phrase(" ", headerFont));
			cell.setColspan(1);
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pdfHeader.addCell(cell);
			
			return pdfHeader;
		}
		
		public static PdfPTable PDFFooterContent(){
			
			PdfPTable pdfFooter = new PdfPTable(1);
			
			PdfPCell cell = new PdfPCell(new Phrase(" ", headerFont));
			cell.setColspan(1);
			cell.setBorder(0);
			cell.setBorderWidthBottom(0.1f);
			cell.setBorderColorBottom(BaseColor.BLACK);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pdfFooter.addCell(cell);
			
			cell = new PdfPCell(new Phrase(" Fidel Transport Management System, Powered By YamGet IT Solution PLC, +251 91 166 2766, +251 91 219 5853 ", footerFont));
			cell.setColspan(1);
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
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
			AttachmentPDF_PaymentRequestForm.frightOrderInfo = frightOrderInfo;
		}

		public static String getCoupon_status() {
			return coupon_status;
		}

		public static void setCoupon_status(String coupon_status) {
			AttachmentPDF_PaymentRequestForm.coupon_status = coupon_status;
		}
}
