
package terminalgame;

import java.awt.Font;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Pong implements Updatable, Renderable
{
    private boolean[] directions;
    private boolean next;
    private final int SCREEN_WIDTH = 800, SCREEN_HEIGHT = 600, BALL_SPEED = 10, YOU_SPEED = 5, JAMES_SPEED = 9;
    private final int BALL_SIZE = 20, PADDLE_LENGTH = 100;
    private int ballX, ballY;
    private int playerPaddleX, playerPaddleY;
    private int JamePaddleX, JamesPaddleY;
    private int playerScore;
    private int JamesScore;
    
    public Pong()
    {
        directions = new boolean[4];
        directions[0] = true;
        directions[2] = true;
        next = false;
        ballX = SCREEN_WIDTH / 2;
        ballY = SCREEN_HEIGHT / 2;
        playerPaddleX = 50;
        playerPaddleY = 250;
        JamePaddleX = SCREEN_WIDTH - 50;
        JamesPaddleY = 250;
        playerScore = 0;
        JamesScore = 0;
    }
    
    public int getHeight()
    {
        return SCREEN_HEIGHT;
    }
    
    public boolean getNext()
    {
        return next;
    }

    @Override
    public void update(InputHandler inputHandler)
    {
        try
        {
            AudioHandler scoreSound = new AudioHandler("src/audio/point.wav");
            // Player Paddle Movement
            if(inputHandler.getArrow()[0])
            {
                playerPaddleY -= YOU_SPEED;
            }
            else if(inputHandler.getArrow()[3])
            {
                playerPaddleY += YOU_SPEED;
            }
            
            // JAMES Paddle Movement
            if(ballY < JamesPaddleY + PADDLE_LENGTH / 2)
            {
                JamesPaddleY -= JAMES_SPEED;
            }
            else if(ballY > JamesPaddleY + PADDLE_LENGTH / 2)
            {
                JamesPaddleY += JAMES_SPEED;
            }
            
            // Score
            if(ballX < 0)
            {
                scoreSound.start();
                JamesScore++;
                ballX = 100;
                ballY = SCREEN_HEIGHT / 2;
                directions[2] = true;
                directions[3] = false;
            }
            
            if(ballX > SCREEN_WIDTH - BALL_SIZE)
            {
                scoreSound.start();
                playerScore++;
                ballX = 700 - BALL_SIZE;
                ballY = SCREEN_HEIGHT / 2;
                directions[2] = false;
                directions[3] = true;
            }
            
            if(playerScore >= 5)
            {
                next = true;
            }
            
            if(JamesScore >= 5)
            {
                JamesScore = 0;
            }
            
            // Paddle and Ball Collision
            if((ballX > playerPaddleX && ballX < playerPaddleX + BALL_SIZE) && (ballY > playerPaddleY && ballY < playerPaddleY + PADDLE_LENGTH) && inputHandler.getArrow()[0])
            {
                directions[0] = true;
                directions[1] = false;
                directions[2] = true;
                directions[3] = false;
            }
            else if((ballX > playerPaddleX && ballX < playerPaddleX + BALL_SIZE) && (ballY > playerPaddleY && ballY < playerPaddleY + PADDLE_LENGTH) && inputHandler.getArrow()[3])
            {
                directions[0] = false;
                directions[1] = true;
                directions[2] = true;
                directions[3] = false;
            }
            else if((ballX > playerPaddleX && ballX < playerPaddleX + BALL_SIZE) && (ballY > playerPaddleY && ballY < playerPaddleY + PADDLE_LENGTH) && !inputHandler.getArrow()[0] && !inputHandler.getArrow()[3])
            {
                directions[2] = true;
                directions[3] = false;
            }
            
            if((ballX + BALL_SIZE > JamePaddleX && ballX < JamePaddleX) && (ballY > JamesPaddleY && ballY < JamesPaddleY + PADDLE_LENGTH))
            {
                directions[2] = false;
                directions[3] = true;
            }
            
            // Y max and min
            if(ballY < 0)
            {
                directions[0] = false;
                directions[1] = true;
            }
            else if(ballY > SCREEN_HEIGHT - BALL_SIZE)
            {
                directions[0] = true;
                directions[1] = false;
            }
            
            // Change Direction
            if(directions[0])
            {
                ballY -= BALL_SPEED;
            }
            else if(directions[1])
            {
                ballY += BALL_SPEED;
            }
            
            if(directions[2])
            {
                ballX += BALL_SPEED;
            }
            else if(directions[3])
            {
                ballX -= BALL_SPEED;
            }
        }
        catch (UnsupportedAudioFileException ex)
        {
            Logger.getLogger(Pong.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex)
        {
            Logger.getLogger(Pong.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (LineUnavailableException ex)
        {
            Logger.getLogger(Pong.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void render(Graphics2D graphics, float interpolation)
    {
        // Ball
        graphics.fillRect(ballX, ballY, BALL_SIZE, BALL_SIZE);
        
        // Player Paddle
        graphics.fillRect(playerPaddleX, playerPaddleY, BALL_SIZE, PADDLE_LENGTH);
        
        // JOE Paddle
        graphics.fillRect(JamePaddleX, JamesPaddleY, BALL_SIZE, PADDLE_LENGTH);
        
        // Score
        graphics.setFont(new Font("Monospace", Font.PLAIN, 32));
        graphics.drawString("" + playerScore, 250, SCREEN_HEIGHT / 2);
        graphics.drawString("" + JamesScore, 550, SCREEN_HEIGHT / 2);
    }
    
}
