<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ==================================== -->
<jsp:include page="/WEB-INF/views/headerTransportDispDangerousgoods.jsp" />
<!-- =====================end header =================================== -->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/jquery.calculator.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="resources/js/jquery-ui-timepicker-addon.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="resources/js/transportdispglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>			
	<SCRIPT type="text/javascript" src="resources/js/transportdisp_workflow_dangerousgoods.js?ver=${user.versionEspedsg}"></SCRIPT>
	
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
				<spring:message code="systema.transportdisp.title"/> - <spring:message code="systema.transportdisp.workflow.dangerousgoods.label"/>
				&nbsp;<font class="text16MediumBlue">Avd/Opd&nbsp;&nbsp;&nbsp;<b>${model.avd}</b>/<b>${model.opd}</b> &nbsp;&nbsp;&nbsp;Linjenr&nbsp;<b>${model.linNr}</b></font>	
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
	        			<tr height="5"><td></td></tr> 
						
						<tr>
							<td class="ownScrollableSubWindow" style="width:90%; height:15em;">
								<table width="100%" cellspacing="0" border="0" cellpadding="0">
									<tr class="tableHeaderField" height="20" valign="left">
										<td width="2%" class="tableHeaderFieldFirst" title="Update"></td>
										<td width="2%" align="center" class="tableHeaderField" style="font-size: 12px"><span title="ffunnr">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.unNr"/>&nbsp;</span></td>
							 			<td width="2%" align="right" class="tableHeaderField" style="font-size: 12px"><span title="ffembg">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.emg"/>&nbsp;</span></td>
							 			<td width="2%" align="right" class="tableHeaderField" style="font-size: 12px"><span title="ffindx">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.emg.index"/>&nbsp;</span></td>
							 			<td width="2%" align="right" class="tableHeaderField" style="font-size: 12px"><span title="ffantk">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.ant2"/>&nbsp;</span></td>
							 			<td width="2%" align="right" class="tableHeaderField" style="font-size: 12px"><span title="ffante">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.mengd"/>&nbsp;</span></td>
							 			<td width="2%" align="right" class="tableHeaderField" style="font-size: 12px"><span title="ffenh">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.unit"/>&nbsp;</span></td>
					        				<td width="2%" align="right" class="tableHeaderField" style="font-size: 12px"><span title="ffpoen/hepoen(Tot)">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.adr"/>&nbsp;</span></td>
						 				<td width="2%" align="center" class="tableHeaderField" style="font-size: 12px">&nbsp;<spring:message code="systema.transportdisp.delete" />&nbsp;</td>
					               </tr> 
					               
				 					  <c:forEach items="${Xmodel.list}" var="record" varStatus="counter">    
							               <tr class="tableRow" >
							                   
							               <td align="center" width="2%" class="tableCellFirst" >
							     				<a id="recordUpdate_${Xrecord.fskode}@${Xrecord.fssok}" href="#" onClick="getItemData(this);">
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
						               	   <td width="2%" align="right"></td>
						               	   <td width="2%" align="right"></td>
						               	   <td width="2%" align="right"></td>
						               	   <td width="2%" align="right"></td>
						               	   <td width="2%" align="right"></td>
						               	   <td width="2%" align="right"></td>
						               	   <td width="2%" align="right"></td>
						               	   
						               	   <%-- DELETE cell --%>							           
							               <td width="2%" class="tableCell" align="center">
							               	   <c:if test="${not empty Xrecord.fskode && not empty Xrecord.fssok}">
							                   		<a style="cursor:pointer;" id="avd_${Xrecord.fsavd}@opd_${Xrecord.fsopd}@kode_${Xrecord.fskode}@sok_${Xrecord.fssok}" onClick="doDeleteItemLine(this);" tabindex=-1 >
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
			<c:if test="${not empty Xmodel.errorMessage}">
				<tr>
				<td>
		           	<table class="tabThinBorderWhiteWithSideBorders" width="90%" align="left" border="0" cellspacing="0" cellpadding="0">
		           	<tr>
					<td valign="bottom" class="textError">					
			            <ul>
			            	<li >${Xmodel.errorMessage}</li>
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
					<input tabindex=-1  class="inputFormSubmitStd" type="button" name="newRecordButton" id="newRecordButton" value='<spring:message code="systema.transportdisp.createnew"/>'>
				</td>
			</tr>
			<tr height="5"><td class="text14" align="left" ></td></tr>
           	<tr>
	 			<td >
	 				<form action="transportdisp_workflow_dangerousgoods_edit.do" name="transportDispDangerousgoodsUpdateItemForm" id="transportDispDangerousgoodsUpdateItemForm" method="post">
				 	<%--Required key parameters from the Topic parent --%>
				 	<input type="hidden" name="applicationUser" id="applicationUser" value='${user.user}'>
				 	<input type="hidden" name="action" id="action" value='doUpdate'/>
					<input type="hidden" name="avd" id="avd" value='${model.avd}'>
					<input type="hidden" name="opd" id="opd" value='${model.opd}'>
					<input type="hidden" name="linNr" id="linNr" value='${model.linNr}'>
					<input type="hidden" name="isModeUpdate" id="isModeUpdate" value="${Xmodel.record.isModeUpdate}">
					
				 	<%-- <input type="hidden" name="numberOfItemLinesInTopic" id="numberOfItemLinesInTopic" value="${numberOfItemLinesInTopic}" /> --%>
				 	
				 	<%-- Topic ITEM CREATE --%>
	 				<table width="90%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
	 					
				 		<tr height="15">
				 			<td class="text14White" align="left" >
				 				<b>&nbsp;&nbsp;<spring:message code="systema.transportdisp.itemline"/>&nbsp;</b>
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
							 			<td class="text14" align="left">&nbsp;<font class="text14RedBold" >*</font><span title="todo">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.unNr"/></span></td>
								        <td class="text14" align="left">&nbsp;<span title="todo">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.emg"/></span></td>
								        <td class="text14" align="left">&nbsp;<span title="todo">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.emg.index"/></span></td>
								        <td class="text14" align="left">&nbsp;<span title="todo">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.ant2"/></span></td>
								        <td class="text14" align="left">&nbsp;<span title="todo">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.mengd"/></span></td>
								        <td class="text14" align="left">&nbsp;<span title="todo">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.unit"/></span></td>
								        
							        </tr>
							        <tr>
							        		<td class="text14" align="left" >
							        			<input type="text" class="inputTextMediumBlueMandatoryField" name="ffunnr" id="ffunnr" size="5" maxlength="4" value="${Xmodel.record.ffunnr}">
								        </td>
						        			<td class="text14" align="left" >
							        			<input type="text" class="inputTextMediumBlue" name="ffembg" id="ffembg" size="4" maxlength="3" value="${Xmodel.record.ffembg}">
								        </td>
								        <td class="text14" align="left" >
							        			<input type="text" class="inputTextMediumBlue" name="ffindx" id="ffindx" size="3" maxlength="2" value="${Xmodel.record.ffindx}">
								        </td>
								        <td class="text14" align="left" >
							        			<input type="text" class="inputTextMediumBlue" name="ffantk" id="ffantk" size="6" maxlength="5" value="${Xmodel.record.ffantk}">
								        </td>
								        <td class="text14" align="left" >
							        			<input type="text" class="inputTextMediumBlue" name="ffante" id="ffante" size="13" maxlength="12" value="${Xmodel.record.ffante}">
								        </td>
								        <td class="text14" align="left" >
							        			<select class="inputTextMediumBlue" onChange="TODOvalidateDangerousGoodsUnnrNewLine();" name="ffenh" id="ffenh">
					 							<option value="">?</option>
					 							<option value="KG" <c:if test="${Xmodel.record.fraktbrevRecord.ffenh == 'KG'}"> selected </c:if> >KG</option>
					 							<option value="LTR" <c:if test="${Xmodel.record.fraktbrevRecord.ffenh == 'LTR'}"> selected </c:if> >LTR</option>
					 							<option value="GR" <c:if test="${Xmodel.record.fraktbrevRecord.ffenh == 'GR'}"> selected </c:if> >GR</option>
					 							
											</select>
								        </td>
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

