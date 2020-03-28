package com.valcano.vjdol.compilerUtil;//package com.valcano.vjdol.compilerUtil;
////imports
//import javax.swing.JFrame;
//import javax.swing.JTextArea;
//import java.io.PrintStream;
//class StreamToTextArea extends JFrame {
//    //declare PrintStream and JTextArea
//    private static PrintStream ps = null;
//    private JTextArea textPane = new JTextArea();  //constructor
//    public StreamToTextArea() {
//
//        setSize( 310, 180 );
//
//        getContentPane().add(textPane);
//
//        //this is the trick: overload the println(String)
//        //method of the PrintStream
//        //and redirect anything sent to this to the text box
//        ps = new PrintStream(System.out) {
//            public void println(String x) {
//                textPane.append(x + "\n");
//            }
//        };
//    }
//
//    public PrintStream getPs() {
//        return ps;
//    }
//
//    public static void main(String args[]) {
//        //create object
//        StreamToTextArea blah = new StreamToTextArea();
//        //show it
//        blah.show();
//        //redirect the output stream
//        System.setOut(blah.getPs());
//        //print to the text box
//        System.out.println("IT'S ALIVE!!");
//        //print to the terminal (not a string)
//        System.out.println(1);
//        //print the same thing to the text box (now a string)
//        System.out.println("" + 1);
//    }
//}


import java.io.*;

public class StreamToTextArea {
    private static PrintStream ps;// 声明打印流对象
    public static void main(String arg[]) throws Exception {

        // 如果现在是使用FileOuputStream实例化，意味着所有的输出是向文件之中
        ps = new PrintStream(new FileOutputStream(new File("/Users/huoshan/IdeaProjects/vjdol/src/main/resources/testPointData" + File.separator + "2.txt")));
        System.setOut(ps);
        System.out.println("dsadadsadas");

    }

}