package sg.edu.nus.iss;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        // args[0] for port number
        String port = args[0];

        // slide 8 - establish server connection
        Integer portNum = Integer.parseInt(port);
        Socket socket = new Socket("localhost", portNum);

        try (InputStream is = socket.getInputStream()) {
            BufferedInputStream bis = new BufferedInputStream(is);
            DataInputStream dis = new DataInputStream(bis);
            String line = dis.readUTF();

            System.out.println(line);
        }

    }
}
