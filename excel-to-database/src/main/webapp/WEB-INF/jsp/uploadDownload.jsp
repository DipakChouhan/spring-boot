<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="header.jsp" />
<body>
<br>
<br>
<div class="container">
	<div class="row">
		<c:if test="${excelDbDataBean.download}">
			<div class="col-sm-12">
				<div class="card">
					<div class="card-header">Download Table</div>
					<div class="card-body">
						<form:form method="POST" action="/excel-db/download-table/xsls-report" modelAttribute="excelDbDataBean.downUpModel">
							<div class="row">
								<div class="col-sm-12">
									<form:select path="downloadTableName" multiple="false" class="custom-select" style="box-shadow:none;border-radius:0px;border:0px">
										<form:option  value="" label="Select"/>
										<form:options items="${excelDbDataBean.tablesList}" />
									</form:select>
								</div>
							</div><br>
							<div class="row">
								<div class="col-sm-12">
									<input type="submit" class="btn btn-block btn btn-success" value="Download" />
								</div>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</c:if>
		<c:if test="${excelDbDataBean.upload}">
			<div class="col-sm-12">
				<div class="card">
					<div class="card-header">Upload Table</div>
					<div class="card-body">
						<form:form class="form-group" action="" method="POST" enctype="multipart/form-data" modelAttribute="excelDbDataBean.downUpModel">
							<div class="row" >
								<div class="col-sm-6">
									<form:select path="uploadTableName" multiple="false" class="custom-select" style="box-shadow:none;border-radius:0px;border:0px">
										<form:option  value="" label = "Select"/>
										<form:options items = "${excelDbDataBean.tablesList}" />
									</form:select>
								</div>
								<div class="col-sm-6">
									 <div class="custom-file" >
									    <input type="file" name="file" class="custom-file-input" id="customFile" style="box-shadow:none;border-radius:0px;border:0px">
									    <label class="custom-file-label" for="customFile" style="box-shadow:none;border-radius:0px;border:0px">Choose file</label>
									 </div> 
								</div>
							</div><br>
							<div class="row">
								<div class="col-sm-12">
									<input type="submit" class="btn btn-block btn btn-success" value="Upload" />
								</div>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</c:if>
	</div>
</div>
<script>
// Add the following code if you want the name of the file appear on select
$(".custom-file-input").on("change", function() {
  var fileName = $(this).val().split("\\").pop();
  $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
});
</script>
</body>
</html>