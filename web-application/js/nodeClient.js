var socket = io.connect('https://5ab89cde.ngrok.io');

$("#messageForm").submit(function() {
	var from = $("#from").val();
	var name = $("#name").val();
	var to = $("#to").val();
	var message = $("#message").val();

	socket.emit('message', { from: from, name: name, to: to, message: message });
	
	// Ajax call for saving datas
	$.ajax({
		url: "./ajax/insertNewMessage.php",
		type: "POST",
		data: { from: from, name: name, to: to, message: message },
		success: function(data) {
			
		}
	});
	
	return false;
});

socket.on('message', function(data) {
	$("#messages").append('<li><strong>' + data.name + '</strong> : ' + data.message + '</li>');
	$('#message').val('');
});