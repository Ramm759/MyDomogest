package com.example.mydomogest;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class EmetteurUdp {
    static final String DESTINATION = "192.168.1.53";
    static final int PORT = 1470;
    static final int DELAI = 2000;

    public static void emettre(String chaine) {
        try {
            byte tampon[] = chaine.getBytes();

            //byte[] tampon = {0x60,0x03,0x05,0x60,0x18,0x00,0x0B,0x26,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x11};

            InetAddress adresse = InetAddress.getByName(DESTINATION);

            DatagramPacket paquet = new DatagramPacket(tampon, 0, tampon.length, adresse, PORT);

            DatagramSocket socket = new DatagramSocket();
            socket.setBroadcast(true);
            socket.send(paquet);

            /*while (true) {
                socket.send(paquet);

                try {
                    Thread.sleep(DELAI);
                } catch( InterruptedException ie ) {}
            }*/
        } catch (Exception e) {
            System.err.println("Houston we have a problem");
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String args[]) {
        emettre("0x60,0x03,0x05,0x60,0x18,0x00,0x0B,0x26,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x11");

        //emettre("60030560180008220000000000000001");


        //emettre("`\\03\\05`\\18\\00\\08\"\\00\\00\\00\\00\\00\\00\\00\\01");
    }
}