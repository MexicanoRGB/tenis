import greenfoot.*;  // Importa as classes necessárias

public class Bola extends Actor
{
    private int velocidadeX;
    private int velocidadeY;
    private int delayColisao = 0;
    private int contadorImagem = 0;
    private final int TEMPO_IMAGEM_ALTERADA = 50;

    private GreenfootImage imagemNormal;
    private GreenfootImage imagemPlayer;
    private GreenfootImage imagemInimigo;

    public Bola()
    {
        imagemNormal = new GreenfootImage("Bola1.png");
        imagemNormal.scale(40, 40);
        
        imagemPlayer = new GreenfootImage("bola2.png");
        imagemPlayer.scale(50, 50);
        
        imagemInimigo = new GreenfootImage("bola3.png");
        imagemInimigo.scale(50, 50);
        
        setImage(imagemNormal);
        iniciarVelocidade();
    }

    public void act() 
    {
        moverBola();
        verificarColisoes();
        verificarParedeEsquerda();
        verificarParedeDireita();
        
        if (delayColisao > 0) {
            delayColisao--;
        }

        if (contadorImagem > 0) {
            contadorImagem--;
            if (contadorImagem == 0) {
                setImage(imagemNormal);
            }
        }
    }

    private void iniciarVelocidade()
    {
        velocidadeX = Greenfoot.getRandomNumber(2) == 0 ? 4 : -4;
        velocidadeY = Greenfoot.getRandomNumber(2) == 0 ? 4 : -4;
    }

    private void moverBola()
    {
        setLocation(getX() + velocidadeX, getY() + velocidadeY);

        if (getX() >= getWorld().getWidth() - 1) {
            velocidadeX = -velocidadeX;
        }
        if (getY() <= 0 || getY() >= getWorld().getHeight() - 1) {
            velocidadeY = -velocidadeY;
        }
    }

    private void verificarParedeEsquerda() {
    if (getX() <= 0) { // Verifica se a bola tocou a borda esquerda
        // Adiciona pontos para o inimigo ao tocar a parede esquerda
        Inimigo inimigo = (Inimigo) getWorld().getObjects(Inimigo.class).get(0);
        inimigo.aumentarPontos(); 

        // Verifica se o Inimigo atingiu 10 pontos
        if (inimigo.getPontos() >= 10) {
            getWorld().showText("Player 2 venceu!", getWorld().getWidth() / 2, getWorld().getHeight() / 2);
            Greenfoot.stop(); // Para o jogo
        } else {
            resetarBola(); // Reposiciona a bola no centro e redefine velocidade
        }
    }
}

private void verificarParedeDireita() {
    if (getX() >= getWorld().getWidth() - 1) { // Verifica se a bola tocou a borda direita
        // Adiciona pontos para o player ao tocar a parede direita
        Player player = (Player) getWorld().getObjects(Player.class).get(0);
        player.aumentarPontos();

        // Verifica se o Player atingiu 10 pontos
        if (player.getPontos() >= 10) {
            getWorld().showText("Player 1 venceu!", getWorld().getWidth() / 2, getWorld().getHeight() / 2);
            Greenfoot.stop(); // Para o jogo
        } else {
            resetarBola(); // Reposiciona a bola no centro e redefine velocidade
        }
    }
}






    private void verificarColisoes() {
    if (delayColisao == 0) {
        // Verifica colisão com o Player
        Player player = (Player) getWorld().getObjects(Player.class).get(0);
        if (colisaoComRaquete(player) && player.podeRebater(getY())) {
            // Inverte a direção no eixo X
            velocidadeX = -velocidadeX;
            setImage(imagemPlayer); // Muda a imagem ao colidir
            contadorImagem = TEMPO_IMAGEM_ALTERADA;
            delayColisao = 20;
            return;
        }

        // Verifica colisão com o Inimigo
        Inimigo inimigo = (Inimigo) getWorld().getObjects(Inimigo.class).get(0);
        if (colisaoComRaquete(inimigo) && inimigo.podeRebater(getY())) {
            // Inverte a direção no eixo X
            velocidadeX = -velocidadeX;
            setImage(imagemInimigo); // Muda a imagem ao colidir
            contadorImagem = TEMPO_IMAGEM_ALTERADA;
            delayColisao = 20;
        }
    }
}

// Método auxiliar para verificar colisão com a raquete
private boolean colisaoComRaquete(Actor raquete) {
    int bolaX = getX();
    int bolaY = getY();
    int raqueteX = raquete.getX();
    int raqueteY = raquete.getY();
    int larguraRaquete = raquete.getImage().getWidth() / 4;
    int alturaRaquete = raquete.getImage().getHeight() / 3;

    // Verifica se a bola está nos limites horizontais da raquete (eixo X)
    boolean colisaoX = Math.abs(bolaX - raqueteX) <= larguraRaquete;
    // Verifica se a bola está nos limites verticais da raquete (eixo Y)
    boolean colisaoY = Math.abs(bolaY - raqueteY) <= alturaRaquete;

    return colisaoX && colisaoY;
}


    private void resetarBola()
    {
        setLocation(getWorld().getWidth() / 2, getWorld().getHeight() / 2);
        iniciarVelocidade();
        setImage(imagemNormal);
    }
}
