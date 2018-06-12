package org.duodo;

import org.duodo.cmpp3s.service.CmppServerService;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author sys53
 * @date 2018/6/12
 */
public class CmppServer {
    public static void main(String[] args) throws IOException {
        CmppServerService serverService = new CmppServerService();
        System.out.println( "正在启动 CMPP　Server ..." );
        serverService.downstreamServiceInit();
        serverService.duplexstreamDeliverServiceInit();
        serverService.upstreamDeliverServiceInit();
        System.out.println( " CMPP　Server 已启动" );
        System.out.println( " 输入q|quit|exit　将退出服务。" );
        String cmd = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (!isExistCmd( cmd )) {
                cmd = br.readLine();
        }
        System.out.println( " CMPP　Server 已停止 ." );
    }

    private static boolean isExistCmd(String cmd) {
        return "q".equalsIgnoreCase( cmd ) || "quit".equalsIgnoreCase( cmd ) || "exit".equalsIgnoreCase( cmd );
    }
}
