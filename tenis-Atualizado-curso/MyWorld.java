
import greenfoot.*;  // Importa as classes necessárias
public class MyWorld extends World
{
    public MyWorld()
    {    
        super(610, 340, 1); // Tamanho do mundo
        Player player = new Player(); // Cria uma instância do jogador
        addObject(player, 100, 200); // Adiciona o jogador ao mundo
        
        // Adiciona vários inimigos ao mundo
        
            Inimigo inimigo = new Inimigo();
            addObject(inimigo, 500, 200);
            
        Bola bola = new Bola();
        addObject(bola, 300, 170); // Adiciona a bola no centro do mundo
    }
}
