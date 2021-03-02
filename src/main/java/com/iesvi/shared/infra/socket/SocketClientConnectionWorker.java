package com.iesvi.shared.infra.socket;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**

 */
public class SocketClientConnectionWorker implements Runnable{

    protected Socket clientSocket = null;
    protected String clientID = null;

    private InputStream entrada;
    private OutputStream salida;

    private DataInputStream flujoEntrada;
    private DataOutputStream flujoSalida;

    public SocketClientConnectionWorker(Socket clientSocket, String clientID) {
        this.clientSocket = clientSocket;
        this.clientID = clientID;

        try {
            this.clientSocket.setReceiveBufferSize(1024);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void run() {
        try {
            entrada  = clientSocket.getInputStream();
            salida = clientSocket.getOutputStream();

            flujoEntrada = new DataInputStream(entrada);

            System.out.println(clientID + ". Waiting....");

            byte[] buffer = new byte[1024];

            while(true) {
                int readbytes = entrada.read(buffer);
                //String mm = flujoEntrada.readUTF();
                String s = new String(buffer, StandardCharsets.UTF_8);

                System.out.println(this.clientID + ". Valor recibido:\n" + s);
            }

        } catch (IOException e) {
            //report exception somewhere.
            e.printStackTrace();
        }
    }

    public void Send(String text) {
        try{

            salida = clientSocket.getOutputStream();

            flujoSalida = new DataOutputStream(salida);

            flujoSalida.flush();
            flujoSalida.writeUTF(text);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void Recibir() {
        try {

            entrada = clientSocket.getInputStream();

            flujoEntrada = new DataInputStream(entrada);

            String mensaje = flujoEntrada.readUTF();

            System.out.println(this.clientID + ". Valor recibido: " + mensaje);

        }catch (IOException e){
            e.printStackTrace();
        }



    }

}

