package de.hs_heilbronn.stud.areinsch.dronecontrol.drone.connection;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by AnAnd on 21.12.2017.
 */

/**
 * Creates a simple tcp connection to a socket server.
 * Send and receive data.
 */
public class TCPClient {

    /**
     * Tag for logs.
     */
    private static final String TAG = "MY_CLIENT";

    /**
     * Listens for new data.
     */
    private ConnectionListener listener;

    /**
     * Socket used to create the connection.
     */
    private Socket socket;

    /**
     * IP address of the server.
     */
    private static final String SERVER_IP = "192.168.0.105";

    /**
     * Port of the server.
     */
    private static final int SERVER_PORT = 8000;

    /**
     * Used to send data to the server.
     */
    private PrintWriter writer;

    /**
     * Used to receive data from the server.
     */
    private BufferedReader reader;


    public TCPClient(ConnectionListener listener) {
        this.listener = listener;
    }

    public void sendData(String msg) {
        new SendDataTask().execute(msg);
    }

    public void reqData(String msg) {
        new ReqDataTask().execute(msg);
    }

    public void close() {
        try {
            socket.close();
        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    private class ReqDataTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... msg) {
            Log.d(TAG, "Start ReqTask: " + msg[0]);
            if(writer == null) {
                try {

                    InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
                    socket = new Socket(serverAddr, SERVER_PORT);

                    writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                            socket.getOutputStream())));
                    reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                } catch (UnknownHostException hostError) {
                    Log.d(TAG, "Failed to connect: " + hostError.toString());
                } catch (IOException ioError) {
                    Log.d(TAG, "Failed to connect: " + ioError.toString());
                } catch (Exception error) {
                    Log.d(TAG, "Failed to connect: " + error.toString());
                }
            }
            if (writer != null && !writer.checkError()) {
                writer.print(msg[0]);
                writer.flush();
            }

            int dataLength = 0;
            char[] data = null;
            char[] buffer = new char[100];
            try {
                dataLength = reader.read(buffer);
            } catch (Exception error) {
                Log.d(TAG, "Error rec msg: " + error.toString());
            }
            data = new char[dataLength];
            System.arraycopy(buffer, 0, data, 0, dataLength);
            return new String(data);
        }

        protected void onPostExecute(String msg) {
            Log.d(TAG, "End ReqTask: " + msg);
            listener.dataReceived(msg);
        }
    }

    private class SendDataTask extends AsyncTask<String, Integer, String> {
        protected String doInBackground(String... msg) {
            Log.d(TAG, "Start SendTask: " + msg[0]);
            if(writer == null) {
                try {

                    InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
                    socket = new Socket(serverAddr, SERVER_PORT);

                    writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                            socket.getOutputStream())));
                    reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                } catch (Exception error) {
                    Log.d(TAG, "Failed to connect: " + error.toString());
                }
            }
            if (writer != null && !writer.checkError()) {
                writer.println(msg[0]);
                writer.flush();
            }
            return "";
        }
        protected void onPostExecute(String msg) {
            Log.d(TAG, "End SendTask");
        }
    }
}
