package rooms;

import javafx.application.Platform;
import scenes.GUIController;

public class Hook extends OperatableObject
{
    public Hook()
    {
        super("Hook");
    }

    public String operate()
    {
        if(!functional)
            return "Looks broken...";
        hookRunner hookR = new hookRunner();
        hookR.main();
        return "There we go! The hook should now swing down on the fiend when it's here.";
    }

    public class hookRunner
    {
        private void main()
        {
            hookRun thread = new hookRun();
            thread.setDaemon(true);
            thread.start();
            java.awt.EventQueue.invokeLater(() -> {
            });
        }
        public class hookRun extends Thread
        {

            @Override
            public void run()
            {
                if(GUIController.enemy.x == 0 && GUIController.enemy.y == 2)
                {
                    if(GUIController.getCurrentRoom().lit)
                    {
                        GUIController.enemy.hp -= 10;
                    }
                    GUIController.enemy.x = (int) (Math.random() * 3);
                    GUIController.enemy.y = (int) (Math.random() * 3);
                }
                try { Thread.sleep(7000); } catch (InterruptedException e) { e.printStackTrace(); }
            }
        }
    }
}
