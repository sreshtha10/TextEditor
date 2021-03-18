import java.awt.event.*;
import java.io.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.TextAction;


//inheritance (interface)
public class TextEditor implements ActionListener,Runnable {

	//class variables
	JTextArea textArea;
	private final JFrame window; 
	JMenuBar menuBar;
	JScrollPane scrollPane;
	JMenu file; //File
	JMenu theme;// Theme
	JMenu edit; // Edit
	JButton about; // about
	JMenuItem light;
	JMenuItem dark;
	JMenuItem classic;
	JMenuItem def;
	JMenuItem open;
	JMenuItem saveAs;
	JMenuItem exit;
	JMenuItem bold;
	JMenuItem italic;
	JMenuItem currThread;
	JLabel fontSelector;
	JComboBox<String> fontBox;
	JButton color;
	JLabel fontSize;
	JSpinner fontSpinner;
	
	
	//constructor
	public TextEditor() {
		// creating the window of the text editor
	
		this.window = new JFrame();
		ImageIcon icon = new ImageIcon("C:\\Users\\sresh\\Downloads\\icon.png");
		this.window.setIconImage(icon.getImage());
		this.window.setResizable(false);  // fixed size of the frame (window)
		FlowLayout layout  = new FlowLayout();
		layout.setHgap(15);
		this.window.setLayout(layout);
		this.window.setTitle("Text Editor");
		this.window.setSize(600,650);
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  
		
		//Menu - File, Edit, Theme, About
		this.menuBar = new JMenuBar();
		this.file = new JMenu("File");  //File
		this.theme = new JMenu("Theme");  // Theme
		this.edit = new JMenu("Edit"); // Edit
		this.about = new JButton("About"); // About
		this.about.setBorderPainted(false);
		this.about.setContentAreaFilled(false);;
		//Menu Items
		this.light = new JMenuItem("Light");
		this.dark = new JMenuItem("Dark");
		this.classic = new JMenuItem("Classic");
		this.def = new JMenuItem("Default");
		this.open = new JMenuItem("Open");
		this.saveAs = new JMenuItem("Save As");
		this.exit = new JMenuItem("Exit");
		this.bold = new JMenuItem("Bold");
		this.italic  = new JMenuItem("Italic");
		this.currThread = new JMenuItem("Thread info");
		
		//adding all the menuItems to menu
		this.file.add(this.open);
		this.file.add(this.saveAs);
		this.file.add(this.exit);
		this.file.add(currThread);
		this.theme.add(this.light);
		this.theme.add(this.dark);
		this.theme.add(this.classic);
		this.theme.add(this.def);
		this.edit.add(this.bold);
		this.edit.add(this.italic);
		
		//adding all menus to menuBar
		this.menuBar.add(file);	
		this.menuBar.add(theme);
		this.menuBar.add(edit);
		this.menuBar.add(about);
		this.menuBar.setVisible(true);
		
		
		this.about.addActionListener(this);
		this.exit.addActionListener(this);
		this.open.addActionListener(this);
		this.saveAs.addActionListener(this);
		this.dark.addActionListener(this);
		this.light.addActionListener(this);
		this.classic.addActionListener(this);
		this.def.addActionListener(this);
		this.bold.addActionListener(this);
		this.italic.addActionListener(this);
		this.currThread.addActionListener(this);
		//end of menu bar
		
		//textArea
		
		this.textArea = new JTextArea();
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
		
		// font size spinner
		this.fontSize =  new JLabel("Font Size:");
		this.fontSpinner = new JSpinner();
		this.fontSpinner.setPreferredSize(new Dimension(50,25));
		this.fontSpinner.setValue(25);  //default size will be 25
		this.fontSpinner.addChangeListener(new ChangeListener() {  // Anonymous class
			
			//changing the font size in the textArea whenever size is change in the fontSpinner
			@Override
			public void stateChanged(ChangeEvent e) {
				textArea.setFont(new Font(textArea.getFont().getFamily(),Font.PLAIN,(int)fontSpinner.getValue()));
				
			}
		});
		
		//Adding shortcuts
		
		//ctrl+w for exit
		KeyStroke exitStroke = KeyStroke.getKeyStroke(KeyEvent.VK_W,KeyEvent.CTRL_DOWN_MASK);
		this.exit.setAccelerator(exitStroke);
		
		//ctrl +o for open
		KeyStroke openStroke = KeyStroke.getKeyStroke(KeyEvent.VK_O,KeyEvent.CTRL_DOWN_MASK);
		this.open.setAccelerator(openStroke);
		
		
		//ctrl+s for save
		KeyStroke saveStroke = KeyStroke.getKeyStroke(KeyEvent.VK_S,KeyEvent.CTRL_DOWN_MASK);
		this.saveAs.setAccelerator(saveStroke);
		
		
		//cut copy paste
		addAction(new DefaultEditorKit.CutAction(), KeyEvent.VK_X, "Cut" );
	    addAction(new DefaultEditorKit.CopyAction(), KeyEvent.VK_C, "Copy" );
	    addAction(new DefaultEditorKit.PasteAction(), KeyEvent.VK_V, "Paste" );
		
		
		//window (frame)
		window.add(this.fontSelector);
		window.add(this.fontBox);
		window.add(this.color);
		window.add(this.fontSize);
		window.add(this.fontSpinner);
		this.window.setJMenuBar(this.menuBar);
		this.window.add(this.scrollPane);
		this.window.validate();  // validating the sub components of the container (window)
		this.window.setVisible(true);
		this.window.setLocationRelativeTo(null);  // centering the window.
		
		
		
		
		
	}
	
	
	//adding functionality to the text editor 
  
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Opening a file (File Handling)
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
			System.gc();
			System.exit(0);
		}
		
		
		//save as  (File handling)
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
		
		
		//About 
		if(e.getSource() == this.about) {
			JOptionPane.showMessageDialog(this.window,"Created by Sreshtha Mehrotra \nGithub link: www.github.com/sreshtha10 ");
			
		}
		
		
		if(e.getSource() == this.currThread) {
			JOptionPane.showMessageDialog(this.window,"Current thread"+Thread.currentThread().getName()+"\nCurrent Thread Priority :"+Thread.currentThread().getPriority());
		}
	}
	
	
	// Method to add cut copy paste feature. 
	private void addAction(TextAction action, int key, String text) {
	      action.putValue(AbstractAction.ACCELERATOR_KEY,KeyStroke.getKeyStroke(key, InputEvent.CTRL_DOWN_MASK));
	      action.putValue(AbstractAction.NAME, text);
	      this.edit.add(new JMenuItem(action));
	  }	
	
	@Override
	protected void finalize() throws Throwable {
		try {
			System.out.println("Finalizing");
		}
		catch(Throwable e) {
			System.out.println("Error occured !");
		}
		
	}


	@Override
	public void run() {
		// for later.
	}

	
	
}
