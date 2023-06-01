package lk.blacky.chatapp.client;


import java.io.*;
import java.net.Socket;

public class Client {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private  String userName;

    public Client (Socket socket,String userName) throws IOException {

        try{


        this.socket=socket;
        this.bufferedWriter=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.userName=userName;
    } catch (IOException e){
            e.printStackTrace();
            closeEverything(socket,bufferedReader,bufferedWriter);
        }
    }

    private void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
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