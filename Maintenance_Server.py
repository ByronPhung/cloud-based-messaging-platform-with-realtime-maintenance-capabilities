#Maintenance Server Code

#Create a Class for Devices
#all devices will be a class device: name(int address, bool status, int priority)

class Devices:
    def __init__ (device, address, status, priority):
        device.a = address
        device.s = status
        device.p = priority

#David = Devices(12345, True, 2)
#Kim = Devices(54321, True, 2)
#Byron = Devices(67890, True, 1)
#Eric = Devices(09876, True, 3)
#Michael = Devices(24680, True, 3)
#Salman = Devices(08642, True, 1)

Name = ['David', 'Kim', 'Byron', 'Eric', 'Michael', 'Salman']
Address = [1234, 4321, 5678, 8765, 2468, 8642]
Status = [False, True, False, True, False, True]
Priority = [2, 2, 1, 3, 3, 1]
Off_Devices1 = []
Off_Devices2 = []
Off_Devices3 = []

#pull the current list of devices
 

#wait for status ping from devices


#if no status ping from a device in 30 seconds change the device status to false


#if device has status = false add the device to array for devices with no connection

print ('The list of devices that needs to be fixed are:')
for i in range (6):
    if Status[i] == False:
        if Priority[i] == 1:
            Off_Devices1.append(Name[i])
        elif Priority[i] == 2:
            Off_Devices2.append(Name[i])
        else:
            Off_Devices3.append(Name[i])

for x in Off_Devices1:
        print (x)
for y in Off_Devices2:
        print (y)
for z in Off_Devices3:
        print (z)

#put all device names in an array
#for loop that array and check status
#status == False
    


#sort array by the priority level of each device


#print out current list of devices that needs maintenance