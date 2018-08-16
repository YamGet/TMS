package org.fidel.tms.report;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.fidel.tms.model.FrightOrder;
import org.fidel.tms.utils.SysConstant;

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

public class ReportPDF_LoadingUnloadingDiffernecePerTruck {
	
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
	
	private static List<FrightOrder> getLoadingUnloadingDifferencePerTruck;
		
	public static byte[] generatePDFDocument(String FileName, List<FrightOrder> getLoadingUnloadingDifferencePerTruck){
		
		setGetLoadingUnloadingDifferencePerTruck(getLoadingUnloadingDifferencePerTruck);
			
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
			
			float[] columnWidths = {4, 5, 10, 7, 7, 7, 7, 7, 7, 7};
			
			PdfPTable pdfContent = new PdfPTable(columnWidths);
			
			PdfPCell cell = new PdfPCell(PDFHeaderContent());
			cell.setColspan(13);
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pdfContent.addCell(cell);
			
			insertCell(pdfContent, "No", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
			insertCell(pdfContent, "FON", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
			insertCell(pdfContent, "Driver Name", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
			insertCell(pdfContent, "Loading Date", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
			insertCell(pdfContent, "Loading Type", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);			
			insertCell(pdfContent, "Origin Place", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
			insertCell(pdfContent, "Destination Place", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
			insertCell(pdfContent, "Loading Quantity", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
			insertCell(pdfContent, "Delivered Quantity", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);			
			insertCell(pdfContent, "Difference", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);			
			
			for(int i = 0; i < getLoadingUnloadingDifferencePerTruck.size(); i++){
				
				int rowNum = i + 1;
				insertCell(pdfContent, String.valueOf(rowNum), Element.ALIGN_CENTER, 1, 1, tblContentFont);
				
				insertCell(pdfContent, getLoadingUnloadingDifferencePerTruck.get(i).getFon(), Element.ALIGN_LEFT, 1, 1, tblContentFont);
				insertCell(pdfContent, getLoadingUnloadingDifferencePerTruck.get(i).getDrivers().getFname() + " " + getLoadingUnloadingDifferencePerTruck.get(i).getDrivers().getMname(), Element.ALIGN_LEFT, 1, 1, tblContentFont);
				insertCell(pdfContent, getLoadingUnloadingDifferencePerTruck.get(i).getDate_from(), Element.ALIGN_CENTER, 1, 1, tblContentFont);
				insertCell(pdfContent, getLoadingUnloadingDifferencePerTruck.get(i).getFrightOrderTripDetail().getLoading_type(), Element.ALIGN_CENTER, 1, 1, tblContentFont);
				
				insertCell(pdfContent, getLoadingUnloadingDifferencePerTruck.get(i).getFrightOrderTripDetail().getOrigin_place(), Element.ALIGN_LEFT, 1, 1, tblContentFont);
				insertCell(pdfContent, getLoadingUnloadingDifferencePerTruck.get(i).getFrightOrderTripDetail().getDestination_place(), Element.ALIGN_LEFT, 1, 1, tblContentFont);
				
				insertCell(pdfContent, getLoadingUnloadingDifferencePerTruck.get(i).getFrightOrderTripDetail().getLoading_quantity(), Element.ALIGN_CENTER, 1, 1, tblContentFont);
				insertCell(pdfContent, String.valueOf(getLoadingUnloadingDifferencePerTruck.get(i).getFrightOrderTripDetail().getDelivered_quantity()), Element.ALIGN_CENTER, 1, 1, tblContentFont);
				
				double loading_qty = Double.parseDouble(getLoadingUnloadingDifferencePerTruck.get(i).getFrightOrderTripDetail().getLoading_quantity());
				double delivered_qty = getLoadingUnloadingDifferencePerTruck.get(i).getFrightOrderTripDetail().getDelivered_quantity();
				
				insertCell(pdfContent, String.valueOf(loading_qty - delivered_qty), Element.ALIGN_RIGHT, 1, 1, tblContentFont);
			}
			
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
			
			cell = new PdfPCell(new Phrase("Loading Unloading Difference Per Truck", headerFont));
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

		public static List<FrightOrder> getGetLoadingUnloadingDifferencePerTruck() {
			return getLoadingUnloadingDifferencePerTruck;
		}

		public static void setGetLoadingUnloadingDifferencePerTruck(List<FrightOrder> getLoadingUnloadingDifferencePerTruck) {
			ReportPDF_LoadingUnloadingDiffernecePerTruck.getLoadingUnloadingDifferencePerTruck = getLoadingUnloadingDifferencePerTruck;
		}
		
//		private static void addEmptyLine(Paragraph paragraph, int number){
//			for(int i = 0; i < number; i++){
//				paragraph.add(new Paragraph(" "));
//			}
//		}

}
