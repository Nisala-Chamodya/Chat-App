package lk.blacky.chatapp.server;

import lk.blacky.chatapp.client.ClientManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }
    public  void startServer(){
        try {
            while (!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();
                System.out.println("A New Client has Connected!!!");
                ClientManager clientManager=new ClientManager(socket);

                Thread thread=new Thread(clientManager);
                thread.start();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void  closeServerSocket(){
        try{
            if (serverSocket != null){
                serverSocket.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket=new ServerSocket(1235);
        Server server=new Server(serverSocket);
        server.startServer();


    }
}
