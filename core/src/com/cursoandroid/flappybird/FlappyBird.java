package com.cursoandroid.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FlappyBird extends ApplicationAdapter {
    ///private int contador = 0;
    private SpriteBatch batch;
    private Texture passaro;
    private Texture fundo;

    //Atriguto de configuração
    private int movimento = 0;
    private int larguraDispositivo;
    private int alturaDispositivo;

	@Override
	public void create () {
		//Gdx.app.log("Create", "Inicializado o jogo");

        batch = new SpriteBatch();
        passaro =  new Texture("passaro1.png");
        fundo   =  new Texture("fundo.png");

        larguraDispositivo = Gdx.graphics.getWidth();
        alturaDispositivo = Gdx.graphics.getHeight();
	}

	@Override
	public void render () {
        /*contador++;
        Gdx.app.log("Render", "Renderizando o jogo: "+contador);*/

        movimento++;

        batch.begin();
        //batch.draw(x, y);
        batch.draw(fundo,0,0, larguraDispositivo, alturaDispositivo);
        batch.draw(passaro, movimento, 400);

        batch.end();

	}


}
