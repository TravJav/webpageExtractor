package gerardo;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.System.in;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

public class Gerardo extends JFrame {

    final static Color HILIT_COLOR = Color.RED;
    public String find_string;
    public String line;
    public Scanner scan;
    public String web_page;
    public String phrase_wanted;
    public URL url;
    public JTextArea jta;
    public String getThis;
    public JScrollPane scroll;
    Highlighter hilit;
    Highlighter.HighlightPainter painter;
    public JTextField jtf;

    public Gerardo() {
        super("search String");
     
       
        jta = new JTextArea(5, 20);
        jta.setLineWrap(true);
        jta.setEditable(true);
        scroll = new JScrollPane(jta);
jta.setBorder(new LineBorder(Color.BLUE));
        JPanel pangui = new JPanel();
        pangui.add(scroll);
        setSize(500, 500);
        add(scroll);

        // jtf.setBounds(200, 200, 300, 500);
        // add(jtf);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    /*
    
    JOptionPane gets webpage address creates URL connection and appends to JtextArea
    
    
    System out is used for debugging in the future or when i add more of a GUI and need to track my work for bugs
     */
    public void getData() throws MalformedURLException, IOException, BadLocationException {

        String mssg = "Enter web page address ie. google.com";
        web_page = "";
        getThis = JOptionPane.showInputDialog(null, web_page, mssg);
        // check if user box is empty or not
        if (getThis.isEmpty()) {
            System.out.println("You Have Not Entered A  String For Us To Read..");
        } else {
            //debug
            System.out.println(getThis);

            // for getting phrase
            System.out.println("Enter Your String You Want To Search For");
            scan = new Scanner(System.in);

            // for getting webpage
            System.out.println("OK Searching on" + getThis + "searching for phrase:");
            url = new URL("http://" + getThis);

            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            while ((line = in.readLine()) != null) {
                jta.append(line);
                String finished = line.toString();
                find_chars();
            }
        }

        in.close();

    }

    /*
    
    This is the method that finds the selected strings in the actual JTextArea itself
    and highlights the desired strings/int etc.
    
    
    
     */
    public void find_chars() throws BadLocationException {

        JOptionPane.showMessageDialog(null, "Enter Phrase in System/terminal");

        hilit = new DefaultHighlighter();
        painter = new DefaultHighlighter.DefaultHighlightPainter(HILIT_COLOR);
        hilit = jta.getHighlighter();
        hilit.removeAllHighlights();

        Scanner scan = new Scanner(System.in);
        String userD = scan.nextLine();
        System.out.println("-------------" + userD);
        String find_me = jta.getText();

        // my jtextarea
        System.out.println("im looking for your string" + find_me);
        hilit = jta.getHighlighter();
        hilit.removeAllHighlights();

        int index = find_me.indexOf(userD);

        while (index >= 0) {

            System.out.println("im searching for" + index);
            int len = userD.length();
            hilit.addHighlight(index, index + len, painter);
            index = find_me.indexOf(userD, index + len);

        }

       
    }

    public static void main(String[] args) throws IOException, MalformedURLException, BadLocationException {

  
        
        
        
        Gerardo g = new Gerardo();
        g.getData();

    }

}
