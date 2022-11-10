package com.myjava.mydomogest.service;

import com.myjava.mydomogest.exceptions.UdpConnexionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.*;

public class UdpService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UdpService.class);
    private static int port;
    // Pour écoute
    private static int tailleTrame;
    // Connexion Udp (singleton)
    private static DatagramSocket singleDatagramSocket;
    private final String destination;
    private final byte[] buffer;
    // Adresse interface
    private final InetAddress address;

    public UdpService(String destination, int port, int tailleTrame) throws UnknownHostException {
        this.destination = destination;
        address = InetAddress.getByName(destination);
        UdpService.port = port;
        UdpService.tailleTrame = tailleTrame;
        buffer = new byte[tailleTrame];
    }

    // Création connexion udp
    public static DatagramSocket getSingleDatagramSocket() {
        if (singleDatagramSocket == null) {
            try {
                singleDatagramSocket = new DatagramSocket(port);
            } catch (SocketException e) {
                // TODO : Corriger Exception
                throw new UdpConnexionException("Pas de réponse de l'interface");
            }
        }
        return singleDatagramSocket;
    }

    public String send(byte[] trame) throws IOException {
        // TODO : Corriger Exception

        // Récupération de la connexion udp
        DatagramSocket socket = getSingleDatagramSocket();

        // Création du message
        DatagramPacket packet = new DatagramPacket(trame, trame.length, address, port);

        socket.send(packet);
        ecoute();
        // TODO : retourner la réponse
        return "received";
    }

    public void ecoute() {
        // Récupération de la connexion udp
        DatagramSocket socket = getSingleDatagramSocket();

        while (true) {
            DatagramPacket data = new DatagramPacket(buffer, buffer.length);
            try {
                socket.receive(data);
            } catch (IOException e) {
                // TODO : Corriger Exception
                throw new RuntimeException(e);
            }

            byte[] trameRecue = data.getData();

            String chaine = "";
            String chaineHexa = "";

            for (byte octetEnCours : trameRecue) {
                String octetEnCoursHexa = Integer.toHexString(octetEnCours);
                chaine = chaine + octetEnCours + ",";
                chaineHexa = chaineHexa + octetEnCoursHexa + ",";
            }
            System.out.println("Décimal : " + chaine);
            System.out.println("Hexa : " + chaineHexa);
            System.out.println();

        }
    }

    public void closeSocket() {
        singleDatagramSocket.close();
    }
}