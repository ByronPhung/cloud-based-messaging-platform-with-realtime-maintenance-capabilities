<?php
	include_once(dirname(__FILE__) . '/../aws/aws-autoloader.php');

	function sec_session_start() {
		$session_name = 'sec_session_id'; // Set a custom session name.
		$secure = false;
		// This stops JavaScript being able to access the session ID.
		$httponly = true;

		// Forces sessions to only use cookies.
		if (ini_set('session.use_only_cookies', 1) === false) {
			echo '<script type="text/javascript">alert("hello124214!");</script>';
			header("Location: ../error.php?err=Could not initiate a safe session (ini_set)");
			exit();
		}

		// Gets current cookies params.
		$cookieParams = session_get_cookie_params();
		session_set_cookie_params($cookieParams["lifetime"],
			$cookieParams["path"],
			$cookieParams["domain"],
			$secure,
			$httponly);

		// Sets the session name to the one set above.
		session_name($session_name);
		session_start(); // Start the PHP session.
		session_regenerate_id(true); // Regenerated the session and deleted the old one.
	}

	function login($user, $password, $dynamodb) {
		$response = $dynamodb->getItem([
	      'TableName' => 'Users',
	      'Key' => [
	        'UserId' => ['S' => $user]
	      ]
	    ]);

	    if (empty($response['Item'])) {
	      return false;
	    } else {
	      if (password_verify($password, $response['Item']['Password']['S'])) {
			$user_browser = $_SERVER['HTTP_USER_AGENT'];
			$_SESSION['user_id'] = $user;
			$_SESSION['login_string'] = $response['Item']['Password']['S'];
			return true;
		  } else {
		  	return false;
		  }
		}
	}

	function login_check($dynamodb) {
		// Check if all session variables are set.
		if (isset($_SESSION['user_id'], $_SESSION['login_string'])) {
			$user_id = $_SESSION['user_id'];
			$login_string = $_SESSION['login_string'];

			// Get the user-agent string of the user.
			$user_browser = $_SERVER['HTTP_USER_AGENT'];

			$response = $dynamodb->getItem([
		      'TableName' => 'Users',
		      'Key' => [
		        'UserId' => ['S' => $user_id]
		      ]
		    ]);

		    if (empty($response['Item'])) {
		      return false;
		    } else {
		      $login_check = $response['Item']['Password']['S'];
		      if ($login_check == $login_string) {
		      	return true;
		      } else {
		      	return false;
		      }
			}
		} else {
			return false;
		}
	}
?>
