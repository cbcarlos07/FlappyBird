package com.cursoandroid.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class FlappyBird extends ApplicationAdapter {
    ///private int contador = 0;
    private SpriteBatch batch;
    private Texture[] passaros;
    private Texture fundo;
    private Texture canoBaixo;
    private Texture canoTopo;
    private Random numeroRandomico;


    //Atriguto de configuração

    private int larguraDispositivo;
    private int alturaDispositivo;

    private float variacao = 0;
    private float velocidadeQueda = 0;
    private float posicaoInicialVertical  = 0;
    private float posicaoMovimentoCanoHorizontal;
    private float espacoEntreCanos;
    private float deltaTime;
    private float alturaEntreCanosRandomica;

	@Override
	public void create () {
		//Gdx.app.log("Create", "Inicializado o jogo");

        batch = new SpriteBatch();
        numeroRandomico = new Random();
        passaros =  new Texture[3];
        passaros[0] = new Texture("passaro1.png");
        passaros[1] = new Texture("passaro2.png");
        passaros[2] = new Texture("passaro3.png");

        fundo      =  new Texture("fundo.png");
        canoBaixo  =  new Texture("cano_baixo.png");
        canoTopo   =  new Texture("cano_topo.png");

        larguraDispositivo = Gdx.graphics.getWidth();
        alturaDispositivo = Gdx.graphics.getHeight();
        posicaoInicialVertical = alturaDispositivo / 2;
        posicaoMovimentoCanoHorizontal = larguraDispositivo;
        espacoEntreCanos = 300;
	}

	@Override
	public void render () {
        deltaTime = Gdx.graphics.getDeltaTime();

        /*contador++;
        Gdx.app.log("Render", "Renderizando o jogo: "+contador);*/

        variacao += deltaTime * 10;
        posicaoMovimentoCanoHorizontal -= deltaTime * 200; //velocidade do movimento do cano horizontal
        velocidadeQueda++;

        Gdx.app.log("Variacao", "Variacao: "+Gdx.graphics.getDeltaTime());

        if( variacao > 2 ){
            variacao = 0;
        }


        if(Gdx.input.justTouched()){
           //Gdx.app.log("Toque","Toque na tela");
            velocidadeQueda = -15;
        }


        if( posicaoInicialVertical > 0 || velocidadeQueda  < 0)
            posicaoInicialVertical -= velocidadeQueda;

        //verifica se o cano saiu internamente da tela
        if(posicaoMovimentoCanoHorizontal < -canoTopo.getWidth()){
            posicaoMovimentoCanoHorizontal = larguraDispositivo;
            alturaEntreCanosRandomica = numeroRandomico.nextInt(400) - 200;

        }

        batch.begin();

        //batch.draw(x, y);
        batch.draw(fundo,0,0, larguraDispositivo, alturaDispositivo);
        batch.draw( canoTopo, posicaoMovimentoCanoHorizontal, alturaDispositivo / 2 + espacoEntreCanos / 2 + alturaEntreCanosRandomica);
        batch.draw( canoBaixo, posicaoMovimentoCanoHorizontal, alturaDispositivo / 2 - canoBaixo.getHeight() - espacoEntreCanos / 2 + alturaEntreCanosRandomica);
        batch.draw( passaros[  (int) variacao ], 120, posicaoInicialVertical );

        batch.end();

	}


}
