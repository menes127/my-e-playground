<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
The initialize time of request scope testBean:
<c:out value="${requestTestBean.initializeTime}" />
<br />
The initialize time of session scope testBean:
<c:out value="${sessionTestBean.initializeTime}" />
<br />
The initialize time of global session scope testBean:
<c:out value="${globalSessionTestBean.initializeTime}" />