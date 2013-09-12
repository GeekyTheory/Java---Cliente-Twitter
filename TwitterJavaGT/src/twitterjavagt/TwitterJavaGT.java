package twitterjavagt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
public class TwitterJavaGT {
    TwitterJavaGT() throws TwitterException, MalformedURLException {
        Twitter twitter;
        ConfigurationBuilder configBuilder = new ConfigurationBuilder();
        String Token = new String(); //Los almaceno en un string, ya que pueden variar según la cuenta
        String TokenSecret = new String();
        File archivo = null;
        FileReader fileR = null;
        BufferedReader lecturaTeclado = null;
        try {
            // Apertura del fichero y creacion de BufferedReader
            archivo = new File(System.getProperty("user.home")+"/auth_file.txt".replace("\\","/"));
            fileR = new FileReader(archivo);
            lecturaTeclado = new BufferedReader(fileR);
            // Lectura del fichero
            String linea = new String();
            int n = 1;
            while ((linea = lecturaTeclado.readLine()) != null) {
                if (n == 1) { //La primera línea es el Access Token
                    //System.out.println(linea);
                    Token = linea;
                } else if (n == 2) { //La segunda línea es el Access Token Secret
                    //System.out.println(linea);
                    TokenSecret = linea;
                }
                n++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta
            // una excepción.
            try {
                if (null != fileR) {
                    fileR.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
            configBuilder.setDebugEnabled(true)
                    .setOAuthConsumerKey(new Tokens().OAuthConsumerKey)
                    .setOAuthConsumerSecret(new Tokens().OAuthConsumerSecret)
                .setOAuthAccessToken(Token)
                .setOAuthAccessTokenSecret(TokenSecret);
        twitter = new TwitterFactory(configBuilder.build()).getInstance();
        //Recuperar listado de ultimos tweets escritos
        
        
        TwitterFrame ventana=new TwitterFrame(twitter);
        //Actualizar tu estado
        //Status tweetEscrito = twitter.updateStatus("[TUTORIAL] Twitter+Java @geekytheory www.geekytheory.com");
    }
    public static void main(String ar[]) throws TwitterException, IOException, URISyntaxException {
        try{
            TwitterJavaGT twitter = new TwitterJavaGT();
        } catch(TwitterException ex){
            AutorizacionVentana auth=new AutorizacionVentana();
        }
    }
}
