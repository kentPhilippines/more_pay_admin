package com.nacos;

import java.net.InetAddress;
import java.util.Arrays;

/**
 * @Auther: Jess
 * @Date: 2023/9/8 12:47
 * @Description:
 */
public class IPv6ToIPv4Converter {
    public static void main(String[] args) {
        try {
            // 获取IPv6地址
            InetAddress ip = InetAddress.getByName("240e:87c:8c9:548a:14b7:be48:b670:d180");

            // 判断IPv6地址是否合法
          //  boolean isIPv6 = ip.isIPv6Address();

//            if (!isIPv6) {
//                System.out.println("输入的不是一个合法的IPv6地址");
//                return;
//            }

            // 将IPv6地址转换为字节数组
            byte[] ipBytes = ip.getAddress();

            // 将字节数组转换为IPv4地址
            byte[] ipv4Bytes = Arrays.copyOfRange(ipBytes, 12, 16);

            // 输出IPv4地址
            String ipv4 = InetAddress.getByAddress(ipv4Bytes).getHostAddress();
            System.out.println(ipv4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
