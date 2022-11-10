package com.myjava.mydomogest.old;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ConnexionUdpProvider {
    private static DatagramSocket singleDatagramSocket;
    private static final String destination = "192.168.1.53";
    private static InetAddress address;
    private final int port = 1470;

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
}