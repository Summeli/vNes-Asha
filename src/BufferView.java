import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

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


public class BufferView {

    // Scale modes:
    public static final int SCALE_NONE = 0;
    public static final int SCALE_HW2X = 1;
    public static final int SCALE_HW3X = 2;
    public static final int SCALE_NORMAL = 3;
    public static final int SCALE_SCANLINE = 4;
    public static final int SCALE_RASTER = 5;
    protected NES nes;
    private Image img;
    private int width;
    private int height;
    private int[] pix;
    private int[] pix_scaled;
    private int scaleMode;
    // FPS counter variables:
    private boolean showFPS = false;
    private long prevFrameTime;
    private String fps;
    private int fpsCounter;
    private int bgColor = 000;
    private vNesCanvas canvas;
    // Constructor
    public BufferView(NES nes, int width, int height) {
        this.nes = nes;
        this.width = width;
        this.height = height;
        //No scaling
        this.scaleMode = SCALE_NONE;
        this.canvas = nes.getGui();
        pix = new int[width * height];
    }

    public void setBgColor(int color) {
        bgColor = color;
    }


    public void init() {
    	

    }


    public void imageReady(boolean skipFrame) {

        // Skip image drawing if minimized or frameskipping:
        if (!skipFrame) {

            nes.ppu.requestRenderAll = false;
            
            //TODO: paint
            //paint(getGraphics());
            canvas.imageReady(skipFrame);
        }

    }

    public Image getImage() {
        return img;
    }

    public int[] getBuffer() {
        return pix;
    }

    public void update(Graphics g) {
    }

    public boolean scalingEnabled() {
        return scaleMode != SCALE_NONE;
    }

    public int getScaleMode() {
        return scaleMode;
    }

    public boolean useNormalScaling() {
        return (scaleMode == SCALE_NORMAL);
    }

    public void setFPSEnabled(boolean val) {

        // Whether to show FPS count.
        showFPS = val;

    }

    public void paintFPS(int x, int y, Graphics g) {

        if (showFPS) {

            // Update FPS count:
            if (--fpsCounter <= 0) {

                long ct = nes.getGui().getTimer().currentMicros();
                long frameT = (ct - prevFrameTime) / 45;
                if (frameT == 0) {
                    fps = "FPS: -";
                } else {
                    fps = "FPS: " + (1000000 / frameT);
                }
                fpsCounter = 45;
                prevFrameTime = ct;

            }

            // Draw FPS.
            /*
            gfx.setColor(Color.black);
            gfx.fillRect(x, y - gfx.getFontMetrics().getAscent(), gfx.getFontMetrics().stringWidth(fps) + 3, gfx.getFontMetrics().getHeight());
            gfx.setColor(Color.cyan);
            gfx.drawString(fps, x, y);
             	*/
        }

    }

    public int getBufferWidth() {
        return width;
    }

    public int getBufferHeight() {
        return height;
    }

    public boolean useHWScaling() {
        return useHWScaling(scaleMode);
    }

    public boolean useHWScaling(int mode) {
        return mode == SCALE_HW2X || mode == SCALE_HW3X;
    }

    public int getScaleModeScale(int mode) {
        if (mode == -1) {
            return -1;
        } else if (mode == SCALE_NONE) {
            return 1;
        } else if (mode == SCALE_HW3X) {
            return 3;
        } else {
            return 2;
        }
    }

    public void destroy() {

        nes = null;
        img = null;

    }
}