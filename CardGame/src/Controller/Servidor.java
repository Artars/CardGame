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
    private String port = "12345";
    private boolean stop;
    
    public Servidor(MultController controller, boolean isHost, String port) {
        this.controller = controller;
        host = isHost;
        ip = "127.0.0.1";
        stop = false;
    }
    
    public Servidor(MultController controller, String port) {
        this.controller = controller;
        host = true;
        ip = "127.0.0.1";
        stop = false;
    }
    
    public Servidor(MultController controller, String ip, String port) {
        this.controller = controller;
        this.ip = ip;
        this.port = port;
        host = false;
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
                ServerSocket servidor = new ServerSocket(Integer.parseInt(port));
                System.out.println("Porta " + port + " aberta!");
                cliente = servidor.accept();
                System.out.println("Nova conexão com o cliente " +
                        cliente.getInetAddress().getHostAddress()
                );
            }
            else {
                cliente = new Socket(ip, Integer.parseInt(port));
                System.out.println("O cliente se conectou ao servidor!");
            }
            BufferedReader s = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            //Scanner s = new Scanner(cliente.getInputStream());
            PrintStream saida = new PrintStream(cliente.getOutputStream());
            output = new PrintWriter(saida);
            controller.conseguiuConectar();
            
            output.println("Teste");
            String in;
            try {
                while ((in = s.readLine()) != null) {
                    System.out.println(in);
                    controller.receberComando(in);
                }
            }
            catch(IOException e) {
                System.out.println("Desconectou");
                controller.receberComando("Sair");
                s.close();
                output.close();
                cliente.close();
            }
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
