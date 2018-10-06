package com.cpsc.supersenior.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;

/* *
*   This was straight up copied from the youtube tutorial
*   https://www.youtube.com/watch?v=lMx59_D02qU&index=16&list=PLrnO5Pu2zAHKAIjRtTLAXtZKMSA6JWnmf
*   https://github.com/hollowbit/libgdx-2d-tutorial
* */

public class View {

    private OrthographicCamera cam;
    private StretchViewport viewport;

    public View (int w, int h) {
        cam = new OrthographicCamera();
        viewport = new StretchViewport(w, h, cam);
        viewport.apply();
        cam.position.set(w/2, h/2, 0);
        cam.update();
    }

    public Matrix4 combined() {
        return cam.combined;
    }

    public void update (int w, int h) {
        viewport.update(w, h);
    }

    public Vector2 getInput(){
        Vector3 screenInput = new Vector3(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY(), 0);
        Vector3 unprojected = cam.unproject(screenInput);
        return new Vector2(unprojected.x, unprojected.y);
    }

}
