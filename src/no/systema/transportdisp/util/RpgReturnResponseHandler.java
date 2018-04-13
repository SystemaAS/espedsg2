/**
 * 
 */
package no.systema.transportdisp.util;

import java.util.*;


import org.apache.log4j.Logger;

/**
 * The class evaluates return codes from RPG operations.
 * 
 * @author oscardelatorre
 * @date Jun 2, 2014
 * 
 */
public class RpgReturnResponseHandler {
	private static final Logger logger = Logger.getLogger(RpgReturnResponseHandler.class.getName());
	
	private String errorMessage = null;
	public void setErrorMessage(String value){ this.errorMessage=value;  }
	public String getErrorMessage(){ return this.errorMessage;  }
	
	private String user = null;
	public void setUser(String value){ this.user=value;  }
	public String getUser(){ return this.user;  }
	
	private String tupro = null;
	public void setTupro(String value){ this.tupro=value;  }
	public String getTupro(){ return this.tupro;  }
	
	private String tuavd = null;
	public void setTuavd(String value){ this.tuavd=value;  }
	public String getTuavd(){ return this.tuavd;  }
	
	private String wstur = null;
	public void setWstur(String value){ this.wstur=value;  }
	public String getWstur(){ return this.wstur;  }
	
	private String heopd = null;
	public void setHeopd(String value){ this.heopd=value;  }
	public String getHeopd(){ return this.heopd;  }
	
	private String heavd = null;
	public void setHeavd(String value){ this.heavd=value;  }
	public String getHeavd(){ return this.heavd;  }
	
	private String newopd = null;
	public void setNewopd(String value){ this.newopd=value;  }
	public String getNewopd(){ return this.newopd;  }
	
	private String newavd = null;
	public void setNewavd(String value){ this.newavd=value;  }
	public String getNewavd(){ return this.newavd;  }
	
	private String newhesg = null;
	public void setNewhesg(String value){ this.newhesg=value;  }
	public String getNewhesg(){ return this.newhesg;  }
	
	/**
	 * Sets the error message code after an RPG-call been made by an HTML-POST request on a trip update
	 * 
	 * @param rpgRawReturnPayload
	 * @return
	 */
	public void evaluateRpgResponseOnTripUpdate(String rpgRawResponsePayload){
		
		if(rpgRawResponsePayload!=null){
			String tmp = rpgRawResponsePayload.replaceAll("\"", "");
			String tmp2 = tmp.replace("{", "");
			String cleanRawPayload = tmp2.replace("}", "");
			
			String[] record = cleanRawPayload.split(",");
			List <String>list = Arrays.asList(record);
			for(String field: list){
				//logger.info(field);
				String[] keyValuePair = field.split(":");
				if(keyValuePair[0]!=null){
					if(keyValuePair[0].trim().equalsIgnoreCase("errMsg")){
						if(keyValuePair.length>1){
							String errorCode = keyValuePair[1];
							if(errorCode!=null && !"".equals(errorCode.trim())){
								this.errorMessage = errorCode ;
								logger.info(this.errorMessage);
							}
						}
					}else if(keyValuePair[0].trim().equalsIgnoreCase("user")){
						this.user = keyValuePair[0] + ":" + keyValuePair[1] + ",";
					}else if(keyValuePair[0].trim().equalsIgnoreCase("tupro")){
						//this.tupro = keyValuePair[0] + ":" + keyValuePair[1]+ ",";
						this.tupro = keyValuePair[1].trim();
					}else if(keyValuePair[0].trim().equalsIgnoreCase("tuavd")){
						//this.tuavd = keyValuePair[0] + ":" + keyValuePair[1]+ ",";
						this.tuavd = keyValuePair[1].trim();
					}
				}
			}
		}
		
		
	}
	/**
	 * Return json when add-remove order from trip (WorkflowShippingPlanning)
	 * @param rpgRawResponsePayload
	 */
	public void evaluateRpgResponseOnAddRemoveOrder(String rpgRawResponsePayload){
		if(rpgRawResponsePayload!=null){
			String tmp = rpgRawResponsePayload.replaceAll("\"", "");
			String tmp2 = tmp.replace("{", "");
			String cleanRawPayload = tmp2.replace("}", "");
			//logger.info(cleanRawPayload);
			String[] record = cleanRawPayload.split(",");
			List <String>list = Arrays.asList(record);
			for(String field: list){
				//logger.info(field);
				String[] keyValuePair = field.split(":");
				if(keyValuePair[0]!=null){
					if(keyValuePair[0].trim().equalsIgnoreCase("error")){
						if(keyValuePair.length>1){
							String errorCode = keyValuePair[1];
							if(errorCode!=null && !"".equals(errorCode.trim())){
								this.errorMessage = errorCode ;
								//logger.info(this.errorMessage);
							}
						}
					}else if(keyValuePair[0].trim().equalsIgnoreCase("user")){
						this.user = keyValuePair[0] + ":" + keyValuePair[1] + ",";
					}else if(keyValuePair[0].trim().equalsIgnoreCase("wstur")){
						//this.tupro = keyValuePair[0] + ":" + keyValuePair[1]+ ",";
						this.wstur = keyValuePair[1].trim();
					}else if(keyValuePair[0].trim().equalsIgnoreCase("wsavd")){
						//this.tupro = keyValuePair[0] + ":" + keyValuePair[1]+ ",";
						this.heavd = keyValuePair[1].trim();
					}else if(keyValuePair[0].trim().equalsIgnoreCase("wsopd")){
						//this.tupro = keyValuePair[0] + ":" + keyValuePair[1]+ ",";
						this.heopd = keyValuePair[1].trim();
						//logger.info(this.heopd);
					}
				}
			}
		}
		
		
	}
	/**
	 * 
	 * @param rpgRawResponsePayload
	 */
	public void evaluateRpgResponseOnPermanentDeleteOrder(String rpgRawResponsePayload){
		if(rpgRawResponsePayload!=null){
			String tmp = rpgRawResponsePayload.replaceAll("\"", "");
			String tmp2 = tmp.replace("{", "");
			String cleanRawPayload = tmp2.replace("}", "");
			
			String[] record = cleanRawPayload.split(",");
			List <String>list = Arrays.asList(record);
			for(String field: list){
				//logger.info(field);
				String[] keyValuePair = field.split(":");
				if(keyValuePair[0]!=null){
					if(keyValuePair[0].trim().equalsIgnoreCase("errMsg")){
						if(keyValuePair.length>1){
							String errorCode = keyValuePair[1];
							if(errorCode!=null && !"".equals(errorCode.trim())){
								this.errorMessage = errorCode ;
								logger.info(this.errorMessage);
							}
						}
					}else if(keyValuePair[0].trim().equalsIgnoreCase("heavd")){
						this.heavd = keyValuePair[0] + ":" + keyValuePair[1] + ",";
					}else if(keyValuePair[0].trim().equalsIgnoreCase("heopd")){
						//this.tupro = keyValuePair[0] + ":" + keyValuePair[1]+ ",";
						this.heopd = keyValuePair[1].trim();
					}
				}
			}
		}
		
		
	}
	/**
	 * Copy
	 * @param rpgRawResponsePayload
	 */
	public void evaluateRpgResponseOnCopyOrder(String rpgRawResponsePayload){
		if(rpgRawResponsePayload!=null){
			String tmp = rpgRawResponsePayload.replaceAll("\"", "");
			String tmp2 = tmp.replace("{", "");
			String cleanRawPayload = tmp2.replace("}", "");
			
			String[] record = cleanRawPayload.split(",");
			List <String>list = Arrays.asList(record);
			for(String field: list){
				//logger.info(field);
				String[] keyValuePair = field.split(":");
				if(keyValuePair[0]!=null){
					if(keyValuePair[0].trim().equalsIgnoreCase("errMsg")){
						if(keyValuePair.length>1){
							String errorCode = keyValuePair[1];
							if(errorCode!=null && !"".equals(errorCode.trim())){
								this.errorMessage = errorCode ;
								logger.info(this.errorMessage);
							}
						}
					}else if(keyValuePair[0].trim().equalsIgnoreCase("heavd")){
						this.heavd = keyValuePair[0] + ":" + keyValuePair[1] + ",";
					}else if(keyValuePair[0].trim().equalsIgnoreCase("heopd")){
						this.heopd = keyValuePair[1].trim();
					}else if(keyValuePair[0].trim().equalsIgnoreCase("newavd")){
						this.newavd = keyValuePair[1].trim();
					}else if(keyValuePair[0].trim().equalsIgnoreCase("newopd")){
						this.newopd = keyValuePair[1].trim();
					}else if(keyValuePair[0].trim().equalsIgnoreCase("newhesg")){
						this.newhesg = keyValuePair[1].trim();
					}
				}
			}
		}
		
		
	}
	/**
	 * 
	 * @param rpgRawResponsePayload
	 */
	public void evaluateRpgResponseOnMoveOrder(String rpgRawResponsePayload){
		if(rpgRawResponsePayload!=null){
			String tmp = rpgRawResponsePayload.replaceAll("\"", "");
			String tmp2 = tmp.replace("{", "");
			String cleanRawPayload = tmp2.replace("}", "");
			
			String[] record = cleanRawPayload.split(",");
			List <String>list = Arrays.asList(record);
			for(String field: list){
				//logger.info(field);
				String[] keyValuePair = field.split(":");
				if(keyValuePair[0]!=null){
					if(keyValuePair[0].trim().equalsIgnoreCase("errMsg")){
						if(keyValuePair.length>1){
							String errorCode = keyValuePair[1];
							if(errorCode!=null && !"".equals(errorCode.trim())){
								this.errorMessage = errorCode ;
								logger.info(this.errorMessage);
							}
						}
					}else if(keyValuePair[0].trim().equalsIgnoreCase("heavd")){
						this.heavd = keyValuePair[0] + ":" + keyValuePair[1] + ",";
					}else if(keyValuePair[0].trim().equalsIgnoreCase("heopd")){
						this.heopd = keyValuePair[1].trim();
					}else if(keyValuePair[0].trim().equalsIgnoreCase("newavd")){
						this.newavd = keyValuePair[1].trim();
					}else if(keyValuePair[0].trim().equalsIgnoreCase("newopd")){
						this.newopd = keyValuePair[1].trim();
					}else if(keyValuePair[0].trim().equalsIgnoreCase("newhesg")){
						this.newhesg = keyValuePair[1].trim();
					}
				}
			}
		}
		
		
	}
	
	/**
	 * 
	 * @param rpgRawResponsePayload
	 */
	public void evaluateRpgResponseOnEditSpecificOrder(String rpgRawResponsePayload){
		if(rpgRawResponsePayload!=null){
			String tmp = rpgRawResponsePayload.replaceAll("\"", "");
			String tmp2 = tmp.replace("{", "");
			String cleanRawPayload = tmp2.replace("}", "");
			
			String[] record = cleanRawPayload.split(",");
			List <String>list = Arrays.asList(record);
			for(String field: list){
				//logger.info(field);
				String[] keyValuePair = field.split(":");
				if(keyValuePair[0]!=null){
					if(keyValuePair[0].trim().equalsIgnoreCase("errMsg")){
						if(keyValuePair.length>1){
							String errorCode = keyValuePair[1];
							if(errorCode!=null && !"".equals(errorCode.trim())){
								this.errorMessage = errorCode ;
								logger.info(this.errorMessage);
							}
						}
					}else if(keyValuePair[0].trim().equalsIgnoreCase("user")){
						this.user = keyValuePair[0] + ":" + keyValuePair[1] + ",";
					}else if(keyValuePair[0].trim().equalsIgnoreCase("heopd")){
						this.heopd = keyValuePair[1].trim();
					}else if(keyValuePair[0].trim().equalsIgnoreCase("heavd")){
						this.heavd = keyValuePair[1].trim();
					}
				}
			}
		}
		
		
	}
	
	/**
	 * 
	 * @param rpgRawResponsePayload
	 */
	public void evaluateRpgResponseOnValidateSpecificOrderLine(String rpgRawResponsePayload){
		if(rpgRawResponsePayload!=null){
			String tmp = rpgRawResponsePayload.replaceAll("\"", "");
			String tmp2 = tmp.replace("{", "");
			String cleanRawPayload = tmp2.replace("}", "");
			
			String[] record = cleanRawPayload.split(",");
			List <String>list = Arrays.asList(record);
			for(String field: list){
				//logger.info(field);
				String[] keyValuePair = field.split(":");
				if(keyValuePair[0]!=null){
					if(keyValuePair[0].trim().equalsIgnoreCase("errMsg")){
						if(keyValuePair.length>1){
							String errorCode = keyValuePair[1];
							if(errorCode!=null && !"".equals(errorCode.trim())){
								this.errorMessage = errorCode ;
								logger.info(this.errorMessage);
							}
						}
					}
				}
			}
		}
		
		
	}
	
	
	
	
}
