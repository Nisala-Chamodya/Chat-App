package lk.blacky.chatapp.client;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientManager implements Runnable{
    public static ArrayList<ClientManager> clientManagers=new ArrayList<>();

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String clientUserName;
    public ClientManager(){}

    public ClientManager(Socket socket){
        try {
            this.socket=socket;
            this.bufferedWriter=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())) ;
            this.bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.clientUserName= bufferedReader.readLine();
            clientManagers.add(this);
            brodcastMessage("Server "+ clientUserName + " Has Enter The Chat");


        }catch (IOException e){
            closeEverything(socket,bufferedReader,bufferedWriter);
        }
    }
    @Override
    public void run() {
        String messageFromClient;
        while (socket.isConnected()){
            try {
                messageFromClient=bufferedReader.readLine();
                brodcastMessage(messageFromClient);
            }catch (IOException e){
                closeEverything(socket,bufferedReader,bufferedWriter);
                break;
            }
        }

    }
    public void brodcastMessage(String messageToSend){
        for (ClientManager clientManager : clientManagers ) {
            try {
                if (!clientManager.clientUserName.equals(clientUserName)){
                    clientManager.bufferedWriter.write(messageToSend);
                    clientManager.bufferedWriter.newLine();
                    clientManager.bufferedWriter.flush();
                }
            }catch (IOException e){
                closeEverything(socket,bufferedReader,bufferedWriter);
            }

        }
    }
    public void removeClientHandler(){
        clientManagers.remove(this);
        brodcastMessage("SERVER : "+ clientUserName+ "has left the chat");
    }
    public void closeEverything(Socket socket,BufferedReader bufferedReader,BufferedWriter bufferedWriter){
        removeClientHandler();

        try {
            if (bufferedReader != null){
                bufferedReader.close();
            }
            if (bufferedWriter !=null){
                bufferedWriter.close();
            }
            if (socket != null){
                socket.close();
            }

        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
