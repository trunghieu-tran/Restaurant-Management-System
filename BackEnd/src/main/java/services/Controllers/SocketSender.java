package services.Controllers;
import java.io.*;
import java.util.*;
import java.net.*;

import static utils.SocketResources.HEADER;
import static utils.SocketResources.PACKET;
import static utils.SocketResources.PORT;
import static utils.SocketResources.decodeByte;

public class SocketSender {

    public static void sendMessage(InetAddress rcvAddr, String data)
    {
        byte[] storePad = new byte [PACKET+HEADER];	// create array to receive ACK
        byte[] fileBytes = new byte [PACKET+HEADER];	// create the array for the packets

        try
        {
            Socket sockPort = new Socket (rcvAddr, PORT);	// open the port connection
            DataOutputStream outStream = new DataOutputStream(sockPort.getOutputStream());	// create stream for receiving replies
            DataInputStream inStream = new DataInputStream(sockPort.getInputStream());	// create stream for getting data out
//            inStream.read(storePad);	// get initial connection

            byte[] temp = data.getBytes();	// create a byte array to hold the data
            fileBytes[2] = (byte) temp.length;	// store the length in the header
            System.arraycopy(temp, 0, fileBytes, HEADER, temp.length); // load up the data
            int checkSum = 0;
            for (int n = 0; n < temp.length; n++)	// iterate through the data
            {		// get the checksum
                checkSum += decodeByte(fileBytes[HEADER+n]);
                checkSum = checkSum % 256;
            }
            fileBytes[1] = (byte)checkSum;

            outStream.write(fileBytes);	// send the connection info
            inStream.read(storePad);	// receive ACK
            while (!Arrays.equals(fileBytes, storePad))	// verify ACK
            {
                if (storePad[0] != 1)	// should be an ACK
                {       // if not, resend
                    outStream.write(fileBytes);	// send the connection info
                    inStream.read(storePad);	// receive ACK
                }
            }

            System.out.println("File sent with TCP.");
            outStream.close();	// and close our resources
            inStream.close();
            sockPort.close();
        }
        catch (Exception E)
        {
            E.printStackTrace();
        }
    }
}
