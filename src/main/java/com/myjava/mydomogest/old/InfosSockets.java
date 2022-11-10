package com.myjava.mydomogest.old;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

class InfosSockets {
    public static void main(String args[]) throws Exception
    {
        while(true)
        {

            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            DatagramSocket clientSocket = new DatagramSocket();
            clientSocket.setBroadcast(true);

            InetAddress IPAddress = InetAddress.getByName("192.168.1.53");
            byte[] sendData = new byte[50];
            // byte[] receiveData = new byte[50];
            String sentence = inFromUser.readLine();
            sendData = sentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,new InetSocketAddress("192.168.1.53",1470));
            clientSocket.send(sendPacket);
            // DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            // clientSocket.receive(receivePacket);
            // String modifiedSentence = new String(receivePacket.getData());
            // System.out.println("FROM SERVER:" + modifiedSentence);
            clientSocket.close();
        }
    }

}