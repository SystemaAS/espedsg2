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

import no.systema.transportdisp.model.jsonjackson.workflow.shippinglists.JsonTransportDispWorkflowShippingPlanningOpenOrdersListRecord;
import no.systema.transportdisp.util.TransportDispConstants;
import no.systema.main.context.TdsAppContext;
/**
 * 
 * @author oscardelatorre
 * @date Maj 4, 2015
 * 
 */
public class WorkflowOpenOrdersListExcelBuilder extends AbstractExcelView {
	private ApplicationContext context;
	
	public WorkflowOpenOrdersListExcelBuilder(){
		this.context = TdsAppContext.getApplicationContext();
	}
	
	protected void buildExcelDocument(Map<String, Object> model,
        HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // get data model which is passed by the Spring Container via our own Controller implementation
        List<JsonTransportDispWorkflowShippingPlanningOpenOrdersListRecord> list = (List<JsonTransportDispWorkflowShippingPlanningOpenOrdersListRecord>) model.get(TransportDispConstants.DOMAIN_LIST_OPEN_ORDERS);
         
        // create a new Excel sheet
        HSSFSheet sheet = workbook.createSheet("OPEN ORDERS list");
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

        header.createCell(0).setCellValue(this.context.getMessage("systema.transportdisp.orders.open.list.search.label.ourRef", new Object[0], locale));
        header.getCell(0).setCellStyle(style);
        
        header.createCell(1).setCellValue(this.context.getMessage("systema.transportdisp.orders.open.list.search.label.poNr", new Object[0], locale));
        header.getCell(1).setCellStyle(style);
        
        header.createCell(2).setCellValue(this.context.getMessage("systema.transportdisp.orders.open.list.search.label.shipper", new Object[0], locale));
        header.getCell(2).setCellStyle(style);
        
        header.createCell(3).setCellValue(this.context.getMessage("systema.transportdisp.orders.open.list.search.label.from", new Object[0], locale));
        header.getCell(3).setCellStyle(style);
         
        header.createCell(4).setCellValue(this.context.getMessage("systema.transportdisp.orders.open.list.search.label.consignee", new Object[0], locale));
        header.getCell(4).setCellStyle(style);
        
        header.createCell(5).setCellValue(this.context.getMessage("systema.transportdisp.orders.open.list.search.label.to", new Object[0], locale));
        header.getCell(5).setCellStyle(style);
        
        header.createCell(6).setCellValue(this.context.getMessage("systema.transportdisp.orders.open.list.search.label.goodsDesc", new Object[0], locale));
        header.getCell(6).setCellStyle(style);

        header.createCell(7).setCellValue(this.context.getMessage("systema.transportdisp.orders.open.list.search.label.pcs", new Object[0], locale));
        header.getCell(7).setCellStyle(style);

        header.createCell(8).setCellValue(this.context.getMessage("systema.transportdisp.orders.open.list.search.label.weight", new Object[0], locale));
        header.getCell(8).setCellStyle(style);

        header.createCell(9).setCellValue(this.context.getMessage("systema.transportdisp.orders.open.list.search.label.volume", new Object[0], locale));
        header.getCell(9).setCellStyle(style);

        header.createCell(10).setCellValue(this.context.getMessage("systema.transportdisp.orders.open.list.search.label.loadMtr", new Object[0], locale));
        header.getCell(10).setCellStyle(style);

        
        
        // create data rows
        int rowCount = 1;
         
        for (JsonTransportDispWorkflowShippingPlanningOpenOrdersListRecord record : list) {
            HSSFRow aRow = sheet.createRow(rowCount++);
            aRow.createCell(0).setCellValue(record.getHeavd() + "/" + record.getHeopd());
            aRow.createCell(1).setCellValue(record.getHerfa());
            aRow.createCell(2).setCellValue(record.getHenas());
            aRow.createCell(3).setCellValue(record.getHelka() + " " + record.getHeads3());
            aRow.createCell(4).setCellValue(record.getHenak());
            aRow.createCell(5).setCellValue(record.getHetri() + " " + record.getHeadk3());
            aRow.createCell(6).setCellValue(record.getHevs1());
            aRow.createCell(7).setCellValue(record.getHent());
            aRow.createCell(8).setCellValue(record.getHevkt());
            aRow.createCell(9).setCellValue(record.getHem3());
            aRow.createCell(10).setCellValue(record.getHelm());
        }
    }
	
}
