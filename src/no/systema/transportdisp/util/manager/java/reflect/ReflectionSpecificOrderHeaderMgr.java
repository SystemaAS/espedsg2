	/**
 * 
 */
package no.systema.transportdisp.util.manager.java.reflect;

import java.util.*;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

import org.apache.log4j.Logger;

import no.systema.main.util.JsonDebugger;
import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispWorkflowSpecificOrderFraktbrevRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispWorkflowSpecificOrderRecord;


/**
 * Work with Trips - Transport Disponering
 *
 * This Manager is not instantiated by the Spring Container at start up. 
 * Instead, it is instantiated by a caller when needed.
 * 
 * It aims to provide a java reflection mechanism handler.
 *  
 * 
 * @author oscardelatorre
 * @date Aug 25, 2015
 * 
 * 
 */
public class ReflectionSpecificOrderHeaderMgr {
	private static final Logger logger = Logger.getLogger(ReflectionSpecificOrderHeaderMgr.class.getName());
	private static final JsonDebugger jsonDebugger = new JsonDebugger(500);
	
	private List<JsonTransportDispWorkflowSpecificOrderFraktbrevRecord> targetFraktbrevListUpdated = new ArrayList<JsonTransportDispWorkflowSpecificOrderFraktbrevRecord>();
	public List<JsonTransportDispWorkflowSpecificOrderFraktbrevRecord> getTargetFraktbrevListUpdated() { return this.targetFraktbrevListUpdated;}
	
	
	
	/**
	 * The method deals with the hand-over of values between a source and a target.
	 * 
	 * @param targetRecord
	 * @param sourceRecord
	 */
	public void updateOriginalAttributesOnTargetRecord(JsonTransportDispWorkflowSpecificOrderRecord targetRecord, JsonTransportDispWorkflowSpecificOrderRecord sourceRecord){
		try{
			Class<?> sourceRecordClazz = sourceRecord.getClass();
			Method  theMethod = null;
			Class<?> returnType = null;
			for(Method method : sourceRecordClazz.getDeclaredMethods()){
				//only getters
				String getter = method.getName();
				if(getter.startsWith("get")){
					theMethod= sourceRecordClazz.getDeclaredMethod(method.getName());
					returnType = theMethod.getReturnType();
					if(returnType.equals(String.class)){
						String value = (String)theMethod.invoke(sourceRecord);
						//we are interested of those that are not null
						if(value!=null && !"".equals(value)){
							logger.info(jsonDebugger.debugJsonPayloadWithLog4J(method.getName() + " Value:" + value));
							String setter = getter.replace("get", "set");
							//now update target record
							this.updateTargetRecordProperty(targetRecord, setter, value);
							
						}
					}
				}
				
			}
			
		}catch(Exception e){
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			logger.info(errors);
		}
	}
	
	/**
	 * Updates the values in target record (when applicable)
	 * @param targetRecord
	 * @param setterMethod
	 * @param value
	 * @throws Exception
	 */
	private void updateTargetRecordProperty(JsonTransportDispWorkflowSpecificOrderRecord targetRecord, String setterMethod, String value) throws Exception{
		//[1]init parameters and correct method
		try{
			Class[] parameter=new Class[1];
	        parameter[0]= String.class;
	        Class<?> targetRecordClazz = targetRecord.getClass();
	      
	        Method thisMethod = targetRecordClazz.getMethod(setterMethod, parameter);
	        //[2]call the method with the new value
	        thisMethod.invoke(targetRecord, value);
		}catch (Exception e){
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			logger.info(errors);
			
		}
	}
	/**
	 * Updates original values with the ones caught on back-end validation...
	 * 
	 * @param targetRecord
	 * @param sourceList
	 */
	public void updateOriginalAttributesOnTargetFraktbrevLines(JsonTransportDispWorkflowSpecificOrderRecord targetRecord, List<JsonTransportDispWorkflowSpecificOrderFraktbrevRecord> sourceList){
		List<JsonTransportDispWorkflowSpecificOrderFraktbrevRecord> targetList = new ArrayList<JsonTransportDispWorkflowSpecificOrderFraktbrevRecord>();
		if(targetRecord!=null){ 
			targetList = targetRecord.getFraktbrevList(); 
			//init
			this.targetFraktbrevListUpdated = new ArrayList<JsonTransportDispWorkflowSpecificOrderFraktbrevRecord>();
		}
		//logger.info("*****SOURCE LIST:" + sourceList);
		try{
			Class<?> sourceRecordClazz = null;
			Method  theMethod = null;
			Class<?> returnType = null;
			//A convertion from List to map must be done in order not to iterate a list within another list... could risk double iterations...
			Map<String, JsonTransportDispWorkflowSpecificOrderFraktbrevRecord>mapSourceList = new HashMap();
			this.convertSourceListToMap(mapSourceList, sourceList);
			//Now iterate through target record and update if needed
			for(JsonTransportDispWorkflowSpecificOrderFraktbrevRecord record : targetList){
				String fvlinrId = record.getFvlinr();
				JsonTransportDispWorkflowSpecificOrderFraktbrevRecord sourceRecord = (JsonTransportDispWorkflowSpecificOrderFraktbrevRecord)mapSourceList.get(fvlinrId);
				if(sourceRecord!=null){
					if(sourceRecord.getFvlinr().equals(record.getFvlinr())){
						sourceRecordClazz = sourceRecord.getClass();
						theMethod = null;
						returnType = null;
						for(Method method : sourceRecordClazz.getDeclaredMethods()){
							//only getters
							String getter = method.getName();
							if(getter.startsWith("get")){
								theMethod= sourceRecordClazz.getDeclaredMethod(method.getName());
								returnType = theMethod.getReturnType();
								if(returnType.equals(String.class)){
									String value = (String)theMethod.invoke(sourceRecord);
									//we are interested of those that are not null
									if(value!=null && !"".equals(value)){
										logger.info(jsonDebugger.debugJsonPayloadWithLog4J(method.getName() + " Value:" + value));
										//System.out.println(method.getName() + " Value:" + value);
										String setter = getter.replace("get", "set");
										//now update target record
										this.updateTargetRecordProperty(record, setter, value);
									}
								}
							}
						}
					}
				}
				
				this.targetFraktbrevListUpdated.add(record);
			}
		}catch(Exception e){
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			logger.info(errors);
		}
	}
	/**
	 * 
	 * @param sourceListMap
	 * @param sourceList
	 */
	private void convertSourceListToMap(Map<String, JsonTransportDispWorkflowSpecificOrderFraktbrevRecord> sourceListMap ,List<JsonTransportDispWorkflowSpecificOrderFraktbrevRecord>sourceList){
		for(JsonTransportDispWorkflowSpecificOrderFraktbrevRecord sourceRecord: sourceList){
			if(sourceRecord.getFvlinr()!=null && !"".equals(sourceRecord.getFvlinr())){
				sourceListMap.put(sourceRecord.getFvlinr(), sourceRecord);
			}
		}
	}
	/**
	 * 
	 * @param targetRecord
	 * @param setterMethod
	 * @param value
	 * @throws Exception
	 */
	private void updateTargetRecordProperty(JsonTransportDispWorkflowSpecificOrderFraktbrevRecord targetRecord, String setterMethod, String value) throws Exception{
		//[1]init parameters and correct method
		Class[] parameter=new Class[1];
        parameter[0]= String.class;
        Class<?> targetRecordClazz = targetRecord.getClass();
        Method thisMethod = targetRecordClazz.getMethod(setterMethod, parameter);
        //[2]call the method with the new value
        thisMethod.invoke(targetRecord, value);
		
	}
	
}
