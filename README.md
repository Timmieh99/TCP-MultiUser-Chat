# TCP-MultiUser-Chat
Here's a breakdown of how the TCP multi-user chat program works:

    Server:
        The server listens on a specified port (in this case, port 1234) using a ServerSocket.
        It continuously accepts client connections by calling the accept() method on the ServerSocket.
        When a client connects, a new ClientHandler thread is created to handle communication with that client.
        The ClientHandler thread manages the input and output streams for each client and handles the chat logic.
        It asks the client for their name by sending a prompt through the output stream.
        The client's name is read from the input stream and stored in the clientName variable.
        The client's name is added to the clientNames list, and a broadcast message is sent to all clients to announce that a new client has joined the chat.
        Inside the run() method, the ClientHandler thread listens for messages from the client through the input stream.
        When a message is received, it broadcasts the message to all connected clients, including the sender's name.
        If an IOException occurs during communication with the client or when the client disconnects, the ClientHandler thread closes the client socket, removes the client from the list of clients, and sends a broadcast message to inform all clients that the client has left the chat.

    Client:
        The client connects to the server using a Socket and specifies the server's IP address and port number.
        It sets up input and output streams for communication with the server.
        The client prompts the user to enter their name, which is then sent to the server.
        Inside separate threads, the client listens for messages from the server through the input stream and reads user input from the console to send messages to the server through the output stream.
        The receiving thread continuously listens for messages from the server. When a message is received, it is printed to the console.
        The sending thread continuously reads user input from the console. When the user enters a message, it is sent to the server.

In this way, multiple clients can connect to the server, exchange messages, and see messages from other clients in the chat. The server acts as a central hub that relays messages between clients, allowing for a multi-user chat experience.
