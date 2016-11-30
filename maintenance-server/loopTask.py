#!/usr/bin/python
import requests, time
while(True):
	r = requests.get('http://localhost:5000/reset')
	time.sleep(5)
	r = requests.get('http://localhost:5000/update')
	time.sleep(5)
	r = requests.get('http://localhost:5000/update')