<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/views/common/taglibIncludes.jsp" %>

<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs/jq-2.2.4/dt-1.10.13/af-2.1.3/b-1.2.4/b-colvis-1.2.4/b-html5-1.2.4/cr-1.3.2/r-2.1.0/rr-1.2.0/se-1.2.0/datatables.min.css"/>
<script type="text/javascript" src="https://cdn.datatables.net/v/bs/jq-2.2.4/dt-1.10.13/af-2.1.3/b-1.2.4/b-colvis-1.2.4/b-html5-1.2.4/cr-1.3.2/r-2.1.0/rr-1.2.0/se-1.2.0/datatables.min.js"></script>

<body>
    <div class="container">
        <div class="starter-template">
            <h1>Bootstrap starter template</h1>
            <p class="lead">Use this document as a way to quickly start any new project.<br> All you get is this text and a mostly barebones HTML document.</p>
        </div>


        <label class="glyphicon glyphicon-expand collapsable"><span>Filters</span></label>
        <jsp:include page="/WEB-INF/views/partial/filters.jsp" />

        <br>

        <label class="glyphicon glyphicon-expand collapsable"><span>Columns</span></label>
        <jsp:include page="/WEB-INF/views/partial/visibility.jsp" />

        <div>
            <table id="stocks" class="table table-striped table-bordered table-hover dt-responsive nowrap" cellspacing="0" width="100%">
                <thead>
                    <tr>
                        <th>Symbol</th>
                        <th>Name</th>
                        <th>Quote</th>
                        <th>Currency</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="stock" items="${stocks}">
                    <tr>
                        <td>${stock.getSymbol()}</td>
                        <td>${stock.getName()}</td>
                        <td>${stock.getQuote().getPrice()}</td>
                        <td>${stock.getCurrency()}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>