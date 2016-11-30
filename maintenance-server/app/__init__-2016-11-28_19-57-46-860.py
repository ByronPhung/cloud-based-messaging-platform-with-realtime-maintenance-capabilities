from flask import Flask, request
from flask_restful import reqparse, Resource, Api

app = Flask(__name__)
from app import views

api = Api(app)
parser = reqparse.RequestParser()
parser.add_argument('userid', action='append')

class Devices:
    def __init__(self, user, address, status, priority):
        self.user = user
        self.address = address
        self.status = status
        self.priority = priority
michael = Devices('Michael', 2468, False, 3)
byron = Devices('Byron', 5678, False, 1)
salman = Devices('Salman', 8642, False, 1)
david = Devices('David', 1234, False, 2)
kim = Devices('Kim', 4321, False, 2)
eric = Devices('Eric', 8765, False, 3)

# devices = []
# devices.append(byron)
# devices.append(salman)
# devices.append(david)
# devices.append(kim)
# devices.append(michael)
# devices.append(eric)

devices = {}
devices['byron'] = byron
devices['salman'] = salman
devices['david'] = david
devices['eric'] = eric
devices['michael'] = michael
devices['kim'] = kim

unconnected = []

class CBMP(Resource):
	def get(self):
		return unconnected

class Reset(Resource):
	def get(self):
		for key, value in devices.iteritems():
			value.status = False
			unconnected.append([value.user, value.priority])


class Update(Resource):
	def get(self):
		for key, value in devices.iteritems():
			if value.status == False:
				if (value.user in sublist for sublist in unconnected):
					print value.user + " IN UNCONNECTED" 
				else:
					print value.user + " IS FALSE" 
					unconnected.append([value.user, value.priority])
		offline = sorted(unconnected,key=lambda l:l[1])
		return offline

class HelloWorld(Resource):
    def get(self):
        return 'This is a Test'

class Ping(Resource):
    def get(self):
    	args = request.args
    	userid = args['userid'].encode('ascii').strip("'")
    	devices[userid].status = True
    	print "CHANGED " + userid + " TO TRUE"
    	unconnected.remove(unconnected.index(userid))
        return devices[userid].user

api.add_resource(HelloWorld, '/')
api.add_resource(Ping, '/ping')
api.add_resource(Update, '/update')
api.add_resource(Reset, '/reset')
api.add_resource(CBMP, '/cbmp')

if __name__ == '__main__':
    app.run(debug=True)