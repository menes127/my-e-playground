<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>
<html>
  <head>
    <%@ include file="/includes/meta.jsp"%>
    <title>list</title>
  </head>
  <body>
    <%@include file="/includes/header-group.jsp"%>
    <%@include file="/includes/message.jsp"%>
    <table border="1" width="100%">
      <tr>
        <th>Group Name</th>
        <th>Members</th>
        <th>Authorities</th>
        <th>Operation</th>
      </tr>
<c:forEach var="item" items="${list}">
      <tr>
        <td>${item.name}&nbsp;</td>
        <td>${item.member}&nbsp;</td>
        <td>${item.authority}&nbsp;</td>
        <td>
          <a href="group.do?action=view&name=${item.name}">View</a>
          |
          <a href="group.do?action=edit&name=${item.name}">Update</a>
          |
          <a href="group.do?action=remove&name=${item.name}">Remove</a>
        </td>
      </tr>
</c:forEach>
    </table>
  </body>
</html>