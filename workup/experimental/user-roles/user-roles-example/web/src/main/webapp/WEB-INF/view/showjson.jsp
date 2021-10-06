<%-- 
    Document   : content
    Created on : Jan 4, 2020, 11:19:47 AM
    Author     : cgallen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
// request set in controller
//    request.setAttribute("selectedPage","home");
%>
<jsp:include page="header.jsp" />
<!-- Begin page content -->
<script>
    function sendTestData() {
        // construct an HTTP request
        // note .textContent or value used instead of innerHTML because of security risk
        var xhr = new XMLHttpRequest();
        xhr.open('POST', './rest/transactionRequest', true);
        xhr.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
        xhr.setRequestHeader('Accept', 'application/json; charset=UTF-8');
        // send the collected data as JSON
        var data = document.getElementById('transactionRequestJson').value;
        document.getElementById('message').textContent = 'POSTing to rest service';
        xhr.send(data);
        xhr.onloadend = function () {
            document.getElementById('message').textContent = 'POSTing response received';
            var rxJsonObject = JSON.parse(xhr.responseText);
            document.getElementById('transactionResponseJson').textContent = JSON.stringify(rxJsonObject, null, 2);
        };
    }
</script>
<main role="main" class="container">
    <H1>Request Json</H1>
    <!-- print message / error message if there is one -->
    <div id="errorMessage" style="color:red;">${errorMessage}</div>
    <div id="message" style="color:green;">${message}</div>
    <div class="btn-group">
        <a href="./home" class="btn ml-2 rounded">Return to home page</a>
        <input class="btn ml-2 rounded" id="Post Json Message" type="button" value="Post transaction to server" onclick="sendTestData();" />
    </div>
    <div class="row">
        <div class="col-md-6">
            <div class="form-group">
                <label for="transactionRequestJson">Transaction Request Json</label><br>
                <textarea id="transactionRequestJson" rows="60" cols="50" >${transactionRequestJson}</textarea>
            </div>
        </div>
        <div class="col-md-6">
            <div class="form-group">
                <label for="transactionResponseJson">Transaction Response Json</label><br>
                <textarea id="transactionResponseJson" rows="60" cols="50" ></textarea>
            </div>
        </div>
    </div>
</div>
</main>
<jsp:include page="footer.jsp" />

