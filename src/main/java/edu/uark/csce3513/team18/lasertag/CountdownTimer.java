package edu.uark.csce3513.team18.lasertag;

import java.util.Timer;
import java.util.TimerTask;

public class CountdownTimer
{
  int count = 0;
  Timer timer;
  boolean isRunning;

  public CountdownTimer(int count)
  {
    this.count = count;
    this.isRunning = false;
  }

  public void start()
  {
    if(!isRunning)
    {
      timer = new Timer();
      timer.scheduleAtFixedRate(new TimerTask() 
      { 
        public void run() 
        {
          if (count > 0) {
            count--;
          System.out.println(count);
          } 
          else 
          {
            stop();
          }
        }
      }, 0, 1000);
      isRunning = true;
    }
  }

  public void stop()
  {
    if(isRunning)
    {
      timer.cancel();
      isRunning = false;
    }
  }

  public int getCount()
  {
    return count;
  }
}
