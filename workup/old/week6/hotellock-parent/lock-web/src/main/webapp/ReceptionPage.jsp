<%-- 
    Document   : ReceptionPage
    Created on : Oct 20, 2018, 6:36:52 PM
    Author     : cgallen

For some explanation see
https://stackoverflow.com/questions/31714044/accessing-request-attributes-in-jsp

https://docs.oracle.com/javaee/1.3/tutorial/doc/JSPIntro11.html


--%>
<!-- Imports perform the same role as in a normal java class -->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="solent.ac.uk.ood.examples.hotellock.model.CardKey"%>
<%@page import="solent.ac.uk.ood.examples.hotellock.model.HotelReceptionService"%>
<%@page import="solent.ac.uk.ood.examples.hotellock.model.SecretKeyProvider"%>
<%@page import="solent.ac.uk.ood.examples.hotellock.secretkey.SecretKeyProviderImpl"%>
<%@page import="solent.ac.uk.ood.examples.hotellock.reception.HotelReceptionServiceImpl"%>

<%
    // this is java code used to initialise and drive the page.
    // geneally it is good practice to not put too much logic in the JSP and use helper classes instead
    // however we have put all of the logic here as an example

    // we store objects in the session if we want the to persist for the user between page actions
    // used to store one hotelReceptionService in session
    HotelReceptionService hotelReceptionService = (HotelReceptionService) session.getAttribute("hotelReceptionService");

    // If the user session has no hotelReceptionService, create a new one
    if (hotelReceptionService == null) {
        hotelReceptionService = new HotelReceptionServiceImpl();
        SecretKeyProvider secretKeyProvider = new SecretKeyProviderImpl();
        hotelReceptionService.setSecretKeyProvider(secretKeyProvider);
        session.setAttribute("hotelReceptionService", hotelReceptionService);
    }

    String submitAction = (String) request.getParameter("submitAction");

    // used to access current roomNumber either from session or from request
    String roomNumber = (String) request.getParameter("roomNumber");
    if (roomNumber == null) {
        roomNumber = (String) session.getAttribute("sessionRoomNumber");
        if (roomNumber == null) {
            roomNumber = "";
        }
    } else {
        session.setAttribute("sessionRoomNumber", roomNumber);
    }

    // date format used  to parse dates from strings
    DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm");

    // constants used to generate default time values
    Date now = new Date();
    Date tomorrow = new Date(now.getTime() + 1000 * 60 * 60 * 24); // 1 day later
    String timeStrNow = df.format(now);
    String timeStrTomorrow = df.format(tomorrow.getTime());

    // get request parameters for startdaate and endDate
    String requestStartDateStr = (String) request.getParameter("startDate");
    if (requestStartDateStr == null) {
        requestStartDateStr = timeStrNow;
    }

    String requestEndDateStr = (String) request.getParameter("endDate");
    if (requestEndDateStr == null) {
        requestEndDateStr = timeStrTomorrow;
    }

    Date endDate = null;
    Date startDate = null;
    String codedCard = "";

    CardKey keyCard = null;
    boolean errorReadingCard = false;

    boolean errorCodingCard = false;
    if ("createCard".equals(submitAction)) {

        // decode card if requested 
        if (roomNumber.isEmpty()) {
            errorCodingCard = true;
        } else {
            try {
                endDate = df.parse(requestEndDateStr);
                startDate = df.parse(requestStartDateStr);
                codedCard = hotelReceptionService.createCardCode(roomNumber, startDate, endDate);
            } catch (Exception ex) {
                errorCodingCard = true;
            }
        }
    } else if ("readCard".equals(submitAction)) {

        // used to access request card code
        String requestCardCode = (String) request.getParameter("cardCode");
        if (requestCardCode != null) {
            try {
                keyCard = hotelReceptionService.readCard(requestCardCode);
            } catch (Exception ex) {
                errorReadingCard = true;
            }
        }
    }

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hotel Reception Page</title>
    </head>
    <body>
        <h1>Welcome to the Hotel Reception</h1>

        <p>Use the following form to generate a new key</p>
        <form action="./ReceptionPage.jsp">
            Enter Room Number:<br>
            <input type="text" name="roomNumber" value="<%=roomNumber%>"> 
            <% if (roomNumber.isEmpty()) { %>
            Room Number must not be empty
            <% }%>
            <br>
            Enter Start Time<br>
            <input type="text" name="startDate" value="<%=requestStartDateStr%>">
            <br>
            Enter End Time<br>
            <input type="text" name="endDate" value="<%=requestEndDateStr%>">
            <br><br>

            <input name="submitAction" type="hidden" value="createCard">
            <input type="submit" value="Submit">
            <br>
        </form> 
        <%if (errorCodingCard) { %>
        <p>Cannot code card. Check the format of your input values and retry.</p>
        
        <% } else {%>
        Encoded Card<br>
        <div id="encodeCardResult"><%=codedCard%></div>
        <% }%>

        <p>Use the following form to read a key card</p>
        <form action="./ReceptionPage.jsp">
            Enter Card Code:<br>
            <input type="text" name="cardCode" size="100" value="">
            <br>
            <input name="submitAction" type="hidden" value="readCard">
            <input type="submit" value="Read Key Card">
        </form> 
        <br>
        <% if (errorReadingCard || keyCard == null) { %>
        <p>Cannot decode card. Check Card Code value and retry.</p>
        <% } else {%>
        Decoded Card<br>
        <table >
            <tr>
                <td>Room Number</td>
                <td><%=keyCard.getRoomNumber()%></td>
            </tr>
            <tr>
                <td>Issue Number</td>
                <td><%=keyCard.getIssueNumber()%></td>
            </tr>
            <tr>
                <td>Start Date</td>
                <td><%=df.format(keyCard.getStartDate())%></td>
            </tr>
            <tr>
                <td>End Date</td>
                <td><%=df.format(keyCard.getEndDate())%></td>
            </tr>
        </table>
        <% }%>

    </body>
</html>
