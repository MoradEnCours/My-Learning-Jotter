import sys
import socket
import os
import RM

'''
Gets server address and port number from parsing CLI
'''
def get_serveradd():
    try:
        return sys.argv[1], int(sys.argv[2])
    except:
        print("The following is not a port number: {}".format(sys.argv[2]))
        exit()
'''
Parses the command request entered by the user for get and returns a dictionary containing the specified arguments
'''
def get_command():
    if len(sys.argv) < 4:
        print("Number of arguments provided is not sufficient. Format should be: <python> <Client.py> <ServerAddress> <PortNo.> <Command> [<Optional Paramater>]")
        exit()
    commands = ['list', 'get', 'put']
    if sys.argv[3] not in commands:
        print("{} :Unacceptable command given: choose either get/put/list".format(sys.argv[3]))
        exit()
    return {"command": sys.argv[3]}

def get_getarg():
    if len(sys.argv) < 5:
        print("No file specified in additional get argument to download")
        exit()

    if len(sys.argv) == 6:
        if sys.argv[5] == "ow":
            return {"file_name": sys.argv[4], "ow": "True"}
    return {"file_name": sys.argv[4]}

'''
Parses the command request entered by the user for put and returns a dictionary containing the specified arguments
'''
def get_putarg():
    if len(sys.argv) < 5:
        print("No file specified in additional put argument to upload")
        exit()
    data = ""
    try:
        with open(sys.argv[4], 'rb') as upload_file:
            data = upload_file.read()
        if len(sys.argv) == 6:
            if sys.argv[5] == "ow":
                return {"data": data, "file_name": sys.argv[4], "ow": "True"}
        return {"data": data, "file_name": sys.argv[4]}
    except Exception as e:
        print(e)
        exit()

'''
Parses the command request entered by the user for list and returns a dictionary containing the specified arguments
List does not have any additional parameters so an empty list is returned.
'''
def get_listarg():
    return {}

'''
Returns a dictionary containing the request to be sent to the server,
'''
def requestor_d():
    request_d = get_command()
    get_func_args = {"list": get_listarg, "get": get_getarg, "put":get_putarg}
    request_d.update(get_func_args[request_d["command"]]())
    return request_d


request_d = requestor_d()
cli_sock = None
try:
    server_addr, server_port = get_serveradd()
    cli_sock = RM.client_socket(server_addr, server_port)
except:
    print("The connection to the server failed. Check server address and port number match.")
    exit()

RM.sender_d(cli_sock, request_d)
responder_d = {}
try:
    responder_d = RM.receiver_d(cli_sock)
    print("Got the response")
except (ValueError, RuntimeError) as e:
    print(e)
    exit()
if "status" in responder_d:
    print("\nstatus: {}".format(responder_d["status"]))
    if responder_d["status"] != 200:
        print("Problem error: {}\n".format(responder_d["error"]))
    else:
        print("Message log: {}\n".format(responder_d["log"]))
        if request_d["command"] == "get":
            try:
                if "ow" not in request_d:
                    with open(request_d["file_name"],"bx") as file_downloaded:
                        file_downloaded.write(responder_d["data"])
                        print("The file {} has been stored.".format(request_d["file_name"]))
                else:
                    with open(request_d["file_name"],"bw") as file_downloaded:
                        file_downloaded.write(responder_d["data"])
                        print("The file {} has been stored.".format(request_d["file_name"]))
            except Exception as e:
                print (e)
                print("Failed to store the file")
        elif(request_d["command"] == "list"):
            for file_name in responder_d["data"]:
                print(file_name)
else:
    print("Yup, done.")

