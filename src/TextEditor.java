import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    //Declaring properties of texteditor
    JFrame frame;

    JMenuBar menuBar;

    JMenu file,edit;
    JMenuItem newFile,openFile,saveFile;
    JMenuItem cut,copy,paste,selectAll,close;
    JTextArea textArea;
    TextEditor(){
        //initialize a frame
        frame=new JFrame();

        //Initialize a menubar
        menuBar=new JMenuBar();
        //Initialize textArea
        textArea=new JTextArea();

        //Initialize menu
        file=new JMenu("File");
        edit=new JMenu("Edit");

        //Initialize file menu items
        newFile=new JMenuItem("New Window");
        openFile=new JMenuItem("Open");
        saveFile=new JMenuItem("Save");
        //add action listener to file menu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        //add menu items to file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);
        //Initialize file menu items
        cut=new JMenuItem("Cut");
        copy=new JMenuItem("Copy");
        paste=new JMenuItem("Paste");
        selectAll=new JMenuItem("Select All");
        close=new JMenuItem("Close");
        //add action Listener to edit menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        //add menu items to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);


        //add menu to menuBar
        menuBar.add(file);
        menuBar.add(edit);

        //set menuBar to frame
        frame.setJMenuBar(menuBar);
        //create content pane
        JPanel panel=new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        //add text area to panel
        panel.add(textArea,BorderLayout.CENTER);
        //create scroll pane
        JScrollPane scrollPane=new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //add scroll pane to panel
        panel.add(scrollPane);
        //add panel to frame
        frame.add(panel);
        //set dimensions of frame
        frame.setBounds(100,100,400,400);
        frame.setTitle("Text Editor");
        frame.setVisible(true);
        frame.setLayout(null);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource()==cut){
            //perform cut operation
            textArea.cut();
        }
        if(actionEvent.getSource()==copy){
            //perform copy operation
            textArea.copy();
        }
        if(actionEvent.getSource()==paste){
            //perform paste operation
            textArea.paste();
        }
        if(actionEvent.getSource()==selectAll){
            //perform selectAll operation
            textArea.selectAll();
        }
        if(actionEvent.getSource()==close){
            //perform exit/close operation
            System.exit(0);
        }
        if(actionEvent.getSource()==openFile){
            //open a file chooser
            JFileChooser fileChooser=new JFileChooser("C:");
            int chooseOption=fileChooser.showOpenDialog(null);
            //if we have clicked on open buttton
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                //getting selected file
                File file=fileChooser.getSelectedFile();
                //Get the path of selected file
                String filePath=file.getPath();
                try{
                  //initialize file reader
                    FileReader fileReader=new FileReader(filePath);
                    //Initialized bufferd reader
                    BufferedReader bufferedReader=new BufferedReader(fileReader);
                    String intermediate="",output="";
                    //read contents of file line by line
                    while((intermediate=bufferedReader.readLine())!=null){
                        output+=intermediate+"\n";
                    }
                    //set the output string to text area
                    textArea.setText(output);
                }
                catch(FileNotFoundException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }
                catch(IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource()==saveFile){
            JFileChooser fileChooser=new JFileChooser("C:");
            //Get choose option from file chooser
            int chooseOption=fileChooser.showSaveDialog(null);
            //check if we clicked on saved button
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                //create a new file with choosen directory path and file name
                File file=new File(fileChooser.getSelectedFile().getAbsoluteFile()+".txt");
                try{
                    //Initialize file writer
                    FileWriter fileWriter=new FileWriter(file);
                    //Initialize Buffered writer
                    BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
                    //write content of text area to file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch(IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource()==newFile){
            TextEditor newTextEditor=new TextEditor();
        }
    }
    public static void main(String[] args) {
        TextEditor textEditor=new TextEditor();
    }
}