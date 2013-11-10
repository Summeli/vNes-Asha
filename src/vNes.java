import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.List;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;


public class vNes extends MIDlet implements CommandListener {
	private static vNesCanvas canvasUI;
	
	private List mainMenu;
	private Form messageForm;
	private FileSelector fileSelector;
	
	// UI components
	public static Display display;
	
	protected void startApp() throws MIDletStateChangeException {
		canvasUI = new vNesCanvas(this);
		display = Display.getDisplay(this);
		fileSelector = new FileSelector(this);
		showMainMenu();
		
	}
	public void commandAction(Command arg0, Displayable arg1) {
		String item = mainMenu.getString(mainMenu.getSelectedIndex());
		if (item == "Load ROM") {
			if(vNesSettings.isAsha == true){
				fileSelector.showAshaFileSelectionDialog();
			}else{
				fileSelector.initialize();
				display.setCurrent(fileSelector);
			}
		}else if (item == "About") {
			showMessage(vNesSettings.getVersionString(), vNesSettings.getVersionString() + " for S40 and Nokia Asha \n" +
					                 "by: Antti Pohjola, summeli@summeli.fi \nhttp://www.summeli.fi\n"+
					                 "vNes is licenced under GPLv2 licence \n" +
					                 "You can get the source code from: http://github.com/Summeli/Meboy-Asha \n\n"+
					                 "Meboy was originally developed for j2ME by: Björn Carlin, 2005-2009.\nhttp://arktos.se/meboy/ \n\n"+
					                 "LEGAL: This product is not affiliated with, not authorized, endorsed or licensed in any way by Nintendo Corporation, its affiliates or subsidiaries.");
		}
	}

	public void showMainMenu(){
		mainMenu = new List(vNesSettings.getVersionString(), List.IMPLICIT);
		
		//set all the commands
		mainMenu.append("Load ROM", null);
		mainMenu.append("About",null);
		mainMenu.setCommandListener(this);
		display.setCurrent(mainMenu);
	}
	
	public void showMessage(String title, String message) {
		messageForm = new Form(title);
		messageForm.append(message);
		messageForm.setCommandListener(this);
		messageForm.addCommand(new Command("Back", Command.BACK, 0));
		display.setCurrent(messageForm);
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
		canvasUI.loadROM(url);
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
