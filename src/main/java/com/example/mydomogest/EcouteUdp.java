package com.example.mydomogest;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

class EcouteUdp {
    final static int port = 1470;
    final static int taille = 16;
    final static byte buffer[] = new byte[taille];

    public static void main(String argv[]) throws Exception {
        DatagramSocket socket = new DatagramSocket(port);
        while (true) {
            DatagramPacket data = new DatagramPacket(buffer, buffer.length);
            socket.receive(data);

            byte[] trameRecue = data.getData();

            System.out.println(trameRecue);

            String chaine = "";
            String chaineHexa = "";

            for (byte octetEnCours : trameRecue) {
                //System.out.println(octetEnCours);
                String  octetEnCoursHexa = Integer.toHexString(octetEnCours);
                //System.out.println("Hexadecimal = " + Integer.toHexString(res));
                //System.out.println(octetEnCours);
                chaine = chaine + octetEnCours + ",";
                chaineHexa = chaineHexa + octetEnCoursHexa + ",";
            }
            System.out.println("Décimal : "+ chaine);
            System.out.println("Hexa : " + chaineHexa);
            System.out.println("");



            /*System.out.println(data1[0]);
            System.out.println(data1[1]);*//*

            // System.out.println(data.getAddress());

        }*/


            // Volet haut : [70,FF,05] [50,18,01,00] [32]
            // Volet bas : [70,FF,05] [50,18,01,01] [32]
        }
    }
}