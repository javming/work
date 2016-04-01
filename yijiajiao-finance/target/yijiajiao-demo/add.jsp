<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>上传批量付款文件</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	</head>

	<body>
		<div>
					<h1>
						xls文件:
					</h1>
					<form action="http://localhost:8080/yijiajiao-finance//batchpay/excel.json" method="post">
						<table>
							<input type="file" name="img" />
						</table>
						<p>
							<input type="submit" class="button" value="提交" />&nbsp;
						</p>
					</form>
				</div>
	</body>
</html>

