package org.fidel.tms.report;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fidel.tms.controller.FilesController;
import org.fidel.tms.model.FrightOrder;
import org.fidel.tms.service.FilesService;
import org.fidel.tms.utils.SysConstant;
import org.fidel.tms.utils.TodayDate_YYYYMMDD;
import org.springframework.beans.factory.annotation.Autowired;

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
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

public class ReportPDF_CollectedPayment extends HttpServlet {

	private static final long serialVersionUID = -755078667905032650L;

	public static final String FONT = "C:/windows/fonts/ARIALUNI.TTF";
	public static final String CALIBRI_FONT = "C:/windows/fonts/calibri.ttf";

	private static Font customHeaderFont = FontFactory.getFont(CALIBRI_FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 15,
			Font.BOLD);
	BaseFont baseFont = customHeaderFont.getBaseFont();

	// private static Font headerFont = new Font(Font.FontFamily.TIMES_ROMAN,
	// 14, Font.BOLD);
	private static Font headerFont = FontFactory.getFont(CALIBRI_FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 10,
			Font.BOLD);

	private static Font titleFont = FontFactory.getFont(CALIBRI_FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 9, Font.BOLD);

	// private static Font tblHeaderFont = new Font(Font.FontFamily.TIMES_ROMAN,
	// 12, Font.BOLD);
	private static Font tblHeaderFont = FontFactory.getFont(CALIBRI_FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 9,
			Font.BOLD);

	// private static Font tblContentFont = new
	// Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
	private static Font tblContentFont = FontFactory.getFont(FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 8, Font.NORMAL);

	private static List<FrightOrder> collectedPyamentList;

	public static void downloadPDF(HttpServletResponse response, String fileName, String fName) throws IOException {
		
		response.setContentType("application/octet-stream");
		String disHeader = "Attachment; Filename=\"" + fName + "\"";
		response.setHeader("Content-Disposition", disHeader);
		
		File fileToDownload = new File(fileName);
		
		InputStream in = null;
		ServletOutputStream outs = response.getOutputStream();
		
		try{
			in = new BufferedInputStream(new FileInputStream(fileToDownload));
			int ch;
			while((ch = in.read()) != -1){
				outs.print((char)ch);
			}
		} finally {
			if(in != null){ in.close(); }
			outs.flush();
			outs.close();
		}
	}
	
	private static final int BUFFERED_SIZE = 4096;
	
	public static void doDownload(HttpServletRequest request, HttpServletResponse response, String fileDestination) throws IOException{
		
		String filePath = fileDestination;
		
		//get absolute path of the application
		ServletContext context = request.getServletContext();
		String appPath = context.getRealPath("");
		//System.out.println("appPath" + appPath);
		
		//construct the complete absolute path of file
		String fullPath = appPath + filePath;
		File downloadFile = new File(fullPath);
		FileInputStream inputStream = new FileInputStream(downloadFile);
		//System.out.println("fullPath " + fullPath);
		
		//get MIME type of the file
		String mimeType = context.getMimeType(fullPath);
		if(mimeType == null){
			mimeType = "application/octet-stream";
		}
		//System.out.println("mimeType " + mimeType);
		
		//set content attribute for the responce
		response.setContentType(mimeType);
		response.setContentLength((int)downloadFile.length());
		
		//set headers for the response
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename = \"%s\"", downloadFile.getName());
		response.setHeader(headerKey, headerValue);
		
		//get output stream of the response
		OutputStream outStream = response.getOutputStream();
		
		byte[] buffer = new byte[BUFFERED_SIZE];
		int bytesRead = -1;
		
		//write bytes read from the input stream into the output stream
		while((bytesRead = inputStream.read(buffer)) != -1){
			outStream.write(buffer, 0, bytesRead);
		}
		
		inputStream.close();
		outStream.close();
		
	}
	
	public static byte[] generatePDFDocument(String FileName, List<FrightOrder> collectedPyamentList, HttpServletRequest request, HttpServletResponse response, String fName) {

		setCollectedPyamentList(collectedPyamentList);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {

			Document document = new Document();

//			PdfWriter.getInstance(document, new FileOutputStream(FileName));			
						
			PdfWriter.getInstance(document, baos);

			document.setPageSize(PageSize.A4.rotate());

			document.open();

			addPDFMetaData(document, FileName);

			addPDFContent(document);

			document.close();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return baos.toByteArray();
		
		
		
//		downloadPDF(response, FileName, fName);

//////>>>> Option 0
//		//get absolute path of the application
//		ServletContext context = request.getServletContext();
//		String appPath = context.getRealPath("");
//		System.out.println("appPath" + appPath);
//		
//		//construct the complete absolute path of file
//		String fullPath = appPath + fileDestination;
//					
//		File source = new File(FileName);
//		File dest = new File(fullPath);			
//		Files.copy(source.toPath(), dest.toPath());
//		
//		doDownload(request, response, fileDestination);
		
//////>>>> Option 1
//////>>> problem --- Cannot call sendRedirect() after the response has been committed
//		response.reset();
//		response.setContentType("application/pdf");
//		
//		ServletOutputStream sos = null;
//		BufferedInputStream bis = null;
//		BufferedOutputStream bos = null;
//		
//		bis = new BufferedInputStream(new FileInputStream(FileName));
//		byte[] data = new byte[bis.available()];
//		bis.read(data);
//		
//		response.setContentLength(data.length);
//		response.setHeader("Content-disposition", "inline;filename=" + FileName);
//		
//		sos = response.getOutputStream();
//		bos = new BufferedOutputStream(sos);
//		bos.write(data);
//		
//		bos.flush();
//		bos.close();
//		bis.close();
//		response.sendRedirect("collected_payment_inline_pdf.jsp");

		
//////>>>> Option 2
//		File file = new File(FileName);
//		System.out.println(file.length() + " length");
//		byte bytes[] = new byte[(int)file.length()];
//		new FileInputStream(file).read(bytes);
//		response.setContentType("application/vnd.pdf");
//		response.setHeader("Content-disposition", "inline;filename=" + FileName);
//		response.setContentLength(bytes.length);
//		
//		OutputStream out = response.getOutputStream();
//		out.write(bytes);
//		out.flush();
//		out.close();

		
//////>>>> Option 3
//		System.out.println(baos.size() + " size ");
//		response.setContentType("application/pdf");
//		response.setContentLength(baos.size());			
//		response.setHeader("Content-Disposition", "inline; filename=" + FileName);
//		response.setHeader("Expires", "0");
//		response.setHeader("Cache-Control", "must-revalidate,post-check=0,pre-check=0");
//		response.setHeader("Pragma", "public");
//		
//		ServletOutputStream os = response.getOutputStream();
//		baos.writeTo(os);
//		
//		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(os);
//		bufferedOutputStream.write(baos.toByteArray(), 0, baos.size());
//		bufferedOutputStream.flush();
//		
//		os.flush();
//		os.close();
//		baos.close();
//		bufferedOutputStream.flush();
//		bufferedOutputStream.close();
		
	}

	public static void addPDFMetaData(Document document, String FileName) {
		document.addTitle("NotClosedFrightOrderList");
		document.addSubject("TilahunMesafent");
		document.addKeywords("java, PDF, iText" + FileName);
		document.addAuthor("Yofetahe");
		document.addCreator("Fidel");
	}

	public static void addPDFContent(Document document) {

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

	public static PdfPTable PDFBodyContent() {

		float[] columnWidths = { 4, 10, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7 };

		PdfPTable pdfContent = new PdfPTable(columnWidths);

		PdfPCell cell = new PdfPCell(PDFHeaderContent());
		cell.setColspan(13);
		cell.setBorder(0);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pdfContent.addCell(cell);

		insertCell(pdfContent, "No", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
		insertCell(pdfContent, "Client Organization", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
		insertCell(pdfContent, "Fright Order No.", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
		insertCell(pdfContent, "Truck Plate No.", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
		
		insertCell(pdfContent, "Price", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
		insertCell(pdfContent, "Delivered Quantity", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
		
		insertCell(pdfContent, "Commission", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
		insertCell(pdfContent, "Payment Amount", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
//		insertCell(pdfContent, "Payment Type", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
		insertCell(pdfContent, "Payment Doc. Ref. No", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
		insertCell(pdfContent, "CRSI Number", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
		insertCell(pdfContent, "Payment Date", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
		
		insertCell(pdfContent, "Place of Origin", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
		insertCell(pdfContent, "Destination Place", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
		
		double total_collected_payment = 0.0;

		for (int i = 0; i < collectedPyamentList.size(); i++) {

			int rowNum = i + 1;
			insertCell(pdfContent, String.valueOf(rowNum), Element.ALIGN_CENTER, 1, 1, tblContentFont);
			insertCell(pdfContent, collectedPyamentList.get(i).getFrightOrderTripDetail().getClient_organization(), Element.ALIGN_LEFT, 1, 1, tblContentFont);
			insertCell(pdfContent, collectedPyamentList.get(i).getFon(), Element.ALIGN_CENTER, 1, 1, tblContentFont);
			insertCell(pdfContent, collectedPyamentList.get(i).getTruckInformation().getTruck_plate_no(), Element.ALIGN_CENTER, 1, 1, tblContentFont);
			
			insertCell(pdfContent, String.format("%,.2f", collectedPyamentList.get(i).getFrightOrderTripDetail().getPrice()), Element.ALIGN_CENTER, 1, 1, tblContentFont);
			insertCell(pdfContent, String.valueOf(collectedPyamentList.get(i).getFrightOrderTripDetail().getDelivered_quantity()), Element.ALIGN_CENTER, 1, 1, tblContentFont);
			
			insertCell(pdfContent, String.valueOf(collectedPyamentList.get(i).getCommission()), Element.ALIGN_CENTER, 1, 1, tblContentFont);
			insertCell(pdfContent, String.format("%,.2f", collectedPyamentList.get(i).getPayment().getPayment_amount()), Element.ALIGN_RIGHT, 1, 1, tblContentFont);
			
			total_collected_payment += collectedPyamentList.get(i).getPayment().getPayment_amount();
			
//			insertCell(pdfContent, collectedPyamentList.get(i).getPayment().getPayment_type(), Element.ALIGN_CENTER, 1, 1, tblContentFont);
			insertCell(pdfContent, collectedPyamentList.get(i).getPayment().getPayment_doc_ref_no(), Element.ALIGN_CENTER, 1, 1, tblContentFont);
			insertCell(pdfContent, collectedPyamentList.get(i).getFrightOrderTripDetail().getCrv_number(), Element.ALIGN_CENTER, 1, 1, tblContentFont);
			insertCell(pdfContent, collectedPyamentList.get(i).getPayment().getPayment_date(), Element.ALIGN_CENTER, 1, 1, tblContentFont);
			
			insertCell(pdfContent, collectedPyamentList.get(i).getFrightOrderTripDetail().getOrigin_place(), Element.ALIGN_CENTER, 1, 1, tblContentFont);
			insertCell(pdfContent, collectedPyamentList.get(i).getFrightOrderTripDetail().getDestination_place(), Element.ALIGN_CENTER, 1, 1, tblContentFont);
		}
		
		insertCell(pdfContent, "Total Collected ", Element.ALIGN_RIGHT, 6, 1, tblContentFont);
		insertCell(pdfContent, String.format("%,.2f", total_collected_payment), Element.ALIGN_RIGHT, 1, 1, tblContentFont);
		insertCell(pdfContent, "", Element.ALIGN_CENTER, 6, 1, tblContentFont);

		return pdfContent;
	}

	private static void insertCell(PdfPTable table, String text, int align, int colspan, int rowspan, Font font) {

		String val = text + "";

		PdfPCell cell = new PdfPCell(new Phrase(text == null ? "" : text, font));

		cell.setHorizontalAlignment(align);

		// cell.setBorder(0);
		cell.setRowspan(rowspan);

		cell.setColspan(colspan);

		if (val.equalsIgnoreCase("")) {
			cell.setMinimumHeight(15f);
		}

		cell.setMinimumHeight(15f);

		table.addCell(cell);
	}

	public static PdfPTable PDFHeaderContent() {

		PdfPTable pdfHeader = new PdfPTable(1);

		PdfPCell cell = new PdfPCell(new Phrase(SysConstant.ORGANIZATION_FULL_NAME, customHeaderFont));
		cell.setColspan(1);
		cell.setBorder(0);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pdfHeader.addCell(cell);

		cell = new PdfPCell(new Phrase("Collected Paymnet", headerFont));
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

	public static PdfPTable PDFFooterContent() {

		PdfPTable pdfFooter = new PdfPTable(1);

		return pdfFooter;
	}

	public static List<FrightOrder> getCollectedPyamentList() {
		return collectedPyamentList;
	}

	public static void setCollectedPyamentList(List<FrightOrder> collectedPyamentList) {
		ReportPDF_CollectedPayment.collectedPyamentList = collectedPyamentList;
	}
}
