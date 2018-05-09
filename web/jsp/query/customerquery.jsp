<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<TITLE>综合信息查询</TITLE>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
	<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
		  rel=stylesheet>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.4.min.js"></script>
	<SCRIPT language=javascript>
        function to_page(page){
            if(page){
                $("#page").val(page);
            }
            document.customerForm.submit();

        }
	</SCRIPT>

	<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
<FORM id="customerForm" name="customerForm"
	  action="${pageContext.request.contextPath }/customer_query.action"
	  method=post>

	<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
		<TBODY>
		<TR>
			<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_019.jpg"
							  border=0></TD>
			<TD width="100%" background="${pageContext.request.contextPath }/images/new_020.jpg"
				height=20></TD>
			<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_021.jpg"
							  border=0></TD>
		</TR>
		</TBODY>
	</TABLE>
	<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
		<TBODY>
		<TR>
			<TD width=15 background=${pageContext.request.contextPath }/images/new_022.jpg><IMG
					src="${pageContext.request.contextPath }/images/new_022.jpg" border=0></TD>
			<TD vAlign=top width="100%" bgColor=#ffffff>
				<TABLE cellSpacing=0 cellPadding=5 width="100%" border=0>
					<TR>
						<TD class=manageHead>当前位置：综合信息查询 &gt; 客户查询</TD>
					</TR>
					<TR>
						<TD height=2></TD>
					</TR>
				</TABLE>
				<TABLE borderColor=#cccccc cellSpacing=0 cellPadding=0
					   width="100%" align=center border=0>
					<TBODY>
					<TR>
						<TD height=25>
							<TABLE cellSpacing=0 cellPadding=2 border=0>
								<TBODY>
								<TR>
									<TD>客户名称：</TD>
									<TD><INPUT class=textbox id=sChannel2
											   style="WIDTH: 80px" maxLength=50 name="custName"></TD>
								</TR>
<%--								<tr>
									<TD>客户级别：</TD>
									<TD>
										<select name="custLevel">
											<option value="null">--请选择--</option>
											<c:forEach items="${levelList}" var="cust">
												<option value="${cust.custLevel}">${cust.custLevel}</option>
											</c:forEach>
										</select>
									</TD>
								</tr>--%>
								<tr>
									<TD>客户来源：</TD>
									<TD>
										<select name="custSource">
											<option value="">--请选择--</option>
											<c:forEach items="${sourceList}" var="customer">
												<option value="${customer.custSource}">${customer.custSource}</option>
											</c:forEach>
										</select>
									</TD>
								</tr>
								<tr>
									<TD>地址：</TD>
									<TD><INPUT class=textbox id=sChannel2
											   style="WIDTH: 80px" maxLength=50 name="custAddress"></TD>
								</tr>
								<TR>
									<TD><INPUT class=button id=sButton2 type=submit
											   value="查询" name=sButton2></TD>
								</TR>
								</TBODY>
							</TABLE>
						</TD>
					</TR>
					</TBODY>
				</TABLE>
			</TD>
			<TD width=15 background="${pageContext.request.contextPath }/images/new_023.jpg"><IMG
					src="${pageContext.request.contextPath }/images/new_023.jpg" border=0></TD>
		</TR>
		</TBODY>
	</TABLE>
	<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
		<TBODY>
		<TR>
			<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_024.jpg"
							  border=0></TD>
			<TD align=middle width="100%"
				background="${pageContext.request.contextPath }/images/new_025.jpg" height=15></TD>
			<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_026.jpg"
							  border=0></TD>
		</TR>
		</TBODY>
	</TABLE>
</FORM>
</BODY>
</HTML>
