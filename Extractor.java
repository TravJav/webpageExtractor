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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.text.BadLocationException;

public class Extractor extends JFrame {
    final static Color HILIT_COLOR = Color.RED;

    public Extractor() {
        super("search String");
        JTextArea jta = new JTextArea(5, 20);
        jta.setLineWrap(true);
        jta.setEditable(true);
        JScrollPane scroll = new JScrollPane(jta);
        jta.setBorder(new LineBorder(Color.BLUE));
        JPanel pangui = new JPanel();
        pangui.add(scroll);
        setSize(500, 500);
        add(scroll);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void getData() throws MalformedURLException, IOException, BadLocationException {
        String line;
        String mssg = "Enter web page address ie. google.com";
        String web_page = "";
        String getThis = JOptionPane.showInputDialog(null, web_page, mssg);
        if (getThis.isEmpty()) {
            System.out.println("You Have Not Entered A  String For Us To Read..");
        } else {
            System.out.println("Enter Your String You Want To Search For");
            Scanner scan = new Scanner(System.in);
            System.out.println("OK Searching on" + getThis + "searching for phrase:");
            URL url = new URL("http://" + getThis);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            while ((line = in.readLine()) != null) {
                jta.append(line);
                String finished = line.toString();
                find_chars();
            }
        }
        in.close();
    }

    public void find_chars() throws BadLocationException {
        JOptionPane.showMessageDialog(null, "Enter Phrase in System/terminal");
        Highlighter hilit = new DefaultHighlighter();
        Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(HILIT_COLOR);
        hilit = jta.getHighlighter();
        hilit.removeAllHighlights();
        Scanner scan = new Scanner(System.in);
        String userD = scan.nextLine();
        String find_me = jta.getText();
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
        Extractor exe = new Extractor();
        exe.getData();
    }
}
