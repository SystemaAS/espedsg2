<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===============================-->
<jsp:include page="/WEB-INF/views/headerTransportDispFerjeoverfarter.jsp" />
<!-- =====================end header ==============================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/jquery.calculator.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="resources/js/jquery-ui-timepicker-addon.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="resources/js/transportdispglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>			
	<SCRIPT type="text/javascript" src="resources/js/transportdisp_workflow_ferjeoverfarter.js?ver=${user.versionEspedsg}"></SCRIPT>
	<%-- for dialog popup --%>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	
	<style type = "text/css">
	.ui-dialog{font-size:10pt;}
	.ui-datepicker { font-size:9pt;}
	.ui-timepicker-div .ui-widget-header { margin-bottom: 8px; }
	.ui-timepicker-div dl { text-align: left; }
	.ui-timepicker-div dl dt { float: left; clear:left; padding: 0 0 0 5px; }
	.ui-timepicker-div dl dd { margin: 0 10px 10px 40%; }
	.ui-timepicker-div td { font-size: 90%; }
	.ui-tpicker-grid-label { background: none; border: none; margin: 0; padding: 0; }
	
	.ui-timepicker-rtl{ direction: rtl; }
	.ui-timepicker-rtl dl { text-align: right; padding: 0 5px 0 0; }
	.ui-timepicker-rtl dl dt{ float: right; clear: right; }
	.ui-timepicker-rtl dl dd { margin: 0 40% 10px 10px; }
	
	/* this line will align the datatable search field in the left */
	.dataTables_wrapper .localFilter .dataTables_filter{float:left}
	</style>
	
	
	<%-- -------------------------------- --%>	
 	<%-- tab area container MASTER TOPIC  --%>
	<%-- -------------------------------- --%>
 	<table width="100%" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" border="0" cellspacing="0" cellpadding="0">
		<tr height="3"><td colspan="2">&nbsp;</td></tr>
		<tr>
			<td colspan="3" class="text16Bold">&nbsp;&nbsp;&nbsp;
				<img width="20px" height="20px" src="resources/images/find.png" border="0" alt="frisok">
				<spring:message code="systema.transportdisp.title"/> - Ferjeoverfarter
				&nbsp;<font class="text16MediumBlue">Avd/Tur&nbsp;&nbsp;&nbsp;<b>${model.avd}</b>/<b>${model.tur}</b></font>	
			</td>
		</tr>
		<tr height="5"><td colspan="2">&nbsp;</td></tr>
			
			
		<tr>
		<td >
		<table border="0" width="99%" align="center">
			
           	<%-- ---------------------- --%>
           	<%-- LIST of existent ITEMs --%>
           	<%-- ---------------------- --%>
           	<tr>
				<td>
					<table width="100%" cellspacing="0" border="0" cellpadding="0">
	    				<%-- separator --%>
	        			<tr height="10"><td></td></tr> 
						<tr >
							<td>
							<form name="createNewItemLine" id="createNewItemLine" method="post" action="TODO.do">
								<table width="80%" cellspacing="0" border="0" cellpadding="0">
									<tr>
										<td class="text12">
											<b>&nbsp;Antall reg.</b>
					            		</td>
									</tr>
									<tr height="2"><td></td></tr>
								</table>
							</form>
							</td>
						</tr> 
						<tr>
							<td class="ownScrollableSubWindow" style="width:90%; height:15em;">
								<table width="100%" cellspacing="0" border="0" cellpadding="0">
									<tr class="tableHeaderField" height="20" valign="left">
										<th width="2%" align="center" class="tableHeaderFieldFirst" ></th>
										<th width="2%" align="center" class="tableHeaderField" ><span title="fedat2">Dato</span></th>
										<th width="2%" class="tableHeaderField" ><span title="fetime">Kl.</span></th>
										<th width="2%" class="tableHeaderField" >&nbsp;<span title="fefrom">Fra</span></th>
										<th width="2%" class="tableHeaderField" >&nbsp;<span title="feto">Til</span></th>  
										<th width="2%" class="tableHeaderField" >&nbsp;<span title="feleng">Lengd</span></th>
					                    <th width="2%" class="tableHeaderField" >&nbsp;<span title="levNavn">Selskap</span></th>
					        			<th width="2%" align="right" class="tableHeaderField" ><span title="fepri1">Kostpris</span></th>
					        			<th width="2%" align="center" class="tableHeaderField" ><span title="fecurr">Valuta</span></th>
					        			<th width="2%" align="center" class="tableHeaderField" ><span title="febiln">Bilnr.</span></th>
					        			<th width="2%" align="center" class="tableHeaderField" >Slett</th>
					               </tr> 
				 					  <c:forEach items="${model.list}" var="record" varStatus="counter">    
							               <tr class="tableRow" height="20" >
							                   
							               <td width="2%" align="center" class="tableCellFirst" >
							     				<a id="recordUpdate_${XXrecord.fssok}" href="#" onClick="getItemData(this);">
							     					<c:choose>
								     					<c:when test="${not empty Xrecord.fskode}">
						               						<img title="Update" style="vertical-align:bottom;" src="resources/images/update.gif" border="0" alt="update">&nbsp;
						               					</c:when>
						               					<c:otherwise>
						               						&nbsp;
						               					</c:otherwise>
					               					</c:choose>
					               				</a>
						               	   </td>
						               	   <td width="2%" align="center" class="tableCell" align="center">${record.fedat2}</td>
						               	   <td width="2%" class="tableCell" >&nbsp;${record.fetime}</td>
						               	   <td width="2%" class="tableCell" >&nbsp;${record.fefrom}</td>
							               <td width="2%" class="tableCell" >&nbsp;${record.feto}</td>
							               <td width="2%" class="tableCell" >&nbsp;${record.feleng}</td>
							               <td width="2%" class="tableCell" >&nbsp;${record.levNavn}</td>
							               
							               <td width="2%" align="right" class="tableCell" >&nbsp;${record.fepri1}</td>
							               <td width="2%" align="center" class="tableCell" >&nbsp;${record.fecurr}</td>
							               <td width="2%" align="center" class="tableCell" >&nbsp;${record.febiln}</td>
							               
							               <%-- DELETE cell	--%>						           
							               <td width="2%" class="tableCell" align="center">
							               	   <a style="cursor:pointer;" id="avd_${Xrecord.fsavd}@opd_${Xrecord.fsopd}@kode_${Xrecord.fskode}@sok_${Xrecord.fssok}" onClick="doDeleteItemLine(this);" tabindex=-1 >
													<img valign="bottom" src="resources/images/delete.gif" border="0" alt="remove">
									           </a>&nbsp;							               
					               		  </td> 
							            </tr>
								        <%-- this param is used ONLY in this JSP 
								        <c:set var="totalNumberOfItemLines" value="${counter.count}" scope="request" />
								        --%> 
								        <%-- this param is used throughout the Controller --%>
								        <c:set var="numberOfItemLinesInTopic" value="${Xrecord.svln}" scope="request" /> 
								        </c:forEach>
						           	 
						        </table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr height="5"><td></td></tr>
			<%-- Validation errors --%>
			<spring:hasBindErrors name="record"> <%-- name must equal the command object name in the Controller --%>
			<tr>
				<td>
	            	<table width="100%" align="left" border="0" cellspacing="0" cellpadding="0">
	            	<tr>
					<td class="textError">					
			            <ul>
			            <c:forEach var="error" items="${errors.allErrors}">
			                <li >
			                	<spring:message code="${error.code}" text="${error.defaultMessage}"/>
			                </li>
			            </c:forEach>
			            </ul>
					</td>
					</tr>
					</table>
				</td>
			</tr>
			</spring:hasBindErrors>
			
			<%-- -------------------------- --%>
			<%-- Validation errors on model --%>
			<%-- -------------------------- --%>
			<c:if test="${not empty model.errorMessage}">
				<tr>
				<td>
		           	<table class="tabThinBorderWhiteWithSideBorders" width="90%" align="left" border="0" cellspacing="0" cellpadding="0">
		           	<tr>
					<td valign="bottom" class="textError">					
			            <ul>
			            	<li >${model.errorMessage}</li>
			            </ul>
					</td>
					</tr>
					</table>
				</td>
				</tr>		
			</c:if>
			<%-- ------------------------------------------------- --%>
           	<%-- DETAIL Section - Create Item line PRIMARY SECTION --%>
           	<%-- ------------------------------------------------- --%>
           	<tr>
	 			<td class="text14" align="left" >
					<input tabindex=-1  class="inputFormSubmitStd" type="button" name="newRecordButton" id="newRecordButton" value='Lage ny'>
				</td>
			</tr>
			<tr height="5"><td class="text14" align="left" ></td></tr>
			

           	<tr>
	 			<td >
	 				<%-- Topic ITEM CREATE --%>
	 				<table style="width:100%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
	 					
				 		<tr height="15">
				 			<td class="text14White" align="left" >
				 				<b>&nbsp;&nbsp;Linje&nbsp;</b>
 								<img onClick="showPop('updateInfo');" src="resources/images/update.gif" border="0" alt="edit">&nbsp;&nbsp;<font id="editLineNr"></font>
			 				</td>
		 				</tr>
	 				</table>
	 			</td>
 			</tr>
	 		<tr>			
				<td>
					<form action="TODOtransportdisp_workflow_ferjeoverfarter_edit.do" name="transportDispFerjerUpdateItemForm" id="transportDispFerjerUpdateItemForm" method="post">
				 	<%--Required key parameters from the Topic parent --%>
				 	<input type="hidden" name="applicationUser" id="applicationUser" value='${user.user}'>
				 	<input type="hidden" name="action" id="action" value='doUpdate'/>
					<input type="hidden" name="feavd" id="feavd" value='${model.avd}'>
					<input type="hidden" name="fetur" id="fetur" value='${model.tur}'>
					<input type="hidden" name="isModeUpdate" id="isModeUpdate" value="${Xmodel.record.isModeUpdate}">
					
					<table style="width:100%"  align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="12"><td class="text" align="left"></td></tr>
				 		<tr>
					 		<td>
						 		<table  width="99%" class="tableBorderWithRoundCornersGray" border="0" cellspacing="0" cellpadding="0">
						 			<tr height="5"><td class="text" align="left"></td></tr>
						 			<tr >
						 				<td class="text14" align="left">&nbsp;<font class="text14RedBold" >*</font><span title="fedat2">&nbsp;Avg.dato</span></td>
							            <td class="text14" align="left">&nbsp;<font class="text14RedBold" >*</font><span title="wsfajn">&nbsp;Fakt.kode</span></td>
							        </tr>
							        <tr>
						        		<td class="text14" align="left" >&nbsp;<input type="text" class="inputTextMediumBlueMandatoryField" name="fedat2" id="fedat2" size="9" maxlength="8" value="${Xmodel.record.fedat2}"></td>
							            <td class="text14" align="left" >&nbsp;<input type="text" class="inputTextMediumBlueMandatoryField" name="wsfajn" id="wsfajn" size="1" maxlength="1" value="${Xmodel.record.wsfajn}"></td>
						        	</tr>
						        	
							        <tr>
							        	<td class="text14" align="left">&nbsp;<span title="fetime">&nbsp;Søk</span></td>    
					            		<td class="text14" align="left">&nbsp;<span title="fetime">&nbsp;Kl.</span></td>
					            		<td class="text14" align="left">&nbsp;<span title="fefrom">&nbsp;Fra</span></td>
					            		<td class="text14" align="left">&nbsp;<span title="feto">&nbsp;Til</span></td>
					            		<td class="text14" align="left">&nbsp;<span title="feleng">&nbsp;Lengd</span></td>
					            		<td class="text14" align="left">&nbsp;<span title="fefirm">&nbsp;Selskap</span></td>
					            		<td class="text14" align="left">&nbsp;<span title="fepri1">&nbsp;Kostpris</span></td>
					            		<td class="text14" align="left">&nbsp;<span title="fepri2">&nbsp;Pris</span></td>
					            		<td class="text14" align="left">&nbsp;<span title="fecurr">&nbsp;Valuta</span></td>
							        </tr>
							        <tr height="8"><td class="text" align="left"></td></tr>
							        <tr>
							        	<td><img id="imgFerjeSoklista" src="resources/images/find.png" border="0" alt="ferjeoverf." width="16px" height="16px" ></td>	
						        		<td class="text14" align="left" >&nbsp;<input readonly type="text" class="inputTextReadOnly" name="fetime" id="fetime" size="4" value="${Xmodel.record.fetime}"></td>
						        		<td class="text14" align="left" >&nbsp;<input readonly type="text" class="inputTextReadOnly" name="fefrom" id="fefrom" size="5" value="${Xmodel.record.fefrom}"></td>
						        		<td class="text14" align="left" >&nbsp;<input readonly type="text" class="inputTextReadOnly" name="feto" id="feto" size="5" value="${Xmodel.record.feto}"></td>
						        		<td class="text14" align="left" >&nbsp;<input readonly type="text" class="inputTextReadOnly" name="feleng" id="feleng" size="3" value="${Xmodel.record.feleng}"></td>
						        		<td class="text14" align="left" >&nbsp;<input readonly type="text" class="inputTextReadOnly" name="fefirm" id="fefirm" size="8" value="${Xmodel.record.fefirm}"></td>
						        		<td class="text14" align="left" >&nbsp;<input readonly type="text" class="inputTextReadOnly" name="fepri1" id="fepri1" size="15" value="${Xmodel.record.fepri1}"></td>
						        		<td class="text14" align="left" >&nbsp;<input readonly type="text" class="inputTextReadOnly" name="fepri2" id="fepri2" size="15" value="${Xmodel.record.fepri2}"></td>
						        		<td class="text14" align="left" >&nbsp;<input readonly type="text" class="inputTextReadOnly" name="fecurr" id="fecurr" size="3" value="${Xmodel.record.fecurr}"></td>
							        </tr>
							        
							        <tr height="8"><td class="text" align="left"></td></tr>
						        </table>
					        </td>
				        </tr>
					    <tr height="10"><td colspan="2" ></td></tr>
					    <tr>	
						    <td align="left" colspan="5">
								<input class="inputFormSubmit" type="submit" name="submit" id="submit" value='<spring:message code="systema.transportdisp.submit.save"/>'>
								
							</td>
														        	
				        </tr>
        	        </table>
       	         	</form>
		        </td>
		       
		    </tr>
			<tr height="20"><td colspan="2" ></td></tr>
					<tr>
				
					<td>
					<table id="sokList" class="display compact cell-border" width="100%" >
						<thead>
						<tr class="tableHeaderField" height="20" valign="left">
							<th width="2%" align="center" class="tableHeaderFieldFirst" ></th>
							<th width="2%" align="center" class="tableHeaderField" ><span title="fedat2">Dato</span></th>
							<th width="2%" class="tableHeaderField" ><span title="fetime">Kl.</span></th>
							<th width="2%" class="tableHeaderField" >&nbsp;<span title="fefrom">Fra</span></th>
							<th width="2%" class="tableHeaderField" >&nbsp;<span title="feto">Til</span></th>  
							<th width="2%" class="tableHeaderField" >&nbsp;<span title="feleng">Lengd</span></th>
		                    <th width="2%" class="tableHeaderField" >&nbsp;<span title="levNavn">Selskap</span></th>
		        			<th width="2%" align="right" class="tableHeaderField" ><span title="fepri1">Kostpris</span></th>
		        			<th width="2%" align="center" class="tableHeaderField" ><span title="fecurr">Valuta</span></th>
		        			<th width="2%" align="center" class="tableHeaderField" ><span title="febiln">Bilnr.</span></th>
		               </tr> 
		               </thead>
		               <tbody>
	 					  <c:forEach items="${model.list}" var="record" varStatus="counter">    
				               <tr class="tableRow" height="20" >
				                   
				               <td width="2%" align="center" class="tableCell" style="background-color:ivory" >
				     				<a id="recordUpdate_${XXrecord.fssok}" href="#" onClick="getItemData(this);">
				     					<c:choose>
					     					<c:when test="${not empty Xrecord.fskode}">
			               						<img title="Update" style="vertical-align:bottom;" src="resources/images/update.gif" border="0" alt="update">&nbsp;
			               					</c:when>
			               					<c:otherwise>
			               						&nbsp;
			               					</c:otherwise>
		               					</c:choose>
		               				</a>
			               	   </td>
			               	   <td width="2%" align="center" class="tableCell" style="background-color:ivory">${record.fedat2}</td>
			               	   <td width="2%" class="tableCell" style="background-color:ivory">&nbsp;${record.fetime}</td>
			               	   <td width="2%" class="tableCell" style="background-color:ivory">&nbsp;${record.fefrom}</td>
				               <td width="2%" class="tableCell" style="background-color:ivory">&nbsp;${record.feto}</td>
				               <td width="2%" class="tableCell" style="background-color:ivory">&nbsp;${record.feleng}</td>
				               <td width="2%" class="tableCell" style="background-color:ivory">&nbsp;${record.levNavn}</td>
				               
				               <td width="2%" align="right" class="tableCell" style="background-color:ivory">&nbsp;${record.fepri1}</td>
				               <td width="2%" align="center" class="tableCell" style="background-color:ivory">&nbsp;${record.fecurr}</td>
				               <td width="2%" align="center" class="tableCell" style="background-color:ivory">&nbsp;${record.febiln}</td>
				               
				              
				            </tr>
					        <%-- this param is used ONLY in this JSP 
					        <c:set var="totalNumberOfItemLines" value="${counter.count}" scope="request" />
					        --%> 
					        <%-- this param is used throughout the Controller --%>
					        <c:set var="numberOfItemLinesInTopic" value="${Xrecord.svln}" scope="request" /> 
					        </c:forEach>
			           	 </tbody>
			        </table>
					</td>
					</tr>
				
			<tr height="10"><td></td></tr>
			
		</table>
		</td>
		</tr>

	</table>    
	
	
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

