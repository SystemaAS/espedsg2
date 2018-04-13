<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===============================-->
<jsp:include page="/WEB-INF/views/headerTransportDispFrisokvei.jsp" />
<!-- =====================end header ==============================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/jquery.calculator.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="resources/js/jquery-ui-timepicker-addon.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="resources/js/transportdispglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>			
	<SCRIPT type="text/javascript" src="resources/js/transportdisp_workflow_frisokvei.js?ver=${user.versionEspedsg}"></SCRIPT>
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
	
	</style>
	
	
	<%-- -------------------------------- --%>	
 	<%-- tab area container MASTER TOPIC  --%>
	<%-- -------------------------------- --%>
 	<table width="100%" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" border="0" cellspacing="0" cellpadding="0">
		<tr height="3"><td colspan="2">&nbsp;</td></tr>
		<tr>
			<td colspan="3" class="text16Bold">&nbsp;&nbsp;&nbsp;
				<img width="20px" height="20px" src="resources/images/find.png" border="0" alt="frisok">
				<spring:message code="systema.transportdisp.title"/> - <spring:message code="systema.transportdisp.workflow.frisokvei.label"/>
				&nbsp;<font class="text16MediumBlue">Avd/Opd&nbsp;&nbsp;&nbsp;<b>${model.container.avd}</b>/<b>${model.container.opd}</b></font>	
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
											<b>&nbsp;Antall varelinjer&nbsp;&nbsp;</b><font class="text12MediumBlue"><b>${Xmodel.container.totalNumberOfItemLines}</b></font>
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
										<td align="center" width="2%" class="tableHeaderFieldFirst" >&nbsp;<span title="todo">Oppd.&nbsp;</span></td>
										<td align="center" width="4%" class="tableHeaderField" >&nbsp;<span title="fskode">Kode&nbsp;</span></td>
										<td align="left" class="tableHeaderField" >&nbsp;<span title="fskode">Kodetekst&nbsp;</span></td>
										<td class="tableHeaderField" >&nbsp;<span title="fssok">Søketekst/verdi</span></td>  
										<td width="4%" class="tableHeaderField" >&nbsp;<span title="krav">Krav&nbsp;</span></td>
					                    <td class="tableHeaderField" width="4%" >&nbsp;<span title="fsdokk">Dok.kode&nbsp;</span></td>
					        			<td align="center" width="2%" class="tableHeaderField" >&nbsp;Slett&nbsp;</td>
					               </tr> 
					               
				 					  <c:forEach items="${model.list}" var="record" varStatus="counter">    
							               <c:choose>           
							                   <c:when test="${counter.count%2==0}">
							                   	   <%--highlight cost lines --%>	
							                       <tr class="tableRow" height="20" >
							                   </c:when>
							                   <c:otherwise> 
							                       <tr class="tableOddRow" height="20" >
							                   </c:otherwise>
							               </c:choose>
							               <td align="center" width="2%" class="tableCellFirst" >
							     				<a id="recordUpdate_${record.fskode}@${record.fssok}" href="#" onClick="getItemData(this);">
							     					<c:choose>
								     					<c:when test="${not empty record.fskode}">
						               						<img title="Update" style="vertical-align:bottom;" src="resources/images/update.gif" border="0" alt="update">&nbsp;
						               					</c:when>
						               					<c:otherwise>
						               						&nbsp;
						               					</c:otherwise>
					               					</c:choose>
					               				</a>
						               	   </td>
						               	   <td align="center" width="4%" class="tableCell" align="center"><b>${record.fskode}</b></td>
						               	   <td align="left" class="tableCell" align="center">&nbsp;${record.kfsotx}</td>
						               	   
							               <td class="tableCell" >&nbsp;${record.fssok}</td>
							               <td align="center" width="2%" class="tableCell" >
							               		<c:choose>
								     					<c:when test="${not empty record.krav && record.krav == '2'}">
						               						<span title="Obligatorisk"><img style="vertical-align:bottom;" src="resources/images/redFlag.png" width="15" height="17" border="0" alt="mandatory"></span>
						               					</c:when>
						               					<c:otherwise>
						               						<c:if test="${not empty record.krav}">
						               							<span title="${record.krav}"><img style="vertical-align: bottom;" src="resources/images/info3.png" width="12px" height="12px" border="0" alt="info"></span>
						               						</c:if>
						               					</c:otherwise>
					               					</c:choose>
							               	</td>
							               <td class="tableCell" width="4%" >&nbsp;${record.fsdokk}</td>
							               <%-- DELETE cell --%>							           
							               <td width="2%" class="tableCell" align="center">
							               	   <c:if test="${not empty record.fskode && not empty record.fssok}">
							                   		<a style="cursor:pointer;" id="avd_${record.fsavd}@opd_${record.fsopd}@kode_${record.fskode}@sok_${record.fssok}" onClick="doDeleteItemLine(this);" tabindex=-1 >
									               		<img valign="bottom" src="resources/images/delete.gif" border="0" alt="remove">
									               	</a>&nbsp;
									               	
								               	</c:if>
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
	 			<td class="text12" align="left" >
					<input tabindex=-1  class="inputFormSubmitStd" type="button" name="newRecordButton" id="newRecordButton" value='Lage ny'>
				</td>
			</tr>
			<tr height="5"><td class="text12" align="left" ></td></tr>
           	<tr>
	 			<td >
	 				<form action="transportdisp_workflow_frisokvei_edit.do" name="transportDispInvoiceUpdateItemForm" id="transportDispInvoiceUpdateItemForm" method="post">
				 	<%--Required key parameters from the Topic parent --%>
				 	<input type="hidden" name="applicationUser" id="applicationUser" value='${user.user}'>
				 	<input type="hidden" name="action" id="action" value='doUpdate'/>
					<input type="hidden" name="avd" id="avd" value='${model.container.avd}'>
					<input type="hidden" name="opd" id="opd" value='${model.container.opd}'>
					<input type="hidden" name="fskodeKey" id="fskodeKey" value='${model.record.fskodeKey}'>
					<input type="hidden" name="fssokKey" id="fssokKey" value='${model.record.fssokKey}'>
					<input type="hidden" name="isModeUpdate" id="isModeUpdate" value="${model.record.isModeUpdate}">
					
				 	<%-- <input type="hidden" name="numberOfItemLinesInTopic" id="numberOfItemLinesInTopic" value="${numberOfItemLinesInTopic}" /> --%>
				 	
				 	<%-- Topic ITEM CREATE --%>
	 				<table width="90%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
	 					
				 		<tr height="15">
				 			<td class="text12White" align="left" >
				 				<b>&nbsp;&nbsp;Varelinje&nbsp;</b>
 								<img onClick="showPop('updateInfo');" src="resources/images/update.gif" border="0" alt="edit">&nbsp;&nbsp;<font id="editLineNr"></font>
			 				</td>
		 				</tr>
	 				</table>
					<table width="90%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="12"><td class="text" align="left"></td></tr>
				 		<tr>
					 		<td>
						 		<table  class="tableBorderWithRoundCornersGray" width="90%" border="0" cellspacing="0" cellpadding="0">
						 			<tr height="5"><td class="text" align="left"></td></tr>
						 			<tr >
						 				
						            	<td class="text12" align="left">&nbsp;<font class="text14RedBold" >*</font><span title="fskode">&nbsp;Kode</span>
							            	<a tabindex=-1 id="fskodeIdLink">
	 											<img id="imgFrisokveiCodesSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
	 										</a>
							            </td>
							            <td class="text12" align="left">&nbsp;<font class="text14RedBold" >*</font><span title="fssok">&nbsp;Søketekst/Verdi</span>
							            	<a tabindex=-1 id="fsverdiIdLink">
	 											<img id="imgFrisokveiDocCodesGiltighetslistSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
	 										</a>
							            </td>
					            		<td class="text12" align="left">&nbsp;<span title="fsdokk">&nbsp;Dok.kode</span>
					            			<a tabindex=-1 id="fsdokkIdLink">
	 											<img id="imgFrisokveiDocCodesSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
	 										</a>
					            		</td>
					            		
							        </tr>
							        <tr>
						        		<td class="text12" align="left" >&nbsp;<input type="text" class="inputTextMediumBlueMandatoryField" name="fskode" id="fskode" size="4" maxlength="3" value="${model.record.fskode}"></td>
							            <td class="text12" align="left" >
						        			&nbsp;<input type="text" class="inputTextMediumBlueMandatoryField" name="fssok" id="fssok" size="36" maxlength="35" value="${model.record.fssok}">
						        		</td>
						        		<td class="text12" align="left" >&nbsp;<input type="text" class="inputTextMediumBlue" name="fsdokk" id="fsdokk" size="11" maxlength="10" value="${model.record.fsdokk}"></td>
							            
							        </tr>
							        
							        <tr height="8"><td class="text" align="left"></td></tr>
						        </table>
					        </td>
				        </tr>
					    <tr height="10"><td colspan="2" ></td></tr>
					    <tr>	
						    <td align="left" colspan="5">
								<input class="inputFormSubmit" type="submit" name="submit" id="submit" value='<spring:message code="systema.transportdisp.submit.save"/>'>
								<%-- 
								&nbsp;&nbsp;<input class="inputFormSubmitGray" type="button" name="updCancelButton" id="updCancelButton" value='<spring:message code="systema.transportdisp.cancel"/>'>
								--%>
							</td>
														        	
				        </tr>
        	        </table>
       	         	</form>
		        </td>
		       
		    </tr>
			<tr height="20"><td colspan="2" ></td></tr>
			<tr height="30"><td></td></tr>
			
		</table>
		</td>
		</tr>
	</table>    
	
	
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

