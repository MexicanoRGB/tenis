import greenfoot.*;  // Importa as classes necessárias

public class Inimigo extends Actor
{
    private int vidas; 
    private int pontos;

    public Inimigo()
    {
        vidas = 3;
        pontos = 0;
        
        GreenfootImage inimigoImage = new GreenfootImage("raquete 2.png"); 
        inimigoImage.scale(250, 250);
        setImage(inimigoImage);
    }

    public void act() 
    {
        moveInimigo(); 
        
        
        exibirPontos(); 
    }

    private void moveInimigo()
    {
        if (Greenfoot.isKeyDown("up")) {
            setLocation(getX(), getY() - 5); 
        }
        if (Greenfoot.isKeyDown("down")) {
            setLocation(getX(), getY() + 5);
        }
        if (Greenfoot.isKeyDown("left")) {
            setLocation(getX() - 5, getY()); 
        }
        if (Greenfoot.isKeyDown("right")) {
            setLocation(getX() + 5, getY());
        }
        
        // Impede o jogador de atravessar a metade do mapa (X > 305)
        if (getX() < 305) {
            setLocation(305, getY()); // Mantém o jogador na posição máxima permitida
        }
    }

    public boolean podeRebater(int bolaY)
    {
        int y = getY();
        int alturaRaquete = getImage().getHeight();

        if (bolaY > y - 10 && bolaY < y + 10) {
            return true; 
        }
        return false;
    }

    

    public void aumentarPontos() {
        pontos++; // Aumenta os pontos do inimigo
    }

    public int getPontos() {
        return pontos; // Retorna a pontuação atual do inimigo
    }
    
    

    

    private void exibirPontos()
    {
        getWorld().showText("Pontos Player 2: " + pontos, getWorld().getWidth() - 100, 20);
    }
}
