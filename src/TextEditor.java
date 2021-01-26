import java.awt.event.*;
import java.io.*;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;


public class TextEditor implements ActionListener {
	
	JTextArea textArea;
	JFrame window;
	JMenuBar menuBar;
	JScrollPane scrollPane;
	JMenu menu1 = new JMenu("File");  //File
	JMenu menu2 = new JMenu("Theme");// Theme
	JMenuItem light;
	JMenuItem dark;
	JMenuItem open;
	JMenuItem saveAs;
	JMenuItem exit;
	public TextEditor() {
		// creating the window of the text editor
		
		this.window = new JFrame();
		this.window.setLayout(new FlowLayout());
		this.window.setTitle("Text Editor");
		this.window.setSize(600,600);
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  
		
		//Menu - File, Theme, Color, Font
		this.menuBar = new JMenuBar();
		this.menu1 = new JMenu("File");  //File
		this.menu2 = new JMenu("Theme");  // Theme
		this.light = new JMenuItem("Light");
		this.dark = new JMenuItem("Dark");
		this.open = new JMenuItem("Open");
		this.saveAs = new JMenuItem("Save As");
		this.exit = new JMenuItem("Exit");
		this.menu1.add(this.open);
		this.menu1.add(this.saveAs);
		this.menu1.add(this.exit);
		this.menu2.add(this.light);
		this.menu2.add(this.dark);
		
		this.menuBar.add(menu1);	
		this.menuBar.add(menu2);
		this.menuBar.setVisible(true);
		
		this.exit.addActionListener(this);
		this.open.addActionListener(this);
		this.saveAs.addActionListener(this);
		this.dark.addActionListener(this);
		this.light.addActionListener(this);
		//end of menu bar
		
		//textArea
		this.textArea = new JTextArea();
		this.textArea.setPreferredSize(new Dimension(570,530));
		this.textArea.setLineWrap(true);
		this.textArea.setWrapStyleWord(true);
		this.textArea.setVisible(true); 
		//end of textArea
		
		
		//scrollPane
		this.scrollPane = new JScrollPane(this.textArea);
		this.scrollPane.setPreferredSize(new Dimension(570,530));
		this.scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		this.scrollPane.setVisible(true);
		//end of scrollPane

		//window (frame)
		this.window.add(this.scrollPane);
		this.window.setJMenuBar(this.menuBar);
		this.window.validate(); 
		this.window.setVisible(true);
		this.window.setLocationRelativeTo(null);  // centering the window.
		
		
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Opening a file
		if(e.getSource() == this.open) {
			JFileChooser fChooser = new JFileChooser();
			fChooser.setCurrentDirectory(new File("."));
			FileNameExtensionFilter extension = new FileNameExtensionFilter("Text Files", "txt");
			fChooser.setFileFilter(extension);
			
			int response  = fChooser.showOpenDialog(null);

			if(response == JFileChooser.APPROVE_OPTION) {
				File file = new File(fChooser.getSelectedFile().getAbsolutePath());
				try {
					FileInputStream fr = new FileInputStream(file);
					int i =0;
					while((i= fr.read()) != -1) {
						this.textArea.append(Character.toString((char)i));
						
					}
					fr.close();
				}
				catch(IOException exception) {
					JOptionPane.showMessageDialog(null,"File can't be opened !");
				}

			}
			
		}
		
		
		//exiting the program from menu bar.
		if(e.getSource() == this.exit) {
			System.exit(0);
		}
		if(e.getSource() == this.saveAs) {
			
		}
		
	}
}
