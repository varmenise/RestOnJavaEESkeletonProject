

<HTML>
<head>
<style>


body {
	font-family: Helvetica, Verdana, sans-serif;
}

#welcome {
	text-align: center;
	color: #CC00FF;
}

#tec {
	text-align: center;
	color: #CC00FF;
}

#outer {
	text-align: center;
}

#inner {
	width: 200px;
	height: 200px;
	text-align: center;
	margin: 0 auto;
}

.t {
	float: left;
}

table {
	border: 2px solid black;
	width: 300px;
}

#clearit {
	clear: left;
}
</style>
</head>
<BODY>
	<div id="welcome">
		<h1>
			<b>WELCOME!</b>
		</h1>
	</div>
	<div id="outer">
		<br> This is a simple application that runs REST services on
		JAVAEE container using:
		<div id=tec>
			<br> <br> <b>JERSEY 1.8</b><br> <b>EJB 3.1</b><br>
			<b>MYSQL</b><br> <b>JBOSS 7</b> <br><br>
		</div>

		<div id="inner">

			<div class="t">
				<form action="RestDispatcherServlet" method="get">

					<table align="center">
						<tr>
							<td><b>Nation</b>
							<td><input type="text" name="nation">
							<td>
						</tr>
						<tr>
							<td><b>Capital </b></td>
							<td><input type="text" name="capital"></td>
						</tr>
						<tr>
							<td><input type="submit" value="SaveCapital"></td>
						</tr>
						<tr>
							<td><input type="hidden" name="action" value="Save"></td>
						</tr>
					</table>
				</form>
			</div>


			<div class="t">

				<form action="RestDispatcherServlet" method="get"
					name="getCapitalByNation">
					<table align="center">

						<tr>
							<td><b>Nation</b>
							<td><input type="text" name="nationToRetrieve">
							<td>
						</tr>
						<tr>
							<td><b>Capital </b></td>
							<td><%=request.getAttribute("capitalRetrieved") != null ? request
					.getAttribute("capitalRetrieved") : ""%></td>
						</tr>
						<tr>
							<td><input type="submit" value="getCapitalByNation"></td>
						</tr>
						<tr>
							<td><input type="hidden" name="action" value="Retrieve"></td>
						</tr>
					</table>
				</form>
			</div>
		</div>

		<div id="clearit"></div>

	</div>


</BODY>
</HTML>