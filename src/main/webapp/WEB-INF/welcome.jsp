

<HTML>
<head>
<style>
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
	border: 1px solid black;
}

#clearit {
	clear: left;
}
</style>
</head>
<BODY>
	<div id="outer">
		<b>WELCOME!</b><br> This is a simple application that integrates
		JBOSS, EJB,JPA and REST using:<br>


		<b>MYSQL</b><br>
		<b>JERSEY 1.8</b><br>


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