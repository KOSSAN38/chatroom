import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author magnus
 */
public class Server {
    public static void main(String[] args) {
        int port = 1234;
        boolean run = true;
        ServerSocket serverSocket;
        Socket socket;
        System.out.println("Server started.");

        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                System.out.println("Waiting for connections!");
                socket = serverSocket.accept();
                // Go
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                System.out.println("Client connected!");

                //Protocol
                String name = in.readLine();
                if (name.equals("Shutdown")) {
                    out.println("SERVER: Oh no, you terminated me...");
                    in.close();
                    out.close();
                    socket.close();
                    System.out.println("server shutting down...");
                    System.exit(0);
                } else {
                    while (run) {
                        System.out.println(name);
                        System.out.println("Sending feedback");
                        out.println("SERVER: Varför heter du " + name + "?! Keep up the good work??");
                        name = in.readLine();
                    }
                    try {
                        while (run) {
                            out.println(JOptionPane.showInputDialog(null, "Nu", "Prata med din kompis"));
                        }
                    } catch (Exception e) {
                        System.out.println("Client failed to communicate");
                    }
                    in.close();
                    out.close();
                    socket.close();

                    System.out.println("Closing down " + name);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
