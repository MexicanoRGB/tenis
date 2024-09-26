import greenfoot.*;  // Importa as classes necessárias
public class Inimigo extends Actor
{
    private int velocidade; // Velocidade do inimigo
    public Inimigo()
    {
        // Cria a imagem do inimigo
        GreenfootImage inimigoImage = new GreenfootImage("raquete 2.png"); // Certifique-se de ter esta imagem
        inimigoImage.scale(300, 300);
        setImage(inimigoImage);
         velocidade = 3;
    }
    public void act() 
    {
        seguirBola();
    }

    private void seguirBola()
    {
        // Obtém a referência para a bola no mundo
        Bola bola = (Bola) getWorld().getObjects(Bola.class).get(0);

        // Faz o inimigo se mover na direção da bola no eixo Y (para cima ou para baixo)
        if (bola.getY() > getY()) {
            setLocation(getX(), getY() + velocidade); // Move para baixo
        } else if (bola.getY() < getY()) {
            setLocation(getX(), getY() - velocidade); // Move para cima
        }
    }
}