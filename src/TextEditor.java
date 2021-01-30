import java.awt.event.*;
import java.io.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;


public class TextEditor implements ActionListener {

	JTextArea textArea;
	JFrame window;
	JMenuBar menuBar;
	JScrollPane scrollPane;
	JMenu file; //File
	JMenu theme;// Theme
	JMenu edit; // Edit
	JMenuItem light;
	JMenuItem dark;
	JMenuItem classic;
	JMenuItem def;
	JMenuItem open;
	JMenuItem saveAs;
	JMenuItem exit;
	JMenuItem bold;
	JMenuItem italic;
	JLabel fontSelector;
	JComboBox<String> fontBox;
	JButton color;
	
	public TextEditor() {
		// creating the window of the text editor
		
		this.window = new JFrame();
		this.window.setLayout(new FlowLayout());
		this.window.setTitle("Text Editor");
		this.window.setSize(600,650);
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  
		
		//Menu - File, Theme, Color, Font
		this.menuBar = new JMenuBar();
		this.file = new JMenu("File");  //File
		this.theme = new JMenu("Theme");  // Theme
		this.edit = new JMenu("Edit");
		this.light = new JMenuItem("Light");
		this.dark = new JMenuItem("Dark");
		this.classic = new JMenuItem("Classic");
		this.def = new JMenuItem("Default");
		this.open = new JMenuItem("Open");
		this.saveAs = new JMenuItem("Save As");
		this.exit = new JMenuItem("Exit");
		this.bold = new JMenuItem("Bold");
		this.italic  = new JMenuItem("Italic");
		this.file.add(this.open);
		this.file.add(this.saveAs);
		this.file.add(this.exit);
		this.theme.add(this.light);
		this.theme.add(this.dark);
		this.theme.add(this.classic);
		this.theme.add(this.def);
		this.edit.add(this.bold);
		this.edit.add(this.italic);
		this.menuBar.add(file);	
		this.menuBar.add(theme);
		this.menuBar.add(edit);
		this.menuBar.setVisible(true);
		
		this.exit.addActionListener(this);
		this.open.addActionListener(this);
		this.saveAs.addActionListener(this);
		this.dark.addActionListener(this);
		this.light.addActionListener(this);
		this.classic.addActionListener(this);
		this.def.addActionListener(this);
		this.bold.addActionListener(this);
		this.italic.addActionListener(this);
		//end of menu bar
		
		//textArea
		
		this.textArea = new JTextArea();
		this.textArea.setPreferredSize(new Dimension(570,530));
		this.textArea.setLineWrap(true);
		this.textArea.setWrapStyleWord(true);
		this.textArea.setVisible(true); 
		//end of textArea
		
		//setting up default font and size
		this.textArea.setFont(new Font("Times New Roman",Font.PLAIN,25));
		
		//scrollPane
		this.scrollPane = new JScrollPane(this.textArea);
		this.scrollPane.setPreferredSize(new Dimension(570,530));
		this.scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		this.scrollPane.setVisible(true);
		//end of scrollPane
		
		
		//font selector
		this.fontSelector = new JLabel("Font :",JLabel.LEFT);
		this.fontSelector.setPreferredSize(new Dimension(35,20));
		String allFonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		this.fontBox = new JComboBox<String>(allFonts);
		this.fontBox.addActionListener(this);
		this.fontBox.setSelectedItem("Times New Roman");
		this.fontBox.setPreferredSize(new Dimension(120,25));
		this.fontSelector.setVisible(true);
		this.fontBox.setVisible(true);
		
		// color selector
		this.color =  new JButton("Font Color");
		this.color.addActionListener(this);
		
		

		
		//window (frame)
		window.add(this.fontSelector);
		window.add(this.fontBox);
		window.add(this.color);
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
			FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("Text Files", "txt");
			fChooser.setFileFilter(extensionFilter);
			
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
		
		
		//save as
		if(e.getSource() == this.saveAs) {
			JFileChooser fChooser = new JFileChooser();
			fChooser.setCurrentDirectory(new File("."));
			FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("Text Files", "txt");
			fChooser.setFileFilter(extensionFilter);
			int response = fChooser.showSaveDialog(null);
			if(response == JFileChooser.APPROVE_OPTION) {
				File file = new File(fChooser.getSelectedFile().getAbsolutePath());
				try {
					FileOutputStream fo = new FileOutputStream(file);
					byte b[] = (this.textArea.getText().getBytes());
					fo.write(b);
					fo.close();
				}
				catch(IOException exception) {
					JOptionPane.showMessageDialog(null,"File can't be saved");
				}
			}
			
			
		}
		
		
		//fontBox 
		if(e.getSource() == this.fontBox) {
			textArea.setFont(new Font((String)fontBox.getSelectedItem(),Font.PLAIN,25));
		}
		
		
		//font color
		if(e.getSource() == this.color) {
			Color c = JColorChooser.showDialog(null,"Choose a color",Color.black);
			textArea.setForeground(c);
		}
		//bold
		if(e.getSource() == this.bold) {
			textArea.setFont(new Font(textArea.getFont().getFamily(),Font.BOLD,textArea.getFont().getSize()));
			
		}
		
		
		//italic
		if(e.getSource() == this.italic) {
			textArea.setFont(new Font(textArea.getFont().getFamily(),Font.ITALIC,textArea.getFont().getSize()));
		}
		
		
		//themes
		if(e.getSource() == this.dark) {
			try {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
				SwingUtilities.updateComponentTreeUI(window);
			}
			catch(Exception exception) {
				JOptionPane.showMessageDialog(null,"Theme can't be changed");
			}
		}
		
		if(e.getSource() == this.light) {
			try {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
				SwingUtilities.updateComponentTreeUI(window);
			}
			catch(Exception exception) {
				JOptionPane.showMessageDialog(null,"Theme can't be changed");
			}
		}
		
		if(e.getSource() == this.classic) {
			try {
				UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
				SwingUtilities.updateComponentTreeUI(window);
			}
			catch(Exception exception) {
				JOptionPane.showMessageDialog(null,"Theme can't be changed");
			}
		}
		
		if(e.getSource() == this.def) {
			try {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
				SwingUtilities.updateComponentTreeUI(window);
			}
			catch(Exception exception) {
				JOptionPane.showMessageDialog(null,"Theme can't be changed");
			}
		}
		
		
	}
}
