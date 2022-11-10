package com.myjava.mydomogest.entities;

        /*
        - Octet 0 : Commande d’interface (à ne pas confondre avec la commande CAN).
        - Octet 1 : PCID : Identifie le PC à qui on répond (voir note)
        - Octet 2 : Nombre d’octets de Data d’interface de la trame, de 0 à 12
        - Octet 3 à x : Data d’interface = informations utiles de la trame, nombre = octet 2
        - Octets x+1 à 14 : octets inutiles (dummy bytes), toujours à 0x00
        - Octet 15 : Checksum = reste de la division par 256 de la somme des octets 0 à 14
        */

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InterfaceTrame {
    private final Integer TAILLE_TRAME_INTERFACE = 16;

    private byte cdeInterface;
    private byte pcid;







}


