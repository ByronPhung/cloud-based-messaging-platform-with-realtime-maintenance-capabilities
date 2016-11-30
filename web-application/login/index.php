<?php
	include_once(dirname(__FILE__) . '/../inc/db_connect.php');
	include_once(dirname(__FILE__) . '/../inc/functions.php');

	sec_session_start();

	if (login_check($dynamodb) == true) {
		$logged = 'in';
	}

	else {
		$logged = 'out';
	}
?>

<!DOCTYPE html>
<html>
	<head>
		<title>CBMP - Login</title>

		<!-- Responsive and Mobile Friendly Stuff -->
		<meta name="HandheldFriendly" content="True">
		<meta name="MobileOptimized" content="320">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
	</head>

	<body>
		<!-- If User is Signed In -->
        <?php if (login_check($dynamodb) == true) : header('Location: ../'); ?>
        
        <!-- If User is NOT Signed In -->
        <?php else : ?>
        <div class="bodycontainer">
            <div class="maincontainer">
                <h4>CBMP Login</h4>
                <hr>
                <p>
                	NOTE: You must manually click "Login" to login instead of hitting enter from the keyboard.
                </p>
                <hr>

                <!-- Display Error Message if Problem Logging In -->
                <?php
					if (isset($_GET['error'])) {
						echo '<p class="error">Error logging in. Please check your User ID and/or Password.</p>';
					}
				?>

                <p>
                    <form action="process/" method="post">
						User ID: <input type="text" name="user" /><br><br>
						Password: <input type="password" name="password" /><br><br>
						<input type="submit" value="Login" />
					</form>
                </p>
			</div>
		</div>
    	<?php endif; ?>
	</body>
</html>