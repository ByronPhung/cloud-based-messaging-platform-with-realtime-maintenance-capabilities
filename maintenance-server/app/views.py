from app import app

# @app.route('/')
# @app.route('/index')
class HelloWorld:
	def get(self):
		print "HelloWorld"


	def index():
		    #Maintenance Server Code
		# Maintenance Server Code

		# Create a Class for Devices
		# all devices will be a class device: name(int address, bool status, int priority)

		#Create a Class for Devices
		#all devices will be a class device: name(int address, bool status, int priority)

		class Devices:
		    def __init__(self, user, address, status, priority):
		        self.user = user
		        self.address = address
		        self.status = status
		        self.priority = priority

		# Name = ['David', 'Kim', 'Byron', 'Eric', 'Michael', 'Salman']
		# Address = [1234, 4321, 5678, 8765, 2468, 8642]
		Off_Devices1 = []
		Off_Devices2 = []
		Off_Devices3 = []

		#pull the current list of devices
		 

		#wait for status ping from devices


		#if no status ping from a device in 30 seconds change the device status to false

		michael = Devices('Michael', 2468, False, 3)
		byron = Devices('Byron', 5678, False, 1)
		salman = Devices('Salman', 8642, True, 1)
		david = Devices('David', 1234, False, 2)
		kim = Devices('Kim', 4321, True, 2)
		eric = Devices('Eric', 8765, True, 3)
		devices = []
		devices.append(Byron)
		devices.append(Salman)
		devices.append(David)
		devices.append(Kim)
		devices.append(Michael)
		devices.append(Eric)

		print 'The list of devices that needs to be fixed are:'
		for device in devices:
		    if not device.status:
		        if device.priority == 1:
		            Off_Devices1.append(device)
		        elif device.priority == 2:
		            Off_Devices2.append(device)
		        else:
		            Off_Devices3.append(device)

		for x in Off_Devices1:
		    print x.user
		for y in Off_Devices2:
		    print y.user
		for z in Off_Devices3:
		    print z.user

		#put all device names in an array
		#for loop that array and check status
		return "The Device fixed are: " + x.user + " " + y.user + " " + z.user

# @app.route('/ping')
class Ping():
	def get():
		return "ping"



