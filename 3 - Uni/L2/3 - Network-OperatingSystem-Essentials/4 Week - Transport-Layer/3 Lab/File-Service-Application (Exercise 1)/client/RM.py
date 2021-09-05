import sys
import socket
import os
import time

'''
S
'''
def server_socket(port):
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_socket.bind((socket.gethostname(), port))
    server_socket.listen(5)
    print("Listening...")
    return server_socket

'''
Creates a socket for the given address and port number
'''
def client_socket(server_addr, server_port):
    hlocal_addr = ["0.0.0.0", "127.0.0.1", "localhost", "", " "]
    if server_addr in hlocal_addr:
        server_addr = socket.gethostname()
    cli_sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    cli_sock.connect((server_addr, server_port))
    print("\n Connection made with server: {} at the port:".format(server_addr, server_port))
    return cli_sock

'''
Sends variable to the specified socket. After the value has been converted to bytes,
a string is added to indicate variable type for decompression.
Surrounding string help to mark start and finish of values.
'''
def value_send(socket, string_marker, value):
    socket.sendall(string_marker.encode())
    if type(value) == bytes:
        socket.sendall("//BYTES//".encode())
        socket.sendall(value)
    elif type(value) == str:
        socket.sendall("//STR//".encode())
        socket.sendall(value.encode())
    elif type(value) == int:
        socket.sendall("//NUM//".encode())
        socket.sendall((str(value)).encode())
    elif type(value) == list:
        for item in value:
            value_send(socket, "//LIST//", item)
    else:
        raise TypeError("Invalid format of value to send")
    socket.sendall(string_marker.encode())

'''
Sends a dictionary to either the given server or client socket.
'''
def sender_d(socket, sending_dictionary):
    if type(sending_dictionary) != dict:
        raise(TypeError("The value being sent is not of type dict"))
    for key, value in sending_dictionary.items():
        value_send(socket, "//KEY//",key)
        value_send(socket, "//VALUE//", value)
    socket.sendall(("//END//").encode())

'''
Rather than it looking like b'"//typeofthevalue//value//,
it gets transformed to typeofthevalue(value.decode()) instead.
'''
def decode_value(value_encd):
    if value_encd.find("//BYTES//".encode())  == 0:
        return value_encd[9:]
    elif value_encd.find("//STR//".encode()) == 0:
        return value_encd[7:].decode()
    elif value_encd.find("//NUM//".encode()) == 0:
        return int(value_encd[7:].decode())
    elif value_encd.find("//LIST//".encode()) == 0:
        list_extracted = []
        while len(value_encd) > 0:
            item, value_encd = datavalue_extract("//LIST//", value_encd)
            list_extracted.append(item)
        return list_extracted
    raise ValueError("The format of the string is invalid")


'''
Changes it from format '"string_marker"-"marker_type"-"value"-"string_marker"-"data_input xtras+'
to just being '"marker_type"-"value"-"data_input xtras+'
'''
def datavalue_extract(string_marker, data_input):
    bytes_marker = string_marker.encode()
    marker_start = data_input.find(bytes_marker)
    marker_end = data_input[marker_start + len(bytes_marker):].find(bytes_marker)
    if marker_start != 0 or marker_end == -1:
        raise ValueError("The format of the string is invalid")
    value_encd = data_input[marker_start + len(bytes_marker):marker_start + len(bytes_marker) + marker_end]
    value = decode_value(value_encd)

    return value, data_input[marker_start + len(bytes_marker) + marker_end + len(bytes_marker):]


'''
Listens to the specified socket, then gives a byte array output relating to the delivere array.
Discontinues once the signal terminates. 
'''
def data_receive(socket):
    data = bytearray(1)
    data_received = bytearray(0)
    start_interval = time.time()
    time_elapsed_since_pvalue = time.time()
    while len(data) > 0:
        if start_interval + 15 < time.time():
            raise RuntimeError("The upload took too long.")
        if time_elapsed_since_pvalue + 5 < time.time():
            raise RuntimeError("Sender may not be functioning as the exit string has not been received")
        data = socket.recv(4096)
        data_received += data
        if len(data) > 0:
            time_elapsed_since_pvalue = time.time()
        if bytes("//END//", "utf-8") in data_received:
            data_received = data_received[:-7]
            break
    return data_received

'''
Receives and outputs the dictionary from the given socket
'''
def receiver_d(socket):
    received_data = data_receive(socket)
    dic = {}
    while len(received_data) > 0:
        key, received_data = datavalue_extract("//KEY//", received_data)
        value, received_data = datavalue_extract("//VALUE//", received_data)
        dic[key] = value
    return dic

