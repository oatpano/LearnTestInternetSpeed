package com.example.anirutteerabut.learntestspeed;

import java.math.BigDecimal;

public class SpeedModel {
    public float progress;
    public BigDecimal uploadSpeed;
    public BigDecimal downloadSpeed;

    public SpeedModel(int progress, int uploadSpeed, int downloadSpeed) {
        this.progress = progress;
        this.uploadSpeed = uploadSpeed;
        this.downloadSpeed = downloadSpeed;
    }
}
