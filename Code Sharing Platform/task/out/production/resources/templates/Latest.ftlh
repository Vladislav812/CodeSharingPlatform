<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Latest</title>
    <link rel="stylesheet" href="/css/styles.css" type="text/css"/>
<body>
<#list latestList_ as record>
    <span id="load_date">${record["date"]}</span>
    <br>
    <pre id="code_snippet">${record["code"]}</pre>
    <br>
</#list>
</body>
</html>