<%@page import = "java.util.ArrayList"%>
<%@page import = "com.Accio.SearchResult"%>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<h1>Simple Search Engine</h1>
<form action = "Search">
    <input type="text" name ="keyword" placeholder = "search your query"></input>
    <button type="submit"> Search </button>
</form>
<form action = "History">
    <button type="submit"> History </button>
</form>
    <table border = 2 class="resultTable">
        <tr>
            <th>Title</th>
            <th>Links</th>
        </tr>
        <%
            ArrayList<SearchResult> results = (ArrayList<SearchResult>)request.getAttribute("results");
            for(SearchResult currResult:results){
        %>
        <tr>
            <td><%out.println(currResult.getTitle());%></td>
            <td><a href = "<%out.println(currResult.getLink());%>"><%out.println(currResult.getLink());%></td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>