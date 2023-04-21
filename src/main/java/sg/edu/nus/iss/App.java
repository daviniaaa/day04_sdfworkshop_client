package sg.edu.nus.iss;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {

        // console input from keyboard
        // variable to save keyboard inputs
        // variable to save msgReceived
        Console cons = System.console();
        String input = "";
        String msgReceived = "";
        
        // args[0] for port number
        String serverHost = args[0];
        String serverPort = args[1];

        // slide 8 - establish server connection
        // ** server side must be started first
        Integer portNum = Integer.parseInt(serverPort);
        Socket socket = new Socket(serverHost, portNum);

        try (InputStream is = socket.getInputStream()) {
            BufferedInputStream bis = new BufferedInputStream(is);
            DataInputStream dis = new DataInputStream(bis);

            try (OutputStream os = socket.getOutputStream()) {
                BufferedOutputStream bos = new BufferedOutputStream(os);
                DataOutputStream dos = new DataOutputStream(os);

                // while loop to keep going
                while(!input.equals("quit")) {
                    input = cons.readLine(">> ");
                    if (!input.equals("get-cookie")) {
                        continue;
                    }

                    // send message out across communication tunnel
                    dos.writeUTF(input);
                    dos.flush();

                    // receive message from server (the response to client input) and process it
                    String line = dis.readUTF();
                    System.out.println(line);
                    

                }
                


                dos.close();
                bos.close();
                os.close();

            } catch (EOFException e) {
                
            }

            dis.close();
            bis.close();
            is.close();
        }


        socket.close();

    }
}
