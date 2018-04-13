/**
 * 
 */
package no.systema.sporringoppdrag.view;

import java.util.*;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.view.document.AbstractExcelView;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;

import no.systema.sporringoppdrag.model.jsonjackson.topic.JsonSporringOppdragTopicListRecord;
import no.systema.sporringoppdrag.util.SporringOppdragConstants;
import no.systema.main.context.TdsAppContext;
/**
 * 
 * @author oscardelatorre
 * @date Feb 16, 2015
 * 
 */
public class MainListExcelBuilder extends AbstractExcelView {
	private ApplicationContext context;
	
	public MainListExcelBuilder(){
		this.context = TdsAppContext.getApplicationContext();
	}
	
	protected void buildExcelDocument(Map<String, Object> model,
        HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // get data model which is passed by the Spring Container via our own Controller implementation
        List<JsonSporringOppdragTopicListRecord> itemList = (List<JsonSporringOppdragTopicListRecord>) model.get(SporringOppdragConstants.DOMAIN_LIST);
         
        // create a new Excel sheet
        HSSFSheet sheet = workbook.createSheet("SP.OPPDRAG list");
        sheet.setDefaultColumnWidth(30);
         
        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE.index);
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);

        //Note: the locale must be fetched from the response since we are working with the Spring Interceptor.
        Locale locale = response.getLocale();
        // create header row
        HSSFRow header = sheet.createRow(0);

        header.createCell(0).setCellValue(this.context.getMessage("systema.sporringoppdrag.mainlist.column.label.avdOppdr", new Object[0], locale));
        header.getCell(0).setCellStyle(style);
         
        header.createCell(1).setCellValue(this.context.getMessage("systema.sporringoppdrag.mainlist.column.label.turNr", new Object[0], locale));
        header.getCell(1).setCellStyle(style);
        
        header.createCell(2).setCellValue(this.context.getMessage("systema.sporringoppdrag.mainlist.column.label.oppdgRef", new Object[0], locale));
        header.getCell(2).setCellStyle(style);
        
        header.createCell(3).setCellValue(this.context.getMessage("systema.sporringoppdrag.mainlist.column.label.dato", new Object[0], locale));
        header.getCell(3).setCellStyle(style);
         
        header.createCell(4).setCellValue(this.context.getMessage("systema.sporringoppdrag.mainlist.column.label.fra", new Object[0], locale));
        header.getCell(4).setCellStyle(style);
        
        header.createCell(5).setCellValue(this.context.getMessage("systema.sporringoppdrag.mainlist.column.label.til", new Object[0], locale));
        header.getCell(5).setCellStyle(style);
        
        header.createCell(6).setCellValue(this.context.getMessage("systema.sporringoppdrag.mainlist.column.label.avsender", new Object[0], locale));
        header.getCell(6).setCellStyle(style);
        
        header.createCell(7).setCellValue(this.context.getMessage("systema.sporringoppdrag.mainlist.column.label.mottaker", new Object[0], locale));
        header.getCell(7).setCellStyle(style);
        
        header.createCell(8).setCellValue(this.context.getMessage("systema.sporringoppdrag.mainlist.column.label.prKD", new Object[0], locale));
        header.getCell(8).setCellStyle(style);
        
        header.createCell(9).setCellValue(this.context.getMessage("systema.sporringoppdrag.mainlist.column.label.antall", new Object[0], locale));
        header.getCell(9).setCellStyle(style);
        
        header.createCell(10).setCellValue(this.context.getMessage("systema.sporringoppdrag.mainlist.column.label.vekt", new Object[0], locale));
        header.getCell(10).setCellStyle(style);
        
        header.createCell(11).setCellValue(this.context.getMessage("systema.sporringoppdrag.mainlist.column.label.kubikk", new Object[0], locale));
        header.getCell(11).setCellStyle(style);
        
        header.createCell(12).setCellValue(this.context.getMessage("systema.sporringoppdrag.mainlist.column.label.lm", new Object[0], locale));
        header.getCell(12).setCellStyle(style);
        
        header.createCell(13).setCellValue(this.context.getMessage("systema.sporringoppdrag.mainlist.column.label.levert", new Object[0], locale));
        header.getCell(13).setCellStyle(style);
        
        header.createCell(14).setCellValue(this.context.getMessage("systema.sporringoppdrag.mainlist.column.label.faktsum", new Object[0], locale));
        header.getCell(14).setCellStyle(style);
        
        
        // create data rows
        int rowCount = 1;
         
        for (JsonSporringOppdragTopicListRecord record : itemList) {
            HSSFRow aRow = sheet.createRow(rowCount++);
            String avdOpd = record.getHeavd() + "/" + record.getHeopd();
            aRow.createCell(0).setCellValue(avdOpd);
            
            aRow.createCell(1).setCellValue(record.getHepro());
            aRow.createCell(2).setCellValue(record.getXwsref());
            aRow.createCell(3).setCellValue(record.getHedtop());
            aRow.createCell(4).setCellValue(record.getHesdf());
            aRow.createCell(5).setCellValue(record.getHesdt());
            aRow.createCell(6).setCellValue(record.getHenas());
            aRow.createCell(7).setCellValue(record.getHenak());
            aRow.createCell(8).setCellValue(record.getHekdpl());
            aRow.createCell(9).setCellValue(record.getHent());
            aRow.createCell(10).setCellValue(record.getHevkt());
            aRow.createCell(11).setCellValue(record.getHem3());
            aRow.createCell(12).setCellValue(record.getHelm());
            aRow.createCell(13).setCellValue(record.getPoddato());
            aRow.createCell(14).setCellValue(record.getFaktsum());
            
        }
    }
	
}
