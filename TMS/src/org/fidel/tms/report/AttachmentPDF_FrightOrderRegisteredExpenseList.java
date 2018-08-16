package org.fidel.tms.report;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import org.fidel.tms.model.Expense;
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

public class AttachmentPDF_FrightOrderRegisteredExpenseList {

	public static final String FONT = "C:/windows/fonts/ARIALUNI.TTF";
	public static final String CALIBRI_FONT = "C:/windows/fonts/calibri.ttf";
	
	private static Font customHeaderFont = FontFactory.getFont(CALIBRI_FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 20, Font.BOLD);
	BaseFont baseFont = customHeaderFont.getBaseFont();
	
	//private static Font headerFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
	private static Font headerFont = FontFactory.getFont(CALIBRI_FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 13, Font.BOLD);
		
	private static Font titleFont = FontFactory.getFont(CALIBRI_FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 9, Font.BOLD);
	
	//private static Font tblHeaderFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	private static Font tblHeaderFont = FontFactory.getFont(CALIBRI_FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 9, Font.BOLD);
	
	//private static Font tblContentFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
	private static Font tblContentFont = FontFactory.getFont(FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 8, Font.NORMAL); 
	
	private static Font footerFont = FontFactory.getFont(CALIBRI_FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 10, Font.NORMAL);
	
	private static List<Expense> expenseList;
	
	private static String isCouponOnCredit;
		
	public static byte[] generatePDFDocument(String FileName, List<Expense> expenseList, String isCouponOnCredit){
			
			setExpenseList(expenseList);
			
			setIsCouponOnCredit(isCouponOnCredit);
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			try{
				
				Document document = new Document();
				
				PdfWriter.getInstance(document, baos);
				
				document.setPageSize(PageSize.A4);
				
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
			
			float[] columnWidths = {50, 15, 15, 15};
			
			PdfPTable pdfContent = new PdfPTable(columnWidths);
			
			PdfPCell cell = new PdfPCell(PDFHeaderContent());
			cell.setColspan(4);
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pdfContent.addCell(cell);
			
			insertCell(pdfContent, "Detail", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
			insertCell(pdfContent, "Account Number", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
			insertCell(pdfContent, "Debit", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
			insertCell(pdfContent, "Credit", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
			
			double total_expense = 0.0;
			double fuel_value = 0.0;
						
			for(int i = 0; i < expenseList.size(); i++){
				
				if(expenseList.get(i).getExpenseType().getAccount_number().equals("7003")){
					
					fuel_value = expenseList.get(i).getExpense_amount();
				}
				
				total_expense += expenseList.get(i).getExpense_amount();
								
				insertCell(pdfContent, expenseList.get(i).getExpenseType().getExpense_type_name(), Element.ALIGN_LEFT, 1, 1, tblContentFont);
				insertCell(pdfContent, expenseList.get(i).getExpenseType().getAccount_number(), Element.ALIGN_CENTER, 1, 1, tblContentFont);				
				insertCell(pdfContent, String.format("%,.2f", expenseList.get(i).getExpense_amount()), Element.ALIGN_RIGHT, 1, 1, tblContentFont);				
				insertCell(pdfContent, "", Element.ALIGN_CENTER, 1, 1, tblContentFont);
				
			}
			
			if(getIsCouponOnCredit().equals("true")){
				
				insertCell(pdfContent, " ", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
				insertCell(pdfContent, " ", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
				insertCell(pdfContent, " ", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
				insertCell(pdfContent, String.format("%,.2f", fuel_value), Element.ALIGN_RIGHT, 1, 1, tblContentFont);
				
				insertCell(pdfContent, " ", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
				insertCell(pdfContent, " ", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
				insertCell(pdfContent, " ", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
				insertCell(pdfContent, String.format("%,.2f", total_expense-fuel_value), Element.ALIGN_RIGHT, 1, 1, tblContentFont);
			
			} else {
			
				insertCell(pdfContent, " ", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
				insertCell(pdfContent, " ", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
				insertCell(pdfContent, " ", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
				insertCell(pdfContent, String.format("%,.2f", total_expense), Element.ALIGN_RIGHT, 1, 1, tblContentFont);
			}
			insertCell(pdfContent, "Total ", Element.ALIGN_RIGHT, 2, 1, tblHeaderFont);
			insertCell(pdfContent, String.format("%,.2f", total_expense), Element.ALIGN_RIGHT, 1, 1, tblHeaderFont);
			insertCell(pdfContent, String.format("%,.2f", total_expense), Element.ALIGN_RIGHT, 1, 1, tblHeaderFont);
			
//			insertCell(pdfContent, " ", Element.ALIGN_RIGHT, 2, 1, tblHeaderFont);
//			insertCell(pdfContent, " ", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
//			insertCell(pdfContent, String.format("%,.2f", total_expense), Element.ALIGN_RIGHT, 1, 1, tblHeaderFont);
			
			cell = new PdfPCell(PDFFooterContent());
			cell.setColspan(4);
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pdfContent.addCell(cell);
			
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
			
			PdfPTable pdfHeader = new PdfPTable(2);
			
			PdfPCell cell = new PdfPCell(PDFHeaderBox());
			cell.setColspan(1);
			cell.setRowspan(3);
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pdfHeader.addCell(cell);
			
			cell = new PdfPCell(new Phrase("J.V.No ________________________", tblContentFont));
			cell.setColspan(1);
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			pdfHeader.addCell(cell);
			
			cell = new PdfPCell(new Phrase(" ", tblContentFont));
			cell.setColspan(1);
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			pdfHeader.addCell(cell);
			
			cell = new PdfPCell(new Phrase("Date _________________________", tblContentFont));
			cell.setColspan(1);
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			pdfHeader.addCell(cell);
			
			cell = new PdfPCell(new Phrase("JOURNAL VOUCHER", customHeaderFont));
			cell.setColspan(1);
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			pdfHeader.addCell(cell);
			
			cell = new PdfPCell(new Phrase("FON "+ expenseList.get(0).getFrightOrder().getFon() + ", Truck Plate No. " + expenseList.get(0).getFrightOrder().getTruckInformation().getTruck_plate_no(), tblContentFont));
			cell.setColspan(1);
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			pdfHeader.addCell(cell);
			
			return pdfHeader;
		}
		
		public static PdfPTable PDFFooterContent(){
			
			PdfPTable pdfFooter = new PdfPTable(2);
			
			PdfPCell cell = new PdfPCell(new Phrase(" ", tblContentFont));
			cell.setColspan(2);
			cell.setBorder(0);			
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			pdfFooter.addCell(cell);
			
			cell = new PdfPCell(new Phrase("BEING  __________________________________________________________________________________________________ ", footerFont));
			cell.setColspan(2);
			cell.setBorder(0);			
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			pdfFooter.addCell(cell);
			
			cell = new PdfPCell(new Phrase("________________________________________________________________________________________________________ ", footerFont));
			cell.setColspan(2);
			cell.setBorder(0);			
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			pdfFooter.addCell(cell);
			
			cell = new PdfPCell(new Phrase("________________________________________________________________________________________________________ ", footerFont));
			cell.setColspan(2);
			cell.setBorder(0);			
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			pdfFooter.addCell(cell);
			
			cell = new PdfPCell(new Phrase("________________________________________________________________________________________________________ ", footerFont));
			cell.setColspan(2);
			cell.setBorder(0);			
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			pdfFooter.addCell(cell);
			
			cell = new PdfPCell(new Phrase(" ", tblContentFont));
			cell.setColspan(2);
			cell.setBorder(0);			
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			pdfFooter.addCell(cell);
			
			cell = new PdfPCell(new Phrase("PREPARED BY. _______________________________ ", footerFont));
			cell.setColspan(1);
			cell.setBorder(0);			
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			pdfFooter.addCell(cell);
			
			cell = new PdfPCell(new Phrase("      APPROVED BY. _______________________________ ", footerFont));
			cell.setColspan(1);
			cell.setBorder(0);			
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			pdfFooter.addCell(cell);
			
			return pdfFooter;
		}
		
		public static PdfPTable PDFHeaderBox(){
			
			PdfPTable pdfFooter = new PdfPTable(1);
			
			PdfPCell cell = new PdfPCell(new Phrase(" Tilahun Mesafint ", headerFont));
			cell.setColspan(1);
			cell.setBorderColor(BaseColor.WHITE);
			cell.setFixedHeight(40f);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			pdfFooter.addCell(cell);
			
			cell = new PdfPCell(new Phrase(" ", headerFont));
			cell.setColspan(2);
			cell.setBorder(0);			
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pdfFooter.addCell(cell);
			
			return pdfFooter;
		}
		
//		private static void addEmptyLine(Paragraph paragraph, int number){
//			for(int i = 0; i < number; i++){
//				paragraph.add(new Paragraph(" "));
//			}
//		}

		public static List<Expense> getExpenseList() {
			return expenseList;
		}

		public static void setExpenseList(List<Expense> expenseList) {
			AttachmentPDF_FrightOrderRegisteredExpenseList.expenseList = expenseList;
		}

		public static String getIsCouponOnCredit() {
			return isCouponOnCredit;
		}

		public static void setIsCouponOnCredit(String isCouponOnCredit) {
			AttachmentPDF_FrightOrderRegisteredExpenseList.isCouponOnCredit = isCouponOnCredit;
		}
}
