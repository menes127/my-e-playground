<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<portlet:actionURL var="actionURL" />
<form:form commandName="addressBook" action="${actionURL}">
	<table>
		<tr>
			<td>Name:<c:out value="${addressBook.name}" /></td>
			<td><form:input path="name" /></td>
		</tr>
		<tr>
			<td>Address:</td>
			<td><form:input path="address" /></td>
		</tr>
		<tr>
			<td>Telphone:</td>
			<td><form:input path="telphone" /></td>
		</tr>
		<tr>
			<td>Mobile:</td>
			<td><form:input path="mobile" /></td>
		</tr>
		<tr>
			<td>Email:</td>
			<td><form:input path="email" /></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="submit" /></td>
		</tr>
	</table>
</form:form>
