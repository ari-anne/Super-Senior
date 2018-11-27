package com.cpsc.supersenior.tools;

import com.badlogic.gdx.Input;

public class TextListener implements Input.TextInputListener {
    private int UserScore = 0;
    @Override
    public void input (String text) {
        DataBaseApi DBApi = new DataBaseApi();
        DBApi.set_UserScore(text, UserScore);
    }

    public void  set_UserScore(int score){
        UserScore = score;
    }

    @Override
    public void canceled () {
    }
}
