package com.example.mydomogest.travail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.*;

public class UdpService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UdpService.class);
    
    private String destination;
    private int port;
    private int tailleTrame;
    private byte buffer[];

    private static DatagramSocket singleDatagramSocket;
    private InetAddress address;

    public UdpService(String destination, int port, int tailleTrame) throws UnknownHostException {
        this.destination = destination;
        address = InetAddress.getByName(destination);
        this.port = port;
        this.tailleTrame = tailleTrame;
        buffer = new byte[tailleTrame];
    }

    // Création connexion udp
    public static DatagramSocket getSingleDatagramSocket() {
        if (singleDatagramSocket == null) {
            try {
                singleDatagramSocket = new DatagramSocket();
            } catch (SocketException e) {
                throw new RuntimeException(e);
            }
        }
        return singleDatagramSocket;
    }


    public String send(String msg) throws IOException {
        // Récupération de la connexion udp
        DatagramSocket socket = getSingleDatagramSocket();
        // InetAddress address = InetAddress.getByName(destination);

        // TODO : Tramsformer msg en byte[]
        // buffer = msg.getBytes();
        buffer = new byte[]{0x60, 0x03, 0x05, 0x60, 0x18, 0x00, 0x0B, 0x26, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x11};


        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);
        socket.send(packet);
        /*packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        String received = new String(packet.getData(), 0, packet.getLength());*/
        return "received";
    }

    public void closeSocket() {
        singleDatagramSocket.close();
    }
}