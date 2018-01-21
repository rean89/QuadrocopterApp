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
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
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
    private static ConnectionListener listener;

    /**
     * Socket used to create the connection.
     */
    private Socket socket;

    /**
     * IP address of the server.
     */
    private static final String SERVER_IP = "192.168.10.1";

    /**
     * Port of the server.
     */
    private static final int SERVER_PORT = 8000;

    /**
     * Used to send data to the server.
     */
    private static PrintWriter writer;

    /**
     * Used to receive data from the server.
     */
    private static BufferedReader reader;


    public TCPClient(ConnectionListener cListener) {
        listener = cListener;
        socket = new Socket();
    }

    public void sendData(String msg) {
        new SendDataTask().execute(msg);
    }

    public void reqData(String msg) {
        new ReqDataTask().execute(msg);
    }

    /**
     * Test, if the host is reachable.
     * @return
     */
    public boolean isConnected() {
        try {
            Log.d(TAG, "Con. state: " + socket.isConnected());
            return socket.isConnected() && socket.getInetAddress().isReachable(500);
        } catch (Exception e) {
            Log.d(TAG, "Host not reachable or not connected.");
        }
        return false;
    }

    public boolean connect() {
        if (!isConnected()) {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    try {

                        InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
                        InetSocketAddress socketAddr = new InetSocketAddress(serverAddr, SERVER_PORT);
                        socket.connect(socketAddr, 500);
                        socket.setSoTimeout(100);

                        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                                socket.getOutputStream())));
                        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        Log.d(TAG, "Connected to the server");
                    } catch (UnknownHostException hostError) {
                        Log.d(TAG, "Failed to connect: " + hostError.toString());
                    } catch (IOException ioError) {
                        Log.d(TAG, "Failed to connect: " + ioError.toString());
                    } catch (Exception error) {
                        Log.d(TAG, "Failed to connect: " + error.toString());
                    }
                }
            };
            thread.start();

        }
        return isConnected();
    }

    public void close() {
        try {
            socket.close();
        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    private static class ReqDataTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... msg) {
            Log.d(TAG, "Start ReqTask: " + msg[0]);

            /*if (!isConnected()) {
                Log.d(TAG, "Req data, but not connected.");
                //connect();
                return "";
            }
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
            }*/
            if (writer != null && !writer.checkError()) {
                writer.print(msg[0]);
                writer.flush();
            } else {
                return msg[0];
            }

            int dataLength = 0;
            char[] data = null;
            char[] buffer = new char[100];
            try {
                if(reader != null) {
                    dataLength = reader.read(buffer);
                }
            } catch (IOException error) {
                Log.d(TAG, "Error rec msg: " + error.toString());
            }
            data = new char[dataLength];
            System.arraycopy(buffer, 0, data, 0, dataLength);
            return new String(data);
        }

        protected void onPostExecute(String msg) {
            if (msg != null && msg.length() != 0) {
                listener.dataReceived(msg);
                Log.d(TAG, "End ReqTask: " + msg);
            }
        }
    }

    private static class SendDataTask extends AsyncTask<String, Integer, String> {
        protected String doInBackground(String... msg) {
            Log.d(TAG, "Start SendTask: " + msg[0]);

            /*if (!isConnected()) {
                Log.d(TAG, "Req data, but not connected.");
                return "";
            }*/

            /*if(writer == null) {
                try {

                    InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
                    socket = new Socket(serverAddr, SERVER_PORT);

                    writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                            socket.getOutputStream())));
                    reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                } catch (Exception error) {
                    Log.d(TAG, "Failed to connect: " + error.toString());
                }
            }*/
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
