/**
 * 
 */
package no.systema.transportdisp.service.html.dropdown;

import java.util.List;

import org.apache.log4j.Logger;

import no.systema.main.context.TdsServletContext;
import no.systema.main.util.io.TextFileReaderService;
import no.systema.transportdisp.util.TransportDispConstants;
import no.systema.transportdisp.model.jsonjackson.workflow.codes.JsonTransportDispCodeContainer;
import no.systema.transportdisp.mapper.jsonjackson.JsonTransportDispCodeMapper;
import no.systema.transportdisp.mapper.jsonjackson.avdsignature.JsonTransportDispSignatureMapper;
import no.systema.transportdisp.mapper.jsonjackson.frankatur.JsonTransportDispFrankaturMapper;
import no.systema.transportdisp.mapper.jsonjackson.oppdragstype.JsonTransportDispOppdragTypeMapper;
import no.systema.transportdisp.mapper.jsonjackson.JsonTransportDispChildWindowMapper;

import no.systema.transportdisp.model.jsonjackson.workflow.avdsignature.JsonTransportDispSignatureContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.frankatur.JsonTransportDispFrankaturContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.oppdragstype.JsonTransportDispOppdragTypeContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.oppdragstype.JsonTransportDispOppdragTypeParametersContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.invoice.childwindow.JsonTransportDispGebyrCodeContainer;







/**
 * This service fetches values into drop downs in HTML
 * Criteria is based upon whether the drop down list is static or dynamic.
 * 
 * This class is used ONLY for STATIC lists (e.g. currency codes, country codes, etc)
 * 
 * The servlet context is necessary in order to get a text-xml file within the webb-application path...
 * All static lists are retrieved from a file on disk.
 *  
 * @author oscardelatorre
 * @date Apr 24, 2015
 * 
 */
public class TransportDispDropDownListPopulationService {
	private static final Logger logger = Logger.getLogger(TransportDispDropDownListPopulationService.class.getName());
	
	private final String FILE_RESOURCE_PATH = TransportDispConstants.RESOURCE_FILES_PATH;
	private TextFileReaderService textFileReaderService = new TextFileReaderService();
	private JsonTransportDispCodeMapper codeMapper = new JsonTransportDispCodeMapper();
	private JsonTransportDispSignatureMapper signMapper = new JsonTransportDispSignatureMapper();
	private JsonTransportDispFrankaturMapper frankaturMapper = new JsonTransportDispFrankaturMapper();
	private JsonTransportDispOppdragTypeMapper oppdragTypeMapper = new JsonTransportDispOppdragTypeMapper();
	private JsonTransportDispChildWindowMapper childWindowMapper = new JsonTransportDispChildWindowMapper();
	
	/**
	 * Years
	 * @return
	 */
	public List<String> getYearList(){
		String LIST_FILE = "yearList.txt";
		List<String> list = textFileReaderService.getFileLines(TdsServletContext.getTdsServletContext().getResourceAsStream(this.FILE_RESOURCE_PATH + LIST_FILE));
		//Debug
		/*
		for(String record : list){
			logger.info(record + "X");
		}*/
		return list;
	}
	
	/**
	 * Months
	 * @return
	 */
	public List<String> getMonthList(){
		String LIST_FILE = "monthList.txt";
		List<String> list = textFileReaderService.getFileLines(TdsServletContext.getTdsServletContext().getResourceAsStream(this.FILE_RESOURCE_PATH + LIST_FILE));
		//Debug
		/*
		for(String record : list){
			logger.info(record + "X");
		}*/
		return list;
	}
	/**
	 * 
	 * @return
	 */
	public List<String> getCurrencyList(){
		String LIST_FILE = "currencyList.txt";
		List<String> list = textFileReaderService.getFileLines(TdsServletContext.getTdsServletContext().getResourceAsStream(this.FILE_RESOURCE_PATH + LIST_FILE));
		//Debug
		/*
		for(String record : list){
			logger.info(record + "X");
		}*/
		return list;
	}
	
	/**
	 * 	
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTransportDispCodeContainer getCodeContainer(String utfPayload) throws Exception{
		return this.codeMapper.getContainer(utfPayload);
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTransportDispSignatureContainer getSignContainer(String utfPayload) throws Exception{
		return this.signMapper.getContainer(utfPayload);
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTransportDispFrankaturContainer getFrankaturContainer(String utfPayload) throws Exception{
		return this.frankaturMapper.getContainer(utfPayload);
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTransportDispOppdragTypeContainer getOppdragTypeContainer(String utfPayload) throws Exception{
		return this.oppdragTypeMapper.getContainer(utfPayload);
	}
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTransportDispOppdragTypeParametersContainer getOppdragTypeTimeContainer(String utfPayload) throws Exception{
		return this.oppdragTypeMapper.getOppdragTypeParametersContainer(utfPayload);
	}
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTransportDispGebyrCodeContainer getGebyrCodeContainer(String utfPayload) throws Exception{
		return this.childWindowMapper.getGebyrContainer(utfPayload);
	}
	
}
