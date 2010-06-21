<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<table>
	<tr>
		<td>Name:</td>
		<td><c:out value="${addressBook.name}" /></td>
	</tr>
	<tr>
		<td>Address:</td>
		<td><c:out value="${addressBook.address}" /></td>
	</tr>
	<tr>
		<td>Telphone:</td>
		<td><c:out value="${addressBook.telphone}" /></td>
	</tr>
	<tr>
		<td>Mobile:</td>
		<td><c:out value="${addressBook.mobile}" /></td>
	</tr>
	<tr>
		<td>Email:</td>
		<td><c:out value="${addressBook.email}" /></td>
	</tr>
	<tr>
		<td colspan="2"><a
			href="<portlet:renderURL portletMode="view" windowState="normal"/>">-
		Home -</a></td>
	</tr>
</table>