<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTransportDispBudget.jsp" />
<!-- =====================end header ==========================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/jquery.calculator.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="resources/js/jquery-ui-timepicker-addon.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="resources/js/transportdispglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>			
	<SCRIPT type="text/javascript" src="resources/js/transportdisp_workflow_budget.js?ver=${user.versionEspedsg}"></SCRIPT>
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
				<img width="30px" height="30px" src="resources/images/budget.png" border="0" alt="budget">
				<spring:message code="systema.transportdisp.title"/> - <spring:message code="systema.transportdisp.workflow.budget.label"/>
				<c:choose>
				<c:when test="${not empty model.parentTrip}">
					<c:if test="${empty model.container.opd}">
						&nbsp;-<font class="text16MediumBlue">Turnr&nbsp;${model.parentTrip}</font>
					</c:if>
				</c:when>
				<c:otherwise>
					&nbsp;<font class="text16MediumBlue">Avd/Opd&nbsp;&nbsp;&nbsp;${model.container.avd}/${model.container.opd}</font>	
				</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr height="5"><td colspan="2">&nbsp;</td></tr>
			
			
		<tr>
		<td >
		<table border="0" width="99%" align="center">
			<%--	
			<tr>
	 			<td >		
	 				<%-- MASTER Topic header 
	 				<table width="80%" align="left" class="formFrameHeaderTransparent" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text12MediumBlue">
				 				&nbsp;Avd./Oppd.nr:&nbsp;<b>${XrecordOrderTransportDisp.heavd}/${XrecordOrderTransportDisp.heopd}</b>
				 				&nbsp;&nbsp;Tur.:&nbsp;<b>${Xmodel.parentTrip}</b>
				 				<%--&nbsp;&nbsp;Prod.kode:&nbsp;<b>${Xmodel.todo}</b> 
				 				&nbsp;&nbsp;Fvekt:&nbsp;<b>${XrecordOrderTransportDisp.hefbv}</b>
			 				</td>
		 				</tr>
		 				
	 				</table>
					<%-- MASTER Topic information [it is passed through a session object: XX] 
				 	<table height="50" width="99%" align="left" class="formFrameTitaniumWhite" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="2"><td class="text" align="left" colspan="2"></td></tr>
				 		<tr>
					 		<td width="45%">
						 		<table width="100%" border="0" cellspacing="1" cellpadding="1">
						 			<tr>
							            <td class="text11Bold" align="left" >Selger</td>
							            <td class="text11" align="left">Fakturapart&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${XrecordOrderTransportDisp.heknsf}&nbsp;${XrecordOrderTransportDisp.henasf}</td>
							        </tr>
							 		<tr>
										<td class="text11" align="left" >&nbsp;&nbsp;</td>
							            <td class="text11" align="left">Avt.kunr.&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${XrecordOrderTransportDisp.avtknrs}&nbsp;${XrecordOrderTransportDisp.avtknavs}</td>
							        </tr>
							        <tr>
							        	<td class="text11Bold" align="left" >Kjøpers</td>
							            <td class="text11" align="left">Fakturapart&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${XrecordOrderTransportDisp.heknkf}&nbsp;${XrecordOrderTransportDisp.henakf}</td>
							        </tr>
							       <tr>
										<td class="text11" align="left" >&nbsp;&nbsp;</td>
							            <td class="text11" align="left">Avt.kunr&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${XrecordOrderTransportDisp.avtknrk}&nbsp;${XrecordOrderTransportDisp.avtknavk}</td>
							        </tr>
			        	        </table>
					        </td>
					        <td width="27%">
						 		<table width="100%" border="0" cellspacing="1" cellpadding="0">
							 		<tr>
							            <td width="30%" class="text11" align="left">Sign.&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${XrecordOrderTransportDisp.hesg}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text11" align="left">Dato/Per&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${XrecordTopicTODO.sinak}</td>
							        </tr>
									<tr>
							            <td width="30%" class="text11" align="left">Godsnr.&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${XrecordTopicTODO.siadk1}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text11" align="left">Oppd.type / Frank.&nbsp;&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${XrecordOrderTransportDisp.heot} / ${XrecordOrderTransportDisp.hefr}</td>
							        </tr>
							        
			        	        </table>
					        </td>
					        <td width="27%">
						 		<table width="100%" border="0" cellspacing="1" cellpadding="0">
						 			<tr>
							            <td width="30%" class="text11" align="left">Avs.ref.&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${XrecordOrderTransportDisp.herfa}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text11" align="left">Mott.ref&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${XrecordOrderTransportDisp.herfk}</td>
							        </tr>
							 		<tr>
							            <td width="30%" class="text11" align="left">Vekt/M3/Lm&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${XrecordOrderTransportDisp.hevkt}/${XrecordOrderTransportDisp.hem3}/${XrecordOrderTransportDisp.helm}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text11" align="left">Tilleggsg&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${XrecordOrderTransportDisp.hestn1}${XrecordOrderTransportDisp.hestn2}${XrecordOrderTransportDisp.hestn3}${XrecordOrderTransportDisp.hestn4}${XrecordOrderTransportDisp.hestn5}
							     			&nbsp;&nbsp;&nbsp;<c:if test="${XrecordOrderTransportDisp.hestn6 == 'P'}">Protected</c:if>
							     		</td>      	
							        </tr>
							        
			        	        </table>
					        </td>
				        </tr>
					</table>          
            	</td>
           	</tr> 
           	 --%>
           	 
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
											<%--
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<img onMouseOver="showPop('lineTypes_info');" onMouseOut="hidePop('lineTypes_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
											Type
							 				<select class="text11" id="linType" name="linType">
						           				<option value="A" <c:if test="${Xmodel.container.type == 'A'}"> selected </c:if> >Alle</option>
						           				<option value="I" <c:if test="${Xmodel.container.type == 'I'}"> selected </c:if> >Inntektslinj.</option>
						           				<option value="O" <c:if test="${Xmodel.container.type == 'O'}"> selected </c:if> >Åpne inntektslinj.</option>
							           		</select>
							           		<div class="text11" style="position: relative;" align="left">
											<span style="position:absolute; left:0px; top:0px;" id="lineTypes_info" class="popupWithInputText"  >
												<font class="text11">
							           			<b>Linjetyper</b>
							           			<div>
							           				<ul>
								           				<li><b>Alle</b>=Vis ALLE linjer (vis inntektslinjer uavhengig av status OG også kostnadslinjer...)</li>
								           				<li><b>Inntektslinj</b>=Vis kun Inntektslinjer=kun linjer for utgående faktura vises (ikke kostnader / ikke slettemerkedede)</li>
		    											<li><b>Åpne inntektslinj.</b>= Open = Vis kun ÅPNE inntekstlinjer = Linjer som ennå ikke er fakturert / kan endres.. </li>
	    											</ul>
	    										</div>	 
						           			</font>
											</span>
											</div>
											 --%>
					            		</td>
									</tr>
									<tr height="2"><td></td></tr>
								</table>
							</form>
							</td>
						</tr> 
						<tr>
							<td class="ownScrollableSubWindow" style="width:99%; height:15em;">
								<table width="100%" cellspacing="0" border="0" cellpadding="0">
									<tr class="tableHeaderField" height="20" valign="left">
										<td align="center" class="tableHeaderFieldFirst" >&nbsp;<span title="bukdm">M&nbsp;</span></td>
									    <td align="center" class="tableHeaderField" >&nbsp;<span title="bust">S&nbsp;</span></td>   
					                    <td class="tableHeaderField" >&nbsp;<span title="bubnr">Rekvnr&nbsp;</span></td>
					                    <td class="tableHeaderField" >&nbsp;<span title="buvk">Geb&nbsp;</span></td>
					                    <td class="tableHeaderField" >&nbsp;<span title="bubl">Beløp&nbsp;</span></td>
					                    <td class="tableHeaderField" >&nbsp;<span title="buval">Val&nbsp;</span></td>
					                    <td class="tableHeaderField" >&nbsp;<span title="bulnr">Leverandør&nbsp;</span></td>
					                    <td class="tableHeaderField" >&nbsp;<span title="butype">Ty&nbsp;</span></td>
					                    <td class="tableHeaderField" >&nbsp;<span title="bublst">Bls&nbsp;</span></td>
					                    <td class="tableHeaderField" >&nbsp;<span title="busg">Sig&nbsp;</span></td>
					                    <td class="tableHeaderField" >&nbsp;<span title="butunr">Turnr&nbsp;</span></td>
   					                    <td class="tableHeaderField" >&nbsp;<span title="buoavd">Avd</span></td>
					                    <td class="tableHeaderField" >&nbsp;<span title="buopd">Oppd&nbsp;</span></td>
					                    <td class="tableHeaderField" >&nbsp;<span title="butxt">Fritekst&nbsp;</span></td>
					                    <td class="tableHeaderField" >&nbsp;<span title="bubiln">Bilnr&nbsp;</span></td>
					                    <td class="tableHeaderField" >&nbsp;<span title="bufedt">Dato&nbsp;</span></td>
					                    <td class="tableHeaderField" >&nbsp;<span title="butnr">Transp.&nbsp;</span></td>
					                    <td class="tableHeaderField" >&nbsp;<span title="bupMn/bupAr">Peri&nbsp;</span></td>
					                    <td class="tableHeaderField" >&nbsp;<span title="todoDato">Plukk&nbsp;</span></td>
					                    <td class="tableHeaderField" >&nbsp;Slett&nbsp;</td>
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
							               <td align="center" class="tableCellFirst" align="center">&nbsp;${record.bukdm}</td>
							               <td align="center" class="tableCell" >&nbsp;<b class="text11Red">${record.bust}</b></td>
							               <td class="tableCell" >&nbsp;
							               	<c:choose>           
							                   <c:when test="${record.bust=='S'}">
								               		<font class="text11MediumBlue">${record.bubnr}</font>
						               			</c:when>
						               			<c:otherwise>
						               				<a id="recordUpdate_${record.bubnr}" href="#" onClick="getBudgetItemData(this);">${record.bubnr}
							               				<img title="Update" style="vertical-align:bottom;" src="resources/images/update.gif" border="0" alt="update">&nbsp;
							               			</a>
						               			</c:otherwise>
					               			</c:choose>
							               	</td>
							               <td class="tableCell" >&nbsp;${record.buvk}</td>
							               <td class="tableCell" >&nbsp;${record.bubl}</td>
							               <td class="tableCell" >&nbsp;${record.buval}</td>
							               <td class="tableCell" >&nbsp;
							               	 <c:if test="${not empty record.levNavn}">
							               		<font class="text11MediumBlue">${record.levNavn}</font>&nbsp;[${record.bulnr}]
							               	</c:if>	
					               			</td>
							               <td class="tableCell" >&nbsp;${record.butype}</td>
							               <td class="tableCell" >&nbsp;${record.bublst}</td>
							               <td class="tableCell" >&nbsp;${record.busg}</td>
							               <td class="tableCell" >&nbsp;
							               		<c:if test="${not empty record.butunr && record.butunr!='0'}">	
							               			${record.butunr}
							               		</c:if>	
						               		</td>
							               <td class="tableCell" >&nbsp;
							               		<c:if test="${not empty record.buoavd && record.buoavd!='0'}">	
							               			${record.buoavd}
							               		</c:if>
							               	</td>
							               <td class="tableCell" >&nbsp;
							               		<c:if test="${not empty record.buopd && record.buopd!='0'}">
							               			${record.buopd}
							               		</c:if>	
							               	</td>
							               <td class="tableCell" >&nbsp;${record.butxt}</td>
							               <td class="tableCell" >&nbsp;${record.bubiln}</td>
							               <td class="tableCell" >&nbsp;
							               		<c:if test="${not empty record.bufedt && record.bufedt!='0'}">
							               			${record.bufedt}
							               		</c:if>
						               		</td>
							               <td class="tableCell" >&nbsp;
							               	  <c:if test="${not empty record.traNavn}">	
							               		<font class="text11MediumBlue">${record.traNavn}</font>&nbsp;[${record.butnr}]
							               	  </c:if>	
					               			</td>
							               <td class="tableCell" >&nbsp;${record.bupMn}/${record.bupCc}${record.bupAr}</td>
							               <td class="tableCell" >&nbsp;todo</td>
							               <%-- DELETE cell --%>							           
							               <td class="tableCell" align="center">
							               	   <c:if test="${not empty record.bubnr}">
							                   		<a style="cursor:pointer;" id="avd_${record.buoavd}@opd_${record.buopd}@tur_${record.butunr}@bubnr_${record.bubnr}" onClick="doPermanentlyDeleteInvoiceLine(this);" tabindex=-1 >
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
		           	<table class="tabThinBorderWhiteWithSideBorders" width="100%" align="left" border="0" cellspacing="0" cellpadding="0">
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
	 			<td >
	 				<form action="transportdisp_workflow_budget_edit.do" name="transportDispInvoiceUpdateItemForm" id="transportDispInvoiceUpdateItemForm" method="post">
				 	<%--Required key parameters from the Topic parent --%>
				 	<input type="hidden" name="applicationUser" id="applicationUser" value='${user.user}'>
				 	<input type="hidden" name="action" id="action" value='doUpdate'/>
					<input type="hidden" name="avd" id="avd" value='${model.container.avd}'>
					<input type="hidden" name="opd" id="opd" value='${model.container.opd}'>
					<input type="hidden" name="hepro" id="hepro" value='${model.container.tur}'>
					<input type="hidden" name="isModeUpdate" id="isModeUpdate" value="${model.record.isModeUpdate}">
					
				 	<%-- <input type="hidden" name="numberOfItemLinesInTopic" id="numberOfItemLinesInTopic" value="${numberOfItemLinesInTopic}" /> --%>
				 	
				 	<%-- Topic ITEM CREATE --%>
	 				<table width="99%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text12White" align="left" >
				 				<b>&nbsp;&nbsp;V<label onClick="showPop('debugPrintlnAjaxItemFetchAdmin');" >a</label>relinje&nbsp;</b>
				 				
		 									<span style="position:absolute; left:150px; top:200px; width:800px; height:400px;" id="debugPrintlnAjaxItemFetchAdmin" class="popupWithInputText"  >
								           		<div class="text11" align="left">
								           			<label id="debugPrintlnAjaxItemFetchInfo"></label>
								           			<br/>
								           			&nbsp;&nbsp;
								           			<button name="specialInformationButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('debugPrintlnAjaxItemFetchAdmin');">
								           			Close
								           			</button> 
								           		</div>
								        		</span>
		 				
				 				
				 				<img onClick="showPop('updateInfo');" src="resources/images/update.gif" border="0" alt="edit">&nbsp;&nbsp;<font id="editLineNr"></font>
				 				<span style="position:absolute; left:150px; top:200px; width:800px; height:400px;" id="updateInfo" class="popupWithInputText"  >
		           		   			<div class="text12" align="left" style="display:block;width:700px;word-break:break-all;">
		           		   				${XactiveUrlRPGUpdate_TvinnSad}<br/><br/>
		           		   				<button name="updateInformationButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('updateInfo');">Close</button> 
		           		   			</div>
						        </span>  
			 				</td>
		 				</tr>
	 				</table>
					<table width="99%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="12"><td class="text" align="left"></td></tr>
				 		<tr>
					 		<td>
						 		<table  class="tableBorderWithRoundCornersGray" width="100%" border="0" cellspacing="0" cellpadding="0">
						 			<tr height="5"><td class="text" align="left"></td></tr>
						 			<tr >
						 				<td class="text12" align="left"><span title="bubnr">&nbsp;
							            	<img onMouseOver="showPop('rekvnr_info');" onMouseOut="hidePop('rekvnr_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							 				Rekvnr.</span>
							 			</td>
							            <div class="text11" style="position: relative;" align="left">
											<span style="position:absolute; left:20px; top:20px; width:350px" id="rekvnr_info" class="popupWithInputText"  >
												<font class="text11">
							           			<b>Rekvnr.</b>
							           			<div>
							           				<p>Løpenummer tildeles automatisk fra telleverk (bilagsserie Å). <br/>
							           				Denne serien initieres automatisk og starter på 1000000.</p>
	    										</div>	 
						           			</font>
											</span>
											</div>
							            <td class="text12" align="left"><span title="bukdm">&nbsp;MVA&nbsp;</span></td>
						            	<td class="text12" align="left"><span title="buvk">&nbsp;Geb.</span>
							            	<a tabindex=-1 id="buvkIdLink">
	 											<img id="imgGebyrCodesSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
	 										</a>
							            </td>
							            
							            <td class="text12" align="left"><span title="bubl">&nbsp;Beløp</span></td>
					            		<td class="text12" align="left"><span title="buval">&nbsp;Valuta</span></td>
					            		<td class="text12" align="left"><span title="bulnr">&nbsp;Leverandør</span>
						            		<a tabindex=-1 id="bulnrIdLink">
	 											<img id="imgSupplierSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
	 										</a>
						            	</td>
						            	<td class="text12" align="left"><span title="levNavn">&nbsp;Lev.navn</span></td>
						            	<td class="text12" align="left"><span title="butype">&nbsp;
						            		<img onMouseOver="showPop('typekost_info');" onMouseOut="hidePop('typekost_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						            		Type&nbsp;</span>
						            	</td>
						            		<div class="text11" style="position: relative;" align="left">
											<span style="position:absolute; left:920px; top:20px; width:350px" id="typekost_info" class="popupWithInputText"  >
												<font class="text11">
							           			<b>Type forventet kostnad</b>
							           			<div>
							           				<p>Rett kodeangivelse letter attestering.</p>
							           				<ul>
							           					<li><b>A</b> = Avtalt pris/kostnad</li>
							           					<li><b>E</b> = Estimert pris/kostnad</li>
							           					<li><b>P</b> = Provisjonskalkulert estimat. Denne koden kan ikke settes manuelt. <br/>
							           							Den betyr at program for "Avsetningsforslag ved periodeslutt er kjørt og at pris for turoppgjør ikke var avtalt/estimert. <br/>
							           							Systemet har kalkulert et provisjonsbasert estimat som er benyttet i forslaget.</li>
							           				</ul>
	    										</div>	 
						           			</font>
											</span>
											</div>
						            	<td class="text12" align="left"><span title="bublst">&nbsp;
						            		<img onMouseOver="showPop('bls_info');" onMouseOut="hidePop('bls_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						            		Bls&nbsp;</span>
						            	</td>
						            		<div class="text11" style="position: relative;" align="left">
											<span style="position:absolute; left:1020px; top:20px; width:350px" id="bls_info" class="popupWithInputText"  >
												<font class="text11">
							           			<b>Bls. Belastes</b>
							           			<div>
							           				<p>Hvem forventes å bære kostnaden:</p>
							           				<ul>
							           					<li><b>E</b> = Egen (Det er vi selv som må bære kostnaden)</li>
							           					<li><b>T</b> = Transportør (Trekkes i transportøravregning)</li>
							           					<li><b>K</b> = Kunder (Den som mottar faktura på oppdraget skal ekstradebiteres denne kostnaden).<br/>
							           								<b>Teknisk:</b> Ved plukk til kostn.føring legges 100,00 i feltet for "prosent til inntekt". 
							           								<br/>Diff mellom kostnad og det som ER fakturert legges ut som åpen fakturalinje(v/overf. til øko).</li>
							           					<li><b>U</b> = Ubetinget mot KUNDE. (selv om inntekt finnes fra før, Teknisk 999,99 i felt for "prosent til inntekt").</li>
							           					<li><b>O</b> = Ferdig utført trekk i hovedavregning på angitt tur</li>
							           					<li><b>J</b> = Justering (benyttes NÅR TUR ALT ER AVREGNET)</li>
							           					<li><b>P</b> = Justering ER UTFØRT (hovedstatus blir samtidig A)</li>
							           					
							           				</ul>
	    										</div>	 
						           			</font>
											</span>
											</div>
						            	<td class="text12" align="left"><span title="busg">&nbsp;Sign&nbsp;</span></td>
						            	<td class="text12" align="left" ><span title="butunr">&nbsp;Turnr</span></td>
						            	<td class="text12" align="left"><span title="buoavd">&nbsp;&nbsp;&nbsp;Avd</span></td>
						            	<td class="text12" align="left" ><span title="buopd">&nbsp;Oppd.</span></td>
							        </tr>
							        <tr>
						        		<td class="text12" align="left">&nbsp;
						            		<input readonly type="text" class="inputTextReadOnly" name="bubnr" id="bubnr" size="8" maxlength="8" value="${model.record.bubnr}">
							            </td>
							            <td class="text12" align="left" valign="middle">
						            		<select class="inputTextMediumBlueField" id="bukdm" name="bukdm">
						            			<option value="">-blank-</option>
						            			<option value="J" <c:if test="${model.record.bukdm == 'J'}"> selected </c:if> >J</option>
						           				<option value="N" <c:if test="${model.record.bukdm == 'N'}"> selected </c:if> >N</option>
							           		</select>
						            	</td>
							            <td align="left">
						        			<select class="inputTextMediumBlueMandatoryField" name="buvk" id="buvk">
						 						<option value="">-select-</option>
							 				  	<c:forEach var="record" items="${model.gebyrCodesList}" >
							 				  		<option value="${record.kgekod}"<c:if test="${model.record.buvk == record.kgekod}"> selected </c:if> >${record.kgekod}</option>
												</c:forEach>
											</select>
										</td>
										<td class="text12" align="left">
						            		<input type="text" onKeyPress="return amountKey(event)" class="inputTextMediumBlueMandatoryField" name="bubl" id="bubl" size="8" maxlength="8" value="${model.record.bubl}">
							            </td>
										<td align="left" nowrap valign="middle">
							            	<select class="inputTextMediumBlueMandatoryField" name="buval" id="buval">
						 						<option value="">-valuta-</option>
							 				  	<c:forEach var="currency" items="${model.currencyCodeList}" >
							 				  		<option value="${currency}"<c:if test="${model.record.buval == currency || (empty model.record.buval && currency=='NOK')}"> selected </c:if> >${currency}</option>
												</c:forEach> 
											</select>
										</td>
						        		<td class="text12" align="left" ><input type="text" class="inputText" name="bulnr" id="bulnr" size="9" maxlength="8" value="${model.record.bulnr}"></td>
							            <td class="text12" align="left">&nbsp;
						            		<input readonly tabindex=-1 class="inputTextReadOnly" name="levNavn" id="levNavn" size="20" maxlength="35" value="${model.record.levNavn}">
							            </td>
							            <td class="text12" align="left" >
							            	<select class="inputTextMediumBlueMandatoryField" name="butype" id="butype">
						 						<option value="">-select-</option>
						 						<c:choose>
						 							<c:when test="${not empty model.record.butype}">
						 								<option value="A" <c:if test="${model.record.butype == 'A'}"> selected </c:if> >A</option>
					           							<option value="E" <c:if test="${model.record.butype == 'E'}"> selected </c:if> >E</option>
					           							<option value="P" <c:if test="${model.record.butype == 'P'}"> selected </c:if> >P</option>
					           						</c:when>
					           						<c:otherwise>
					           							<option value="A" >A</option>
					           							<option value="E" selected >E</option>
					           							<option value="P" >P</option>
					           						</c:otherwise>
				           						</c:choose>						 						
					           				</select>
						            	</td>
							            <td class="text12" align="left" >
							            	<select class="inputTextMediumBlueMandatoryField" name="bublst" id="bublst">
						 						<option value="">-select-</option>
						 						<c:choose>
						 							<c:when test="${not empty model.record.bublst}">
						 								<option value="E" <c:if test="${model.record.bublst == 'E'}"> selected </c:if> >E</option>
							           					<option value="T" <c:if test="${model.record.bublst == 'T'}"> selected </c:if> >T</option>
							           					<option value="K" <c:if test="${model.record.bublst == 'K'}"> selected </c:if> >K</option>
							           					<option value="U" <c:if test="${model.record.bublst == 'U'}"> selected </c:if> >U</option>
							           					<option value="O" <c:if test="${model.record.bublst == 'O'}"> selected </c:if> >O</option>
							           					<option value="J" <c:if test="${model.record.bublst == 'J'}"> selected </c:if> >J</option>
							           					<option value="P" <c:if test="${model.record.bublst == 'P'}"> selected </c:if> >P</option>
					           						</c:when>
					           						<c:otherwise>
					           							<c:choose>
						           							<c:when test="${not empty model.parentTrip}">
											            		<option value="E" >E</option>
									           					<option value="T" selected >T</option>
									           					<option value="K" >K</option>
									           					<option value="U" >U</option>
									           					<option value="O" >O</option>
									           					<option value="J" >J</option>
									           					<option value="P" >P</option>
								           					</c:when>
								           					<c:otherwise>
								           						<option value="E" selected >E</option>
									           					<option value="T" >T</option>
									           					<option value="K" >K</option>
									           					<option value="U" >U</option>
									           					<option value="O" >O</option>
									           					<option value="J" >J</option>
									           					<option value="P" >P</option>
								           					</c:otherwise>
							           					</c:choose>
					           						</c:otherwise>
				           						</c:choose>
					           				</select>
						            	</td>
							            <td class="text12" align="left" ><input type="text" class="inputText" name="busg" id="busg" size="3" maxlength="3" value="${user.signatur}"></td>
							            
						        		<td class="text12" align="left" >
						        			<input type="text" class="inputText" name="butunr" id="butunr" size="8" maxlength="10" value="${model.container.tur}">
						        		</td>
						        		<td class="text12">&nbsp;
						        			<c:choose>
												<c:when test="${not empty model.record.buoavd}">
							        				<input type="text" class="inputText" name="buoavd" id="buoavd" size="5" maxlength="4" value="${model.record.buoavd}">
							        			</c:when>
							        			<c:otherwise>
							        				<c:choose>
													<c:when test="${not empty model.parentTrip}">
							        					<input type="text" class="inputText" name="buoavd" id="buoavd" size="5" maxlength="4" value="">
							        				</c:when>
							        				<c:otherwise>
							        					<input type="text" class="inputText" name="buoavd" id="buoavd" size="5" maxlength="4" value="${model.container.avd}">
							        				</c:otherwise>
							        				</c:choose>
							        			</c:otherwise>
						        			</c:choose>
						        		</td>
						        		<td class="text12" align="left" >
						        			<c:choose>
												<c:when test="${not empty model.record.buopd}">
							        				<input type="text" class="inputText" name="buopd" id="buopd" size="8" maxlength="7" value="${model.record.buopd}">
							        			</c:when>
							        			<c:otherwise>
							        				<c:choose>
													<c:when test="${not empty model.parentTrip}">
														<input type="text" class="inputText" name="buopd" id="buopd" size="8" maxlength="7" value="">
													</c:when>
													<c:otherwise>
							        					<input type="text" class="inputText" name="buopd" id="buopd" size="8" maxlength="7" value="${model.container.opd}">
							        				</c:otherwise>
							        				</c:choose>
							        			</c:otherwise>
						        			</c:choose>
						        		</td>
						        		
							        </tr>
							        <tr height="12"><td class="text" align="left" colspan="20"><hr></td></tr>
							        <tr height="3"><td class="text" align="left"></td></tr>
							        <tr>
							 			<td colspan="2" class="text12" align="left" >&nbsp;&nbsp;<span title="butxt">Fritekst</span></td>
							            <td class="text12" align="left" >&nbsp;<span title="bubiln">Bilnr.</td>
							            <td class="text12" align="left" >&nbsp;<span title="bufedt">Dato</td>
							            <td class="text12" align="left" >&nbsp;<span title="todoKo">Ko</td>
							            <td class="text12" align="left" >&nbsp;<span title="butnr">Transp.
							            	<a tabindex=-1 id="butnrIdLink">
		 										<img id="imgTruckersNrSearch" style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="13px" height="13px" border="0" alt="search">
		 									</a>
							            </td>
							            <td class="text12" align="left"><span title="traNavn">&nbsp;Transp.navn</span></td>
							            <td class="text12" align="left" >&nbsp;<span title="bupMn">Periode</td>
							            <td class="text12" align="left" >&nbsp;<span title="bupAr">År</td>
					            		<td class="text12" align="left" >&nbsp;<span title="bubilk">Bilkode</td>	
							        </tr>
							        <tr>
							        	<td colspan="2" class="text12">&nbsp;	
						        			<input type="text" class="inputText" name="butxt" id="butxt" size="35" maxlength="35" value="${model.record.butxt}">
										</td>
										<td class="text12">
											<c:choose>
											<c:when test="${not empty model.record.bubiln}">	
						        				<input type="text" class="inputText" name="bubiln" id="bubiln" size="10" maxlength="8" value="${model.record.bubiln}">
						        			</c:when>
						        			<c:otherwise>
						        				<input type="text" class="inputText" name="bubiln" id="bubiln" size="10" maxlength="8" value="${model.recordSpecificTrip.tubiln}">
						        			</c:otherwise>
						        			</c:choose>
										</td>
										<td class="text12">	
											<c:choose>
											<c:when test="${not empty model.record.bufedt}">
						        				<input type="text" class="inputText" name="bufedt" id="bufedt" size="9" maxlength="8" value="${model.record.bufedt}">
						        			</c:when>
						        			<c:otherwise>
						        				<input type="text" class="inputText" name="bufedt" id="bufedt" size="9" maxlength="8" value="${model.recordSpecificTrip.tudt}">
						        			</c:otherwise>
						        			</c:choose>
										</td>
										<td class="text12">	
						        			<select class="inputTextMediumBlueField" name="todoKo" id="todoKo">
						 						<option value="">-select-</option>
							            		<option value="B" <c:if test="${Xmodel.record.todo == 'B'}"> selected </c:if> >?</option>
					           				</select>
										</td>
										<td class="text12">	
											<c:choose>
											<c:when test="${not empty model.record.butnr}">
						        				<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="butnr" id="butnr" size="9" maxlength="8" value="${model.record.butnr}">
						        			</c:when>
						        			<c:otherwise>
						        				<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="butnr" id="butnr" size="9" maxlength="8" value="${model.recordSpecificTrip.tuknt2}">
						        			</c:otherwise>
						        			</c:choose>
										</td>
										<td class="text12" align="left">&nbsp;
						            		<input tabindex=-1 readonly class="inputTextReadOnly" name="traNavn" id="traNavn" size="20" maxlength="35" value="${model.record.traNavn}">
							            </td>
										<td class="text12">	
											<c:choose>
											<c:when test="${not empty model.record.bupMn}">
						        				<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="bupMn" id="bupMn" size="2" maxlength="2" value="${model.record.bupMn}">
						        			</c:when>
						        			<c:otherwise>
						        				<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="bupMn" id="bupMn" size="2" maxlength="2" value="${model.recordSpecificTrip.turmnd}">
						        			</c:otherwise>
						        			</c:choose>
										</td>
										<td class="text12">	
											<c:choose>
											<c:when test="${not empty model.record.bupAr}">
						        				<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="bupAr" id="bupAr" size="4" maxlength="4" value="${model.record.bupCc}${model.record.bupAr}">
						        			</c:when>
						        			<c:otherwise>
						        				<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="bupAr" id="bupAr" size="4" maxlength="4" value="${model.recordSpecificTrip.centuryYearTurccTuraar}">
						        			</c:otherwise>
						        			</c:choose>
										</td>
										<td class="text12">&nbsp;
											<c:choose>
											<c:when test="${not empty model.record.bubilk}">	
						        				<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="bubilk" id="bubilk" size="3" maxlength="3" value="${model.record.bubilk}">
						        			</c:when>
						        			<c:otherwise>
						        				<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="bubilk" id="bubilk" size="3" maxlength="3" value="${model.recordSpecificTrip.tubilk}">
						        			</c:otherwise>
						        			</c:choose>
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
								&nbsp;&nbsp;<input class="inputFormSubmitGray" type="button" name="updCancelButton" id="updCancelButton" value='<spring:message code="systema.transportdisp.cancel"/>'>
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

