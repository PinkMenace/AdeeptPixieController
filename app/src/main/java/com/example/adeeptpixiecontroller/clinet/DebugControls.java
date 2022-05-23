package com.example.adeeptpixiecontroller.clinet;

public class DebugControls {
    public int ChangeLEDColours(int r, int g, int b){
        int alpha   = 0xFF000000;
        int red     = r << 16;
        int green   = g << 8;
        int blue    = b;


        return (0xFF | red | green | blue);
    }
}
