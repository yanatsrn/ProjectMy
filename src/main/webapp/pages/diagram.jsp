<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${pageContext.request.contextPath}/static/styles/general-styles.css" rel="stylesheet" type="text/css">
    <title>Betting office</title>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js">
    </script>
    <script type="text/javascript">
        // Load the Visualization API and the piechart package.
        google.charts.load('current', {'packages': ['corechart']});

        // Set a callback to run when the Google Visualization API is loaded.
        google.charts.setOnLoadCallback(drawChart);

        // Callback that creates and populates a data table,
        // instantiates the pie chart, passes in the data and
        // draws it.
        function drawChart() {
            /*data.addRows([

            <c:forEach items="${statisticTeamValues}" var="entry">
                        [ '
            ${entry.key}',
            ${entry.value} ],

            </c:forEach>
                    ]);*/
            var data = google.visualization.arrayToDataTable([
                ['Country', 'Area(square km)'],
                <c:forEach items="${statisticTeamValues}" var="entry">
                ['${entry.key}', ${entry.value}],
                </c:forEach>
            ]);

            // Set chart options
            var options = {
                'title': 'Оценки риска команд с наибольшим количеством сыгранных матчей', //title which will be shown right above the Google Pie Chart
                is3D: false, //render Google Pie Chart as 3D
                pieSliceText: 'label',
                tooltip: {showColorCode: true},
                'width': 900, //width of the Google Pie Chart
                'height': 500 //height of the Google Pie Chart
            };

            // Instantiate and draw our chart, passing in some options.
            var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
            chart.draw(data, options);
        }
    </script>
</head>
<body>
<div class="container">
    <div class="body" style="display: flex;
    flex-wrap: wrap;">
        <div class="main-content">
            <div class="main-body">
                <div style="width: 600px;">
                    <div id="chart_div"></div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
