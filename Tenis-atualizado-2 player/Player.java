import greenfoot.*;  // Importa as classes necessárias

public class Player extends Actor
{
    private int vidas; // Quantidade de vidas do jogador
    private int pontos; // Pontuação do jogador

    public Player()
    {
        
        pontos = 0;
        
        GreenfootImage playerImage = new GreenfootImage("raquete 1.png"); 
        playerImage.scale(250, 250); // Ajusta o tamanho da raquete
        setImage(playerImage); 
    }

    public void act() 
    {
        movePlayer();
        
         
        exibirPontos(); 
    }

    private void movePlayer()
    {
        if (Greenfoot.isKeyDown("d")) 
        {
            setLocation(getX() + 5, getY());
        }
        if (Greenfoot.isKeyDown("a")) 
        {
            setLocation(getX() - 5, getY());
        }
        if (Greenfoot.isKeyDown("w")) 
        {
            setLocation(getX(), getY() - 5);
        }
        if (Greenfoot.isKeyDown("s")) 
        {
            setLocation(getX(), getY() + 5);
        }
        
        // Impede o jogador de atravessar a metade do mapa (X > 305)
        if (getX() > 305) {
            setLocation(305, getY()); // Mantém o jogador na posição máxima permitida
        }
    }

    // Método que verifica a parte da raquete que pode rebater a bola
    public boolean podeRebater(int bolaY)
    {
        int y = getY();
        int alturaRaquete = getImage().getHeight();

        // Rebater apenas se a bola estiver na região central (± 50px do centro da raquete)
        if (bolaY > y - 20 && bolaY < y + 0) {
            return true; // Parte central da raquete
        }
        return false; // Se não for a parte central, não rebate
    }

    

    public void aumentarPontos() {
        pontos++; // Aumenta os pontos do jogador
    }

    public int getPontos() {
        return pontos; // Retorna a pontuação atual do jogador
    }
    
    

    

    private void exibirPontos()
    {
        getWorld().showText("Pontos Player 1: " + pontos, 100, 20);
    }
}
