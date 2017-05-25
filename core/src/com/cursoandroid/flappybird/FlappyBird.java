package com.cursoandroid.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
    private BitmapFont fonte;


    //Atriguto de configuração

    private int larguraDispositivo;
    private int alturaDispositivo;
    private int estadoJogo = 0; // 0 -> jogo nao iniciado; 1 -> jogo iniciado
    private int pontuacao = 0;

    private float variacao = 0;
    private float velocidadeQueda = 0;
    private float posicaoInicialVertical  = 0;
    private float posicaoMovimentoCanoHorizontal;
    private float espacoEntreCanos;
    private float deltaTime;
    private float alturaEntreCanosRandomica;

    private boolean marcouPonto;

	@Override
	public void create () {
		//Gdx.app.log("Create", "Inicializado o jogo");

        batch = new SpriteBatch();
        numeroRandomico = new Random();
        fonte = new BitmapFont();
        fonte.setColor(Color.WHITE);
        fonte.getData().setScale(6);


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

        /** ESSA PRIMEIRA PARTE O PASSARO FICA ESPERANDO O PRIMEIRO TOQUE PARA COMEÇAR **/
        deltaTime = Gdx.graphics.getDeltaTime();

                /*contador++;
                Gdx.app.log("Render", "Renderizando o jogo: "+contador);*/

        variacao += deltaTime * 10;




        if( variacao > 2 ){
            variacao = 0;
        }

        /**
         * A TELA AINDA NAO FOI TOCADA
         */
        if( estadoJogo == 0 ){ // Não iniciado

            if( Gdx.input.justTouched() ){
                estadoJogo = 1;
            }

        }else{

                    posicaoMovimentoCanoHorizontal -= deltaTime * 200; //velocidade do movimento do cano horizontal
                    velocidadeQueda++;

                    Gdx.app.log("Variacao", "Variacao: "+Gdx.graphics.getDeltaTime());



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
                        marcouPonto = false;

                    }
                    // verifica a pontuação
                    if( posicaoMovimentoCanoHorizontal < 120 ){ // cano passou do passaro
                        if( !marcouPonto){
                            marcouPonto = true;
                            pontuacao++;
                        }

                    }

        }
            batch.begin();

            //batch.draw(x, y);
            batch.draw(fundo,0,0, larguraDispositivo, alturaDispositivo);
            batch.draw( canoTopo, posicaoMovimentoCanoHorizontal, alturaDispositivo / 2 + espacoEntreCanos / 2 + alturaEntreCanosRandomica);
            batch.draw( canoBaixo, posicaoMovimentoCanoHorizontal, alturaDispositivo / 2 - canoBaixo.getHeight() - espacoEntreCanos / 2 + alturaEntreCanosRandomica);
            batch.draw( passaros[  (int) variacao ], 120, posicaoInicialVertical );
            fonte.draw(batch, String.valueOf(pontuacao), larguraDispositivo / 2, alturaDispositivo - 50); //exibindo a pontuacao

            batch.end();



	}


}
