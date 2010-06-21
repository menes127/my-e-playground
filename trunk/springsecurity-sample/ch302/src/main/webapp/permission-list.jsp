<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
    <title>list</title>
  </head>
  <body>
    <table width="100%">
      <tr>
        <td><a href="permission.do?action=create&clz=${param.clz}&id=${param.id}">Add Permission</a></td>
        <td align="right">
          username: <sec:authentication property="name"/>
          |
          <a href="j_spring_security_logout">logout</a>
        </td>
      </tr>
    </table>
    <hr>
    <table border="1" width="100%">
      <tr>
        <th>ace</th>
        <th>delete</th>
      </tr>
<c:forEach var="item" items="${acl.entries}">
      <tr>
        <td>${item.sid.principal} | ${item.permission.mask}&nbsp;</td>
        <td><a href="permission.do?action=remove&clz=${param.clz}&id=${param.id}&sid=${item.sid.principal}&permission=${item.permission.mask}">delete</a>&nbsp;</td>
      </tr>
</c:forEach>
    </table>
  </body>
</html>