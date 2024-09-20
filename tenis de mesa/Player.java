import greenfoot.*;  // Importa as classes necessárias

public class Player extends Actor
{
    private GreenfootImage raqueteImage;

    public Player()
    {
        // Cria a imagem do jogador
        GreenfootImage playerImage = new GreenfootImage("raquete 1.png"); // Certifique-se de ter esta imagem
        setImage(playerImage); // Preenche a imagem da raquete
    }

    public void act() 
    {
        movePlayer();
        
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
    }


    
}
