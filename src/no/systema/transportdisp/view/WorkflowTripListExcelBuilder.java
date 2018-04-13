/**
 * 
 */
package no.systema.transportdisp.view;

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

import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowListRecord;
import no.systema.transportdisp.util.TransportDispConstants;
import no.systema.main.context.TdsAppContext;
/**
 * 
 * @author oscardelatorre
 * @date Maj 4, 2015
 * 
 */
public class WorkflowTripListExcelBuilder extends AbstractExcelView {
	private ApplicationContext context;
	
	public WorkflowTripListExcelBuilder(){
		this.context = TdsAppContext.getApplicationContext();
	}
	
	protected void buildExcelDocument(Map<String, Object> model,
        HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // get data model which is passed by the Spring Container via our own Controller implementation
        List<JsonTransportDispWorkflowListRecord> list = (List<JsonTransportDispWorkflowListRecord>) model.get(TransportDispConstants.DOMAIN_LIST);
         
        // create a new Excel sheet
        HSSFSheet sheet = workbook.createSheet("SHIPPING TRIP list");
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

        header.createCell(0).setCellValue(this.context.getMessage("systema.transportdisp.workflow.trip.list.search.label.department", new Object[0], locale));
        header.getCell(0).setCellStyle(style);
        
        header.createCell(1).setCellValue(this.context.getMessage("systema.transportdisp.workflow.trip.list.search.label.trip", new Object[0], locale));
        header.getCell(1).setCellStyle(style);
        
        header.createCell(2).setCellValue(this.context.getMessage("systema.transportdisp.workflow.trip.list.search.label.sign", new Object[0], locale));
        header.getCell(2).setCellStyle(style);
        
        header.createCell(3).setCellValue(this.context.getMessage("systema.transportdisp.workflow.trip.list.search.label.trucknr", new Object[0], locale));
        header.getCell(3).setCellStyle(style);
        
        header.createCell(4).setCellValue(this.context.getMessage("systema.transportdisp.workflow.trip.list.search.label.ordertype", new Object[0], locale));
        header.getCell(4).setCellStyle(style);
        
        header.createCell(5).setCellValue(this.context.getMessage("systema.transportdisp.workflow.trip.list.search.label.pda.status", new Object[0], locale));
        header.getCell(5).setCellStyle(style);
        
        header.createCell(6).setCellValue(this.context.getMessage("systema.transportdisp.workflow.trip.list.search.label.from", new Object[0], locale));
        header.getCell(6).setCellStyle(style);
         
        header.createCell(7).setCellValue(this.context.getMessage("systema.transportdisp.workflow.trip.list.search.label.date", new Object[0], locale));
        header.getCell(7).setCellStyle(style);
        
        header.createCell(8).setCellValue(this.context.getMessage("systema.transportdisp.workflow.trip.list.search.label.to", new Object[0], locale));
        header.getCell(8).setCellStyle(style);
        
        header.createCell(9).setCellValue(this.context.getMessage("systema.transportdisp.workflow.trip.list.search.label.date", new Object[0], locale));
        header.getCell(9).setCellStyle(style);
        
        header.createCell(10).setCellValue(this.context.getMessage("systema.transportdisp.workflow.trip.list.search.label.roundTrip", new Object[0], locale));
        header.getCell(10).setCellStyle(style);
        
        header.createCell(11).setCellValue(this.context.getMessage("systema.transportdisp.workflow.trip.list.search.label.antopd", new Object[0], locale));
        header.getCell(11).setCellStyle(style);
        
        header.createCell(12).setCellValue(this.context.getMessage("systema.transportdisp.workflow.trip.list.search.label.antpod", new Object[0], locale));
        header.getCell(12).setCellStyle(style);
        
        header.createCell(13).setCellValue(this.context.getMessage("systema.transportdisp.workflow.trip.list.search.label.weight", new Object[0], locale));
        header.getCell(13).setCellStyle(style);
        
        header.createCell(14).setCellValue(this.context.getMessage("systema.transportdisp.workflow.trip.list.search.label.m3", new Object[0], locale));
        header.getCell(14).setCellStyle(style);
        
        header.createCell(15).setCellValue(this.context.getMessage("systema.transportdisp.workflow.trip.list.search.label.lm", new Object[0], locale));
        header.getCell(15).setCellStyle(style);
        
        header.createCell(16).setCellValue(this.context.getMessage("systema.transportdisp.workflow.trip.list.search.label.fg", new Object[0], locale));
        header.getCell(16).setCellStyle(style);
        
        header.createCell(17).setCellValue(this.context.getMessage("systema.transportdisp.workflow.trip.list.search.label.res", new Object[0], locale));
        header.getCell(17).setCellStyle(style);
        
        // create data rows
        int rowCount = 1;
        for (JsonTransportDispWorkflowListRecord record : list) {
            HSSFRow aRow = sheet.createRow(rowCount++);
            aRow.createCell(0).setCellValue(record.getTuavd());
            aRow.createCell(1).setCellValue(record.getTupro());
            aRow.createCell(2).setCellValue(record.getTusg());
            aRow.createCell(3).setCellValue(record.getTubiln());
            aRow.createCell(4).setCellValue(record.getTuopdt());
            aRow.createCell(5).setCellValue(record.getPdaStat());
            aRow.createCell(6).setCellValue(record.getTustef());
            aRow.createCell(7).setCellValue(record.getTudt());
            aRow.createCell(8).setCellValue(record.getTustet());
            aRow.createCell(9).setCellValue(record.getTudtt());
            aRow.createCell(10).setCellValue(record.getTurund());
            aRow.createCell(11).setCellValue(record.getTuao());
            aRow.createCell(12).setCellValue(record.getPodTxt());
            aRow.createCell(13).setCellValue(record.getTutvkt());
            aRow.createCell(14).setCellValue(record.getTutm3());
            aRow.createCell(15).setCellValue(record.getTutlm2());
            aRow.createCell(16).setCellValue(record.getTupoen());
            aRow.createCell(17).setCellValue(record.getTures());
        }
    }
	
}
