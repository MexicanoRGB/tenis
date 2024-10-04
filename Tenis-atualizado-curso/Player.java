import greenfoot.*;  // Importa as classes necessárias

public class Player extends Actor
{
    private int vidas; // Quantidade de vidas do jogador
    private int pontos; // Pontuação do jogador

    public Player()
    {
        // Inicializa as vidas com 3
        vidas = 3;
        
        // Cria a imagem do jogador
        GreenfootImage playerImage = new GreenfootImage("raquete 1.png"); // Certifique-se de ter esta imagem
        playerImage.scale(250, 250);
        setImage(playerImage); // Preenche a imagem da raquete
    }

    public void act() 
    {
        movePlayer();
        verificarGameOver(); // Verifica se o jogador perdeu todas as vidas
        exibirVidas(); // Exibe as vidas no canto superior esquerdo
        exibirPontos(); // Exibe os pontos no canto superior direito
    }

    private void movePlayer()
    {
        if (Greenfoot.isKeyDown("right")) // Move para a direita
        {
            setLocation(getX() + 5, getY());
        }
        if (Greenfoot.isKeyDown("left")) // Move para a esquerda
        {
            setLocation(getX() - 5, getY());
        }
        if (Greenfoot.isKeyDown("up")) // Move a raquete para cima
        {
            setLocation(getX(), getY() - 5);
        }
        if (Greenfoot.isKeyDown("down")) // Move a raquete para baixo
        {
            setLocation(getX(), getY() + 5);
        }
        
        // Impede o jogador de atravessar a metade do mapa (X > 305)
        if (getX() > 305) {
            setLocation(305, getY()); // Mantém o jogador na posição máxima permitida
        }
    }

    // Método para reduzir vida do jogador
    public void reduzirVida() 
    {
        vidas--; // Reduz uma vida
    }

    public void aumentarPontos() 
    {
        if (pontos < 10) { // Limita a pontuação a 10
            pontos++;
        }
    }
    
    // Verifica se as vidas acabaram e finaliza o jogo
    private void verificarGameOver()
    {
        if (vidas <= 0) 
        {
            Greenfoot.stop(); // Para o jogo
            getWorld().showText("Game Over!", getWorld().getWidth() / 2, getWorld().getHeight() / 2); // Exibe "Game Over" no centro
        }
    }

    // Método para exibir o número de vidas no canto superior esquerdo
    private void exibirVidas()
    {
        getWorld().showText("Vidas: " + vidas, 50, 20); // Exibe as vidas no canto superior esquerdo
    }
    
    // Método para exibir o número de pontos no canto superior direito
    private void exibirPontos()
    {
        getWorld().showText("Pontos: " + pontos, getWorld().getWidth() - 100, 20); // Exibe os pontos no canto superior direito
    }
}