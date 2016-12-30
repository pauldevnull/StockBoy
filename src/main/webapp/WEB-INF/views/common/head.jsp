<%@ include file="/WEB-INF/views/common/taglibIncludes.jsp" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<tiles:importAttribute name="pageTitle" />
<tiles:importAttribute name="javascriptsAsync"/>
<tiles:importAttribute name="stylesheets"/>
<title>${pageTitle}</title>

    <%--css--%>
    <c:forEach var="css" items="${stylesheets}">
        <link rel="stylesheet" type="text/css" href="<c:url value="${css}"/>">
    </c:forEach>
    <%--end css--%>

    <%--async javascript--%>
    <c:forEach var="script" items="${javascriptsAsync}">
        <script src="<c:url value="${script}"/>"async></script>
    </c:forEach>
    <%--end async javascript--%>
</head>