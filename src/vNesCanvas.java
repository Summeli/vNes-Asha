import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;

import com.nokia.mid.ui.multipointtouch.MultipointTouchListener;


public class vNesCanvas extends Canvas implements CommandListener, MultipointTouchListener {
	public vNes parent;

	vNesCanvas(vNes p) {
		parent = p;
		
	}
	public void pointersChanged(int[] pointerIds) {
		// TODO Auto-generated method stub
		
	}

	public void commandAction(Command arg0, Displayable arg1) {
		// TODO Auto-generated method stub
		
	}

	protected void paint(Graphics arg0) {
		// TODO Auto-generated method stub
		
	}

	public static InputHandler getJoy1() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void showLoadProgress(int progress) {
		// TODO Auto-generated method stub
		
	}
}
