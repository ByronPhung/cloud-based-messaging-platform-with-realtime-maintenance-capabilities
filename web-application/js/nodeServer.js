var socket = require('socket.io');
var express = require('express');
var http = require('http');

var app = express();
var server = http.createServer(app);

var io = socket.listen(server);

io.sockets.on('connection', function(client) {
	console.log("New client!");
	
	client.on('message', function(data) {
		console.log('Message received!');
        console.log('From: ' + data.from + ' (' + data.name + ')');
        console.log('To: ' + data.to);
        console.log("Message: " + data.message);
		io.sockets.emit('message', { from: data.from, name: data.name, to: data.to, message: data.message });
	});
});

server.listen(8080);