package com.cpsc.supersenior.tools;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;
import java.util.Map;

public class Gestures extends GestureDetector implements GestureDetector.GestureListener {
    // https://stackoverflow.com/questions/15185799/libgdx-get-swipe-up-or-swipe-right-etc
    // http://www.gamefromscratch.com/post/2013/10/24/LibGDX-Tutorial-5-Handling-Input-Touch-and-gestures.aspx

    public class TouchInfo {
        public float x = 0;
        public float y = 0;
        public boolean touched = false;
    }

    public static Map<Integer, TouchInfo> touches = new HashMap<Integer, TouchInfo>();

    public interface DirectionListener{
        void swipeUp();     // jump
        void swipeDown();   // crouch
        void tap();         // pressing buttons, using items
    }

    public Gestures(GestureListener listener) {
        super(listener);

    }

    public Gestures(float halfTapSquareSize, float tapCountInterval, float longPressDuration, float maxFlingDelay, GestureListener listener) {
        super(halfTapSquareSize, tapCountInterval, longPressDuration, maxFlingDelay, listener);
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        touches.get(0).touched = true;
        touches.get(0).x = x;
        touches.get(0).y = y;
        return true;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() { }
}
