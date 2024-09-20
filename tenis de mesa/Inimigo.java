import greenfoot.*;  // Importa as classes necessárias

public class Inimigo extends Actor
{
    public Inimigo()
    {
        // Cria a imagem do inimigo
        GreenfootImage inimigoImage = new GreenfootImage("raquete 2.png"); // Certifique-se de ter esta imagem
        setImage(inimigoImage);
    }

    public void act() 
    {
        moveRandomly();
    }

    private void moveRandomly()
    {
        // Movimentação aleatória
        if (Greenfoot.getRandomNumber(100) < 5) // 5% de chance de mudar de direção
        {
            int direction = Greenfoot.getRandomNumber(4); // 0 = cima, 1 = baixo, 2 = esquerda, 3 = direita
            switch (direction)
            {
                case 0: setLocation(getX(), getY() - 5); break; // Cima
                case 1: setLocation(getX(), getY() + 5); break; // Baixo
                case 2: setLocation(getX() - 5, getY()); break; // Esquerda
                case 3: setLocation(getX() + 5, getY()); break; // Direita
            }
        }
    }
}
