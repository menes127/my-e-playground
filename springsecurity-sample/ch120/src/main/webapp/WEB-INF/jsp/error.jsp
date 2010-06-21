<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<h3>An Error Occurred</h3>
message:
<c:out value="${exception.message}" />
<a href="<portlet:renderURL portletMode="view" windowState="normal"/>">-
Home -</a>