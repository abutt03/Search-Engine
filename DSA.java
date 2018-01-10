package webSearch;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import org.xml.sax.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class DSA extends DefaultHandler {
 static int count = 1;
 static int tcounter = 0;
 static int idcounter = 0;

static String ide;
static String stitle;
static int wordcount;
static int titlecount = 0;
static String lastLine = "";
static String sCurrentLine = "";
static int PageRank = 0;
    public static void main(String[] args) {
    	
        //SAXParser and SAXParserFActory is used to parse the document
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = null;
        
        //StringBuilder is used to build a string of the lines read 
        //inside the text tag
        StringBuilder sb = new StringBuilder(64);
        StringBuilder sb1 = new StringBuilder(64);
        
        
        try {
            saxParser = factory.newSAXParser();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(DSA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(DSA.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        DefaultHandler handler = new DefaultHandler(){
        
        	//the required boolean variables are set to false
            boolean title = false;
            boolean id = false;
            boolean text = false;            
            boolean idcheck = false;
            
            //performs an action when an opening tag of a tag is reached 
            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes)
                    throws SAXException {
                if (qName.equalsIgnoreCase("title")) {
                    title = true;
                }
                else if (qName.equalsIgnoreCase("id") && !idcheck) {
                    id = true;
                }
                else if (qName.equalsIgnoreCase("text")) {
                    text = true;
                }
            }
            int id_counter =0;
            
            //performs an action when the ending tag of a tag is reached
            @Override
            public void endElement(String uri, String localName, String qName) throws SAXException {
              
                if (qName.equalsIgnoreCase("text")) {
                    text =false;
                    id_counter=0;
                    idcheck=false;                    
                }
                String ide1 = "";
                if (qName.equalsIgnoreCase("page")) {
                    count++;
                    String b = new String(sb.toString());
                    String title2;
                    String del[] = {"\\bin\\b","\\bthe\\b","\\ba\\b","\\band\\b","\\bor\\b","\\ban\\b","\\bif\\b","\\bof\\b",
                        "\\bam\\b","\\bthen\\b","\\bthen\\b","\\bthan\\b","\\bfor\\b","\\bwas\\b","\\bwere\\b","\\bhas\\b",
                        "\\bhave\\b","\\bhad\\b","\\bwill\\b","\\bas\\b","\\bsuch\\b","\\bthat\\b","\\bto\\b","\\bwhich\\b",
                        "\\band\\b","\\bhe\\b","\\bshe\\b","\\while\\b", "\\bby\\b", "\\bis\\b","\\bon\\b","\\babove\\b",
                        "\\bbelow\\b","\\bthey\\b","\\bdid\\b"};
                    
                    //converts the lines read from the document 
                    //to lower case to make parsing easier
                    b = b.toLowerCase();
                    title2 = stitle.toLowerCase();
                    
                    //removes the stopping words
                    for(int i = 0; i < del.length; i++)
                    {
                        b = b.replaceAll(del[i], " ");
                        title2 = title2.replaceAll(del[i], " ");
                    }
                    
                    //splits the line wherever a space occurs between two word
                    //and stores each word in an array  
                    String arr[] = b.toString().split("\\s+");
                    String arr1[] = title2.toString().split("\\s+");
                    String hash[] = title2.toString().split("\\s+");
                    
                  //counts number of times each word occurs on the page
                    for(int i = 0; i < arr1.length; i++)
                    {
                    	int titlecount = 0;
                        for(int j = 0; j < arr1.length; j++)
                        {
                            if(arr1[i].equals(arr1[j]))
                            {
                                titlecount++;
                            }
                        }
                        hash[i] = arr1[i] + "," + titlecount;
                    }
                    
                    //counts number of times each word occurs on the page
                    for(int i = 0; i < arr.length; i++)
                    {
                        int h=0;
                        
                        if(!(arr[i]=="")){                        
                        int wordcount = 0;
                        String titleword[];
                        String countTitle = "0";
                        for(int j = 0; j < arr.length; j++)
                        {
                            if(arr[i].equals(arr[j]))
                            {
                                wordcount++;
                                if(i!=j){
                                arr[j]="";}
                            }
                        }
                        for(int j = 0; j < hash.length; j++)
                        {
                            titleword = hash[j].split(",");
                            if(arr[i].equals(titleword[0]))
                            {
                                countTitle = titleword[1];
                            }
                        }
                    
                    //creates the directory file for each word
                        File f = null;
                        try{
                            
                                f = new File("C:\\Users\\Abdullah Butt\\Documents\\NetBeansProjects\\DSA\\src\\dir\\"+ arr[i] +".txt");
                                String sCurrentline;
                                String lastLine= null;
                                String[] word;
                                PageRank = Integer.parseInt(countTitle)* 100 + wordcount;
                                String d=ide + "\t" + PageRank + "\t" + wordcount + "\t" + countTitle + "\t" + stitle;
                                try(FileWriter fw = new FileWriter("C:\\Users\\Abdullah Butt\\Documents\\NetBeansProjects\\DSA\\src\\dir\\"+ arr[i] +".txt", true);
                                BufferedWriter bw = new BufferedWriter(fw);
                                PrintWriter out = new PrintWriter(bw))
                                {   if(h==0){
                                    out.println(d);
                                    h++;}
                                }
                        }catch (IOException e) {
                                    //exception handling left as an exercise for the reader
                        }
                        catch(Exception ex){
                            System.out.println(ex);
                        }
                        }
                    }
                    System.out.println(count + "-----" + ide);
                    PageRank = 0;
                    sb.setLength(0);
                    StringBuilder sb = new StringBuilder();
                }
            }
            
            //reads the content between the opening and closing tags
            @Override
            public void characters(char ch[], int start, int length) throws SAXException {
                if (title) {
                    stitle = new String(ch,start,length);
                    title = false;
                    tcounter++;
                }
                else if (id) {
                    idcheck=true;
                    ide = new String(ch,start,length);
                    id = false;
                }
                else if (text) {
                    for(int i=0;i<length;i++)
                    {
                        if(ch[i] == '$'||ch[i] == '&'||ch[i] == '\\'||ch[i] == '/'||ch[i] == ':'||ch[i] == '\''||ch[i] == '\"'||ch[i] == '!'||ch[i] == '#'||ch[i] == '^'||ch[i] == '*'||ch[i] == '('||ch[i] == ')'||ch[i] == '{'||ch[i] == '}'||ch[i] == '['||ch[i] == ']'||ch[i] == '|'||ch[i] == '+'||ch[i] == '='||ch[i] == '-'||ch[i] == '_'||ch[i] == ','||ch[i] == '.'||ch[i] == '?'||ch[i] == '<'||ch[i] == '>'){
                            ch[i] = ' ';
                        }
                    }
                    String body = new String(ch,start,length);
                    sb.append(body);
                }
            }
        };
        //Used to parse the xml file
        try {
            saxParser.parse("C:\\Users\\Abdullah Butt\\Documents\\NetBeansProjects\\DSA\\src\\original.xml", handler);
        } catch (SAXException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }   
}