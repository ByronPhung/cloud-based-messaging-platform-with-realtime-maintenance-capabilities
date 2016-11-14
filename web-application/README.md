# Installation

You must have Node.JS installed. To download, visit https://nodejs.org/en/.

Once Node.JS is installed, install the express dependencies and Socket.IO module using NPM by running the following commands in the root project directory:
```bash
npm install --save express@4.10.2
npm install --save socket.io
```

# Usage

To start the web server, run the following command in the root project directory:
```bash
node index.js
```

Once started, the web application should be accessible on localhost on port 3000 by default: http://localhost:3000

# Public Access

To publish the web application and make it publicly accessible, you will need to use a way to tunnel out your server connection whether through a domain name service or using [ngrok](https://ngrok.com/). 
