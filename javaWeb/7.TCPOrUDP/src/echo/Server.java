package echo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {
    public static void main(String[] args) throws IOException {
        //1.新建一个DatahramSocket
        DatagramSocket datagramSocket = new DatagramSocket(8989);
        byte[] bytes = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
        //2.等着客户端来撩
        datagramSocket.receive(datagramPacket);

        InetAddress address = datagramPacket.getAddress();
        System.out.println("我从ip："+address.getHostAddress()+"位置收到了"+datagramPacket.getLength()+"字节的数据");
        System.out.println("消息|为"+new String(datagramPacket.getData(),0,datagramPacket.getLength(),"UTF-8"));

        //最后关闭资源
        datagramSocket.close();
    }
}
