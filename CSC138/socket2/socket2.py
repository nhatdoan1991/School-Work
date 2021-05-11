#Nhat Doan CSC138 Spring 2020 - LAB socket 2
from socket import*

msg = 'I love networking'
endmsg = '\r\n.\r\n'

#Connection SETUP
# Create socket called clientSocket and establish a TCP connection with mailserver
emailserver = ('localhost',25)
clientSocket = socket(AF_INET,SOCK_STREAM)
clientSocket.connect(emailserver)
isConnected = clientSocket.recv(1024)
print (isConnected)
if isConnected[:3] != '220':
	print ('220 reply not received from server.')

# Send HELO command and print server response.
heloCommand = 'HELO Alice\r\n'
clientSocket.send(heloCommand.encode())
recv1 = clientSocket.recv(1024).decode()
print (recv1)
if recv1[:3] != '250':
	print ('250 reply not received from server.')
	

#SENDER SETUP 
clientSocket.send("MAIL From: nobody@csus.edu\r\n".encode())
recv2 = clientSocket.recv(1024).decode()
print (recv2)
if recv2[:3] != '250':
	print ('250 reply not received from server.')
	
#RECEIVER SETUP
clientSocket.send("RCPT TO: nhatdoan@csus.edu\r\n".encode())
recv2 = clientSocket.recv(1024).decode()
print (recv2)
if recv2[:3] != '250':
	print ('250 reply not received from server.')


	
#Send DATA command and print server response.
clientSocket.send("DATA\r\n".encode())
recv2 = clientSocket.recv(1024).decode()
print (recv2)
if recv2[:3] != '354':
	print ('354 reply not received from server.')

#Send Data and print server response.
clientSocket.send("SUBJECT: SMTP Mail Client Test\r\n\n".encode())
clientSocket.send('Hello this is for testing\n'.encode())
clientSocket.send(endmsg.encode())
#clientSocket.send(endmsg)
recv2 = clientSocket.recv(1024).decode()
print (recv2)
if recv2[:3] != '250':
	print ('250 reply not received from server.')

	


	
#Send QUIT and print server response.
clientSocket.send("QUIT\r\n".encode())
recv2 = clientSocket.recv(1024).decode()
print (recv2)
if recv2[:3] != '221':
	print ('250 reply not received from server.')

print ("Mail Sent")
	
	
	
clientSocket.close()