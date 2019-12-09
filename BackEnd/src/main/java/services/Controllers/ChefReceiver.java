package services.Controllers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

import static utils.SocketResources.HEADER;
import static utils.SocketResources.PACKET;
import static utils.SocketResources.PORT;
import static utils.SocketResources.decodeByte;


public class ChefReceiver {

    public static String getMessage(InetAddress rcvAddr) throws IOException
    {
        String input;
        byte[] storePad = new byte [PACKET+HEADER];	// create array to send ACK
        byte[] fileBytes = new byte [PACKET+HEADER];	// create the array for the packets

        try
        {
            ServerSocket servSockPort = new ServerSocket (PORT);
            Socket sockPort = servSockPort.accept();
            DataOutputStream outStream = new DataOutputStream(sockPort.getOutputStream());	// stream for sending replies
            DataInputStream inStream = new DataInputStream(sockPort.getInputStream());	// stream for receiving data
//            System.out.println("Beginning Transfer.");
//            input = "Control Transfer";
//            outStream.write(input.getBytes()); // switch control to the sending side
            inStream.read(fileBytes);	// get initial connection data

            int k, checkSum;
            while (true)
            {
                k = decodeByte(fileBytes[2]);	// pull the length out
                checkSum = 0;
                for (int n = 0; n < k; n++)	// iterate over the data
                {
                    checkSum += decodeByte(fileBytes[HEADER+n]);
                    checkSum = checkSum % 256;
                }
                if (fileBytes[1] != (byte)checkSum)	// verify checksum
                {
                    outStream.write(storePad);	// error, resend previous ACK
                    inStream.read(fileBytes);
                }
                else
                {	break;	}
            }
            System.arraycopy(fileBytes, 0, storePad, 0, HEADER+k);	// move data over to reply buffer
            storePad[0] += 1;	// change type from connection (0) to connection ACK (1)
            outStream.write(storePad);	// send the ACK back
            input = new String(fileBytes, HEADER, k);	// get the name and length
            System.out.println("Result: " + input);

            System.out.println("File received from TCP.");
            outStream.close();
            inStream.close();
            sockPort.close();
            servSockPort.close();
            return input;
        }
        catch (Exception E)
        {
            return E.getMessage();
        }
    }
}

