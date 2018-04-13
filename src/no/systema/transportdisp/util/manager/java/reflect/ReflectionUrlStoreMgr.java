	/**
 * 
 */
package no.systema.transportdisp.util.manager.java.reflect;

import java.util.*;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.Field;

import org.apache.log4j.Logger;

import no.systema.main.util.JsonDebugger;
import no.systema.transportdisp.url.store.TransportDispUrlDataStore;


/**
 * Url store - Transport Disponering
 *
 * This Manager is not instantiated by the Spring Container at start up. 
 * Instead, it is instantiated by a caller when needed.
 * 
 * It aims to provide a java reflection mechanism handler.
 *  
 * 
 * @author oscardelatorre
 * @date Sep 14, 2015
 * 
 * 
 */
public class ReflectionUrlStoreMgr {
	private static final Logger logger = Logger.getLogger(ReflectionUrlStoreMgr.class.getName());
	private static final JsonDebugger jsonDebugger = new JsonDebugger(500);
	
	
	/**
	 * The method deals with the hand-over of values between a source and a target.
	 * 
	 * @param targetRecord
	 * @param sourceRecord
	 */
	public String printProperties(String urlStoreClassPath, String outputChannel){
		String lineFeedCharacter = "\n";
		if(outputChannel.equalsIgnoreCase("html")){
			lineFeedCharacter = "<br/>";
		}
		StringBuffer retval = new StringBuffer();
		try{
			Field[] fields = Class.forName(urlStoreClassPath).getFields();
			int counter = 0;
			for (Field field : fields) {
				counter++;
	            //System.out.println("field name = " + field.getName()); //prints only 'sideLength' 
	            field.setAccessible(true);
	            if(field.isAccessible()){
	                 String value = (String) field.get(null);
	                 retval.append("[" + counter + "]" + " ["+ field.getName() + "] " + value + lineFeedCharacter); 
	            }
	        }
		}catch(Exception e){
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			logger.info(errors);
		}
		return retval.toString();
	}
}
	
	
	