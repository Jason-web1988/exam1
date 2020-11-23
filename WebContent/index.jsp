<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%
   String pp = request.getParameter("pp");
   if(pp == null)
   {   
      pp = "join";
   }
%>
 --%>
 
<c:choose>
   <c:when test="${empty param.pp }">
      <c:set var="status" value="join" />
   </c:when>
   <c:otherwise>
      <c:set var="status" value="${param.pp }" />
   </c:otherwise>
</c:choose>


<jsp:include page="include/top.jsp"/>
<jsp:include page="include/nav.jsp"/>

<%--<jsp:include page='<%=pp + ".jsp" %>'/> --%>

    <article>
      <jsp:include page="${status }.jsp"/>
   </article> 
<jsp:include page="include/footer.jsp"/>