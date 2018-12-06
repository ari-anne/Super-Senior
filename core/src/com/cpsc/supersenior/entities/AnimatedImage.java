package com.cpsc.supersenior.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class AnimatedImage extends Image
{
    protected Animation<TextureRegion> animation = null;
    private float stateTime = 0;
    private Runner runner;

    public AnimatedImage(Animation animation, Runner runner) {
        super((TextureRegion)animation.getKeyFrame(0));
        this.animation = animation;
        this.runner = runner;
    }

    @Override
    public void act(float delta)
    {
        Vector2 pos = runner.getBodyPosition();
        System.out.println(pos.y);
        if(pos.y < 4.0){
            setHeight(250);
            setPosition(pos.x *50+50, pos.y*90-120, 4);
        }
        else{
            setHeight(512);
            setPosition(pos.x *50+50, pos.y*90-220, 4);
        }
        ((TextureRegionDrawable)getDrawable()).setRegion(animation.getKeyFrame(stateTime+=delta, true));
        super.act(delta);
    }

}
