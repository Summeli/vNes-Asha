/*
vNES
Copyright © 2006-2013 Open Emulation Project

This program is free software: you can redistribute it and/or modify it under
the terms of the GNU General Public License as published by the Free Software
Foundation, either version 3 of the License, or (at your option) any later
version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY
WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with
this program.  If not, see <http://www.gnu.org/licenses/>.
 */


public class ScreenView extends BufferView {

    private boolean notifyImageReady;
    private vNesCanvas canvas;
    public ScreenView(NES nes, vNesCanvas canvas, int width, int height) {
    	super(nes,width,height);
    	this.canvas = canvas;
    }

    public void init() {

    }

    public void setNotifyImageReady(boolean value) {
        this.notifyImageReady = value;
    }

    public void imageReady(boolean skipFrame) {

        // Draw image first:
       // super.imageReady(skipFrame);

        // Notify GUI, so it can write the sound buffer:
        
        //vNesCanvas should know
        /*
        if (notifyImageReady) {
           nes.getGui().imageReady(skipFrame);
        }*/
    	canvas.imageReady(skipFrame);
    }
}