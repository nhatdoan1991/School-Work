#Nhat Doan CSC138 Socket programming 
from socket import *
import time

serverSocket = socket (AF_INET, SOCK_STREAM)
#Prepare a sever socket 
host='192.168.1.10'
serverPort = 1024
serverSocket.bind(("",serverPort))
serverSocket.listen(1)

print('Hostname is:', gethostname())
print('Host IP is', gethostbyname(gethostname()))
print('Host port is', serverPort)


while True:
#Establish the connection 
	print('Ready to serve')

	connectionSocket, addr = serverSocket.accept()


	try:
		message = connectionSocket.recv(1024)
		print ('Got request...')
		print('connected with: ',addr)
		print("\n")
		print(message)
		print("\n")
		filename = message.split()[1]
		print("File name is: ", filename)
		print("\n")
		f = open(filename[1:])
		outputdata = f.read()
		connectionSocket.send(b'HTTP/1.1 200 OK\r\n\r\n')
		for i in range(0, len(outputdata)):
			connectionSocket.send(outputdata[i].encode())
		
		connectionSocket.close()
		print("Connection with client has ended")
	except IOError:
		print("IOError")
		connectionSocket.send(b'HTTP/1.1 404 Not Found\r\n\r\n')
		connectionSocket.send(b'<html><body><h1>404 Not Found</h1></body></html>\r\n')
		connectionSocket.close()
		print("Connection with client has ended")
serverSocket.close()