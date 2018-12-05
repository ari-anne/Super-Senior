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
        Vector2 runneryPos = runner.getBodyPosition();
        ((TextureRegionDrawable)getDrawable()).setRegion(animation.getKeyFrame(stateTime+=delta, true));
        //super.setBounds(runneryPos.x, runneryPos.y, runner.getWidth(), runner.getHeight());
        super.act(delta);
    }

}
