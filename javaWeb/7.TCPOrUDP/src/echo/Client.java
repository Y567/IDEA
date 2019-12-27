package echo;

import java.io.IOException;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        //客户端
        DatagramSocket datagramSocket = new DatagramSocket();
        String message = "ansabefuewb";
        byte[] bytes = message.getBytes("UTF-8");
        //创建服务器的Ip地址byte数组
        byte[] serverIp = new byte[4];
        serverIp[0] = 127;
        serverIp[1] = 0;
        serverIp[2] = 0;
        serverIp[3] = 1;
        InetAddress byAddress = InetAddress.getByAddress(serverIp);
        DatagramPacket senddata = new DatagramPacket(bytes, bytes.length, byAddress, 8989);
        datagramSocket.send(senddata);
        datagramSocket.close();
    }
}
