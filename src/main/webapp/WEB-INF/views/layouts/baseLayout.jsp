<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ include file="/WEB-INF/views/common/taglibIncludes.jsp" %>
<html>
<tiles:insertAttribute name="head" />
<tiles:importAttribute name="javascripts"/>
    <body>
        <tiles:insertAttribute name="topbar" />
        <tiles:insertAttribute name="body" />
        <tiles:insertAttribute name="jsIncludes" />
        <%--regular javascript--%>
        <c:forEach var="script" items="${javascripts}">
            <script src="<c:url value="${script}"/>" ></script>
        </c:forEach>
        <%--end javascript--%>
    </body>
</html>