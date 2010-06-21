<table width="100%">
  <tr>
<c:if test="${param.action == 'list'}">
    <td><a href="group.do?action=create">Create Group</a></td>
</c:if>
<c:if test="${param.action != 'list'}">
    <td><a href="group.do?action=list">Back</a></td>
</c:if>
    <td align="right">
      <a href="user.do?action=list" style="padding:5px;background-color:gray;color:white;font-weight:bold;">Manage User</a>
      |
      username: <sec:authentication property="name"/>
      |
      <a href="user.do?action=preChangePassword">Change Password</a>
      |
      <a href="j_spring_security_logout">Logout</a>
    </td>
  </tr>
</table>
<hr>