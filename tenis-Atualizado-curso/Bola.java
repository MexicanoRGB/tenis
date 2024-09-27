import greenfoot.*;  // Importa as classes necessárias

public class Bola extends Actor
{
    private int velocidadeX; // Velocidade no eixo X
    private int velocidadeY; // Velocidade no eixo Y
    private int delayColisao = 0; // Delay para evitar colisões múltiplas
    private GreenfootImage imagemNormal; // Imagem normal da bola
    private GreenfootImage imagemPlayer; // Imagem ao colidir com o Player
    private GreenfootImage imagemInimigo; // Imagem ao colidir com o Inimigo
    public Bola()
    {
        // Carregar e ajustar o tamanho das imagens da bola
        imagemNormal = new GreenfootImage("Bola1.png");
        imagemNormal.scale(50, 50);
        
        imagemPlayer = new GreenfootImage("bola2.png");
        imagemPlayer.scale(50, 50);
        
        imagemInimigo = new GreenfootImage("bola3.png");
        imagemInimigo.scale(50, 50);
        
        // Define a imagem inicial da bola
        setImage(imagemNormal);

        // Define a velocidade inicial da bola
        iniciarVelocidade();
    }

    public void act() 
    {
        moverBola();
        verificarColisoes();
        verificarParedeEsquerda(); // Verifica se a bola tocou a parede esquerda
        verificarParedeDireita();  // Verifica se a bola tocou a parede direita
        if (delayColisao > 0) {
            delayColisao--; // Reduz o tempo de espera para a próxima colisão
        }
    }

    private void iniciarVelocidade()
    {
        // Define a velocidade inicial da bola com direção aleatória
        velocidadeX = Greenfoot.getRandomNumber(2) == 0 ? 4 : -4; // Aleatoriamente +4 ou -4
        velocidadeY = Greenfoot.getRandomNumber(2) == 0 ? 4 : -4; // Aleatoriamente +4 ou -4
    }

    private void moverBola()
    {
        setLocation(getX() + velocidadeX, getY() + velocidadeY);

        // Verifica colisões com as bordas do mundo e inverte a direção se necessário
        if (getX() >= getWorld().getWidth() - 1) {
            velocidadeX = -velocidadeX;
        }
        if (getY() <= 0 || getY() >= getWorld().getHeight() - 1) {
            velocidadeY = -velocidadeY;
        }
    }

    // Método para verificar se a bola tocou a parede esquerda
    private void verificarParedeEsquerda()
    {
        if (getX() <= 0) { // Verifica se a bola tocou a borda esquerda
            // Busca o objeto Player no mundo
            Player player = (Player) getWorld().getObjects(Player.class).get(0);
            player.reduzirVida(); // Reduz uma vida do jogador
            resetarBola(); // Reposiciona a bola no centro
        }
    }

    // Método para verificar se a bola tocou a parede direita
    private void verificarParedeDireita()
    {
        // Verifica se a bola tocou ou ultrapassou a borda direita
        if (getX() >= getWorld().getWidth() - 1) {
            // Busca o objeto Player no mundo
            Player player = (Player) getWorld().getObjects(Player.class).get(0);
            player.aumentarPontos(); // Aumenta a pontuação do jogador
            resetarBola(); // Reposiciona a bola no centro
        }
    }
    
    // Método para reposicionar a bola no centro após perder uma vida
    private void resetarBola()
    {
        setLocation(getWorld().getWidth() / 2, getWorld().getHeight() / 2); // Centraliza a bola
        iniciarVelocidade(); // Redefine a velocidade
        setImage(imagemNormal);
    }

    private void verificarColisoes()
    {
        if (delayColisao == 0) {
            // Verifica se colidiu com o Player
            Player player = (Player) getOneIntersectingObject(Player.class);
            if (player != null && estaProximo(player)) {
                velocidadeX = -velocidadeX;
                setImage(imagemPlayer); // Troca a imagem da bola ao colidir com o player
                delayColisao = 10; // Define um tempo de espera para evitar colisões repetidas
                return;
            }

            // Verifica se colidiu com o Inimigo
            Inimigo inimigo = (Inimigo) getOneIntersectingObject(Inimigo.class);
            if (inimigo != null && estaProximo(inimigo)) {
                velocidadeX = -velocidadeX;
                setImage(imagemInimigo); // Troca a imagem da bola ao colidir com o inimigo
                delayColisao = 10;
            }
        }
    }
    private boolean estaProximo(Actor actor)
    {
        // Define uma distância mínima para considerar uma colisão
        int distanciaX = Math.abs(getX() - actor.getX());
        int distanciaY = Math.abs(getY() - actor.getY());
        return distanciaX < 30 && distanciaY < 30; // Distância mínima de 30 pixels
    }
}