package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DigitalChannel;

public class PuyallupLEDController extends SubsystemBase {
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

    public void setByte(byte b) {
        
        storage.setState(false);
        for (int i = 0; i < 8; i++) {
            shift.setState(false);
            data.setState((b & (1 << (7 - i))) != 0);
            shift.setState(true);
        }
        storage.setState(true);
        currentByte = b;
    }


}
