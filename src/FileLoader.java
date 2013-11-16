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

import java.io.*;

import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;

public class FileLoader {

    // Load a file.
    public short[] loadFile(String fileName, vNesCanvas ui) {

        int flen;
        byte[] tmp = new byte[2048];

        FileConnection fileConn = null;
        // Read file:
        try {

        	fileConn =
                    (FileConnection) Connector.open(fileName, Connector.READ);


             
            InputStream in;
            in =  fileConn.openInputStream();
            //in = getClass().getResourceAsStream(fileName);

            if (in == null) {

                // Try another approach.
                if (in == null) {
                    throw new IOException("Unable to load " + fileName);
                }

            }
            boolean zip = false;

            int pos = 0;
            int readbyte = 0;

            if (in != null) {


                long total = fileConn.fileSize();
                //just try to load it all in one loop
                tmp = new byte[tmp.length + 32768];
                
                while (readbyte != -1) {
                    readbyte =  in.read(tmp, pos, tmp.length - pos);
                    if (readbyte != -1) {
                        pos += readbyte;
                    }
		
                /*
                long progress = -1;
                while (readbyte != -1) {
                    readbyte =  in.read(tmp, pos, tmp.length - pos);
                    if (readbyte != -1) {
                        if (pos >= tmp.length) {
                            byte[] newtmp = new byte[tmp.length + 32768];
                            for (int i = 0; i < tmp.length; i++) {
                                newtmp[i] = tmp[i];
                            }
                            tmp = newtmp;
                        }
                        pos += readbyte;
                    }

                    if (total > 0 && ((pos * 100) / total) > progress) {
                        progress = (pos * 100) / total;
                        if (ui != null) {
                            ui.showLoadProgress((int) progress);
                        }
                    }*/

                }

            } else {
            	//fail
            	 throw new IOException("Unable to load " + fileName);
            }

            // Put into array without any padding:
            byte[] newtmp = new byte[pos];
            for (int i = 0; i < pos; i++) {
                newtmp[i] = tmp[i];
            }
            tmp = newtmp;

            // File size:
            flen = tmp.length;

        } catch (IOException ioe) {

            // Something went wrong.
            ioe.printStackTrace();
            return null;

        }

        short[] ret = new short[flen];
        for (int i = 0; i < flen; i++) {
            ret[i] = (short) (tmp[i] & 255);
        }
        return ret;

    }
}
