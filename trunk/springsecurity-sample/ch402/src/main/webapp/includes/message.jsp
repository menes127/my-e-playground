<c:if test="${not empty sessionScope.message}">
  <div style="border:1px solid gray;background-color:green;font-weight:bold;color:white;text-align:center;">${sessionScope.message}</div>
  <c:remove var="message" scope="session"/>
</c:if>