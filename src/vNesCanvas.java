import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;

import com.nokia.mid.ui.multipointtouch.MultipointTouchListener;


public class vNesCanvas extends Canvas implements CommandListener, MultipointTouchListener {
	public vNes parent;
	private HiResTimer timer;
	private ScreenView screenView;
	private NES nes= null;
	
	vNesCanvas(vNes p) {
		parent = p;
		timer = new HiResTimer();
		nes = new NES(this);
		screenView = new ScreenView(nes,256,256);
		
	}
	
	public void loadROM(String uri){
		nes.loadRom(uri);
		nes.startEmulation();
	}
	public void pointersChanged(int[] pointerIds) {
		// TODO Auto-generated method stub
		
	}

	public void commandAction(Command arg0, Displayable arg1) {
		// TODO Auto-generated method stub
		
	}
	public void imageReady(boolean skipframe){
		boolean skipskip = skipframe;
		//TODO
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
	
	//stuff for the emulator engine
	public HiResTimer getTimer(){
		return timer;
	}
	
	public ScreenView getScreenView(){
		return this.screenView;
	}
	
	//dummy functions for returning shit we don't need
    public BufferView getImgPalView() {
        return null;
    }
    
    public BufferView getSprPalView() {
        return null;
    }

    public BufferView getNameTableView() {
        return null;
    }
    
    public BufferView getPatternView() {
        return null;
    }
}
