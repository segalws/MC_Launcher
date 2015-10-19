/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.launcher.run;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 *
 * @author akahito
 */
public class Config {
    private static Config instance;
    private ConfigData data;
    private Config(){
        System.out.println("Config.load");
        data = new ConfigData();
        InputStream stream = this.getClass().getResourceAsStream("/config");
        String line;
        try(Scanner scanner = new Scanner(stream)) {
        	while(scanner.hasNext()) {
        		line = scanner.nextLine();
        		line=line.trim();
        		if(line.length()>1)
        		data.put(line.substring(0, line.indexOf('=')), line.substring(line.indexOf('=')+1));
        	}
        }
    }
    
    public static synchronized Config getInstance() {
        if(instance == null)
            instance = new Config();
        
        return instance;
    }
    
    public ConfigData getData() {
    	return data;
    }
}
