import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Image;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;


public class vNes extends MIDlet implements CommandListener {
	private static vNesCanvas canvasUI;
	
	protected void startApp() throws MIDletStateChangeException {
		canvasUI = new vNesCanvas(this);
		// TODO Auto-generated method stub
		
	}
	public void commandAction(Command arg0, Displayable arg1) {
	// TODO Auto-generated method stub
	

	}

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
	// TODO Auto-generated method stub

	}


	protected void pauseApp() {
	// TODO Auto-generated method stub

	}
	
	public static void showErrorMsg(String errorMsg){
		
	}
	
	public static void printMsg(String msg){
		
	}
	public void fileSelectorExit() {
		// TODO Auto-generated method stub
		
	}
	public void loadSelectedRom(String url) {
		// TODO Auto-generated method stub
		
	}
	
    
	public static InputHandler getJoy1() {
		// TODO Auto-generated method stub
		return null;
	}
	
	static Image makeRotatedImage(String filename){
		Image img = makeImage(filename);
		
		//simple rotation from forum.nokia 
		//http://developer.nokia.com/Community/Wiki/Rotate_an_image_in_Java_ME
		int width = img.getWidth();
		int height = img.getHeight();
	 
		int angle = 90;
		int[] rowData = new int[width];
		int[] rotatedData = new int[width * height];
		
		int rotatedIndex = 0;
		 
		for(int i = 0; i < height; i++)
		{
			img.getRGB(rowData, 0, width, 0, i, width, 1);
	 
			for(int j = 0; j < width; j++)
			{
				rotatedIndex = 
					angle == 90 ? (height - i - 1) + j * height : 
					(angle == 270 ? i + height * (width - j - 1) : 
						width * height - (i * width + j) - 1
					);
	 
				rotatedData[rotatedIndex] = rowData[j];
			}
		}
		return Image.createRGBImage(rotatedData, height, width, true);
		
	}
    // loads a given image by name
    static Image makeImage(String filename) {
        Image image = null;

        try {
            image = Image.createImage(filename);
        } catch (Exception e) {
            // use a null image instead
        }

        return image;
    }
    
    public static vNesCanvas getCanvasUI(){
    	return canvasUI;
    }
}
