package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DigitalChannel;

public class PuyallupLEDController extends SubsystemBase {
    //We are using TPICB595 shift register to control the LEDs
    //Written with the help of my good friend Copilot
    DigitalChannel storage, shift, data;
    byte currentByte = 0;
    public PuyallupLEDController(DigitalChannel storage, DigitalChannel shift, DigitalChannel data) {
        this.storage = storage;
        this.shift = shift;
        this.data = data;

        storage.setMode(DigitalChannel.Mode.OUTPUT);
        shift.setMode(DigitalChannel.Mode.OUTPUT);
        data.setMode(DigitalChannel.Mode.OUTPUT);
    }
    /** Sets the byte to be sent to the shift register
     * @param b The byte to be sent to the shift register.<br>
     *          LED CONTROL SCHEME: <br>
     *          Bit 0: GREEN 0 <br>
     *          Bit 1: PURPLE 0 <br>
     *          Bit 2: GREEN 1  <br>
     *          Bit 3: PURPLE 1 <br>
     *          Bit 4: GREEN 2  <br>
     *          Bit 5: PURPLE 2 <br>
     *          Bit 6: WHEEL RED <br>
     *          Bit 7: WHEEL GREEN  <br>
     *
     */
    public void setByte(byte b) {
        storage.setState(false);
        for (int i = 0; i < 8; i++) {
            shift.setState(false);
            data.setState((b & (1 << (7 - i))) != 0);
            shift.setState(true);
            currentByte = (byte) ((currentByte << 1) | ((b >> (7 - i)) & 1));
            if (currentByte == b) {
                // break early if we've already set the correct byte
                break;
            }
        }
        storage.setState(true);
    }

    public byte getCurrentByte() {
        return currentByte;
    }

}
