<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div style="display: none;" id="filters" class="toggled">
    <form action="#" th:action="@{/filter}" th:object="${filter}" method="post">
        <ul class="dropdown-menu">
            <c:forEach var="filterType" items="${filterTypes}">
                <li><a href="#">${filterType}</a></li>
            </c:forEach>
        </ul>

        Id: <input type="text" th:field="*{id}" />
        Message: <input type="text" th:th:th:field="*{content}" />
        <input type="submit" value="Submit" />
    </form>
</div>