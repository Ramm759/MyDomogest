package com.myjava.mydomogest.service;

import com.myjava.mydomogest.entities.TrameInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TrameInterfaceService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UdpService.class);

    public TrameInterface generateTrameInterface(){

        //byte[] trame = new byte[]{0x60, 0x63, 0x05, 0x60, 0x18, 0x00, 0x0B, 0x26, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x71};

        String cdeInterface = "0x60";
        String pcid = generatePcid();
        String trameCanSize = "0x05";
        String[] trameCan = new  String[] {"0x60", "0x18", "0x00", "0x0B", "0x26"};
        String[] dummy = new String[]  {"0x00", "0x00", "0x00", "0x00", "0x00", "0x00", "0x00"};
        String Checksum = checksun(cdeInterface, pcid, trameCanSize, trameCan, dummy);

        TrameInterface trameInterface = new TrameInterface(cdeInterface, pcid, trameCanSize, trameCan, dummy, Checksum);

        return new TrameInterface(cdeInterface, pcid, trameCanSize, trameCan, dummy, Checksum);
    }

    public String checksun(String cdeInterface, String pcid, String trameCanSize, String[] trameCan, String[] dummy){
        byte[] liste = new byte[16];
        int index = 0;
        String stringEnCours;
        byte octetEnCoursByte;

        stringEnCours = cdeInterface;

        // Ajout cdeInterface
        octetEnCoursByte = convertOctetEnCoursByte(stringEnCours);
        liste[index] = octetEnCoursByte;
        index++;

        // Ajout pcid
        stringEnCours = pcid;
        octetEnCoursByte = convertOctetEnCoursByte(stringEnCours);
        liste[index] = octetEnCoursByte;
        index++;

        // Ajout trameCanSize
        stringEnCours = trameCanSize;
        octetEnCoursByte = convertOctetEnCoursByte(stringEnCours);
        liste[index] = octetEnCoursByte;
        index++;

        // Ajout trameCan
        for (String enCours : trameCan){
            stringEnCours = enCours;
            octetEnCoursByte = convertOctetEnCoursByte(stringEnCours);
            liste[index] = octetEnCoursByte;
            index++;
        }

        // Ajout dummy
        for (String enCours : dummy){
            stringEnCours = enCours;
            octetEnCoursByte = convertOctetEnCoursByte(stringEnCours);
            liste[index] = octetEnCoursByte;
            index++;
        }

        // Calcul du cheksum
        String cheksum = "0x";
        Integer total = 0;
        for (byte byteEnCours : liste){
            total += byteEnCours;
        }

        return "0x" + Integer.toHexString((total % 256));
    }

    // Conversion d'un octet String en byte
    public static byte convertOctetEnCoursByte(String stringEnCours) {
        Integer octetEncoursInteger = Integer.parseInt(stringEnCours.substring(stringEnCours.length() - 2), 16); // radix : base 16
        byte octetEnCoursByte = octetEncoursInteger.byteValue();
        return octetEnCoursByte;
    }


    public String generatePcid() { // Le Pcid est le dernier octet de l'Ip locale
        InetAddress ip = null;
        try {
            ip = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        String hostAddress = ip.getHostAddress();
        return "0x" + hostAddress.substring(hostAddress.length() - 2);
    }
}
