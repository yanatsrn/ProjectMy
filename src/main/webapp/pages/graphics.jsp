
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
        google.charts.load('current', {'packages':['corechart']});
        google.charts.setOnLoadCallback(drawChart);

        function drawChart() {
            var data = google.visualization.arrayToDataTable([
                ['Дата', 'Коэффициент игрока'],
                ['21-02-19',  3.7],
                ['21-02-27',  11],
                ['21-03-21',  8],
                ['21-04-15',  7],
                ['21-04-29',  10.7]
            ]);

            var options = {
                title: 'График изменения коэффициента',
                curveType: 'function',
                legend: { position: 'right' }
            };

            var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

            chart.draw(data, options);
        }
    </script>
</head>
<body>
<div id="curve_chart" style="width: 900px; height: 500px"></div>
</body>
</html>
