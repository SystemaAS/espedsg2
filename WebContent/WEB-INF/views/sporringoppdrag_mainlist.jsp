<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<c:choose>           
     <c:when test="${not empty cw}">
		<!-- ======================= header ===========================-->
		<jsp:include page="/WEB-INF/views/headerSporringOppdragChildWindow.jsp" />
		<!-- =====================end header ==========================-->
     </c:when>
     <c:otherwise>   
		<!-- ======================= header ===========================-->
		<jsp:include page="/WEB-INF/views/headerSporringOppdrag.jsp" />
		<!-- =====================end header ==========================-->
     </c:otherwise>
 </c:choose>
 
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/sporringoppdragglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/sporringoppdrag_mainlist.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
	<style type = "text/css">
	.ui-datepicker { font-size:9pt;}
	</style>

<table width="100%" class="text11" cellspacing="0" border="0" cellpadding="0">
	<tr>
	<td>
	<%-- tab container component --%>
	<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25"> 
			<td width="20%" valign="bottom" class="tab" align="center" nowrap>
				<font class="tabLink">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.tab"/></font>
				<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
			</td>
			<%--
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="10%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a style="display:block;" id="mainList" href="sporringoppdrag_mainlist_filter.do?action=doShow&fs=1">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.filter.tab"/></font>
					<img valign="bottom" src="resources/images/find.png" border="0" alt="general list filter">
				</a>
			</td>
			 --%>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="80%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>	
		</tr>
	</table>
	</td>
	</tr>
		<tr>
		<td>
			<%-- this table wrapper is necessary to apply the css class with the thin border --%>
			<table id="wrapperTable" class="tabThinBorderWhite" width="100%" cellspacing="1">
			<tr height="10"><td></td></tr> 
			<%-- ----------------- --%>
			<%-- SEARCH FORM BEGIN --%>
			<%-- ----------------- --%>	
			<tr>
				<td>
				<form name="sporringOppdragSearchForm" id="searchForm" action="sporringoppdrag_mainlist.do?action=doFind" method="post" >
				<table style="width:100%" border="0" cellspacing="0" cellpadding="0">
				<tr height="5"><td></td></tr>
			    <%-- FORM HEADER --%>
				<tr>
					<td width="5">&nbsp;</td>
		       		<td>
		       		<table style="width:99%;" align="left" class="formFrameHeader" cellspacing="0" cellpadding="0">
			 		<tr height="15">
			 			<td class="text12White">
							&nbsp;&nbsp;&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.form.search.label.title"/>&nbsp;
							<img valign="bottom" src="resources/images/find.png" width="12px" height="12px" border="0" alt="edit">
		 				</td>
						</tr>
					</table>
		        		</td>
		        </tr>
		        <%-- FORM DETAIL --%>
			    <tr>
			    		<td width="5">&nbsp;</td>
		        		<td>
		    			<table style="width:99%;" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
			 		<tr height="10"><td >&nbsp;</td></tr>
			 		
			 		<tr>
						<td colspan="2" width="100%" valign="top">
							<table style="width:99%" cellspacing="1" cellpadding="0">
					 			<tr height="20">
						 			<td class="text14">
							 			<img style="vertical-align: bottom" src="resources/images/appUserOg.gif" width="18" hight="18" border="0" alt="customer">
							 			<b><spring:message code="systema.sporringoppdrag.mainlist.topic.header.customerName"/></b>
							 			<font class="text14">&nbsp;${model.container.knavn}&nbsp;&nbsp;/&nbsp;&nbsp;</font>
							 			<b>Logged on:</b><font class="text14">&nbsp;${user.user}</font>
						 			</td>
						    		</tr>
					 		</table>
						</td>
					</tr>	
			 		
			 		<tr>
			 			<td width="50%" valign="top" >
			 			 	<table style="width:98%" class="tableBorderWithRoundCornersGray" cellspacing="1" cellpadding="0">
					 		<tr height="5"><td></td></tr>
					 		<tr>
						    		<td class="text11" title="wsdtf">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.form.search.label.fromDate"/></td>
						    		<td class="text11" ><input type="text" class="inputTextMediumBlue" name="wsdtf" id="wsdtf" size="9" maxlength="8" value="${searchFilter.wsdtf}"></td>
						    		<td class="text11" title="wsdtt">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.form.search.label.toDate"/></td>
						    		<td class="text11"><input type="text" class="inputTextMediumBlue" name="wsdtt" id="wsdtt" size="9" maxlength="8" value="${searchFilter.wsdtt}"></td>
						    	</tr>
									    	
						    	<tr>
						    		<td class="text11" title="wsrfa">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.form.search.label.oppGivRef"/></td>
						    		<td class="text11"><input type="text" class="inputTextMediumBlue" name="wsrfa" id="wsrfa" size="15" maxlength="15" value="${searchFilter.wsrfa}"></td>
						    		<td class="text11" title="wsrfk">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.form.search.label.mottRef"/></td>
						    		<td class="text11"><input type="text" class="inputTextMediumBlue" name="wsrfk" id="wsrfk" size="15" maxlength="15" value="${searchFilter.wsrfk}"></td>
						    		
						    	</tr>
						    	<tr>	
						    		<td class="text11" title="wsavd/wsopd" nowrap>&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.form.search.label.oppRef.avdOppNr"/></td>
						    		<td class="text11">
						    			<input type="text" class="inputTextMediumBlue" name="wsavd" id="wsavd" size="5" maxlength="4" value="${searchFilter.wsavd}">
						    			&nbsp;<input type="text" class="inputTextMediumBlue" name="wsopd" id="wsopd" size="7" maxlength="7" value="${searchFilter.wsopd}">
						    		</td>
						    	</tr>
						    	<tr>
						    		<td class="text11" title="wsfn">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.form.search.label.fakturaNr"/></td>
						    		<td class="text11"><input type="text" class="inputTextMediumBlue" name="wsfn" id="wsfn" size="8" maxlength="7" value="${searchFilter.wsfn}"></td>
						    		<td class="text11" title="wsgn">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.form.search.label.godsNr"/></td>
						    		<td class="text11"><input type="text" class="inputTextMediumBlue" name="wsgn" id="wsgn" size="15" maxlength="15" value="${searchFilter.wsgn}"></td>
						    	</tr>
						    <tr>
						    		<td class="text11" title="wsawbn">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.form.search.label.awbNr"/></td>
						    		<td class="text11"><input type="text" class="inputTextMediumBlue" name="wsawbn" id="wsawbn" size="20" maxlength="15" value="${searchFilter.wsawbn}"></td>
						    	</tr>
						    	<tr>
						    		<td class="text11" title="wshawb">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.form.search.label.hawbNr"/></td>
						    		<td class="text11"><input type="text" class="inputTextMediumBlue" name="wshawb" id="wshawb" size="9" maxlength="9" value="${searchFilter.wshawb}"></td>
						    		<td class="text11" title="wsdthawb">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.form.search.label.hawbNr.date"/></td>
						    		<td class="text11"><input type="text" class="inputTextMediumBlue" name="wsdthawb" id="wsdthawb" size="9" maxlength="8" value="${searchFilter.wsdthawb}"></td>
						    	</tr>
						    	<tr>
						    		<td class="text11" title="wsot">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.form.search.label.oppType"/></td>
						    		<td class="text11" >
						    			<%--
						    			<select name="wsot" id="wsot">
			            					<option value="">-velg-</option>
			 				  			<c:forEach var="record" items="${model.TODOList}" >
		                             		<option value="${record.TODO}"<c:if test="${model.record.wsot == record.TODO}"> selected </c:if> >${record.TODO}</option>
										</c:forEach> 
									</select>
									 --%>
									<select name="wsot" id="wsot">
							         <option value=""><spring:message code="systema.sporringoppdrag.mainlist.form.search.label.select"/>
							         <option value="IF" <c:if test="${searchFilter.wsot == 'IF'}"> selected </c:if> >IMPORT FLYTENDE TANKGODS
							         <option value="FT" <c:if test="${searchFilter.wsot == 'FT'}"> selected </c:if> >FORTOLLING GENERELL
							         <option value="MS" <c:if test="${searchFilter.wsot == 'MS'}"> selected </c:if> >ANKOMSTMELDING
							        </select>
					    			</td>
					    			<td class="text11" nowrap title="wsdtot">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.form.search.label.oppDate"/></td>
						    		<td class="text11" ><input type="text" class="inputTextMediumBlue" name="wsdtot" id="wsdtot" size="9" maxlength="8" value="${searchFilter.wsdtot}"></td>
						    	</tr>
						    	
						    	<tr height="5"><td ></td></tr>
						 	</table>	
		 				</td>
		 				<td width="50%" valign="top">
			 			 	<table style="width:98%"  class="tableBorderWithRoundCornersGray" cellspacing="1" cellpadding="0">
					 		<tr height="5"><td></td></tr>
						    <tr>
						    		<td class="text12" nowrap title="ownFraktbrevsNr">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.form.search.label.fraktbrevsnr"/></td>
						    		<td class="text11" colspan="3"><input type="text" class="inputTextMediumBlue" name="ownFraktbrevsNr" id="ownFraktbrevsNr" size="35" maxlength="35" value="${searchFilter.ownFraktbrevsNr}"></td>
					    	</tr>
					    	<tr height="1"><td></td></tr>
						    <tr>
						    		<td class="text11" nowrap title="wsmrk1">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.form.search.label.godsmarke"/></td>
						    		<td class="text11" colspan="3"><input type="text" class="inputTextMediumBlue" name="wsmrk1" id="wsmrk1" size="35" maxlength="35" value="${searchFilter.wsmrk1}"></td>
					    	</tr>
						    	
					    		<tr>
						    		<td class="text11" nowrap title="fscd/wsfri2">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.form.search.label.andreRef"/></td>
						    		<td class="text11" colspan="2">
						    			<%-- 99 values
						    			<select name="fscd" id="fscd">
			            					<option value="">-velg-</option>
			 				  			<c:forEach var="record" items="${model.TODOList}" >
		                             		<option value="${record.TODO}"<c:if test="${model.record.fscd == record.TODO}"> selected </c:if> >${record.TODO}</option>
										</c:forEach> 
									</select> 
						         	--%>
						         	<select name="fscd" id="fscd">
							        	<option value=""><spring:message code="systema.sporringoppdrag.mainlist.form.search.label.select"/>
							        	<%-- <option value="IFB" <c:if test="${searchFilter.fscd == 'IFB'}"> selected </c:if> >Fraktbrevref. --%>	
							         	<option value="TLØ" <c:if test="${searchFilter.fscd == 'TLØ'}"> selected </c:if> >Sad/Tvin løpenr (hele el deler eks 2010027  )
							         	<option value="ORD" <c:if test="${searchFilter.fscd == 'ORD'}"> selected </c:if> >Ordrenummer
						         	</select> 
						         	
					    			</td>
						    	</tr>
						    	<tr>
						    		<td class="text11" nowrap>&nbsp;</td>
						    		<td class="text11" colspan="2"><input type="text" class="inputTextMediumBlue" name="wsfri2" id="wsfri2" size="35" maxlength="35" value="${searchFilter.wsfri2}"></td>
						    	</tr>
						    	<tr>
						    		<td class="text11" nowrap title="wsdtfs/wsdtfst">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.form.search.label.andreRef.date"/></td>
						    		<td class="text11" colspan="2">
						    			<input type="text" class="inputTextMediumBlue" name="wsdtfs" id="wsdtfs" size="9" maxlength="8" value="${searchFilter.wsdtfs}">
						    			&nbsp;<input type="text" class="inputTextMediumBlue" name="wsdtfst" id="wsdtfst" size="9" maxlength="8" value="${searchFilter.wsdtfst}">
						    		</td>
						    	</tr>
						    	
					    		<tr>
						    		<td class="text11" nowrap title="wsblnr">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.form.search.label.blnr"/></td>
						    		<td class="text11" colspan="2">
						    			<input type="text" class="inputTextMediumBlue" name="wsblnr" id="wsblnr" size="16" maxlength="16" value="${searchFilter.wsblnr}">
						    		</td>
						    	</tr>
						    	<tr>
						    		<td class="text11" nowrap title="wsblcn">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.form.search.label.containerNr"/></td>
						    		<td class="text11" colspan="2">
						    			<input type="text" class="inputTextMediumBlue" name="wsblcn" id="wsblcn" size="17" maxlength="17" value="${searchFilter.wsblcn}">
						    		</td>
						    	</tr>
						    <tr height="10"><td></td></tr>							    
						 	</table>	
		 				</td>
					</tr>
					<tr height="2"><td></td></tr>	
					<tr>
						<td>&nbsp;</td>
						<td align="right">
							&nbsp;<input class="inputFormSubmit" type="submit" name="submit" value='<spring:message code="systema.sporringoppdrag.search"/>'>
			                   <img src="resources/images/find2.png" width="14px" height="14px" border="0" alt="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		          		</td>
					</tr>
					<tr height="1"><td colspan="2" style="border-bottom:1px solid;border-color:#DDDDDD;" class="text">&nbsp;</td></tr>
					</table>
		       		</td>
		          </tr>
		          <tr height="15"><td></td></tr>
		         </table>
		         </form>
		         </td>
		    </tr>
		    <%-- --------------- --%>
			<%-- SEARCH FORM END --%>
			<%-- --------------- --%>	
		
			<%-- Validation errors --%>
			<spring:hasBindErrors name="record"> <%-- name must equal the command object name in the Controller --%>
			<tr>
				<td>
		           	<table style="width:99%" align="left" border="0" cellspacing="0" cellpadding="0">
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
			<%-- -------------------- --%>
			<%-- Datatables component --%>
			<%-- -------------------- --%>
			
			<%-- list component --%>
			<c:if test="${not empty list}">
			<tr>
            <tr>
				<td>
				<%-- this container table is necessary in order to separate the datatables element and the frame above, otherwise
					 the cosmetic frame will not follow the whole datatable grid including the search field... --%>
				<table id="containerdatatableTable" style="width:100%" cellspacing="3" align="left">
				<tr>
				<td class="ownScrollableSubWindow" style="width:100%; height:60em;">
				<%-- this is the datatables grid (content) --%>
				<table id="oppdragMainList" class="display compact cell-border">
					<thead>
					<tr style="background-color:#EEEEEE">
						<c:if test="${not empty cw}">
							<th width="5%" class="text12">Hente Ordre</th>
						</c:if>
					    <th class="text12">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.column.label.avdOppdr"/>&nbsp;</th>   
	                    <th class="text12">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.column.label.turNr"/>&nbsp;</th>
	                    <th class="text12">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.column.label.oppdgRef"/>&nbsp;</th>
	                    <th class="text12">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.column.label.dato"/>&nbsp;</th>
	                    <th class="text12">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.column.label.fra"/>&nbsp;</th>
	                    <th class="text12">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.column.label.til"/>&nbsp;</th>
	                    <th class="text12">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.column.label.avsender"/>&nbsp;</th>
	                    <th class="text12">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.column.label.mottaker"/>&nbsp;</th>
	                    <th class="text12">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.column.label.prKD"/>&nbsp;</th>
	                    <th class="text12">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.column.label.antall"/>&nbsp;</th>
	                    <th class="text12">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.column.label.vekt"/>&nbsp;</th>

	                    <th class="text12">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.column.label.kubikk"/>&nbsp;</th>
	                    <th class="text12">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.column.label.lm"/>&nbsp;</th>
	                    <th class="text12">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.column.label.levert"/>&nbsp;</th>
	                    <c:if test="${model.container.visFaktSum=='J'}">
	                    	<th class="text12">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.column.label.faktsum"/>&nbsp;</th>
	                    </c:if>
	                </tr> 
	                </thead>
	                
	                <tbody>
		            <c:forEach items="${list}" var="topic" varStatus="counter">    
		               <c:choose>           
		                   <c:when test="${counter.count%2==0}">
		                       <tr >
		                   </c:when>
		                   <c:otherwise>   
		                       <tr >
		                   </c:otherwise>
		               </c:choose>
		               <c:if test="${not empty cw}">
            		   		<td align="center" id="cw${topic.heavd}_${topic.heopd}" class="text11" style="cursor:pointer;" >
            		   			<img title="Add" style="vertical-align:bottom;" src="resources/images/addOrder.png" width="14" height="15" border="0" alt="add">
            		   		</td>
            		   </c:if>
		               <td class="text11">
		               		<a href="sporringoppdrag_show.do?action=doShow&knavn=${model.container.knavn}&heavd=${topic.heavd}&heopd=${topic.heopd}">${topic.heavd}_${topic.heopd}</a> 
            		   </td>
            		   <td class="text11">&nbsp;${topic.hepro}</td>
            		   <td class="text11">&nbsp;${topic.xwsref}</td>
            		   <td class="text11">&nbsp;${topic.hedtop}</td>
            		   <td class="text11">&nbsp;${topic.hesdf}</td>
		               <td class="text11">&nbsp;${topic.hesdt}</td>
            		   <td class="text11">&nbsp;${topic.henas}</td>
		               <td class="text11">&nbsp;${topic.henak}</td>
		               <td class="text11">&nbsp;${topic.hekdpl}</td>
		               <td class="text11">&nbsp;${topic.hent}</td>
		               <td class="text11">&nbsp;${topic.hevkt}</td>
		               <td class="text11">&nbsp;${topic.hem3}</td>
		               <td class="text11">&nbsp;${topic.helm}</td>
		               <td class="text11">&nbsp;${topic.poddato}</td>
		               <c:if test="${model.container.visFaktSum=='J'}">
	                    	<td class="text12">&nbsp;${topic.faktsum}</td>
	                    </c:if>
		            </tr> 
		            </c:forEach>
		            <tfoot>
					<tr style="background-color:#EEEEEE">
						<c:if test="${not empty cw}">
							<th width="5%" class="text12">Hente Ordre</th>
						</c:if>
					    <th class="text12">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.column.label.avdOppdr"/>&nbsp;</th>   
	                    <th class="text12">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.column.label.turNr"/>&nbsp;</th>
	                    <th class="text12">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.column.label.oppdgRef"/>&nbsp;</th>
	                    <th class="text12">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.column.label.dato"/>&nbsp;</th>
	                    <th class="text12">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.column.label.fra"/>&nbsp;</th>
	                    <th class="text12">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.column.label.til"/>&nbsp;</th>
	                    <th class="text12">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.column.label.avsender"/>&nbsp;</th>
	                    <th class="text12">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.column.label.mottaker"/>&nbsp;</th>
	                    <th class="text12">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.column.label.prKD"/>&nbsp;</th>
	                    <th class="text12">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.column.label.antall"/>&nbsp;</th>
	                    <th class="text12">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.column.label.vekt"/>&nbsp;</th>

	                    <th class="text12">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.column.label.kubikk"/>&nbsp;</th>
	                    <th class="text12">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.column.label.lm"/>&nbsp;</th>
	                    <th class="text12">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.column.label.levert"/>&nbsp;</th>
	                    <c:if test="${model.container.visFaktSum=='J'}">
	                    	<th class="text12">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.column.label.faktsum"/>&nbsp;</th>
	                    </c:if>
	                </tr> 
	                </tfoot>
		            </tbody>
	            </table>
	            </td>
            		</tr>
            		<tr height="3"><td></td></tr>
            		<tr>
            		<td colspan="4" class="text12">
	            		<table align="left" class="tabThinBorderWhite">
					<tr>
						<td>	
							<a href="sporringOppdragMainListExcelView.do" target="_new">
			                		<img valign="bottom" id="mainListExcel" src="resources/images/excel.gif" width="14" height="14" border="0" alt="excel">
			                		<font class="text12MediumBlue">&nbsp;Excel</font>
			 	        		</a>
			 	        		&nbsp;
		 	        		</td>
		 	        		<td>		            		
			 	        		<a href="todo.do" target="_new">
			                		<img valign="bottom" id="printer" src="resources/images/printer.png" width="14" height="14" border="0" alt="print">
			                		<font class="text12MediumBlue">&nbsp;Print</font>
			 	        		</a>
			 	        		&nbsp;
		 	        		</td>
	 	        		</tr>
	 	        		</table>
		 		</td>
	         	</tr>
            		</table> <%--containerdatatableTable END --%>
            		</td>
            </tr>
			</c:if>
			</table> <%--wrapperTable END --%>
         </td>
         </tr>
         <tr height="10"><td>&nbsp;</td></tr>
</table>	
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

