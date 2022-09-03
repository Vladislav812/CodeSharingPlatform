<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create</title>
</head>
<body>
<textarea id="code_snippet">
</textarea>
<button id="send_snippet" type="submit" onclick="send()">Submit</button>
<script>
    function send() {
        let object = {
            "code": document.getElementById("code_snippet").value
        };
        let json = JSON.stringify(object);
        let xhr = new XMLHttpRequest();
        xhr.open("POST", '/api/code/new', false)
        xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
        xhr.send(json);
        if (xhr.status == 200) {
            alert("Success!");
        }
    }
</script>
</body>
</html>