/*Concepts implemented:
 * 1. swing and awt for GUI.
 * 2. Classes and objects 
 * 3. Constructor, Anonymous class
 * 4. static, final and access modifiers. 
 * 5. File Handling 
 * 6. Exception Handling
 * 7. Interfaces, method overriding (Runtime Polymorphism).
 * 8. Encapsulation,Multithreading (basic)
 * 9. Important keywords like this,final etc.
 * 10.Garbage collection and finalize.
 */



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
