package org.fidel.tms.report;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.fidel.tms.model.Expense;
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

public class ReportPDF_RevenueExpensePerFon {
	
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
	
	private static List<Expense> expRevPerFonReport;
	
	private static Expense expense;
		
	public static byte[] generatePDFDocument(String FileName, List<Expense> expRevPerFonReport, Expense expense){
			
			setExpRevPerFonReport(expRevPerFonReport);
			
			setExpense(expense);
			
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
			
			float[] columnWidths = {4, 10, 7, 7, 7};
			
			PdfPTable pdfContent = new PdfPTable(columnWidths);
			
			PdfPCell cell = new PdfPCell(PDFHeaderContent());
			cell.setColspan(5);
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pdfContent.addCell(cell);
			
			insertCell(pdfContent, "No", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
			insertCell(pdfContent, "Fright Order No.", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
			insertCell(pdfContent, "Revenue", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
			insertCell(pdfContent, "Expense", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
			insertCell(pdfContent, "Net Difference", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
			
			double rev_grand_total = 0.0, exp_grand_total = 0.0, grand_total = 0.0;
			
			for(int i = 0; i < expRevPerFonReport.size(); i++){
				
				rev_grand_total += expRevPerFonReport.get(i).getTotal_revenue();
				exp_grand_total += expRevPerFonReport.get(i).getTotal_expense();
				
				double netDiff = expRevPerFonReport.get(i).getTotal_revenue() - expRevPerFonReport.get(i).getTotal_expense();
				grand_total += netDiff;
				
				int rowNum = i + 1;
				insertCell(pdfContent, String.valueOf(rowNum), Element.ALIGN_CENTER, 1, 1, tblContentFont);
				insertCell(pdfContent, expRevPerFonReport.get(i).getFrightOrder().getFon(), Element.ALIGN_CENTER, 1, 1, tblContentFont);
				insertCell(pdfContent, String.format("%,.2f", expRevPerFonReport.get(i).getTotal_revenue()), Element.ALIGN_RIGHT, 1, 1, tblContentFont);
				insertCell(pdfContent, String.format("%,.2f", expRevPerFonReport.get(i).getTotal_expense()), Element.ALIGN_RIGHT, 1, 1, tblContentFont);
				insertCell(pdfContent, String.format("%,.2f", netDiff), Element.ALIGN_RIGHT, 1, 1, tblContentFont);
								
			}
			
			insertCell(pdfContent, "Total", Element.ALIGN_CENTER, 2, 1, tblHeaderFont);
			insertCell(pdfContent, String.format("%,.2f", rev_grand_total), Element.ALIGN_RIGHT, 1, 1, tblHeaderFont);
			insertCell(pdfContent, String.format("%,.2f", exp_grand_total), Element.ALIGN_RIGHT, 1, 1, tblHeaderFont);
			insertCell(pdfContent, String.format("%,.2f", grand_total), Element.ALIGN_RIGHT, 1, 1, tblHeaderFont);
			
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
			
			cell = new PdfPCell(new Phrase("Revenue and Expense Per Fright Order", headerFont));
			cell.setColspan(1);
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pdfHeader.addCell(cell);
			
			cell = new PdfPCell(new Phrase(TodayDate_YYYYMMDD.getDayMonthYearFormat(expense.getDate_from()) + "    -    " + TodayDate_YYYYMMDD.getDayMonthYearFormat(expense.getDate_to()), headerFont));
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

		public static List<Expense> getExpRevPerFonReport() {
			return expRevPerFonReport;
		}

		public static void setExpRevPerFonReport(List<Expense> expRevPerFonReport) {
			ReportPDF_RevenueExpensePerFon.expRevPerFonReport = expRevPerFonReport;
		}

		public static Expense getExpense() {
			return expense;
		}

		public static void setExpense(Expense expense) {
			ReportPDF_RevenueExpensePerFon.expense = expense;
		}
}
