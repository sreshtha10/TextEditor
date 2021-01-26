import javax.swing.UIManager;

public class Main{
	public static void main(String args[]) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
			new TextEditor();
		}
		catch(Exception e) {
			System.out.println(e);
		}

	}
}
