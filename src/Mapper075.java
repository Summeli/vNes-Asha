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

public class Mapper075 extends MapperDefault {

    int regs[] = new int[2];

    public void init(NES nes) {
        super.init(nes);
    }

    public void write(int address, short value) {

        if (address < 0x8000) {
            super.write(address, value);
        } else {
            switch (address & 0xF000) {
                case 0x8000:
                     {
                        load8kRomBank(value, 0x8000);
                    }
                    break;

                case 0x9000:
                     {
                        if ((value & 0x01) != 0) {
                            nes.getPpu().setMirroring(ROM.HORIZONTAL_MIRRORING);
                        } else {
                            nes.getPpu().setMirroring(ROM.VERTICAL_MIRRORING);
                        }

                        regs[0] = (regs[0] & 0x0F) | ((value & 0x02) << 3);
                        loadVromBank(regs[0], 0x0000);

                        regs[1] = (regs[1] & 0x0F) | ((value & 0x04) << 2);
                        loadVromBank(regs[1], 0x1000);
                    }
                    break;

                case 0xA000:
                     {
                        load8kRomBank(value, 0xA000);
                    }
                    break;

                case 0xC000:
                     {
                        load8kRomBank(value, 0xC000);
                    }
                    break;

                case 0xE000:
                     {
                        regs[0] = (regs[0] & 0x10) | (value & 0x0F);
                        loadVromBank(regs[0], 0x0000);
                    }
                    break;

                case 0xF000:
                     {
                        regs[1] = (regs[1] & 0x10) | (value & 0x0F);
                        loadVromBank(regs[1], 0x1000);
                    }
                    break;
            }

        }
    }

    public void loadROM(ROM rom) {

        int num_8k_banks = rom.getRomBankCount() * 2;

        // Load PRG-ROM:
        load8kRomBank(0, 0x8000);
        load8kRomBank(1, 0xA000);
        load8kRomBank(num_8k_banks - 2, 0xC000);
        load8kRomBank(num_8k_banks - 1, 0xE000);

        // Load CHR-ROM:
        loadCHRROM();

        // Do Reset-Interrupt:
        nes.getCpu().requestIrq(CPU.IRQ_RESET);

    }

    public void reset() {

        regs[0] = 0;
        regs[1] = 1;
    }
}
