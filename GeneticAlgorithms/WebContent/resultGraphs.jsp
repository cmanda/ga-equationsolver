<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Genetic Algorithm Implementation</title>
<link rel="stylesheet" type="text/css" href="css/view.css" media="all" />
<script type="text/javascript" src="js/view.js"></script>
<script type="text/javascript" src="js/calendar.js"></script>
</head>
<body id="main_body">

<img id="top" src="images/top.png" alt="" />
<div id="form_container">


<h1><a>Genetic Algorithm Implementation</a></h1>
<h2><a>Values</a></h2>
<div>
Optimal Value: <%session.getAttribute("optimalF");%>
<br/>
Values of Xi: <%session.getAttribute("optimalX"); %>
<br/>
</div>
<a href="fitnessGraph.jsp">Plot of Fitness Value against generations</a>
<br/>
<a href="crossOverCountGraph.jsp">Plot of Cross Over count against generations</a>
<div id="footer">Submitted by <a
	href="mailto:beechase@email.arizona.edu">Chandrasekhar Manda</a></div>
</div>
<img id="bottom" src="images/bottom.png" alt="" />
</body>

</html>