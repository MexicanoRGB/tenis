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
        if (delayColisao > 0) {
            delayColisao--; // Reduz o tempo de espera para evitar colisões repetidas
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
        if (getY() <= 0) {
            // Colisão com o topo do mundo, inverte a direção no eixo Y
            velocidadeY = Math.abs(velocidadeY); // Garante que a bola desça
            setLocation(getX(), 1); // Corrige a posição no topo
        } else if (getY() >= getWorld().getHeight() - 1) {
            // Colisão com a base do mundo, inverte a direção no eixo Y
            velocidadeY = -Math.abs(velocidadeY); // Garante que a bola suba
            setLocation(getX(), getWorld().getHeight() - 2); // Corrige a posição na base
        }

        // Verifica colisões com as bordas laterais do mundo e inverte a direção no eixo X
        if (getX() <= 0 || getX() >= getWorld().getWidth() - 1) {
            velocidadeX = -velocidadeX;
        }
    }

    private void verificarColisoes()
    {
        if (delayColisao == 0) {
            // Verifica colisão com o Player em posições específicas
            Player player = (Player) getOneIntersectingObject(Player.class);
            if (player != null && estaProximo(player)) {
                verificarColisaoRaquete(player);
                setImage(imagemPlayer); // Troca a imagem da bola ao colidir com o player
                delayColisao = 15; // Define um tempo de espera para evitar colisões repetidas
                return;
            }

            // Verifica colisão com o Inimigo em posições específicas
            Inimigo inimigo = (Inimigo) getOneIntersectingObject(Inimigo.class);
            if (inimigo != null && estaProximo(inimigo)) {
                verificarColisaoRaquete(inimigo);
                setImage(imagemInimigo); // Troca a imagem da bola ao colidir com o inimigo
                delayColisao = 15;
            }
        }
    }

    // Verifica se a bola está realmente próxima do objeto para confirmar uma colisão
    private boolean estaProximo(Actor actor)
    {
        // Define uma distância mínima para considerar uma colisão
        int distanciaX = Math.abs(getX() - actor.getX());
        int distanciaY = Math.abs(getY() - actor.getY());
        return distanciaX < 30 && distanciaY < 30; // Distância mínima de 30 pixels
    }

    // Verifica em qual parte da raquete a bola colidiu (superior, central ou inferior)
    private void verificarColisaoRaquete(Actor raquete)
    {
        int raqueteAltura = raquete.getImage().getHeight();
        int posicaoBolaY = getY() - raquete.getY();
        
        // Aleatoriza a nova velocidadeY após a colisão
         velocidadeY = Greenfoot.getRandomNumber(10) - 10;

        if (posicaoBolaY < -raqueteAltura / 2) {
            // Colisão na parte superior da raquete
            velocidadeY = -Math.abs(velocidadeY); // Direciona a bola para cima
        } else if (posicaoBolaY > raqueteAltura / 10) {
            // Colisão na parte inferior da raquete
            velocidadeY = Math.abs(velocidadeY); // Direciona a bola para baixo
        } else {
            // Colisão na parte central da raquete
            velocidadeY = 0; // Bola continua reto no eixo Y
        }

        // Aumenta a velocidade X para garantir que a bola se mova rapidamente para longe da raquete
        velocidadeX = -velocidadeX;

         // Move a bola ligeiramente para longe da raquete para evitar colisões repetidas
        // Aqui, a movimentação no eixo Y será aleatória
        int novoY = getY() + Greenfoot.getRandomNumber(21) - 20; // Aleatório entre -30 e +30
        setLocation(getX() + (velocidadeX > 0 ? 10 : -10), novoY); 
    }
}
