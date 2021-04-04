/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicplayer;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.*;
import javax.jms.TextMessage;
import youtubeAPI.JSONYoutube;
import youtubeAPI.YoutubeProvider;

/**
 *
 * @author Filip
 */
public class Main {
    
    @Resource(lookup = "ProjekatConnectionFactory")
    private static ConnectionFactory connectionFactory;
    
    @Resource(lookup = "PlayerSongQueue")
    private static Queue playerSongQueue;

    public static void main(String[] args) {
        JMSContext context = connectionFactory.createContext();
        JMSConsumer consumer = context.createConsumer(playerSongQueue);
        System.out.println("Music player started.");
        
        while (true) {
            try {
                TextMessage message = (TextMessage)consumer.receive();
                String songName = message.getText();
                
                if (message != null) {
                    PlaySongOnYoutube(songName);
                }
            } catch (JMSException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void PlaySongOnYoutube(String songName) {
        try {
            songName = songName.replaceAll(" ", "+");
            YoutubeProvider provider = new YoutubeProvider();
            
            JSONYoutube msg = provider.SearchForSongOnYoutube(songName);
            Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v=" + msg.getItems().get(0).getId().getVideoId()));
        } catch (IOException | URISyntaxException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
