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

    public String send() throws IOException {
        // TODO : Corriger Exception

        // Récupération de la connexion udp
        DatagramSocket socket = getSingleDatagramSocket();

        // Création de la trameString à envoyer
        TrameInterfaceService trameInterfaceService = new TrameInterfaceService();
        TrameInterface trameInterface = trameInterfaceService.generateTrameInterface();

        String cdeInterface = trameInterface.getCdeInterface(); // 0x60
        String pcid = trameInterface.getPcid(); // 0x03
        String trameCanSize = trameInterface.getTrameCanSize(); // 0x05
        String[] trameCan = trameInterface.getTrameCan(); // 0x60, 0x18, 0x00, 0x0B, 0x26
        String[] dummy = trameInterface.getDummy(); // 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00


        // String checksum = trameInterface.getChecksum(); // 0x11


        String[] trameString = new String[16] ;
        trameString[0] = cdeInterface;
        trameString[1] = pcid;
        trameString[2] = trameCanSize;

        trameString[3] = trameCan[0];
        trameString[4] = trameCan[0];
        trameString[5] = trameCan[0];
        trameString[6] = trameCan[0];
        trameString[7] = trameCan[0];

        trameString[8] = dummy[0];
        trameString[9] = dummy[1];
        trameString[10] = dummy[2];
        trameString[11] = dummy[3];
        trameString[12] = dummy[4];
        trameString[13] = dummy[5];
        trameString[14] = dummy[6];

        trameString[15] = "0x11";

        // Conversion String / byte

        byte[] trame = new byte[16];
        int index = 0;
        for (String stringEnCours : trameString){
            String octetEnCoursString = stringEnCours.substring(stringEnCours.length()-2);
            Integer octetEncoursInteger = Integer.parseInt(octetEnCoursString);
            byte octetEnCoursByte = octetEncoursInteger.byteValue();

            //byte[] octetEnCoursByte = octetEnCours.getBytes();
            trame[index] = octetEnCoursByte;
            index++;
        }

        //byte[] trame = new byte[]{0x60, 0x03, 0x05, 0x60, 0x18, 0x00, 0x0B, 0x26, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x11}



        // Création du message
        DatagramPacket packet = new DatagramPacket(trame, trame.length, address, port);

        socket.send(packet);
        LOGGER.debug(String.valueOf(trameString));
        //ecoute();
        // TODO : retourner la réponse
        return "receveid";
    }

    public byte[] decodeHexString(String hexString) {
        if (hexString.length() % 2 == 1) {
            throw new IllegalArgumentException(
                    "Invalid hexadecimal String supplied.");
        }

        byte[] bytes = new byte[hexString.length() / 2];
        for (int i = 0; i < hexString.length(); i += 2) {
            bytes[i / 2] = hexToByte(hexString.substring(i, i + 2));
        }
        return bytes;
    }

    public byte hexToByte(String hexString) {
        int firstDigit = toDigit(hexString.charAt(0));
        int secondDigit = toDigit(hexString.charAt(1));
        return (byte) ((firstDigit << 4) + secondDigit);
    }

    private int toDigit(char hexChar) {
        int digit = Character.digit(hexChar, 16);
        if(digit == -1) {
            throw new IllegalArgumentException(
                    "Invalid Hexadecimal Character: "+ hexChar);
        }
        return digit;
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