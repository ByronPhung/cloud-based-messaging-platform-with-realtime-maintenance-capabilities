<?php
    include_once 'inc/db_connect.php';
    include_once 'inc/functions.php';

    sec_session_start();

    if (login_check($dynamodb) == FALSE) {
        header('Location: login/');
    }
    
    /*
    $response = $dynamodb->getItem([
      'TableName' => 'Users',
      'Key' => [
        'UserId' => ['S' => $_SESSION['user_id']]
      ]
    ]);

    if (empty($response['Item'])) {
      print_r("empty");
    } else {
      print_r($response['Item']);
    }
    */
    
    /*
    $response = $dynamodb->getItem([
      'TableName' => 'Messages',
      'Key' => [
        'UserId' => ['S' => $_SESSION['user_id']]
      ]
    ]);

    if (empty($response['Item'])) {
      print_r("empty");
    } else {
      print_r("Count: " . count($response['Item']['Messages']['M']));
      print_r($response['Item']['Messages']['M']['salman']);
    }
    */

    $response = $dynamodb->getItem([
      'TableName' => 'Users',
      'Key' => [
        'UserId' => ['S' => $_SESSION['user_id']]
      ]
    ]);

    $name = $response['Item']['Name']['S'];
?>

<!DOCTYPE html>
<html>
  <head>
    <title>Socket.IO chat</title>
    <style>
      * { margin: 0; padding: 0; box-sizing: border-box; }
      body { font: 13px Helvetica, Arial; }
      form { background: #000; padding: 3px; position: fixed; bottom: 0; width: 100%; }
      form input { border: 0; padding: 10px; width: 90%; margin-right: .5%; }
      form button { width: 9%; background: rgb(130, 224, 255); border: none; padding: 10px; }
      #messages { list-style-type: none; margin: 0; padding: 0; }
      #messages li { padding: 5px 10px; }
      #messages li:nth-child(odd) { background: #eee; }
    </style>
  </head>
  <body>
    <h1>Welcome, <?php echo $name; ?>!</h1>
    <a href="logout/">Logout</a>
    <ul id="messages">
      <?php
        if ($_SESSION['user_id'] == "salman.hasib@cbmp.com") {
          $conversationId = "Byron";
        } else {
          $conversationId = "Salman";
        }

        $response = $dynamodb->getItem([
          'TableName' => 'Messages',
          'Key' => [
            'UserId' => ['S' => $_SESSION['user_id']]
          ]
        ]);

        if (!empty($response['Item']) && !empty($response["Item"]["Messages"]["M"])) {
          for ($i = 0; $i < count($response["Item"]["Messages"]["M"][$conversationId]["L"]); $i++) {
            echo "<li>" . $response["Item"]["Messages"]["M"][$conversationId]["L"][$i]["S"] . "</li>";
          }
        }
      ?>
    </ul>
    <form id="messageForm">
      <input type="hidden" id="from" value="<?php echo $_SESSION['user_id']; ?>" />
      <input type="hidden" id="name" value="<?php echo $name; ?>" />
      <input type="hidden" id="to" value="<?php echo $conversationId; ?>" />
      <input id="message" autocomplete="off" /><button>Send</button>
    </form>

    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js" ></script>
    <script src="js/bootstrap.js"></script>
    <script src="js/node_modules/socket.io/node_modules/socket.io-client/dist/socket.io.js"></script>
    <script src="js/nodeClient.js"></script>
  </body>
</html>