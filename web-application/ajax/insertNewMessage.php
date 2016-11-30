<?php
	include_once(dirname(__FILE__) . '/../inc/db_connect.php');

	$from = $_POST['from'];
	$name = $_POST['name'];
	$to = $_POST['to'];
	$message = $_POST['message'];
	$fullMessage = "<strong>" . $name . "</strong> : " . $message;

	$response = $dynamodb->updateItem([
		"TableName" => "Messages",
		"Key" => [
			"UserId" => ["S" => $from]
		],
		"ExpressionAttributeValues" => [
			":val1" => ["L" => ["0" => ["S" => $fullMessage]]]
		],
		"UpdateExpression" => "SET Messages." . $to . " = list_append(Messages." . $to . ", :val1)"
	]);
?>