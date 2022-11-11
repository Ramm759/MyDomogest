package com.myjava.mydomogest.service;

import com.myjava.mydomogest.entities.TrameInterface;
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

    public String send(TrameInterface trameInterface) throws IOException {
        // TODO : Corriger Exception
        // Récupération de la connexion udp
        DatagramSocket socket = getSingleDatagramSocket();

       // byte[] trame = new byte[]{0x60, 0x64, 0x05, 0x60, 0x18, 0x00, 0x0B, 0x26, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x72};

        // Création de la trame à envoyer
        String[] trameString = new String[16];

        trameString[0] = trameInterface.getCdeInterface();
        trameString[1] = trameInterface.getPcid();
        trameString[2] = trameInterface.getTrameCanSize();

        String[] trameCan = trameInterface.getTrameCan();
        trameString[3] = trameCan[0];
        trameString[4] = trameCan[1];
        trameString[5] = trameCan[2];
        trameString[6] = trameCan[3];
        trameString[7] = trameCan[4];

        String[] dummy = trameInterface.getDummy();
        trameString[8] = dummy[0];
        trameString[9] = dummy[1];
        trameString[10] = dummy[2];
        trameString[11] = dummy[3];
        trameString[12] = dummy[4];
        trameString[13] = dummy[5];
        trameString[14] = dummy[6];

        trameString[15] = trameInterface.getChecksum();

        // Conversion String / byte
        byte[] trame = new byte[16];
        int index = 0;
        for (String stringEnCours : trameString) { // format : 0xF6
            Integer octetEncoursInteger = Integer.parseInt(stringEnCours.substring(stringEnCours.length() - 2), 16); // radix : base 16
            byte octetEnCoursByte = octetEncoursInteger.byteValue();
            trame[index] = octetEnCoursByte;
            index++;
        }

        // Création du message
        DatagramPacket packet = new DatagramPacket(trame, trame.length, address, port);

        socket.send(packet);
        LOGGER.debug(String.valueOf(trameString));
        //ecoute();
        // TODO : retourner la réponse
        return "receveid";
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

            // TODO : convertir en hexa
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