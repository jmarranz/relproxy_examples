<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring 4 MVC -HelloWorld</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script type="text/javascript">
    function ajaxTest() {
        $.ajax({
        	method: 'POST',
            url : 'ajaxtest.html',
            success : function(data) {
                $('#result').html(data);
            }
        });
    }
</script> 
        
<script type="text/javascript">
    setInterval(ajaxTest, 2000);  
</script>
</head>
<body>
	<center>
		<h2>Hello World</h2>
		<h2>
			${message} ${name} 
		</h2>
	</center>

	<br />
	<div id="result"></div>
</body>
</html>
