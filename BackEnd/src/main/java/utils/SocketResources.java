package utils;

public class SocketResources {
    public static final int HEADER = 3;
    public static final int PACKET = 250;
    public static final int PORT = 1031;

    public static int decodeByte(byte input)	// fixes the 2's complement byte issue
    {
        if (input >= 0)
        {	return (int) input;	}
        else
        {	return 256 + (int) input;	}
    }
}
