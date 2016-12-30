<form action="#" th:action="@{/filter}" th:object="${filter}" method="post">
    <p>Id: <input type="text" th:field="*{id}" />Message: <input type="text" th:th:field="*{content}" /><input type="submit" value="Submit" /></p>
</form>