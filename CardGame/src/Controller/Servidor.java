/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Arthur
 */
public class Servidor implements Runnable {
    private MultController controller;
    private PrintWriter output;
    private boolean host;
    private String ip;
    private boolean stop;
    
    public Servidor(MultController controller, boolean isHost) {
        this.controller = controller;
        host = isHost;
        ip = "127.0.0.1";
        stop = false;
    }
    
    public Servidor(MultController controller) {
        this.controller = controller;
        host = true;
        ip = "127.0.0.1";
        stop = false;
    }
    
    public Servidor(MultController controller, String ip) {
        this.controller = controller;
        this.ip = ip;
        stop = false;
    }
    
    public void setHost(boolean host) {
        this.host = host;
    }
    
    public boolean getHost() {
        return host;
    }
    
    public void setIP(String ip) {
        this.ip = ip;
    }
    
    public void stop() {
        stop = true;
    }
    
    @Override
    public void run (){
        try {
            Socket cliente;
            if(host) {
                ServerSocket servidor = new ServerSocket(12345);
                System.out.println("Porta 12345 aberta!");
                cliente = servidor.accept();
                System.out.println("Nova conexão com o cliente " +
                        cliente.getInetAddress().getHostAddress()
                );
            }
            else {
                cliente = new Socket(ip, 12345);
                System.out.println("O cliente se conectou ao servidor!");
            }
            BufferedReader s = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            //Scanner s = new Scanner(cliente.getInputStream());
            PrintStream saida = new PrintStream(cliente.getOutputStream());
            output = new PrintWriter(saida);
            controller.conseguiuConectar();
            
            output.println("Teste");
            String in;
            while ( ((in = s.readLine()) != null) && !stop) {
                System.out.println(in);
                controller.receberComando(in);
            }
            write("Sair");
            s.close();
            output.close();
            cliente.close();
        }
        catch(IOException e) {
            System.out.println("Não foi possivel iniciar server");
        }
    }

    public void write(String s) {
        output.println(s);
        output.flush();
    }
    
}
