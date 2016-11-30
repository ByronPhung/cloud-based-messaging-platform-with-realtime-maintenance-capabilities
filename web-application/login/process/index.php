<?php
	include_once(dirname(__FILE__) . '/../../inc/db_connect.php');
	include_once(dirname(__FILE__) . '/../../inc/functions.php');

	sec_session_start(); // Our custom secure way of starting a PHP session.

	if (isset($_POST['user'], $_POST['password']) && !empty($_POST['user']) && !empty($_POST['password'])) {
		$user = $_POST['user'];
		$password = $_POST['password'];

		if (login($user, $password, $dynamodb) == true) {
			// Login success.
			header('Location: ../../');
		} else {
			// Login failed.
			header('Location: ../../login/?error=1');
		}
	} else {
		// The correct POST variables were not sent to this page.
		header('Location: ../../login/?error=1');
	}
?>