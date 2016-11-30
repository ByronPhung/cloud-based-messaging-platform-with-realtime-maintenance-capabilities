from flask import Flask, request, render_template
from flask_restful import reqparse, Resource, Api

app = Flask(__name__)

api = Api(app)
parser = reqparse.RequestParser()
parser.add_argument('userid', action='append')

class Devices:
    def __init__(self, user, address, status, priority):
        self.user = user
        self.address = address
        self.status = status
        self.priority = priority
Michael = Devices('Michael', 2468, False, 3)
Byron = Devices('Byron', 5678, False, 1)
Salman = Devices('Salman', 8642, False, 1)
David = Devices('David', 1234, False, 2)
Kim = Devices('Kim', 4321, False, 2)
Eric = Devices('Eric', 8765, False, 3)

devices = {}
devices['Byron'] = Byron
devices['Salman'] = Salman
devices['David'] = David
devices['Eric'] = Eric
devices['Michael'] = Michael
devices['Kim'] = Kim

unconnected = []

class CBMP(Resource):
	def get(self):
		return unconnected

class Reset(Resource):
	def get(self):
		unconnected[:] = []
		for key, value in devices.iteritems():
			value.status = False
			unconnected.append([value.user, value.priority])


class Update(Resource):
	def get(self):
		for key, value in devices.iteritems():
			if value.status == False:
				if not (value.user.capitalize() in sublist for sublist in unconnected):
					print value.user + " IN UNCONNECTED" 
					unconnected.append([value.user, value.priority])
				# else:
				# 	print value.user + " IS FALSE" 
		offline = sorted(unconnected,key=lambda l:l[1])
		#print offline
		index = 0
		print "Name \t Status \t Priority Level"
		for value in offline:
			print offline [index][0] + "\t NOT connected \t\t" + str(offline[index][1])
			index += 1
		return offline

class HelloWorld(Resource):
    def get(self):
        return 'This is a Test'

class Ping(Resource):
    def get(self):
    	args = request.args
    	userid = args['userid'].encode('ascii').strip("'")
    	devices[userid].status = True
    	for sublist in unconnected:
    		if sublist[0].lower() == userid.lower():
    			unconnected.remove(sublist)
    	print userid + " is CONNECTED"
    	return devices[userid].user + " is CONNECTED"
    	# return render_template('test.html', list=devices[userid].user)

api.add_resource(HelloWorld, '/')
api.add_resource(Ping, '/ping')
api.add_resource(Update, '/update')
api.add_resource(Reset, '/reset')
api.add_resource(CBMP, '/cbmp')

if __name__ == '__main__':
    app.run(debug=True)