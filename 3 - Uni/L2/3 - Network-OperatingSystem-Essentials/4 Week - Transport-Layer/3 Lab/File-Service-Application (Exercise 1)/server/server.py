import sys
import socket
import os
import RM

'''
Gets the port number, sets a default if not provided, and reports success or failure
'''
def get_port():
    default_port = 2000
    if len(sys.argv) < 2:
        print("The port number was not entered so port number  2000 will be used as the default")
    else:
        try:
            default_port = int(sys.argv[1])
            hostname = socket.gethostname()
            ip_address = socket.gethostbyname(hostname)
            print(str(ip_address) + ", " + str(sys.argv[1]) + " server up and running")

        except:
            print("The port number must be an integer. The default port of 2000 will be used instead.")
        if len(sys.argv) > 3:
            print("Note: numerous arguments have been entered. The port entered in argv[1] will be used.")
    return default_port

'''
For list requests, this returns the dictionary response
'''
def get_list_response(requestor_d):
    return {"status":200,"data":os.listdir(os.getcwd()),"command":"list","log":"List elements successful"}

'''
For get requests, this returns the dictionary response.
(Spending 3 days trying trying to work on this, learned that code 200 means successful;
400 means failed - which somewhat makes me remember the 404 error for missing webpages.
'''
def get_get_response(requestor_d):
    if "file_name" not in requestor_d:
        return {"status":400, "error":"No fileName was entered"}
    try:
        if(".." in requestor_d["file_name"] or "/" in requestor_d["file_name"]):
            return {"status":400,"error":"Only local files can be accessed so invalid file name was used"}
        with open(requestor_d["file_name"],"rb") as file_tbsent:
            data = file_tbsent.read()
            return {"status":200,"data": data, "fileName": requestor_d["file_name"], "command": "get", "log": "File Request '{}' was  successful.".format(requestor_d["file_name"])}
    except Exception as e:
        return {"status":400,"error":"The file could not be found"}

'''
For put requests, this returns the dictionary response
'''
def get_put_response(requestor_d):
    if ("file_name" not in requestor_d) or ("data" not in requestor_d):
        return {"status":400,"error":"Missing additional information for put command","log":str(requestor_d)}
    if(".." in requestor_d["file_name"] or "/" in requestor_d["file_name"]):
        return {"status":400,"error":"Only local files can be accessed so invalid file name was used"}
    if(requestor_d["file_name"] in os.listdir(os.getcwd())):
        if("ow" not in requestor_d):
            return {"status":400,"error":"It appears that {} is already in the directory. Enter 'ow' to overrite the file".format(requestor_d["file_name"])}
    f = open(requestor_d["file_name"], "bw+")
    f.write(requestor_d["data"])
    f.close()
    return {"status":200, "command": "put", "log": "Uploading '{}' was successful".format(requestor_d["fileName"])}

'''
Based on the type of request received, this method calls the appropriate method for that request.
Tries to return the dictionary response for that appropriate request, reports  an error if things fail.
'''
def get_response(requestor_d):
    if "command" not in requestor_d:
        return {"status":400, "error":"Request does not contain the command"}
    main_commands = {"list": get_list_response, "get": get_get_response, "put": get_put_response}
    if(requestor_d["command"] in main_commands):
        return main_commands[requestor_d["command"]](requestor_d)
    return {"status":400, "error": "The command was not recognised. Use list/get/out."}


try:
    server_socket = RM.server_socket(get_port())
except:
    print ("Socket unavailable: could be in current use")
    exit()

while True:
    (cli_sock, cli_addr) = server_socket.accept()
    print("\n Connection established from {}".format(str(cli_addr)))
    request_d = {}
    try:
        request_d = RM.receiver_d(cli_sock)
        if ("command"in request_d):
            print("The request has been received as: ", request_d["command"])
    except Exception as e:
        RM.sender_d(cli_sock, {"status":400, "error": str(e)})
        continue
    try:
        response_d = get_response(request_d)
        RM.sender_d(cli_sock,response_d)
    except Exception as e:
        raise e
        try:
            RM.sender_d(cli_sock,{"status":500, "error":"Code 500 - means internal server error"})
        except Exception as e:
            continue
        continue


        
