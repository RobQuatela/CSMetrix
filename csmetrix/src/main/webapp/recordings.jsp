<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="com.accountomation.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
.selected {
	background-color: brown;
	color: #fff;
}
</style>
<script>
	function getEmpID() {
		console.log("Starting...");
		var v = document.getElementById("lstEmployee");
		var x = v.options[v.selectedIndex].value;
		var empName = v.options[v.selectedIndex].text;
		console.log(x);
		document.getElementById("hEmployee").value = x;
		document.getElementById("txtCSR").innerHTML = empName;
		console.log(document.getElementById("hEmployee").value);
	}

	function getRecID(x) {
		console.log("Starting...getRecID()");
		document.getElementById("hRecording").value = x.id;
		document.getElementById("hRecordingSearch").value = x.id;
		console.log("hRecording: "
				+ document.getElementById("hRecording").value);
		console.log(x.cells[0].innerHTML);
		document.getElementById("txtScoreName").value = x.cells[0].innerHTML;
	}

	function highlight(e) {
		if (selected[0])
			selected[0].className = '';
		e.target.parentNode.className = 'selected';
	}

	var table = document.getElementById("tbRecordings"), selected = table
			.getElementsByClassName("selected");
	table.onclick = highlight;
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="jumbotron">
		<h1>Call Tracking</h1>
	</div>
	<br />
	<div class="container">
		<div class="row">
			<form name="frmSearchRecordings" method="get" action="Recordings">
				<c:set value="${empID }" var="empID" />
				<div class="col-lg-2">
					<label for="employee">Employee: </label><br /> <select
						id="lstEmployee" multiple name="lstEmployee" onclick="getEmpID()">
						<c:forEach var="employee" items="${employees }">
							<c:if test="${empID == employee.id }">
								<option selected value="${employee.id }">${employee.name }</option>
							</c:if>
							<c:if test="${empID != employee.id }">
								<option value="${employee.id }">${employee.name }</option>
							</c:if>
						</c:forEach>
					</select>
				</div>
				<div class="col-lg-7">
					<div class="row">
						<label for="dtpStart">Start:</label><input id="dtpStart"
							name="dtpStart" type="date" value="${start }" /> <label
							for="dtpEnd"> End:</label><input id="dtpEnd" name="dtpEnd"
							type="date" value="${end }" /> <input type="submit"
							name="btnSearch" value="Get Recordings"> <input
							type="hidden" name="hRecordingSearch" id="hRecordingSearch">
						<br />
					</div>
					<div class="row">
						<div class="col-lg-7">
							<h3>Calls:</h3>
							<table id="tbRecordings" class="table table-hover">
								<tr>
									<th>Name</th>
									<th>Date</th>
									<th>Notes</th>
								</tr>
								<c:forEach var="recording" items="${recordings }">
									<tr id="${recording.getId() }" onclick="getRecID(this)">
										<td id="recName">${recording.getName() }</td>
										<td>${recording.getDate() }</td>
										<td>${recording.getNotes() }</td>
									</tr>
								</c:forEach>
							</table>
							<br />
							<button type="submit" name="btnRetrieveScores">Get
								Scores</button>
						</div>
						<div class="col-lg-5">
							<div class="row">
								<h3>Scores:</h3>
								<table id="tbScores" class="table table-condensed">
									<tr>
										<th>Quality Type</th>
										<th>Score</th>
									</tr>
									<c:forEach var="score" items="${scores }">
										<tr>
											<td>${score.getQualityScore().getQualityType().getName() }</td>
											<td>${score.getQualityScore().getScore().getId() }</td>
										</tr>
									</c:forEach>
									<tr>
										<td>Total</td>
										<td><c:out value="${total }"></c:out></td>
									</tr>
								</table>
							</div>
			</form>
			<div class="row">
				<form name="frmScoreRecording" method="post" action="Recordings">
					<h3>Score Call</h3>
					<input type="hidden" name="hRecording" id="hRecording"> <input
						type="text" name="txtScoreName" id="txtScoreName"> <br />
					<label for="tbQualities">Qualities:</label>
					<table id="tbQualities" class="table table-hover">
						<tr>
							<th>Name</th>
							<th>Description</th>
							<th>Score</th>
						</tr>
						<c:forEach var="quality" items="${qualities }">
							<input type="hidden" name="hQualityType"
								value="${quality.getId() }">
							<tr id="${quality.getId() }">
								<td>${quality.getName() }</td>
								<td>${quality.getDescription() }</td>
								<td><select id="qualityScores" name="ddlQualityScores">
										<c:forEach var="score" items="${quality.getQualityScores() }">
											<option value="${score.getId() }">${score.getScore().getId() }</option>
										</c:forEach>
								</select></td>
							</tr>
						</c:forEach>
					</table>
					<br /> <input type="submit" name="btnInsertScores"
						value="Insert Scores">
				</form>
			</div>
		</div>
	</div>
	</div>
	<div class="col-lg-3">
		<form name="frmAddRecording" method="post" action="Recordings">
			<h2>New Call:</h2>
			<br /> <input type="hidden" name="hEmployee" id="hEmployee">
			<label for="txtCSR">CSR: </label>
			<p id="txtCSR"></p>
			<br /> <label for="txtName">Name: </label><input type="text"
				id="txtName" name="txtName"><br /> <label for="dtpDate">Date:
			</label><input type="date" id="dtpDate" name="dtpDate"><br /> <label
				for="txtNotes">Notes: </label><input type="text" id="txtNotes"
				name="txtNotes"><br /> <input type="submit"
				name="btnInsertRec" value="Insert Recording">
		</form>
	</div>
	</div>
	</div>
</body>
</html>