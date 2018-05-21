package com.simplej.rest.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.simplej.rest.entity.Client;

public class ClientDB {
	
	public static HashMap<Integer, Client> clients = new HashMap<>();
    static{
    	clients.put(1, new Client(1, "Lokesh", "Gupta", "test@email.com"));
    	clients.put(2, new Client(2, "John", "Gruber", "test2@email.com"));
    	clients.put(3, new Client(3, "Melcum", "Marshal", "test3@email.com"));
    }
    
    public static List<Client> getClients(){
        return new ArrayList<Client>(clients.values());
    }

}
