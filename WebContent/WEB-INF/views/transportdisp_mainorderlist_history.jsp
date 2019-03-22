<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTransportDisp.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/transportdispglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/transportdisp_mainorderlist_history.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	
	<style type = "text/css">
	.ui-dialog{font-size:10pt;}
	.ui-datepicker { font-size:9pt;}
	/* this is in order to customize a SPECIFIC ui dialog in the .js file ...dialog() */
	.print-dialog-class .ui-widget-content{ background-color:lightsteelblue } 
	.main-dialog-class .ui-widget-content{ background-image:none;background-color:lemonchiffon }
	
	/* this line will align the datatable search field in the left */
	.dataTables_wrapper .transpDispMainOrderListFilter .dataTables_filter{float:left}
	</style>
	

<table width="100%"  class="text14" cellspacing="0" border="0" cellpadding="0">
	<tr>
	<td>
	<%-- tab container component --%>
	<table width="100%"  class="text14" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25"> 
			<td width="18%" valign="bottom" class="tab" align="center" nowrap>
				<img style="vertical-align:middle;" src="resources/images/bulletGreen.png" width="6px" height="6px" border="0" alt="open orders">
				<font class="tabLink">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.all.openorders.tab"/></font>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="18%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a class="text14" onClick="setBlockUI(this);" href="transportdisp_mainorderlist_history_fellesutskrift.do?avd=${searchFilter.avd}&sign=${searchFilter.sign}" > 	
					<img style="vertical-align:middle;" src="resources/images/printer2.png" width="12px" height="12px" border="0" alt="create new">
					<font class="tabDisabledLink"><spring:message code="systema.transportdisp.fellesutskrift.tab"/></font>
				</a>
				
			</td>
			<td width="80%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>	
		</tr>
	</table>
	</td>
	</tr>
	
	<input type="hidden" name="applicationUser" id="applicationUser" value='${user.user}'>
	<input type="hidden" name="userHttpJQueryDocRoot" id="userHttpJQueryDocRoot" value='${user.httpJQueryDocRoot}'>
	<input type="hidden" name="tripNr" id="tripNr" value='${searchFilter.tur}'>
	<input type="hidden" name="fkeysavd" id="fkeysavd" value='${searchFilter.avd}'>
	<input type="hidden" name="fkeysopd" id="fkeystur" value='${searchFilter.tur}'>
	 	        
	
	<tr>
	<td>
		<table width="100%" class="tabThinBorderWhiteWithSideBorders" border="0" cellspacing="0" cellpadding="0">
			<tr height="10"><td></td></tr>
			<%-- Should be set-on for the whole solution. This here was just a prototype
 	        <tr>
 	        <td height="2px" valign="top" align="right"><font class="text11MediumBlue">Stretch workspace</font><input tabindex="-1" type="checkbox" id="checkBoxVisibility">&nbsp;&nbsp;</td>
 	        </tr>
 	        --%>
		</table>		
	</td>
	</tr>
	
	
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
	
	
		<tr>
		<td>
			<%-- this table wrapper is necessary to apply the css class with the thin border --%>
			<table id="wrapperTable" class="tabThinBorderWhite" width="100%" cellspacing="1">
			
			
			<%-- OPEN ORDERS --%>
			<%-- search filter component --%>
			
			<tr>
				<td>
					
				<%-- this container table is necessary in order to separate the datatables element and the frame above, otherwise
					 the cosmetic frame will not follow the whole datatable grid including the search field... --%>
				<table id="containerdatatableTable2" style="width:100%;" cellspacing="2" align="left" >
				
			    <tr>
   				    <form name="searchForm" id="searchForm" action="transportdisp_mainorderlist_history.do?action=doFind" method="post" >
					<input type="hidden" name="tur" id="tur" value='${searchFilter.tur}'>
					<input type="hidden" name="userAvd" id="userAvd" value='${model.userAvd}'>
			    	<td> 
			    	<table cellspacing="2" >
			    		<tr> 
			    		<td>
				        	&nbsp;<font title="wsprebook" class="text14"><spring:message code="systema.transportdisp.orders.open.search.label.prebook"/></font>
				        </td>
				        
				        <td>
				        	<select class="inputText14" name="wsprebook" id="wsprebook">
		 						<option value="A" <c:if test="${searchFilter.wsprebook == 'A'}"> selected </c:if> >Alle</option>
		 						<option value="F" <c:if test="${searchFilter.wsprebook == 'F'}"> selected </c:if> >Ordre</option>
		 						<option value="P" <c:if test="${searchFilter.wsprebook == 'P'}"> selected </c:if> >PreBook</option>
							</select>
				        </td>
			    		
				        <td>
							<font title="avd/wssavd" class="text14"><spring:message code="systema.transportdisp.orders.open.search.label.dept"/></font>
							<a href="javascript:void(0);" onClick="window.open('transportdisp_workflow_childwindow_avd.do?action=doFind','avdWin','top=100px,left=300px,height=600px,width=800px,scrollbars=no,status=no,location=no')">
		 						<img id="imgAvdSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="14px" width="14px" border="0" alt="search">
		 					</a>
							<font id="objAvdGroupsList" class="text14SkyBlue" style="cursor:pointer;text-decoration: underline;">Grp</font>
			        	</td>
			        	
				        
			    		<td>
							<input type="text" class="inputText" name="avd" id="avd" size="5" maxlength="4" value='${searchFilter.avd}'>
							<div id="divAvdGroupsList" style="display:none;position: relative;height:10em;" class="ownScrollableSubWindowDynamicWidthHeight" align="left" >
		 						<%--
		 						<select class="inputTextMediumBlueMandatoryField" name="avdGroupsList" id="avdGroupsList" size="5">
				            		<c:forEach var="record" items="${model.avdGroupsList}" >
			                       	 	<option style="color:black;" value="${record.agrKode}">${record.agrKode}&nbsp;${record.agrNavn}</option>
									</c:forEach> 
								</select>
								 --%>
								<table id="tblAvdGroupsList" class="inputTextMediumBlueMandatoryField">
									<c:forEach items="${model.avdGroupsList}" var="record" varStatus="counter">  
									<tr>
										<td id="id_${record.agrKode}" OnClick="doPickAvdGroup(this)" class="tableHeaderFieldFirst" style="cursor:pointer;" ><font class="text14SkyBlue">${record.agrKode}</font></td>
										<td class="tableHeaderField">${record.agrNavn}</td>
									</tr>
									</c:forEach>
								</table>	
							</div>	
			        	</td>
			        	
			        	<td>	
			        		&nbsp;<font title="opd/wssopd" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.ourRef"/></font>
			        		<%-- release 2 BRING (punkt (17)) and delete the above space with the link --%>
			        		<a href="javascript:void(0);" onClick="window.open('sporringoppdraggate.do?lang=NO&cw=true','opdWin','top=100px,left=200px,height=900px,width=1500px,scrollbars=no,status=no,location=no')">
		 						<img id="imgOpdSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="14px" width="14px" border="0" alt="search">
		 					</a>
		 					
				        </td>
				        
			        	
			        	<td>	
			        		<input type="text" class="inputText" name="opd" id="opd" size="10" maxlength="15" value='${searchFilter.opd}'>
				        </td>
				        <td>	
			        		&nbsp;<font title="opdType/wssot" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.orderType"/></font>
				        	
				        </td>
				        
				        <td>	
			        		<input type="text" class="inputText" name="opdType" id="opdType" size="10" maxlength="15" value='${searchFilter.opdType}'>
				        </td>
				        <td>	
			        		&nbsp;<font title="wsavs" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.shipper"/></font>
				        </td>
				        <td>	
			        		<input type="text" class="inputText" name="wsavs" id="wsavs" size="10" maxlength="20" value='${searchFilter.wsavs}'>
				        </td>
			        	<td>	
			        		&nbsp;<font title="from/wssdf" class="text14"><spring:message code="systema.transportdisp.orders.open.search.label.from"/></font>
				        </td>
				        <td>	
			        		<input type="text" class="inputText" name="from" id="from" size="9" maxlength="8" value='${searchFilter.from}'>
				        </td>
				        <td>	
				        	&nbsp;<font title="wopdtf/wopdtt" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.orderdate"/></font>
				        </td>
				        
				        <td>	
			        		<input type="text" class="inputText" name="wopdtf" id="wopdtf" size="9" maxlength="8" value='${searchFilter.wopdtf}'>
			        		<input type="text" class="inputText" name="wopdtt" id="wopdtt" size="9" maxlength="8" value='${searchFilter.wopdtt}'>
				        </td>
				        
				        <td>	
			        		&nbsp;<font title="wsgn" class="text14">Godsnr.</font>
				        </td>
				        <td>	
			        		<input type="text" class="inputText" name="wsgn" id="wsgn" size="21" maxlength="20" value='${searchFilter.wsgn}'>
				        </td>
				        
				        <td>	
			        		&nbsp;<font title="wssndn" class="text14">Send.nr</font>
				        </td>
				        <td>	
			        		<input type="text" class="inputText" name="wssndn" id="wssndn" size="10" maxlength="20" value='${searchFilter.wssndn}'>
				        </td>
				        
				        <td>	
			        		&nbsp;<font title="wsclid" class="text14">Kollid</font>
				        </td>
				        <td>	
			        		<input type="text" class="inputText" name="wsclid" id="wsclid" size="10" maxlength="20" value='${searchFilter.wsclid}'>
				        </td>
				        
				        </tr>
				        
				        
				        <tr> 
				        <td>
				        	&nbsp;<font title="wsdista" class="text14"><spring:message code="systema.transportdisp.orders.open.search.label.ordreType"/></font>
				        </td>
				        <td>
				        	<select class="inputText14" name="wsdista" id="wsdista">
		 						<option value="*" >Alle</option>
							</select>
				        </td>
				        
				        <td align="right">	
			        		&nbsp;<font title="sign/wsssg" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.sign"/></font>
				        </td>
						<td>	
			        		<input type="text" class="inputText" name="sign" id="sign" size="4" maxlength="5" value='${searchFilter.sign}'>
				        </td>
				        <td>	
			        		&nbsp;<font title="wsrfa" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.poNr"/></font>
				        </td>
						<td>	
			        		<input type="text" class="inputText" name="wsrfa" id="wsrfa" size="10" maxlength="15" value='${searchFilter.wsrfa}'>
				        </td>
				        <td>	
			        		&nbsp;<font title="wsopdgi" class="text14">Op.giver</font>
				        </td>

						<td>	
			        		<input type="text" class="inputText" name="wsopdgi" id="wsopdgi" size="10" maxlength="20" value='${searchFilter.wsopdgi}'>
				        </td>
				        <td>	
			        		&nbsp;<font title="wsmot" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.consignee"/></font>
				        </td>

						<td>	
			        		<input type="text" class="inputText" name="wsmot" id="wsmot" size="10" maxlength="20" value='${searchFilter.wsmot}'>
				        </td>
			        	<td>	
				        	&nbsp;<font title="to/wssdt" class="text14"><spring:message code="systema.transportdisp.orders.open.search.label.to"/></font>
				        </td>
			        	
				        <td>	
				        	<input type="text" class="inputText" name="to" id="to" size="9" maxlength="8" value='${searchFilter.to}'>
				        </td>
				        
				        <td>	
			        		&nbsp;<font title="wstur" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.tur"/></font>
				        </td>
				        <td>	
			        		<input type="text" class="inputText" name="wstur" id="wstur" size="10" maxlength="20" value='${searchFilter.wstur}'>
				        </td>
				        
				        <td>	
			        		&nbsp;<font title="wstra" class="text14">Transp.</font>
				        </td>
				        <td>	
			        		<input type="text" class="inputText" name="wstra" id="wstra" size="10" maxlength="10" value='${searchFilter.wstra}'>
				        </td>
				        <td>	
			        		&nbsp;<font title="faktknr" class="text14">Fakt.knr</font>
				        </td>
				        <td>	
			        		<input type="text" class="inputText" name="faktknr" id="faktknr" size="10" maxlength="10" value='${searchFilter.faktknr}'>
				        </td>
				        <td>	
				        		<input onClick="setBlockUI(this);" class="inputFormSubmit" type="submit" name="submit" id="submit" value='<spring:message code="systema.transportdisp.search"/>'>
				        	</td>
				        	<td>
				        		<input onClick="setBlockUI(this);" class="inputFormSubmitStd" type="button" name="removeFilterButton" id="removeFilterButton" value='<spring:message code="systema.transportdisp.search.remove.filter"/>'>
				        </td> 
				        
				        </tr>
				        
						<tr height="5"><td></td></tr>
				
				        <tr>
				        <%-- Not applicable for history view ? (JOVO?)
						<td colspan="2" class="text14MediumBlue">
				            <input class="inputFormSubmitStd" tabindex=0 style="cursor:pointer;" type="button" value="<spring:message code="systema.transportdisp.orders.open.form.button.createnew.trip"/>" name="cnButton" id="cnButton">
				        </td>
				         --%>
				        </tr>
				        <tr>
					        <td>	
				        		&nbsp;<font title="wsfskod" class="text14">Frisøk.kode</font>
					        </td>
					        <td>	
				        		<input type="text" class="inputText" name="wsfskod" id="wsfskod" size="4" maxlength="3" value='${searchFilter.wsfskod}'>
					        </td>
					        <td align="right">	
				        		&nbsp;<font title="wsfssok" class="text14">Frisøk.txt</font>
					        </td>
					        <td colspan="3">	
				        		<input type="text" class="inputText" name="wsfssok" id="wsfssok" size="10" maxlength="35" value='${searchFilter.wsfssok}'>
					        </td>
				        </tr>
				    </table>    
					</td>
					</form>
				</tr>
				<tr height="5"><td></td></tr>
				
				<c:if test="${not empty model.containerOpenOrders.maxWarning}">
					<tr>	
						<td class="listMaxLimitWarning">
						<img style="vertical-align:bottom;" src="resources/images/redFlag.png" width="16" height="16" border="0" alt="Warning">
						${model.containerOpenOrders.maxWarning}</td>
					</tr>
				</c:if>
				
				<tr height="5"><td></td></tr>
				<tr>
				<%-- for responsivness fix the with of the cell 
				<td style="max-width: 1000px">
				<table id="openOrders" class="display compact cell-border nowrap" style="width:100%">
				... and put responsive: true in jquery datatable..
				--%>
				
				<td>
				<table id="openOrders" class="display compact cell-border" cellspacing="0">
					<thead >
					<tr class="tableHeaderField" >
						
                   		<th title="avd/ordre" width="2%" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.ourRef"/></th> 
                   		<th title="Track&Trace status:Urørt/Hentet/Levert" width="2%" class="text14"><spring:message code="systema.transportdisp.orders.current.list.search.label.ttstat"/></th>
                   		<th title="Oppdragstype" width="2%" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.orderType"/></th>
	                    <th title="Signatur" width="2%" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.sign"/></th>
	                    <th title="Tur" width="2%" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.tur"/></th>
	                    
	                    <th title="Avsender" width="2%" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.shipper"/></th>   
	                    <th title="Fra" width="2%" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.from"/></th> 
	                    <th title="Dato" width="2%" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.date"/>&nbsp;</th> 
	                    
	                    <th title="Mottaker" width="2%" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.consignee"/></th>   
	                    <th title="Til" width="2%" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.to"/></th> 
	                    <th title="Dato" width="2%" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.date"/></th>
	                    
	                    <th title="Antall" align="right" width="2%" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.pcs"/></th>   
	                    <th title="V.slag" width="2%" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.goodsDesc"/></th>   
	                    <th title="Vekt" align="right" width="2%" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.weight"/></th>   
	                    <th title="M3" align="right" width="2%" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.volume"/></th>   
	                    <th title="Lm" align="right" width="2%" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.loadMtr"/></th>  
	                    <th title="PONr" width="2%" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.poNr"/></th>
	                    <th title="Farliggods" width="2%" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.dangerousgoods.adr"/></th>
	                    <th title="Internmelding" width="2%" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.internmelding.text"/></th>
	                    <th title="Print" width="2%" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.printDocs"/></th>
	                    
	                    <%--
	                    <th width="2%" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.fraktbrev"/></th>
	                     --%>
	                    <th width="2%" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.copy"/></th>
	                    <th width="2%" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.move"/></th>
	                    <th width="2%" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.delete"/></th>
	                    <th title="P=Prebooking. Linjen lys gul!" width="2%" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.prebooking"/></th>
	                    
	                </tr> 
	                </thead>
	                
	                
	                <tbody >
		            <c:forEach items="${listOpenOrders}" var="record" varStatus="counter">  
		            <%-- Prebooking=hestn7: should be cathegorized with another color. In this case with warning color... --%>  
		            <tr class="tex14" <c:if test="${not empty record.hestn7}"> style="background-color:#FEEFB3; color: #9F6000;"</c:if> >
		               
            		   
            		   <td width="2%" class="textMediumBlue" nowrap>
			           		<div id="davd${record.heavd}_dopd${record.heopd}_linkcontainer${counter.count}" ondrop="drop(event)" ondragenter="highlightDropArea(event)" ondragleave="noHighlightDropArea(event)" ondragover="allowDrop(event)" >
			           		<c:choose>
				           		<c:when test="${empty searchFilter.tur && not empty searchFilter.opd}">
					           		<a style="cursor:pointer;" id="hepro_${record.hepro}@heavd_${record.heavd}@heopd_${record.heopd}@alinkOpenOrdersListId_${counter.count}" onClick="goToSpecificOrder(this);">
		    		    				<img title="Update" style="vertical-align:bottom;" src="resources/images/update.gif" border="0" alt="update">
		    		    				<font class="textMediumBlue">${record.heavd}/${record.heopd}</font>
		    		    			</a>
	    		    			</c:when>
	    		    			<c:otherwise>
	    		    				<a style="cursor:pointer;" id="hepro_${searchFilter.tur}@heavd_${record.heavd}@heopd_${record.heopd}@alinkOpenOrdersListId_${counter.count}" onClick="goToSpecificOrder(this);">
		    		    				<img title="Update" style="vertical-align:bottom;" src="resources/images/update.gif" border="0" alt="update">
		    		    				<font class="textMediumBlue">${record.heavd}/${record.heopd}</font>
		    		    			</a>
	    		    			</c:otherwise>
    		    			</c:choose>
    		    			</div>
			           </td>
			           				
			           <c:choose>
					           <c:when test="${not empty record.ttstat}">
					           		<c:choose>
					           		<c:when test="${record.ttstat=='Levert' || record.ttstat=='Urørt' || record.ttstat=='Hentet' }">
						           		<c:if test="${record.ttstat=='Levert'}" >   
						           			<td width="2%" align="center" class="textMediumBlue" >
							           			<font class="inputText11" style="background-color: #DFF2BF;color: #4F8A10;" onMouseOver="showPop('delivered_info${counter.count}');" onMouseOut="hidePop('delivered_info${counter.count}');">${record.ttstat}</font>
							           			<div class="text11" style="position: relative;" align="left">
													<span style="position:absolute; left:15px; top:2px;" id="delivered_info${counter.count}" class="popupWithInputText"  >
							 							<font class="text11" >
										           			${record.hesgm}-${record.hedtmo}:${record.heklmo}
									           			</font>
													</span>
												</div>
						           			</td>
						           		</c:if>
						           		<c:if test="${record.ttstat=='Urørt'}" >   
						           			<td width="2%" align="center" class="textMediumBlue" style="color: #D8000C;">
							           			<font class="inputText11" style="background-color: #FFBABA;color: #D8000C;">${record.ttstat}</font>
						           			</td>
						           		</c:if>
						           		<c:if test="${record.ttstat=='Hentet'}" >   
						           			<td width="2%" align="center" class="textMediumBlue" style="color: #9F6000;">
							           			<font class="inputText11" style="background-color: #FEEFB3;color: #9F6000;">${record.ttstat}</font>
						           			</td>
						           		</c:if>
					           		</c:when>
					           		<c:otherwise>
					           			<td width="2%" align="center" class="textMediumBlue">&nbsp;${record.ttstat}</td>
					           		</c:otherwise>
					           		</c:choose>
					           </c:when>
					           <c:otherwise>
					           		<td width="2%" align="center" class="textMediumBlue">&nbsp;${record.ttstat}</td>
					           </c:otherwise>
			               </c:choose>
			               
									
			           							 		
			           <td width="2%" align="center" class="textMediumBlue">&nbsp;${record.heot}</td>
		               <td width="2%" align="center" class="textMediumBlue">&nbsp;${record.hesg}</td>
		               <td width="2%" class="textMediumBlue">&nbsp;${record.hepro}</td>
		               <td width="2%" class="textMediumBlue">&nbsp;${record.henas}</td>
		               <td width="2%" class="textMediumBlue">&nbsp;${record.helka}-${record.heads3}</td>
		               
		               <td width="2%" class="textMediumBlue">${record.trsdfd}&nbsp;${record.trsdfk}
		               	 <%-- Wait for GO from JOVO (Kingsr.) Right now ONLY ISO-date but below handles the locale issue
		               	 <c:choose>
			               	 <c:when test="${not empty user.usrLang && user.usrLang == 'NO'}">
				               	 <c:if test="${not empty record.trsdfd}">
		    						<%-- Convert the raw strings to a date primitive 
		    						<c:catch var="invalid">
									    <fmt:parseDate value="${record.trsdfd}" var="trsdfd" pattern="yyyyMMdd" />
									    <fmt:formatDate pattern="ddMMyy" value="${trsdfd}" />
									</c:catch>
									<c:if test="${not empty invalid}">
									    ?${record.trsdfd}
									</c:if>
		    					 </c:if>
	    					 </c:when>
	    					 <c:otherwise>
	    					 	${record.trsdfd}
	    					 </c:otherwise>
    					 </c:choose>
    					  --%>
		               </td>
		               
		               <td width="2%" class="textMediumBlue">&nbsp;${record.henak}</td>
		               <td width="2%" class="textMediumBlue">&nbsp;${record.hetri}-${record.headk3}</td>
		               <td width="2%" class="textMediumBlue">&nbsp;${record.trsdtd}&nbsp;${record.trsdtk}</td>
		               <td align="right" width="2%" class="textMediumBlue">&nbsp;${record.hent}</td>
		               <td width="2%" class="textMediumBlue">&nbsp;${record.hevs1}</td>
		               <td align="right" width="2%" class="textMediumBlue">&nbsp;${record.hevkt}</td>
		               <td align="right" width="2%" class="textMediumBlue">&nbsp;${record.hem3}</td>
		               <td align="right" width="2%" class="textMediumBlue">&nbsp;${record.helm}</td>
		               <td width="2%" class="textMediumBlue">&nbsp;${record.herfa}</td>
		               <td width="2%" align="center" class="textMediumBlue" style="color:red;">&nbsp;${record.hepoen}</td>
		               <td width="2%" align="center" class="textMediumBlue">
		               		<c:if test="${not empty record.interninfo}">
			               		<img onMouseOver="showPop('imText_info${counter.count}');" onMouseOut="hidePop('imText_info${counter.count}');" style="vertical-align:bottom;" src="resources/images/info4.png" width="12" height="12" border="0" alt="Internmelding">
			               		
			               		<div class="text10" style="position: relative;" align="left">
		 						<span style="position:absolute; left:-50px; top:2px; width:250px;" id="imText_info${counter.count}" class="popupWithInputText"  >
		 							<font class="text10">
					           			<b>Internmelding</b>
					           			<p>${record.interninfo}</p>
				           			</font>
								</span>
								</div>
							</c:if>
		               </td>
		               
		               <td width="2%" align="center" class="textMediumBlue">
		               		<a title="print Oppd.&nbsp;${record.heopd}" class="printLink" id="printLink${counter.count}" runat="server" href="#">
								<img style="vertical-align: middle;" src="resources/images/printer3.png" width="20px" height="20px" border="0" alt="Print">
							</a>
							<div style="display: none;" class="clazz_dialogPrint" id="dialogPrint${counter.count}" title="Dialog Print">
									<form id="printForm${counter.count}">
										<input type="hidden" id="avd${counter.count}" name="avd${counter.count}" value="${record.heavd}">
										<input type="hidden" id="opd${counter.count}" name="opd${counter.count}" value="${record.heopd}">
										<input type="hidden" id="tur${counter.count}" name="tur${counter.count}" value="${Xrecord.hepro}">
										<input type="hidden" id="signP${counter.count}" name="signP${counter.count}" value="${record.hesg}">
								 	<table>
				   						<tr height="3"><td></td></tr>
				   						<tr>
											<td class="text14" align="left" >
												<c:choose>
												<c:when test="${record.hepk1 == 'J'}">
													<input type="checkbox" name="fbType${counter.count}" id="fbType${counter.count}" value="fb">
												</c:when>
												<c:otherwise>
													&nbsp;<img onMouseOver="showPop('fbType_info${counter.count}');" onMouseOut="hidePop('fbType_info${counter.count}');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/darkbluesquare.png" border="0" alt="info">&nbsp;
									 				<div class="text11" style="position: relative; display:inline;" align="left">
														<span style="position:absolute; left:0px; top:0px;width:200px;" id="fbType_info${counter.count}" class="popupWithInputText"  >
															<font class="text11">
										           			<b style="color:red;">Direct print out not available</b>
										           			<div>
										           			<p>Fraktbrev must be checked (and saved) in order to allow for direct print out</p>
										           			
										           			</div>
									           			</font>
														</span>
													</div>
												</c:otherwise>
												</c:choose>
												
												<span class="clazz_alinkFraktbrevPdf" id="alinkFraktbrevPdf${counter.count}" style="text-decoration: underline;" onMouseOver="style='color:lemonchiffon;cursor:pointer;text-decoration: underline;'" onMouseOut="style='color:black;text-decoration: underline;'">Fraktbrev</span>
											</td>	
											<td class="text14" align="left" >	
												<img class="clazz_imgFraktbrevPdf" id="imgFraktbrevPdf${counter.count}" title="Fraktbr.PDF" style="vertical-align:middle;cursor:pointer;" src="resources/images/pdf.png" width="14" height="14" border="0" alt="Fraktbr. PDF">
												
											</td>
				   						</tr>
				   						<tr>
											<td class="text14" align="left" >
												<c:choose>
												<c:when test="${record.hepk7 == 'C'}">
													<input type="checkbox" name="cmrType${counter.count}" id="cmrType${counter.count}" value="cmr">
												</c:when>
												<c:otherwise>
													&nbsp;<img onMouseOver="showPop('cmrType_info${counter.count}');" onMouseOut="hidePop('cmrType_info${counter.count}');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/darkbluesquare.png" border="0" alt="info">&nbsp;
									 				<div class="text11" style="position: relative; display:inline;" align="left">
														<span style="position:absolute; left:0px; top:0px;width:200px;" id="cmrType_info${counter.count}" class="popupWithInputText"  >
															<font class="text11">
										           			<b style="color:red;">Direct print out not available</b>
										           			<div>
										           			<p>CMR-Fraktbrev must be checked (and saved) in order to allow for direct print out</p>
										           			
										           			</div>
									           			</font>
														</span>
													</div>
												</c:otherwise>
												</c:choose>
												<span class="clazz_alinkCmrFraktbrevPdf" id="alinkCmrFraktbrevPdf${counter.count}" style="text-decoration: underline;" onMouseOver="style='color:lemonchiffon;cursor:pointer;text-decoration: underline;'" onMouseOut="style='color:black;text-decoration: underline;'">CMR-Fraktbrev</span>
											</td>
											<td class="text14" align="left" >	
												<img class="clazz_imgCmrFraktbrevPdf" id="imgCmrFraktbrevPdf${counter.count}" title="CMR.PDF" style="vertical-align:middle;cursor:pointer;" src="resources/images/pdf.png" width="14" height="14" border="0" alt="CMR. PDF">
											</td>
				   						</tr>
				   						<tr>
											<td class="text14" align="left" >
												<input type="checkbox" name="ffType${counter.count}" id="ffType${counter.count}" value="ff">
												<span class="clazz_alinkFFaktPdf" id="alinkFFaktPdf${counter.count}" >Ferdigmeldte-fakturaer</span>
											</td>
				   						</tr>
				   						<tr>
											<td colspan="2" class="text14" align="left" >
												<input type="checkbox" name="aordType${counter.count}" id="aordType${counter.count}" value="aord">
												<span id="alinkAordPdf" >Arbeidsordre</span>
												<c:choose>
												<c:when test="${not empty record.hepro}">
													<select class="inputTextMediumBlue" style="font-size:11px;background-color:#EEEEEE;" name="aordDocumentType${counter.count}" id="aordDocumentType${counter.count}" >
														<option value="S">Single</option>
							 							<option value="I">Intern</option>
							 							<option value="E">Ekstern</option>
													</select>
												</c:when>
												<c:otherwise>
													<input type="hidden" id="aordDocumentType${counter.count}" name="aordDocumentType${counter.count}" value="S">
												</c:otherwise>
												</c:choose>
											</td>
				   						</tr>
				   						
				   						<tr height="15"><td></td></tr>
										<tr>
											<td colspan="4" class="text14MediumBlue" align="left">
												<label id="printStatus${counter.count}"></label>
											</td>
										</tr>
									</table>
									</form>
							</div>
							
	           		   				
            		   </td>
		               <%--
		               <td width="2%" align="center" class="textMediumBlue">
	           		   		<a target="_blank" href="transportdisp_mainorderlist_renderFraktbrev.do?user=${user.user}&wsavd=${record.heavd}&wsopd=${record.heopd}&wstoll=${record.dftoll}">
  		    					<img title="Fraktbr.PDF" style="vertical-align:bottom;" src="resources/images/pdf.png" width="16" height="16" border="0" alt="Fraktbr. PDF">
   							</a>
            		   </td>
            		    --%>
            		    
					   <td width="2%" align="center" class="textMediumBlue">
		               		<a title="copy" class="copyLink" id="copyLink${counter.count}" runat="server" href="#">
								<img src="resources/images/copy.png" border="0" alt="copy">
							</a>
							<div style="display: none;" class="clazz_dialog" id="dialog${counter.count}" title="Dialog">
								<form  action="transportdisp_mainorderlist_copy_order.do" name="copyForm${counter.count}" id="copyForm${counter.count}" method="post">
								 	<input type="hidden" name="action${counter.count}" id="action${counter.count}" value='doUpdate'/>
									<input type="hidden" name="originalAvd${counter.count}" id="originalAvd${counter.count}" value='${record.heavd}'/>
				 					<input type="hidden" name="originalOpd${counter.count}" id="originalOpd${counter.count}" value='${record.heopd}'/>
					 				<input type="hidden" name="sign${counter.count}" id="sign${counter.count}" value='${user.signatur}'/>
					 				
									<p class="text14" >Du kan velge en ny avdeling</p>
									<p class="text14" >En ny oppdrag vil bli etablert automatisk.</p>
									
									<table>
										<tr>
											<td class="text14" align="left" >Ny Avd.</td>
											<td class="text14MediumBlue">
												<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue newAvd" name="newAvd${counter.count}" id="newAvd${counter.count}" size="5" maxlength="4" value="${record.heavd}">
											</td>
                						</tr>
									</table>
								</form>
							</div>
							
		               </td>
		               
		               <td width="2%" align="center" class="textMediumBlue">
		               		<a title="move" class="moveLink" id="moveLink${counter.count}" runat="server" href="#">
								<img src="resources/images/move.png" width="18" height="18" border="0" alt="move">
							</a>
							<div style="display: none;" class="clazz_dialogMove" id="dialogMove${counter.count}" title="Dialog">
								<form  action="transportdisp_mainorderlist_move_order.do" name="moveForm${counter.count}" id="moveForm${counter.count}" method="post">
								 	<input type="hidden" name="action${counter.count}" id="action${counter.count}" value='doUpdate'/>
									<input type="hidden" name="originalAvd${counter.count}" id="originalAvd${counter.count}" value='${record.heavd}'/>
				 					<input type="hidden" name="originalOpd${counter.count}" id="originalOpd${counter.count}" value='${record.heopd}'/>
					 				
									<p class="text14" >Du må velge en ny avdeling</p>
									<p class="text14" >Denne oppdrag vil bli flyttet.</p>
									
									<table>
										<tr>
											<td class="text14" align="left" ><b>Ny Avd.</b></td>
											<td class="text14MediumBlue">
												<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue newAvdMove" name="newAvdMove${counter.count}" id="newAvdMove${counter.count}" size="5" maxlength="4" value="">
											</td>
                						</tr>
									</table>
								</form>
							</div>
		               </td>
		               
		               <td width="2%" align="center" class="textMediumBlue">
            		  	 	<a id="avd_${record.heavd}@opd_${record.heopd}" title="delete" onClick="doPermanentlyDeleteOrder(this)" tabindex=-1>
			               		<img src="resources/images/delete.gif" border="0" alt="remove">
			               	</a>&nbsp;
			               	
					   </td>
					   <td width="2%" align="center" class="text14RedBold">&nbsp;${record.hestn7}</td>
		               			               	
		            </tr> 
		            </c:forEach>
		            </tbody>
		            
	            </table>
				</td>	
				</tr>
				
				 
				
				<tr>
            		<td align="right" class="text14text14">
            		<table >
						<tr>
						<td>	
							<a href="transportDispWorkflowOpenOrdersListExcelView.do" target="_new">
			                		<img valign="bottom" id="openOrdersListExcel" src="resources/images/excel.gif" width="14" height="14" border="0" alt="excel">
			                		<font class="text14MediumBlue">&nbsp;Eksport til Excel</font>
			 	        		</a>
			 	        		&nbsp;
		 	        		</td>
		 	        		<%--TODO
		 	        		<td>		            		
			 	        		<a href="todo.do" target="_new">
			                		<img valign="bottom" id="printer" src="resources/images/printer.png" width="14" height="14" border="0" alt="print">
			                		<font class="text12MediumBlue">&nbsp;Print</font>
			 	        		</a>
			 	        		&nbsp;
		 	        		</td>
		 	        		 --%>
		 	        		<td class="text12" width="15px">&nbsp;</td>
	 	        		</tr>
 	        		</table>
			 		</td>
	         	</tr>
				</table>
				</td>
			</tr>
			
			
			</table>
		</td>
		</tr>
		
		
		<%-- ---------------- --%>
		<%-- DIALOG SMS		  --%>
		<%-- ---------------- --%>
		<tr>
		<td>
			<div id="dialogSMS" title="Dialog">
				 	<table>
				 		<tr>
							<td colspan="3" class="text14" align="left" >Send SMS med lenke til TKeventGrabber</td>
   						</tr>
						<tr height="10"><td></td></tr>
   						<tr>
							<td class="text14" align="left" ><b>SMS-nummer</b>&nbsp;</td>
							<td class="text14" align="left" >
							<input type="text" class="inputText" onKeyPress="return numberKey(event)" id="smsnr" name="smsnr" size="20" maxlength="15" value=''>
							</td>
   						</tr>
						<tr>
   							<td class="text14" align="left" >Språk&nbsp;</td>
							<td class="text14" align="left" >
		   						<select class="inputTextMediumBlue" name="smslang" id="smslang">
		 							<option value="NO" selected>Norsk</option>
		 							<option value="EN" >Engelsk</option>
								</select>
							</td>
						</tr>

						<tr height="10"><td></td></tr>
						<tr>
							<td colspan="3" class="text14MediumBlue" align="left">
								<label id="smsStatus"></label>
							</td>
						</tr>
						
						
					</table>
			</div>
		</td>
		</tr>
		
		
		
</table>	
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

